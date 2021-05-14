package com.we.apiautomation.apitest.model;

import com.we.apiautomation.apitest.model.po.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TStepApi implements Serializable {
    private Long id;

    /**
     * 步骤名称
     */
    private String name;

    /**
     * 1:api
     */
    private Integer type;

    /**
     * 用例id
     */
    private Long testcaseId;

    /**
     * type为1则为api_id
     */
    private Long sourceId;

    /**
     * 状态：1启用 0禁用
     */
    private Integer status;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求的协议+域名
     */
    private String domain;

    /**
     * 请求路径
     */
    private String path;

    /**
     * api请求参数
     */
    private List<ApiParam> apiParams;

    /**
     * 前置执行器
     */
    private List<Action> beforeExcs;

    /**
     * 请求头
     */
    private List<Header> reqHeader;

    /**
     * 请求query
     */
    private List<Query> reqQuery;

    /**
     * 请求body from-data格式
     */
    private List<BodyData> reqBodyData;

    /**
     * 请求body json格式
     */
    private String reqBodyJson;

    /**
     * 请求body的类型
     */
    private String reqBodyType;

    /**
     * 请求提取参数
     */
    private List<Extract> reqExtract;

    /**
     * 请求断言
     */
    private List<Assert> reqAssert;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}
