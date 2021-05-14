package com.we.apiautomation.apitest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.model.ResponseInfo;
import com.we.apiautomation.apitest.model.TApi;
import com.we.apiautomation.apitest.service.TApiService;
import com.we.apiautomation.apitest.utils.FastJSONHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    TApiService tApiService;

    @RequestMapping("/debug")
    public String debugTApi(){
        return "hello-debug";
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "searchData") String searchData) {

        TApi tApi = JSONObject.parseObject(searchData,TApi.class);
        PageInfo pageInfo = tApiService.findByAllwithPage(pageNum, pageSize, tApi);
        return new ResponseInfo(true, pageInfo);
    }
}
