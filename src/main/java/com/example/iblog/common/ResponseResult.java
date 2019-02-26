package com.example.iblog.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"handler"})
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 0L;

    private T result;
    private boolean isSuccess;
    private int statusCode;
    private String message;

    public ResponseResult(T result, ResponseCodeEnum responseCodeEnum) {
        this.result = result;
        this.isSuccess = true;
        this.statusCode = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMessage();
    }

    public ResponseResult(ResponseCodeEnum responseCodeEnum) {
        this.isSuccess = false;
        this.statusCode = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMessage();
    }

    public ResponseResult(int statusCode, String message) {
        this.isSuccess = false;
        this.statusCode = statusCode;
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.isSuccess = true;
        this.result = result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.isSuccess = false;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "result=" + result +
                ", isSuccess=" + isSuccess +
                ", statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
