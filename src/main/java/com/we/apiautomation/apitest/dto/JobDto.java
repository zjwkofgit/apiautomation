package com.we.apiautomation.apitest.dto;

import com.we.apiautomation.apitest.model.Job;
import com.we.apiautomation.apitest.model.JobParams;
import lombok.Data;

import java.util.List;

@Data
public class JobDto extends Job {
    private static final long serialVersionUID = 1L;

    private List<TPlanSuiteApiDto> apiSuiteList;
    private JobParams jobParams;//请求参数
}
