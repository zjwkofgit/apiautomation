package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.Param;

public class ParamListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Param.class;
    }
}
