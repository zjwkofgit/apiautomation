package com.we.apiautomation.apitest.service.Impl;

import com.we.apiautomation.apitest.dao.TApiCaseResultMapper;
import com.we.apiautomation.apitest.dao.TApiResultMapper;
import com.we.apiautomation.apitest.dao.TPlanSuiteApiMapper;
import com.we.apiautomation.apitest.dao.TTestsuiteApiResultMapper;
import com.we.apiautomation.apitest.dto.TApiCaseResultDto;
import com.we.apiautomation.apitest.dto.TPlanSuiteApiDto;
import com.we.apiautomation.apitest.dto.TestcaseApiDto;
import com.we.apiautomation.apitest.model.*;
import com.we.apiautomation.apitest.service.ExcApiService;
import com.we.apiautomation.apitest.service.TTestPlanResultApiService;
import com.we.apiautomation.apitest.service.TTestcaseApiService;
import com.we.apiautomation.apitest.utils.FastJSONHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ExcApiServiceImpl implements ExcApiService {
//
//    @Override
//    public String excJob(Job job) {
//        log.info("开始执行接口自动化任务,测试自动化计划日志");
//
//        System.out.println("打架>>>>>>>"+job.getJobName());
//
//        return "执行接口自动化任务结束";
//    }


    @Resource
    private TTestPlanResultApiService planResultApiService;
    @Resource
    private TPlanSuiteApiMapper planSuiteApiMapper;
    @Resource
    private TTestcaseApiService testcaseApiService;
    @Resource
    private TTestsuiteApiResultMapper testsuiteApiResultMapper;
    @Resource
    private TApiCaseResultMapper apiCaseResultMapper;
    @Resource
    private TApiResultMapper apiResultMapper;





    @Override
    public String excJob(Job job) {
        JobParams jobParams = FastJSONHelper.deserialize(job.getMethodParams(), JobParams.class);
        log.info("开始执行接口自动化任务,测试自动化计划日志");
        TTestPlanResultApi planResultApi = new TTestPlanResultApi();
        planResultApi.setName(job.getJobGroup());
        planResultApi.setCreateTime(new Date());
        planResultApi.setJobId(job.getJobId());
        planResultApi.setStatus(1);
        planResultApi.setProjectId(job.getProjectId());
        planResultApiService.insertSelective(planResultApi);
        log.info("调用执行机执行用例");
        excApiPlan(job.getJobId(), planResultApi, jobParams.getEnvId());
        if (planResultApi.getStatus().equals(0)) {
            planResultApi.setRemark("执行完毕");
            planResultApi.setStatus(2);
        } else {
            planResultApi.setRemark("执行结果失败");
            planResultApi.setStatus(5);
        }
        planResultApi.setEndTime(new Date());
        planResultApiService.updateByPrimaryKeySelective(planResultApi);

        return "执行接口自动化任务结束";
    }

    private void excApiPlan(Long jobId, TTestPlanResultApi planResultApi, Long envId) {
        planResultApi.setStatus(0);
        List<TPlanSuiteApiDto> byJobId = planSuiteApiMapper.findDtoByJobId(jobId);
        if (byJobId == null) {
            return;
        }
        int succCount = 0;
        int failCount = 0;
        for (TPlanSuiteApiDto planSuiteApi : byJobId) {
            TTestsuiteApiResult testsuiteApiResult = new TTestsuiteApiResult();
            testsuiteApiResult.setPlanLogId(planResultApi.getId());
            testsuiteApiResult.setSuiteId(planSuiteApi.getSuiteId());
            testsuiteApiResult.setSuiteName(planSuiteApi.getSuiteName());
            testsuiteApiResult.setCreateTime(new Date());
            testsuiteApiResultMapper.insertSelective(testsuiteApiResult);
            excApiSuite(planSuiteApi.getSuiteId(), testsuiteApiResult, envId);
            if (testsuiteApiResult.getStatus().equals(0)) {
                succCount = succCount + 1;
            } else {
                failCount = failCount + 1;
                planResultApi.setStatus(1);
            }
            testsuiteApiResult.setEndTime(new Date());
            testsuiteApiResultMapper.updateByPrimaryKeySelective(testsuiteApiResult);
        }
        planResultApi.setSuiteTotalCount(byJobId.size());
        planResultApi.setSuiteSuccCount(succCount);
        planResultApi.setSuiteFailCount(failCount);
    }

    private void excApiSuite(Long suiteId, TTestsuiteApiResult testsuiteApiResult, Long envId) {
        testsuiteApiResult.setStatus(0);
        List<TestcaseApiDto> bySuiteId = testcaseApiService.selectDtoBySuiteId(suiteId);
        if (bySuiteId == null) {
            return;
        }
        int succCount = 0;
        int failCount = 0;
        for (TestcaseApiDto testcaseApi : bySuiteId) {
            Date starDate = new Date();
            testcaseApi.setEnvId(envId);
            TApiCaseResultDto caseResultDto = testcaseApiService.excCase(testcaseApi);
            caseResultDto.setSuiteResultId(testsuiteApiResult.getId());
            caseResultDto.setCreateTime(starDate);
            if (caseResultDto.getStatus().equals(0)) {
                succCount = succCount + 1;
            } else {
                failCount = failCount + 1;
                testsuiteApiResult.setStatus(1);
            }
            caseResultDto.setPlanLogId(testsuiteApiResult.getPlanLogId());
            caseResultDto.setSuiteId(testsuiteApiResult.getSuiteId());
            caseResultDto.setSuiteName(testsuiteApiResult.getSuiteName());
            apiCaseResultMapper.insertSelective(caseResultDto);
            List<TApiResult> steps = caseResultDto.getSteps();
            for (TApiResult tApiResult : steps) {
                tApiResult.setCaseResultId(caseResultDto.getId());
                tApiResult.setPlanLogId(testsuiteApiResult.getPlanLogId());
                apiResultMapper.insertSelective(tApiResult);
            }
        }
        testsuiteApiResult.setCaseTotalCount(bySuiteId.size());
        testsuiteApiResult.setCaseSuccCount(succCount);
        testsuiteApiResult.setCaseFailCount(failCount);
    }




}
