package com.hongcheng.fruitmall.common.beanMapper;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateAndDateTimeConvert extends BidirectionalConverter<LocalDate,LocalDateTime> {

    @Override
    public LocalDateTime convertTo(LocalDate localDate, Type<LocalDateTime> type) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    @Override
    public LocalDate convertFrom(LocalDateTime localDateTime, Type<LocalDate> type) {
        return localDateTime.toLocalDate();
    }
}
