package com.we.apiautomation.apitest.dao;

import com.rabbit.model.TStepApi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TStepApiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TStepApi record);

    int insertSelective(TStepApi record);

    TStepApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TStepApi record);

    int updateByPrimaryKey(TStepApi record);

    List<TStepApi> findByTestcaseId(@Param("testcaseId") Long testcaseId);

    Integer findMaxSortByTestcaseId(@Param("testcaseId") Long testcaseId);

    int deleteByTestcaseId(@Param("testcaseId") Long testcaseId);

    List<TStepApi> findByTypeAndSourceId(@Param("type") Integer type, @Param("sourceId") Long sourceId);


}
