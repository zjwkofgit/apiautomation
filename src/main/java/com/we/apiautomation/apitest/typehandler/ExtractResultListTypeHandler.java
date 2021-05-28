package com.we.apiautomation.apitest.typehandler;


import com.we.apiautomation.apitest.model.po.ExtractResult;

public class ExtractResultListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return ExtractResult.class;
    }
}
