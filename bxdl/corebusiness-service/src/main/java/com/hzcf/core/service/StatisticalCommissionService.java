package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.StatisticalCommission;

public interface StatisticalCommissionService {

	PageModel selectStatisticalCommission(Map<String, Object> paras);

	void updateStatisticalCommission(StatisticalCommission exhibitionAllowance);

	void addStatisticalCommission(StatisticalCommission exhibitionAllowance);

	List<StatisticalCommission> getStatisticalCommissionList();

	List<StatisticalCommission> getStatisticalCommissionListNow();

	
}
