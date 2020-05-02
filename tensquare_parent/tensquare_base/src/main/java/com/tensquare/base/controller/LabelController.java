package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签控制层
 * @Author zhang
 * @Date 2020/5/1 20:02
 * @Version 1.0
 */
@RestController
@RequestMapping("label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 标签查询
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll(){
        return new Result(true, StatusCode.OK,"查询成功",
                labelService.findAll());
    }

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result<Label> findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",
                labelService.findById(id));
    }

    /**
     * 新增标签
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    /**
     * 修改标签
     * @param label
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label){
        labelService.update(label);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 条件查询
     * @param label
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findSearch(label));
    }

    /**
     * 分页条件查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/label/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        return new Result(true, StatusCode.OK, "查询成功",  new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
