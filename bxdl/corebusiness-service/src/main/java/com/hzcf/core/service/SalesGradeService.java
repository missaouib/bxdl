package com.hzcf.core.service;
import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesGrade;

public interface SalesGradeService{

	void addSalesGrade(SalesGrade salesGrade);

	void updateSalesGrade(SalesGrade salesGrade);

	PageModel getSalesGradePage(Map<String, Object> params);

	SalesGrade selectById(Map<String, Object> params);

	List<SalesGrade> getSalesGradeList(Map<String, Object> params);
   
}
