package com.lloyvet.hospital.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zihao Shen
 */
@Data
public class VisitorLoginVo {

    @NotNull
    private String certificate;

    @NotNull
    private String password;
}
