package com.we.apiautomation.apitest.service.Impl;

import com.we.apiautomation.apitest.model.TSuiteCaseApi;
import com.we.apiautomation.apitest.service.TSuiteCaseApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TSuiteCaseApiServiceImpl implements TSuiteCaseApiService {

    @Override
    public void addCaseToSuite(List<TSuiteCaseApi> tSuiteCaseApis) {
        Integer maxSortBySuiteId = tSuiteCaseApiMapper.findMaxSortBySuiteId(tSuiteCaseApis.get(0).getSuiteId());
        int sort = 0;
        if (maxSortBySuiteId != null) {
            sort = maxSortBySuiteId + 1;
        }
        for (TSuiteCaseApi tSuiteCaseUi : tSuiteCaseApis) {
            tSuiteCaseUi.setSort(sort);
            tSuiteCaseApiMapper.insertSelective(tSuiteCaseUi);
            sort+++;
        }
    }
}
