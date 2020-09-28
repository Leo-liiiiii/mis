package com.ujiuye.mis.service;

import com.ujiuye.mis.pojo.Sources;

import java.util.List;

/**
 * @author LiLe
 * @create 2020-08-25 20:15
 */
public interface SourcesService {
    Sources findRoot();

    List<Sources> findAll();

    boolean save(Sources sources);

    Sources findById(int id);

    boolean update(Sources sources);

    boolean deleteById(int id);

    List<Sources> findAll2();
}
