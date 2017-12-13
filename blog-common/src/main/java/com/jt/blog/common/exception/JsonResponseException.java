package com.jt.blog.common.exception;/**
 * Created by Administrator on 2016/8/29.
 */

/**
 * @author : 戴瑞
 * @create 2016-08-29 14
 **/
public class JsonResponseException extends RuntimeException {
    private int status = 500;
    private String message = "unknow error";

    public JsonResponseException() {
    }

    public JsonResponseException(String message) {
        this.message = message;
    }

    public JsonResponseException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
