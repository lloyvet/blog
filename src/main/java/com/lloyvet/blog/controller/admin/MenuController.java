package com.lloyvet.blog.controller.admin;

import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Category;
import com.lloyvet.blog.domain.Menu;
import com.lloyvet.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author zihao Shen
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public TableResult listAll() {
        return menuService.listAllMenu();
    }

    @GetMapping("/radio-tree")
    public ResultObj listRadioTree(){
        return ResultObj.ok(menuService.listRadioTree());
    }

    @PostMapping("/save")
    public ResultObj saveMenu(@RequestBody Menu menu){
        menu.setStatus(menu.getStatus() != null);
        menu.setCreateTime(new Date());
        menu.setUpdateTime(menu.getCreateTime());
        menuService.saveOrUpdateMenu(menu);
        return ResultObj.ok();
    }



    /**
     * 编辑
     * @param menu
     * @return
     */
    @PutMapping("/edit")
    public ResultObj updateMenu(@RequestBody Menu menu) {
        menu.setUpdateTime(new Date());
        menuService.saveOrUpdateMenu(menu);
        return ResultObj.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResultObj deleteMenu(@PathVariable("id") Long id){
        menuService.deleteMenuById(id);
        return ResultObj.ok();
    }

}
