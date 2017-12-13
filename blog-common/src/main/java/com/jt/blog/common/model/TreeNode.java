package com.jt.blog.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : 戴瑞
 * @Description :评论pojo 树节点
 * @create 2016-09-21 09
 **/
public class TreeNode<T> implements Serializable {

    private static final long serialVersionUID = 4554281634919170481L;

    private T node;

    private List<TreeNode<T>> children;

    public TreeNode() {
    }

    public TreeNode(T node) {
        this.node = node;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public void addChild(TreeNode<T> child){
        if(children==null){
            children = new ArrayList<>();
        }
        children.add(child);
    }

}

