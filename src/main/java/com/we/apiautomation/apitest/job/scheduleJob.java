package com.we.apiautomation.apitest.job;

import com.we.apiautomation.apitest.common.constant.Constants;
import com.we.apiautomation.apitest.common.constant.ScheduleConstants;
import com.we.apiautomation.apitest.model.Job;
import com.we.apiautomation.apitest.model.JobLog;
import com.we.apiautomation.apitest.service.IJobLogService;
import com.we.apiautomation.apitest.utils.BeanUtils;
import com.we.apiautomation.apitest.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class scheduleJob extends QuartzJobBean {
//    private static final Logger log = LoggerFactory.getLogger(ScheduleJob.class);

    private ThreadPoolTaskExecutor executor = SpringUtils.getBean("threadPoolTaskExecutor");

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Job job = new Job();
        BeanUtils.copyBeanProp(job, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));

        IJobLogService jobLogService = (IJobLogService) SpringUtils.getBean(IJobLogService.class);

        JobLog jobLog = new JobLog();
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setMethodName(job.getMethodName());
        jobLog.setMethodParams(job.getMethodParams());
        jobLog.setJobId(job.getJobId());
        jobLog.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            log.info("任务开始执行 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
//            ScheduleRunnable task = new ScheduleRunnable(job.getJobName(), job.getMethodName(), job.getMethodParams() );
            ScheduleRunnable task = new ScheduleRunnable( job);
            Future<?> future = executor.submit(task);
//            future.get();
            //任务处理超时时间设为 2个小时
            future.get(1000 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
            long times = System.currentTimeMillis() - startTime;
            // 任务状态 0：成功 1：失败
            jobLog.setStatus(Constants.SUCCESS);
            jobLog.setJobMessage(job.getJobName() + " 总共耗时：" + times / 1000 + "秒");

            log.info("任务执行结束 - 名称：{} 耗时：{} 毫秒", job.getJobName(), times);
        } catch (TimeoutException e) {
            log.info("任务执行失败 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            log.error("任务执行异常  - ：", e);
            long times = System.currentTimeMillis() - startTime;
            jobLog.setJobMessage(job.getJobName() + " 总共耗时：" + times + "毫秒");
            // 任务状态 0：成功 1：失败
            jobLog.setStatus(Constants.FAIL);
            jobLog.setExceptionInfo("执行任务超时");
        } catch (Exception e) {
            log.info("任务执行失败 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            log.error("任务执行异常  - ：", e);
            long times = System.currentTimeMillis() - startTime;
            jobLog.setJobMessage(job.getJobName() + " 总共耗时：" + times + "毫秒");
            // 任务状态 0：成功 1：失败
            jobLog.setStatus(Constants.FAIL);
            jobLog.setExceptionInfo(StringUtils.substring(e.getMessage(), 0, 2000));
        } finally {
            jobLogService.addJobLog(jobLog);
        }
    }
}
