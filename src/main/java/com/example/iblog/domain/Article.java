package com.example.iblog.domain;

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
public class Article {
    @Null
    private BigInteger articleId;
    @NotNull
    private String title;
    private String description;
    @Past
    private Date publishTime;
    private Date lastModifyTime;
    @NotNull
    private BigInteger authorId;
    private String authorName;
    private String content;
    private String source;
    private List<Comment> commentList;
}
