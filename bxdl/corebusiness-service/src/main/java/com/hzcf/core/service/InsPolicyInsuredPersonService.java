package com.hzcf.core.service;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.Params;
import com.hzcf.pojo.insurancePolicy.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface InsPolicyInsuredPersonService {
    PageModel getInsurancePolicyList(Map<String, Object> params);

    Map<String, Object> getInsurancePolicyAllList(Map<String, Object> params);

    List<Map<String, Object>> findInsurancePolicyAll(Map<String, Object> params);

    Map<String, Object> selectSearchParams(Map<String, Object> params);

    void addInsPolicy(Params paramsMap);

    /**
     * 查询保单子表
     */
    List<Map<String, Object>> selectInsInformationEntityByKey(Map<String, Object> params);

    /**
     * 查询保单子表
     */
    List<InsPolicyAttEntity> selectInsPolicyAttEntityByKey(Map<String, Object> params);

    /**
     * 查询保单子表
     */
    InsPolicyHolderEntity selectInsPolicyHolderEntityByKey(Map<String, Object> params);

    /**
     * 查询保单子表
     */
    InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByKey(Map<String, Object> params);
    /**
    * @Description: 承保清单根据投保单号， 查询字标信息
    * @Param: [params]
    * @return: com.hzcf.pojo.insurancePolicy.InsPolicyInsuredPersonEntity
    * @Author: liuweichen
    * @Date: 2020/5/13 0013
    */
    InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByCorresponding(Map<String, Object> params);

    /**
     * 查询保单子表
     */
    List<InsPolicyProfitsPersonEntity> selectInsPolicyProfitsPersonEntityByKey(Map<String, Object> params);

    /**
     * 查询保单子表
     */
    Map<String,Object> selectInsPolicyProposalEntityByKey(Map<String, Object> params);

    void updateInsPolicy(Params paramsMap);

    /***
     *生成批次
     * @param params
     */
    void commitBatch(Params params);

    /**
     * 查询投保单的照会信息
     * @param params
     */
    Map<String, Object> selectNote(Map<String, Object> params);

    void addInsuranceNot(Params param);

    void noteCancellationGrant(Map<String, Object> map);

    void noteCancellationReply(Map<String, Object> params);

    void noteCancellationSellBack(Map<String, Object> params);

    void noteCancellationAbnormal(Map<String, Object> params);

    void insurancePolicyEaxmine(Map<String, Object> params);
    /**
     * 保单接收
     * */
    PageModel selectBecomeInsurancePolicyReceiveList(Map<String, Object> params);
    /**
     * 确认接收
     * */
    void becomeRecCommit(Map<String, Object> params);

    Map<String, Object> getPipList(Map<String, Object> map);

    void addInsuranceReceipt(Map<String, Object> params);
    /**
     * 保存审核，全部审核，批量审核
     * */
    void submitReceiptExamine(Map<String, Object> params);
    /**
     * 回执 下发 提交
     * */
    void commitBatchReceipt(Params pList);
    /**
     * 回访信息 提交
     * */
    void addReturnVisit(Map<String, Object> params);
    /**
     * 查询实收记录
     * */
    Map<String, Object> selectNetMap(Map<String, Object> paras);
    /**
     * 查询状态
     * */
    List<Map<String, Object>> selectStateMap(Map<String, Object> paras);

    void addOperation(Map<String, Object> params);

    /**
     * <p>根据查询条件分页查询导入保单</p>
     * @param params    查询条件
     * @return
     */
    PageModel findInsPolicyProfitsPersonPageByParams(Map<String, Object> params);

    void deleteInsByIds(Map<String, Object> params);
    /***
     * 根据投保单查询 审核 pie等信息
     */
    Map<String, Object> selectInsPolicyInsuredExamineByKey(Map<String, Object> paras);


    void submitExamine(Map<String, Object> params);

    /**
     * <p>根据查询条件分页查询客户列表</p>
     * @param params    查询条件
     * @return PageModel
     */
    PageModel findCustomerPageByParams(Map<String, Object> params);

    /**
     * <p>根据查询条件查询客户列表-不分页</p>
     * @param params    查询条件
     * @return List
     */
    List<Map<String, Object>> findCustomerListByParams(Map<String, Object> params);
    /**
     * 查询总保费
     * */
    Map<String, BigDecimal> getTotalPremium(Map<String, Object> params);

    /**
     * 通过 员工编号 产品名称 查询协议Id' 以及员工所属 org 和 team
     * */
    Map<String, Object> selectInsProductAndDept(Map<String, Object> par);

   void insertImportList(Map<String, Object> p);

    Map<String, Object> selectProtocolId(Map<String, Object> par);

    List<Map<String, Object>> getList(Map<String, Object> params);

    /**
     * 通过保险公司code查询下是否存在未删除的投保单
     * @param companyCode 保险公司code
     * @return  true 存在   false 不存在
     */
    boolean findExistInsuranceSlipByCompanyCode(String companyCode);
/**投保单导入
 *
 * */
    void insertImportPolicyList(Map<String, Object> p);
}
