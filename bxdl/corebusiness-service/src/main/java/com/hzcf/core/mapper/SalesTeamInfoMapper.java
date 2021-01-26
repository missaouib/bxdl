package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.insurance.SalesTeamInfo;
import org.apache.ibatis.annotations.Param;

/**
 *保险部门管理
 */
public  interface SalesTeamInfoMapper {
	/*
     * 添加部门
     * */
    int addSalesTeamInfo(SalesTeamInfo salesTeamInfo);

	List<Map<String, Object>> getSalesTeamInfoList(Map<String, Object> params);

	long getSalesTeamInfoListSize(Map<String, Object> params);

	int findPreCode(Map<String, Object> params);

	SalesTeamInfo selectById(Map<String, Object> params);

	List<SalesTeamInfo> getParents(Map<String, Object> params);

	void updateSalesTeamInfo(SalesTeamInfo salesTeamInfo);

    int batchAddSalesTeam(List<Map> list);

    List<SalesTeamInfo> getChildSalesTeamByParentCode(String salesTeamCode);

	void updateSalesTeamTreeCode(List<SalesTeamInfo> allChildTeamList);

    List<SalesTeamInfo> selectByCondition(Map<String, Object> params);

    void updateparentSaleTeamCode(@Param("oldParentSaleTeamCode") String oldParentSaleTeamCode, @Param("parentSaleTeamCode") String parentSaleTeamCode);
}
