package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpitDaoInterface extends MongoRepository<Spit,String> {

    /**
     * 根据parentid查询吐槽列表
     * @param parentId
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentid(String parentId, Pageable pageable);

}
