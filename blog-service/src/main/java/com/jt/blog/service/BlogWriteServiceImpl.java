package com.jt.blog.service;

import com.jt.blog.model.Blog;
import com.jt.blog.model.BlogStatistics;
import com.jt.blog.mapper.BlogStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : 戴瑞
 * @Description :博客服务层的读操作
 * @create : 2017-05-25 18:20
 **/

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BlogWriteServiceImpl extends AbstractBaseService<Blog> implements BlogWriteService {
    @Autowired
    BlogStatisticsMapper blogStatisticsMapper;

    public void create(Blog blog) {
        mapper.insert(blog);
        BlogStatistics blogStatistics = new BlogStatistics(blog.getId());
        blogStatisticsMapper.insert(blogStatistics);
    }

    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void update(Blog blog) {
        mapper.updateByPrimaryKeySelective(blog);
    }
}
