package com.we.apiautomation.apitest.service;

import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.dto.TApiCaseResultDto;
import com.we.apiautomation.apitest.dto.TestcaseApiDto;
import com.we.apiautomation.apitest.model.TTestcaseApi;

import java.util.List;

public interface TTestcaseApiService {

    List<TestcaseApiDto> selectDtoBySuiteId(Long id);

    PageInfo<TTestcaseApi> findByAllwithPage(int pageNum,int pageSize,TTestcaseApi tTestcaseApi);
}




