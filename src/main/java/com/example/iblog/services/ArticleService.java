package com.example.iblog.services;

import com.example.iblog.bean.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> getAll();
    public Article getArticle(int articleId);
    public int insertArticle(Article article);
    public int updateArticle(Article article);
    public int deleteArticleById(int articleId);
}
