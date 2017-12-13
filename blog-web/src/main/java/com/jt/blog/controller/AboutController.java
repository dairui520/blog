package com.jt.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : 戴瑞
 * @Description :
 * @create : 2017-06-23 19:40
 **/
@Controller
public class AboutController {

    @RequestMapping("/about/me")
    public String about(){
        return "front/about";
    }
}
