package com.hzcf.core.mapper;

import com.hzcf.pojo.insurancePolicy.InsPolicyHolderEntity;

import java.util.List;
import java.util.Map;

public interface InsPolicyHolderMapper {
    void addInsPolicyHolder(Map<String, Object> ins_policy_holder);

    InsPolicyHolderEntity selectInsPolicyHolderEntityByKey(Map<String, Object> params);

    void updateInsPolicyHolder(Map<String, Object> ins_policy_holder);

    int batchAddInsPolicyHolder(List<Map> dataList);
}
