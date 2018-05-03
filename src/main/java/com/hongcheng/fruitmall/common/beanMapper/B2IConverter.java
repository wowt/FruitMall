package com.hongcheng.fruitmall.common.beanMapper;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class B2IConverter  extends CustomConverter<Boolean,Integer> {
    @Override
    public Integer convert(Boolean source, Type<? extends Integer> destinationType) {
        return source ? 1 : 0;
    }
}
