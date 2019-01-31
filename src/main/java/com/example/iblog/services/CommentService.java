package com.example.iblog.services;

import com.example.iblog.domain.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentService {
    public List<Comment> getCommentList(BigInteger articleId);
    public int createComment(Comment comment);
}
