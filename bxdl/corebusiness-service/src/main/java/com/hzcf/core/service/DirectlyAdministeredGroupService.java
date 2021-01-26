package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectlyAdministeredGroup;

public interface DirectlyAdministeredGroupService {

	PageModel selectDirectlyAdministeredGroup(Map<String, Object> paras);

	void updateDirectlyAdministeredGroup(DirectlyAdministeredGroup directlyAdministeredGroup);

	void addDirectlyAdministeredGroup(DirectlyAdministeredGroup directlyAdministeredGroup);

	List<DirectlyAdministeredGroup> getDirectlyAdministeredGroupList();

	int checkPersonalBonusSize(Map<String, Object> params);
	
}
