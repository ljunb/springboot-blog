package com.example.iblog.dao;

import com.example.iblog.domain.Article;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

public interface ArticleDao {

    @Select("select * from article")
    public List<Article> findArticleList();

    @Insert({
            "insert into article (title, author_id, content, description, publish_time, last_modify_time, source)",
            "values (#{title}, #{authorId}, #{content}, #{description}, #{publishTime}, #{lastModifyTime}, #{source})"
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
            "<if test='description != null'>",
            "description = #{description},",
            "</if>",
            "<if test='content != null'>",
            "content = #{content},",
            "</if>",
            "<if test='source != null'>",
            "source = #{source},",
            "</if>",
            "last_modify_time = #{lastModifyTime},",
            "author_id = #{authorId}",
            "</set>",
            "where article_id = #{articleId}",
            "</script>"
    })
    public int updateArticle(Article article);

    @Select("select * from article where article_id=#{articleId}")
    public Article findArticle(BigInteger articleId);

    @Delete("delete from article where article_id=#{articleId}")
    public int deleteArticle(BigInteger articleId);
}
