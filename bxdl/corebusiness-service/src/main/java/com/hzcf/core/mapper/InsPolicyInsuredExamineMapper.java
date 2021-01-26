package com.hzcf.core.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface InsPolicyInsuredExamineMapper {
    /**
     * 增加审核
     * */
    void addInsPolicyInsuredExamine(Map<String, Object> map);
    /**
     * 修改生成批次
     * */
    void updateInsExamine(Map<String, Object> map);

    Map<String, Object> selectSizeByPolicy(@Param("policyId") String toString);

}
