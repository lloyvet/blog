package com.lloyvet.blog.controller.front;

import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章前台详情页面
 * @author zihao Shen
 */
@Controller
@RequestMapping("/article")
public class FrontArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 文章详情页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/read/{id}")
    public String getArticleById(@PathVariable("id") Long id, Model model){
        Article article = articleService.getArticleById(id);
        model.addAttribute("article",article);
        return "front/article";
    }

    /**
     * 点赞文章
     * @param id
     * @return
     */
    @PutMapping("/{id}/likes")
    public ResponseEntity<Object> likesArticle(@PathVariable("id") Long id){
        articleService.incrLikes(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchArticle(@RequestParam("keyWord") String keyWord){
        List<Article> articles = articleService.selectListByKeyWord(keyWord);
        return new ResponseEntity<>(articles,HttpStatus.OK);
    }
}
