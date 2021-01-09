package com.lloyvet.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.mapper.ArticleMapper;
import com.lloyvet.blog.to.CategoryTo;
import com.lloyvet.blog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.mapper.CategoryMapper;
import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.service.CategoryService;
import org.springframework.util.StringUtils;

/**
 * @author zihao Shen
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public TableResult selectCategoryList(Integer page, Integer limit, CategoryVo categoryVo) {
        Page<Category> categoryPage = new Page<Category>(page, limit);
        QueryWrapper<Category> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(categoryVo.getName()), "name", categoryVo.getName())
                .eq(null != categoryVo.getDisplay(), "display", categoryVo.getDisplay())
                .between(!StringUtils.isEmpty(categoryVo.getStartDate()) && !StringUtils.isEmpty(categoryVo.getEndDate()),
                        "update_time",
                        categoryVo.getStartDate(), categoryVo.getEndDate());
        categoryPage = categoryMapper.selectPage(categoryPage, qw);
        List<Category> categories = categoryPage.getRecords();
        List<CategoryTo> categoryTos = null;
        if (categories != null && categories.size() > 0) {
            categoryTos = categories.stream().map(category -> {
                CategoryTo categoryTo = new CategoryTo();
                BeanUtil.copyProperties(category, categoryTo);
                Integer articleCount = articleMapper.selectCount(new QueryWrapper<Article>().eq("category_id", category.getId()));
                categoryTo.setArticleCount(articleCount);
                return categoryTo;
            }).collect(Collectors.toList());
        }
        return TableResult.tableOk(categoryTos,Integer.parseInt(String.valueOf(categoryPage.getTotal())));
    }

    @Override
    public List<Category> listByArticleCount() {
        List<Category> categories = this.list();
        for (Category category : categories) {
            Integer articleCount = articleMapper.selectCount(new QueryWrapper<Article>().eq("category_id", category.getId()));
            category.setArticleCount(articleCount);
        }
        categories = categories.stream().filter(category -> category.getArticleCount()!=0).collect(Collectors.toList());
        return categories;
    }

    @Override
    public Page<Article> listPreviewPageByTagId(Integer current, Integer size, Long id) {
        Page<Article> page = new Page<>(current, size);
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.eq("category_id",id).orderByDesc("create_time");
        articleMapper.selectPage(page,qw);
        for (Article article : page.getRecords()) {
            article.setCategory(categoryMapper.selectById(id));
        }
        return page;
    }
}

