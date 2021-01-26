package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.insurance.protocol.InsStandardRateConfig;

public interface InsStandardRateConfigMapper {

    int deleteByPrimaryKey(Long standardId);

    int insert(InsStandardRateConfig record);

    int insertSelective(InsStandardRateConfig record);

    InsStandardRateConfig selectByPrimaryKey(Long standardId);

    int updateByPrimaryKeySelective(InsStandardRateConfig record);

    int updateByPrimaryKey(InsStandardRateConfig record);

	void insertStandardBatch(List<InsStandardRateConfig> list);

	List<InsStandardRateConfig> getUpdateProtocolStandardList(Map<String, Object> paramsCondition);

	Map<String, Object> findStandardByProtocolId(Long protocolId);

	void deleteStandardByProtocolId(Long protocolId);

	Map<String, Object> findInStandardByProtocolId(Long protocolId);

}