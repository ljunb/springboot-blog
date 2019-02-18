package com.example.iblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"handler"})
public class Comment {
    /**
     * 评论id
     */
    @Null
    private BigInteger commentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date publishTime;
    /**
     * 被点赞数
     */
    private BigInteger likeCount;
    /**
     * 评论所属文章id
     */
    @NotNull
    private BigInteger articleId;
    /**
     * 评论人id
     */
    @NotNull
    private BigInteger commenterId;
    /**
     * 评论人姓名
     */
    private String commenter;
    /**
     * 被回复的评论id
     */
    private BigInteger beReplyCommentId;
    /**
     * 被回复的评论人姓名
     */
    private String beReplyCommenter;
}
