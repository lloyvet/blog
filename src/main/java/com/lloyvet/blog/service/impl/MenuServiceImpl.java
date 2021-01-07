package com.lloyvet.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.exception.EntityExistException;
import com.lloyvet.blog.util.MenuTreeUtil;
import com.lloyvet.blog.vo.InitInfoVO;
import com.lloyvet.blog.vo.MenuSelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.domain.Menu;
import com.lloyvet.blog.mapper.MenuMapper;
import com.lloyvet.blog.service.MenuService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author zihao Shen
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public InitInfoVO menu() {
        List<Menu> menuList = this.list();
        return InitInfoVO.init(menuList);
    }

    @Override
    public TableResult listAllMenu() {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.select(Menu.Table.ID, Menu.Table.PID, Menu.Table.TITLE, Menu.Table.HREF, Menu.Table.ICON, Menu.Table.SORT, Menu.Table.TYPE, Menu.Table.STATUS, Menu.Table.CREATE_TIME, Menu.Table.UPDATE_TIME);
        return TableResult.tableOk(menuMapper.selectList(qw),this.count());
    }

    @Override
    public List<MenuSelectVo> listRadioTree() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.select(Menu.Table.ID, Menu.Table.PID, Menu.Table.TITLE);
        List<Menu> menus = menuMapper.selectList(wrapper);
        List<MenuSelectVo> treeList = new ArrayList<>();
        for (Menu menu : menus) {
            MenuSelectVo menuSelectVO = new MenuSelectVo();
            menuSelectVO.setValue(menu.getId());
            menuSelectVO.setName(menu.getTitle());
            menuSelectVO.setPid(menu.getPid());
            treeList.add(menuSelectVO);
        }
        return MenuTreeUtil.toSelectTree(treeList, Constant.MENU_TREE_START);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateMenu(Menu menu) {
        if(menu.getId() == null){
            //查询菜单标题是否重复
            QueryWrapper<Menu> qw = new QueryWrapper<>();
            qw.eq(Menu.COL_TITLE,menu.getTitle());
            Menu oldMenu = menuMapper.selectOne(qw);
            if(null!=oldMenu){
                throw new EntityExistException("菜单标题：" + menu.getTitle() + "已存在");
            }
            menuMapper.insert(menu);
        }else{
            //更新
            //检查菜单标题是否唯一
            QueryWrapper<Menu> wrapper = new QueryWrapper<>();
            wrapper.eq(Menu.Table.TITLE, menu.getTitle());
            List<Menu> menus = menuMapper.selectList(wrapper);
            menus = menus.stream().filter(m -> !m.getId().equals(menu.getId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(menus)) {
                throw new EntityExistException("菜单标题：" + menu.getTitle() + "已存在");
            }
            menuMapper.updateById(menu);
        }
    }

    @Override
    public boolean deleteMenuById(Long id) {
        boolean b = checkCanDelete(id);
        if(b){
            this.removeById(id);
            return true;
        }else{
            throw new RuntimeException("存在父菜单无法删除");
        }
    }

    private boolean checkCanDelete(Long id) {
        List<Menu> menus = this.list();
        for (Menu menu : menus) {
            if(menu.getPid().equals(id)){
                return false;
            }
        }
        return true;
    }


}
