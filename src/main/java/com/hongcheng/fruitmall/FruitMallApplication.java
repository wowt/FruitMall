package com.hongcheng.fruitmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.TimeZone;

@ComponentScan("com.hongcheng.fruitmall")
@ImportResource("classpath:applicationContext.xml")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class FruitMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitMallApplication.class, args);
	}
}
