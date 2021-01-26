package com.hzcf.plantform.feign.product;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.product.InsuranceTypeMapping;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface InsuranceTypeMappingClient {

	@RequestMapping(value = "/Insurance_type_manager/select_Insurance_type",method = RequestMethod.POST)
	public PageModel selectInsuranceType(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/Insurance_type_manager/add_Insurance_type",method = RequestMethod.POST)
	public void addInsuranceType(@RequestBody InsuranceTypeMapping insuranceTypeMapping);
	
	@RequestMapping(value = "/Insurance_type_manager/update_Insurance_type",method = RequestMethod.POST)
	public void updateInsuranceType(@RequestBody InsuranceTypeMapping insuranceTypeMapping);
	
	@RequestMapping(value = "/Insurance_type_manager/del_Insurance_type",method = RequestMethod.POST)
	public void delInsuranceType(@RequestParam(value ="ids")String ids);
	
	@RequestMapping(value = "/Insurance_type_manager/select_Insurance_type_byId",method = RequestMethod.POST)
	public InsuranceTypeMapping selectInsuranceTypeByid(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/Insurance_type_manager/insurance_type_mappings",method = RequestMethod.POST)
	public List<InsuranceTypeMapping> findInsuranceTypeMapping(Map<String, Object> paras);
	
}
