package com.lloyvet.blog.controller.admin;

import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.service.ArticleService;
import com.lloyvet.blog.vo.ArticleAuditVo;
import com.lloyvet.blog.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * @author zihao Shen
 * 文章
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 保存文章
     * @param article
     * @return
     */
    @PostMapping("/save")
    public ResultObj save(@RequestBody Article article){
        article.setComments(0);
        article.setCommentable(article.getCommentable() != null);
        article.setTop(article.getTop() != null);
        article.setRecommend(article.getRecommend() != null);
        article.setCreateTime(new Date());
        article.setUpdateTime(article.getCreateTime());
        article.setAuthorId(1L);
        article.setViews(0);
        article.setLikes(0);
        articleService.saveArticle(article);
        return ResultObj.ok();
    }

    /**
     * 查询文章
     * @param page
     * @param limit
     * @param articleQuery
     * @return
     */
    @GetMapping("/list")
    public TableResult list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                            ArticleVo articleQuery){
        return articleService.selectPageList(page,limit,articleQuery);
    }

    /**
     * 删除单个文章
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultObj deleteArticle(@PathVariable("id") Long id){
        articleService.removeArticleAndTag(id);
        return ResultObj.ok();
    }

    /**
     * 批量删除文章
     */
    @DeleteMapping("/delete")
    public ResultObj deleteBatchArticle(@RequestBody List<Integer> idList){
        articleService.removeBatchByArticleAndTag(idList);
        return ResultObj.ok();
    }

    /**
     * 更新审核
     * @param articleAuditVo
     * @return
     */
    @PutMapping("/audit")
    public ResultObj auditArticle(@RequestBody ArticleAuditVo articleAuditVo){
        articleService.audit(articleAuditVo);
        return ResultObj.ok();
    }

    @PutMapping("/update")
    public ResultObj updateArticle(@RequestBody Article article){
        article.setCommentable(article.getCommentable() != null);
        article.setTop(article.getTop() != null);
        article.setRecommend(article.getRecommend() != null);
        article.setUpdateTime(new Date());
        article.setStatus(Constant.AUDIT_WAIT);
        articleService.saveArticle(article);
        return ResultObj.ok();
    }
}
