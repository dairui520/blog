package com.jt.blog.controller;

import com.jt.blog.common.exception.JsonResponseException;
import com.jt.blog.common.model.Page;
import com.jt.blog.common.model.Response;
import com.jt.blog.dto.CategoryDto;
import com.jt.blog.model.Blog;
import com.jt.blog.model.Category;
import com.jt.blog.model.User;
import com.jt.blog.service.BlogReadService;
import com.jt.blog.service.BlogWriteService;
import com.jt.blog.service.CategoryService;
import com.jt.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author : 戴瑞
 * @create 2016-08-30 10
 **/
@Controller
@RequestMapping("/back/blog")
public class BackBlogController {

    @Autowired
    BlogReadService blogReadService;

    @Autowired
    BlogWriteService blogWriteService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String page(Model model,Page<Blog> page, BlogQuery query,HttpSession session){
        User user = (User)session.getAttribute("user");
        query.setUserId(user.getId());
        Response<Page<Blog>>response = blogReadService.page(page,query);
        if(!response.isSuccess()){
            throw new JsonResponseException(response.getError());
        }
        model.addAttribute("page",response.getResult());
        List<CategoryDto> categories = categoryService.listAndCount();
        model.addAttribute("categories",categories);
        return "back/blog/page";
    }

    @RequestMapping("/edit")
    public String edit(Model model,@RequestParam(required = false) Long id,HttpSession session){
        Blog blog = new Blog();
        if(null!=id){
            User user = (User)session.getAttribute("user");
            Response<Blog>response = blogReadService.get(id,user.getId());
            if(!response.isSuccess()){
                throw new JsonResponseException(response.getError());
            }
            blog = response.getResult();
        }
        model.addAttribute("blog",blog);
        List<Category> categories = categoryService.list();
        model.addAttribute("categories",categories);
        return "back/blog/edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(Blog blog, HttpSession session){
        try{
            Long id = blog.getId();
            if(id==null){
                User user = (User)session.getAttribute("user");
                blog.setAuthor(user.getName());
                blog.setUserId(user.getId());
                blog.setCreateTime(new Date());
                blog.setLastModifyTime(new Date());
                blogWriteService.create(blog);
            }else {
                blog.setLastModifyTime(new Date());
                blogWriteService.update(blog);
            }
        }catch (Exception e){
            throw new JsonResponseException(e.getMessage());
        }
        return true;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        blogWriteService.delete(id);
        return true;
    }
}
