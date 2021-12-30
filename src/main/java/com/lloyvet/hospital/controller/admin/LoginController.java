package com.lloyvet.hospital.controller.admin;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.lloyvet.hospital.common.Constant;
import com.lloyvet.hospital.common.ResultObj;
import com.lloyvet.hospital.domain.User;
import com.lloyvet.hospital.service.UserService;
import com.lloyvet.hospital.to.IndexTo;
import com.lloyvet.hospital.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zihao Shen
 */
@Controller
@RequestMapping("/admin")
public class LoginController {


    @Autowired
    UserService userService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping({"/","/login.html"})
    public String loginPage(){
        return "admin/home/login";
    }

    /**
     * 访问后台首页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "admin/home/index";
    }

    /**
     * 登录
     * @param userTo
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultObj login(UserTo userTo, HttpServletRequest request){
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute("code");
        if(!code.equalsIgnoreCase(userTo.getVerifyCode())){
            return new ResultObj(-1,"验证码错误");
        }
        User user = userService.login(userTo);
        if(null == user){
            return new ResultObj(-1,"账号或密码错误");
        }else{
            request.getSession().setAttribute(Constant.USER,user);
            return new ResultObj(1,"登录成功");
        }
    }

    @RequestMapping("logout")
    @ResponseBody
    public ResultObj logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return ResultObj.ok();
    }
    /**
     * 查询控制面板数据
     */
    @ResponseBody
    @GetMapping("/indexData")
    public ResponseEntity<Object> indexData() {
        IndexTo indexTo = new IndexTo();
        indexTo.setArticleCount(0L);
        indexTo.setArticles(null);
        indexTo.setCategoryCount((long) 0);
        indexTo.setTagCount((long) 0);
        indexTo.setViewCount(0);
        indexTo.setMessageCount(0);
        indexTo.setCommentCount(0);
        indexTo.setVisitorCount(0);
        return new ResponseEntity<>(indexTo, HttpStatus.OK);
    }


    /**
     * 获取登录验证码
     */
    @GetMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36,4,5);
        session.setAttribute("code",lineCaptcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }
}
