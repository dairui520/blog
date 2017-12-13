package com.jt.blog.service;

import com.jt.blog.dto.CategoryDto;
import com.jt.blog.mapper.CategoryMapper;
import com.jt.blog.model.Category;
import com.jt.blog.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author : 戴瑞
 * @Description :博客分类层，加入了redis缓存
 * @create : 2017-05-18 14:20
 **/
@Service
@Transactional
public class CategoryServiceImpl extends AbstractBaseService<Category> implements CategoryService {

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Category> list() {
        Example example = new Example(User.class);
        example.setOrderByClause("sort_index");
        List<Category> categories = mapper.selectByExample(example);
        return categories;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<CategoryDto> listAndCount() {
        // 强转
        CategoryMapper categoryMapper=(CategoryMapper)mapper;
        List<CategoryDto> list = categoryMapper.getListAndCount();
        return list;
    }

    public void create(Category category) {
        mapper.insert(category);
    }

    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void update(Category category) {
        mapper.updateByPrimaryKeySelective(category);
    }
}
