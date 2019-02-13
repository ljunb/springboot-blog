package com.example.iblog.dao;

import com.example.iblog.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

public interface CommentDao {

    @Select("select * from comment where article_id = #{articleId}")
    public List<Comment> findCommentList(BigInteger articleId);

    @Select("select * from comment where comment_id = #{commentId}")
    public Comment findComment(BigInteger commentId);

    @Insert({
            "insert into comment",
            "(content, article_id, commenter_id, publish_time, like_count)",
            "values",
            "(#{content}, #{articleId}, #{commenterId}, #{publishTime}, #{likeCount})"
    })
    public int insertComment(Comment comment);

    @Insert({
            "insert into comment",
            "(content, article_id, publish_time, commenter_id, be_reply_comment_id)",
            "values",
            "(#{content}, #{articleId}, #{publishTime}, #{commenterId}, #{beReplyCommentId})"
    })
    public int updateCommentWithReply(Comment comment);

    @Select("select * from comment where commenter_id = #{authorId}")
    public List<Comment> findAuthorCommentList(BigInteger authorId);
}
