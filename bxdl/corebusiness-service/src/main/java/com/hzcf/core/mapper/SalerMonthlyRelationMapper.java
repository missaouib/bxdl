package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.sales.SalerMonthlyRelation;
import com.hzcf.pojo.insurance.sales.SalerMonthlyRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SalerMonthlyRelationMapper {
    int countByExample(SalerMonthlyRelationExample example);

    int insert(SalerMonthlyRelation record);

    int insertSelective(SalerMonthlyRelation record);

    List<SalerMonthlyRelation> selectByExample(SalerMonthlyRelationExample example);

    int updateByExampleSelective(@Param("record") SalerMonthlyRelation record, @Param("example") SalerMonthlyRelationExample example);

    int updateByExample(@Param("record") SalerMonthlyRelation record, @Param("example") SalerMonthlyRelationExample example);

    List<Map<String,Object>> getSalerRelationByMoth(Map<String, Object> par);

    long addSalerMonthlyRelation(String month);
}