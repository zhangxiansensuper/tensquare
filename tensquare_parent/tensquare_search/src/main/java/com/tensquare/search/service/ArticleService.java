package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.IdWorker;

/**
 * @Author zhang
 * @Date 2020/5/7 20:47
 * @Version 1.0
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加文章到索引库
     * @param article
     */
    public void save(Article article){
        articleDao.save(article);
    }

    /**
     * 搜索文章
     * @param key
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByTitleOrContent(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return  articleDao.findByTitleOrContentLike(key, key, pageable);
    }
}
