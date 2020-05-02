package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhang
 * @Date 2020/5/1 19:17
 * @Date 2020/5/1 19:17
 * @Version 1.0
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 增加标签
     * @param label
     */
    public void add(Label label){
        //设置ID
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根数据
             * @param query 封装的条件数据
             * @param cb 封装
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                    list.add(predicate);
                }
                if(label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }
                if(label.getRecommend() != null && !"".equals(label.getRecommend())) {
                    Predicate predicate = cb.equal(root.get("recommend").as(String.class),label.getRecommend());
                    list.add(predicate);
                }

                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根数据
             * @param query 封装的条件数据
             * @param cb 封装
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                    list.add(predicate);
                }
                if(label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }
                if(label.getRecommend() != null && !"".equals(label.getRecommend())) {
                    Predicate predicate = cb.equal(root.get("recommend").as(String.class),label.getRecommend());
                    list.add(predicate);
                }

                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        },pageable);
    }
}
