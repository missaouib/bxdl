package com.hzcf.core.mapper;

import com.hzcf.pojo.insurancePolicy.InsPolicyAttEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InsPolicyAttMapper {
    void addInsPolicyAtt(Map<String, Object> ins_policy_att_list);

    List<InsPolicyAttEntity> selectInsPolicyAttEntityByKey(Map<String, Object> params);

    void updateInsPolicyAtt(Map<String, Object> ipal);

    void deleteById(Map<String, Object> ipalId);

    void deleteByPolicyIdAndType(@Param("policyId") String policyId, @Param("type") String type);
}
