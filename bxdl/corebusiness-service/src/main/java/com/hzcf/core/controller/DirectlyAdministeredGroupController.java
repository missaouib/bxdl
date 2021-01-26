package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.DirectlyAdministeredGroupService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectlyAdministeredGroup;

@Controller
@RequestMapping("/directly_administer_manager")
public class DirectlyAdministeredGroupController {
    @Autowired
    private DirectlyAdministeredGroupService directlyAdministeredGroupService;

    @RequestMapping("/select_directly_administer")
    @ResponseBody
    public PageModel selectDirectlyAdministeredGroup(@RequestParam Map<String,Object> paras){
        return directlyAdministeredGroupService.selectDirectlyAdministeredGroup(paras);
    }
    @RequestMapping("/add_directly_administer")
    @ResponseBody
    public void addDirectlyAdministeredGroup(@RequestBody DirectlyAdministeredGroup directlyAdministeredGroup){
         directlyAdministeredGroupService.addDirectlyAdministeredGroup(directlyAdministeredGroup);
    }
    @RequestMapping("/update_directly_administer")
    @ResponseBody
    public void updateDirectlyAdministeredGroup(@RequestBody DirectlyAdministeredGroup directlyAdministeredGroup){
        directlyAdministeredGroupService.updateDirectlyAdministeredGroup(directlyAdministeredGroup);
    }
    
    @RequestMapping("/directly_administer_list")
    @ResponseBody
    public List<DirectlyAdministeredGroup> getDirectlyAdministeredGroupList(){
    	return  directlyAdministeredGroupService.getDirectlyAdministeredGroupList();
    }
    
    @RequestMapping("/directly_administer_size")
    @ResponseBody
    public int checkPersonalBonusSize(@RequestParam Map<String, Object> params){
		return directlyAdministeredGroupService.checkPersonalBonusSize(params);
    	
    }; 
    
    
}
