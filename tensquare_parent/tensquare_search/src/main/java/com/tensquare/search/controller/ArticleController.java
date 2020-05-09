package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhang
 * @Date 2020/5/7 20:48
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章到索引库
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK,"添加成功！");
    }

    /**
     * 搜索文章
     * @param key
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/{key}/{page}/{size}", method = RequestMethod.GET)
    public Result findByTitleOrContent(@PathVariable String key,@PathVariable int page, @PathVariable int size){
        Page<Article> pageData = articleService.findByTitleOrContent(key,page,size);
        return new Result(true,StatusCode.OK,"查询成功！",new PageResult<Article>(pageData.getTotalElements(),pageData.getContent()));
    }
}
