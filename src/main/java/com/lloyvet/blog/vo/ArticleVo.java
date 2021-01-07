package com.lloyvet.blog.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * 文章查询参数
 * @author zihao Shen
 */
@Data
public class ArticleVo implements Serializable {
    /**
     * 标题
     */
    private String title;
    /**
     * 类型[1:原创, 2:转载, 3:翻译]
     */
    private Integer type;
    /**
     * 分类ID
     */
    private Long categoryId;
    /**
     * 是否发布
     */
    private Boolean published;
    /**
     * 审核状态[0:审核未过, 2:等待审核, 3:审核通过]
     */
    private Integer status;
    /**
     * 是否置顶
     */
    private Boolean top;
    /**
     * 是否推荐
     */
    private Boolean recommend;
    /**
     * 开始创建日期
     */
    private String startDate;
    /**
     * 结束创建日期
     */
    private String endDate;
}
