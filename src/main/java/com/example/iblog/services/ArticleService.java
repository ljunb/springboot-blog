package com.example.iblog.services;

import com.example.iblog.domain.Article;

import java.math.BigInteger;
import java.util.List;

public interface ArticleService {
    public List<Article> getArticleList();
    public Article getArticle(BigInteger articleId);
    public int createArticle(Article article);
    public int modifyArticle(Article article);
    public int removeArticleById(BigInteger articleId);
}
