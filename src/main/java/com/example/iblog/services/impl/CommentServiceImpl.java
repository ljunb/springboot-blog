package com.example.iblog.services.impl;

import com.example.iblog.dao.CommentDao;
import com.example.iblog.domain.Comment;
import com.example.iblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> getCommentList(BigInteger articleId) {
        return commentDao.findCommentList(articleId);
    }

    @Override
    public int createComment(Comment comment) {
        comment.setPublishTime(new Date());
        return commentDao.insertComment(comment);
    }

    @Override
    public int createReply(Comment comment) {
        comment.setPublishTime(new Date());
        return commentDao.updateCommentWithReply(comment);
    }
}
