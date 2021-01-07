package com.lloyvet.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.domain.LoginLog;
import com.lloyvet.blog.mapper.LoginLogMapper;
import com.lloyvet.blog.service.LoginLogService;

/**
 * @author zihao Shen
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}

