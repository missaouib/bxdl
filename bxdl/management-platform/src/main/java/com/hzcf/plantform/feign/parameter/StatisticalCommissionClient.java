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
import com.hzcf.pojo.parameter.StatisticalCommission;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface StatisticalCommissionClient {
    @RequestMapping(value = "/statistical_commission_manager/select_statistical_commission",method = RequestMethod.POST)
    public PageModel selectStatisticalCommission(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/statistical_commission_manager/add_statistical_commission",method = RequestMethod.POST)
    void addStatisticalCommission(@RequestBody StatisticalCommission statisticalCommission);
    @RequestMapping(value = "/statistical_commission_manager/update_statistical_commission",method = RequestMethod.POST)
    void updateStatisticalCommission(@RequestBody StatisticalCommission statisticalCommission);

    @RequestMapping(value = "/statistical_commission_manager/statistical_commission_list",method = RequestMethod.POST)
	public List<StatisticalCommission> getStatisticalCommissionList();
    @RequestMapping(value = "/statistical_commission_manager/statistical_commission_list_now",method = RequestMethod.POST)
	public List<StatisticalCommission> getStatisticalCommissionListNow();
    
}
