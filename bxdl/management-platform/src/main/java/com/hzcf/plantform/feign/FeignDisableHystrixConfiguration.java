package com.hzcf.plantform.feign;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;

/**
 *<dl>
 *<dt>类名：FeignDisableHystrixConfiguration.java</dt>
 *<dd>描述: 禁用Hystrix配置类</dd> 
 *<dd>创建时间： 2018年8月13日 下午5:22:08</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2018年8月13日 下午5:22:08    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Configuration
public class FeignDisableHystrixConfiguration {

	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuild(){
		return Feign.builder();
	}
}
