package com.we.apiautomation.apitest.model;

import com.we.apiautomation.apitest.model.po.Action;
import com.we.apiautomation.apitest.model.po.CaseVar;
import com.we.apiautomation.apitest.model.po.Param;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TTestcaseApi implements Serializable {
    private Long id;

    private String name;

    /**
     * 环境id
     */
    private Long envId;

    /**
     * 前置执行器
     */
    private List<Action> beforeExcs;

    private Long projectId;

    /**
     * 超时时间，单位分
     */
    private Long timoutTime;

    /**
     * 失败了是否继续0：不继续，1：继续
     */
    private Integer failContinue;

    /**
     * 标签
     */
    private String flags;

    /**
     * 业务名参数
     */
    private List<Param> params;

    /**
     * 用例内部参数
     */
    private List<CaseVar> caseVars;

    /**
     * 1,用例、2：业务
     */
    private Integer caseType;

    /**
     * 备注
     */
    private String remark;

    private String createBy;

    private String updateBy;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
