package com.kl.manager.service;

import com.github.pagehelper.PageInfo;
import com.kl.manager.pojo.Role;
import com.kl.manager.pojo.User;
import com.kl.manager.pojo.dto.UserDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/11/27
 * Time: 14:49
 */
public interface UserService {
    public List<User> query(User user);

    public List<Role> queryRole();

    public Integer saveUserAndRole(UserDto userDto);

    public UserDto getUpdateInfo(Integer userId);

    void updateUserAndRole(UserDto userDto);

    Integer deleteUser(Integer userId);

    PageInfo<User> queryUserByPage(UserDto userDto);

    List<Role> queryRoleByUserId(Integer userId);

    List<User> queryYWY();
}
