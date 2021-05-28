package com.we.apiautomation.apitest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;


/**
 * 定时任务调度日志表 sys_job_log
 */
@Data
public class JobLog
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long jobLogId;

    /** jobID */
    private Long jobId;

    /** 任务名称 */
    private String jobName;

    /** 任务组名 */
    private String jobGroup;

    /** 任务方法 */
    private String methodName;

    /** 方法参数 */
    private String methodParams;

    /** 日志信息 */
    private String jobMessage;

    /** 执行状态（0正常 1失败） */
    private String status;

    /** 异常信息 */
    private String exceptionInfo;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;


    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;
}
