package com.ujiuye.mis.controller;

import com.ujiuye.mis.pojo.Dept;
import com.ujiuye.mis.service.DeptService;
import com.ujiuye.mis.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiLe
 * @create 2020-08-28 15:49
 */

@RestController
@RequestMapping("dept")
public class DeptController {


    @Autowired
    private DeptService deptService;

    @GetMapping("findAll")
    public R findAll(){
       List<Dept> list= deptService.findAll();
       if (CollectionUtils.isEmpty(list)){
           return R.error().message("部门为空");
       }
       return  R.ok().data("item",list);


    }

}

