package com.lloyvet.hospital.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zihao Shen
 */
@Data
public class MenuSelectVo implements Serializable {

    private String name;

    private Long value;

    private Long pid;

    private List<MenuSelectVo> children;
}
