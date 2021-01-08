package com.lloyvet.blog.controller.front;

import com.lloyvet.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zihao Shen
 */
@RestController
public class ArchivesController {


    @Autowired
    ArticleService articleService;
}
