package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.insurance.SalerInvitation;

/**
 *保险部门管理
 */
public  interface SalerInvitationMapper {

	void addSalerInvitation(SalerInvitation salerInvitation);

	List<Map<String, Object>> getSalerInvitationPage(Map<String, Object> params);

	long getSalerInvitationListSize(Map<String, Object> params);

	void updateSalerInvitation(SalerInvitation salerInvitation);

	SalerInvitation selectById(Map<String, Object> params);
		
}
