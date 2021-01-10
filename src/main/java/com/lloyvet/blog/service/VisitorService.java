package com.lloyvet.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Visitor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.blog.vo.VisitorVo;

public interface VisitorService extends IService<Visitor> {


    /**
     * 查询访客
     * @param page
     * @param limit
     * @param visitorVo
     * @return
     */
    Page<Visitor> listTableByPage(Integer page, Integer limit, VisitorVo visitorVo);
}

