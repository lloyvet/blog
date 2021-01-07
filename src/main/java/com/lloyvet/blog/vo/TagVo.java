package com.lloyvet.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zihao Shen
 */
@Data
public class TagVo implements Serializable {
    private String name;

    private String startDate;

    private String endDate;
}
