package com.we.apiautomation.apitest.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TTestsuiteApi implements Serializable {
    private Long id;

    private String name;

    private Long projectId;

    private String createBy;

    private String updateBy;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
