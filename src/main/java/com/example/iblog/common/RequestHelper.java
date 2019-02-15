package com.example.iblog.common;

import com.example.iblog.dao.AuthorDao;
import com.example.iblog.dao.CommentDao;
import com.example.iblog.domain.Article;
import com.example.iblog.domain.Author;
import com.example.iblog.domain.Comment;

import java.util.List;

public class RequestHelper {
    /**
     * 获取文章下所有评论
     * @param commentDao 评论DAO
     * @param authorDao 作者DAO
     * @param article 文章
     * @return 评论列表
     */
    public static List<Comment> getArticleCommentList(CommentDao commentDao, AuthorDao authorDao, Article article) {
        List<Comment> commentList = commentDao.findCommentList(article.getArticleId());
        for (Comment comment: commentList) {
            // 评论人
            Author commenter = authorDao.findAuthor(comment.getCommenterId());
            if (commenter != null) {
                comment.setCommenter(commenter.getName());
            }

            // 被回复的评论
            Comment beReplyComment = commentDao.findComment(comment.getBeReplyCommentId());
            // 被回复的评论人
            if (beReplyComment != null) {
                Author beReplyCommenter = authorDao.findAuthor(beReplyComment.getCommenterId());
                if (beReplyCommenter != null) {
                    comment.setBeReplyCommenter(beReplyCommenter.getName());
                }
            }
        }
        return commentList;
    }
}
