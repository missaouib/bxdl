package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.product.InsuranceTypeMapping;

public interface InsuranceTypeMappingService {

	PageModel selectInsuranceType(Map<String, Object> paras);

	void updateInsuranceType(InsuranceTypeMapping insuranceTypeMapping);

	void delInsuranceType(String ids);

	void addInsuranceType(InsuranceTypeMapping insuranceTypeMapping);

	InsuranceTypeMapping selectInsuranceTypeByid(Map<String, Object> paras);

	List<InsuranceTypeMapping> findInsuranceTypeMapping(Map<String, Object> params);

	
}