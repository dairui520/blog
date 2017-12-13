package com.jt.blog.service;

import com.jt.blog.common.model.Response;
import com.jt.blog.model.User;


/**
 * @author : 戴瑞
 * @create 2016-08-30 11
 **/
public interface UserService {

    Response<User> login(String account, String password, String ip, Integer back);

    Response<User> register(User user);

    void update(User user);
}
