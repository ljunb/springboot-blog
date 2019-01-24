package com.example.iblog.controller;

import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
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
    public Map<String, Object> getArticleList() {
        List<Article> articleList = articleService.getAll();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", articleList != null ? "获取文章列表成功" : "获取文章列表失败");
        resultMap.put("status", articleList != null ? 1 : 0);
        resultMap.put("result", articleList);
        return resultMap;
    }

    @ApiOperation(value = "获取文章信息", notes = "根据articleId获取文章信息")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "number")
    @GetMapping("/{articleId}")
    public Map<String, Object> getArticle(@PathVariable BigInteger articleId) {
        Article article = articleService.getArticle(articleId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", article != null ? "获取文章成功" : "没有此文章相关信息");
        resultMap.put("status", article != null ? 1 : 0);
        resultMap.put("result", article);
        return resultMap;
    }

    @ApiOperation(value = "添加文章", notes = "根据Article对象创建文章")
    @ApiImplicitParam(name = "article", value = "文章对象实体", required = true, dataType = "Article")
    @PostMapping("/create")
    public Map<String, Object> newArticle(@Valid @RequestBody Article article) {
        int code = articleService.insertArticle(article);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "新建文章成功" : "新建文章失败");
        resultMap.put("status", code);
        return resultMap;
    }

    @ApiOperation(value = "更新文章信息", notes = "根据Article实体更新文章信息")
    @ApiImplicitParam(name = "article", value = "文章对象实体", required = true, dataType = "Article")
    @PutMapping("/modify")
    public Map<String, Object> updateArticle(@RequestBody Article article) {
        int code = articleService.updateArticle(article);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "更新文章成功" : "更新文章失败");
        resultMap.put("status", code);
        return resultMap;
    }

    @ApiOperation(value = "删除文章", notes = "根据authorId删除指定文章")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "number")
    @DeleteMapping("/{articleId}")
    public Map<String, Object> deleteArticle(@PathVariable BigInteger articleId) {
        int code = articleService.deleteArticleById(articleId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "删除文章成功" : "删除文章失败");
        resultMap.put("status", code);
        return resultMap;
    }
}
