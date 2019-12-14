package com.kl.manager.dao;

import com.kl.manager.pojo.Customer;
import com.kl.manager.pojo.CustomerExample;
import com.kl.manager.pojo.dto.CustomerDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    long countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<CustomerDto> selectByExample(CustomerExample example);

    CustomerDto selectByPrimaryKey(Integer customerId);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    int hasOrder(Integer id);
}