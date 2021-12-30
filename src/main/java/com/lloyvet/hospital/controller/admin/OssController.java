package com.lloyvet.hospital.controller.admin;

import com.lloyvet.hospital.common.ResultObj;
import com.lloyvet.hospital.config.BlogOssConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;


/**
 * 七牛云存储
 * @author zihao Shen
 */
@RestController
public class OssController {




    @RequestMapping("/oss/policy")
    public ResultObj policy(MultipartFile file){
        String result = "";
        try {
            result = BlogOssConfig.uploadQNImg((FileInputStream) file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultObj.ok(result);
    }
}
