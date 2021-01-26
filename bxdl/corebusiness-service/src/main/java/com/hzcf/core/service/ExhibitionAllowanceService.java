package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.ExhibitionAllowance;

public interface ExhibitionAllowanceService {

	PageModel selectExhibitionAllowance(Map<String, Object> paras);

	void updateExhibitionAllowance(ExhibitionAllowance exhibitionAllowance);

	void addExhibitionAllowance(ExhibitionAllowance exhibitionAllowance);

	List<ExhibitionAllowance> getExhibitionAllowanceList();

	int checkExhibitionAllowanceSize(Map<String, Object> params);
	
}
