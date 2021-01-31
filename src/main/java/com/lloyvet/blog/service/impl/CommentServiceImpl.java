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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

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

    @Autowired
    ThreadPoolExecutor poolExecutor;

    @Override
    public Page<Comment> listByArticleId(Long articleId, Integer current, Integer size) {
        Page<Comment> page = new Page<>(current, size);
        QueryWrapper<Comment> qw = new QueryWrapper<>();
        //根据id查询所有评论
        qw.eq("article_id",articleId).ne("status",0).orderByDesc("create_time");
        List<Comment> allComments = commentMapper.selectList(qw);
        //根据id分页查询顶级评论
        qw.eq("pid",0);
        commentMapper.selectPage(page,qw);
        //设置每条评论的评论者
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            //查询所有访客
            QueryWrapper<Visitor> vQw = new QueryWrapper<>();
            vQw.select(Visitor.COL_ID,Visitor.COL_NICKNAME,Visitor.COL_AVATAR);
            List<Visitor> allVisitor = visitorMapper.selectList(vQw);
            //设置每条评论的评论者
            for (Comment comment : allComments) {
                for (Visitor visitor : allVisitor) {
                    if (comment.getVisitorId() == visitor.getId()) {
                        comment.setVisitor(visitor);
                    }
                }
            }
            //设置分页中父id为0的visitor
            List<Comment> pidList = allComments.stream().filter(comment -> comment.getPid() == 0L).collect(Collectors.toList());
            for (Comment record : page.getRecords()) {
                for (Comment comment : pidList) {
                    if(record.getId() == comment.getId()){
                        record.setVisitor(comment.getVisitor());
                    }
                }
            }
        }, poolExecutor);
        //设置每条评论的子评论
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            for (Comment parent : page.getRecords()) {
                List<Comment> children = new ArrayList<>();
                setChildrenComment(parent, children, allComments);
                parent.setChildren(children);
            }
        }, poolExecutor);
        try {
            CompletableFuture.allOf(task1,task2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return page;
    }

    /**
     * 设置子评论
     * @param parent
     * @param children
     * @param allComments
     */
    private void setChildrenComment(Comment parent, List<Comment> children, List<Comment> allComments) {
        for (Comment comment : allComments) {
            if(parent.getId()==comment.getPid()){
                children.add(comment);
                setChildrenComment(comment,children,allComments);
            }
        }
    }
}
