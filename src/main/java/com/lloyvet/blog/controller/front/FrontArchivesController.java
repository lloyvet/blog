package com.lloyvet.blog.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.service.ArticleService;
import com.lloyvet.blog.to.ArchivesTo;
import com.lloyvet.blog.to.ArticleDateTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author zihao Shen
 */
@RestController
public class FrontArchivesController {


    @Autowired
    ArticleService articleService;

    @GetMapping("/archives")
    public ResponseEntity<Object> archives() {

        List<ArticleDateTo> articleDates = articleService.getCountByDate();
        for (ArticleDateTo articleDate : articleDates) {
            articleDate.setDate(articleDate.getYear()+"-"+articleDate.getMonth()+"-"+articleDate.getDay());
            articleDate.setYear(null);
            articleDate.setMonth(null);
            articleDate.setDay(null);
        }
        Page<Article> pageInfo = articleService.listPreviewByPage(1, Integer.parseInt("6"));
        ArchivesTo archivesTo = new ArchivesTo();
        archivesTo.setArticleDates(articleDates);
        archivesTo.setPageInfo(pageInfo);
        return new ResponseEntity<>(archivesTo, HttpStatus.OK);
    }

    @GetMapping("/archives-articles")
    public ResponseEntity<Object> archivesArticles(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                                   @RequestParam(value = "size", defaultValue = "6") Integer size) {

        Page<Article> pageInfo = articleService.listPreviewByPage(current, size);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

}
