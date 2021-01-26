package com.hzcf.core.mapper;

import com.alibaba.fastjson.JSONArray;
import com.hzcf.pojo.insurancePolicy.InsPolicyInsuredPersonEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface InsPolicyInsuredPersonMapper {
    List<Map<String, Object>> getInsurancePolicyList(Map<String, Object> params);

    long getInsurancePolicyListSize(Map<String, Object> params);

    void addInsPolicyInsuredPerson(Map<String, Object> ins_policy_insured_person);

    InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByKey(Map<String, Object> params);

    InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByCorresponding(Map<String, Object> params);

    void updateInsPolicyInsuredPerson(Map<String, Object> ins_policy_insured_person);

    List<Map<String, Object>> getInsurancePolicyAllList(Map<String, Object> params);

    Map<String, Object> selectNote(Map<String, Object> params);

    List<Map<String, Object>> selectBecomeInsurancePolicyReceiveList(Map<String, Object> params);


    long selectBecomeInsurancePolicyReceiveListSize(Map<String, Object> params);

    Map<String, Object> selectNetMap(Map<String, Object> paras);

    List<Map<String, Object>> selectStateMap(Map<String, Object> paras);

    void deleteInsByIds(@Param("list") List<String> params);

    /**
     * <p>根据条件查询客户列表-不分页</p>
     * @param params    查询条件map
     * @return  LIST
     */
    List<Map<String, Object>> queryCustomerList(Map<String, Object> params);

    /**
     * <p>根据条件分页查询客户列表</p>
     * @param params    查询条件map
     * @return  LIST
     */
    List<Map<String, Object>> queryCustomerPage(Map<String, Object> params);

    /**
     * <p>根据条件查询客户总数</p>
     * @param params    查询条件map
     * @return  LIST
     */
    long queryCustomerTotalCount(Map<String, Object> params);

    /**
     * <p>根据证件号以及证件类型查询首次销售人员相关信息</p>
     * @param cardType  证件类型
     * @param cardNo    证件号码
     * @return
     */
    Map<String, Object> queryFirstSales(@Param("cardType")String cardType, @Param("cardNo")String cardNo);

    /**
     * <p>查询推动协议下保费及外部佣金系数</p>
     * @param startTime
     * @param endTime
     * @param protocolStartTime
     * @param protocolEndTime
     * @param productIds
     * @param orgIds
     * @return
     */
    List<Map<String, Object>> queryTotalPremiumAndOutCommissionCoefficient(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                                                                           @Param("protocolStartTime") Date protocolStartTime, @Param("protocolEndTime") Date protocolEndTime,
                                                                           @Param("productIds") List<Long> productIds, @Param("orgIds")List<Long> orgIds);

    /**
     * <p>根据员工id和起止时间查询保费相关FYC信息</p>
     * @param params    查询参数
     * @return
     */
    List<Map<String,Object>> queryPremiumForFYC(Map<String, Object> params);
    /**
     *查询总保费
     * */
    Map<String, BigDecimal> getTotalPremium(Map<String, Object> params);

    Map<String, Object> selectInsPolicyProposalEntityByKey(Map<String, Object> params);

    Map<String, Object> selectInsProductAndDept(Map<String, Object> par);
    
    long getInsurancePolicySizeByStatishMonth(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    
    List<Map<String,Object>> queryInsurancePolicyListByStatishMonth(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
    																@Param("startRow") Integer startRow,@Param("pageSize") Integer pageSize);

    List<Map<String,Object>> queryServiceChargeAndOutCommissionCoefficient(@Param("insuredPersonId") Long insuredPersonId, @Param("endTime") String endTime,
																    	   @Param("firstDayMonth") String firstDayMonth, @Param("firstDayQuarter") String firstDayQuarter, 
																    	   @Param("firstDayHalfYear") String firstDayHalfYear, @Param("firstDayYear") String firstDayYear);
 
    List<Map<String,Object>> queryRateAdjustAndOutCommissionCoefficient(@Param("insuredPersonId") Long insuredPersonId, @Param("endTime") String endTime,
															    		@Param("firstDayMonth") String firstDayMonth, @Param("firstDayQuarter") String firstDayQuarter, 
															    		@Param("firstDayHalfYear") String firstDayHalfYear, @Param("firstDayYear") String firstDayYear);

    /**
     * 通过保险公司code查询未删除投保单数量
     * @param companyCode 保险公司code
     * @return
     */
    int queryInsuranceSlipCountByCompanyCode(@Param("companyCode") String companyCode);
    //保单
    int batchAddInsPolicyInsuredPerson(@Param("list") List<Map> objects);
    //投保单
    int batchAddPolicyInsuredPerson(@Param("list")List<Map> list);
    //删除投保单   彻底删除包括关联表
    void deleteByIds(@Param("list") List<String> params);
}
