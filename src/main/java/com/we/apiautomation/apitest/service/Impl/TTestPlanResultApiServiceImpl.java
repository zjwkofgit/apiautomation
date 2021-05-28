package com.we.apiautomation.apitest.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.we.apiautomation.apitest.dao.TApiCaseResultMapper;
import com.we.apiautomation.apitest.dao.TApiResultMapper;
import com.we.apiautomation.apitest.dao.TTestPlanResultApiMapper;
import com.we.apiautomation.apitest.dao.TTestsuiteApiResultMapper;
import com.we.apiautomation.apitest.model.TTestPlanResultApi;
import com.we.apiautomation.apitest.service.TTestPlanResultApiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TTestPlanResultApiServiceImpl implements TTestPlanResultApiService {

    @Resource
    private TTestPlanResultApiMapper tTestPlanResultApiMapper;
    @Resource
    private TTestsuiteApiResultMapper testsuiteApiResultMapper;
    @Resource
    private TApiCaseResultMapper apiCaseResultMapper;
    @Resource
    private TApiResultMapper apiResultMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        apiResultMapper.deleteByPlanLogId(id);
        apiCaseResultMapper.deleteByPlanLogId(id);
        testsuiteApiResultMapper.deleteByPlanLogId(id);
        return tTestPlanResultApiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestPlanResultApi record) {
        return tTestPlanResultApiMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestPlanResultApi record) {
        return tTestPlanResultApiMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestPlanResultApi record) {
        return tTestPlanResultApiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestPlanResultApi record) {
        return tTestPlanResultApiMapper.updateByPrimaryKey(record);
    }

    @Override
    public TTestPlanResultApi selectByPrimaryKey(Long id) {
        return tTestPlanResultApiMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<TTestPlanResultApi> findByAllwithPage(int page, int pageSize, TTestPlanResultApi tTestPlanResultApi) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tTestPlanResultApiMapper.findByAll(tTestPlanResultApi));
    }
}


