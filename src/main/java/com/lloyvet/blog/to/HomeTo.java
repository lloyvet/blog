package com.lloyvet.blog.to;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Article;
import lombok.Data;

import java.util.List;

/**
 * 前台主页数据
 * @author zihao Shen
 */
@Data
public class HomeTo {

    /**
     * 置顶文章列表
     */
    private List<Article> topArticles;
    /**
     *推荐文章列表
     */
    private List<Article> recommendArticles;
    /**
     *文章分页
     */
    private Page<Article> pageInfo;
}
