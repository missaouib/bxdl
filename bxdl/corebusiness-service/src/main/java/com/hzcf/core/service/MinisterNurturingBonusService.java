package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.MinisterNurturingBonus;

public interface MinisterNurturingBonusService {

	PageModel selectMinisterNurturingBonus(Map<String, Object> paras);

	void updateMinisterNurturingBonus(MinisterNurturingBonus ministerNurturingBonus);

	void delMinisterNurturingBonus(String ids);

	void addMinisterNurturingBonus(MinisterNurturingBonus ministerNurturingBonus);

	List<MinisterNurturingBonus> getMinisterNurturingList();

	int checkMinisterSize(Map<String, Object> params);
	
}