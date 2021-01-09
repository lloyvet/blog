package com.lloyvet.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.blog.to.ArticleDateTo;
import com.lloyvet.blog.to.HomeTo;
import com.lloyvet.blog.vo.ArticleAuditVo;
import com.lloyvet.blog.vo.ArticleVo;

import java.util.List;

/**
 * @author zihao Shen
 */
public interface ArticleService extends IService<Article>{


    /**
     * 查询文章
     * @param page
     * @param limit
     * @param articleQuery
     * @return
     */
    TableResult selectPageList(Integer page, Integer limit, ArticleVo articleQuery);

    /**
     * 保存文章
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 审核文章
     * @param articleAuditVo
     */
    void audit(ArticleAuditVo articleAuditVo);

    /**
     * 删除文章和对应的标签关联
     * @param id
     */
    void removeArticleAndTag(Long id);

    /**
     * 批量删除文章
     * @param idList
     */
    void removeBatchByArticleAndTag(List<Integer> idList);

    /**
     * 获取热门文章
     * @return
     */
    List<Article> selectHotArticle();

    /**
     * 通过文章id查询文章
     * @param id
     * @return
     */
    Article getArticleById(Long id);

    /**
     * 点赞文章
     * @param id
     */
    void incrLikes(Long id);

    /**
     * 通过标签查询文章
     * @param current
     * @param size
     * @param id
     * @return
     */
    Page<Article> listPreviewPageByTagId(Integer current, Integer size, Long id);

    /**
     * 根据日期统计文章数量
     * @return
     */
    List<ArticleDateTo> getCountByDate();


    /**
     * 查询推荐文章列表
     * @return
     */
    List<Article> listRecommend();

    /**
     * 分页查询所有文章
     *
     * @param current 当前页码
     * @param size    页码大小
     * @return 文章列表
     */
    Page<Article> listPreviewByPage(Integer current, Integer size);

    /**
     * 通过keyWord查询文章
     * @param keyWord
     * @return
     */
    List<Article> selectListByKeyWord(String keyWord);


    /**
     * 获取首页资源
     * @return
     */
    HomeTo getIndexArticle();
}
