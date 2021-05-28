package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.BodyData;

public class BodyDataListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return BodyData.class;
    }
}
