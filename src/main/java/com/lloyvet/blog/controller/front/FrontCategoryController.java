package com.lloyvet.blog.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.domain.Tag;
import com.lloyvet.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zihao Shen
 */
@Controller
@RequestMapping("category")
public class FrontCategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> category() {
        List<Category> tags = categoryService.listByArticleCount();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}/articles")
    public ResponseEntity<Object> categoryArticles(@PathVariable("id") Long id,
                                              @RequestParam(value = "current", defaultValue = "1") Integer current,
                                              @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Page<Article> pageInfo = categoryService.listPreviewPageByTagId(current, size, id);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }
}
