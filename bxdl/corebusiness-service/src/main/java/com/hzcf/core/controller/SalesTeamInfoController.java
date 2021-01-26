package com.hzcf.core.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hzcf.core.service.SalesTeamInfoService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesTeamInfo;

/**
 * 保险部门管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/salesTeamInfo")
public class SalesTeamInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SalesTeamInfoService salesTeamInfoService;
    
    /**
     * 添加部门
     * */
    @RequestMapping("/addSalesTeamInfo")
    @ResponseBody
    public void addSalesTeamInfo(@RequestBody SalesTeamInfo salesTeamInfo){
    	salesTeamInfoService.addSalesTeamInfo(salesTeamInfo);
    }
    
    /**
     * 修改基础信息
     * */
    @RequestMapping("/updateSalesTeamInfo")
    @ResponseBody
    public void updateSalesTeamInfo(@RequestBody SalesTeamInfo salesTeamInfo){
    	salesTeamInfoService.updateSalesTeamInfo(salesTeamInfo);
    }
    
    /**
     * 分页查询保险部门
     * */
    @RequestMapping("/getSalesTeamInfoList")
    @ResponseBody
    public PageModel getSalesTeamInfoList(@RequestParam Map<String,Object> params){
    	return salesTeamInfoService.getSalesTeamInfoList(params);
    }
    
    /**
     * 查找前置编码
     * */
    @RequestMapping("/findMaxTreeCode")
    @ResponseBody
    public int findMaxTreeCode(@RequestParam Map<String,Object> params){
    	return salesTeamInfoService.findMaxTreeCode(params);
    } 
    
    /**
     * 不分页查询保险部门
     * */
    @RequestMapping("/selectById")
    @ResponseBody
    public SalesTeamInfo selectById(@RequestParam Map<String,Object> params){
    	return salesTeamInfoService.selectById(params);
    }    
    
	 /**
    *
    *根据条件查询父辈组织机构
    * */
    @RequestMapping("/getParents")
    @ResponseBody
    public List<SalesTeamInfo> getParents(@RequestParam Map<String,Object> params){
    	return salesTeamInfoService.getParents(params);
    }

     @RequestMapping("/selectByCondition")
    @ResponseBody
    public List<SalesTeamInfo> selectByCondition(@RequestParam Map<String,Object> params){
    	return salesTeamInfoService.selectByCondition(params);
    }


    @RequestMapping(value = "/insertImportList", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object>  insertImportList(@RequestBody Map<String, Object> p){
          Map<String, Object> map = new HashMap<String, Object>();
        try {
            salesTeamInfoService.insertImportList(p);
            map.put("code", 200);
        } catch (RuntimeException e) {
            logger.error("批量导入异常",e);
            map.put("code", 400);
            e.printStackTrace();
        }
        return map;
    }
}
