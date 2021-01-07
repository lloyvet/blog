package com.lloyvet.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author zihao Shen
 */
@Data
@TableName(value = "t_article_tag")
public class ArticleTag implements Serializable {
    /**
     * 文章ID
     */
    @TableId(value = "article_id", type = IdType.INPUT)
    private Long articleId;

    /**
     * 标签ID
     */
    @TableId(value = "tag_id", type = IdType.INPUT)
    private Long tagId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ARTICLE_ID = "article_id";

    public static final String COL_TAG_ID = "tag_id";
}