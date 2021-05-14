package com.we.apiautomation.apitest.service;


import com.we.apiautomation.apitest.model.TApi;
import com.we.apiautomation.apitest.model.TApiResult;
import com.we.apiautomation.apitest.model.po.ApiParam;

import java.util.List;
import java.util.Map;

public interface RequestExecutorServer {
    TApiResult executeHttpRequest(TApi tApi, Map<String, Object> gVars, Map<String, Object> caseVars, List<ApiParam> params );
}






