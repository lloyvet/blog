package com.lloyvet.blog.service;

import com.lloyvet.blog.domain.AccessLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author zihao Shen
 */
public interface AccessLogService extends IService<AccessLog>{


    /**
     * 保存访问日志
     * @param userName
     * @param browser
     * @param ip
     * @param joinPoint
     * @param log
     */
    void save(String userName, String browser, String ip, ProceedingJoinPoint joinPoint, AccessLog log);
}
