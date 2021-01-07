package com.lloyvet.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lloyvet.blog.to.UserTo;
import com.lloyvet.blog.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.domain.User;
import com.lloyvet.blog.mapper.UserMapper;
import com.lloyvet.blog.service.UserService;
import org.springframework.util.StringUtils;

/**
 * @author zihao Shen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(UserTo userTo) {
        String password = Md5Utils.code(userTo.getPassword());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(!StringUtils.isEmpty(userTo.getUsername()),"username",userTo.getUsername())
                .eq(!StringUtils.isEmpty(password),"password",password);
        return userMapper.selectOne(qw);
    }
}
