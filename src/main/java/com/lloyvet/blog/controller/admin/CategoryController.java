package com.lloyvet.blog.controller.admin;

import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.domain.Tag;
import com.lloyvet.blog.service.CategoryService;
import com.lloyvet.blog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * @author zihao Shen
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public TableResult listCategory(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                    CategoryVo categoryVo){
        return categoryService.selectCategoryList(page,limit,categoryVo);
    }

    /**
     * 删除单个
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultObj delete(@PathVariable("id") Long id){
        categoryService.removeById(id);
        return ResultObj.ok();
    }

    /**
     * 批量删除标签
     */
    @DeleteMapping("/delete")
    public ResultObj deleteBatchTag(@RequestBody List<Long> idList){
        categoryService.removeByIds(idList);
        return ResultObj.ok();
    }

    /**
     * 编辑
     * @param category
     * @return
     */
    @PutMapping("/edit")
    public ResultObj update(@RequestBody Category category) {
        category.setUpdateTime(new Date());
        categoryService.saveOrUpdate(category);
        return ResultObj.ok();
    }

    /**
     * 保存
     * @param category
     * @return
     */
    @PostMapping("/save")
    public ResultObj saveCategory(@RequestBody Category category){
        category.setCreateTime(new Date());
        categoryService.save(category);
        return ResultObj.ok();
    }
}
