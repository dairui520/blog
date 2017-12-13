package com.jt.blog.mapper;

import com.jt.blog.dto.CommentDto;
import com.jt.blog.model.Comment;
import com.jt.blog.mybatis.common.MyMapper;

import java.util.List;

public interface CommentMapper extends MyMapper<Comment> {

    List<CommentDto> getByBlogId(Long blogId);
}