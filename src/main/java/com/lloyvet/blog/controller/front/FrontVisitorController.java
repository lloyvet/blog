package com.lloyvet.blog.controller.front;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.domain.Visitor;
import com.lloyvet.blog.service.VisitorService;
import com.lloyvet.blog.util.Md5Utils;
import com.lloyvet.blog.vo.VisitorLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 访客模块
 * @author zihao Shen
 */
@RestController
@RequestMapping("/visitor")
public class FrontVisitorController {

    @Autowired
    VisitorService visitorService;

    /**
     * 访客注册
     * @param visitor
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> register(@RequestBody Visitor visitor){
        visitor.setPassword(Md5Utils.code(visitor.getPassword()));
        visitor.setAvatar(Constant.DEFAULT_AVATAR);
        visitor.setCreateTime(new Date());
        visitor.setUpdateTime(visitor.getCreateTime());
        try {
            visitorService.save(visitor);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * 访客登录
     * @param visitorLoginVO
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody VisitorLoginVo visitorLoginVO, HttpServletRequest request,HttpServletResponse response) {
        visitorLoginVO.setPassword(Md5Utils.code(visitorLoginVO.getPassword()));
        Visitor visitor = visitorService.login(visitorLoginVO);
        if(visitor!=null){
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
