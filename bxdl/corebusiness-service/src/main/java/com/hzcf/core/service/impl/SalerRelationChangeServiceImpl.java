package com.hzcf.core.service.impl;

import cn.hutool.json.JSONUtil;
import com.hzcf.core.mapper.SalerRelationChangeMapper;
import com.hzcf.core.mapper.SalerRelationLogMapper;
import com.hzcf.core.service.SalerRelationChangeService;
import com.hzcf.pojo.insurance.sales.SalerRelationChange;
import com.hzcf.pojo.insurance.sales.SalerRelationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by qin lina on 2020/5/14.
 */
@Service
public class SalerRelationChangeServiceImpl implements SalerRelationChangeService {
    @Autowired
    private SalerRelationChangeMapper  salerRelationChangeMapper;
    @Autowired
    private SalerRelationLogMapper salerRelationLogMapper;

    @Override
    public long executeRelationChange(String relationMonth) {
        return salerRelationChangeMapper.executeRelationChange(relationMonth);
    }

    @Override
    @Transactional
    public int insertSelective(Map<String, Object> params) {
        Object salerRelationChangeObj = params.get("salerRelationChange");
        Object salerRelationLogObj =  params.get("salerRelationLog");
        SalerRelationChange salerRelationChange = JSONUtil.toBean(JSONUtil.toJsonStr(salerRelationChangeObj), SalerRelationChange.class);
        SalerRelationLog salerRelationLog = JSONUtil.toBean(JSONUtil.toJsonStr(salerRelationLogObj), SalerRelationLog.class);
         salerRelationLogMapper.insert(salerRelationLog);
        return salerRelationChangeMapper.insertSelective(salerRelationChange);
    }

    @Override
    public SalerRelationChange selectRelationBySalerIdAndMonth(Map<String, Object> params) {
        return salerRelationChangeMapper.selectRelationBySalerIdAndMonth(params);
    }

    @Override
    public int updateByPrimaryKeySelective(SalerRelationChange salerRelationChange) {
        return salerRelationChangeMapper.updateByPrimaryKey(salerRelationChange);
    }

    @Override
    @Transactional
    public void updateRelationChange(Map<String, Object> params) {
        Object salerRelationChangeObj = params.get("salerRelationChange");
        Object salerRelationLogObj =  params.get("salerRelationLog");
        SalerRelationChange salerRelationChange = JSONUtil.toBean(JSONUtil.toJsonStr(salerRelationChangeObj), SalerRelationChange.class);
        SalerRelationLog salerRelationLog = JSONUtil.toBean(JSONUtil.toJsonStr(salerRelationLogObj), SalerRelationLog.class);

        salerRelationLogMapper.insert(salerRelationLog);
        salerRelationChangeMapper.updateRelationChange(salerRelationChange);
    }


}
