package com.lloyvet.hospital.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zihao Shen
 */
@Data
public class MenuCheckboxVo implements Serializable {

    private Long id;

    private Long parentId;

    private String title;

    private String checkArr;

    private List<MenuCheckboxVo> children;
}
