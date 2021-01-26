package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.insurance.SalesTeamLeaders;

/**
 *保险部门管理
 */
public  interface SalesTeamLeadersMapper {

	void addSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders);

	List<Map<String, Object>> getSalesTeamLeadersPage(Map<String, Object> params);

	long getSalesTeamLeadersListSize(Map<String, Object> params);

	void updateSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders);

	List<SalesTeamLeaders> getSalesTeamLeadersList(Map<String, Object> params);

	SalesTeamLeaders selectById(Map<String, Object> params);

	void disableLeaders(Map<String, Object> params);
		
}
