package com.we.apiautomation.apitest.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.we.apiautomation.apitest.dao.TStepApiMapper;
import com.we.apiautomation.apitest.dao.TTestcaseApiDtoMapper;
import com.we.apiautomation.apitest.dao.TTestcaseApiMapper;
import com.we.apiautomation.apitest.dto.StepApiDto;
import com.we.apiautomation.apitest.dto.TApiCaseResultDto;
import com.we.apiautomation.apitest.dto.TestcaseApiDto;
import com.we.apiautomation.apitest.model.*;
import com.we.apiautomation.apitest.model.po.CaseVar;
import com.we.apiautomation.apitest.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TTestcaseApiServiceImpl implements TTestcaseApiService {

    @Resource
    TTestcaseApiDtoMapper tTestcaseApiDtoMapper;

    @Override
    public List<TestcaseApiDto> selectDtoBySuiteId(Long id) {
        return tTestcaseApiDtoMapper.selectDtoBySuiteId(id);
    }
}




