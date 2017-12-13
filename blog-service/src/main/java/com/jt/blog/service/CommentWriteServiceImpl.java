package com.jt.blog.service;

import com.jt.blog.model.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author : 戴瑞
 * @Description :博客评论层
 * @create : 2017-06-01 13:20
 **/
@Service
@Transactional
public class CommentWriteServiceImpl  extends AbstractBaseService<Comment> implements CommentWriteService {
    @Override
    public void create(Comment comment) {
        comment.setCreateTime(new Date());
        mapper.insert(comment);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Comment comment) {

    }
}
