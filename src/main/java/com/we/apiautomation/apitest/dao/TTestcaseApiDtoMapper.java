package com.we.apiautomation.apitest.dao;

import com.we.apiautomation.apitest.dto.TestcaseApiDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TTestcaseApiDtoMapper {

    List<TestcaseApiDto> selectDtoBySuiteId(Long id);
}
