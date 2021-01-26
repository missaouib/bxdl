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
import com.hzcf.pojo.parameter.DirectorNurtureBonus;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface DirectorNurtureBonusClient {
    @RequestMapping(value = "/nurture_bonus_manager/do_nurture_bonus",method = RequestMethod.POST)
    public PageModel selectDirectorNurtureBonus(@RequestParam Map<String,Object> paras);
    
    @RequestMapping(value = "/nurture_bonus_manager/add_nurture_bonus",method = RequestMethod.POST)
    void addDirectorNurtureBonus(@RequestBody DirectorNurtureBonus directorNurtureBonus);
    
    @RequestMapping(value = "/nurture_bonus_manager/update_nurture_bonus",method = RequestMethod.POST)
    void updateDirectorNurtureBonus(@RequestBody DirectorNurtureBonus directorNurtureBonus);
    
    @RequestMapping(value = "/nurture_bonus_manager/del_nurture_bonus",method = RequestMethod.POST)
    void delDirectorNurtureBonus(@RequestParam(value ="ids")String ids);
    
    @RequestMapping(value = "/nurture_bonus_manager/minister_nurturing_list",method = RequestMethod.POST)
	public List<DirectorNurtureBonus> getDirectorNurtureBonusList();
    
    @RequestMapping(value = "/nurture_bonus_manager/check_minister_nurturing_size",method = RequestMethod.POST)
	public int checkDirectorSize(@RequestParam Map<String, Object> params);
    
    
}
