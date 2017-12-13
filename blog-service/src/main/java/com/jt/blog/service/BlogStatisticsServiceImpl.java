package com.jt.blog.service;

import com.jt.blog.model.BlogStatistics;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author : 戴瑞
 * @Description :博客统计服务层,使用redis缓存博客统计的情况
 * @create : 2017-05-18 14:20
 *
 * @CacheConfig(cacheNames = "blogStatistics") value是cacheNames的别名
 **/
@CacheConfig(cacheNames = "blogStatistics")
@Service
public class BlogStatisticsServiceImpl extends AbstractBaseService<BlogStatistics> implements BlogStatisticsService {

    @Cacheable(key = "'BS'+#blogId")
    @Override
    public BlogStatistics get(Long blogId) {
        return mapper.selectByPrimaryKey(blogId);
    }

    @CachePut(key = "'BS'+#blogStatistics.blogId")
    @Override
    @Transactional
    public BlogStatistics update(BlogStatistics blogStatistics) {
        mapper.updateByPrimaryKeySelective(blogStatistics);
        return blogStatistics;
    }

}
