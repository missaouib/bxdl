package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.DirectlyUnderManagerService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectlyUnderManager;

@Controller
@RequestMapping("/directly_under_manager")
public class DirectlyUnderManagerController {
    @Autowired
    private DirectlyUnderManagerService directlyUnderManagerService;

    @RequestMapping("/select_directly_under")
    @ResponseBody
    public PageModel selectDirectlyUnderManager(@RequestParam Map<String,Object> paras){
        return directlyUnderManagerService.selectDirectlyUnderManager(paras);
    }
    @RequestMapping("/add_directly_under")
    @ResponseBody
    public void addDirectlyUnderManager(@RequestBody DirectlyUnderManager directlyUnderManager){
         directlyUnderManagerService.addDirectlyUnderManager(directlyUnderManager);
    }
    @RequestMapping("/update_directly_under")
    @ResponseBody
    public void updateDirectlyUnderManager(@RequestBody DirectlyUnderManager directlyUnderManager){
        directlyUnderManagerService.updateDirectlyUnderManager(directlyUnderManager);
    }
    
    @RequestMapping("/directly_under_list")
    @ResponseBody
    public List<DirectlyUnderManager> getDirectlyUnderManagerList(){
    	return  directlyUnderManagerService.getDirectlyUnderManagerList();
    }
    
    @RequestMapping("/directly_under_size")
    @ResponseBody
    public int checkDirectlyUnderSize(@RequestParam Map<String, Object> params){
		return directlyUnderManagerService.checkDirectlyUnderSize(params);
    	
    }; 
    
    
}
