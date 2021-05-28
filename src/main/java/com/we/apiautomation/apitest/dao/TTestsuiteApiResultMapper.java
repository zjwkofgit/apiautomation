package com.we.apiautomation.apitest.dao;

import com.we.apiautomation.apitest.dto.TTestsuiteApiResultDto;
import com.we.apiautomation.apitest.model.TTestsuiteApiResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TTestsuiteApiResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestsuiteApiResult record);

    int insertSelective(TTestsuiteApiResult record);

    TTestsuiteApiResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestsuiteApiResult record);

    int updateByPrimaryKey(TTestsuiteApiResult record);

    int deleteByPlanLogId(@Param("planLogId") Long planLogId);

    List<TTestsuiteApiResult> findByAll(TTestsuiteApiResult tTestsuiteApiResult);

    List<TTestsuiteApiResultDto> findDtoByAll(TTestsuiteApiResult tTestsuiteApiResult);

    TTestsuiteApiResult findByPlanIdCount(@Param("planId") Long planLogId);

}
