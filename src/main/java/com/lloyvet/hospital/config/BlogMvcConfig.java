package com.lloyvet.hospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zihao Shen
 */
@Configuration
public class BlogMvcConfig implements WebMvcConfigurer {


    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BlogInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login","/admin/login.html","/admin/getCode","/admin/loginPage");
    }
}
