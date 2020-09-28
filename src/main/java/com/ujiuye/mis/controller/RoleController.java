package com.ujiuye.mis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.ujiuye.mis.pojo.Role;
import com.ujiuye.mis.service.RoleService;
import com.ujiuye.mis.vo.R;
import org.hamcrest.core.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.keyinfo.PGPData;
import java.util.List;

/**
 * @author LiLe
 * @create 2020-08-26 11:13
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("{pageNum}/{pageSize}")
    private R findAll(@PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleService.findAll();
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return CollectionUtils.isEmpty(list) ? R.error() : R.ok().data("item", pageInfo);
    }

    @PostMapping("save")
    public R save(Role role, String ids) {
        if (StringUtils.isEmpty(ids)) {
            return R.error().message("权限不为空");
        }
        boolean ret = roleService.save(role, ids);
        return ret ? R.ok() : R.error().message("保存失败");
    }

    @GetMapping("{id}")
    public R findById(@PathVariable int id){
        Role role = roleService.findById(id);
        return ObjectUtils.isEmpty(role)?R.error().message("查询失败"):R.ok().data("item",role);
    }


    @PostMapping("update")
    public R update (Role role, String ids){
        if (StringUtils.isEmpty(ids)) {
            return R.error().message("角色权限不为空");
        }
       Boolean ret= roleService.update(role,ids);
        return ret?R.ok():R.error().message("修改失败");
    }

    @PostMapping("{ids}")
    public R delBatch(@PathVariable String ids){
        boolean ret = roleService.delBatch(ids);
        return ret? R.ok(): R.error().message("删除失败,请为指定该角色的用户指定其他角色后再试!!!");
    }


    @GetMapping("findAll")
    public  R findAll(){
       List<Role> list= roleService.findAll2();
       if (CollectionUtils.isEmpty(list)){
           return R.error();
       }
       return  R.ok().data("item",list);

    }


}
