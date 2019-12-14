package com.kl.manager.service;

import com.kl.manager.pojo.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/11/15
 * Time: 15:49
 */
public interface RoleService {
    public List<Role> query();

    List<Role> queryRoleByUserId(Integer userId);
}
