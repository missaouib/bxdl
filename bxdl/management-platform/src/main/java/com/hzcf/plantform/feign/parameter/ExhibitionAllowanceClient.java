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
import com.hzcf.pojo.parameter.ExhibitionAllowance;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface ExhibitionAllowanceClient {
    @RequestMapping(value = "/exhibition_allowance_manager/do_exhibition_allowance",method = RequestMethod.POST)
    public PageModel selectExhibitionAllowance(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/exhibition_allowance_manager/add_exhibition_allowance",method = RequestMethod.POST)
    void addExhibitionAllowance(@RequestBody ExhibitionAllowance benchmarkingDiscountCoefficient);
    @RequestMapping(value = "/exhibition_allowance_manager/update_exhibition_allowance",method = RequestMethod.POST)
    void updateExhibitionAllowance(@RequestBody ExhibitionAllowance benchmarkingDiscountCoefficient);

    @RequestMapping(value = "/exhibition_allowance_manager/exhibition_allowance_list",method = RequestMethod.POST)
	public List<ExhibitionAllowance> getExhibitionAllowanceList();
    
    @RequestMapping(value = "/exhibition_allowance_manager/exhibition_allowance_size",method = RequestMethod.POST)
	public int checkMinisterSize(@RequestParam Map<String, Object> params);
    
    
}
