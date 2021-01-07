package com.lloyvet.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zihao Shen
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.lloyvet.blog.mapper"})
@EnableAspectJAutoProxy
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
