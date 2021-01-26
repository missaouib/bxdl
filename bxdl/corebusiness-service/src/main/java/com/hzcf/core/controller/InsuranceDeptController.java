package com.hzcf.core.controller;


import com.hzcf.core.service.InsuranceDeptService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保险部门管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/insurance_dept")
public class InsuranceDeptController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 分页查询保险部门
     * */
    @Autowired
    private InsuranceDeptService insuranceDeptService;
        @RequestMapping("/do_insurance_dept")
        @ResponseBody
        public PageModel doInsuranceDept(@RequestParam Map<String,Object> params){
        return insuranceDeptService.getInsuranceDeptList(params);
    }
    /**
     * 不分页查询保险部门
     * */
    @RequestMapping("/findInsurCompanys")
    @ResponseBody
    public List<InsuranceDept> findInsurCompanys(@RequestBody Map<String,Object> params){
    	List<InsuranceDept> insuranceDept=insuranceDeptService.findInsurCompanys(params);
    	return insuranceDept;
    			
    }
    /**
     * 添加部门
     * */
    @RequestMapping("/add_insurance_dept")
    @ResponseBody
    public void addInsuranceDept(@RequestBody InsuranceDept insuranceDept){
         insuranceDeptService.addInsuranceDept(insuranceDept);
    }
    /**
     * 修改部门
     * */
    @RequestMapping("/update_insurance_dept")
    @ResponseBody
    public void updateInsuranceDept(@RequestBody InsuranceDept insuranceDept){
         insuranceDeptService.updateInsuranceDept(insuranceDept);
    }
    /**
     * 删除部门
     * */
    @RequestMapping("/delete_insurance_dept")
    @ResponseBody
    public void deleteInsuranceDept(@RequestParam String params){
        insuranceDeptService.deleteInsuranceDept(params);
    }

    /**
     * 删除部门
     * */
    @RequestMapping("/delete_insurance_org")
    @ResponseBody
    public void deleteInsuranceOrg(@RequestParam String params){
        insuranceDeptService.deleteInsuranceOrg(params);
    }
    /**
     * 查看详情
     * */
    @RequestMapping("/select_insurance_basic_dept")
    @ResponseBody
    public InsuranceDept selectInsuranceBasicDept(@RequestParam Map<String,Object> params){
       return insuranceDeptService.selectInsuranceBasicDept(params);
    }
    /**
     * 根据部门ID，查询该部门子集
     * */
    @RequestMapping("/select_subset_by_id")
    @ResponseBody
    public  InsuranceDept selectSubsetById(@RequestBody String id){
        return insuranceDeptService.selectSubsetById(id);

    }

    /**
     * 根据部门ID，查询该部门子集
     * */
    @RequestMapping("/select_subset_org_by_id")
    @ResponseBody
    public  InsuranceDept selectSubsetOrgById(@RequestBody String id){
        return insuranceDeptService.selectSubsetOrgById(id);

    }

    /**
     * 查询所有部门
     * */
    @RequestMapping("/select_all_org")
    @ResponseBody
    private List<Map<String,Object>> selectAllOrg(){
        List<Map<String,Object>> map =insuranceDeptService.selectAllOrg();
        return map;
    }

    @RequestMapping(value = "/selectOrg", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> selectOrg(@RequestBody Map<String, Object> pra){
        return insuranceDeptService.selectOrg(pra);
    }

    /**
     * <p>按照名称或代码查询已经存在的保险公司</p>
     * @param name  名称
     * @param code  代码
     * @return
     */
    @RequestMapping(value = "/exist_org", method = RequestMethod.POST)
    @ResponseBody
    public boolean findExistOrgByNameOrCode(@RequestParam(value="id", required = false) Long id, @RequestParam("name")String name, @RequestParam("code")String code){
        return insuranceDeptService.findExistOrgByNameOrCode(id, name, code);
    }


    @RequestMapping(value = "/insertImportList", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object>  insertImportList(@RequestBody Map<String, Object> p){
          Map<String, Object> map = new HashMap<String, Object>();
        try {
            insuranceDeptService.insertImportList(p);
            map.put("code", 200);
        } catch (Exception e) {
            map.put("code", 400);
            logger.error("批量导入保险公司异常",e);
        }
        return map;
    }
}
