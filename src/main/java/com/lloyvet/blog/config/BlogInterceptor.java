package com.lloyvet.blog.config;

import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zihao Shen
 */
public class BlogInterceptor implements HandlerInterceptor {

    public static ThreadLocal<User> userSession = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        if (session.getAttribute(Constant.USER) == null) {
//            response.sendRedirect("/admin/login.html");
//            return false;
//        } else {
//            User user = (User)session.getAttribute(Constant.USER);
//            userSession.set(user);
//            return true;
//        }
        return true;
    }
}
