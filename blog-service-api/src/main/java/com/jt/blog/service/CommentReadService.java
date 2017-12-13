package com.jt.blog.service;

import com.jt.blog.common.model.TreeNode;
import com.jt.blog.dto.CommentDto;
import com.jt.blog.model.Comment;

/**
 * @author : 戴瑞
 * @create 2016-08-30 11
 **/
public interface CommentReadService {

    TreeNode<CommentDto> get(Long blogId, Integer sort);
}
