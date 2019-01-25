package com.example.iblog.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class CommonException {

    // TODO: customer exception handler
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(Exception e) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setErrorCode(0);
        responseResult.setMessage(e.getMessage() + "Common");
        return responseResult;
    }
}
