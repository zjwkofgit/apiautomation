package com.we.apiautomation.apitest.dao;

import com.we.apiautomation.apitest.model.TTestsuiteApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TTestsuiteApiMapper {


    List<TTestsuiteApi> findByAll(TTestsuiteApi tTestsuiteApi);


}
