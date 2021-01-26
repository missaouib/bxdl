package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectorNurtureBonus;
import com.hzcf.pojo.parameter.MinisterNurturingBonus;
import com.hzcf.pojo.product.InsuranceTypeMapping;

public interface DirectorNurtureBonusService{


	PageModel selectDirectorNurtureBonus(Map<String, Object> paras);

	void addDirectorNurtureBonus(DirectorNurtureBonus directorNurtureBonus);

	void updateDirectorNurtureBonus(DirectorNurtureBonus directorNurtureBonus);

	void delDirectorNurtureBonus(String ids);

	List<DirectorNurtureBonus> getDirectorNurtureBonusList();

	int checkDirectorSize(Map<String, Object> params);

}