package com.lloyvet.blog.controller.front;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.domain.Comment;
import com.lloyvet.blog.domain.Visitor;
import com.lloyvet.blog.service.CommentService;
import com.lloyvet.blog.util.StringUtils;
import com.lloyvet.blog.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 评论
 * @author zihao Shen
 */
@RestController
@RequestMapping("/comments")
public class FrontCommentController {


    @Autowired
    CommentService commentService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/listByArticleId/{articleId}")
    public ResponseEntity<Object> listByArticleId(@PathVariable("articleId") Long articleId,
                                                  @RequestParam(value = "current", defaultValue = "1") Integer current,
                                                  @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Page<Comment> pageInfo = commentService.listByArticleId(articleId, current, size);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    @PostMapping
    public ResultObj save(@RequestBody Comment comment, HttpServletRequest request) {
        if(comment.getPid()!=null&&comment.getPid()!=0){
            redisTemplate.boundListOps(Constant.COMMENTCALL).leftPush(JSONUtil.toJsonStr(comment));
        }
        comment.setCreateTime(new Date());
        comment.setAddress(request.getRemoteAddr());
        comment.setStatus(true);
        commentService.save(comment);
        return ResultObj.ok();
    }

}
