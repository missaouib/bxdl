package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.protocol.InsProtocolCost;
import com.hzcf.pojo.insurance.protocol.InsProtocolCostExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface InsProtocolCostMapper {
    long countByExample(InsProtocolCostExample example);

    int deleteByExample(InsProtocolCostExample example);

    int deleteByPrimaryKey(Long protocolCostId);

    int insert(InsProtocolCost record);

    int insertSelective(InsProtocolCost record);

    List<InsProtocolCost> selectByExample(InsProtocolCostExample example);

    InsProtocolCost selectByPrimaryKey(Long protocolCostId);

    int updateByExampleSelective(@Param("record") InsProtocolCost record, @Param("example") InsProtocolCostExample example);

    int updateByExample(@Param("record") InsProtocolCost record, @Param("example") InsProtocolCostExample example);

    int updateByPrimaryKeySelective(InsProtocolCost record);

    int updateByPrimaryKey(InsProtocolCost record);
    
    List<Map<String, Object>> getProtocolCostListByPage(Map<String, Object> paramsCondition);
    
    Long getProtocolCostCount(Map<String, Object> paramsCondition);
}