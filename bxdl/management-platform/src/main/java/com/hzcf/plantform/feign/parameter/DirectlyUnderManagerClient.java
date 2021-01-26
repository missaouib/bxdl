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
import com.hzcf.pojo.parameter.DirectlyUnderManager;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface DirectlyUnderManagerClient {
    @RequestMapping(value = "/directly_under_manager/do_directly_under",method = RequestMethod.POST)
    public PageModel selectDirectlyUnderManager(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/directly_under_manager/add_directly_under",method = RequestMethod.POST)
    void addDirectlyUnderManager(@RequestBody DirectlyUnderManager directlyUnderManager);
    @RequestMapping(value = "/directly_under_manager/update_directly_under",method = RequestMethod.POST)
    void updateDirectlyUnderManager(@RequestBody DirectlyUnderManager directlyUnderManager);

    @RequestMapping(value = "/directly_under_manager/directly_under_list",method = RequestMethod.POST)
	public List<DirectlyUnderManager> getDirectlyUnderManagerList();
    
    @RequestMapping(value = "/directly_under_manager/directly_under_size",method = RequestMethod.POST)
	public int checkDirectlyUnderManagerSize(@RequestParam Map<String, Object> params);
    
    
}
