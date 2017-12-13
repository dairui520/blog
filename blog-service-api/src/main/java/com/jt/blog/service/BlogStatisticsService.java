package com.jt.blog.service;

import com.jt.blog.model.BlogStatistics;

/**
 * @author : 戴瑞
 * @create 2016-09-27 09
 **/
public interface BlogStatisticsService {

    BlogStatistics get(Long blogId);

    BlogStatistics update(BlogStatistics blogStatistics);

}
