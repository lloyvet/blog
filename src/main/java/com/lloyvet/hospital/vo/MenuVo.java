package com.lloyvet.hospital.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zihao Shen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo implements Serializable {
    private Long id;

    /**
     * 父级菜单ID
     */
    private Long pid;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接
     */
    private String href;

    /**
     * 页面跳转方式
     */
    private String target;

    /**
     * 子级菜单列表
     */
    private List<MenuVo> child;

}