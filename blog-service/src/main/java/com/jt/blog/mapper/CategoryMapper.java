package com.jt.blog.mapper;

import com.jt.blog.dto.CategoryDto;
import com.jt.blog.model.Category;
import com.jt.blog.mybatis.common.MyMapper;

import java.util.List;

public interface CategoryMapper extends MyMapper<Category> {

    List<CategoryDto> getListAndCount();
}