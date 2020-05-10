package com.tensquare.article.controller;

import com.tensquare.article.service.CommentService;
import com.tensquare.article.pojo.Comment;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhang
 * @Date 2020/5/6 9:50
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addComment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return new Result(true, StatusCode.OK,"评论成功！");
    }

    /**
     * 根据文章ID查询评论列表
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public Result findCommentByArticleid(@PathVariable String articleId){
        List<Comment> list = commentService.findCommentByArticleid(articleId);
        return new Result(true,StatusCode.OK,"查询成功！",list);
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteComment/{id}",method = RequestMethod.DELETE)
    public Result deleteComment(@PathVariable String id){
        commentService.deleteComment(id);
        return new Result(true,StatusCode.OK,"删除成功！");
    }
}
