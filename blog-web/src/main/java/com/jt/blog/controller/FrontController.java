package com.jt.blog.controller;

import com.jt.blog.common.exception.JsonResponseException;
import com.jt.blog.common.model.Page;
import com.jt.blog.common.model.Response;
import com.jt.blog.common.model.TreeNode;
import com.jt.blog.common.redis.util.RedisUtil;
import com.jt.blog.dto.CategoryDto;
import com.jt.blog.dto.CommentDto;
import com.jt.blog.model.*;
import com.jt.blog.service.*;
import com.jt.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 戴瑞
 * @Description :前台控制器
 * @create : 2017-05-25 18:20
 **/
@Controller
public class FrontController extends CommonController{
    @Autowired
    private BlogReadService blogReadService;

    @Autowired
    private BlogStatisticsService blogStatisticsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentReadService commentReadService;

    @Autowired
    private CommentWriteService commentWriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("")
    public String index(Model model, Page<Blog> page, BlogQuery query){
        query.setPersonal(0);
        Response<Page<Blog>> response = blogReadService.page(page,query);
        if(!response.isSuccess()){
            throw new JsonResponseException(response.getError());
        }
        model.addAttribute("page",response.getResult());
        model.addAttribute("query",query);
        List<CategoryDto> categories = categoryService.listAndCount();
        model.addAttribute("categories",categories);
        return "front/index";
    }

    @RequestMapping("category/{id}")
    public String category(Model model, Page<Blog> page, @PathVariable Long id){
        BlogQuery query = new BlogQuery();
        query.setPersonal(0);
        query.setCategoryId(id);
        Response<Page<Blog>> response = blogReadService.page(page,query);
        if(!response.isSuccess()){
            throw new JsonResponseException(response.getError());
        }
        model.addAttribute("page",response.getResult());
        List<CategoryDto> categories = categoryService.listAndCount();
        model.addAttribute("categories",categories);
        return "front/index";
    }

    @RequestMapping("blog/{id}")
    public String blog(HttpServletRequest request,Model model, @PathVariable Long id){
        Response<Blog> response = blogReadService.get(id);
        if(!response.isSuccess()){
            throw new JsonResponseException(response.getError());
        }
        Blog blog = response.getResult();
        model.addAttribute("blog",blog);
        Response<Map<String,Blog>> response2 = blogReadService.getPrevAndNextBlog(id);
        if(!response2.isSuccess()){
            throw new JsonResponseException(response2.getError());
        }
        model.addAttribute("map",response2.getResult());
        List<CategoryDto> categories = categoryService.listAndCount();
        model.addAttribute("categories",categories);
        String ip = getRemoteHost(request);
        String key = ip+":view:"+id;
        Object obj = redisUtil.get(key);
        if(obj==null){
            BlogStatistics blogStatistics = blog.getBlogStatistics();
            blogStatistics.setViewCount(blogStatistics.getViewCount()+1);
            blogStatisticsService.update(blogStatistics);
            redisUtil.set(key,1,3600L);
        }
        return "front/blogWithMyComment";
    }

    @RequestMapping("blog/like/{blogId}")
    @ResponseBody
    public Object blogLike(HttpServletRequest request, @PathVariable Long blogId){
        String ip = getRemoteHost(request);
        String key = ip+":like:"+blogId;
        Object obj = redisUtil.get(key);
        if(obj==null){
            BlogStatistics blogStatistics = blogStatisticsService.get(blogId);
            blogStatistics.setLikeCount(blogStatistics.getLikeCount()+1);
            blogStatisticsService.update(blogStatistics);
            redisUtil.set(key,1,3600L);
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("comments/{blogId}")
    @ResponseBody
    public Object comments(@PathVariable Long blogId){
        TreeNode<CommentDto> root = commentReadService.get(blogId,1);
        return root;
    }

    @RequestMapping("comment/reply")
    public String reply(Model model,HttpSession session,Comment comment){
        User user = (User)session.getAttribute("visitor");
        if(user==null){
            return "front/login";
        }else{
            model.addAttribute("comment",comment);
            return "front/comment";
        }
    }

    @RequestMapping("comment/create")
    @ResponseBody
    public Object comment(HttpSession session,Comment comment){
        User user = (User)session.getAttribute("visitor");
        Map<String,Object> map = new HashMap<String,Object>();
        if(user==null){
            map.put("flag",false);
            map.put("errMsg","please login first");
        }else{
            comment.setReplyerId(user.getId());
            commentWriteService.create(comment);
            BlogStatistics blogStatistics = blogStatisticsService.get(comment.getBlogId());
            blogStatistics.setCommentCount(blogStatistics.getCommentCount()+1);
            blogStatisticsService.update(blogStatistics);
            map.put("flag",true);
        }
        return map;
    }

}
