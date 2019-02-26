package com.example.iblog.controller;

import com.example.iblog.common.ResponseResult;
import com.example.iblog.common.ResponseCodeEnum;
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
    public ResponseResult<Comment> createComment(@Valid @RequestBody Comment comment) {
        try {
            int resultCode = commentService.createComment(comment);
            if (resultCode > 0) {
                return new ResponseResult<>(comment, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.INSERT_RESOURCE_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "回复文章评论", notes = "根据评论对象回复文章评论")
    @ApiImplicitParam(name = "comment", value = "回复的评论对象实体", required = true, dataType = "Comment")
    @PostMapping("/reply")
    public ResponseResult<Comment> replyComment(@Valid @RequestBody Comment comment) {
        try {
            int resultCode = commentService.createReply(comment);
            if (resultCode > 0) {
                return new ResponseResult<>(comment, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.INSERT_RESOURCE_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }
}
