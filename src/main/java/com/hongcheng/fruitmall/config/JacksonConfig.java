package com.hongcheng.fruitmall.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@SpringBootConfiguration
public class JacksonConfig {

    @Bean(name = "jacksonObjectMapper")
    public ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();

        // 设置配置
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 设置模块
        om.registerModule(new JavaTimeModule()
                .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_DATE))
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        return om;
    }
}
