package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.protocol.InsProtocolSubsidy;
import com.hzcf.pojo.insurance.protocol.InsProtocolSubsidyExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface InsProtocolSubsidyMapper {
    long countByExample(InsProtocolSubsidyExample example);

    int deleteByExample(InsProtocolSubsidyExample example);

    int deleteByPrimaryKey(Long subsidyId);

    int insert(InsProtocolSubsidy record);

    int insertSelective(InsProtocolSubsidy record);

    List<InsProtocolSubsidy> selectByExampleWithBLOBs(InsProtocolSubsidyExample example);

    List<InsProtocolSubsidy> selectByExample(InsProtocolSubsidyExample example);

    InsProtocolSubsidy selectByPrimaryKey(Long subsidyId);

    int updateByExampleSelective(@Param("record") InsProtocolSubsidy record, @Param("example") InsProtocolSubsidyExample example);

    int updateByExampleWithBLOBs(@Param("record") InsProtocolSubsidy record, @Param("example") InsProtocolSubsidyExample example);

    int updateByExample(@Param("record") InsProtocolSubsidy record, @Param("example") InsProtocolSubsidyExample example);

    int updateByPrimaryKeySelective(InsProtocolSubsidy record);

    int updateByPrimaryKeyWithBLOBs(InsProtocolSubsidy record);

    int updateByPrimaryKey(InsProtocolSubsidy record);

	InsProtocolSubsidy findProtocolSubsidyByProtocolId(String protocolId);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	InsProtocolSubsidy findSubsidyByProtocolId(Long protocolId);

	void deleteSubsidyByProtocolId(Long protocolId);

	void updateRateTypeByProtocolId(Map<String, Object> map);

	void insertEpProtocolSubsidy(List<InsProtocolSubsidy> pList);
}