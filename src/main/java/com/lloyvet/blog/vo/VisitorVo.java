package com.lloyvet.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zihao Shen
 */
@Data
public class VisitorVo implements Serializable {

    /**
     * 分类名称
     */
    private String userName;

    /**
     * 前台显示
     */
    private String email;

    private String startDate;

    private String endDate;
}
