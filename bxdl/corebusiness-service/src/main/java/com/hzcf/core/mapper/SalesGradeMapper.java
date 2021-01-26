package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.SalesGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *保险部门管理
 */
public  interface SalesGradeMapper {

	void addSalesGrade(SalesGrade salesGrade);

	List<Map<String, Object>> getSalesGradePage(Map<String, Object> params);

	long getSalesGradeListSize(Map<String, Object> params);

	void updateSalesGrade(SalesGrade salesGrade);

	List<SalesGrade> getSalesGradeList(Map<String, Object> params);

	SalesGrade selectById(Map<String, Object> params);

	SalesGrade querySalesGradeByName(@Param("salesGradeName") String salesGradeName);

}
