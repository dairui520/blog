package com.jt.blog.service;

import com.google.common.base.Objects;
import com.jt.blog.common.model.Response;
import com.jt.blog.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author : 戴瑞
 * @Description :博客评论层
 * @create : 2017-05-20 11:20
 **/
@Service
@Transactional
public class UserServiceImpl extends AbstractBaseService<User> implements UserService{

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Response<User> login(String account, String password, String ip, Integer back) {
        Response<User> response = new Response<User>();
        try{
            checkArgument(!StringUtils.isEmpty(account), "账号为空");
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("account",account);
            if(back==1){
                // 代表登录的管理员
                criteria.andEqualTo("admin","1");
            }else if (back==0){
                // 代表登录的游客
                criteria.andEqualTo("admin","0");
            }
            List<User> users = (List)mapper.selectByExample(example);
            checkArgument(users != null&&users.size()>0, "账号不存在");
            User user = users.get(0);
            String realPassword = user.getPassword();
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
            checkArgument(Objects.equal(md5Password, realPassword), "密码错误");
            user.setLastLoginIp(ip);
            user.setLastLoginTime(new Date());
            mapper.updateByPrimaryKeySelective(user);
            response.setResult(user);
        } catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    public Response<User> register(User user) {
        Response<User> response = new Response<User>();
        try{
            checkArgument(!(null==user), "账号为空");
            String account = user.getAccount();
            checkArgument(!StringUtils.isEmpty(account), "账号为空");
            String password = user.getPassword();
            checkArgument(!StringUtils.isEmpty(password), "密码为空");
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("account",account);
            List<User> users = (List)mapper.selectByExample(example);
            checkArgument(users == null||users.size()<1, "账号已存在");
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
            user.setPassword(md5Password);
            mapper.insert(user);
            response.setResult(user);
        } catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public void update(User user) {
        mapper.updateByPrimaryKeySelective(user);
    }
}
