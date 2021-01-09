package com.lloyvet.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.mapper.VisitorMapper;
import com.lloyvet.blog.domain.Visitor;
import com.lloyvet.blog.service.VisitorService;
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService{

}
