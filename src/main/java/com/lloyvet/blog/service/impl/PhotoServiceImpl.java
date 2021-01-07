package com.lloyvet.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.to.CategoryTo;
import com.lloyvet.blog.vo.PhotoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.mapper.PhotoMapper;
import com.lloyvet.blog.domain.Photo;
import com.lloyvet.blog.service.PhotoService;
import org.springframework.util.StringUtils;

/**
 * @author zihao Shen
 */
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService{


    @Autowired
    PhotoMapper photoMapper;

    @Override
    public TableResult selectPhotoList(Integer page, Integer limit, PhotoVo photoVo) {
        Page<Photo> photoPage = new Page<>(page, limit);
        QueryWrapper<Photo> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(photoVo.getDescription()), "description", photoVo.getDescription())
                .between(!StringUtils.isEmpty(photoVo.getStartDate()) && !StringUtils.isEmpty(photoVo.getEndDate()),
                        "update_time",
                        photoVo.getStartDate(), photoVo.getEndDate());
        photoPage = photoMapper.selectPage(photoPage, qw);
        return TableResult.tableOk(photoPage.getRecords(),Integer.parseInt(String.valueOf(photoPage.getTotal())));
    }
}
