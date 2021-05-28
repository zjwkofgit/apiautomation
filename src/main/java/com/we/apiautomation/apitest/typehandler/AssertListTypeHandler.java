package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.Assert;

public class AssertListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Assert.class;
    }
}
