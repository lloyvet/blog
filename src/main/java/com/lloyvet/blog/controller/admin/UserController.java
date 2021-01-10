//package com.lloyvet.blog.controller.admin;
//
//import com.lloyvet.blog.common.ResultObj;
//import com.lloyvet.blog.service.UserService;
//import com.lloyvet.blog.vo.UserVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author zihao Shen
// */
//@RestController
//@RequestMapping("/admin")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    @PutMapping("/user/password")
//    public ResultObj setPassword(@RequestBody UserVo userVo){
//
//        boolean b = userService.reSetPassword(userVo);
//        if(b){
//            return ResultObj.ok();
//        }
//    }
//
//}
