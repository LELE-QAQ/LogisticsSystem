package com.kl.manager.service.impl;

import com.kl.common.Constant;
import com.kl.manager.dao.CustomerMapper;
import com.kl.manager.pojo.BasicData;
import com.kl.manager.pojo.Customer;
import com.kl.manager.pojo.CustomerExample;
import com.kl.manager.pojo.User;
import com.kl.manager.pojo.dto.CustomerDto;
import com.kl.manager.service.BasicService;
import com.kl.manager.service.CustomerService;
import com.kl.manager.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/13
 * Time: 20:42
 */
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private BasicService basicService;
    @Autowired
    private UserService userService;

    /**
     * 查询客户信息
     * <p>
     * 业务员：只能查询自己管理的客户
     * 操作员和管理员：可以查看所有的客户
     * 非以上角色是查看不了客户信息的
     *
     * @param
     * @return
     */
    @Override
    public List<CustomerDto> query() {
        CustomerExample customerExample = new CustomerExample();
        //获取当前登录用户信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (subject.hasRole(Constant.ROLE_CZY) || subject.hasRole(Constant.ROLE_ADMIN)) {
            //操作员或管理员返回所有客户
            return customerMapper.selectByExample(customerExample);
        } else if (subject.hasRole(Constant.ROLE_YWY)) {
            //业务员根据业务员id查询
            customerExample.createCriteria().andUserIdEqualTo(user.getUserId());
            return customerMapper.selectByExample(customerExample);
        }
        return null;
    }

    /**
     * 根据id查询客户
     *
     * @param id
     * @return
     */
    @Override
    public CustomerDto queryById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加客户
     *
     * @param customer
     */
    @Override
    public void addCustomer(Customer customer) {
        customerMapper.insertSelective(customer);
    }

    /**
     * 查询出所有的常用区间与业务员
     *
     * @param model
     */
    @Override
    public void getSalesManAndCommonInteval(Model model) {
        List<BasicData> dataList = basicService.queryBase(Constant.BASE_COMMON_INTERVAL);
        List<User> userList = userService.queryYWY();
        model.addAttribute("commons", dataList);
        model.addAttribute("users", userList);

    }

    /**
     * 修改客户信息
     * @param customer
     */
    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        int count = customerMapper.hasOrder(id);
        if(count == 0){
            customerMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

}
