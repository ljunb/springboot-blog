package com.example.iblog.controller;

import com.example.iblog.common.ResponseResult;
import com.example.iblog.common.ServiceErrorCode;
import com.example.iblog.domain.Comment;
import com.example.iblog.services.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("文章评论APIs")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @ApiOperation(value = "添加文章评论", notes = "根据评论对象添加文章下的评论")
    @ApiImplicitParam(name = "comment", value = "评论对象实体", required = true, dataType = "Comment")
    @PostMapping("/create")
    public ResponseResult createComment(@Valid @RequestBody Comment comment) {
        ResponseResult responseResult = new ResponseResult();
        try {
            commentService.createComment(comment);
            responseResult.setSuccess(true);
            responseResult.setMessage(ServiceErrorCode.SERVICE_OK.getMessage());
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.INSERT_RESOURCE_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }
}
