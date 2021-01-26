package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.insurance.protocol.InsProtocolPersistencyBonus;
import com.hzcf.pojo.insurance.protocol.PersistencyBonusRule;

public interface PersistencyBonusMapper {

	InsProtocolPersistencyBonus selectPb(Map<String, Object> params);

	int addPb(InsProtocolPersistencyBonus ippbs);

	List<PersistencyBonusRule> getPbrList(Map<String, Object> params);

	List<Object> getOutProducts(Map<String, Object> params);

	void addPbr(List<PersistencyBonusRule> pbr);

	void updatePbr(List<PersistencyBonusRule> pbr);

	void deletePbrs(Map<String, Object> params);

	void updatePb(InsProtocolPersistencyBonus ippbs);

}
