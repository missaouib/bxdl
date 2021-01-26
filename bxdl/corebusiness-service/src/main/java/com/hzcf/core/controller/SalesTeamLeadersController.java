package com.hzcf.core.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.SalesTeamLeadersService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesTeamLeaders;

/**
 * 职级序列管理
 * @author yl
 *
 */
@Controller
@RequestMapping("/salesTeamLeaders")
public class SalesTeamLeadersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SalesTeamLeadersService salesTeamLeadersService;
    
    /**
     * 添加序列
     * */
    @RequestMapping("/addSalesTeamLeaders")
    @ResponseBody
    public void addSalesTeamLeaders(@RequestBody SalesTeamLeaders salesTeamLeaders){
    	salesTeamLeadersService.addSalesTeamLeaders(salesTeamLeaders);
    }
    
    /**
     * 修改基础信息
     * */
    @RequestMapping("/updateSalesTeamLeaders")
    @ResponseBody
    public void updateSalesTeamLeaders(@RequestBody SalesTeamLeaders salesTeamLeaders){
    	salesTeamLeadersService.updateSalesTeamLeaders(salesTeamLeaders);
    }
    
    /**
     * 分页查询职级序列
     * */
    @RequestMapping("/getSalesTeamLeadersPage")
    @ResponseBody
    public PageModel getSalesTeamLeadersPage(@RequestParam Map<String,Object> params){
    	return salesTeamLeadersService.getSalesTeamLeadersPage(params);
    }
    
    /**
     * 不分页查询职级序列
     * */
    @RequestMapping("/selectById")
    @ResponseBody
    public SalesTeamLeaders selectById(@RequestParam Map<String,Object> params){
    	return salesTeamLeadersService.selectById(params);
    }   
    
    /**
     * 终止
     * */
    @RequestMapping("/disableLeaders")
    @ResponseBody
    public void disableLeaders(@RequestParam Map<String,Object> params){
    	salesTeamLeadersService.disableLeaders(params);
    }      
    
	 /**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/getSalesTeamLeadersList")
    @ResponseBody
    public List<SalesTeamLeaders> getSalesTeamLeadersList(@RequestParam Map<String,Object> params){
    	return salesTeamLeadersService.getSalesTeamLeadersList(params);
    }
}
