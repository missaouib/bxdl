package com.hzcf.plantform.feign.parameter;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.PersonalContinuityRateBonus;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface PersonalContinuityRateBonusClient {
    @RequestMapping(value = "/personal_bonus_manager/do_personal_bonus",method = RequestMethod.POST)
    public PageModel selectPersonalContinuityRateBonus(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/personal_bonus_manager/add_personal_bonus",method = RequestMethod.POST)
    void addPersonalContinuityRateBonus(@RequestBody PersonalContinuityRateBonus ministerNurturingBonus);
    @RequestMapping(value = "/personal_bonus_manager/update_personal_bonus",method = RequestMethod.POST)
    void updatePersonalContinuityRateBonus(@RequestBody PersonalContinuityRateBonus ministerNurturingBonus);
    @RequestMapping(value = "/personal_bonus_manager/del_personal_bonus",method = RequestMethod.POST)
    void delPersonalContinuityRateBonus(@RequestParam(value ="ids")String ids);
    
    @RequestMapping(value = "/personal_bonus_manager/personal_bonus_list",method = RequestMethod.POST)
	public List<PersonalContinuityRateBonus> getPersonalContinuityRateBonusList();
    
    @RequestMapping(value = "/personal_bonus_manager/personal_bonus_size",method = RequestMethod.POST)
	public int checkPersonalRateBonusSize(@RequestParam Map<String, Object> params);
    
}
