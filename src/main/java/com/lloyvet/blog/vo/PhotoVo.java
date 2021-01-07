package com.lloyvet.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zihao Shen
 */
@Data
public class PhotoVo implements Serializable {

    private String description;

    private String startDate;

    private String endDate;
}
