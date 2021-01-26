package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.parameter.PartnershipCommissionSet;
import com.hzcf.pojo.parameter.PartnershipCommissionSub;

public interface PartnershipCommissionSetService {

	
	PageModel getPartnershipCommissionSetList(Map<String, Object> params);

	void addPartnershipCommissionSet(PartnershipCommissionSet partnershipCommissionSet);

	void updatePartnershipCommissionSet(PartnershipCommissionSet partnershipCommissionSet);

	PartnershipCommissionSet selectPartnershipCommissionSetDetail(Map<String, Object> params);

	List<PartnershipCommissionSub> getPartnershipCommissionSubList(Map<String, Object> params);

	PageModel getPartnershipListSub(Map<String, Object> params);

	void addPartnershipCommissionSub(PartnershipCommissionSub partnershipCommissionSub);

	void updatePartnershipCommissionSub(PartnershipCommissionSub partnershipCommissionSub);

	PageModel getPartnershipListSubByAdd(Map<String, Object> params);

	PageModel getPartnershipListSubEdit(Map<String, Object> params);

	List<PartnershipCommissionSet> findPartnershipCommissionSet(Map<String, Object> params);
	/**
	 *@描述  根据产品编号和组织机构查找  该组织机构下设置的该产品的基础佣金率
	 *@创建人 qin lina
	 *@创建时间 2020/4/28
	 */
    Map<String, Object> selectCommissionRate(Map<String, Object> par);

}
