package com.lloyvet.blog.controller.admin;

import com.lloyvet.blog.common.MenuTreeNode;
import com.lloyvet.blog.domain.Menu;
import com.lloyvet.blog.service.MenuService;
import com.lloyvet.blog.vo.InitInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    public Object initMenu(){
        InitInfoVO initInfoVO = menuService.menu();
        return new ResponseEntity<>(initInfoVO, HttpStatus.OK);
    }

}
