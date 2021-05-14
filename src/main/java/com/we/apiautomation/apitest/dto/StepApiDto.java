package com.we.apiautomation.apitest.dto;

import com.we.apiautomation.apitest.model.TApi;
import com.we.apiautomation.apitest.model.TStepApi;
import lombok.Data;

@Data
public class StepApiDto extends TStepApi {
    private static final long serialVersionUID = 1L;
    private TApi api;
}
