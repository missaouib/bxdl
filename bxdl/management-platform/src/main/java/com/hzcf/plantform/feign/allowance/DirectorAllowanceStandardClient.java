package com.hzcf.plantform.feign.allowance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface DirectorAllowanceStandardClient {
    @RequestMapping(value = "/allowance_manager/do_director_allowance",method = RequestMethod.POST)
    public PageModel doDirectorAllowance(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/allowance_manager/add_director_allowance",method = RequestMethod.POST)
    void addDirectorAllowance(@RequestBody DirectorAllowanceStandardPojo directorAllowanceStandardPojo);
    @RequestMapping(value = "/allowance_manager/update_director_allowance",method = RequestMethod.POST)
    void updateDirectorAllowance(@RequestBody DirectorAllowanceStandardPojo directorAllowanceStandardPojo);
    @RequestMapping(value = "/allowance_manager/del_director_allowance",method = RequestMethod.POST)
    void delDirectorAllowance(@RequestParam(value ="ids")String ids);
}
