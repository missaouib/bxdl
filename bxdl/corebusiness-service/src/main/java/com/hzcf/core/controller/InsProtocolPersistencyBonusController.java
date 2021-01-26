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

import com.hzcf.core.service.PersistencyBonusService;
import com.hzcf.pojo.insurance.protocol.InsProtocolPersistencyBonus;
import com.hzcf.pojo.insurance.protocol.PersistencyBonusRule;

/**
 * 职级序列管理
 *
 */
@Controller
@RequestMapping("/persistencyBonus")
public class InsProtocolPersistencyBonusController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private PersistencyBonusService persistencyBonusService;
    
    @RequestMapping("/selectPb")
    @ResponseBody
    public InsProtocolPersistencyBonus selectPb(@RequestParam Map<String,Object> params){
    	return persistencyBonusService.selectPb(params);
    }
    
    /**
     * 新增
     * */
    @RequestMapping("/addPb")
    @ResponseBody
    public Long addPb(@RequestBody InsProtocolPersistencyBonus ippbs){
    	persistencyBonusService.addPb(ippbs);
    	return ippbs.getBonusId();
    }
    
    /**
     * 修改
     * */
    @RequestMapping("/updatePb")
    @ResponseBody
    public void updatePb(@RequestBody InsProtocolPersistencyBonus ippbs){
    	persistencyBonusService.updatePb(ippbs);
    }        
    
    @RequestMapping("/getPbrList")
    @ResponseBody
    public List<PersistencyBonusRule> getPbrList(@RequestParam Map<String,Object> params){
    	return persistencyBonusService.getPbrList(params);
    }
    
    @RequestMapping("/getOutProducts")
    @ResponseBody
    public List<Object> getOutProducts(@RequestParam Map<String,Object> params){
    	return persistencyBonusService.getOutProducts(params);
    }
    
    /**
     * 新增
     * */
    @RequestMapping("/addPbr")
    @ResponseBody
    public void addPbr(@RequestBody List<PersistencyBonusRule> pbr){
    	persistencyBonusService.addPbr(pbr);
    }  
    
    /**
     * 修改
     * */
    @RequestMapping("/updatePbr")
    @ResponseBody
    public void updatePbr(@RequestBody List<PersistencyBonusRule> pbr){
    	persistencyBonusService.updatePbr(pbr);
    }
    
    /**
     * 修改
     * */
    @RequestMapping("/deletePbrs")
    @ResponseBody
    public void deletePbrs(@RequestParam Map<String,Object> params){
    	persistencyBonusService.deletePbrs(params);
    }
    
}
