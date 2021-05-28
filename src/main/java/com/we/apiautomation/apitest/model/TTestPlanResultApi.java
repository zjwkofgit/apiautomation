package com.we.apiautomation.apitest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TTestPlanResultApi implements Serializable {
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务id
     */
    private Long jobId;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 状态 0未执行 1执行中 2执行完成 3任务超时中断 4连接客户端失败, 5执行失败
     */
    private Integer status;

    /**
     * 用例集总数
     */
    private Integer suiteTotalCount;

    /**
     * 成功数
     */
    private Integer suiteSuccCount;

    /**
     * 失败数
     */
    private Integer suiteFailCount;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * createTime
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
