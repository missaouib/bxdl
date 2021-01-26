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

import com.hzcf.core.service.SalesGradeService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesGrade;

/**
 * 职级序列管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/salesGrade")
public class SalesGradeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SalesGradeService salesGradeService;
    
    /**
     * 添加序列
     * */
    @RequestMapping("/addSalesGrade")
    @ResponseBody
    public void addSalesGrade(@RequestBody SalesGrade salesGrade){
    	salesGradeService.addSalesGrade(salesGrade);
    }
    
    /**
     * 修改基础信息
     * */
    @RequestMapping("/updateSalesGrade")
    @ResponseBody
    public void updateSalesGrade(@RequestBody SalesGrade salesGrade){
    	salesGradeService.updateSalesGrade(salesGrade);
    }
    
    /**
     * 分页查询职级序列
     * */
    @RequestMapping("/getSalesGradePage")
    @ResponseBody
    public PageModel getSalesGradePage(@RequestParam Map<String,Object> params){
    	return salesGradeService.getSalesGradePage(params);
    }
    
    /**
     * 不分页查询职级序列
     * */
    @RequestMapping("/selectById")
    @ResponseBody
    public SalesGrade selectById(@RequestParam Map<String,Object> params){
    	return salesGradeService.selectById(params);
    }    
    
	 /**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/getSalesGradeList")
    @ResponseBody
    public List<SalesGrade> getSalesGradeList(@RequestParam Map<String,Object> params){
    	return salesGradeService.getSalesGradeList(params);
    }
}
