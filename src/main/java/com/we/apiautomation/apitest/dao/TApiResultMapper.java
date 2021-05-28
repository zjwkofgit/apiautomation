package com.we.apiautomation.apitest.dao;


import com.we.apiautomation.apitest.model.TApiResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TApiResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TApiResult record);

    int insertSelective(TApiResult record);

    TApiResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApiResult record);

    int updateByPrimaryKey(TApiResult record);

    int deleteByPlanLogId(@Param("planLogId") Long planLogId);

    List<TApiResult> findByAll(TApiResult tApiResult);


}
