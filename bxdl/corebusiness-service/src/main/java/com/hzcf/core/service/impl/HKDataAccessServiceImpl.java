package com.hzcf.core.service.impl;

import com.hzcf.core.async.AsyncTaskService;
import com.hzcf.core.mapper.CommonMapper;
import com.hzcf.core.mapper.InsuranceSalesInfoMapper;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.service.HKDataAccessService;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Component
public class HKDataAccessServiceImpl implements HKDataAccessService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int PAGE_SIZE = 300;

    @Autowired
    CommonMapper commonMapper;
    @Autowired
    AsyncTaskService asyncTaskService;
    @Autowired
    InsuranceSalesInfoMapper insuranceSalesInfoMapper;
    @Autowired
    SalesOrgInfoMapper salesOrgInfoMapper;

    @Override
    public void hKSalersDataAccess(Map<String, Object> params) {
        logger.info("同步人员销售机构信息========开始");
        int startIndex = 0;
        params.put("pageSize", PAGE_SIZE);

        int dataSize = commonMapper.getSalesNoticyHKLogsSize(params);
        logger.info("本次同步销售人员数量={}",dataSize);
        if(dataSize <= 0) {
            return;
        }
        int page = (dataSize / PAGE_SIZE);
        page = ((dataSize % PAGE_SIZE) >= 1) ? (page +1) : page;

        //分页同步销售人员数据
        for (int i = 0; i < page; i++) {
            startIndex = i * PAGE_SIZE;
            params.put("startIndex", startIndex);
            List<InsuranceSalesInfo> insuranceSalesInfos  = insuranceSalesInfoMapper.getAccessHKInsuranceSalesInfos(params);
            asyncTaskService.salesHkNotice(insuranceSalesInfos);
        }

        logger.info("同步人员销售机构信息========结束");
    }

    @Override
    public void hKSaleOrgsDataAccess(Map<String, Object> params) {
        logger.info("同步销售机构信息========开始");
        int startIndex = 0;
        params.put("pageSize", PAGE_SIZE);

        int dataSize = commonMapper.getSaleOrgsNoticyHKLogsSize(params);
        logger.info("本次同步销售机构数量={}",dataSize);
        if(dataSize > 0) {
            List<SalesOrgInfo> salesOrgInfos  = salesOrgInfoMapper.getAccessHkSaleOrgInfos(params);
            asyncTaskService.orgsHkNotice(salesOrgInfos);
        }

        logger.info("同步销售机构信息========结束");
    }
}
