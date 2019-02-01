package com.example.iblog.services.impl;

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
            article.setCommentList(commentList);
        }

        return articleList;
    }
}
