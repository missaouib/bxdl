package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjust;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjustExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface InsProtocolRateAdjustMapper {
    long countByExample(InsProtocolRateAdjustExample example);

    int deleteByExample(InsProtocolRateAdjustExample example);

    int deleteByPrimaryKey(Long adjustId);

    int insert(InsProtocolRateAdjust record);

    int insertSelective(InsProtocolRateAdjust record);

    List<InsProtocolRateAdjust> selectByExample(InsProtocolRateAdjustExample example);

    InsProtocolRateAdjust selectByPrimaryKey(Long adjustId);

    int updateByExampleSelective(@Param("record") InsProtocolRateAdjust record, @Param("example") InsProtocolRateAdjustExample example);

    int updateByExample(@Param("record") InsProtocolRateAdjust record, @Param("example") InsProtocolRateAdjustExample example);

    int updateByPrimaryKeySelective(InsProtocolRateAdjust record);

    int updateByPrimaryKey(InsProtocolRateAdjust record);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	void insertSelectiveAndReturnId(InsProtocolRateAdjust rateAdjust);

	Map<String, Object> findParamByAdjustId(Long valueOf);
}