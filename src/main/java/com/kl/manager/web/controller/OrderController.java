package com.kl.manager.web.controller;

import com.kl.manager.pojo.dto.OrderDto;
import com.kl.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/14
 * Time: 15:21
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 添加前的准备
     * @return
     */
    @RequestMapping("/goAddOrUpdate")
    public String goAddOrUpdate(Model model,Integer orderId){
        orderService.getAddOrUpdateInfo(model,orderId);
        return "order/order_update";
    }

    /**
     * 添加订单
     * @param orderDto
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody OrderDto orderDto){
        boolean flag =  orderService.saveOrderInfo(orderDto);
        return flag;
    }


}
