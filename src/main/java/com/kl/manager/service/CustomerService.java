package com.kl.manager.service;

import com.kl.manager.pojo.Customer;
import com.kl.manager.pojo.dto.CustomerDto;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/13
 * Time: 20:42
 */
public interface CustomerService {
    List<CustomerDto> query();

    CustomerDto queryById(Integer id);

    void addCustomer(Customer customer);

    void getSalesManAndCommonInteval(Model model);

    void updateCustomer(Customer customer);

    boolean deleteCustomer(Integer id);
}
