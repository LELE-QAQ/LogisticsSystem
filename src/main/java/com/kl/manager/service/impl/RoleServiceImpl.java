package com.kl.manager.service.impl;

import com.kl.manager.dao.RoleMapper;
import com.kl.manager.pojo.Role;
import com.kl.manager.pojo.RoleExample;
import com.kl.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/11/15
 * Time: 15:49
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> query() {
        RoleExample roleExample = new RoleExample();
        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public List<Role> queryRoleByUserId(Integer userId) {
        return roleMapper.queryRoleByUserId(userId);
    }
}
