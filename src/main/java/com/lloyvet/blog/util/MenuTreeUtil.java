package com.lloyvet.blog.util;


import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.vo.MenuCheckboxVo;
import com.lloyvet.blog.vo.MenuSelectVo;
import com.lloyvet.blog.vo.MenuVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descriptin 菜单树工具
 **/
public class MenuTreeUtil {
    public static List<MenuVo> toTree(List<MenuVo> treeList, Long pid) {
        List<MenuVo> retList = new ArrayList<>();
        for (MenuVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChild(parent, treeList));
            }
        }
        return retList;
    }

    private static MenuVo findChild(MenuVo parent, List<MenuVo> treeList) {
        for (MenuVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChild(child, treeList));
            }
        }
        return parent;
    }

    public static List<MenuSelectVo> toSelectTree(List<MenuSelectVo> treeList, Long pid) {
        MenuSelectVo root = new MenuSelectVo();
        root.setName(Constant.MENU_TREE_ROOT_NAME);
        root.setValue(Constant.MENU_TREE_ROOT);
        root.setPid(pid);
        treeList.add(root);
        List<MenuSelectVo> retList = new ArrayList<>();
        for (MenuSelectVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildSelect(parent, treeList));
            }
        }
        return retList;
    }

    private static MenuSelectVo findChildSelect(MenuSelectVo parent, List<MenuSelectVo> treeList) {
        for (MenuSelectVo children : treeList) {
            if (parent.getValue().equals(children.getPid())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildSelect(children, treeList));
            }
        }
        return parent;
    }

    public static List<MenuCheckboxVo> toCheckBoxTree(List<MenuCheckboxVo> treeList, Long pid) {
        MenuCheckboxVo root = new MenuCheckboxVo();
        root.setTitle(Constant.MENU_TREE_ROOT_NAME);
        root.setId(Constant.MENU_TREE_ROOT);
        root.setParentId(pid);
        root.setCheckArr(Constant.MENU_TREE_NOT_SELECTED);
        treeList.add(root);
        List<MenuCheckboxVo> retList = new ArrayList<>();
        for (MenuCheckboxVo parent : treeList) {
            if (pid.equals(parent.getParentId())) {
                retList.add(findChildCheckBox(parent, treeList));
            }
        }
        return retList;
    }

    private static MenuCheckboxVo findChildCheckBox(MenuCheckboxVo parent, List<MenuCheckboxVo> treeList) {
        for (MenuCheckboxVo children : treeList) {
            if (parent.getId().equals(children.getParentId())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildCheckBox(children, treeList));
            }
        }
        return parent;
    }
}

