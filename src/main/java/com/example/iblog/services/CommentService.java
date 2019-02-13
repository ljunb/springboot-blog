package com.example.iblog.services;

import com.example.iblog.domain.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentService {
    /**
     * 获取某篇文章评论列表
     * @param articleId 文章id
     * @return 评论列表
     */
    public List<Comment> getCommentList(BigInteger articleId);

    /**
     * 新建评论
     * @param comment 评论对象实体
     * @return 是否评论成功
     */
    public int createComment(Comment comment);

    /**
     * 回复评论
     * @param comment 回复评论对象实体
     * @return 是否回复成功
     */
    public int createReply(Comment comment);
}
