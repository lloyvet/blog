package com.lloyvet.blog.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zihao Shen
 */
@Controller
public class DashboardController {

    @GetMapping("admin/page/home/dashboard")
    public String dashboardHome(){
        return "admin/home/dashboard";
    }
}
