package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.protocol.InsInsideStandardRateConfig;
import com.hzcf.pojo.insurance.protocol.InsInsideStandardRateConfigExample;
import com.hzcf.pojo.insurance.protocol.InsStandardRateConfig;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface InsInsideStandardRateConfigMapper {
    long countByExample(InsInsideStandardRateConfigExample example);

    int deleteByExample(InsInsideStandardRateConfigExample example);

    int deleteByPrimaryKey(Long insideStandardId);

    int insert(InsInsideStandardRateConfig record);

    int insertSelective(InsInsideStandardRateConfig record);

    List<InsInsideStandardRateConfig> selectByExample(InsInsideStandardRateConfigExample example);

    InsInsideStandardRateConfig selectByPrimaryKey(Long insideStandardId);

    int updateByExampleSelective(@Param("record") InsInsideStandardRateConfig record, @Param("example") InsInsideStandardRateConfigExample example);

    int updateByExample(@Param("record") InsInsideStandardRateConfig record, @Param("example") InsInsideStandardRateConfigExample example);

    int updateByPrimaryKeySelective(InsInsideStandardRateConfig record);

    int updateByPrimaryKey(InsInsideStandardRateConfig record);

	void insertInsideStandardBatch(List<InsInsideStandardRateConfig> list);

	List<InsInsideStandardRateConfig> getUpdateProtocolInStandardList(Map<String, Object> paramsCondition);

	Map<String, Object> findInStandardByProtocolId(Long protocolId);

	void deleteInStandardByProtocolId(Long protocolId);
}