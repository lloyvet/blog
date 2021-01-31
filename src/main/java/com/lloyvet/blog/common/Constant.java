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
    public static final String VISITOR = "visitor";

    public static final String COMMENTCALL = "comment:replay";
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

    public static final Byte SUCCESS = 1;

    public static final Integer FAILURE = 0;

    public static final String REGION = "内网IP|内网IP";

    public static final String ARTICLE_COUNT = "blog_article_count";


    public static final String DEFAULT_AVATAR = "https://gravatar.loli.net/avatar/f2c02ce7474e4b228a576f7e47f00bd1?d=mp&v=1.3.10";

}
