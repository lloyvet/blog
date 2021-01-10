package com.lloyvet.blog.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zihao Shen
 */


@Data
public class ArticleTopVo implements Serializable {

    /**
     *文章id
     */
    @NotNull
    private Long id;

}
