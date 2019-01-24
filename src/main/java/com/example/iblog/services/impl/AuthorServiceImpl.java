package com.example.iblog.services.impl;

import com.example.iblog.dao.AuthorDao;
import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import com.example.iblog.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDao authorDao;

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author getAuthor(BigInteger authorId) {
        return authorDao.getAuthor(authorId);
    }

    @Override
    public int createAuthor(Author author) {
        return authorDao.createAuthor(author);
    }

    @Override
    public int updateAuthor(Author author) {
        return authorDao.updateAuthor(author);
    }

    @Override
    public int deleteAuthorById(BigInteger authorId) {
        return authorDao.deleteAuthorById(authorId);
    }

    @Override
    public List<Article> getArticleList(BigInteger authorId) {
        return authorDao.getArticleList(authorId);
    }
}
