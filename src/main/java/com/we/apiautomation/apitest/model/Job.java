package com.we.apiautomation.apitest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.we.apiautomation.apitest.common.constant.ScheduleConstants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度信息 sys_job
 */
@Data
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long jobId;//任务ID
    private String jobName;//任务名称
    private String jobGroup;//任务组名
    private String methodName;//任务方法
    private String methodParams;//方法参数
    private String cronExpression;//cron执行表达式
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;//cron计划策略
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间
    private String updateBy;//更新者
    private String status;//任务状态（0正常 1暂停）
    private String createBy;//创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;//更新时间
    private String remark;//备注
    /** 请求参数 */
    private Long projectId;//    项目名称
    private Integer jobType; //任务类型0系统 1 UI自动化 2 APP自动化 3接口自动化 4 UI自动化新
}
