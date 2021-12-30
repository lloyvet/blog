package com.lloyvet.hospital.service;

import com.lloyvet.hospital.common.TableResult;
import com.lloyvet.hospital.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.hospital.to.UserTo;
import com.lloyvet.hospital.vo.ResetPasswordVo;
import com.lloyvet.hospital.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zihao Shen
 */
public interface UserService extends IService<User> {


    /**
     * 用户登录
     *
     * @param userTo
     * @return
     */
    User login(UserTo userTo);

    TableResult getAllUser(Integer page, Integer limit, UserVo userVo);

    /**
     * 更新密码
     * @param passwordVo
     * @param request
     * @return
     */
    void resetPassword(ResetPasswordVo passwordVo, HttpServletRequest request);
}

