package com.hzcf.api.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.show}")
    private boolean swaggerShow;
	@Bean
	public Docket createRestApi() {
		List<springfox.documentation.service.Parameter> pars = new ArrayList<>();
		ParameterBuilder channelPar = new ParameterBuilder();
		channelPar.name("channel").description("请求渠道").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
		ParameterBuilder timeStamp = new ParameterBuilder();
		timeStamp.name("timeStamp").description("时间戳").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
		pars.add(channelPar.build());
		pars.add(timeStamp.build());
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(swaggerShow)
				.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.hzcf.api.controller"))
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
				.build().globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("基础微服务api").description("基础微服务接口文档说明")
				.version("1.0").build();
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration(null, "list", "alpha", "schema", UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
				false, true, 60000L);
	}
}