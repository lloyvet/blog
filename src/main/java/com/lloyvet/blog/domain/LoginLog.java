package com.lloyvet.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_loginLog")
public class LoginLog implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 登录名
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 登录ip
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 是否登录成功
     */
    @TableField(value = "login_ok")
    private Integer loginOk;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_LOGIN_NAME = "login_name";

    public static final String COL_LOGIN_IP = "login_ip";

    public static final String COL_LOGIN_OK = "login_ok";

    public static final String COL_LOGIN_TIME = "login_time";
}