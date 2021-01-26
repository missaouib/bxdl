package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectlyUnderManager;

public interface DirectlyUnderManagerService {

	PageModel selectDirectlyUnderManager(Map<String, Object> paras);

	void updateDirectlyUnderManager(DirectlyUnderManager directlyUnderManager);

	void addDirectlyUnderManager(DirectlyUnderManager directlyUnderManager);

	List<DirectlyUnderManager> getDirectlyUnderManagerList();

	int checkDirectlyUnderSize(Map<String, Object> params);
	
}
