package com.ujiuye.mis.service.impl;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import com.ujiuye.mis.mapper.RoleMapper;
import com.ujiuye.mis.mapper.RoleSourcesMapper;
import com.ujiuye.mis.mapper.SourcesMapper;
import com.ujiuye.mis.pojo.*;
import com.ujiuye.mis.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LiLe
 * @create 2020-08-26 11:19
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleSourcesMapper roleSourcesMapper;

    @Autowired
    private SourcesMapper sourcesMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.selectByExample(null);
    }

    @Override
    @Transactional
    public boolean save(Role role, String ids) {

        try {
            roleMapper.insertSelective(role);
            Integer roleid = role.getRoleid();

            Stream<String> stream = Arrays.stream(ids.split("-"));
            Stream<Integer> integerStream = stream.map(id -> Integer.valueOf(id));
            List<Integer> sids = integerStream.collect(Collectors.toList());
            // List<Integer> sids = Arrays.stream(ids.split("-")).map(id -> Integer.valueOf(id)).collect(Collectors.toList());

            for (Integer sid : sids) {
                RoleSources roleSources = new RoleSources();
                roleSources.setRsdis(role.getRolename() + "的权限");
                roleSources.setRoleFk(roleid);
                roleSources.setResourcesFk(sid);
                roleSourcesMapper.insertSelective(roleSources);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }


    }

    @Override
    public Role findById(int id) {

        Role role = roleMapper.selectByPrimaryKey(id);
        RoleSourcesExample roleSourcesExample = new RoleSourcesExample();
        roleSourcesExample.createCriteria().andRoleFkEqualTo(id);
        List<RoleSources> roleSources = roleSourcesMapper.selectByExample(roleSourcesExample);
        List<Integer> sids = roleSources.stream().map(rs -> rs.getResourcesFk()).collect(Collectors.toList());

        List<Sources> sources = sourcesMapper.selectByExample(null);
        System.out.println("=================="+sids);
        for (Sources source : sources) {
            if (sids.contains(source.getId())){
                source.setChecked(true);
            }
        }

        role.setSourcesList(sources);

        return role;
    }
    @Override
    @Transactional
    public Boolean update(Role role, String ids) {

        try {
            roleMapper.updateByPrimaryKey(role);

            RoleSourcesExample roleSourcesExample = new RoleSourcesExample();
            roleSourcesExample.createCriteria().andRoleFkEqualTo(role.getRoleid());
            roleSourcesMapper.deleteByExample(roleSourcesExample);
            List<Integer> sids = Arrays.stream(ids.split("-")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

            for (Integer sid : sids) {
                RoleSources roleSources = new RoleSources();
                roleSources.setRsdis(role.getRolename()+"的权限");
                roleSources.setRoleFk(role.getRoleid());
                roleSources.setResourcesFk(sid);
                roleSourcesMapper.insertSelective(roleSources);
            }
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delBatch(String ids) {

        try {
            List<Integer> list = Arrays.stream(ids.split("-")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

            RoleSourcesExample roleSourcesExample = new RoleSourcesExample();
            roleSourcesExample.createCriteria().andRoleFkIn(list);
            roleSourcesMapper.deleteByExample(roleSourcesExample);

            RoleExample roleExample = new RoleExample();
            roleExample.createCriteria().andRoleidIn(list);
            roleMapper.deleteByExample(roleExample);
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return  false;
        }


    }

    @Override
    public List<Role> findAll2() {
        return roleMapper.selectByExample(null);
    }


}
