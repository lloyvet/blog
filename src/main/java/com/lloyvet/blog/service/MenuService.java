package com.lloyvet.blog.service;

import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.blog.vo.InitInfoVO;
import com.lloyvet.blog.vo.MenuSelectVo;

import java.util.List;

/**
 * @author zihao Shen
 */
public interface MenuService extends IService<Menu>{


    /**
     * 菜单
     * @return
     */
    InitInfoVO menu();

    /**
     * 查询菜单
     * @return
     */
    TableResult listAllMenu();

    /**
     * 查询菜单树
     * @return
     */
    List<MenuSelectVo> listRadioTree();

    /**
     * 保存菜单
     * @param menu
     */
    void saveOrUpdateMenu(Menu menu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    boolean deleteMenuById(Long id);
}
