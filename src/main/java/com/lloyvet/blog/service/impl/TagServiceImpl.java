package com.lloyvet.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.blog.common.Constant;
import com.lloyvet.blog.common.ResultObj;
import com.lloyvet.blog.common.TableResult;
import com.lloyvet.blog.domain.ArticleTag;
import com.lloyvet.blog.mapper.ArticleTagMapper;
import com.lloyvet.blog.to.ArticleTagTo;
import com.lloyvet.blog.to.TagTo;
import com.lloyvet.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.mapper.TagMapper;
import com.lloyvet.blog.domain.Tag;
import com.lloyvet.blog.service.TagService;
import org.springframework.util.StringUtils;

/**
 * @author zihao Shen
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService{


    @Autowired
    TagMapper tagMapper;

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public ResultObj selectTagList(Integer page, Integer limit, TagVo tagVo) {
        Page<Tag> tagPage = new Page<>(page,limit);
        QueryWrapper<Tag> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(tagVo.getName()),"name",tagVo.getName())
                .between(!StringUtils.isEmpty(tagVo.getStartDate())&&!StringUtils.isEmpty(tagVo.getEndDate()),"update_time",
                        tagVo.getStartDate(),tagVo.getEndDate());
        Page<Tag> tagPages = tagMapper.selectPage(tagPage, qw);
        List<Tag> tags = tagPages.getRecords();
        List<TagTo> tagTos = null;
        if(tags!=null&&tags.size()>0){
            tagTos = tags.stream().map(tag -> {
                TagTo tagTo = new TagTo();
                BeanUtil.copyProperties(tag,tagTo);
                Integer articleCount = articleTagMapper.selectCount(new QueryWrapper<ArticleTag>().eq("tag_id", tag.getId()));
                tagTo.setArticleCount(articleCount);
                return tagTo;
            }).collect(Collectors.toList());
        }
        return TableResult.tableOk(tagTos,Integer.valueOf(String.valueOf(tagPage.getTotal())));
    }

    @Override
    public Object listColor() {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.select(Tag.Table.COLOR)
                .groupBy(Tag.Table.COLOR);
        List<Tag> tags = tagMapper.selectList(wrapper);
        return tags.stream().map(Tag::getColor).collect(Collectors.toList());
    }

    @Override
    public List<Tag>  listTagByArticleId(Long id) {
        QueryWrapper<Tag> qw = new QueryWrapper<>();
        List<ArticleTag> articleTags = articleTagMapper.selectList(new QueryWrapper<ArticleTag>().eq("article_id", id));
        List<Long> ids = articleTags.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        qw.in("id",ids);
        return tagMapper.selectList(qw);
    }


    @Override
    public List<Tag> listByArticleCount() {
        List<Tag> tags = this.list();
        for (Tag tag : tags) {
            Integer count = articleTagMapper.selectCount(new QueryWrapper<ArticleTag>().eq("tag_id", tag.getId()));
                tag.setArticleCount(count);
        }
        tags = tags.stream().filter(tag ->
            tag.getArticleCount()!=0).collect(Collectors.toList());
        return tags;
    }

    @Override
    public Integer getCount() {
        List<Long> articleTagTos = articleTagMapper.selectTagCount();
        return articleTagTos.size();
    }
}
