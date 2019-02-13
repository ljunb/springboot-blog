package com.example.iblog.services;

import com.example.iblog.domain.Article;

import java.math.BigInteger;
import java.util.List;

public interface ArticleService {
    /**
     * 获取所有文章列表
     * @return 文章列表
     */
    public List<Article> getArticleList();

    /**
     * 获取具体文章信息
     * @param articleId 文章id
     * @return 具体文章
     */
    public Article getArticle(BigInteger articleId);

    /**
     * 新建文章
     * @param article 文章信息
     * @return 是否成功标识位（1/0：成功/失败）
     */
    public int createArticle(Article article);

    /**
     * 编辑文章
     * @param article 文章信息
     * @return 是否成功标识位（1/0：成功/失败）
     */
    public int modifyArticle(Article article);

    /**
     * 删除文章
     * @param articleId 文章id
     * @return 是否成功标识位（1/0：成功/失败）
     */
    public int removeArticleById(BigInteger articleId);
}
