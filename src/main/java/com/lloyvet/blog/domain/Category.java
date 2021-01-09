package com.lloyvet.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@TableName(value = "t_category")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Category implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 是否前台显示
     */
    @TableField(value = "display")
    private Boolean display;

    /**
     * 颜色
     */
    @TableField(value = "color")
    private String color;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private Integer articleCount;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_DISPLAY = "display";

    public static final String COL_COLOR = "color";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}