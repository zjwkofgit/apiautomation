package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.AssertResult;

public class AssertResultListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return AssertResult.class;
    }
}
