package com.we.apiautomation.apitest.dao;


import com.we.apiautomation.apitest.model.TSuiteCaseApi;
import org.apache.ibatis.annotations.Param;

public interface TSuiteCaseApiMapper {

    int insertSelective(TSuiteCaseApi record);



}
