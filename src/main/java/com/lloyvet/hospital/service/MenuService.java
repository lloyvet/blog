package com.lloyvet.hospital.service;

import com.lloyvet.hospital.common.TableResult;
import com.lloyvet.hospital.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.hospital.vo.InitInfoVO;
import com.lloyvet.hospital.vo.MenuSelectVo;

import java.util.List;

/**
 * @author zihao Shen
 */
public interface MenuService extends IService<Menu> {


    /**
     * 菜单
     *
     * @param type
     * @return
     */
    InitInfoVO menu(Integer type);

    /**
     * 查询菜单
     *
     * @return
     */
    TableResult listAllMenu();

    /**
     * 查询菜单树
     *
     * @return
     */
    List<MenuSelectVo> listRadioTree();

    /**
     * 保存菜单
     *
     * @param menu
     */
    void saveOrUpdateMenu(Menu menu);

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    boolean deleteMenuById(Long id);
}

