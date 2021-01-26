package com.hzcf.core.service;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalerInvitation;

public interface SalerInvitationService{

	void addSalerInvitation(SalerInvitation salerInvitation);

	void updateSalerInvitation(SalerInvitation salerInvitation);

	PageModel getSalerInvitationPage(Map<String, Object> params);

	SalerInvitation selectById(Map<String, Object> params);   
}
