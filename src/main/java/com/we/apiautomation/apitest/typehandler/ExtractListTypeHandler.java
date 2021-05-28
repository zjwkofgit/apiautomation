package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.Extract;

public class ExtractListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Extract.class;
    }
}
