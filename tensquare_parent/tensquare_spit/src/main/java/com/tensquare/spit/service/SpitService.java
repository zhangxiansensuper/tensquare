package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDaoInterface;
import com.tensquare.spit.pojo.Spit;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 吐槽类service层
 * @Author zhang
 * @Date 2020/5/5 11:19
 * @Version 1.0
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDaoInterface spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增吐槽
     * @param spit
     */
    public void addSpit(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
            // 如果存在上级ID,评论
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 查询全部吐槽
     * @return
     */
    public List<Spit>  spitList(){
        List<Spit> spitList = spitDao.findAll();
        return spitList;
    }

    /**
     * 根据id查询吐槽
     * @param id
     * @return
     */
    public Spit findById(String id){
       return spitDao.findById(id).get();
    }

    /**
     * 修改吐槽
     * @param spit
     */
    public void updateSpit(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 删除吐槽
     * @param id
     */
    public void deleteSpit(String id){
        spitDao.deleteById(id);
    }

    /**
     * 根据parentId查询吐槽列表
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    public Page<Spit>  findByParentId(String parentId, int page, int size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentId,pageRequest);
    }

    /**
     * 吐槽点赞
     * @param id
     */
    public void thumbupSpit(String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update=new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }

}
