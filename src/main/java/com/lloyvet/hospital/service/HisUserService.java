package com.lloyvet.hospital.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.hospital.common.TableResult;
import com.lloyvet.hospital.domain.HisUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.hospital.vo.VisitorLoginVo;
import com.lloyvet.hospital.vo.HisUserVo;

/**
 * @author zihao Shen
 */
public interface HisUserService extends IService<HisUser> {

    /**
     * 访客登录
     *
     * @param visitorLoginVO
     * @return
     */
    HisUser login(VisitorLoginVo visitorLoginVO);

    TableResult getAllVisitor(Integer page, Integer limit, HisUserVo hisUserVo);
}


