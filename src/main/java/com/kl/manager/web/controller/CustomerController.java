package com.kl.manager.web.controller;

import com.kl.manager.pojo.Customer;
import com.kl.manager.pojo.dto.CustomerDto;
import com.kl.manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/13
 * Time: 20:41
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    /**
     * 查询客户
     *
     * @param customer
     * @param model
     * @return
     */
    @RequestMapping("/query")
    public String query(Customer customer, Model model) {
        List<CustomerDto> customerList = customerService.query();
        model.addAttribute("list", customerList);
        return "customer/customer";
    }


    /**
     * 添加与修改前的操作
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/goAddOrUpdate")
    public String goAddOrUpdate(Model model, Integer id) {
        if (id != null) {
            CustomerDto customerDto = customerService.queryById(id);
            model.addAttribute("dto", customerDto);

        }
        customerService.getSalesManAndCommonInteval(model);
        return "customer/customer_update";
    }

    /**
     * 添加或修改
     *
     * @param customer
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Customer customer) {
        if (customer.getCustomerId() != null) {
            customerService.updateCustomer(customer);
        } else {
            customerService.addCustomer(customer);
        }
        return "redirect:/customer/query";
    }


    /**
     * 删除客户
     *
     * @param id
     * @param request
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
        boolean flag = customerService.deleteCustomer(id);
        return flag;
    }


}
