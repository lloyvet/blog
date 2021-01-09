package com.lloyvet.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author zihao Shen
 */
@Data
@TableName(value = "t_comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 父级评论id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 访客id
     */
    @TableField(value = "visitor_id")
    private Long visitorId;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 审核状态[0:审核未过, 1:等待审核, 2:审核通过]
     */
    @TableField(value = "`status`")
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * ip来源
     */
    @TableField(value = "address")
    private String address;

    /**
     * 父级评论昵称
     */
    @TableField(value = "parent_nickname")
    private String parentNickname;

    @TableField(exist = false)
    private List<Comment> children;

    @TableField(exist = false)
    private Visitor visitor;


    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PID = "pid";

    public static final String COL_ARTICLE_ID = "article_id";

    public static final String COL_VISITOR_ID = "visitor_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_ADDRESS = "address";

    public static final String COL_PARENT_NICKNAME = "parent_nickname";
}