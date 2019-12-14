package com.kl.manager.service;

import com.kl.manager.pojo.BasicData;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/12
 * Time: 19:07
 */
public interface BasicService {
    List<BasicData> query(BasicData basicData);

    List<BasicData> queryParentData();

    void addBasic(BasicData basicData);

    BasicData queryById(Integer baseId);

    void updateBasic(BasicData basicData);

    List<BasicData> queryBase(String baseCommonInterval);
}
