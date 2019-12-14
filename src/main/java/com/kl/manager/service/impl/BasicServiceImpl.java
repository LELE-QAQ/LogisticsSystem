package com.kl.manager.service.impl;

import com.kl.manager.dao.BasicDataMapper;
import com.kl.manager.pojo.BasicData;
import com.kl.manager.pojo.BasicDataExample;
import com.kl.manager.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/12
 * Time: 19:07
 */
@Service
public class BasicServiceImpl implements BasicService {
    @Autowired
    private BasicDataMapper basicDataMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有基础数据
     * @param basicData
     * @return
     */
    @Override
    public List<BasicData> query(BasicData basicData) {
        //1.从缓存中查询
        List<BasicData> dataList = redisTemplate.boundHashOps(BasicData.class.getSimpleName()).values();

        //2.缓存中没有
        if(dataList == null || dataList.size() == 0){
            BasicDataExample basicDataExample = new BasicDataExample();
            //从数据库中查询
            dataList = basicDataMapper.selectByExample(basicDataExample);
            //添加入数据库
            for (BasicData data : dataList) {
                /*redisTemplate.boundHashOps(BasicData.class.getSimpleName()).put(basicData.getBaseId(),basicData);*/
                redisTemplate.boundHashOps(BasicData.class.getSimpleName()).put(data.getBaseId(),data);
            }
            System.out.println("<----------数据库中查询");
        }
        System.out.println(dataList);

        return dataList;
    }

    /**
     * 查询父类信息
     * @return
     */
    @Override
    public List<BasicData> queryParentData() {
        BasicDataExample example = new BasicDataExample();
        example.createCriteria().andParentIdIsNull();
        return basicDataMapper.selectByExample(example);
    }

    /**
     * 添加基础数据
     * @param basicData
     */
    @Override
    public void addBasic(BasicData basicData) {
        //删除缓存中的基础数据
        redisTemplate.delete(BasicData.class.getSimpleName());
        basicDataMapper.insertSelective(basicData);
    }

    /**
     * 根据baseId查询基础数据
     * @param baseId
     * @return
     */
    @Override
    public BasicData queryById(Integer baseId) {
        BasicData basicData = (BasicData) redisTemplate.boundHashOps(BasicData.class.getSimpleName()).get(baseId);

        if(basicData == null){
            basicData = basicDataMapper.selectByPrimaryKey(baseId);
        }

        return basicData;
    }

    /**
     * 更新基础数据
     * @param basicData
     */
    @Override
    public void updateBasic(BasicData basicData) {
        redisTemplate.delete(BasicData.class.getSimpleName());
        basicDataMapper.updateByPrimaryKeySelective(basicData);
    }

    /**
     * 查询所有常用区间
     * @param baseCommonInterval
     * @return
     */
    @Override
    public List<BasicData> queryBase(String baseCommonInterval) {
        return basicDataMapper.queryByParentBaseName(baseCommonInterval);
    }
}
