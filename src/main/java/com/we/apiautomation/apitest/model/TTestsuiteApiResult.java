package com.we.apiautomation.apitest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TTestsuiteApiResult implements Serializable {
    private Long id;

    /**
     * 日志id
     */
    private Long planLogId;

    /**
     * 用例集id
     */
    private Long suiteId;

    /**
     * 用例集名称
     */
    private String suiteName;

    /**
     * 执行状态 0成功 1失败 2跳过
     */
    private Integer status;

    /**
     * 用例总数
     */
    private Integer caseTotalCount;

    /**
     * 成功数
     */
    private Integer caseSuccCount;

    /**
     * 失败数
     */
    private Integer caseFailCount;

    /**
     * 跳过数
     */
    private Integer caseSkipCount;

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
