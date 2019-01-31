package com.example.iblog.services.impl;

import com.example.iblog.dao.ArticleDao;
import com.example.iblog.dao.CommentDao;
import com.example.iblog.domain.Article;
import com.example.iblog.domain.Comment;
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
    private CommentDao commentDao;

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
        List<Comment> commentList = commentDao.findCommentList(articleId);
        Article article = articleDao.getArticle(articleId);
        article.setCommentList(commentList);
        return article;
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
