package com.we.apiautomation.apitest.dto;

import com.we.apiautomation.apitest.model.TTestcaseApi;
import lombok.Data;

import java.util.List;

@Data
public class TestcaseApiDto extends TTestcaseApi {
    private static final long serialVersionUID = 1L;
    private List<StepApiDto> testSteps;
    private Long suiteId;
    private Long suiteCaseId;
    private Integer sort;//排序
}
