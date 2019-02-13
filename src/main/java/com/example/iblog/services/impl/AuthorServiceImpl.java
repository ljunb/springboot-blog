package com.example.iblog.services.impl;

import com.example.iblog.dao.ArticleDao;
import com.example.iblog.dao.AuthorDao;
import com.example.iblog.dao.CommentDao;
import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import com.example.iblog.domain.Comment;
import com.example.iblog.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Author> getAuthorList() {
        return authorDao.findAuthorList();
    }

    @Override
    public Author getAuthor(BigInteger authorId) {
        return authorDao.findAuthor(authorId);
    }

    @Override
    public int createAuthor(Author author) {
        return authorDao.insertAuthor(author);
    }

    @Override
    public int modifyAuthor(Author author) {
        return authorDao.updateAuthor(author);
    }

    @Override
    public int removeAuthorById(BigInteger authorId) {
        return authorDao.deleteAuthorById(authorId);
    }

    @Override
    public List<Article> getArticleList(BigInteger authorId) {
        List<Article> articleList = authorDao.findArticleList(authorId);
        Author author = authorDao.findAuthor(authorId);

        for (Article article: articleList) {
            List<Comment> commentList = commentDao.findCommentList(article.getArticleId());
            article.setAuthorName(author.getName());

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
    public List<Comment> getCommentList(BigInteger authorId) {
        return commentDao.findAuthorCommentList(authorId);
    }
}
