package com.lloyvet.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.vo.VisitorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.mapper.VisitorMapper;
import com.lloyvet.blog.domain.Visitor;
import com.lloyvet.blog.service.VisitorService;
import org.springframework.util.StringUtils;

/**
 * @author zihao Shen
 */
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {


    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public Page<Visitor> listTableByPage(Integer page, Integer limit, VisitorVo visitorVo) {
        Page<Visitor> visitorPage = new Page<>(page,limit);
        QueryWrapper<Visitor> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(visitorVo.getUserName()),"username",visitorVo.getUserName())
                .between(!StringUtils.isEmpty(visitorVo.getStartDate())&&!StringUtils.isEmpty(visitorVo.getEndDate()),"update_time",
                        visitorVo.getStartDate(),visitorVo.getEndDate());
        visitorMapper.selectPage(visitorPage,qw);
        return visitorPage;
    }
}

