package com.example.iblog.services.impl;

import com.example.iblog.dao.AuthorDao;
import com.example.iblog.dao.CommentDao;
import com.example.iblog.domain.Author;
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
    @Autowired
    private AuthorDao authorDao;

    @Override
    public List<Comment> getCommentList(BigInteger articleId) {
        return commentDao.findCommentList(articleId);
    }

    @Override
    public int createComment(Comment comment) {
        Author author = authorDao.findAuthor(comment.getCommenterId());
        comment.setPublishTime(new Date());
        comment.setCommenter(author.getName());
        // 当前是回复的评论
        if (comment.getBeReplyCommentId() != null) {
            Comment beReplyComment = commentDao.findComment(comment.getBeReplyCommentId());
            Author beReplyAuthor = authorDao.findAuthor(beReplyComment.getCommenterId());
            comment.setBeReplyCommenter(beReplyAuthor.getName());
        }
        return commentDao.insertComment(comment);
    }

    @Override
    public int createReply(Comment comment) {
        comment.setPublishTime(new Date());
        return commentDao.updateCommentWithReply(comment);
    }
}
