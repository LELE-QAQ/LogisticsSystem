package com.kl.manager.service.impl;

import com.kl.common.Constant;
import com.kl.manager.dao.OrderDetailMapper;
import com.kl.manager.dao.OrderMapper;
import com.kl.manager.pojo.BasicData;
import com.kl.manager.pojo.Customer;
import com.kl.manager.pojo.OrderDetail;
import com.kl.manager.pojo.User;
import com.kl.manager.pojo.dto.CustomerDto;
import com.kl.manager.pojo.dto.OrderDto;
import com.kl.manager.service.BasicService;
import com.kl.manager.service.CustomerService;
import com.kl.manager.service.OrderService;
import com.kl.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/14
 * Time: 15:22
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BasicService basicService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public void getAddOrUpdateInfo(Model model, Integer orderId) {
        //1.查询所有业务员
        List<User> userList = userService.queryYWY();
        //2.查询所有客户信息
        List<CustomerDto> customerList = customerService.query();
        //3.查询常用区间
        List<BasicData> dataList = basicService.queryBase(Constant.BASE_COMMON_INTERVAL);
        // 4.付款方式
        List<BasicData> payments = basicService.queryBase(Constant.BASIC_PAYMENT_TYPE);
        // 5.货运方式
        List<BasicData> freights = basicService.queryBase(Constant.BASIC_FREIGHT_TYPE);
        // 6.取件方式
        List<BasicData> fetchs = basicService.queryBase(Constant.BASIC_FETCH_TYPE);
        // 7.单位
        List<BasicData> units = basicService.queryBase(Constant.BASIC_UNIT);

        ArrayList<Customer> customers = new ArrayList<>();

        for (CustomerDto customerDto : customerList) {
            customers.add(customerDto.getCustomer());
        }

        model.addAttribute("users", userList);
        model.addAttribute("intervals", dataList);
        model.addAttribute("payments", payments);
        model.addAttribute("freights", freights);
        model.addAttribute("fetchs", fetchs);
        model.addAttribute("customers", customers);
        // m.addAttribute("countrys", countrys);
        model.addAttribute("units", units);
    }

    /**
     * 添加订单
     * @param orderDto
     * @return
     */
    @Override
    public boolean saveOrderInfo(OrderDto orderDto) {
        try {
            //添加订单信息
            orderMapper.insertSelective(orderDto);
            //订单详情
            List<OrderDetail> orderDetails = orderDto.getOrderDetails();
            if (orderDetails != null && orderDetails.size() > 0) {
                for (OrderDetail orderDetail : orderDetails) {
                    orderDetail.setOrderId(orderDto.getOrderId());
                    orderDetailMapper.insertSelective(orderDetail);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
