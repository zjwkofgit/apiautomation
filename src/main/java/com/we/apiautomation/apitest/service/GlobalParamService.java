package com.we.apiautomation.apitest.service;

import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.model.GlobalParam;

import java.util.List;
import java.util.Map;

public interface GlobalParamService {


    int deleteByPrimaryKey(Long id);

    int insertSelective(GlobalParam record);

    GlobalParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GlobalParam record);

    int updateByPrimaryKey(GlobalParam record);

    PageInfo<GlobalParam> findByAllwithPage(int page, int pageSize, GlobalParam globalParam);

    List<GlobalParam> findByParamNameAndProjectIdAndType(String paramName, Long projectId, Integer type);

    List<GlobalParam> findByParamNameAndProjectIdAndTypeAndIdNot(String paramName, Long projectId, Integer type, Long notId);

    int insert(GlobalParam record);

    Map<String, Object> findByProjectIdAndTypeAndEnvId(Long projectId, Integer type, Long envId);
}






