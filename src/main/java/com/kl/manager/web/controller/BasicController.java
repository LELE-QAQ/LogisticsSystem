package com.kl.manager.web.controller;

import com.kl.manager.dao.BasicDataMapper;
import com.kl.manager.pojo.BasicData;
import com.kl.manager.pojo.BasicDataExample;
import com.kl.manager.service.BasicService;
import com.sun.xml.internal.org.jvnet.staxex.Base64Data;
import jdk.management.resource.ResourceId;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:基础数据
 * Created By KL
 * Date: 2019/12/12
 * Time: 19:04
 */
@Controller
@RequestMapping("/basic")
public class BasicController {
    @Autowired
    private BasicService basicService;

    /**
     * 查询基础数据
     * 13	12	预付全款
     * 14	12	预付定金&到付全款
     * 15	12	到付
     * 注意key的命名的规则:
     * basicData:13  bascId:13 parentId:12 bascName:预付款	bascDesc:null
     * @param basicData
     * @param model
     * @returni
     */
    @RequestMapping("/query")
    public String query(BasicData basicData , Model model){
        List<BasicData> query = basicService.query(basicData);
        model.addAttribute("list",query);
        return "basic/basic";
    }

    /**
     * 添加或修改前的准备
     * @param model
     * @param baseId
     * @return
     */
    @RequestMapping("/goAddOrUpdate")
    public String goAddOrUpdate(Model model, @Param("baseId") Integer baseId){
        List<BasicData> dataList = basicService.queryParentData();
        model.addAttribute("parents",dataList);
        if(baseId != null){
            BasicData basicData =  basicService.queryById(baseId);
            model.addAttribute("data",basicData);
        }
        return "basic/basic_update";
    }

    /**
     * 添加或修改操作
     * @param basicData
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(BasicData basicData){
        if(basicData.getParentId() == -1){
            basicData.setParentId(null);
        }
        if(basicData.getBaseId() != null){
           basicService.updateBasic(basicData);
        }else {
            basicService.addBasic(basicData);
        }
        return "redirect:/basic/query";
    }





}
