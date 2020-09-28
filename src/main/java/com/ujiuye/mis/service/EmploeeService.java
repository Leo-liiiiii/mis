package com.ujiuye.mis.service;

import com.ujiuye.mis.pojo.Employee;
import com.ujiuye.mis.query.Qo;

import java.util.List;

/**
 * @author LiLe
 * @create 2020-08-27 14:46
 */
public interface EmploeeService {
    List<Employee> findAll(int pageNum, int pageSize, Qo qo);



    List<Employee>  login(String username, String password);

    List<Employee> checkName(String username);

}
