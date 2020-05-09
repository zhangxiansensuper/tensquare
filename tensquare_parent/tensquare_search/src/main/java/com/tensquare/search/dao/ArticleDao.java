package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Article搜索引擎dao层
 * @Author zhang
 * @Date 2020/5/7 20:43
 * @Version 1.0
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
