package com.we.apiautomation.apitest.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.model.ResponseInfo;
import com.we.apiautomation.apitest.model.TTestcaseApi;
import com.we.apiautomation.apitest.service.TTestcaseApiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/apiTestCase")
public class ApiTestCaseController {

    @Resource
    TTestcaseApiService tTestcaseApiService;

    @RequestMapping("/listPage")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum,@RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "searchData") String searchData ){
        TTestcaseApi tTestcaseApi = JSONObject.parseObject(searchData, TTestcaseApi.class);
        PageInfo<TTestcaseApi> pageInfo = tTestcaseApiService.findByAllwithPage(pageNum, pageSize, tTestcaseApi);
        return new ResponseInfo(true,pageInfo);
    }

}
