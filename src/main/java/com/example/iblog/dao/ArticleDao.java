package com.example.iblog.dao;

import com.example.iblog.dto.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleDao {

    @Select("select * from article")
    public List<Article> getAll();
}
