package com.we.apiautomation.apitest.dao;

import com.we.apiautomation.apitest.model.TApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TApiMapper {

    List<TApi> findByAll(TApi tApi);

    int insertSelective(TApi record);

    List<TApi> findByNameAndProjectId(@Param("name") String name, @Param("projectId") Long projectId);



}
