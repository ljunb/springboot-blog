package com.example.iblog.services;

import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;

import java.math.BigInteger;
import java.util.List;

public interface AuthorService {
    public List<Author> getAuthorList();
    public Author getAuthor(BigInteger authorId);
    public int createAuthor(Author author);
    public int modifyAuthor(Author author);
    public int removeAuthorById(BigInteger authorId);

    public List<Article> getArticleList(BigInteger authorId);
}
