package com.lloyvet.blog;

import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void sendPost(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("通知今晚开会");  //标题
        simpleMailMessage.setText("今晚6点");   //内容
        simpleMailMessage.setTo("2849459428@qq.com");   //接收方，这个是要发给谁
        simpleMailMessage.setFrom("1924143976@qq.com");   //发送方 这个就是配置文件中配置的
        javaMailSender.send(simpleMailMessage);
    }


}
