package com.hzcf.core.controller;

import com.hzcf.core.service.EmpWorkGroupService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/empWorkGroup")
@Controller
public class EmpWorkGroupController {
    public static Log logger = LogFactory.getLog(EmpWorkGroupController.class);
@Autowired
private EmpWorkGroupService empWorkGroupService;
    @RequestMapping(value = "/listTreeDeptGroup", method = RequestMethod.POST)
    @ResponseBody
    List<Map<String, Object>> findDepartmentGroupList(@RequestParam Map<String,Object> params){
        List<Map<String, Object>> deptLists = null;
        try {
            deptLists = empWorkGroupService.findDepartmentGroupList(params);
        }catch (Exception e){
            logger.info(e.getMessage(),e);

        }
        return  deptLists;
    }

    @RequestMapping(value = "/findDeptGroupCheckList", method = RequestMethod.POST)
    @ResponseBody
    List<Map<String, Object>> findDeptGroupCheckList(@RequestParam Map<String, Object> params){
        List<Map<String, Object>> deptLists = null;
        try {
            deptLists = empWorkGroupService.findDeptGroupCheckList(params);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
        }
        return  deptLists;
    }

    @RequestMapping(value = "/insertWorkGroups", method = RequestMethod.POST)
    @ResponseBody
    Map<String,Object> insertWorkGroups(@RequestParam(value="workGroupsStr") String workGroupsStr,@RequestParam(value="employeeId") String employeeId){
        Map<String,Object> msg = new HashMap<String,Object>();
        try {
            empWorkGroupService.insertWorkGroups(workGroupsStr,employeeId);
            msg.put("code", "200");
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            msg.put("code", "400");
        }
        return  msg;
    }
}
