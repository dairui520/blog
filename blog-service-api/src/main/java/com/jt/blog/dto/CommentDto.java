package com.jt.blog.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : 戴瑞
 * @create 2016-09-22 10
 **/
public class CommentDto implements Serializable{

    private static final long serialVersionUID = -922218546689690755L;

    private Long id;

    private String comment;

    private Date createTime;

    private Long blogId;

    private Long pid;

    private Integer level;

    private Integer type;

    private Integer status;

    private Long receiverId;

    private String receiverName;

    private String receiverHome;

    private String receiverHead;

    private String receiverEmail;

    private Long replyerId;

    private String replyerName;

    private String replyerHome;

    private String replyerHead;

    private String replyerEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverHome() {
        return receiverHome;
    }

    public void setReceiverHome(String receiverHome) {
        this.receiverHome = receiverHome;
    }

    public String getReceiverHead() {
        return receiverHead;
    }

    public void setReceiverHead(String receiverHead) {
        this.receiverHead = receiverHead;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public Long getReplyerId() {
        return replyerId;
    }

    public void setReplyerId(Long replyerId) {
        this.replyerId = replyerId;
    }

    public String getReplyerName() {
        return replyerName;
    }

    public void setReplyerName(String replyerName) {
        this.replyerName = replyerName;
    }

    public String getReplyerHome() {
        return replyerHome;
    }

    public void setReplyerHome(String replyerHome) {
        this.replyerHome = replyerHome;
    }

    public String getReplyerHead() {
        return replyerHead;
    }

    public void setReplyerHead(String replyerHead) {
        this.replyerHead = replyerHead;
    }

    public String getReplyerEmail() {
        return replyerEmail;
    }

    public void setReplyerEmail(String replyerEmail) {
        this.replyerEmail = replyerEmail;
    }
}
