package com.lloyvet.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author zihao Shen
 */
@Data
@TableName(value = "sys_menu")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu implements Serializable {
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
     * 链接
     */
    @TableField(value = "href")
    private String href;

    /**
     * 父级菜单ID
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;


    /**
     * 排序值
     */
    @TableField(value = "sort")
    private Short sort;

    /**
     * 类型[1:目录, 2:菜单, 3:按钮]
     */
    @TableField(value = "`type`")
    private Byte type;

    /**
     * 是否显示
     */
    @TableField(value = "`status`")
    private Boolean status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 页面跳转方式
     */
    @TableField(value = "target")
    private String target;

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

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_HREF = "href";

    public static final String COL_PID = "pid";

    public static final String COL_ICON = "icon";

    public static final String COL_SORT = "sort";

    public static final String COL_TYPE = "type";

    public static final String COL_STATUS = "status";

    public static final String COL_REMARK = "remark";

    public static final String COL_TARGET = "target";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public interface Table {
        String ID = "id";
        String TITLE = "title";
        String HREF = "href";
        String PID = "pid";
        String ICON = "icon";
        String SORT = "sort";
        String TYPE = "type";
        String STATUS = "status";
        String REMARK = "remark";
        String TARGET = "target";
        String CREATE_TIME = "create_time";
        String UPDATE_TIME = "update_time";
    }
}