package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.PersonalContinuityRateAdjustService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.PersonalContinuityRateAdjust;

@Controller
@RequestMapping("/personal_personal_adjust")
public class PersonalContinuityRateAdjustController {
    @Autowired
    private PersonalContinuityRateAdjustService personalContinuityRateAdjustService;

    @RequestMapping("/select_personal_adjust")
    @ResponseBody
    public PageModel selectPersonalContinuityRateAdjust(@RequestParam Map<String,Object> paras){
        return personalContinuityRateAdjustService.selectPersonalContinuityRateAdjust(paras);
    }
    @RequestMapping("/add_personal_adjust")
    @ResponseBody
    public void addPersonalContinuityRateAdjust(@RequestBody PersonalContinuityRateAdjust personalContinuityRateAdjust){
    	personalContinuityRateAdjustService.addPersonalContinuityRateAdjust(personalContinuityRateAdjust);
    }
    @RequestMapping("/update_personal_adjust")
    @ResponseBody
    public void updatePersonalContinuityRateAdjust(@RequestBody PersonalContinuityRateAdjust personalContinuityRateAdjust){
    	personalContinuityRateAdjustService.updatePersonalContinuityRateAdjust(personalContinuityRateAdjust);
    }
    @RequestMapping("/del_personal_adjust")
    @ResponseBody
    public void delPersonalContinuityRateAdjust(@RequestParam String ids){
    	personalContinuityRateAdjustService.delPersonalContinuityRateAdjust(ids);
    }
    
    @RequestMapping("/personal_adjust_list")
    @ResponseBody
    public List<PersonalContinuityRateAdjust> getPersonalContinuityRateAdjustList(){
    	return  personalContinuityRateAdjustService.getPersonalContinuityRateAdjustList();
    }
    
    @RequestMapping("/personal_adjust_size")
    @ResponseBody
    public int checkPersonalAdjustSize(@RequestParam Map<String, Object> params){
		return personalContinuityRateAdjustService.checkPersonalAdjustSize(params);
    	
    };
}
