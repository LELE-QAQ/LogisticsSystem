package com.kl.manager.web.controller;

import com.github.pagehelper.PageInfo;
import com.kl.manager.pojo.Role;
import com.kl.manager.pojo.User;
import com.kl.manager.pojo.dto.UserDto;
import com.kl.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/11/27
 * Time: 14:13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询用户
     *
     * @param model
     * @return
     */
    @RequestMapping("/query")
    public String query(Model model) {
        List<User> userList = userService.query(null);
        model.addAttribute("list", userList);
        return "user/user";
    }


    /**
     * 分页查询用户
     * @param model
     * @param userDto
     * @return
     */
    @RequestMapping("/queryPage")
    public String queryPage(Model model,UserDto userDto){
        PageInfo<User> pageInfo = userService.queryUserByPage(userDto);
        model.addAttribute("pageModel",pageInfo);
        System.out.println(pageInfo);
        return "user/user";
    }

    /**
     * 添加或修改前的准备
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/goAddOrUpdate")
    public String goAddOrUpdate(Integer userId, Model model) {
        List<Role> userRoles = userService.queryRole();
        model.addAttribute("roles", userRoles);
        if (userId != null) {
            UserDto userDto = userService.getUpdateInfo(userId);
            model.addAttribute("userDto", userDto);
        }
        return "user/user_update";
    }

    /**
     * 保存用户数据
     *
     * @param userDto
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(UserDto userDto) {
        if (userDto != null && userDto.getUser() != null && userDto.getUser().getUserId() != null) {
            userService.updateUserAndRole(userDto);
        } else {
            Integer count = userService.saveUserAndRole(userDto);
        }

        return "redirect:/user/queryPage";
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Integer userId){
        userService.deleteUser(userId);
        return "redirect:/user/queryPage";
    }
}
