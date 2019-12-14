package com.kl.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kl.manager.dao.UserMapper;
import com.kl.manager.pojo.Role;
import com.kl.manager.pojo.User;
import com.kl.manager.pojo.UserExample;
import com.kl.manager.pojo.dto.UserDto;
import com.kl.manager.service.RoleService;
import com.kl.manager.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/11/27
 * Time: 14:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public List<User> query(User user) {
        UserExample userExample = new UserExample();
        if (user != null) {
            UserExample.Criteria criteria = userExample.createCriteria();
            if (user.getUserName() != null && !"".equals(user.getUserName())) {
                criteria.andUserNameEqualTo(user.getUserName());
            }
        }
        return userMapper.selectByExample(userExample);
    }

    /**
     * 查询所有角色信息
     * @return
     */
    @Override
    public List<Role> queryRole() {
        return roleService.query();
    }

    @Override
    public Integer saveUserAndRole(UserDto userDto) {
        //1.添加用户
        User user = userDto.getUser();
        String salt = UUID.randomUUID().toString().replace("-", "");
        user.setU1(salt);
        user.setPassword(new Md5Hash(user.getPassword(), salt, 1024).toString());
        this.addUser(user);

        //2.用户与角色信息关联
        List<Integer> roleIds = userDto.getRoleIds();
        if (roleIds != null && roleIds.size() > 0) {
            for (Integer roleId : roleIds) {
                this.saveUserIdAndRoleId(user.getUserId(), roleId);
            }
        }
        return 1;
    }

    /**
     * 修改准备，查询用户信息并返回
     *
     * @param userId
     * @return
     */
    @Override
    public UserDto getUpdateInfo(Integer userId) {
        User user = this.queryById(userId);
        List<Role> roleList = this.queryRoleByUserId(userId);
        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setRoles(roleList);
        return userDto;
    }

    /**
     * 更新用户数据与关联信息
     *
     * @param userDto
     */
    @Override
    public void updateUserAndRole(UserDto userDto) {
        User user = userDto.getUser();
        //1.更新用户数据
        this.updateUser(user);
        //2.更新关联表数据
        this.deleteUserRoleByUserId(user.getUserId());
        //3.重新添加关联信息
        List<Integer> roleIds = userDto.getRoleIds();
        if (roleIds != null && roleIds.size() > 0) {
            for (Integer roleId : roleIds) {
                this.saveUserIdAndRoleId(user.getUserId(), roleId);
            }
        }

    }

    /**
     * 用户删除
     *
     * @param userId
     */
    @Override
    public Integer deleteUser(Integer userId) {
        //删除关联表信息
        this.deleteUserRoleByUserId(userId);
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public PageInfo<User> queryUserByPage(UserDto userDto) {
        //设置分页参数
        PageHelper.startPage(userDto.getPageNum(), userDto.getPageSize());
        //执行查询操作
        List<User> list = this.query(null);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 查询用户对应的角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> queryRoleByUserId(Integer userId) {
        return roleService.queryRoleByUserId(userId);
    }

    /**
     * 查询所有业务员
     * @return
     */
    @Override
    public List<User> queryYWY() {
        return userMapper.queryYWY();
    }


    /**
     * 删除关联表旧数据
     *
     * @param userId
     */
    private void deleteUserRoleByUserId(Integer userId) {
        userMapper.deleteUserRoleByUserId(userId);
    }

    private void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }


    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    private User queryById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 关联用户与角色
     *
     * @param userId
     * @param roleId
     */
    private void saveUserIdAndRoleId(Integer userId, Integer roleId) {
        userMapper.saveUserIdAndRoleId(userId, roleId);
    }

    /**
     * insertSelective对应的sql语句加入了NULL校验，即只会插入数据不为null的字段值。
     * 成功返回1，失败返回0。
     * insert则会插入所有字段，会插入null,如果存在默认值会覆盖。
     *
     * @param user
     * @return
     */
    private Integer addUser(User user) {
        return userMapper.insertSelective(user);
    }


}
