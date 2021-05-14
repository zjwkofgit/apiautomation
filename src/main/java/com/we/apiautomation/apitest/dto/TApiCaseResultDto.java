package com.we.apiautomation.apitest.dto;

import com.we.apiautomation.apitest.model.TApiCaseResult;
import com.we.apiautomation.apitest.model.TApiResult;
import lombok.Data;

import java.util.List;

@Data
public class TApiCaseResultDto extends TApiCaseResult {
    private static final long serialVersionUID = 1L;
    private List<TApiResult> steps;
}
