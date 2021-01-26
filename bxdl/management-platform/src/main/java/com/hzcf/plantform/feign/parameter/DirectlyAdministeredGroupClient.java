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
import com.hzcf.pojo.parameter.DirectlyAdministeredGroup;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface DirectlyAdministeredGroupClient {
    @RequestMapping(value = "/directly_administer_manager/do_directly_administer",method = RequestMethod.POST)
    public PageModel selectDirectlyAdministeredGroup(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/directly_administer_manager/add_directly_administer",method = RequestMethod.POST)
    void addDirectlyAdministeredGroup(@RequestBody DirectlyAdministeredGroup directlyAdministeredGroup);
    @RequestMapping(value = "/directly_administer_manager/update_directly_administer",method = RequestMethod.POST)
    void updateDirectlyAdministeredGroup(@RequestBody DirectlyAdministeredGroup directlyAdministeredGroup);
    @RequestMapping(value = "/directly_administer_manager/directly_administer_list",method = RequestMethod.POST)
	public List<DirectlyAdministeredGroup> getDirectlyAdministeredGroupList();
    @RequestMapping(value = "/directly_administer_manager/directly_administer_size",method = RequestMethod.POST)
	public int checkDirectlyAdministerSize(@RequestParam Map<String, Object> params);
    
    
}
