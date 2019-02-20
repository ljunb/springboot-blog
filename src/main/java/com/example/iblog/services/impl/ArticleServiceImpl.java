package com.example.iblog.services.impl;

import com.example.iblog.dao.ArticleDao;
import com.example.iblog.dao.AuthorDao;
import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import com.example.iblog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private AuthorDao authorDao;

    @Override
    public List<Article> getArticleList() {
        return articleDao.findArticleList();
    }

    @Override
    public int createArticle(Article article) {
        Author author = authorDao.findAuthor(article.getAuthorId());
        article.setAuthor(author.getName());
        article.setPublishTime(new Date());
        article.setLastModifyTime(new Date());
        return articleDao.insertArticle(article);
    }

    @Override
    public Article getArticle(BigInteger articleId) {
        return articleDao.findArticle(articleId);
    }

    @Override
    public int modifyArticle(Article article) {
        article.setLastModifyTime(new Date());
        return articleDao.updateArticle(article);
    }

    @Override
    public int removeArticleById(BigInteger articleId) {
        return articleDao.deleteArticle(articleId);
    }
}
