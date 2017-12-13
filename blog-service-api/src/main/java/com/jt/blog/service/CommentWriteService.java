package com.jt.blog.service;

import com.jt.blog.model.Comment;

/**
 * @author : 戴瑞
 * @create 2016-09-21 14
 **/
public interface CommentWriteService {

    void create(Comment comment);

    void delete(Long id);

    void update(Comment comment);
}
