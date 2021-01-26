package com.hzcf.plantform.feign.protocol;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.pojo.insurance.protocol.InsProtocolPersistencyBonus;
import com.hzcf.pojo.insurance.protocol.PersistencyBonusRule;

@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface InsProtocolPersistencyBonusClient {
	
	@RequestMapping(value = "/persistencyBonus/selectPb", method = RequestMethod.POST)
	public InsProtocolPersistencyBonus selectPb(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/persistencyBonus/addPb", method = RequestMethod.POST)
	public Long addPb(InsProtocolPersistencyBonus ippbs);

	@RequestMapping(value = "/persistencyBonus/getPbrList", method = RequestMethod.POST)
	public List<PersistencyBonusRule> getPbrList(@RequestParam Map<String, Object> map);

	@RequestMapping(value = "/persistencyBonus/getOutProducts", method = RequestMethod.POST)
	public List<Object> getOutProducts(@RequestParam Map<String, Object> map);

	@RequestMapping(value = "/persistencyBonus/updatePb", method = RequestMethod.POST)
	public void updatePb(InsProtocolPersistencyBonus ippbs);

	@RequestMapping(value = "/persistencyBonus/updatePbr", method = RequestMethod.POST)
	public void updatePbr(List<PersistencyBonusRule> pbrUpd);

	@RequestMapping(value = "/persistencyBonus/deletePbrs", method = RequestMethod.POST)
	public void deletePbrs(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/persistencyBonus/addPbr", method = RequestMethod.POST)
	public void addPbr(List<PersistencyBonusRule> pbrAdd);
	
}
