package com.kl.manager.pojo.dto;

import com.kl.manager.pojo.BasePage;
import com.kl.manager.pojo.Role;
import com.kl.manager.pojo.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:User的拓展类
 * Created By KL
 * Date: 2019/11/27
 * Time: 15:30
 */
public class UserDto extends BasePage {
    private User user;
    private List<Integer> roleIds;
    private List<Role> roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
