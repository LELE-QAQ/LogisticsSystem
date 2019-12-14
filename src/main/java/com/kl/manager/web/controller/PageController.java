package com.kl.manager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/11/15
 * Time: 14:26
 */
@Controller
public class PageController {
    @RequestMapping("/{path}")
    public String page(@PathVariable String path) {
        return path;
    }
}
