package com.lloyvet.blog.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zihao Shen
 */
@Data
public class MenuTreeNode {
    //菜单树
    private Long id;
    private Long pid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    private String icon;
    private String href;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String target;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTreeNode> child=new ArrayList<>();


    /**
     * 构造主页左边的树
     * @param id
     * @param pid
     * @param title
     * @param href
     * @param icon
     * @param target
     */
    public MenuTreeNode(Long id, Long pid, String title, String href, String icon, String target) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.icon = icon;
        this.target = target;
    }


    public static class  MenuTreeNodeBuilder{
        public static List<MenuTreeNode> build(List<MenuTreeNode> treeNodes, Long topId){
            List<MenuTreeNode> nodes=new ArrayList<>();
            for (MenuTreeNode n1 : treeNodes) {
                if(n1.getPid().equals(topId)){
                    nodes.add(n1);
                }
                for (MenuTreeNode n2 : treeNodes) {
                    if(n2.getPid().equals(n1.getId())){
                        n1.getChild().add(n2);
                    }
                }
            }

            return nodes;
        }
    }



}