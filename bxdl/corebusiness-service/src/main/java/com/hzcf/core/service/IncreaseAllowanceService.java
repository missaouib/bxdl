package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.IncreaseAllowance;

public interface IncreaseAllowanceService {

	PageModel selectIncreaseAllowance(Map<String, Object> paras);

	void updateIncreaseAllowance(IncreaseAllowance exhibitionAllowance);

	void addIncreaseAllowance(IncreaseAllowance exhibitionAllowance);

	List<IncreaseAllowance> getIncreaseAllowanceList();

	int checkIncreaseAllowanceSize(Map<String, Object> params);
	
}
