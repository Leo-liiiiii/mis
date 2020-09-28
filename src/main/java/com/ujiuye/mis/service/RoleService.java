package com.ujiuye.mis.service;

import com.ujiuye.mis.pojo.Role;

import java.util.List;

/**
 * @author LiLe
 * @create 2020-08-26 11:19
 */
public interface RoleService {
    List<Role> findAll();

    boolean save(Role role, String ids);

    Role findById(int id);

    Boolean update(Role role, String ids);

    boolean delBatch(String ids);

    List<Role> findAll2();
}
