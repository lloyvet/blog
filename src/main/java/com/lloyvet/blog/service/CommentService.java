package com.lloyvet.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CommentService extends IService<Comment>{


    /**
     * 评论列表
     * @param articleId
     * @param current
     * @param size
     * @return
     */
    Page<Comment> listByArticleId(Long articleId, Integer current, Integer size);
}
