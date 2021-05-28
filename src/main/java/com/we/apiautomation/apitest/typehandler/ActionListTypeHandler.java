package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.Action;

public class ActionListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Action.class;
    }
}
