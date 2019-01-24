package com.example.iblog.controller;

import com.example.iblog.domain.Article;
import com.example.iblog.services.impl.ArticleServiceImpl;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServiceImpl articleService;

    @GetMapping()
    public List<Article> getArticleList() {
        return articleService.getAll();
    }

    @GetMapping("/{articleId}")
    public Map<String, Object> getArticle(@PathVariable BigInteger articleId) {
        Article article = articleService.getArticle(articleId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", article != null ? "获取文章成功" : "没有此文章相关信息");
        resultMap.put("status", article != null ? 1 : 0);
        resultMap.put("result", article);
        return resultMap;
    }

    @PostMapping("/create")
    public Map<String, Object> newArticle(@Valid @RequestBody Article article) {
        int code = articleService.insertArticle(article);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "新建文章成功" : "新建文章失败");
        resultMap.put("status", code);
        return resultMap;
    }

    @PutMapping("/modify")
    public Map<String, Object> updateArticle(@RequestBody Article article) {
        int code = articleService.updateArticle(article);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "更新文章成功" : "更新文章失败");
        resultMap.put("status", code);
        return resultMap;
    }

    @DeleteMapping("/{articleId}")
    public Map<String, Object> deleteArticle(@PathVariable BigInteger articleId) {
        int code = articleService.deleteArticleById(articleId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "删除文章成功" : "删除文章失败");
        resultMap.put("status", code);
        return resultMap;
    }
}
