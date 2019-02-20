package com.example.iblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public class Article {
    /**
     * 文章id
     */
    @Null
    private BigInteger articleId;
    /**
     * 文章标题
     */
    @NotNull
    private String title;
    /**
     * 文章简介
     */
    private String description;
    /**
     * 文章发布时间
     */
    @Past
    private Date publishTime;
    /**
     * 最后更新时间
     */
    private Date lastModifyTime;
    /**
     * 文章作者id
     */
    @NotNull
    private BigInteger authorId;
    /**
     * 作者姓名
     */
    private String author;
    /**
     * 文章详细内容
     */
    private String content;
    /**
     * 文章来源
     */
    private String source;
    /**
     * 评论列表
     */
    private List<Comment> commentList;
}
