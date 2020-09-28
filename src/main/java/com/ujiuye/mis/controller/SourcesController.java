package com.ujiuye.mis.controller;

import com.ujiuye.mis.pojo.Sources;
import com.ujiuye.mis.service.SourcesService;
import com.ujiuye.mis.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author LiLe
 * @create 2020-08-25 20:07
 */

@RestController
@RequestMapping("source")
public class SourcesController {

    @Autowired
    private SourcesService sourcesService;

    @GetMapping("list")
    public R list(){
        Sources root = sourcesService.findRoot();
        return ObjectUtils.isEmpty(root) ? R.error(): R.ok().data("item",root);
    }

    @GetMapping("all")
    public  R findAll(){
       List<Sources> ret= sourcesService.findAll();
       return CollectionUtils.isEmpty(ret)? R.error(): R.ok().data("items",ret);

    }

    @PostMapping("save")
    public  R save(Sources sources){
        if (sources.getPid()==null){
           return R.error().message("pid不为空");
        }
        boolean ret=sourcesService.save(sources);
        return  ret? R.ok(): R.error().message("操作失败");
    }

    @GetMapping("{id}")
    public R findById(@PathVariable int id) {
        Sources sources = sourcesService.findById(id);
        return ObjectUtils.isEmpty(sources) ? R.error() : R.ok().data("item", sources);
    }

    @PostMapping("update")
    public R update(Sources sources) {
        if (sources.getId() == null) {
            return R.error().message("the parameter(id) is not found");
        }
        if (sources.getPid() == null) {
            return R.error().message("the parameter(pid) is not found");
        }
        boolean ret = sourcesService.update(sources);
        return ret ? R.ok() : R.error().message("操作失败");

    }

    @PostMapping("{id}")
    public R deleteById(@PathVariable int id) {
        boolean ret = sourcesService.deleteById(id);
        return ret ? R.ok() : R.error().message("操作失败");
    }


    @GetMapping("all2")
    public  R findAll2(){
        List<Sources> ret= sourcesService.findAll2();
        return CollectionUtils.isEmpty(ret)? R.error(): R.ok().data("items",ret);

    }











}
