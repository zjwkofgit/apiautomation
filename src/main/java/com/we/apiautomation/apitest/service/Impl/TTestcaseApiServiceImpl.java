package com.we.apiautomation.apitest.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.dao.TStepApiMapper;
import com.we.apiautomation.apitest.dao.TTestcaseApiDtoMapper;
import com.we.apiautomation.apitest.dao.TTestcaseApiMapper;
import com.we.apiautomation.apitest.dto.StepApiDto;
import com.we.apiautomation.apitest.dto.TApiCaseResultDto;
import com.we.apiautomation.apitest.dto.TestcaseApiDto;
import com.we.apiautomation.apitest.model.*;
import com.we.apiautomation.apitest.model.po.CaseVar;
import com.we.apiautomation.apitest.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TTestcaseApiServiceImpl implements TTestcaseApiService {

    @Resource
    TTestcaseApiDtoMapper tTestcaseApiDtoMapper;
    @Resource
    TTestcaseApiMapper tTestcaseApiMapper;
    @Resource
    private TApiService apiService;
    @Resource
    private GlobalParamService globalParamService;

    @Override
    public List<TestcaseApiDto> selectDtoBySuiteId(Long id) {
        return tTestcaseApiDtoMapper.selectDtoBySuiteId(id);
    }

    @Override
    public PageInfo<TTestcaseApi> findByAllwithPage(int pageNum,int pageSize,TTestcaseApi tTestcaseApi){

        PageHelper.startPage(pageNum,pageSize);
        List<TTestcaseApi> mapper = tTestcaseApiMapper.findByAll(tTestcaseApi);
        System.out.println(mapper.toString());
        return new PageInfo<>(mapper);

    }

    @Override
    public TApiCaseResultDto excCase(TestcaseApiDto testcaseApi) {
        Map<String, Object> gVars = globalParamService.findByProjectIdAndTypeAndEnvId(testcaseApi.getProjectId(), 2, testcaseApi.getEnvId());
        Map<String, Object> caseVars = new ConcurrentHashMap<>();

        TApiCaseResultDto tApiCaseResult = new TApiCaseResultDto();
        tApiCaseResult.setCreateTime(new Date());
        tApiCaseResult.setCaseId(testcaseApi.getId());
        tApiCaseResult.setCaseName(testcaseApi.getName());
        tApiCaseResult.setStatus(0);
        List<CaseVar> caseVarList = testcaseApi.getCaseVars();
        if (caseVarList != null) {
            for (CaseVar caseVar : caseVarList) {
                caseVars.put(caseVar.getName(), caseVar.getValue());
            }
        }
        List<StepApiDto> testSteps = testcaseApi.getTestSteps();
        List<TApiResult> stepResults = new ArrayList();
        int success = 0;
        int failed = 0;
        int skipped = 0;
        if (testSteps == null) {
            return tApiCaseResult;
        }
        for (StepApiDto stepApiDto : testSteps) {
            if (stepApiDto.getStatus() == 0) {
                continue;
            }
            TApi tApi = stepApiDto.getApi();

            tApi.setEnvId(testcaseApi.getEnvId());
            tApi.setBeforeExcs(stepApiDto.getBeforeExcs());
            tApi.setReqAssert(stepApiDto.getReqAssert());
            tApi.setReqExtract(stepApiDto.getReqExtract());
//TODO添加用例的bodujson
            if (null !=stepApiDto.getReqBodyJson()){
                tApi.setReqBodyJson(stepApiDto.getReqBodyJson());
            }
            if (null !=stepApiDto.getReqHeader()){
                tApi.setReqHeader(stepApiDto.getReqHeader());
            }
            if (null !=stepApiDto.getReqQuery()){
                tApi.setReqQuery(stepApiDto.getReqQuery());
            }


            TApiResult tApiResult = apiService.excApi(tApi, gVars, caseVars, stepApiDto.getApiParams());
            if (tApiResult.getResultType().equals(1)) {
                success = success + 1;
            } else {
                failed = failed + 1;
                tApiCaseResult.setStatus(1);
            }
            tApiResult.setStepId(stepApiDto.getId());
            tApiResult.setStepName(stepApiDto.getName());
            stepResults.add(tApiResult);
        }

        tApiCaseResult.setTotal(testSteps.size());
        tApiCaseResult.setSuccess(success);
        tApiCaseResult.setFailed(failed);
        tApiCaseResult.setSkipped(skipped);
        tApiCaseResult.setSteps(stepResults);
        tApiCaseResult.setEndTime(new Date());
        return tApiCaseResult;
    }

}




