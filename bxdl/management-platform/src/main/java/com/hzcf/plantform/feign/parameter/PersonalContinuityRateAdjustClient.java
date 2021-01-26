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
import com.hzcf.pojo.parameter.PersonalContinuityRateAdjust;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface PersonalContinuityRateAdjustClient {
    @RequestMapping(value = "/personal_personal_adjust/do_personal_adjust",method = RequestMethod.POST)
    public PageModel selectPersonalContinuityRateAdjust(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/personal_personal_adjust/add_personal_adjust",method = RequestMethod.POST)
    void addPersonalContinuityRateAdjust(@RequestBody PersonalContinuityRateAdjust ministerNurturingBonus);
    @RequestMapping(value = "/personal_personal_adjust/update_personal_adjust",method = RequestMethod.POST)
    void updatePersonalContinuityRateAdjust(@RequestBody PersonalContinuityRateAdjust ministerNurturingBonus);
    @RequestMapping(value = "/personal_personal_adjust/del_personal_adjust",method = RequestMethod.POST)
    void delPersonalContinuityRateAdjust(@RequestParam(value ="ids")String ids);
    
    @RequestMapping(value = "/personal_personal_adjust/personal_adjust_list",method = RequestMethod.POST)
	public List<PersonalContinuityRateAdjust> getPersonalContinuityRateAdjustList();
    
    @RequestMapping(value = "/personal_personal_adjust/personal_adjust_size",method = RequestMethod.POST)
	public int checkPersonalAdjustSize(Map<String, Object> params);
    
}
