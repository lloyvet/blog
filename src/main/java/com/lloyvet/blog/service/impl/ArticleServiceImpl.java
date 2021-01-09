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
import com.lloyvet.blog.to.ArticleDateTo;
import com.lloyvet.blog.to.ArticleTo;
import com.lloyvet.blog.to.HomeTo;
import com.lloyvet.blog.vo.ArticleAuditVo;
import com.lloyvet.blog.vo.ArticleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
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


@Slf4j
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

    @Override
    public List<Article> selectHotArticle() {
        Page<Article> page = new Page<>(0,3);
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.orderByDesc(Article.COL_VIEWS).select(Article.COL_ID,Article.COL_TITLE,Article.COL_SUMMARY,Article.COL_COVER);
        articleMapper.selectPage(page,qw);
        return page.getRecords();
    }

    @Override
    public Article getArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        //分类
        Category category = categoryMapper.selectById(article.getCategoryId());
        article.setCategory(category);
        //标签
        List<Long> tagIds = articleTagMapper.selectList(new QueryWrapper<ArticleTag>()
                .eq("article_id", article.getId())).stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        List<Tag> tags = tagMapper.selectList(new QueryWrapper<Tag>().in("id", tagIds));
        article.setTagList(tags);
        //增加浏览量
        incrViewById(id);
        return article;
    }

    @Override
    public void incrLikes(Long id) {
        Article article = articleMapper.selectById(id);
        article.setLikes(article.getLikes()+1);
        articleMapper.updateById(article);
    }

    @Override
    public Page<Article> listPreviewPageByTagId(Integer current, Integer size, Long id) {
        Page<Article> page = new Page<>(current, size);
        List<ArticleTag> articleTags = articleTagMapper.selectList(new QueryWrapper<ArticleTag>().eq("tag_id", id));
        List<Long> articleIds = articleTags.stream().map(ArticleTag::getArticleId).collect(Collectors.toList());
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.in("id",articleIds).orderByDesc("create_time");
        articleMapper.selectPage(page,qw);
        List<Article> articles = page.getRecords();
        for (Article article : articles) {
            article.setCategory(categoryMapper.selectById(article.getCategoryId()));
        }
        return page;
    }

    @Override
    public List<ArticleDateTo> getCountByDate() {
        return articleMapper.selectByDate();
    }

    @Override
    public List<Article> listRecommend() {
        //通过likes排序
        return articleMapper.selectArticleAndSetCategory("likes",0,4);
    }

    @Override
    public Page<Article> listPreviewByPage(Integer current, Integer size) {
        Page<Article> articlePage = new Page<>(current, size);
        articlePage.setTotal(this.count());
        List<Article> articles = articleMapper.selectArticleAndSetCategory("create_time", (current-1)*size, size);
        for (Article article : articles) {
            //标签
            List<Long> tagIds = articleTagMapper.selectList(new QueryWrapper<ArticleTag>()
                    .eq("article_id", article.getId())).stream().map(ArticleTag::getTagId).collect(Collectors.toList());
            List<Tag> tags = tagMapper.selectList(new QueryWrapper<Tag>().in("id", tagIds));
            article.setTagList(tags);
        }
        articlePage.setRecords(articles);
        return articlePage;
    }

    @Override
    public List<Article> selectListByKeyWord(String keyWord) {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.like("title",keyWord).or().like("content",keyWord)
        .select(Article.COL_ID,Article.COL_TITLE,Article.COL_SUMMARY,Article.COL_CONTENT);
        List<Article> articles = articleMapper.selectList(qw);
        for (Article article : articles) {
            article.setContent(new StringBuilder(article.getContent()).substring(0,300));
        }
        return articles;
    }

    @Override
    public HomeTo getIndexArticle() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        HomeTo homeTo = new HomeTo();
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            homeTo.setTopArticles(this.selectHotArticle());
        }, poolExecutor);
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            homeTo.setRecommendArticles(this.listRecommend());
        }, poolExecutor);
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
            homeTo.setPageInfo(this.listPreviewByPage(1, 6));
        }, poolExecutor);
        try {
            CompletableFuture.allOf(task1,task2,task3).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        date = new Date();
        log.info(format);
        String format1 = sdf.format(date);
        log.info(format1);
        return homeTo;
    }


    /**
     * 增加浏览量
     * @param id
     */
    private void incrViewById(Long id) {
        Article article = articleMapper.selectById(id);
        article.setViews(article.getViews()+1);
        articleMapper.updateById(article);
    }

    /**
     * 设置文章分类和标签
     * @param articles
     */
    public void articleSetCategoryAndTag(List<Article> articles){
        for (Article article : articles) {
            //分类
            article.setCategory(categoryMapper.selectById(article.getCategoryId()));
            //标签
            List<Long> tagIds = articleTagMapper.selectList(new QueryWrapper<ArticleTag>()
                    .eq("article_id", article.getId())).stream().map(ArticleTag::getTagId).collect(Collectors.toList());
            List<Tag> tags = tagMapper.selectList(new QueryWrapper<Tag>().in("id", tagIds));
            article.setTagList(tags);
        }
    }
}
