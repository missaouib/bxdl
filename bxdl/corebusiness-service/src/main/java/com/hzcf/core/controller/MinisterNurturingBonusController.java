package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.MinisterNurturingBonusService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.MinisterNurturingBonus;

@Controller
@RequestMapping("/minister_nurturing_manager")
public class MinisterNurturingBonusController {
    @Autowired
    private MinisterNurturingBonusService ministerNurturingBonusService;

    @RequestMapping("/select_minister_nurturing")
    @ResponseBody
    public PageModel selectMinisterNurturingBonus(@RequestParam Map<String,Object> paras){
        return ministerNurturingBonusService.selectMinisterNurturingBonus(paras);
    }
    @RequestMapping("/add_minister_nurturing")
    @ResponseBody
    public void addMinisterNurturingBonus(@RequestBody MinisterNurturingBonus ministerNurturingBonus){
         ministerNurturingBonusService.addMinisterNurturingBonus(ministerNurturingBonus);
    }
    @RequestMapping("/update_minister_nurturing")
    @ResponseBody
    public void updateMinisterNurturingBonus(@RequestBody MinisterNurturingBonus ministerNurturingBonus){
        ministerNurturingBonusService.updateMinisterNurturingBonus(ministerNurturingBonus);
    }
    @RequestMapping("/del_minister_nurturing")
    @ResponseBody
    public void delMinisterNurturingBonus(@RequestParam String ids){
        ministerNurturingBonusService.delMinisterNurturingBonus(ids);
    }
    
    @RequestMapping("/minister_nurturing_list")
    @ResponseBody
    public List<MinisterNurturingBonus> getMinisterNurturingList(){
    	return  ministerNurturingBonusService.getMinisterNurturingList();
    }
    
    @RequestMapping("/minister_size")
    @ResponseBody
    public int checkMinisterSize(@RequestParam Map<String, Object> params){
		return ministerNurturingBonusService.checkMinisterSize(params);
    	
    };
    
}
