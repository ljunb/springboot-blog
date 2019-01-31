package com.example.iblog.dao;

import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

public interface AuthorDao {

    @Select("select * from author")
    public List<Author> getAll();

    @Select("select * from author where author_id = #{authorId}")
    public Author getAuthor(BigInteger authorId);

    @Insert({
            "insert into author",
            "(name, sex, birthday, address, avatar)",
            "values",
            "(#{name}, #{sex}, #{birthday}, #{address}, #{avatar})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "author_id")
    public int createAuthor(Author author);

    @Update({
            "<script>",
            "update author",
            "<set>",
            "<if test='name != null'>",
            "name = #{name},",
            "</if>",
            "<if test='address != null'>",
            "address = #{address},",
            "</if>",
            "<if test='sex != null'>",
            "sex = #{sex},",
            "</if>",
            "<if test='birthday != null'>",
            "birthday = #{birthday}",
            "</if>",
            "<if test='avatar != null'>",
            "avatar = #{avatar}",
            "</if>",
            "</set>",
            "where author_id = #{authorId}",
            "</script>"
    })
    public int updateAuthor(Author author);

    @Delete("delete from author where author_id = #{authorId}")
    public int deleteAuthorById(BigInteger authorId);

    @Select("select * from article where author_id = #{authorId}")
    public List<Article> getArticleList(BigInteger authorId);
}
