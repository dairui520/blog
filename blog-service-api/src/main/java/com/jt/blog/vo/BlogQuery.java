package com.jt.blog.vo;

import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * @author : 戴瑞
 * @decription 查询条件pojo
 * @create 2016-08-30 15
 **/
public class BlogQuery implements Serializable {
    private static final long serialVersionUID = -7585199598535442193L;

    private static final String DEFAUTL_DATE_FORMATTER= "yyyy-MM-dd";

    private String title;

    private Long categoryId;

    private String dateStr;

    private Date beginTime;

    private Date endTime;

    private Integer personal;

    private Long userId;

    public BlogQuery() {
        super();
    }

    public BlogQuery(Long categoryId, String dateStr,Integer personal) {
        this.categoryId = categoryId;
        this.dateStr = dateStr;
        this.personal = personal;
        try {
            this.beginTime = DateUtils.parseDate(dateStr,DEFAUTL_DATE_FORMATTER);
            this.endTime = DateUtils.addSeconds(DateUtils.addDays(beginTime,1),-1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public BlogQuery(Long categoryId, Date beginTime, Date endTime,Integer personal) {
        this.categoryId = categoryId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.personal = personal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPersonal() {
        return personal;
    }

    public void setPersonal(Integer personal) {
        this.personal = personal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
