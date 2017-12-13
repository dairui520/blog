package com.jt.blog.controller;

import com.jt.blog.common.exception.JsonResponseException;
import com.jt.blog.model.Category;
import com.jt.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : 戴瑞
 * @create 2016-08-30 10
 **/
@Controller
@RequestMapping("/back/category")
public class BackCategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String list(Model model){
        List<Category> categories = categoryService.list();
        model.addAttribute("categories",categories);
        return "back/category/list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(Category category){
        try{
            Long id = category.getId();
            if(null==id){
                categoryService.create(category);
            }else{
                categoryService.update(category);
            }
        }catch (Exception e){
            throw new JsonResponseException(e.getMessage());
        }
        return true;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        try{
            categoryService.delete(id);
        }catch (Exception e){
            throw new JsonResponseException(e.getMessage());
        }
        return true;
    }
}
