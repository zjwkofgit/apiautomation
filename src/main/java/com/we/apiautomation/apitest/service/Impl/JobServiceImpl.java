package com.we.apiautomation.apitest.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.common.constant.ScheduleConstants;
import com.we.apiautomation.apitest.dao.JobMapper;
import com.we.apiautomation.apitest.dao.TPlanSuiteApiMapper;
import com.we.apiautomation.apitest.dto.JobDto;
import com.we.apiautomation.apitest.model.Job;
import com.we.apiautomation.apitest.service.IJobService;
import com.we.apiautomation.apitest.service.TPlanSuiteApiService;
import com.we.apiautomation.apitest.utils.Convert;
import com.we.apiautomation.apitest.utils.CronUtils;
import com.we.apiautomation.apitest.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务调度信息 服务层
 */
@Service
public class JobServiceImpl implements IJobService {
    @Qualifier("schedulerFactoryBean")
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobMapper jobMapper;


    @Resource
    private TPlanSuiteApiMapper planSuiteApiMapper;


    @Resource
    private TPlanSuiteApiService planSuiteApiService;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<Job> jobList = jobMapper.selectJobAll();
        for (Job job : jobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, job.getJobId());
            // 如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, job);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, job);
            }
        }
    }

    /**
     * 获取quartz调度器的计划任务列表
     *
     * @param job 调度信息
     * @return
     */
    @Override
    public List<Job> selectJobList(Job job) {
        return jobMapper.selectJobList(job);
    }

    @Override
    public PageInfo selectJobListPage(int pageNum, int pageSize, Job job) {
        PageHelper.startPage(pageNum, pageSize);
        List<Job> list = jobMapper.selectJobList(job);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public PageInfo selectCustomJobListPage(int pageNum, int pageSize, Job job) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobDto> list = jobMapper.selectCustomJobList(job);
        PageInfo page = new PageInfo(list);
        return page;
    }

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public JobDto selectJobById(Long jobId) {
        JobDto jobDto = jobMapper.selectJobById(jobId);
        if (jobDto.getJobType().equals(4)) {
//            jobDto.setSuiteList(testsuiteUiMapper.findDtoByJobId(jobId));
        } else if (jobDto.getJobType().equals(3)) {
            jobDto.setApiSuiteList(planSuiteApiMapper.findDtoByJobId(jobId));
        }
        return jobDto;
    }

    /**
     * 暂停任务
     *
     * @param job 调度信息
     */
    @Override
    public int pauseJob(Job job) {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
//        job.setUpdateBy(UserUtil.getLoginUser().getUsername());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            ScheduleUtils.pauseJob(scheduler, job.getJobId());
        }
        return rows;
    }

    /**
     * 恢复任务
     *
     * @param job 调度信息
     */
    @Override
    public int resumeJob(Job job) {
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
//        job.setUpdateBy(UserUtil.getLoginUser().getUsername());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            ScheduleUtils.resumeJob(scheduler, job.getJobId());
        }
        return rows;
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     */
    @Override
    @Transactional
    public int deleteJob(Job job) {
        if (job.getJobType() != null && job.getJobType().equals(4)) {
//            planSuiteUiMapper.deleteByJobId(job.getJobId());
        } else if (job.getJobType() != null && job.getJobType().equals(3)) {
            planSuiteApiService.deleteByJobId(job.getJobId());
        }
        int rows = jobMapper.deleteJobById(job.getJobId());
        if (rows > 0) {
            ScheduleUtils.deleteScheduleJob(scheduler, job.getJobId());
        }
        return rows;
    }

    /**
     * 批量删除调度信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public void deleteJobByIds(String ids) {
        Long[] jobIds = Convert.toLongArray(ids);
        for (Long jobId : jobIds) {
            Job job = jobMapper.selectJobById(jobId);
            deleteJob(job);
        }
    }

    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     */
    @Override
    public int changeStatus(Job job) {
        int rows = 0;
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
            rows = resumeJob(job);
        } else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
            rows = pauseJob(job);
        }
        return rows;
    }

    /**
     * 立即运行任务

     */
    @Override
    public int run(Job job) {
        return ScheduleUtils.run(scheduler, selectJobById(job.getJobId()));
    }

    /**
     * 新增任务
     *
     * @param job 调度信息 调度信息
     */
    @Override
    public int insertJobCron(Job job) {
//        job.setCreateBy(UserUtil.getLoginUser().getUsername());
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.insertJob(job);
        if (rows > 0) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }

    /**
     * 更新任务的时间表达式
     *
     * @param job 调度信息
     */
    @Override
    public int updateJobCron(Job job) {
//        job.setUpdateBy(UserUtil.getLoginUser().getUsername());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            ScheduleUtils.updateScheduleJob(scheduler, job);
        }
        return rows;
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }


}
