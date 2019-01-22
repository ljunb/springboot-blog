package com.example.iblog.services.impl;

import com.example.iblog.dao.ArticleDao;
import com.example.iblog.domain.Article;
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

    @Override
    public int insertArticle(Article article) {
        return articleDao.insertArticle(article);
    }

    @Override
    public Article getArticle(int articleId) {
        return articleDao.getArticle(articleId);
    }

    @Override
    public int updateArticle(Article article) {
        return articleDao.updateArticle(article);
    }

    @Override
    public int deleteArticleById(int articleId) {
        return articleDao.deleteArticle(articleId);
    }
}
