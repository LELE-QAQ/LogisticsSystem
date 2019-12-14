package com.kl.manager.dao;

import com.kl.manager.pojo.BasicData;
import com.kl.manager.pojo.BasicDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicDataMapper {
    long countByExample(BasicDataExample example);

    int deleteByExample(BasicDataExample example);

    int deleteByPrimaryKey(Integer baseId);

    int insert(BasicData record);

    int insertSelective(BasicData record);

    List<BasicData> selectByExample(BasicDataExample example);

    BasicData selectByPrimaryKey(Integer baseId);

    int updateByExampleSelective(@Param("record") BasicData record, @Param("example") BasicDataExample example);

    int updateByExample(@Param("record") BasicData record, @Param("example") BasicDataExample example);

    int updateByPrimaryKeySelective(BasicData record);

    int updateByPrimaryKey(BasicData record);

    List<BasicData> queryByParentBaseName(String baseCommonInterval);
}