package com.lloyvet.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.blog.domain.ArticleTag;
import com.lloyvet.blog.mapper.ArticleTagMapper;
import com.lloyvet.blog.service.ArticleTagService;
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService{

}
