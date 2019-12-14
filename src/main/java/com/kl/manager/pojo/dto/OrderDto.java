package com.kl.manager.pojo.dto;

import com.kl.manager.pojo.Order;
import com.kl.manager.pojo.OrderDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/14
 * Time: 16:30
 */
public class OrderDto extends Order {

    private List<OrderDetail> orderDetails;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
