package com.example.iblog.controller;

import com.example.iblog.common.PageResponseResult;
import com.example.iblog.common.ResponseResult;
import com.example.iblog.common.ServiceErrorCode;
import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import com.example.iblog.services.impl.AuthorServiceImpl;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@Api("作者APIs")
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorServiceImpl authorService;

    @ApiOperation("获取作者列表")
    @GetMapping
    public ResponseResult<List<Author>> getAllAuthors() {
        ResponseResult<List<Author>> responseResult = new ResponseResult();
        try {
            List<Author> authors = authorService.getAll();
            if (authors != null) {
                responseResult.setResult(authors);
                responseResult.setMessage(ServiceErrorCode.SERVICE_OK.getMessage());
            } else {
                responseResult.setErrorCode(ServiceErrorCode.RESOURCE_NOT_FOUNDED_ERROR.getCode());
                responseResult.setMessage(ServiceErrorCode.REMOVE_RESOURCE_ERROR.getMessage());
            }
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.RESOURCE_NOT_FOUNDED_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @ApiOperation(value = "获取作者信息", notes = "根据authorId获取用户信息")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @GetMapping("/{authorId}")
    public ResponseResult<Author> getAuthor(@PathVariable BigInteger authorId) {
        ResponseResult<Author> responseResult = new ResponseResult();
        try {
            Author author = authorService.getAuthor(authorId);
            if (author != null) {
                responseResult.setResult(author);
                responseResult.setMessage(ServiceErrorCode.SERVICE_OK.getMessage());
            } else {
                responseResult.setErrorCode(ServiceErrorCode.RESOURCE_NOT_FOUNDED_ERROR.getCode());
                responseResult.setMessage(ServiceErrorCode.RESOURCE_NOT_FOUNDED_ERROR.getMessage());
            }
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.RESOURCE_NOT_FOUNDED_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @ApiOperation(value = "添加作者", notes = "根据Author对象创建作者")
    @ApiImplicitParam(name = "author", value = "作者对象实体", required = true, dataType = "Author")
    @PostMapping("/create")
    public ResponseResult createAuthor(@Valid @RequestBody Author author) {
        ResponseResult responseResult = new ResponseResult();
        try {
            authorService.createAuthor(author);
            responseResult.setSuccess(true);
            responseResult.setMessage(ServiceErrorCode.SERVICE_OK.getMessage());
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.INSERT_RESOURCE_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @ApiOperation(value = "获取文章列表", notes = "根据authorId获取该作者下的所有文章")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @GetMapping("/{authorId}/articlelist")
    public PageResponseResult<Article> getArticleListByAuthorId(
            @PathVariable BigInteger authorId,
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = authorService.getArticleList(authorId);
        return new PageResponseResult<Article>(articleList);
    }

    @ApiOperation(value = "更新作者信息", notes = "根据Author实体更新作者信息")
    @ApiImplicitParam(name = "author", value = "作者对象实体", required = true, dataType = "Author")
    @PutMapping("/modify")
    public ResponseResult updateArticle(@RequestBody Author author) {
        ResponseResult responseResult = new ResponseResult();
        try {
            authorService.updateAuthor(author);
            responseResult.setSuccess(true);
            responseResult.setMessage(ServiceErrorCode.SERVICE_OK.getMessage());
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.MODIFY_RESOURCE_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @ApiOperation(value = "删除作者", notes = "根据authorId删除指定作者")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @DeleteMapping("/{authorId}")
    public ResponseResult deleteArticle(@PathVariable BigInteger authorId) {
        ResponseResult responseResult = new ResponseResult();
        try {
            authorService.deleteAuthorById(authorId);
            responseResult.setSuccess(true);
            responseResult.setMessage("删除作者成功");
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.REMOVE_RESOURCE_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }
}
