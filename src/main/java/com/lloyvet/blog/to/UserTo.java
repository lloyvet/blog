package com.lloyvet.blog.to;

import lombok.Data;

/**
 * @author zihao Shen
 */
@Data
public class UserTo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String verifyCode;
}
