package com.hzcf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.hzcf.core.mapper")
public class CorebusinessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorebusinessServiceApplication.class, args);
    }

}
