package com.lloyvet.hospital.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zihao Shen
 */
@Data
public class HisUserVo implements Serializable {

    /**
     * 分类名称
     */
    private String userName;

    /**
     * 前台显示
     */
    private String phone;

}
