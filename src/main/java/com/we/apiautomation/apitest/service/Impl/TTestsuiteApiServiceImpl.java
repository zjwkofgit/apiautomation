package com.we.apiautomation.apitest.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.we.apiautomation.apitest.dao.TTestsuiteApiMapper;
import com.we.apiautomation.apitest.model.TTestsuiteApi;
import com.we.apiautomation.apitest.service.TTestsuiteApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TTestsuiteApiServiceImpl implements TTestsuiteApiService {

    @Resource
    TTestsuiteApiMapper testsuiteApiMapper;

    @Override
    public PageInfo<TTestsuiteApi> findByAllwithPage(int page, int pageSize, TTestsuiteApi tTestsuiteApi) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(testsuiteApiMapper.findByAll(tTestsuiteApi));
    }

}
