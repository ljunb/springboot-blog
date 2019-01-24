package com.example.iblog.controller;

import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import com.example.iblog.services.impl.AuthorServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorServiceImpl authorService;

    @ApiOperation("获取作者列表")
    @GetMapping
    public List<Author> getAllAuthors() { return authorService.getAll(); }

    @ApiOperation(value = "获取作者信息", notes = "根据authorId获取用户信息")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @GetMapping("/{authorId}")
    public Map<String, Object> getArticle(@PathVariable BigInteger authorId) {
        Author author = authorService.getAuthor(authorId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", author != null ? "获取作者信息成功" : "没有此作者相关信息");
        resultMap.put("status", author != null ? 1 : 0);
        resultMap.put("result", author);
        return resultMap;
    }

    @ApiOperation(value = "添加作者", notes = "根据Author对象创建作者")
    @ApiImplicitParam(name = "author", value = "作者对象实体", required = true, dataType = "Author")
    @PostMapping("/create")
    public Map<String, Object> createAuthor(@Valid @RequestBody Author author) {
        int code = authorService.createAuthor(author);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "添加作者成功" : "添加作者失败");
        resultMap.put("status", code);
        return resultMap;
    }

    @ApiOperation(value = "获取文章列表", notes = "根据authorId获取该作者下的所有文章")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @GetMapping("/{authorId}/articlelist")
    public Map<String, Object> getArticleListByAuthorId(@PathVariable BigInteger authorId) {
        List<Article> articleList = authorService.getArticleList(authorId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", articleList != null ? "获取文章列表成功" : "获取文章列表失败");
        resultMap.put("status", articleList != null ? 1 : 0);
        resultMap.put("result", articleList);
        return resultMap;
    }

    @ApiOperation(value = "更新作者信息", notes = "根据Author实体更新作者信息")
    @ApiImplicitParam(name = "author", value = "作者对象实体", required = true, dataType = "Author")
    @PutMapping("/modify")
    public Map<String, Object> updateArticle(@RequestBody Author author) {
        int code = authorService.updateAuthor(author);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "更新作者信息成功" : "更新作者信息失败");
        resultMap.put("status", code);
        return resultMap;
    }

    @ApiOperation(value = "删除作者", notes = "根据authorId删除指定作者")
    @ApiImplicitParam(name = "authorId", value = "作者id", required = true, dataType = "number")
    @DeleteMapping("/{authorId}")
    public Map<String, Object> deleteArticle(@PathVariable BigInteger authorId) {
        int code = authorService.deleteAuthorById(authorId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "删除作者成功" : "删除作者失败");
        resultMap.put("status", code);
        return resultMap;
    }
}
