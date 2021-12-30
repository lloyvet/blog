package com.lloyvet.hospital.controller.admin;

import com.lloyvet.hospital.common.Constant;
import com.lloyvet.hospital.domain.User;
import com.lloyvet.hospital.service.MenuService;
import com.lloyvet.hospital.vo.InitInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 首页
 * @author zihao Shen
 */
@Controller
@RequestMapping("adminIndex")
public class IndexController {

    @Autowired
    MenuService menuService;

    /**
     * 加载目录
     * @return
     */
    @GetMapping("/init")
    @ResponseBody
    public Object initMenu(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constant.USER);
        InitInfoVO initInfoVO = menuService.menu(user.getType());
        return new ResponseEntity<>(initInfoVO, HttpStatus.OK);
    }

}
