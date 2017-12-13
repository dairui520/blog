package com.jt.blog.service;/**
 * Created by Administrator on 2016/8/29.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author : 戴瑞
 * @create 2016-08-29 14
 **/
public abstract class AbstractBaseService<T> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Mapper<T> mapper;
}
