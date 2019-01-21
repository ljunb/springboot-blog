package com.example.iblog.services.impl;

import com.example.iblog.dao.ArticleDao;
import com.example.iblog.dto.Article;
import com.example.iblog.services.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleDao articleDao;

    @Override
    public List<Article> getAll() {
        return articleDao.getAll();
    }
}
