package com.lloyvet.blog.service;

import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.domain.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.blog.vo.TagVo;

import java.util.List;

/**
 * @author zihao Shen
 */
public interface TagService extends IService<Tag>{


    /**
     * 查询tag
     * @param page
     * @param limit
     * @param tagVo
     * @return
     */
    ResultObj selectTagList(Integer page, Integer limit, TagVo tagVo);

    /**
     * 获取颜色
     * @return
     */
    Object listColor();

    /**
     * 通过文章id获取对应的标签列表
     * @param id
     * @return
     */
    List<Tag> listTagByArticleId(Long id);

    /**
     * 获取标签
     * @return
     */
    List<Tag> listByArticleCount();

}
