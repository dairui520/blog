package com.jt.blog.service;

import com.jt.blog.dto.CategoryDto;
import com.jt.blog.model.Category;

import java.util.List;

/**
 * @author : 戴瑞
 * @create 2016-08-30 11
 **/
public interface CategoryService{

    List<Category> list();

    List<CategoryDto> listAndCount();

    void create(Category category);

    void delete(Long id);

    void update(Category category);
}
