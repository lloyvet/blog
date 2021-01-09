package com.lloyvet.blog.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Comment;
import com.lloyvet.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 评论
 * @author zihao Shen
 */
@Controller
@RequestMapping("/comments")
public class FrontCommentController {


    @Autowired
    CommentService commentService;

    @GetMapping("/listByArticleId/{articleId}")
    public ResponseEntity<Object> listByArticleId(@PathVariable("articleId") Long articleId,
                                                  @RequestParam(value = "current", defaultValue = "1") Integer current,
                                                  @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Page<Comment> pageInfo = commentService.listByArticleId(articleId, current, size);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }
}
