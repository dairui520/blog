package com.jt.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Tim on 2016/8/27.
 */
@Entity
@Table(name = "blog_statistics")
public class BlogStatistics implements Serializable{

    private static final long serialVersionUID = 8916763803501983828L;
    @Id
    @Column(name="blog_id")
    private Long blogId;

    @Column(name="comment_count")
    private Integer commentCount=0;

    @Column(name="view_count")
    private Integer viewCount=0;

    @Column(name="like_count")
    private Integer likeCount=0;

    public BlogStatistics() {
        super();
    }

    public BlogStatistics(Long blogId) {
        this.blogId = blogId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
