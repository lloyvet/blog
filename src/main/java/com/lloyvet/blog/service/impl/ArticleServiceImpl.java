package com.lloyvet.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.ArticleTag;
import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.domain.Tag;
import com.lloyvet.blog.mapper.ArticleTagMapper;
import com.lloyvet.blog.mapper.CategoryMapper;
import com.lloyvet.blog.mapper.TagMapper;
import com.lloyvet.blog.to.ArticleTo;
import com.lloyvet.blog.vo.ArticleAuditVo;
import com.lloyvet.blog.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.mapper.ArticleMapper;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.service.ArticleService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author zihao Shen
 */


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Autowired
    ThreadPoolExecutor poolExecutor;

    @Override
    public TableResult selectPageList(Integer pageNum, Integer limit, ArticleVo articleQuery) {
        Page<Article> page = new Page<>(pageNum,limit);
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(articleQuery.getTitle()),"title",articleQuery.getTitle())
                .eq(articleQuery.getType()!=null,"type",articleQuery.getType())
                .eq(articleQuery.getCategoryId()!=null,"category_id",articleQuery.getCategoryId())
                .eq(articleQuery.getPublished()!=null,"published",articleQuery.getPublished())
                .eq(articleQuery.getStatus()!=null,"status",articleQuery.getStatus())
                .eq(articleQuery.getTop()!=null,"top",articleQuery.getTop())
                .eq(articleQuery.getRecommend()!=null,"recommend",articleQuery.getRecommend());
        qw.between(!StringUtils.isEmpty(articleQuery.getStartDate())&&!StringUtils.isEmpty(articleQuery.getEndDate()),"update_time",
                articleQuery.getStartDate(),articleQuery.getEndDate());
        //分页查询文章
        Page<Article> articlePage = articleMapper.selectPage(page, qw);
        //查询到的文章
        List<Article> articles = articlePage.getRecords();
        //返回的文章
        List<ArticleTo> articleTos = null;
        if(articles!=null&&articles.size()>0){
            articleTos = articles.stream().map(article -> {
                ArticleTo articleTo = new ArticleTo();
                BeanUtil.copyProperties(article, articleTo);
                //异步任务，任务一查找文章对应的目录名
                CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
                    Category category = categoryMapper.selectById(article.getCategoryId());
                    articleTo.setCategory(category);
                }, poolExecutor);
                //任务二查找文章对应的标签
                CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
                    List<ArticleTag> articleTagList = articleTagMapper.selectList(
                            new QueryWrapper<ArticleTag>().eq("article_id", article.getId()));
                    List<Long> tagIds = articleTagList.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
                    List<Tag> tagList = tagMapper.selectBatchIds(tagIds);
                    if (tagList != null && tagList.size() > 0) {
                        articleTo.setTagList(tagList);
                    }
                }, poolExecutor);
                try {
                    CompletableFuture.allOf(task1, task2).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return articleTo;
            }).collect(Collectors.toList());
        }
        return TableResult.tableOk(articleTos,Integer.valueOf(String.valueOf(page.getTotal())));
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void saveArticle(Article article) {
        //存在新标签则添加新标签
        List<Tag> newTagList = article.getTagList().stream().filter(t -> (t.getId() == null)).collect(Collectors.toList());
        for (Tag newTag : newTagList) {
            //添加标签
            newTag.setColor(Constant.DEFAULT_COLOR);
            newTag.setCreateTime(new Date());
            newTag.setUpdateTime(newTag.getCreateTime());
            tagMapper.insert(newTag);
        }
        if (article.getId() == null) {
            //新增
            articleMapper.insert(article);
        } else {
            //更新
            //更新文章信息
            articleMapper.updateById(article);
            //删除原有标签
            QueryWrapper<ArticleTag> articleTagWrapper = new QueryWrapper<>();
            articleTagWrapper.eq("article_id", article.getId());
            articleTagMapper.delete(articleTagWrapper);
        }
        //添加新标签
        List<Long> tagIdList = article.getTagList().stream().map(Tag::getId).collect(Collectors.toList());
        articleTagMapper.insertBatch(article.getId(), tagIdList);
    }

    @Override
    public void audit(ArticleAuditVo articleAuditVo) {
        Article article = new Article();
        article.setId(articleAuditVo.getId());
        article.setStatus(articleAuditVo.getStatus());
        articleMapper.updateById(article);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void removeArticleAndTag(Long id) {
        //删除对应文章
        articleMapper.deleteById(id);
        //删除文章对应的article_tag表中的内容
        QueryWrapper<ArticleTag> qw = new QueryWrapper<>();
        qw.eq("article_id",id);
        articleTagMapper.delete(qw);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void removeBatchByArticleAndTag(List<Integer> idList) {
        //批量删除文章
        articleMapper.deleteBatchIds(idList);
        //批量删除文章对应的article_tag表中的内容
        QueryWrapper<ArticleTag> qw = new QueryWrapper<>();
        qw.in("article_id",idList);
        articleTagMapper.delete(qw);
    }
}
