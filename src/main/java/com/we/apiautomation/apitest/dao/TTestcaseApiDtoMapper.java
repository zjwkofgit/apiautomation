package com.we.apiautomation.apitest.dao;

import com.we.apiautomation.apitest.dto.TestcaseApiDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TTestcaseApiDtoMapper {

    List<TestcaseApiDto> selectDtoBySuiteId(Long id);
}
