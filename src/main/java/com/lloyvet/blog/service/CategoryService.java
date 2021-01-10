package com.lloyvet.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.blog.vo.CategoryVo;

import java.util.List;

/**
 * @author zihao Shen
 */
public interface CategoryService extends IService<Category> {


    /**
     * 查询分类
     *
     * @param page
     * @param limit
     * @param categoryVo
     * @return
     */
    TableResult selectCategoryList(Integer page, Integer limit, CategoryVo categoryVo);

    /**
     * 分类列表
     * @return
     */
    List<Category> listByArticleCount();

    /**
     * 通过分类id获取文章
     * @param current
     * @param size
     * @param id
     * @return
     */
    Page<Article> listPreviewPageByTagId(Integer current, Integer size, Long id);

    /**
     * 获取有文章关联的目录
     * @return
     */
    Integer getCount();

}

