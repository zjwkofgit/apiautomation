package com.we.apiautomation.apitest.typehandler;

import com.we.apiautomation.apitest.model.po.Header;

public class HeaderListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Header.class;
    }
}
