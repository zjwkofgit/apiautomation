package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.CaseVar;

public class CaseVarListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return CaseVar.class;
    }
}
