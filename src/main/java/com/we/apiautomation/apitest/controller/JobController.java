package com.we.apiautomation.apitest.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import com.we.apiautomation.apitest.dto.JobDto;
import com.we.apiautomation.apitest.model.ErrorInfo;
import com.we.apiautomation.apitest.model.Job;
import com.we.apiautomation.apitest.model.ResponseInfo;
import com.we.apiautomation.apitest.service.IJobService;
import com.we.apiautomation.apitest.service.TPlanSuiteApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调度任务信息操作处理
 */
@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private IJobService jobService;

//    @Autowired
//    private TTestsuiteUiService tTestsuiteUiService;

    @Resource
    private TPlanSuiteApiService planSuiteApiService;

    @GetMapping("/list")
    public ResponseInfo list(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        Job job = JSONObject.toJavaObject(jsonObject, Job.class);
        PageInfo pageInfo = jobService.selectJobListPage(pageNum, pageSize, job);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByCustomer")
    public ResponseInfo listByCustomer(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        Job job = JSONObject.toJavaObject(jsonObject, Job.class);
        PageInfo pageInfo = jobService.selectCustomJobListPage(pageNum, pageSize, job);
        return new ResponseInfo(true, pageInfo);
    }


    @PostMapping("/export")
    public ResponseInfo export(Job job) {
        List<Job> list = jobService.selectJobList(job);
//        ExcelUtil<Job> util = new ExcelUtil<Job>(Job.class);
//        return util.exportExcel(list, "定时任务");
        return new ResponseInfo(true, list);
    }

    @PostMapping("/remove")
    @Transactional
    public ResponseInfo remove(@RequestBody JobDto job) {
        jobService.deleteJob(job);
        return new ResponseInfo(true, "删除成功");
    }

    @GetMapping("/detail/{jobId}")
    public ResponseInfo detail(@PathVariable("jobId") Long jobId) {
        JobDto job = jobService.selectJobById(jobId);
        return new ResponseInfo(true, job);
    }

    /**
     * 任务调度状态修改
     */
    @PostMapping("/changeStatus")
    public ResponseInfo changeStatus(@RequestBody Job job) {
        jobService.changeStatus(job);
        return new ResponseInfo(true, "修改成功");
    }

    /**
     * 任务调度立即执行一次
     */
    @PostMapping("/run")
    public ResponseInfo run(@RequestBody Job job) {
        jobService.run(job);
        return new ResponseInfo(true, "执行成功");
    }


    /**
     * 新增保存调度
     */
    @PostMapping("/add")
    public ResponseInfo addSave(@RequestBody JobDto job) {
        if (!jobService.checkCronExpressionIsValid(job.getCronExpression())) {
            return new ResponseInfo(false, new ErrorInfo(300, "表达式错误"));
        }
        jobService.insertJobCron(job);
        if (job.getJobType() != null && job.getJobType().equals(4)) {
//            tTestsuiteUiService.addUiSuiteToPlan(job);
        } else if (job.getJobType() != null && job.getJobType().equals(3)) {
            planSuiteApiService.addApiSuiteToPlan(job);
        }
        return new ResponseInfo(true, "新增保存调度成功");
    }


    /**
     * 修改保存调度
     */
    @PutMapping("/edit")
    @Transactional
    public ResponseInfo editSave(@RequestBody JobDto job) {
        if (!jobService.checkCronExpressionIsValid(job.getCronExpression())) {
            return new ResponseInfo(false, new ErrorInfo(520, "表达式错误"));
        }
        log.info("修改定时任务==={}", job);
        if (job.getJobType() != null && job.getJobType().equals(4)) {
//            tTestsuiteUiService.addUiSuiteToPlan(job);
        } else if (job.getJobType() != null && job.getJobType().equals(3)) {
            planSuiteApiService.addApiSuiteToPlan(job);
        }
        jobService.updateJobCron(job);
        return new ResponseInfo(true, "修改保存调度成功");
    }

    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    public ResponseInfo checkCronExpressionIsValid(@RequestBody Job job) {
        return new ResponseInfo(true, jobService.checkCronExpressionIsValid(job.getCronExpression()));
    }
}
