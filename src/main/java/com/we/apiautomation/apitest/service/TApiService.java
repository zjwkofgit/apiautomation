package com.we.apiautomation.apitest.service;


import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.model.TApi;
import com.we.apiautomation.apitest.model.TApiResult;
import com.we.apiautomation.apitest.model.po.ApiParam;

import java.util.List;
import java.util.Map;

public interface TApiService {

    PageInfo<TApi> findByAllwithPage(int page, int pageSize, TApi tApi);

    TApiResult excApi(TApi api, Map<String, Object> gVars, Map<String, Object> caseVars, List<ApiParam> params);

    List<TApi> findByNameAndProjectId(String name, Long projectId);

    int insertSelective(TApi record);
}
