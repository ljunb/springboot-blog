package com.example.iblog.services;

import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import com.example.iblog.domain.Comment;

import java.math.BigInteger;
import java.util.List;

public interface AuthorService {
    /**
     * 获取所有作者列表
     * @return 作者实体列表
     */
    public List<Author> getAuthorList();

    /**
     * 获取某个作者信息
     * @param authorId 作者id
     * @return 作者对象实体
     */
    public Author getAuthor(BigInteger authorId);

    /**
     * 添加作者
     * @param author 作者实体对象
     * @return 是否成功标识位（1/0：成功/失败）
     */
    public int createAuthor(Author author);

    /**
     * 更新作者信息
     * @param author 作者实体对象
     * @return 是否成功标识位（1/0：成功/失败）
     */
    public int modifyAuthor(Author author);

    /**
     * 删除作者信息
     * @param authorId 作者id
     * @return 是否成功标识位（1/0：成功/失败）
     */
    public int removeAuthorById(BigInteger authorId);

    /**
     * 获取某作者所有文章列表
     * @param authorId 作者id
     * @return 文章实体列表
     */
    public List<Article> getArticleList(BigInteger authorId);

    /**
     * 或者某作者所有评论列表
     * @param authorId 作者id
     * @return 评论实体列表
     */
    public List<Comment> getCommentList(BigInteger authorId);
}
