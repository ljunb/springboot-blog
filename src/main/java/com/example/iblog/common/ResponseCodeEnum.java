package com.example.iblog.common;

public enum ResponseCodeEnum {
    SERVICE_OK(10000, "请求成功"),
    SERVICE_ERROR(20000, "服务错误"),
    RESOURCE_NOT_FOUNDED_ERROR(10001, "资源不存在"),
    INSERT_RESOURCE_ERROR(10003, "插入资源错误"),
    MODIFY_RESOURCE_ERROR(10004, "修改资源错误"),
    REMOVE_RESOURCE_ERROR(10005, "删除资源错误")
    ;
    private String message;
    private int code;

    private ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
