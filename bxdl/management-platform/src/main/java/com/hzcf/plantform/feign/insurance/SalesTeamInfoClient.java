package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.SalesTeamInfo;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "corebusiness-service", fallback =FeignDisableHystrixConfiguration.class)
public interface SalesTeamInfoClient {
	
	/**
	 *增加销售组织
	 * @param salesTeamInfo 
	 * */
	@RequestMapping(value = "/salesTeamInfo/addSalesTeamInfo",method = RequestMethod.POST)
	void addSalesTeamInfo(SalesTeamInfo salesTeamInfo);

	/**
	 *销售组织列表
	 * @param salesTeamInfoList 
	 * */
	@RequestMapping(value = "/salesTeamInfo/getSalesTeamInfoList",method = RequestMethod.POST)
	public PageModel getSalesTeamInfoList(@RequestParam Map<String, Object> paras);

	/**
	 * 获取前置编码
	 * @param  
	 * */
	@RequestMapping(value = "/salesTeamInfo/findMaxTreeCode",method = RequestMethod.POST)
	public int findMaxTreeCode(@RequestParam Map<String, Object> paras);

	/**
	 *查
	 * @param selectById 
	 * */
	@RequestMapping(value = "/salesTeamInfo/selectById",method = RequestMethod.POST)
	public SalesTeamInfo selectById(@RequestParam Map<String, Object> paras);

	 /**
    *
    *根据条件查询父辈组织机构
    * */
	@RequestMapping(value = "/salesTeamInfo/getParents",method = RequestMethod.POST)
	public List<SalesTeamInfo> getParents(@RequestParam Map<String, Object> paras);


	@RequestMapping(value = "/salesTeamInfo/selectByCondition",method = RequestMethod.POST)
	public List<SalesTeamInfo> selectByCondition(@RequestParam Map<String, Object> paras);

	 /**
    *
    *修改基础信息
    * */
	@RequestMapping(value = "/salesTeamInfo/updateSalesTeamInfo",method = RequestMethod.POST)
	public void updateSalesTeamInfo(SalesTeamInfo salesTeamInfo);

    /**
     *@描述 批量插入销售团队信息
     *@参数
     *@返回值
     *@创建人 qin lina
     *@创建时间 2020/3/9
     */
	@RequestMapping(value = "/salesTeamInfo/insertImportList",method = RequestMethod.POST)
	Map<String,Object> insertImportList(Map<String, Object> p);
}
