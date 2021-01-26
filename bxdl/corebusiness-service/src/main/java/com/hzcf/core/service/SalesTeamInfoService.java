package com.hzcf.core.service;
import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesTeamInfo;

public interface SalesTeamInfoService{

    /**
     *新增组织机构
     * */
	public int addSalesTeamInfo(SalesTeamInfo salesTeamInfo);
	
    /**
     *获取列表
     * @param salesTeamInfoList 
     * */
	public PageModel getSalesTeamInfoList(Map<String, Object> params);

    /**
     *获取前置编码
     * @param
     * */
	public int findMaxTreeCode(Map<String, Object> params);

    /**
     *获取
     * @param  
     * */
	public SalesTeamInfo selectById(Map<String, Object> params);

	 /**
    *
    *根据条件查询父辈组织机构
    * */
	public List<SalesTeamInfo> getParents(Map<String, Object> params);

	public List<SalesTeamInfo> selectByCondition(Map<String, Object> params);

    /**
     * 修改基础信息
     * */
	public void updateSalesTeamInfo(SalesTeamInfo salesTeamInfo);

    /**
     *@描述 批量插入销售团队信息
     *@创建人 qin lina
     *@创建时间 2020/3/9
     */
    void insertImportList(Map<String, Object> p);
}
