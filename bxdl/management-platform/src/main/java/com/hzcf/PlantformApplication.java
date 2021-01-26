package com.hzcf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.tobato.fastdfs.FdfsClientConfig;
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableScheduling
@Import(FdfsClientConfig.class)
public class PlantformApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlantformApplication.class, args);
	}
	
}