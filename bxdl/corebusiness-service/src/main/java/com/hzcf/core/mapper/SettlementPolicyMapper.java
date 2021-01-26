package com.hzcf.core.mapper;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurancePolicy.SettlementPolicy;
import com.hzcf.pojo.insurancePolicy.SettlementPolicyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SettlementPolicyMapper {
    int countByExample(SettlementPolicyExample example);

    int deleteByExample(SettlementPolicyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SettlementPolicy record);

    int insertSelective(SettlementPolicy record);

    List<SettlementPolicy> selectByExample(SettlementPolicyExample example);

    SettlementPolicy selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SettlementPolicy record, @Param("example") SettlementPolicyExample example);

    int updateByExample(@Param("record") SettlementPolicy record, @Param("example") SettlementPolicyExample example);

    int updateByPrimaryKeySelective(SettlementPolicy record);

    int updateByPrimaryKey(SettlementPolicy record);

    void updateSettlementStatus(@Param("idList") List<Long> idList, @Param("settlementStatus") String settlementStatus);

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
     * 查询结算列表数据
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchSettlementPolicyList(Map<String, Object> paramMap);

    /**
     * 查询结算列表数据记录数
     * @param paramMap
     * @return
     */
    Long searchSettlementPolicyCount(Map<String, Object> paramMap);

    int submitAuditResult(Map<String,Object> params);
}