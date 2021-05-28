package com.we.apiautomation.apitest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.model.ErrorInfo;
import com.we.apiautomation.apitest.model.ResponseInfo;
import com.we.apiautomation.apitest.model.TApi;
import com.we.apiautomation.apitest.service.TApiService;
import com.we.apiautomation.apitest.utils.FastJSONHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "api相关接口")
public class ApiController {

    @Resource
    TApiService tApiService;

    @RequestMapping("/hello")
    public String debugTApi(){
        return "hello-test";
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "searchData") String searchData) {
        TApi tApi = JSONObject.parseObject(searchData,TApi.class);
        PageInfo pageInfo = tApiService.findByAllwithPage(pageNum, pageSize, tApi);
        return new ResponseInfo(true, pageInfo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口")
    public ResponseInfo saveTApi(@RequestBody TApi tApi) {
        List<TApi> tApis = tApiService.findByNameAndProjectId(tApi.getName(), tApi.getProjectId());
        if (tApis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口" + tApi.getName() + "已存在"));
        }

        tApiService.insertSelective(tApi);
        return new ResponseInfo(true, tApi);
    }


}
