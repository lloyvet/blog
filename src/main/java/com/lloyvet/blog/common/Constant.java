package com.lloyvet.blog.common;

/**
 * @author zihao Shen
 */
public class Constant {
    public static final String HOME_TITLE = "首页";

    public static final String HOME_HREF = "/admin/page/home/dashboard";

    public static final String LOGO_TITLE = "Lloyvet Blog";

    public static final String LOGO_IMAGE = "/static/admin/layuimini/images/logo.png";
    public static final String USER = "user";
    /**
     * 默认颜色
     */
    public static final String DEFAULT_COLOR = "#D5F5E3";

    /**
     * 审核状态
     */
    public static final Byte AUDIT_PASS = 2;

    public static final Byte AUDIT_WAIT = 1;

    public static final Byte AUDIT_NOT_PASS = 0;

    /**
     * 菜单树根结点
     */
    public static final Long MENU_TREE_ROOT = 0L;

    /**
     * 菜单树根结点名称
     */
    public static final String MENU_TREE_ROOT_NAME = "根目录";

    /**
     * 菜单树开始结点
     */
    public static final Long MENU_TREE_START = -1L;

    /**
     * 菜单树复选框未选中
     */
    public static final String MENU_TREE_NOT_SELECTED = "0";

    /**
     * 菜单树复选框选中
     */
    public static final Integer MENU_TREE_SELECTED = 1;

    /**
     * 评论链表根节点
     */
    public static final Long COMMENT_LINKED_LIST_ROOT = 0L;

}
