package com.lloyvet.blog.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 文章日期统计
 * @author zihao Shen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArticleDateTo {

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer articleCount;

    private String date;
}
