package com.lloyvet.hospital.to;

import lombok.Data;

import java.util.List;

/**
 * 后台主页数据
 * @author zihao Shen
 */
@Data
public class IndexTo {

    /**
     * 文章数量
     */
    private Long articleCount;

    /**
     * 分类数量
     */
    private Long categoryCount;

    /**
     * 标签数量
     */
    private Long tagCount;

    /**
     *评论数量
     */
    private Integer commentCount;

    /**
     *用户数量
     */
    private Integer userCount;

    /**
     *访客数量
     */
    private Integer visitorCount;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 留言数量
     */
    private Integer messageCount;


    /**
     * 最近文章列表
     */
    private List<Object> articles;



}
