package com.example.iblog.services.impl;

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

            List<Comment> commentList = commentDao.findCommentList(article.getArticleId());
            for (Comment comment: commentList) {
                // 评论人
                Author commenter = authorDao.findAuthor(comment.getCommenterId());
                if (commenter != null) {
                    comment.setCommenter(commenter.getName());
                }

                // 被回复的评论
                Comment beReplyComment = commentDao.findComment(comment.getBeReplyCommentId());
                // 被回复的评论人
                if (beReplyComment != null) {
                    Author beReplyCommenter = authorDao.findAuthor(beReplyComment.getCommenterId());
                    if (beReplyCommenter != null) {
                        comment.setBeReplyCommenter(beReplyCommenter.getName());
                    }
                }
            }
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
        List<Comment> commentList = commentDao.findCommentList(articleId);
        Article article = articleDao.findArticle(articleId);
        Author author = authorDao.findAuthor(article.getAuthorId());

        for (Comment comment: commentList) {
            // 评论人
            Author commenter = authorDao.findAuthor(comment.getCommenterId());
            if (commenter != null) {
                comment.setCommenter(commenter.getName());
            }

            // 被回复的评论
            Comment beReplyComment = commentDao.findComment(comment.getBeReplyCommentId());
            // 被回复的评论人
            if (beReplyComment != null) {
                Author beReplyCommenter = authorDao.findAuthor(beReplyComment.getCommenterId());
                if (beReplyCommenter != null) {
                    comment.setBeReplyCommenter(beReplyCommenter.getName());
                }
            }
        }
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
