package com.we.apiautomation.apitest.dao;


import com.we.apiautomation.apitest.model.TSuiteCaseApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TSuiteCaseApiMapper {

    int insertSelective(TSuiteCaseApi record);

    Integer findMaxSortBySuiteId(@Param("suiteId")Long suiteId);


}
