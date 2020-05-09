package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 评论Dao
 * @Author zhang
 * @Date 2020/5/6 9:28
 * @Version 1.0
 */
public interface CommentDao extends MongoRepository<Comment, String> {

    /**
     * 根据文章ID查询
     * @param articleId
     * @return
     */
    public List<Comment> findCommentByArticleid(String articleId);

    /**
     * 删除评论
     * @param id
     */
    public void deleteById(String id);
}
