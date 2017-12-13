package com.jt.blog.common.model;

import org.springframework.context.annotation.Description;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * @author : 戴瑞
 * @create 2016-08-30 10
 **/
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 7028505176571583916L;

    private List<T> data;// 数据

    private Long total;// 总记录数

    private Integer pageMax = Integer.valueOf(1);// 总页面

    private Boolean isEmpty = Boolean.valueOf(true);

    private Integer pageNo = Integer.valueOf(1);// 当前页面(默认为1)

    private Integer pageSize = Integer.valueOf(10);// 页面的行数()

    public Page(){
    }

    public Page(List data){
        this.data = data;
        if(data!=null&&data.size()>0){
            this.isEmpty = Boolean.valueOf(false);
        }
    }

    public Page(List data , Integer total){
        this.data = data;
        if(data != null && data.size() > 0) {
            this.isEmpty = Boolean.valueOf(false);
        }
        this.total = Long.valueOf(total);
        Double temp = Math.floor(total.doubleValue()/pageSize);
        this.pageMax = temp.intValue();
    }

    public Integer getOffset() {
        Integer offset = Integer.valueOf((this.pageNo.intValue() - 1) * this.pageSize.intValue());
        return Integer.valueOf(offset.intValue() > 0?offset.intValue():0);
    }

    public Integer getLimit() {
        return Integer.valueOf(this.pageSize.intValue() > 0?this.pageSize.intValue():20);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        if(data!=null&&data.size()>0){
            this.isEmpty = Boolean.valueOf(false);
        }
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageMax() {
        return pageMax;
    }

    public void setPageMax(Integer pageMax) {
        this.pageMax = pageMax;
    }
}
