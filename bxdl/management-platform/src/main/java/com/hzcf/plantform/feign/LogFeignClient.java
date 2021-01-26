package com.hzcf.plantform.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.mongo.Logs;
import com.hzcf.plantform.util.PageModel;


/**
*<dl>
*<dt>类名：LogFeignClient.java</dt>
*<dd>描述: 日志管理feign</dd>
*<dd>创建时间： 2018年11月14日 下午2:20:18</dd>
*<dd>创建人：tie</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2018年11月14日 下午2:20:18 tie 1.0 1.0 Version
* </pre>
*</dl>
*/
@FeignClient(name = "basic-microservice", configuration=FeignDisableHystrixConfiguration.class)
public interface  LogFeignClient {

	@RequestMapping(value = "/apiLog/getLogList/", method = RequestMethod.POST)
	PageModel getLogList(@RequestParam Map<String, Object> paramsCondition);


	  
	  
	  
}
