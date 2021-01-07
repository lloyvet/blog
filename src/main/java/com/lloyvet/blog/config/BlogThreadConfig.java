package com.lloyvet.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class BlogThreadConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(20,200,
                10, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1000*10)
                , Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    }
}
