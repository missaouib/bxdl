package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.SalesGrade;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "corebusiness-service", fallback =FeignDisableHystrixConfiguration.class)
public interface SalesGradeClient {
	
	/**
	 *分页
	 * @param SalesGrade 
	 * */
	@RequestMapping(value = "/salesGrade/getSalesGradePage",method = RequestMethod.POST)
	PageModel getSalesGradePage(@RequestParam Map<String, Object> params);
	/**
	 *列表
	 * @param SalesGrade 
	 * */
	@RequestMapping(value = "/salesGrade/getSalesGradeList",method = RequestMethod.POST)
	List<SalesGrade> getSalesGradeList(@RequestParam Map<String, Object> params);
	/**
	 *新增
	 * @param SalesGrade 
	 * */
	@RequestMapping(value = "/salesGrade/addSalesGrade",method = RequestMethod.POST)
	void addSalesGrade(SalesGrade salesGrade);
	/**
	 *修改
	 * @param SalesGrade 
	 * */
	@RequestMapping(value = "/salesGrade/updateSalesGrade",method = RequestMethod.POST)
	void updateSalesGrade(SalesGrade salesGrade);
	/**
	 *明细
	 * @param SalesGrade 
	 * */
	@RequestMapping(value = "/salesGrade/selectById",method = RequestMethod.POST)
	SalesGrade selectById(@RequestParam Map<String, Object> map);	
}
