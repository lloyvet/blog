package com.lloyvet.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lloyvet.blog.domain.ArticleTag;
import com.lloyvet.blog.to.ArticleTagTo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zihao Shen
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {


    /**
     * 批量插入
     * @param id
     * @param tagIdList
     */
    void insertBatch(@Param("id") Long id, @Param("tagIdList") List<Long> tagIdList);


    /**
     * 获取文章相关联标签数量
     * @return
     */
    List<Long> selectTagCount();

}