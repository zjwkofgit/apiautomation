package com.we.apiautomation.apitest.service;

import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.model.TTestsuiteApi;

public interface TTestsuiteApiService {



    PageInfo<TTestsuiteApi> findByAllwithPage(int page, int pageSize, TTestsuiteApi tTestsuiteApi);

}
