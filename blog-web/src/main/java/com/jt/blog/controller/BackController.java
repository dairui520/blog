package com.jt.blog.controller;

import com.jt.blog.common.model.Response;
import com.jt.blog.model.User;
import com.jt.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 戴瑞
 * @Description :前台控制器
 * @create 2016-09-01 12:09
 **/
@Controller
@RequestMapping("/back")
public class BackController extends CommonController{

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String index(){
        return "back/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String target){
        return "back/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object login(HttpServletRequest request,
                        @RequestParam("account") String account,
                        @RequestParam("password") String password,
                        @RequestParam("verification") String verification,
                        HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        String vcode = (String)session.getAttribute("vcode");
        if(!verification.equalsIgnoreCase(vcode)){
            map.put("flag",false);
            map.put("errMsg","验证码错误");
        }else{
            String ip = getRemoteHost(request);
            Response<User> response = userService.login(account,password,ip,1);
            if(!response.isSuccess()){
                map.put("flag",false);
                map.put("errMsg",response.getError());
            }else{
                session.setAttribute("user", response.getResult());
                map.put("flag",true);
            }
        }
        return map;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object logout(HttpSession session) {
        session.invalidate();
        return true;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@RequestParam(required = false) String target){
        return "back/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object register(HttpServletRequest request,
                        HttpSession session, User user,@RequestParam("verification") String verification){
        String ip = getRemoteHost(request);
        Map<String,Object> map = new HashMap<String,Object>();
        if(ip.equals("127.0.0.1")){
            String vcode = (String)session.getAttribute("vcode");
            if(!verification.equalsIgnoreCase(vcode)){
                map.put("flag",false);
                map.put("errMsg","pic code is error");
            }else {
                user.setIp(ip);
                user.setLastLoginTime(new Date());
                user.setAdmin(1);
                Response<User> response = userService.register(user);
                if (!response.isSuccess()) {
                    map.put("flag", false);
                    map.put("errMsg", response.getError());
                }
                session.setAttribute("user", response.getResult());
                map.put("flag", true);
            }
        }else{
            map.put("flag",false);
            map.put("errMsg","no authorization");
        }
        return map;
    }

}
