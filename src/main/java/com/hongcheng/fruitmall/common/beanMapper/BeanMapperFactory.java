package com.hongcheng.fruitmall.common.beanMapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BeanMapperFactory {
	static MapperFactory factory;

	static synchronized private void initialize() {
		if (factory == null) {
			factory = new DefaultMapperFactory.Builder().build();
			ConverterFactory converterFactory = factory.getConverterFactory();
			converterFactory.registerConverter(new LocalDateConverter());
			converterFactory.registerConverter(new LocalTimeConverter());
			converterFactory.registerConverter(new LocalDateTimeConverter());
//			converterFactory.registerConverter(new B2IConverter());
			converterFactory.registerConverter(new DateAndDateTimeConvert());
		}

	}

	static public MapperFacade getMapperFacade() {
		if (factory == null) {
			initialize();
		}

		return factory.getMapperFacade();
	}
}
