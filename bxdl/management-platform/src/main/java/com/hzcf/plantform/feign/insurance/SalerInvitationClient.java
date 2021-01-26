package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.SalerInvitation;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "corebusiness-service", fallback =FeignDisableHystrixConfiguration.class)
public interface SalerInvitationClient {
	
	/**
	 *分页
	 * @param SalerInvitation 
	 * */
	@RequestMapping(value = "/salerInvitation/getSalerInvitationPage",method = RequestMethod.POST)
	PageModel getSalerInvitationPage(@RequestParam Map<String, Object> params);
	/**
	 *新增
	 * @param SalerInvitation 
	 * */
	@RequestMapping(value = "/salerInvitation/addSalerInvitation",method = RequestMethod.POST)
	void addSalerInvitation(SalerInvitation salerInvitation);
	/**
	 *修改
	 * @param SalerInvitation 
	 * */
	@RequestMapping(value = "/salerInvitation/updateSalerInvitation",method = RequestMethod.POST)
	void updateSalerInvitation(SalerInvitation salerInvitation);
	/**
	 *明细
	 * @param SalerInvitation 
	 * */
	@RequestMapping(value = "/salerInvitation/selectById",method = RequestMethod.POST)
	SalerInvitation selectById(@RequestParam Map<String, Object> map);	
}
