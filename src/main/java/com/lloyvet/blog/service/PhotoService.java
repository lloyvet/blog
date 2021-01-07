package com.lloyvet.blog.service;

import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Photo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.blog.vo.PhotoVo;

/**
 * @author zihao Shen
 */
public interface PhotoService extends IService<Photo>{


    /**
     * 条件查询照片
     * @param page
     * @param limit
     * @param photoVo
     * @return
     */
    TableResult selectPhotoList(Integer page, Integer limit, PhotoVo photoVo);
}
