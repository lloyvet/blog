package com.lloyvet.blog.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.annotation.AccessLogs;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.domain.Tag;
import com.lloyvet.blog.service.ArticleService;
import com.lloyvet.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 标签详情页
 * @author zihao Shen
 */
@Controller
@RequestMapping("tag")
public class FrontTagController {

    @Autowired
    TagService tagService;

    @Autowired
    ArticleService articleService;

    @AccessLogs("访问标签页")
    @GetMapping
    public ResponseEntity<Object> tag() {
        List<Tag> tags = tagService.listByArticleCount();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}/articles")
    public ResponseEntity<Object> tagArticles(@PathVariable("id") Long id,
                                              @RequestParam(value = "current", defaultValue = "1") Integer current,
                                              @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Page<Article> pageInfo = articleService.listPreviewPageByTagId(current, size, id);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }
}
