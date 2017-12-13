package com.jt.blog.dto;

import javax.persistence.*;

/**
 * @author : 戴瑞
 * @create : 2017-07-15 19:45
 * @Description :添加了分类的数量的统计字段 Category数据传输对象
 **/

public class CategoryDto {


    private Long id;

    private String name;

    private int sortIndex;

    private Long userId;

    private Integer blogNum;// blog 统计数量

    public Integer getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(Integer blogNum) {
        this.blogNum = blogNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
