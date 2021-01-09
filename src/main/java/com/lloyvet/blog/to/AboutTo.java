package com.lloyvet.blog.to;

import com.lloyvet.blog.domain.Photo;
import lombok.Data;

import java.util.List;

/**
 * 关于页返回信息
 * @author zihao Shen
 */
@Data
public class AboutTo {

    /**
     *文章数量
     */
    private Integer articleCount;

    /**
     *分类数量
     */
    private Integer categoryCount;

    /**
     *标签数量
     */
    private Integer tagCount;

}
