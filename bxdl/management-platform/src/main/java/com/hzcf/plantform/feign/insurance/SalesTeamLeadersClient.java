package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.SalesTeamLeaders;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "corebusiness-service", fallback =FeignDisableHystrixConfiguration.class)
public interface SalesTeamLeadersClient {
	
	/**
	 *分页
	 * @param SalesTeamLeaders 
	 * */
	@RequestMapping(value = "/salesTeamLeaders/getSalesTeamLeadersPage",method = RequestMethod.POST)
	PageModel getSalesTeamLeadersPage(@RequestParam Map<String, Object> params);
	/**
	 *列表
	 * @param SalesTeamLeaders 
	 * */
	@RequestMapping(value = "/salesTeamLeaders/getSalesTeamLeadersList",method = RequestMethod.POST)
	List<SalesTeamLeaders> getSalesTeamLeadersList(@RequestParam Map<String, Object> params);
	/**
	 *新增
	 * @param SalesTeamLeaders 
	 * */
	@RequestMapping(value = "/salesTeamLeaders/addSalesTeamLeaders",method = RequestMethod.POST)
	void addSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders);
	/**
	 *修改
	 * @param SalesTeamLeaders 
	 * */
	@RequestMapping(value = "/salesTeamLeaders/updateSalesTeamLeaders",method = RequestMethod.POST)
	void updateSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders);
	/**
	 *明细
	 * @param SalesTeamLeaders 
	 * */
	@RequestMapping(value = "/salesTeamLeaders/selectById",method = RequestMethod.POST)
	SalesTeamLeaders selectById(@RequestParam Map<String, Object> map);
	/**
	 *明细
	 * @param SalesTeamLeaders 
	 * */
	@RequestMapping(value = "/salesTeamLeaders/disableLeaders",method = RequestMethod.POST)	
	void disableLeaders(@RequestParam Map<String, Object> params);	
}
