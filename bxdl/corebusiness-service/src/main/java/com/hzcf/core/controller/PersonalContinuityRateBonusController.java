package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.PersonalContinuityRateBonusService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.PersonalContinuityRateBonus;

@Controller
@RequestMapping("/personal_bonus_manager")
public class PersonalContinuityRateBonusController {
    @Autowired
    private PersonalContinuityRateBonusService personalContinuityRateBonusService;

    @RequestMapping("/select_personal_bonus")
    @ResponseBody
    public PageModel selectPersonalContinuityRateBonus(@RequestParam Map<String,Object> paras){
        return personalContinuityRateBonusService.selectPersonalContinuityRateBonus(paras);
    }
    @RequestMapping("/add_personal_bonus")
    @ResponseBody
    public void addPersonalContinuityRateBonus(@RequestBody PersonalContinuityRateBonus ministerNurturingBonus){
         personalContinuityRateBonusService.addPersonalContinuityRateBonus(ministerNurturingBonus);
    }
    @RequestMapping("/update_personal_bonus")
    @ResponseBody
    public void updatePersonalContinuityRateBonus(@RequestBody PersonalContinuityRateBonus ministerNurturingBonus){
        personalContinuityRateBonusService.updatePersonalContinuityRateBonus(ministerNurturingBonus);
    }
    @RequestMapping("/del_personal_bonus")
    @ResponseBody
    public void delPersonalContinuityRateBonus(@RequestParam String ids){
        personalContinuityRateBonusService.delPersonalContinuityRateBonus(ids);
    }
    
    @RequestMapping("/personal_bonus_list")
    @ResponseBody
    public List<PersonalContinuityRateBonus> getPersonalContinuityRateBonusList(){
    	return  personalContinuityRateBonusService.getPersonalContinuityRateBonusList();
    }
    
    @RequestMapping("/personal_bonus_size")
    @ResponseBody
    public int checkPersonalBonusSize(@RequestParam Map<String, Object> params){
		return personalContinuityRateBonusService.checkPersonalBonusSize(params);
    	
    }; 
    
    
}
