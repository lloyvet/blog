package com.lloyvet.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zihao Shen
 */
@Data
public class CategoryVo implements Serializable {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 前台显示
     */
    private Boolean display;

    private String startDate;

    private String endDate;
}
