package com.example.iblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Null
    private BigInteger commentId;
    private String content;
    private Date publishTime;
    private BigInteger likeCount;
    @NotNull
    private BigInteger articleId;
}
