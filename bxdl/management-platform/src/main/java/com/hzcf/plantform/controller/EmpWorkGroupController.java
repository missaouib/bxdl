package com.hzcf.plantform.controller;

import com.alibaba.fastjson.JSON;
import com.hzcf.plantform.feign.EmpWorkGroupFeignClient;
import com.hzcf.plantform.feign.insurance.InsuranceSalesInfoClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.util.ReturnMsgData;
import com.hzcf.pojo.empWorkGroup.EmpWorkGroupPojo;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/empWorkGroup")
@Controller
public class EmpWorkGroupController {
    public static Log logger = LogFactory.getLog(EmpWorkGroupController.class);
    @Autowired
    private EmpWorkGroupFeignClient empWorkGroupFeignClient;
@Autowired
private SalesOrgInfoClient salesOrgInfoClient;
@Autowired
private InsuranceSalesInfoClient insuranceSalesInfoClient;
    @RequiresPermissions("workGroup:update") // 权限管理;
    @RequestMapping("/updateWorkGroup")
    public String updateWorkGroup(HttpServletRequest request, Model model ){
        Object insuranceSalesId = request.getParameter("id");
        model.addAttribute("employeeId",insuranceSalesId);
        return  "workGroup/dept_group_select";
    }
    @ResponseBody
    @RequestMapping("/listTreeDeptGroup")
    public Map<String,Object> listTreeDeptGroup(HttpServletRequest request,Model model){
        Map<String,Object> data = new HashMap<String,Object>();
        Object employeeId = request.getParameter("eid");
        List<Map<String, Object>> deptLists = null;
        List<Map<String, Object>> checkDeptLists = null;
        Map<String,Object> params = new HashMap<String,Object>();
        try {
            params.put("employeeId",employeeId);
            params.put("insuranceSalesId",employeeId);
            //员工信息
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(params);
            Long salesOrgId = insuranceSalesInfo.getSalesOrgId();
            params.put("salesOrgId",salesOrgId);
            //工作组部门list
            deptLists = empWorkGroupFeignClient.findDepartmentGroupList(params);
            //员工所属部门
            String deptNames = salesOrgInfoClient.selectDeptNamesByOrgId(params);
            //员工现工作组
            checkDeptLists = empWorkGroupFeignClient.findDeptGroupCheckList(params);
            String deptIds = "";
            String checkedStatus = "";
            String halfStatus = "";
            for (Map<String, Object> map : checkDeptLists) {
                deptIds += map.get("dept_id") + ",";
                checkedStatus += map.get("checked_status") + ",";
                halfStatus += map.get("half_status") + ",";
            }
            if (checkDeptLists.size() > 1) {
                data.put("deptIds", deptIds.substring(0, deptIds.length()-1));
                data.put("checkedStatus", checkedStatus.substring(0, checkedStatus.length()-1));
                data.put("halfStatus", halfStatus.substring(0, halfStatus.length()-1));
            }
            data.put("deptLists",deptLists);
            data.put("deptNames",deptNames);
            data.put("empName",insuranceSalesInfo.getInsuranceSaler());
            data.put("employeeId",employeeId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("/addWorkGroup")
    public Map<String,Object> addWorkGroup(@RequestParam Integer employeeId,@RequestParam String deptIds, @RequestParam String checkedStatus, @RequestParam String halfStatus) {
        Map<String,Object> msg =null;
        try {
            String[] deptIdsArray = {};
            String[] checkedStatusArray = {};
            String[] halfStatusArray = {};
            if(StringUtil.isNotBlank(deptIds) && StringUtil.isNotBlank(checkedStatus) && StringUtil.isNotBlank(halfStatus)){
                deptIdsArray = deptIds.split(",");
                checkedStatusArray = checkedStatus.split(",");
                halfStatusArray = halfStatus.split(",");
            }
            List<EmpWorkGroupPojo> workGroups = new ArrayList<EmpWorkGroupPojo>();
            EmpWorkGroupPojo workGroup = null;
            for (int i = 0; i < deptIdsArray.length; i++) {
                workGroup = new EmpWorkGroupPojo();
                workGroup.setInsuranceSalesId(employeeId);
                String s = deptIdsArray[i];
                Map<String,Object> params = new HashMap<>();
                params.put("salesOrgCode", s);
                List<SalesOrgInfo> salesOrgs = salesOrgInfoClient.findSalesOrgs(params);
                SalesOrgInfo salesOrgInfo = salesOrgs.get(0);
                workGroup.setSalesOrgId(Integer.valueOf(salesOrgInfo.getSalesOrgId().toString()));
                workGroup.setCheckedStatus(checkedStatusArray[i]);
                workGroup.setHalfStatus(halfStatusArray[i]);
                workGroups.add(workGroup);
            }
            String workGroupsStr = JSON.toJSONString(workGroups);
            msg= empWorkGroupFeignClient.insertWorkGroups(workGroupsStr,employeeId.toString());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            msg = new HashMap<String,Object>();
            msg.put("code", "400");
        }
        return msg;
    }


}
