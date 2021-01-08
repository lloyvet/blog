package com.lloyvet.blog.controller.front;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前台路由页面
 * @author zihao Shen
 */
@Controller
public class FrontRouteController {


    @GetMapping("/page/{pageName}")
    public ModelAndView getPage(@PathVariable("pageName") String pageName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/" + pageName);
        return modelAndView;
    }
}
