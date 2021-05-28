package com.we.apiautomation.apitest.dto;

import com.we.apiautomation.apitest.model.TPlanSuiteApi;
import lombok.Data;

@Data
public class TPlanSuiteApiDto extends TPlanSuiteApi {
	private static final long serialVersionUID = 1L;
	private String suiteName;
}
