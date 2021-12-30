package com.lloyvet.hospital.vo;

import lombok.Data;

/**
 * @author zihao Shen
 */
@Data
public class CommentVo {

    private Long articleId;

    private Long pid;

    private String content;

    private String parentNickname;

}
