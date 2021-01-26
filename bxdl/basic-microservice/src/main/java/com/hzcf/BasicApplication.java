package com.hzcf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.hzcf.basic.mapper")//扫描：该包下相应的class,主要是MyBatis的持久化类.
public class BasicApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
}



