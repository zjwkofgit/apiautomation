package com.we.apiautomation.apitest.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.dao.TApiMapper;
import com.we.apiautomation.apitest.model.TApi;
import com.we.apiautomation.apitest.model.TApiResult;
import com.we.apiautomation.apitest.model.po.ApiParam;
import com.we.apiautomation.apitest.service.RequestExecutorServer;
import com.we.apiautomation.apitest.service.TApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TApiServiceImpl implements TApiService {

    @Resource
    private RequestExecutorServer requestExecutorServer;
    @Resource
    private TApiMapper tApiMapper;


    @Override
    public PageInfo<TApi> findByAllwithPage(int page, int pageSize, TApi tApi) {
        PageHelper.startPage(page, pageSize);
        List<TApi> apiList = tApiMapper.findByAll(tApi);
        return new PageInfo<>(apiList);
    }

    @Override
    public TApiResult excApi(TApi api, Map<String, Object> gVars, Map<String, Object> caseVars, List<ApiParam> params) {
        return requestExecutorServer.executeHttpRequest(api, gVars, caseVars, params);
    }
}
