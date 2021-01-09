package com.lloyvet.blog.to;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Article;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 归档页面数据
 * @author zihao Shen
 */
@Data
public class ArchivesTo implements Serializable {

    private List<ArticleDateTo> articleDates;

    private Page<Article> pageInfo;
}
