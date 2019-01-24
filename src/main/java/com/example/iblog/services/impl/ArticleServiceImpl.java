package com.example.iblog.services.impl;

import com.example.iblog.dao.ArticleDao;
import com.example.iblog.domain.Article;
import com.example.iblog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public List<Article> getAll() {
        return articleDao.getAll();
    }

    @Override
    public int insertArticle(Article article) {
        article.setPublishTime(new Date());
        article.setLastModifyTime(new Date());
        return articleDao.insertArticle(article);
    }

    @Override
    public Article getArticle(BigInteger articleId) {
        return articleDao.getArticle(articleId);
    }

    @Override
    public int updateArticle(Article article) {
        article.setLastModifyTime(new Date());
        return articleDao.updateArticle(article);
    }

    @Override
    public int deleteArticleById(BigInteger articleId) {
        return articleDao.deleteArticle(articleId);
    }
}
