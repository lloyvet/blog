package com.lloyvet.blog.service;

import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.blog.vo.CategoryVo;

/**
 * @author zihao Shen
 */
public interface CategoryService extends IService<Category> {


    /**
     * 查询目录
     *
     * @param page
     * @param limit
     * @param categoryVo
     * @return
     */
    TableResult selectCategoryList(Integer page, Integer limit, CategoryVo categoryVo);
}

