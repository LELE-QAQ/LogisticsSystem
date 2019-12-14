package com.kl.manager.service;

import com.kl.manager.pojo.dto.OrderDto;
import org.springframework.ui.Model;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/14
 * Time: 15:21
 */
public interface OrderService {
    void getAddOrUpdateInfo(Model model, Integer orderId);

    boolean saveOrderInfo(OrderDto orderDto);

}
