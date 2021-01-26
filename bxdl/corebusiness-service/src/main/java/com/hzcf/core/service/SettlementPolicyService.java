package com.hzcf.core.service;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurancePolicy.SettlementPolicy;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/4/30.
 */
public interface SettlementPolicyService {
    //根据主键id  修改发放状态
    void updateSettlementStatus(List<Long> idList, String settlementStatus);

    /**
     * 获取可结算佣金保单集合
     * @return
     */
    List<SettlementPolicy> getSettlementPolicyList();

    /**
     * 批量新增可结算保单数据
     * @param settlementPolicies
     */
    void batchAddSettlementPolicy(List<SettlementPolicy> settlementPolicies);

    /**
     * 结算保单列表数据
     * @param params
     * @return
     */
    PageModel searchSettlementPolicyList(Map<String, Object> params);

    int submitAuditResult(Map<String,Object> params);
}
