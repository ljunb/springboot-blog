package com.example.iblog.controller;

import com.example.iblog.common.ResponseResult;
import com.example.iblog.common.ResponseCodeEnum;
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
        try {
            List<Article> articleList = articleService.getArticleList();
            return new ResponseResult<>(articleList, ResponseCodeEnum.SERVICE_OK);
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取文章信息", notes = "根据articleId获取文章信息")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "number")
    @GetMapping("/{articleId}")
    public ResponseResult<Article> getArticle(@PathVariable BigInteger articleId) {
        try {
            Article article = articleService.getArticle(articleId);
            if (article != null) {
                return new ResponseResult<>(article, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.RESOURCE_NOT_FOUNDED_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "添加文章", notes = "根据Article对象创建文章")
    @ApiImplicitParam(name = "article", value = "文章对象实体", required = true, dataType = "Article")
    @PostMapping("/create")
    public ResponseResult<Article> createArticle(@Valid @RequestBody Article article) {
        try {
            int resultCode = articleService.createArticle(article);
            if (resultCode > 0) {
                return new ResponseResult<>(article, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.INSERT_RESOURCE_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "更新文章信息", notes = "根据Article实体更新文章信息")
    @ApiImplicitParam(name = "article", value = "文章对象实体", required = true, dataType = "Article")
    @PutMapping("/modify")
    public ResponseResult<Article> modifyArticle(@RequestBody Article article) {
        try {
            int resultCode = articleService.modifyArticle(article);
            if (resultCode > 0) {
                return new ResponseResult<>(article, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.MODIFY_RESOURCE_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "删除文章", notes = "根据authorId删除指定文章")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "number")
    @DeleteMapping("/{articleId}")
    public ResponseResult<BigInteger> removeArticleById(@PathVariable BigInteger articleId) {
        try {
            int resultCode = articleService.removeArticleById(articleId);
            if (resultCode > 0) {
                return new ResponseResult<>(articleId, ResponseCodeEnum.SERVICE_OK);
            } else {
                return new ResponseResult<>(ResponseCodeEnum.REMOVE_RESOURCE_ERROR);
            }
        } catch (Exception e) {
            return new ResponseResult<>(ResponseCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
        }
    }
}
