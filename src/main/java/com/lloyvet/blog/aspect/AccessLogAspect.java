package com.lloyvet.blog.aspect;

import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.domain.AccessLog;
import com.lloyvet.blog.domain.User;
import com.lloyvet.blog.service.AccessLogService;
import com.lloyvet.blog.util.RequestHolder;
import com.lloyvet.blog.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zihao Shen
 * 访问日志切面
 */
@Aspect
@Component
public class AccessLogAspect {

    @Autowired
    AccessLogService accessLogService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 切点
     */
    @Pointcut("@annotation(com.lloyvet.blog.annotation.AccessLogs)")
    public void logPointCut(){};

    /**
     * 配置环绕通知
     */
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        AccessLog log = new AccessLog("INFO", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        log.setStatus(Constant.SUCCESS);
        User user = (User)request.getSession().getAttribute(Constant.USER);
        String userName = "";
        if(user != null){
            userName = user.getUsername();
        }
        accessLogService.save(userName, StringUtils.getBrowser(request), StringUtils.getIp(request), joinPoint, log);
        return result;
    }
}
