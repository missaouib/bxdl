package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.InsuranceTypeMappingService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.pojo.product.InsuranceTypeMapping;

@Controller
@RequestMapping("/Insurance_type_manager")
public class InsuranceTypeMappingController {
    @Autowired
    private InsuranceTypeMappingService insuranceTypeMappingService;

    @RequestMapping("/select_Insurance_type")
    @ResponseBody
    public PageModel selectInsuranceType(@RequestParam Map<String,Object> paras){
        return insuranceTypeMappingService.selectInsuranceType(paras);
    }
    @RequestMapping("/add_Insurance_type")
    @ResponseBody
    public void addInsuranceType(@RequestBody InsuranceTypeMapping insuranceTypeMapping){
    	insuranceTypeMappingService.addInsuranceType(insuranceTypeMapping);
    }
    @RequestMapping("/update_Insurance_type")
    @ResponseBody
    public void updateInsuranceType(@RequestBody InsuranceTypeMapping insuranceTypeMapping){
    	insuranceTypeMappingService.updateInsuranceType(insuranceTypeMapping);
    }
    @RequestMapping("/del_Insurance_type")
    @ResponseBody
    public void delInsuranceType(@RequestParam String ids){
    	insuranceTypeMappingService.delInsuranceType(ids);
    }
    
    @RequestMapping("/select_Insurance_type_byId")
    @ResponseBody
    public InsuranceTypeMapping selectInsuranceTypeByid(@RequestParam Map<String,Object> params){
    	
		return insuranceTypeMappingService.selectInsuranceTypeByid(params);
    }
    
    /**
     * 不分页查询保险公司产品类型
     * */
    @RequestMapping("/insurance_type_mappings")
    @ResponseBody
    public List<InsuranceTypeMapping> findInsuranceTypeMapping(@RequestBody Map<String,Object> params){
    	List<InsuranceTypeMapping> insuranceTypeMapping=insuranceTypeMappingService.findInsuranceTypeMapping(params);
    	return insuranceTypeMapping;
    			
    } 
    
   /* //更新状态0-正常，1-删除
    @RequestMapping("/update_type_status")
    @ResponseBody
    public void updateTypeStatus(@RequestBody InsuranceTypeMapping insuranceTypeMapping){
    	insuranceTypeMappingService.updateInsuranceType(insuranceTypeMapping);
    }*/
}
