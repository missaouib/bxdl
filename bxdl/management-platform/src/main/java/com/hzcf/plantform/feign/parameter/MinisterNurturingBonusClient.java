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
import com.hzcf.pojo.parameter.MinisterNurturingBonus;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface MinisterNurturingBonusClient {
    @RequestMapping(value = "/minister_nurturing_manager/do_minister_nurturing",method = RequestMethod.POST)
    public PageModel selectMinisterNurturingBonus(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/minister_nurturing_manager/add_minister_nurturing",method = RequestMethod.POST)
    void addMinisterNurturingBonus(@RequestBody MinisterNurturingBonus ministerNurturingBonus);
    @RequestMapping(value = "/minister_nurturing_manager/update_minister_nurturing",method = RequestMethod.POST)
    void updateMinisterNurturingBonus(@RequestBody MinisterNurturingBonus ministerNurturingBonus);
    @RequestMapping(value = "/minister_nurturing_manager/del_minister_nurturing",method = RequestMethod.POST)
    void delMinisterNurturingBonus(@RequestParam(value ="ids")String ids);
    
    @RequestMapping(value = "/minister_nurturing_manager/minister_nurturing_list",method = RequestMethod.POST)
	public List<MinisterNurturingBonus> getMinisterNurturingList();
    
    @RequestMapping(value = "/minister_nurturing_manager/minister_size",method = RequestMethod.POST)
	public int checkMinisterSize(@RequestParam Map<String, Object> params);
    
    
}
