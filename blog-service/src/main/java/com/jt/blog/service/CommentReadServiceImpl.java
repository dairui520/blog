package com.jt.blog.service;

import com.jt.blog.common.model.TreeNode;
import com.jt.blog.dto.CommentDto;
import com.jt.blog.mapper.CommentMapper;
import com.jt.blog.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 戴瑞
 * @Description :博客评论层
 * @create : 2017-05-26 10:20
 **/
@Service
public class CommentReadServiceImpl extends AbstractBaseService<Comment> implements CommentReadService {

    @Override
    public TreeNode<CommentDto> get(Long blogId, Integer sort) {
        CommentMapper commentMapper = (CommentMapper) mapper;
        List<CommentDto> comments = commentMapper.getByBlogId(blogId);
        TreeNode<CommentDto> root = new TreeNode<>();
        cycleHandle(root , comments);
        return root;
    }

    /**
     * 递归 把list集合转变为树型结构
     * 为父节点添加叶子节点
     * @param father
     * @param comments
     */
    private void cycleHandle(TreeNode<CommentDto> father, List<CommentDto> comments) {
        CommentDto fc = father.getNode();
        for (CommentDto comment : comments) {
            if ((fc == null && comment.getPid() == null) ||
                    (fc != null && fc.getId() == comment.getPid())) {
                TreeNode<CommentDto> node = new TreeNode<>(comment);
                father.addChild(node);
                cycleHandle(node, comments);
            }
        }
    }
}
