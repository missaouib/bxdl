package com.hzcf.core.service;
import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesTeamInfo;
import com.hzcf.pojo.insurance.SalesTeamLeaders;

public interface SalesTeamLeadersService{

	void addSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders);

	void updateSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders);

	PageModel getSalesTeamLeadersPage(Map<String, Object> params);

	SalesTeamLeaders selectById(Map<String, Object> params);

	List<SalesTeamLeaders> getSalesTeamLeadersList(Map<String, Object> params);

	void disableLeaders(Map<String, Object> params);

	void updateTeams(SalesTeamInfo salesTeamInfo, List<SalesTeamInfo> allChildTeamList, SalesTeamInfo salesTeamInfoOld);
   
}
