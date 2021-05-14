package com.we.apiautomation.apitest.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class BodyData implements Serializable {
    private String key;
    private String type;
    private String value;
    private String remark;
    private static final long serialVersionUID = 1L;
}
