package com.example.iblog.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"handler"})
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 0L;

    private T result;
    private boolean isSuccess;
    private int errorCode;
    private String message;

    public ResponseResult() {}

    public ResponseResult(T result) {
        this.result = result;
        this.isSuccess = true;
    }

    public ResponseResult(int errorCode, String message) {
        this.isSuccess = false;
        this.errorCode = errorCode;
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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.isSuccess = false;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(ResponseResult<?> responseResult) {
        this.setErrorCode(responseResult.getErrorCode());
        this.setMessage(responseResult.getMessage());
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "result=" + result +
                ", isSuccess=" + isSuccess +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
