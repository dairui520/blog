package com.jt.blog.controller;

import com.jt.blog.common.model.Response;
import com.jt.blog.model.User;
import com.jt.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 戴瑞
 * @Description :前台登陆Controller
 * @create : 2017-06-14 11:09
 **/
@Controller
@RequestMapping("/visitor/")
public class LoginController extends CommonController {

    @Autowired
    private UserService userService;


    @RequestMapping("register")
    @ResponseBody
    public Object register(HttpServletRequest request, HttpSession session, User user) {
        String ip = getRemoteHost(request);
        user.setIp(ip);
        user.setLastLoginTime(new Date());
        user.setAdmin(0); //游客身份标识
        String head = user.getHead();
        user.setHead("/image"+head);
        Response<User> response = userService.register(user);
        Map<String, Object> map = new HashMap<String, Object>();
        if (!response.isSuccess()) {
            map.put("flag", false);
            map.put("errMsg", response.getError());
            return map;
        }
        session.setAttribute("visitor", response.getResult());
        map.put("flag", true);
        return map;
    }


    @RequestMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpSession session,
                        String account, String password) {
        String ip = getRemoteHost(request);
        Response<User> response = userService.login(account, password, ip, 0);
        Map<String, Object> map = new HashMap<String, Object>();
        if (!response.isSuccess()) {
            map.put("flag", false);
            map.put("errMsg", response.getError());
            return map;
        }
        User user = response.getResult();
        session.setAttribute("visitor", user);
        map.put("flag", true);
        map.put("tip", user.getName());
        return map;
    }

    @RequestMapping("logout")
    @ResponseBody
    public Object logout(HttpSession session) {
        session.invalidate();
        return true;
    }
}
