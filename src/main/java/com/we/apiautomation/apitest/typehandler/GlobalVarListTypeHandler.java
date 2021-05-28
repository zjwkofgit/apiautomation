package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.GlobalVar;

public class GlobalVarListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return GlobalVar.class;
    }
}
