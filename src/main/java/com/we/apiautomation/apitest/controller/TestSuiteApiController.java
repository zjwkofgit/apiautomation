package com.we.apiautomation.apitest.controller;

import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.model.ResponseInfo;
import com.we.apiautomation.apitest.model.TSuiteCaseApi;
import com.we.apiautomation.apitest.model.TTestsuiteApi;
import com.we.apiautomation.apitest.service.TSuiteCaseApiService;
import com.we.apiautomation.apitest.service.TTestcaseApiService;
import com.we.apiautomation.apitest.service.TTestsuiteApiService;
import com.we.apiautomation.apitest.utils.FastJSONHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/testsuiteApi")
@Api(tags = "API用例集相关接口")
public class TestSuiteApiController {

    @Resource
    TTestsuiteApiService testsuiteApiService;
    @Resource
    TTestcaseApiService testcaseApiService;
    @Resource
    TSuiteCaseApiService suiteCaseApiService;

    @GetMapping("/listPage")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "searchData") String searchData) {
        TTestsuiteApi testsuiteApi = FastJSONHelper.deserialize(searchData, TTestsuiteApi.class);
        PageInfo pageInfo = testsuiteApiService.findByAllwithPage(pageNum, pageSize, testsuiteApi);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listCaseById/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listCaseById(@PathVariable long id) {
        return new ResponseInfo(true, testcaseApiService.selectDtoBySuiteId(id));
    }

    @PostMapping("/addCaseToSuite")
    @ApiOperation(value = "把用例加入用例集")
    public ResponseInfo addCaseToBusiness(@RequestBody List<TSuiteCaseApi> tSuiteCaseApis) {
        suiteCaseApiService.addCaseToSuite(tSuiteCaseApis);
        return new ResponseInfo(true, "新增用例成功");
    }





}
