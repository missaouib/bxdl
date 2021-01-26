package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.DirectorNurtureBonusService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectorNurtureBonus;
import com.hzcf.pojo.parameter.MinisterNurturingBonus;

@Controller
@RequestMapping("/nurture_bonus_manager")
public class DirectorNurtureBonusController {
    @Autowired
    private DirectorNurtureBonusService directorNurtureBonusService;

    @RequestMapping("/do_nurture_bonus")
    @ResponseBody
    public PageModel selectDirectorNurtureBonus(@RequestParam Map<String,Object> paras){
        return directorNurtureBonusService.selectDirectorNurtureBonus(paras);
    }
    @RequestMapping("/add_nurture_bonus")
    @ResponseBody
    public void addDirectorNurtureBonus(@RequestBody DirectorNurtureBonus directorNurtureBonus){
         directorNurtureBonusService.addDirectorNurtureBonus(directorNurtureBonus);
    }
    @RequestMapping("/update_nurture_bonus")
    @ResponseBody
    public void updateDirectorNurtureBonus(@RequestBody DirectorNurtureBonus directorNurtureBonus){
        directorNurtureBonusService.updateDirectorNurtureBonus(directorNurtureBonus);
    }
    @RequestMapping("/del_nurture_bonus")
    @ResponseBody
    public void delDirectorNurtureBonus(@RequestParam String ids){
        directorNurtureBonusService.delDirectorNurtureBonus(ids);
    }
    
    @RequestMapping("/minister_nurturing_list")
    @ResponseBody
    public List<DirectorNurtureBonus> getDirectorNurtureBonusList(){
        return directorNurtureBonusService.getDirectorNurtureBonusList();
    }
    @RequestMapping("/check_minister_nurturing_size")
    @ResponseBody
    public int checkDirectorSize(@RequestParam Map<String, Object> params){
		return directorNurtureBonusService.checkDirectorSize(params);
    	
    };
    
}
