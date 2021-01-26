package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.PersistencyBonusMapper;
import com.hzcf.core.service.PersistencyBonusService;
import com.hzcf.pojo.insurance.protocol.InsProtocolPersistencyBonus;
import com.hzcf.pojo.insurance.protocol.PersistencyBonusRule;

@Service
public class PersistencyBonusServiceImpl implements PersistencyBonusService{
	
	@Autowired
	private PersistencyBonusMapper persistencyBonusMapper;

	@Override
	public InsProtocolPersistencyBonus selectPb(Map<String, Object> params) {
		return persistencyBonusMapper.selectPb(params);
	}

	@Override
	public int addPb(InsProtocolPersistencyBonus ippbs) {
		return persistencyBonusMapper.addPb(ippbs);
	}
	
	@Override
	public void updatePb(InsProtocolPersistencyBonus ippbs) {
		persistencyBonusMapper.updatePb(ippbs);
	}	

	@Override
	public List<PersistencyBonusRule> getPbrList(Map<String, Object> params) {
		return persistencyBonusMapper.getPbrList(params);
	}

	@Override
	public List<Object> getOutProducts(Map<String, Object> params) {
		return persistencyBonusMapper.getOutProducts(params);
	}

	@Override
	public void addPbr(List<PersistencyBonusRule> pbr) {
		persistencyBonusMapper.addPbr(pbr);
	}

	@Override
	public void updatePbr(List<PersistencyBonusRule> pbr) {
		persistencyBonusMapper.updatePbr(pbr);
	}

	@Override
	public void deletePbrs(Map<String, Object> params) {
		persistencyBonusMapper.deletePbrs(params);	
	}
}
