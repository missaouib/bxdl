package com.hzcf.core.controller;

import com.hzcf.core.service.DirectorAllowanceStandardService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/allowance_manager")
public class DirectorAllowanceStandardController {
    @Autowired
    private DirectorAllowanceStandardService directorAllowanceStandardService;

    @RequestMapping("/do_director_allowance")
    @ResponseBody
    public PageModel doDirectorAllowance(@RequestParam Map<String,Object> paras){
        return directorAllowanceStandardService.doDirectorAllowance(paras);
    }
    @RequestMapping("/add_director_allowance")
    @ResponseBody
    public void addDirectorAllowance(@RequestBody DirectorAllowanceStandardPojo directorAllowanceStandardPojo){
         directorAllowanceStandardService.addDirectorAllowance(directorAllowanceStandardPojo);
    }
    @RequestMapping("/update_director_allowance")
    @ResponseBody
    public void updateDirectorAllowance(@RequestBody DirectorAllowanceStandardPojo directorAllowanceStandardPojo){
        directorAllowanceStandardService.updateDirectorAllowance(directorAllowanceStandardPojo);
    }
    @RequestMapping("/del_director_allowance")
    @ResponseBody
    public void delDirectorAllowance(@RequestParam String ids){
        directorAllowanceStandardService.delDirectorAllowance(ids);
    }
}
