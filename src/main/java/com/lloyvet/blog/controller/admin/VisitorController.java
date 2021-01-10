package com.lloyvet.blog.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Visitor;
import com.lloyvet.blog.service.VisitorService;
import com.lloyvet.blog.vo.VisitorVo;
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
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/list")
    public TableResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  VisitorVo visitorVo) {
        Page<Visitor> pageInfo = visitorService.listTableByPage(page, limit, visitorVo);
        return TableResult.tableOk(pageInfo.getRecords(), (int) pageInfo.getTotal());
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
