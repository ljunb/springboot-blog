package com.example.iblog.dao;

import com.example.iblog.bean.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleDao {

    @Select("select * from article")
    public List<Article> getAll();

    @Insert("insert into article (title, author, description, publish_time) values (#{title}, #{author}, #{description}, #{publishTime})")
    @Options(useGeneratedKeys = true, keyProperty = "article_id")
    public int insertArticle(Article article);

    @Update("update article set title=#{title}, author=#{author}, description=#{description}, publish_time=#{publishTime} where article_id=#{articleId}")
    public int updateArticle(Article article);

    @Select("select * from article where article_id=#{articleId}")
    public Article getArticle(int articleId);

    @Delete("delete from article where article_id=#{articleId}")
    public int deleteArticle(int articleId);
}
