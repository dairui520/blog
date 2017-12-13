package com.jt.blog.service;

import com.jt.blog.common.model.Page;
import com.jt.blog.common.model.Response;
import com.jt.blog.model.Blog;
import com.jt.blog.vo.BlogQuery;

import java.util.Map;

/**
 * Blog
 * @author : 戴瑞
 * @create 2016-08-30 11
 **/
public interface BlogReadService {

    /**
     * 分页查询
     * @param page
     * @param query
     * @return
     */
    Response<Page<Blog>> page(Page<Blog> page,BlogQuery query);

    Response<Blog> get(Long id);

    Response<Blog> get(Long id,Long userId);

    boolean isExist(String url);

    Response<Map<String,Blog>> getPrevAndNextBlog(Long id);
}
