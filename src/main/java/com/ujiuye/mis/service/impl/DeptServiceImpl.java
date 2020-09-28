package com.ujiuye.mis.service.impl;

import com.ujiuye.mis.mapper.DeptMapper;
import com.ujiuye.mis.pojo.Dept;
import com.ujiuye.mis.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.List;

/**
 * @author LiLe
 * @create 2020-08-28 15:51
 */

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.selectByExample(null);

    }
}
