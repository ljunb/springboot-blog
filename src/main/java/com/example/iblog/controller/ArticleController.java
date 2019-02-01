package com.example.iblog.controller;

import com.example.iblog.common.ResponseResult;
import com.example.iblog.common.ServiceErrorCode;
import com.example.iblog.domain.Article;
import com.example.iblog.services.impl.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;

@Api("文章APIs")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServiceImpl articleService;

    @ApiOperation("获取所有文章列表")
    @GetMapping()
    public ResponseResult<List<Article>> getArticleList() {
        ResponseResult<List<Article>> responseResult = new ResponseResult();
        try {
            List<Article> articleList = articleService.getArticleList();
            if (articleList != null) {
                responseResult.setResult(articleList);
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

    @ApiOperation(value = "获取文章信息", notes = "根据articleId获取文章信息")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "number")
    @GetMapping("/{articleId}")
    public ResponseResult<Article> getArticle(@PathVariable BigInteger articleId) {
        ResponseResult<Article> responseResult = new ResponseResult();
        try {
            Article article = articleService.getArticle(articleId);
            if (article != null) {
                responseResult.setResult(article);
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

    @ApiOperation(value = "添加文章", notes = "根据Article对象创建文章")
    @ApiImplicitParam(name = "article", value = "文章对象实体", required = true, dataType = "Article")
    @PostMapping("/create")
    public ResponseResult createArticle(@Valid @RequestBody Article article) {
        ResponseResult responseResult = new ResponseResult();
        try {
            articleService.createArticle(article);
            responseResult.setSuccess(true);
            responseResult.setMessage(ServiceErrorCode.SERVICE_OK.getMessage());
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.INSERT_RESOURCE_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @ApiOperation(value = "更新文章信息", notes = "根据Article实体更新文章信息")
    @ApiImplicitParam(name = "article", value = "文章对象实体", required = true, dataType = "Article")
    @PutMapping("/modify")
    public ResponseResult modifyArticle(@RequestBody Article article) {
        ResponseResult responseResult = new ResponseResult();
        try {
            articleService.modifyArticle(article);
            responseResult.setSuccess(true);
            responseResult.setMessage(ServiceErrorCode.SERVICE_OK.getMessage());
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.MODIFY_RESOURCE_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @ApiOperation(value = "删除文章", notes = "根据authorId删除指定文章")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "number")
    @DeleteMapping("/{articleId}")
    public ResponseResult removeArticleById(@PathVariable BigInteger articleId) {
        ResponseResult responseResult = new ResponseResult();
        try {
            articleService.removeArticleById(articleId);
            responseResult.setSuccess(true);
            responseResult.setMessage(ServiceErrorCode.SERVICE_OK.getMessage());
        } catch (Exception e) {
            responseResult.setErrorCode(ServiceErrorCode.REMOVE_RESOURCE_ERROR.getCode());
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }
}
