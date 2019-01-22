package com.example.iblog.controller;

import com.example.iblog.bean.Article;
import com.example.iblog.services.impl.ArticleServiceImpl;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileOutputStream;
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
    public Article getArticle(@PathVariable int articleId) {
        return articleService.getArticle(articleId);
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
    public Map<String, Object> deleteArticle(@PathVariable int articleId) {
        int code = articleService.deleteArticleById(articleId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", code == 1 ? "删除文章成功" : "删除文章失败");
        resultMap.put("status", code);
        return resultMap;
    }

    @PostMapping(value = "/{id}/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto(@PathVariable int id, @RequestParam("photo") MultipartFile imgFile) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("target/");
        stringBuilder.append(imgFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(stringBuilder.toString());
        IOUtils.copy(imgFile.getInputStream(), fileOutputStream);
        fileOutputStream.close();
    }
}
