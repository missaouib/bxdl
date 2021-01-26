package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.PartnershipCommissionSub;

public interface PartnershipCommissionSubMapper {
    int deleteByPrimaryKey(Long partnershipSubId);

    int insert(PartnershipCommissionSub record);

    int insertSelective(PartnershipCommissionSub record);

    PartnershipCommissionSub selectByPrimaryKey(Long partnershipSubId);

    int updateByPrimaryKeySelective(PartnershipCommissionSub record);

    int updateByPrimaryKey(PartnershipCommissionSub record);

	List<PartnershipCommissionSub> getPartnershipCommissionSubList(Map<String, Object> params);

	List<Map<String, Object>> getPartnershipListSubs(Map<String, Object> params);

	long getPartnershipListsSize(Map<String, Object> params);
}