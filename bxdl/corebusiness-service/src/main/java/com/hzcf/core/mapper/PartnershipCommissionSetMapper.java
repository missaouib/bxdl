package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.PartnershipCommissionSet;

public interface PartnershipCommissionSetMapper {
    int deleteByPrimaryKey(Long partnershipId);

    int insert(PartnershipCommissionSet record);

    int insertSelective(PartnershipCommissionSet record);

    PartnershipCommissionSet selectByPrimaryKey(Long partnershipId);

    int updateByPrimaryKeySelective(PartnershipCommissionSet record);

    int updateByPrimaryKey(PartnershipCommissionSet record);

	List<Map<String, Object>> getPartnershipCommissionSetList(Map<String, Object> params);

	long getPartnershipCommissionSetSize(Map<String, Object> params);

	PartnershipCommissionSet selectPartnershipCommissionSetDetail(Map<String, Object> params);

	List<PartnershipCommissionSet> findPartnershipCommissionSet(Map<String, Object> params);
	/**
	 *@描述  根据产品编号和组织机构查找  该组织机构下设置的该产品的基础佣金率
	 *@创建人 qin lina
	 *@创建时间 2020/4/28
	 */
	 Map<String, Object> selectCommissionRate(Map<String, Object> par);
}