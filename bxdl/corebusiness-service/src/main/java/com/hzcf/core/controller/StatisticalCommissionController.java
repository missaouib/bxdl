package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.StatisticalCommissionService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.StatisticalCommission;

@Controller
@RequestMapping("/statistical_commission_manager")
public class StatisticalCommissionController {
    @Autowired
    private StatisticalCommissionService statisticalCommissionService;

    @RequestMapping("/select_statistical_commission")
    @ResponseBody
    public PageModel selectStatisticalCommission(@RequestParam Map<String,Object> paras){
        return statisticalCommissionService.selectStatisticalCommission(paras);
    }
    @RequestMapping("/add_statistical_commission")
    @ResponseBody
    public void addStatisticalCommission(@RequestBody StatisticalCommission directlyUnderManager){
         statisticalCommissionService.addStatisticalCommission(directlyUnderManager);
    }
    @RequestMapping("/update_statistical_commission")
    @ResponseBody
    public void updateStatisticalCommission(@RequestBody StatisticalCommission directlyUnderManager){
        statisticalCommissionService.updateStatisticalCommission(directlyUnderManager);
    }
    
    @RequestMapping("/statistical_commission_list")
    @ResponseBody
    public List<StatisticalCommission> getStatisticalCommissionList(){
    	return  statisticalCommissionService.getStatisticalCommissionList();
    }
    
    @RequestMapping("/statistical_commission_list_now")
    @ResponseBody
    public List<StatisticalCommission> getStatisticalCommissionListNow(){
    	return  statisticalCommissionService.getStatisticalCommissionListNow();
    }
    
    
}
