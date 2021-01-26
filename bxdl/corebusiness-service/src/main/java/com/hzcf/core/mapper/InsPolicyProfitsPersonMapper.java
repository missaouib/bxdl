package com.hzcf.core.mapper;

import com.hzcf.pojo.insurancePolicy.InsPolicyProfitsPersonEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InsPolicyProfitsPersonMapper {
    void addInsPolicyProfitsPerson(Map<String, Object> ins_policy_profits_person_list);

    List<InsPolicyProfitsPersonEntity> selectInsPolicyProfitsPersonEntityByKey(Map<String, Object> params);

    void updateInsPolicyProfitsPerson(Map<String, Object> ippp);

    /**
     * <p>根据分页条件查询记录</p>
     * @param params 分页查询条件
     * @return
     */
    List<InsPolicyProfitsPersonEntity> queryInsPolicyProfitsPersonByParams(@Param("params")Map<String, Object> params);

    /**
     * <p>根据查询条件查询数量</p>
     * @param params 查询条件
     * @return
     */
    long queryInsPolicyProfitsPersonCountByParams(@Param("params")Map<String, Object> params);


    Map<String, Object> selectProtocolId(Map<String, Object> par);

    int beathAddInsPolicyProfitsPerson(List<Map> list);

    void deleteInsPolicyProfitsPerson(Map<String, Object> ins_policy_insured_person);
}
