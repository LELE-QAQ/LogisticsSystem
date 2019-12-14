package com.kl.manager.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/10
 * Time: 20:58
 */
@Controller
public class LoginController {

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("/loginPage.do")
    public String loginPage() {
        return "redirect:login";
    }


    /*@RequestMapping("/login.do")
    public String login(HttpServletRequest request, Model model) {
        Object ex = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (ex != null) {
            if (UnknownAccountException.class.getName().equals(ex)) {
                System.out.println("----账号不正确----->");
                model.addAttribute("msg", "账号不正确");
            } else if (IncorrectCredentialsException.class.getName().equals(ex)) {
                System.out.println("----密码不正确----->");
                model.addAttribute("msg", "密码不正确");
            } else {
                System.out.println("----其他错误----->");
                model.addAttribute("msg", "其他错误");
            }

        }
        return "login";
    }*/

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login.do")
    private String login(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         Model model) {
        Subject user = SecurityUtils.getSubject();
        if (!user.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //token.setRememberMe(true);
            try {
                user.login(token);
            } catch (UnknownAccountException uae) {
                model.addAttribute("msg", "账号不正确");
                return "login";
            } catch (IncorrectCredentialsException ice) {
                model.addAttribute("msg", "密码不正确");
                return "login";
            } catch (AuthenticationException ae) {
                model.addAttribute("msg", "其他错误");
                return "login";
            }
        }
        return "redirect:main";
    }

    /**
     * 用户退出
     *
     * @return
     */
    @RequestMapping("/logout.do")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }
}
