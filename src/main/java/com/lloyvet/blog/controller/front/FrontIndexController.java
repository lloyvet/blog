package com.lloyvet.blog.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.service.ArticleService;
import com.lloyvet.blog.to.HomeTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author zihao Shen
 * 首页
 */
@Controller
public class FrontIndexController {

    @Autowired
    ArticleService articleService;

    @GetMapping({"/","index","index.html"})
    public String index(){
        return "/front/index";
    }

    /**
     * 首页资源
     * @return
     */
    @GetMapping("/home")
    public ResponseEntity<Object> home() {
        HomeTo homeTo = articleService.getIndexArticle();

        return new ResponseEntity<>(homeTo, HttpStatus.OK);

    }

    @GetMapping("/articles")
    public ResponseEntity<Object> articles(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                           @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Page<Article> articlePage = articleService.listPreviewByPage(current, size);
        return new ResponseEntity<>(articlePage, HttpStatus.OK);
    }

}
