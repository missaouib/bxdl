package com.hzcf.plantform.feign.account;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;

@FeignClient(name = "account-center-microservice", fallback =FeignDisableHystrixConfiguration.class)
public interface CashAccountFeignClient {
	//查询现金账户列表
	@RequestMapping(value = "/account/getCashAccountList", method = RequestMethod.POST)
	public PageModel getCashAccountList(@RequestParam Map<String, Object> paramsCondition);
	
    //查询账户明细列表
	@RequestMapping(value = "/account/getCashTransDetailList", method = RequestMethod.POST)
	public PageModel getCashTransDetailList(@RequestParam Map<String, Object> paramsCondition);
	//导出现金账户列表
	@RequestMapping(value = "/account/exportCashAccountList", method = RequestMethod.POST)
	public List<Map<String, Object>> exportCashAccountList(@RequestParam Map<String, Object> paramsCondition);
	
	//导出现金账户明细列表
	@RequestMapping(value = "/account/exportCashTransDetailList", method = RequestMethod.POST)
	public List<Map<String, Object>> exportCashTransDetailList(@RequestParam Map<String, Object> paramsCondition);

}
