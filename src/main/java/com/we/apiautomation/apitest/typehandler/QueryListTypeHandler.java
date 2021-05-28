package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.Query;

public class QueryListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Query.class;
    }
}
