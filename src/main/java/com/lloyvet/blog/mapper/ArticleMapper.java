package com.lloyvet.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.to.ArticleDateTo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zihao Shen
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据日期获取文章数量
     *
     * @return
     */
    List<ArticleDateTo> selectByDate();

    /**
     * 获取文章并设置分类
     *
     * @param condition
     * @param limit
     * @param size
     * @return
     */
    List<Article> selectArticleAndSetCategory(@Param("condition") String condition, @Param("limit") int limit, @Param("size") int size);

}