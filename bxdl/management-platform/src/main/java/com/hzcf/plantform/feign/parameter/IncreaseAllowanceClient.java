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
import com.hzcf.pojo.parameter.IncreaseAllowance;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface IncreaseAllowanceClient {
    @RequestMapping(value = "/increase_allowance_manager/do_increase_allowance",method = RequestMethod.POST)
    public PageModel selectIncreaseAllowance(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/increase_allowance_manager/add_increase_allowance",method = RequestMethod.POST)
    void addIncreaseAllowance(@RequestBody IncreaseAllowance increaseAllowance);
    @RequestMapping(value = "/increase_allowance_manager/update_increase_allowance",method = RequestMethod.POST)
    void updateIncreaseAllowance(@RequestBody IncreaseAllowance increaseAllowance);

    @RequestMapping(value = "/increase_allowance_manager/increase_allowance_list",method = RequestMethod.POST)
	public List<IncreaseAllowance> getIncreaseAllowanceList();
    
    @RequestMapping(value = "/increase_allowance_manager/increase_allowance_size",method = RequestMethod.POST)
	public int checkMinisterSize(@RequestParam Map<String, Object> params);
    
    
}
