package com.hzcf.core.service;

import com.hzcf.pojo.insurance.sales.SalerMonthlyRelation;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/4/29.
 */
public interface SalerMonthlyRelationService {

    List<Map<String,Object>> getSalerRelationByMoth(Map<String,Object> par);

    long addSalerMonthlyRelation(String month);

    int insertSalerMonthlyRelation(SalerMonthlyRelation salerMonthlyRelation);
}
