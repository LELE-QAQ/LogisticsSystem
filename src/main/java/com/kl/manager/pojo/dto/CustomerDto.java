package com.kl.manager.pojo.dto;

import com.kl.manager.pojo.BasePage;
import com.kl.manager.pojo.Customer;

/**
 * Created with IntelliJ IDEA
 * Description:客户类拓展
 * Created By KL
 * Date: 2019/12/13
 * Time: 21:11
 */
public class CustomerDto extends BasePage {

    private Customer customer;
    //常用区间
    private String commonInterval;
    //业务员
    private String realName;
    //订单数目
    private Integer orderNum;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCommonInterval() {
        return commonInterval;
    }

    public void sectCommonInterval(String commonInterval) {
        this.commonInterval = commonInterval;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
