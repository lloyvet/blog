package com.lloyvet.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lloyvet.blog.domain.ArticleTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zihao Shen
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {


    void insertBatch(@Param("id") Long id, @Param("tagIdList") List<Long> tagIdList);

}