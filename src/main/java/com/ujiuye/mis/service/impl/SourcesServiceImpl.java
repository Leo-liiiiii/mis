package com.ujiuye.mis.service.impl;

import com.ujiuye.mis.mapper.SourcesMapper;
import com.ujiuye.mis.pojo.Sources;
import com.ujiuye.mis.pojo.SourcesExample;
import com.ujiuye.mis.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author LiLe
 * @create 2020-08-25 20:17
 */
@Service
public class SourcesServiceImpl implements SourcesService {
    @Autowired
    private SourcesMapper sourcesMapper;

    @Override
    public Sources findRoot() {
        SourcesExample sourcesExample = new SourcesExample();
        sourcesExample.createCriteria().andPidEqualTo(0);
        Sources root = sourcesMapper.selectByExample(sourcesExample).get(0);

        setChildren(root);

        return root;
    }

    @Override
    public List<Sources> findAll() {
        return sourcesMapper.selectByExample(null);
    }



    private void setChildren(Sources root) {
        root.setUrl(null);
        SourcesExample sourcesExample = new SourcesExample();
        sourcesExample.createCriteria().andPidEqualTo(root.getId());
        List<Sources> sources = sourcesMapper.selectByExample(sourcesExample);


        if (!CollectionUtils.isEmpty(sources)) {
            root.setChildren(sources);
            for (Sources source : sources) {
                setChildren(source);
            }
        }
    }


    @Override
    public boolean save(Sources sources) {
        return sourcesMapper.insert(sources)>0;
    }

    @Override
    public Sources findById(int id) {
        return sourcesMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean update(Sources sources) {
        return sourcesMapper.updateByPrimaryKey(sources)>0;
    }

    @Override
    public boolean deleteById(int id) {
        return sourcesMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public List<Sources> findAll2() {
        SourcesExample sourcesExample = new SourcesExample();
        sourcesExample.createCriteria().andPidEqualTo(1);
        return  sourcesMapper.selectByExample(sourcesExample);
    }
}
