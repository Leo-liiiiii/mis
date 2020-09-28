package com.ujiuye.mis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.mis.pojo.Employee;
import com.ujiuye.mis.pojo.EmployeeVo;
import com.ujiuye.mis.pojo.Role;
import com.ujiuye.mis.query.Qo;
import com.ujiuye.mis.service.EmploeeService;
import com.ujiuye.mis.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiLe
 * @create 2020-08-27 14:45
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmploeeService emploeeService;

    @GetMapping("{pageNum}/{pageSize}")
    public R findAll(@PathVariable int pageNum, @PathVariable int pageSize, Qo qo) {
       // PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = emploeeService.findAll(pageNum,pageSize,qo);
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        return CollectionUtils.isEmpty(list) ? R.error() : R.ok().data("item", pageInfo);
    }

    @PostMapping("login")
    public  R login(String username, String password, HttpServletRequest request){
        List<Employee> ret = emploeeService.login(username,password);
         if (CollectionUtils.isEmpty(ret)){
             return R.error().message("密码错误,请从新输入");
         }
        request.getSession().setAttribute("active",ret.get(0));
          return R.ok();
    }

    @GetMapping("checkName")
    public    R   checkName(String username){

      List<Employee> employees=  emploeeService.checkName(username);
        if (CollectionUtils.isEmpty(employees)) {
            return R.error();
        }
            return   R.ok();
    }







}
