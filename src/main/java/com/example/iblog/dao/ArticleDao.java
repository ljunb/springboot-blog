package com.example.iblog.dao;

import com.example.iblog.domain.Article;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

public interface ArticleDao {

    @Select("select * from article")
    public List<Article> getAll();

    @Insert({
            "insert into article (title, author, content, description, publish_time, last_modify_time)",
            "values (#{title}, #{author}, #{content}, #{description}, #{publishTime}, #{lastModifyTime})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "article_id")
    public int insertArticle(Article article);

    @Update({
            "<script>",
            "update article",
            "<set>",
            "<if test='title != null'>",
            "title = #{title},",
            "</if>",
            "<if test='author != null'>",
            "author = #{author},",
            "</if>",
            "<if test='description != null'>",
            "description = #{description},",
            "</if>",
            "<if test='content != null'>",
            "content = #{content},",
            "</if>",
            "last_modify_time = #{lastModifyTime}",
            "</set>",
            "where article_id = #{articleId}",
            "</script>"
    })
    public int updateArticle(Article article);

    @Select("select * from article where article_id=#{articleId}")
    public Article getArticle(BigInteger articleId);

    @Delete("delete from article where article_id=#{articleId}")
    public int deleteArticle(BigInteger articleId);
}
