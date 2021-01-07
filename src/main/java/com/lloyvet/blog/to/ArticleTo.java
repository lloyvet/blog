package com.lloyvet.blog.to;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.domain.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 文章查询参数
 * @author zihao Shen
 */
@Data
public class ArticleTo implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * html内容
     */
    private String content;

    /**
     * markdown内容
     */
    private String textContent;

    /**
     * 封面
     */
    private String cover;

    /**
     * 类型[1:原创, 2:转载, 3:翻译]
     */
    private Byte type;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 点赞量
     */
    private Integer likes;

    /**
     * 评论量
     */
    private Integer comments;

    /**
     * 开启赞赏
     */
    private Boolean appreciable;

    /**
     * 开启评论
     */
    private Boolean commentable;

    /**
     * 开启置顶
     */
    private Boolean top;

    /**
     * 开启推荐
     */
    private Boolean recommend;

    /**
     * 是否发布
     */
    private Boolean published;

    /**
     * 排序评分
     */
    private Byte sort;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 分类名
     */
    private Category category;

    /**
     * 标签
     */
    private List<Tag> tagList;

    /**
     * 审核状态[0:审核未过, 1:等待审核, 2:审核通过]
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
