package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.ApiParam;

public class ApiParamListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return ApiParam.class;
    }
}
