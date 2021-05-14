package com.we.apiautomation.apitest.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Assert implements Serializable {
    private String dataSource;
    private String extractType;
    private String extractExpress;
    private String expectRelation;
    private String expectValue;
    private Integer regexNo;
    private static final long serialVersionUID = 1L;
}
