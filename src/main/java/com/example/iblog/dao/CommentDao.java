package com.example.iblog.dao;

import com.example.iblog.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

public interface CommentDao {

    @Select("select * from comment where article_id = #{articleId}")
    public List<Comment> findCommentList(BigInteger articleId);

    @Insert({
            "insert into comment",
            "(content, article_id, publish_time, like_count)",
            "values",
            "(#{content}, #{articleId}, #{publishTime}, #{likeCount})"
    })
    public int insertComment(Comment comment);
}
