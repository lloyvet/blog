package com.lloyvet.blog.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zihao Shen
 */


@Data
public class ArticleAuditVo implements Serializable {

    /**
     *文章id
     */
    @NotNull
    private Long id;

    /**
     *审核状态[0:审核未过, 2:等待审核, 3:审核通过
     */
    @NotNull
    private Byte status;
}
