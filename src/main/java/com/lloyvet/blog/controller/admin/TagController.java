package com.lloyvet.blog.controller.admin;

import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.domain.Tag;
import com.lloyvet.blog.service.TagService;
import com.lloyvet.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 标签控制器
 * @author zihao Shen
 */

@RestController
@RequestMapping("/admin/tag")
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * 查询标签
     * @param page
     * @param limit
     * @param tagVo
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public ResultObj listAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                             TagVo tagVo){
        return tagService.selectTagList(page,limit,tagVo);
    }

    /**
     * 删除标签
     */
    @DeleteMapping("/delete/{id}")
    public ResultObj deleteTag(@PathVariable("id") Long id){
        tagService.removeById(id);
        return ResultObj.ok();
    }

    /**
     * 批量删除标签
     */
    @DeleteMapping("/delete")
    public ResultObj deleteBatchTag(@RequestBody List<Long> idList){
        tagService.removeByIds(idList);
        return ResultObj.ok();
    }

    /**
     * 保存标签
     */
    @PostMapping("save")
    public ResultObj saveTag(@RequestBody Tag tag){
        tag.setCreateTime(new Date());
        tag.setUpdateTime(tag.getUpdateTime());
        tagService.saveOrUpdate(tag);
        return ResultObj.ok();
    }

    /**
     * 编辑
     * @param tag
     * @return
     */
    @PutMapping("edit")
    public ResultObj update(@RequestBody Tag tag) {
        tag.setUpdateTime(new Date());
        tagService.saveOrUpdate(tag);
        return ResultObj.ok();
    }

    /**
     * 查询标签颜色
     * @return
     */
    @GetMapping("/colors")
    public ResultObj getColors(){
        return ResultObj.ok(tagService.listColor());
    }
}
