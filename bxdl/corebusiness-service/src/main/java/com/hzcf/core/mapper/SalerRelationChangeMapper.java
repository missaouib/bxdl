package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.sales.SalerRelationChange;
import com.hzcf.pojo.insurance.sales.SalerRelationChangeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SalerRelationChangeMapper {
    int countByExample(SalerRelationChangeExample example);

    int deleteByExample(SalerRelationChangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SalerRelationChange record);

    int insertSelective(SalerRelationChange record);

    List<SalerRelationChange> selectByExample(SalerRelationChangeExample example);

    SalerRelationChange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SalerRelationChange record, @Param("example") SalerRelationChangeExample example);

    int updateByExample(@Param("record") SalerRelationChange record, @Param("example") SalerRelationChangeExample example);

    int updateByPrimaryKeySelective(SalerRelationChange record);

    int updateByPrimaryKey(SalerRelationChange record);

    long executeRelationChange(String relationMonth);

    SalerRelationChange selectRelationBySalerIdAndMonth(Map<String, Object> params);


    int batchSalerRelationChange(List<Map> list);

    int deleteSalerRelationChange(Map map);

    int batchUpdateRelationChange(List<Map> relationChangeUpdate);

    int updateRelationChange(SalerRelationChange relationChange);
}