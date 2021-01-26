package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.IncreaseAllowanceService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.IncreaseAllowance;

@Controller
@RequestMapping("/increase_allowance_manager")
public class IncreaseAllowanceController{
    @Autowired
    private IncreaseAllowanceService increaseAllowanceService;

    @RequestMapping("/select_increase_allowance")
    @ResponseBody
    public PageModel selectIncreaseAllowance(@RequestParam Map<String,Object> paras){
        return increaseAllowanceService.selectIncreaseAllowance(paras);
    }
    @RequestMapping("/add_increase_allowance")
    @ResponseBody
    public void addIncreaseAllowance(@RequestBody IncreaseAllowance increaseAllowance){
         increaseAllowanceService.addIncreaseAllowance(increaseAllowance);
    }
    @RequestMapping("/update_increase_allowance")
    @ResponseBody
    public void updateIncreaseAllowance(@RequestBody IncreaseAllowance increaseAllowance){
        increaseAllowanceService.updateIncreaseAllowance(increaseAllowance);
    }
    
    @RequestMapping("/increase_allowance_list")
    @ResponseBody
    public List<IncreaseAllowance> getIncreaseAllowanceList(){
    	return  increaseAllowanceService.getIncreaseAllowanceList();
    }
    
    @RequestMapping("/increase_allowance_size")
    @ResponseBody
    public int checkIncreaseAllowanceSize(@RequestParam Map<String, Object> params){
		return increaseAllowanceService.checkIncreaseAllowanceSize(params);
    	
    }; 
    
    
}
