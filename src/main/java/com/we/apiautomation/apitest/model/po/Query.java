package com.we.apiautomation.apitest.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Query implements Serializable {
    private String key;
    private String value;
    private static final long serialVersionUID = 1L;
}
