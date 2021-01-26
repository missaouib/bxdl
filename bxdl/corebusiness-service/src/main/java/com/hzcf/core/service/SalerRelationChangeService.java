package com.hzcf.core.service;

import com.hzcf.pojo.insurance.sales.SalerRelationChange;

import java.util.Map;

/**
 * Created by qin lina on 2020/5/14.
 */
public interface SalerRelationChangeService {
    long executeRelationChange(String relationMonth);

    int insertSelective(Map<String, Object> params);

    SalerRelationChange selectRelationBySalerIdAndMonth(Map<String, Object> params);

    int updateByPrimaryKeySelective(SalerRelationChange salerRelationChange);

    void updateRelationChange(Map<String, Object> params);
}
