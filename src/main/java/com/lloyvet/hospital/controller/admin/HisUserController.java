package com.lloyvet.hospital.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.hospital.common.ResultObj;
import com.lloyvet.hospital.common.TableResult;
import com.lloyvet.hospital.domain.HisUser;
import com.lloyvet.hospital.service.HisUserService;
import com.lloyvet.hospital.vo.HisUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 访客管理
 * @author zihao Shen
 */
@RestController
@RequestMapping("/admin/visitor")
public class HisUserController {

    @Autowired
    private HisUserService visitorService;

    @GetMapping("/getAllVisitor")
    public TableResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  HisUserVo hisUserVo) {
        return visitorService.getAllVisitor(page,limit,hisUserVo);
    }
    @DeleteMapping("/{id}")
    public ResultObj remove(@NotNull @PathVariable("id") Long id) {
        visitorService.removeById(id);
        return ResultObj.ok();
    }

    @DeleteMapping
    public ResultObj removeBatch(@NotEmpty @RequestBody List<Long> idList) {
        visitorService.removeByIds(idList);
        return ResultObj.ok();
    }
}
