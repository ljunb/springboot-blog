package com.example.iblog.services;

import com.example.iblog.domain.Article;

import java.math.BigInteger;
import java.util.List;

public interface ArticleService {
    public List<Article> getAll();
    public Article getArticle(BigInteger articleId);
    public int insertArticle(Article article);
    public int updateArticle(Article article);
    public int deleteArticleById(BigInteger articleId);
}
