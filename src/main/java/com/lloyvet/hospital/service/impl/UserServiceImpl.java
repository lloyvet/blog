package com.lloyvet.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.hospital.common.TableResult;
import com.lloyvet.hospital.domain.User;
import com.lloyvet.hospital.to.UserTo;
import com.lloyvet.hospital.util.Md5Utils;
import com.lloyvet.hospital.vo.ResetPasswordVo;
import com.lloyvet.hospital.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.hospital.mapper.UserMapper;
import com.lloyvet.hospital.service.UserService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zihao Shen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(UserTo userTo) {
        String password = Md5Utils.code(userTo.getPassword());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(!StringUtils.isEmpty(userTo.getUsername()), "username", userTo.getUsername())
                .eq(!StringUtils.isEmpty(password), "password", password);
        return userMapper.selectOne(qw);
    }

    @Override
    public TableResult getAllUser(Integer page, Integer limit, UserVo userVo) {
        Page<User> pages = new Page<>(page,limit);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(userVo.getUserName()),"username",userVo.getUserName())
                .like(!StringUtils.isEmpty(userVo.getPhone()),"phone",userVo.getPhone())
                .eq("type",1);
        userMapper.selectPage(pages,qw);
        return TableResult.tableOk(pages.getRecords(), (int) pages.getTotal());
    }

    @Override
    public void resetPassword(ResetPasswordVo passwordVo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String code = Md5Utils.code(passwordVo.getOldPassword());
        if(!user.getPassword().equals(code)){
            throw new RuntimeException("初始密码错误");
        }
        if(user.getPassword().equals(code)&&passwordVo.getNewPassword().equals(passwordVo.getAgainPassword())){
            String password = Md5Utils.code(passwordVo.getAgainPassword());
            user.setPassword(password);
            userMapper.updateById(user);
        }else{
            throw new RuntimeException();
        }

    }
}

