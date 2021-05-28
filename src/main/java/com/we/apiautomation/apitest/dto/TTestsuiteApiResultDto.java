package com.we.apiautomation.apitest.dto;

import com.we.apiautomation.apitest.model.TApiCaseResult;
import com.we.apiautomation.apitest.model.TTestsuiteApiResult;
import lombok.Data;

import java.util.List;

@Data
public class TTestsuiteApiResultDto extends TTestsuiteApiResult {
    private static final long serialVersionUID = 1L;
    private List<TApiCaseResult> caseResults;
}
