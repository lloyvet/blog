package com.lloyvet.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_access_log")
public class AccessLog implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 异常详情
     */
    @TableField(value = "exception_detail")
    private String exceptionDetail;

    /**
     * 日志类型[info, error...]
     */
    @TableField(value = "log_type")
    private String logType;

    /**
     * 执行方法名称
     */
    @TableField(value = "`method`")
    private String method;

    /**
     * 参数名称
     */
    @TableField(value = "params")
    private String params;

    /**
     * 请求IP
     */
    @TableField(value = "request_ip")
    private String requestIp;

    /**
     * 请求耗时
     */
    @TableField(value = "`time`")
    private Long time;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 浏览器
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * IP来源
     */
    @TableField(value = "address")
    private String address;

    /**
     * 请求结果[0:失败, 1:成功]
     */
    @TableField(value = "`status`")
    private Byte status;

    public AccessLog(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_EXCEPTION_DETAIL = "exception_detail";

    public static final String COL_LOG_TYPE = "log_type";

    public static final String COL_METHOD = "method";

    public static final String COL_PARAMS = "params";

    public static final String COL_REQUEST_IP = "request_ip";

    public static final String COL_TIME = "time";

    public static final String COL_USERNAME = "username";

    public static final String COL_BROWSER = "browser";

    public static final String COL_ADDRESS = "address";

    public static final String COL_STATUS = "status";
}