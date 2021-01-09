package com.lloyvet.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.domain.Visitor;
import com.lloyvet.blog.mapper.VisitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.domain.Comment;
import com.lloyvet.blog.mapper.CommentMapper;
import com.lloyvet.blog.service.CommentService;
/**
 * @author zihao Shen
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{


    @Autowired
    CommentMapper commentMapper;

    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public Page<Comment> listByArticleId(Long articleId, Integer current, Integer size) {
        Page<Comment> page = new Page<>(current, size);
        QueryWrapper<Comment> qw = new QueryWrapper<>();
        //根据id查询所有评论
        qw.eq("article_id",articleId).ne("status",0).orderByDesc("create_time");
        List<Comment> allComments = commentMapper.selectList(qw);
        //根据id分页查询顶级评论
        qw.eq("pid",0);
        page = commentMapper.selectPage(page,qw);
        List<Comment> resultComments = getResultComments(page.getRecords(),allComments);
        page.setRecords(resultComments);
        return page;
    }

    private List<Comment> getResultComments(List<Comment> records, List<Comment> allComments) {
        List<Comment> res = new ArrayList<>();
        for (Comment parent : records) {
            //设置visitor
            QueryWrapper<Visitor> qwV = new QueryWrapper<>();
            qwV.eq("id",parent.getVisitorId()).select(Visitor.COL_ID,Visitor.COL_NICKNAME,Visitor.COL_AVATAR,Visitor.COL_LINK);
            Visitor visitor = visitorMapper.selectOne(qwV);
            parent.setVisitor(visitor);
            //设置子回复
            List<Comment> children = new ArrayList<>();
            findCommentChildren(parent,children,allComments);
            parent.setChildren(children);
            res.add(parent);
        }
        return res;
    }

    /**
     * 递归查询子评论
     * @param parent
     * @param children
     * @param allComments
     */
    private void findCommentChildren(Comment parent, List<Comment> children, List<Comment> allComments) {
        for (Comment comment : allComments) {
            //设置visitor
            QueryWrapper<Visitor> qwV = new QueryWrapper<>();
            qwV.eq("id",comment.getVisitorId()).select(Visitor.COL_ID,Visitor.COL_NICKNAME,Visitor.COL_AVATAR,Visitor.COL_LINK);
            Visitor visitor = visitorMapper.selectOne(qwV);
            comment.setVisitor(visitor);
            if(parent.getId().equals(comment.getPid())){
                children.add(comment);
                findCommentChildren(comment,children,allComments);
            }
        }
    }
}
