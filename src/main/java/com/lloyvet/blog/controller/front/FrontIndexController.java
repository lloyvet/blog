package com.lloyvet.blog.controller.front;

import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author zihao Shen
 * 首页
 */
@Controller
public class FrontIndexController {

    @Autowired
    ArticleService articleService;

    @GetMapping({"/","index","index.html"})
    public String index(Model model){
        List<Article> articles = articleService.selectHotArticle();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日");
        for (Article article : articles) {
            String format = sdf.format(article.getCreateTime());
            article.setTime(format);
        }
        model.addAttribute("articles",articles);
        return "/front/index";
    }

}
