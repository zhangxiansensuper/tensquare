package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

/**
 * @Author zhang
 * @Date 2020/5/6 9:35
 * @Version 1.0
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 评论service层方法
     * @param comment
     */
    public void addComment(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }

    /**
     * 根据文章ID查询评论列表
     * @param articleId
     * @return
     */
    public List<Comment> findCommentByArticleid(String articleId){
        return commentDao.findCommentByArticleid(articleId);
    }

    /**
     * 删除评论
     * @param id
     */
    public void deleteComment(String id){
        commentDao.deleteById(id);
    }
}
