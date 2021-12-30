package com.lloyvet.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.hospital.common.TableResult;
import com.lloyvet.hospital.vo.VisitorLoginVo;
import com.lloyvet.hospital.vo.HisUserVo;
import com.lloyvet.hospital.domain.HisUser;
import com.lloyvet.hospital.mapper.HisUserMapper;
import com.lloyvet.hospital.service.HisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;

/**
 * @author zihao Shen
 */
@Service
public class HisUserServiceImpl extends ServiceImpl<HisUserMapper, HisUser> implements HisUserService {


    @Autowired
    HisUserMapper hisUserMapper;

//    @Override
//    public Page<HisUser> listTableByPage(Integer page, Integer limit, HisUserVo hisUserVo) {
//        Page<HisUser> visitorPage = new Page<>(page, limit);
//        QueryWrapper<HisUser> qw = new QueryWrapper<>();
//        qw.like(!StringUtils.isEmpty(hisUserVo.getUserName()), "username", hisUserVo.getUserName())
//                .like(!StringUtils.isEmpty(hisUserVo.getPhone()), "phone", hisUserVo.getPhone())
//                .between(!StringUtils.isEmpty(hisUserVo.getStartDate()) && !StringUtils.isEmpty(hisUserVo.getEndDate()), "update_time",
//                        hisUserVo.getStartDate(), hisUserVo.getEndDate());
//        hisUserMapper.selectPage(visitorPage, qw);
//        return visitorPage;
//    }


    @Override
    public HisUser login(VisitorLoginVo visitorLoginVO) {
        QueryWrapper<HisUser> qw = new QueryWrapper<>();
        qw.eq(HisUser.COL_EMAIL, visitorLoginVO.getCertificate()).or().eq(HisUser.COL_USERNAME, visitorLoginVO.getCertificate());
        qw.eq("password", visitorLoginVO.getPassword());
        return hisUserMapper.selectOne(qw);
    }

    @Override
    public TableResult getAllVisitor(Integer page, Integer limit, HisUserVo hisUserVo) {
        Page<HisUser> pages = new Page<>(page,limit);
        QueryWrapper<HisUser> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(hisUserVo.getUserName()),"username",hisUserVo.getUserName())
                .like(!StringUtils.isEmpty(hisUserVo.getPhone()),"phone",hisUserVo.getPhone());
        hisUserMapper.selectPage(pages,qw);
        return TableResult.tableOk(pages.getRecords(), (int) pages.getTotal());
    }
}


