package com.ujiuye.mis.service.impl;

import com.github.pagehelper.PageHelper;
import com.ujiuye.mis.mapper.DeptMapper;
import com.ujiuye.mis.mapper.EmployeeMapper;
import com.ujiuye.mis.pojo.Dept;
import com.ujiuye.mis.pojo.DeptExample;
import com.ujiuye.mis.pojo.Employee;
import com.ujiuye.mis.pojo.EmployeeExample;
import com.ujiuye.mis.query.Qo;
import com.ujiuye.mis.service.EmploeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiLe
 * @create 2020-08-27 14:46
 */
@Service
public class EmploeeServiceImpl implements EmploeeService {


    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Employee> findAll(int pageNum, int pageSize, Qo qo) {

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        if (qo.getType()!=null && qo.getType()==1){
            criteria.andEnameLike("%"+qo.getContent()+"%");
        }if (qo.getType()!=null && qo.getType()==2){
            DeptExample deptExample = new DeptExample();
            deptExample.createCriteria().andDnameLike("%" + qo.getContent() + "%");
            List<Dept> depts = deptMapper.selectByExample(deptExample);
            System.out.println("==============="+depts.size());
            if (depts.size()==0){
                return  new ArrayList<>();
            }
            List<Integer> dnos = depts.stream().map(d -> d.getDeptno()).collect(Collectors.toList());
            criteria.andDfkIn(dnos);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        employees.stream().forEach(e -> e.setDept(deptMapper.selectByPrimaryKey(e.getDfk())));
        return employees;
    }



    @Override
    public  List<Employee> login(String username, String password) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<Employee>   employees= employeeMapper.selectByExample(employeeExample);
       return  employees;

    }

    @Override
    public List<Employee> checkName(String username) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUsernameEqualTo(username);
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        return employees;


    }
}
