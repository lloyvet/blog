package com.lloyvet.blog.aspect;


import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.domain.LoginLog;
import com.lloyvet.blog.domain.User;
import com.lloyvet.blog.service.LoginLogService;
import com.lloyvet.blog.util.RequestHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 登录日志
 * @author zihao Shen
 */

@Aspect
@Component
public class LoginAopConfig {

    @Autowired
    LoginLogService loginLogService;

    @Pointcut("execution(* com.lloyvet.blog.controller.admin.LoginController.login(*,*))")
    private void loginPointcut(){}


    @Around("loginPointcut()")
    public Object loginAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ResultObj result = (ResultObj)joinPoint.proceed();
        LoginLog loginLog = new LoginLog();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        String ip = request.getRemoteAddr();
        User user = (User)request.getSession().getAttribute(Constant.USER);
        loginLog.setLoginIp(ip);
        loginLog.setLoginName(user.getUsername());
        loginLog.setLoginTime(new Date());
        loginLog.setLoginOk(result.getCode()!=-1?1:0);
        loginLogService.save(loginLog);
        return result;
    }
}
