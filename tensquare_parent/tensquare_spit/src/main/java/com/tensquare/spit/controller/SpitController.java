package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 吐槽类controller层
 * @Author zhang
 * @Date 2020/5/5 14:02
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 增加吐槽
     * @param spit
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addSpit(@RequestBody Spit spit){
        spitService.addSpit(spit);
        return new Result(true, StatusCode.OK,"吐槽成功！");
    }

    /**
     * 查询全部吐槽
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result spitList(){
        List<Spit> spitList = spitService.spitList();
        return new Result(true,StatusCode.OK,"查询成功！",spitList);
    }

    /**
     * 根据id查询吐槽
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findByid(@PathVariable String id){
        Spit spit = spitService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功！",spit);
    }

    /**
     * 修改吐槽
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result updateSpit(@PathVariable String id, @RequestBody Spit spit){
        spit.set_id(id);
        spitService.updateSpit(spit);
        return new Result(true, StatusCode.OK,"修改成功！");
    }

    /**
     * 删除吐槽
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deletSpit(@PathVariable String id){
        spitService.deleteSpit(id);
        return new Result(true,StatusCode.OK,"删除成功！");
    }
    /**
     * 根据parentId查询吐槽
     * @param id
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/comment/{id}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String id,@PathVariable int page, @PathVariable int size){
        Page<Spit> pageSpit = spitService.findByParentId(id, page, size);
        PageResult pageResult = new PageResult(pageSpit.getTotalElements(),pageSpit.getContent());
        return new Result(true,StatusCode.OK,"查询成功！",pageResult);
    }

    /**
     * 点赞吐槽
     * @param id
     * @return
     */
    @RequestMapping(value = "/thumbup/{id}",method = RequestMethod.PUT)
    public Result thumbupSpit(@PathVariable String id){
        String userid = "1023";
        if(redisTemplate.opsForValue().get("thumbup_"+userid+"_"+id) != null){
            return new Result(false,StatusCode.ERROR,"您已经点过赞了！");
        }
        spitService.thumbupSpit(id);
        redisTemplate.opsForValue().set("thumbup_"+userid+"_"+id,"1");
        return new Result(true,StatusCode.OK,"点赞成功！");
    }

}
