package com.jt.blog.controller;

import com.jt.blog.crawler.BlogCrawlerController;
import com.jt.blog.crawler.BlogParserRegEx;
import com.jt.blog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author : 戴瑞
 * @create 2016-12-09 20
 **/
@Controller
@RequestMapping("/back/crawler")
public class CrawlerController {

    @RequestMapping("")
    public String edit(){
        return "back/crawler/edit";
    }

    @RequestMapping("/start")
    @ResponseBody
    public Object start(HttpSession session,final BlogParserRegEx regEx){
        final User user = (User)session.getAttribute("user");
        regEx.setUserId(user.getId());
        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    BlogCrawlerController.crawler(regEx);
                } catch (Exception e) {
                    BlogCrawlerController.setStatus(user.getId(),0);
                }
            }
        };
        thread.start();
        return true;

    }

    @RequestMapping("/status")
    @ResponseBody
    public Object status(HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer status = BlogCrawlerController.status(user.getId());
        return status;
    }
}
