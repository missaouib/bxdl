package com.hzcf.core.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface InsStateMapper {
    /**
     * 状态表新增状态
     * */
    void addInsState(Map<String, Object> map);

    /**
     * 修改保单  状态
     * */
    void updateTypeByPolicyId(@Param("id") Object policy_id, @Param("type") String insStateHis1);


}
