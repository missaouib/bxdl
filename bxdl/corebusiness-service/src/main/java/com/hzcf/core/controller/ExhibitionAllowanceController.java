package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.ExhibitionAllowanceService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.ExhibitionAllowance;

@Controller
@RequestMapping("/exhibition_allowance_manager")
public class ExhibitionAllowanceController {
    @Autowired
    private ExhibitionAllowanceService exhibitionAllowanceService;

    @RequestMapping("/select_exhibition_allowance")
    @ResponseBody
    public PageModel selectExhibitionAllowance(@RequestParam Map<String,Object> paras){
        return exhibitionAllowanceService.selectExhibitionAllowance(paras);
    }
    @RequestMapping("/add_exhibition_allowance")
    @ResponseBody
    public void addExhibitionAllowance(@RequestBody ExhibitionAllowance directlyUnderManager){
         exhibitionAllowanceService.addExhibitionAllowance(directlyUnderManager);
    }
    @RequestMapping("/update_exhibition_allowance")
    @ResponseBody
    public void updateExhibitionAllowance(@RequestBody ExhibitionAllowance directlyUnderManager){
        exhibitionAllowanceService.updateExhibitionAllowance(directlyUnderManager);
    }
    
    @RequestMapping("/exhibition_allowance_list")
    @ResponseBody
    public List<ExhibitionAllowance> getExhibitionAllowanceList(){
    	return  exhibitionAllowanceService.getExhibitionAllowanceList();
    }
    
    @RequestMapping("/exhibition_allowance_size")
    @ResponseBody
    public int checkExhibitionAllowanceSize(@RequestParam Map<String, Object> params){
		return exhibitionAllowanceService.checkExhibitionAllowanceSize(params);
    	
    }; 
    
    
}
