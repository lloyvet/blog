package com.lloyvet.blog.service;

import com.lloyvet.blog.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.blog.to.UserTo;

/**
 * @author zihao Shen
 */
public interface UserService extends IService<User>{


    /**
     * 用户登录
     * @param userTo
     * @return
     */
    User login(UserTo userTo);
}
