package com.we.apiautomation.apitest.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TPlanSuiteApi implements Serializable {
    private Long id;

    private Long jobId;

    private Long suiteId;

    private Integer sort;

    /**
     * 1:有效，2：无效
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}
