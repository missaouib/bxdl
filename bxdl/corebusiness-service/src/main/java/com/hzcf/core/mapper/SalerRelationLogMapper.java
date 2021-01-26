package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.sales.SalerRelationLog;

import java.util.List;
import java.util.Map;

public interface SalerRelationLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SalerRelationLog record);

    int insertSelective(SalerRelationLog record);

    SalerRelationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SalerRelationLog record);

    int updateByPrimaryKey(SalerRelationLog record);

    int batchSalerRelationLog(List<Map> relationLogsList);
}