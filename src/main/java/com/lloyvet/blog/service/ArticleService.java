package com.lloyvet.blog.service;

import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
