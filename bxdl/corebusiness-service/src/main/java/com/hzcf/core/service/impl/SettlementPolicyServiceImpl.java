package com.hzcf.core.service.impl;

import com.hzcf.core.mapper.SettlementPolicyMapper;
import com.hzcf.core.service.SettlementPolicyService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.insurancePolicy.SettlementPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 可结算列表
 * Created by qin lina on 2020/4/30.
 */
@SuppressWarnings("ALL")
@Service
public class SettlementPolicyServiceImpl implements SettlementPolicyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SettlementPolicyMapper settlementPolicyMapper;
    @Override
    public void updateSettlementStatus(List<Long> idList, String settlementStatus) {
         List<List<?>> lists = PublicUtil.splitList(idList, 50);
        lists.forEach(list->{
             settlementPolicyMapper.updateSettlementStatus((List<Long>) list,settlementStatus);
        });
    }

    @Override
    public List<SettlementPolicy> getSettlementPolicyList() {
        return settlementPolicyMapper.getSettlementPolicyList();
    }

    @Override
    public void batchAddSettlementPolicy(List<SettlementPolicy> settlementPolicies) {
        settlementPolicyMapper.batchAddSettlementPolicy(settlementPolicies);
    }

    @Override
    public PageModel searchSettlementPolicyList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSizeInt", Integer.valueOf(params.get("pageSize").toString()));
        params.put("pageNoInt", Integer.valueOf(params.get("pageNo").toString()));
        logger.info("查询条件：" + params);

        List<Map<String, Object>> list = settlementPolicyMapper.searchSettlementPolicyList(params);
        long size = settlementPolicyMapper.searchSettlementPolicyCount(params);
       // logger.info("查询数据" + list.toString() + "条数" + size);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public int submitAuditResult(Map<String, Object> params) {
        return settlementPolicyMapper.submitAuditResult(params);
    }
}
