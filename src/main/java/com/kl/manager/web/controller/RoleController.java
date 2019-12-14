package com.kl.manager.web.controller;

import com.kl.manager.pojo.Role;
import com.kl.manager.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/11/15
 * Time: 15:48
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/query")
    @RequiresRoles("管理员")
    public String query(Model model){
        List<Role> roleList =  roleService.query();
        model.addAttribute("roleList",roleList);
        return "role/role";
    }
}
