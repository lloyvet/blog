package com.lloyvet.blog.service.impl;

import com.lloyvet.blog.annotation.AccessLogs;
import com.lloyvet.blog.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.domain.AccessLog;
import com.lloyvet.blog.mapper.AccessLogMapper;
import com.lloyvet.blog.service.AccessLogService;
/**
 * @author zihao Shen
 */
@Service
public class AccessLogServiceImpl extends ServiceImpl<AccessLogMapper, AccessLog> implements AccessLogService{


    @Autowired
    AccessLogMapper accessLogMapper;

    @Override
    public void save(String userName, String browser, String ip, ProceedingJoinPoint joinPoint, AccessLog log) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AccessLogs aopLogs = (AccessLogs) method.getAnnotation(AccessLogs.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // 描述
        if (log != null) {
            log.setDescription(aopLogs.value());
        }
        assert log != null;
        log.setRequestIp(ip);

        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                assert argValues != null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.setAddress("");
        log.setMethod(methodName);
        log.setUsername(userName);
        log.setParams(params.toString() + " }");
        log.setBrowser(browser);
        log.setCreateTime(new Date());
        accessLogMapper.insert(log);
    }
}
