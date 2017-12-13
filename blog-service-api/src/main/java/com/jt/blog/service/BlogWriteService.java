package com.jt.blog.service;

import com.jt.blog.model.Blog;

/**
 * @author : 戴瑞
 * @create 2016-08-30 11
 **/
public interface BlogWriteService {

    void create(Blog blog);

    void delete(Long id);

    void update(Blog blog);

}
