package com.jt.blog.crawler;

import com.jt.blog.service.BlogReadService;
import com.jt.blog.service.BlogWriteService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : 戴瑞
 * @create 2016-12-12 20
 **/
@Component
public class BlogOperationUtil {

    public static BlogWriteService blogWriteService;

    public static BlogReadService blogReadService;

    @Resource
    public void setBlogWriteService(BlogWriteService blogWriteService){
        BlogOperationUtil.blogWriteService = blogWriteService;
    }

    @Resource
    public void setBlogReadService(BlogReadService blogReadService){
        BlogOperationUtil.blogReadService = blogReadService;
    }
}
