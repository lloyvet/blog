package com.lloyvet.blog.controller.front;

import com.lloyvet.blog.service.ArticleService;
import com.lloyvet.blog.service.CategoryService;
import com.lloyvet.blog.service.TagService;
import com.lloyvet.blog.to.AboutTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zihao Shen
 */
@Controller
@RequestMapping
public class FrontAboutController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;

    @GetMapping("about")
    public ResponseEntity<Object> about(){
        AboutTo aboutTo = new AboutTo();
        aboutTo.setArticleCount(articleService.count());
        aboutTo.setCategoryCount(categoryService.getCount());
        aboutTo.setTagCount(tagService.getCount());
        return new ResponseEntity<>(aboutTo, HttpStatus.OK);
    }
}
