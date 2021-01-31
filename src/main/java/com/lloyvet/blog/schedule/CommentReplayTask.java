package com.lloyvet.blog.schedule;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.domain.Article;
import com.lloyvet.blog.domain.Comment;
import com.lloyvet.blog.domain.Visitor;
import com.lloyvet.blog.service.ArticleService;
import com.lloyvet.blog.service.CommentService;
import com.lloyvet.blog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 用户回复评论后定时邮件任务通知被回复者
 * @author zihao Shen
 */
@Configuration
@EnableScheduling
public class CommentReplayTask {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    VisitorService visitorService;

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleService articleService;

    @Scheduled(cron = "0 15 10 * * ?")
    public void sendEmail(){
        String commentReplay = (String)redisTemplate.opsForList().leftPop(Constant.COMMENTCALL);
        if(commentReplay==null){
            return;
        }
        Comment comment = JSONUtil.toBean(commentReplay, Comment.class);
        //回复者id
        Long visitorId = comment.getVisitorId();
        //被回复的文章
        Article article = articleService.getById(comment.getArticleId());
        //被回复的评论id
        Long pid = comment.getPid();
        Comment replayComment = commentService.getById(pid);
        //回复者
        Visitor visitor = visitorService.getById(replayComment.getVisitorId());
        //被回复者
        Visitor replayVisitor = visitorService.getById(visitorId);
        //邮件设置
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("你在LloyvetBlog网站的留言被回复了");
        message.setTo(replayVisitor.getEmail());
        message.setFrom("1924143976@qq.com");
        message.setText(visitor.getNickname()+"用户在LloyvetBlog(http://lloyvet.top)网站回复了你在"
                +article.getTitle()+"文章下的"+replayComment.getContent()+"此条评论，回复内容为"+comment.getContent()+
                "请尽快回复他哦");
        mailSender.send(message);
    }
}
