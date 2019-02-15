package com.example.iblog.services.impl;

import com.example.iblog.common.RequestHelper;
import com.example.iblog.dao.ArticleDao;
import com.example.iblog.dao.AuthorDao;
import com.example.iblog.dao.CommentDao;
import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
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
    private AuthorDao authorDao;
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Article> getArticleList() {
        List<Article> articleList = articleDao.findArticleList();

        for (Article article: articleList) {
            Author author = authorDao.findAuthor(article.getAuthorId());
            article.setAuthorName(author.getName());
            List<Comment> commentList = RequestHelper.getArticleCommentList(commentDao, authorDao, article);
            article.setCommentList(commentList);
        }

        return articleList;
    }

    @Override
    public int createArticle(Article article) {
        article.setPublishTime(new Date());
        article.setLastModifyTime(new Date());
        return articleDao.insertArticle(article);
    }

    @Override
    public Article getArticle(BigInteger articleId) {
        Article article = articleDao.findArticle(articleId);
        Author author = authorDao.findAuthor(article.getAuthorId());
        List<Comment> commentList = RequestHelper.getArticleCommentList(commentDao, authorDao, article);
        article.setCommentList(commentList);
        article.setAuthorName(author.getName());
        return article;
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
