package com.lloyvet.hospital.service;

import com.lloyvet.hospital.common.TableResult;
import com.lloyvet.hospital.domain.Photo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.hospital.vo.PhotoVo;

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
