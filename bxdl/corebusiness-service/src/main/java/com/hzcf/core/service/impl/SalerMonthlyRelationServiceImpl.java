package com.hzcf.core.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.hzcf.core.mapper.SalerMonthlyRelationMapper;
import com.hzcf.core.service.SalerMonthlyRelationService;
import com.hzcf.pojo.insurance.sales.SalerMonthlyRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/4/29.
 */
@Service
public class SalerMonthlyRelationServiceImpl implements SalerMonthlyRelationService {
    @Autowired
    private SalerMonthlyRelationMapper salerMonthlyRelationMapper;

    @Override
    public List<Map<String,Object>> getSalerRelationByMoth(Map<String, Object> par) {
        return salerMonthlyRelationMapper.getSalerRelationByMoth(par);
    }

    @Override
    public long addSalerMonthlyRelation(String month) {
        return salerMonthlyRelationMapper.addSalerMonthlyRelation(month);
    }

    @Override
    public int insertSalerMonthlyRelation(SalerMonthlyRelation salerMonthlyRelation) {
        return salerMonthlyRelationMapper.insertSelective(salerMonthlyRelation);
    }

}
