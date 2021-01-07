package com.lloyvet.blog.controller.admin;

import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.domain.Photo;
import com.lloyvet.blog.service.PhotoService;
import com.lloyvet.blog.vo.CategoryVo;
import com.lloyvet.blog.vo.PhotoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zihao Shen
 */
@RestController
@RequestMapping("/admin/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @GetMapping("/list")
    public TableResult listCategory(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                    PhotoVo photoVo){
        return photoService.selectPhotoList(page,limit,photoVo);
    }

    /**
     * 删除单个
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultObj delete(@PathVariable("id") Long id){
        photoService.removeById(id);
        return ResultObj.ok();
    }

    /**
     * 批量删除标签
     */
    @DeleteMapping("/delete")
    public ResultObj deleteBatchTag(@RequestBody List<Long> idList){
        photoService.removeByIds(idList);
        return ResultObj.ok();
    }

    /**
     * 编辑
     * @param photo
     * @return
     */
    @PutMapping("/edit")
    public ResultObj update(@RequestBody Photo photo) {
        photo.setUpdateTime(new Date());
        photoService.saveOrUpdate(photo);
        return ResultObj.ok();
    }

    /**
     * 保存
     * @param photo
     * @return
     */
    @PostMapping("/save")
    public ResultObj saveCategory(@RequestBody Photo photo){
        photo.setCreateTime(new Date());
        photoService.save(photo);
        return ResultObj.ok();
    }
}
