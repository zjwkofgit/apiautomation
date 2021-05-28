package com.we.apiautomation.apitest.dao;


import com.we.apiautomation.apitest.model.TTestPlanResultApi;

import java.util.List;

public interface TTestPlanResultApiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestPlanResultApi record);

    int insertSelective(TTestPlanResultApi record);

    TTestPlanResultApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestPlanResultApi record);

    int updateByPrimaryKey(TTestPlanResultApi record);

    List<TTestPlanResultApi> findByAll(TTestPlanResultApi tTestPlanResultApi);


}
