package com.example.iblog.controller;

import com.example.iblog.dto.Article;
import com.example.iblog.services.impl.ArticleServiceImpl;
import jdk.management.resource.ResourceRequestDeniedException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    ArticleServiceImpl tvServicesService;

    @GetMapping()
    public List<Article> getAll() {
        return tvServicesService.getAll();
    }

    @GetMapping("/{id}")
    public Article getOne(@PathVariable int id) {
        return null;
    }

    @PostMapping
    public Article insetOne(@Valid @RequestBody Article article) {
        return article;
    }

    @PutMapping("/{id}")
    public Article updateOne(@PathVariable int id, Article article) {
        return article;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteOne(@PathVariable int id, HttpServletRequest request,
                                         @RequestParam(value = "delete_reason", required = false) String deleteReason) throws Exception {
        Map<String, String> result = new HashMap<>();
        if (id == 1) {
            result.put("message", request.getRemoteAddr());
        } else if (id == 2) {
            throw new RuntimeException("2 not allowed delete");
        } else {
            throw new ResourceRequestDeniedException();
        }
        return result;
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
