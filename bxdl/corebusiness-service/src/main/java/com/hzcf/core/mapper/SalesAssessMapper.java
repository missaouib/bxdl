package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.sales.SalesAssess;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SalesAssessMapper {

    /**
     * <p>新增员工考核记录</p>
     * @param salesAssess
     */
    void insertSalesAssess(SalesAssess salesAssess);

    /**
     * <p>根据id更新员工考核记录</p>
     * @param salesAssess
     */
    int updateSalesAssess(SalesAssess salesAssess);

    /**
     * <p>根据id查询考核记录</p>
     * @param id    id
     * @return  考核记录
     */
    SalesAssess querySalesAssessById(@Param("id")long id);

    /**
     * <p>根据条件分页查询员工考核记录</p>
     * @param params  查询条件
     * @return  考核记录集合
     */
    List<SalesAssess> querySalesAssessList(Map<String, Object> params);

    /**
     * <p>根据条件查询员工考核记录总数</p>
     * @param params  查询条件
     * @return  考核记录总数
     */
    long querySalesAssessCount(Map<String, Object> params);

    /**
     * <p>根据员工id查询最新考核结果</p>
     * @param insuranceSalesId  员工id
     * @param salesGradeId  职级id
     * @return
     */
    SalesAssess queryNewSalesAssess(@Param("insuranceSalesId")long insuranceSalesId, @Param("salesGradeId")long salesGradeId);

	
}