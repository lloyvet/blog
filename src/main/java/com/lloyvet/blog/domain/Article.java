package com.lloyvet.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author zihao Shen
 */
@Data
@TableName(value = "t_article")
public class Article implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 摘要
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * html内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * markdown内容
     */
    @TableField(value = "text_content")
    private String textContent;

    /**
     * 封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 类型[1:原创, 2:转载, 3:翻译]
     */
    @TableField(value = "`type`")
    private Byte type;

    /**
     * 浏览量
     */
    @TableField(value = "views")
    private Integer views;

    /**
     * 点赞量
     */
    @TableField(value = "likes")
    private Integer likes;

    /**
     * 评论量
     */
    @TableField(value = "comments")
    private Integer comments;

    /**
     * 开启赞赏
     */
    @TableField(value = "appreciable")
    private Boolean appreciable;

    /**
     * 开启评论
     */
    @TableField(value = "commentable")
    private Boolean commentable;

    /**
     * 开启置顶
     */
    @TableField(value = "`top`")
    private Boolean top;

    /**
     * 开启推荐
     */
    @TableField(value = "recommend")
    private Boolean recommend;

    /**
     * 是否发布
     */
    @TableField(value = "published")
    private Boolean published;

    /**
     * 排序评分
     */
    @TableField(value = "sort")
    private Byte sort;

    /**
     * 作者ID
     */
    @TableField(value = "author_id")
    private Long authorId;

    /**
     * 分类ID
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 审核状态[0:审核未过, 1:等待审核, 2:审核通过]
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private String time;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private List<Tag> tagList;


    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_SUMMARY = "summary";

    public static final String COL_CONTENT = "content";

    public static final String COL_TEXT_CONTENT = "text_content";

    public static final String COL_COVER = "cover";

    public static final String COL_TYPE = "type";

    public static final String COL_VIEWS = "views";

    public static final String COL_LIKES = "likes";

    public static final String COL_COMMENTS = "comments";

    public static final String COL_APPRECIABLE = "appreciable";

    public static final String COL_COMMENTABLE = "commentable";

    public static final String COL_TOP = "top";

    public static final String COL_RECOMMEND = "recommend";

    public static final String COL_PUBLISHED = "published";

    public static final String COL_SORT = "sort";

    public static final String COL_AUTHOR_ID = "author_id";

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}