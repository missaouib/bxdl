package com.hzcf.plantform.feign.insurancePolicy;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.Params;
import com.hzcf.pojo.insurancePolicy.InsPolicyAttEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyHolderEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyInsuredPersonEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyProfitsPersonEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface InsPolicyInsuredPersonFeign {
    @RequestMapping(value = "/insurance_policy/do_insurance_policy",method = RequestMethod.POST )
    PageModel getInsurancePolicyList(@RequestParam Map<String,Object> params);

    @RequestMapping(value = "/insurance_policy/select_search_params",method = RequestMethod.POST )
    Map<String, Object> selectSearchParams(@RequestParam Map<String,Object> params);

    @RequestMapping(value = "/insurance_policy/selectInsInformationEntityByKey",method = RequestMethod.POST )
    List<Map<String, Object>> selectInsInformationEntityByKey(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/selectInsPolicyAttEntityByKey",method = RequestMethod.POST )
    List<InsPolicyAttEntity> selectInsPolicyAttEntityByKey(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/selectInsPolicyHolderEntityByKey",method = RequestMethod.POST )
    InsPolicyHolderEntity selectInsPolicyHolderEntityByKey(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/selectInsPolicyInsuredPersonEntityByKey",method = RequestMethod.POST )
    InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByKey(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/selectInsPolicyInsuredPersonEntityByCorresponding",method = RequestMethod.POST )
    InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByCorresponding(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/selectInsPolicyProfitsPersonEntityByKey",method = RequestMethod.POST )
    List<InsPolicyProfitsPersonEntity> selectInsPolicyProfitsPersonEntityByKey(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/selectInsPolicyProposalEntityByKey",method = RequestMethod.POST )
    Map<String,Object> selectInsPolicyProposalEntityByKey(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/add_ins_policy",method = RequestMethod.POST )
    Map<String,Object>  addInsurancePolicy(@RequestBody Params params);
    @RequestMapping(value = "/insurance_policy/update_ins_policy",method = RequestMethod.POST )
    Map<String,Object>  updateInsurancePolicy(@RequestBody Params params);
    @RequestMapping(value = "/insurance_policy/insPol",method = RequestMethod.POST )
    String addInsPol(@RequestBody Params params);
    @RequestMapping(value = "/insurance_policy/get_ins_pol_all_list",method = RequestMethod.POST )
    Map<String,Object> getInsurancePolicyAllList(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/getList",method = RequestMethod.POST )
    List<Map<String,Object>> getList(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/insurance_policy_all",method = RequestMethod.POST )
    List<Map<String,Object>> findInsurancePolicyAll(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/insurance_policy/commit_batch",method = RequestMethod.POST )
    Map<String,Object>  commitBatch(@RequestBody Params params);
    @RequestMapping(value = "/insurance_policy/to_submit_note",method = RequestMethod.POST )
    Map<String, Object> toSubmitNote(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/add_insurance_note",method = RequestMethod.POST )
    Map<String, Object> addInsuranceNote(@RequestBody Params param);
    @RequestMapping(value = "/insurance_policy/note_cancellation_grant",method = RequestMethod.POST )
    Map<String, Object> noteCancellationGrant(@RequestParam Map<String, Object> map);
    @RequestMapping(value = "/insurance_policy/note_cancellation_reply",method = RequestMethod.POST )
    Map<String, Object> noteCancellationReply(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/note_cancellation_sell_back",method = RequestMethod.POST )
    Map<String, Object> noteCancellationSellBack(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/note_cancellation_Abnormal",method = RequestMethod.POST )
    Map<String, Object> noteCancellationAbnormal(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/insurance_policy_eaxmine",method = RequestMethod.POST )
    Map<String, Object> insurancePolicyEaxmine(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/do_become_insurance_policy_receive",method = RequestMethod.POST )
    PageModel selectBecomeInsurancePolicyReceiveList(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/becomeRecCommit",method = RequestMethod.POST )
    Map<String, Object> becomeRecCommit(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/getPipList",method = RequestMethod.POST )
    Map<String, Object> getPipList(@RequestParam Map<String, Object> map);
    @RequestMapping(value = "/insurance_policy/add_insurance_receipt",method = RequestMethod.POST )
    Map<String, Object> addInsuranceReceipt(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/submit_receipt_examine",method = RequestMethod.POST )
    Map<String, Object> submitReceiptExamine(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/commit_batch_receipt",method = RequestMethod.POST )
    Map<String, Object> commitBatchReceipt(@RequestBody Params pList);
    @RequestMapping(value = "/insurance_policy/add_return_visit",method = RequestMethod.POST )
    Map<String, Object> addReturnVisit(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/selectNetMap",method = RequestMethod.POST )
    Map<String, Object> selectNetMap(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/selectStateMap",method = RequestMethod.POST )
    List<Map<String, Object>> selectStateMap(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/addOperation",method = RequestMethod.POST )
    Map<String, Object> addOperation(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insuranceSalesInfo/selectInsuranceSalesOne",method = RequestMethod.POST)
    Map<String,Object> selectInsuranceSalesOne (@RequestParam Map<String,Object> params);
    @RequestMapping(value = "/insurance_policy/deleteInsByIds",method = RequestMethod.POST)
    Map<String, Object> deleteInsByIds(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/selectInsPolicyInsuredExamineByKey",method = RequestMethod.POST)
    Map<String, Object> selectInsPolicyInsuredExamineByKey(@RequestParam Map<String, Object> paras);
    @RequestMapping(value = "/insurance_policy/submitExamine",method = RequestMethod.POST)
    Map<String, Object> submitExamine(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/insurance_policy/customerPage",method = RequestMethod.POST)
    PageModel findCustomerPageByParams(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/insurance_policy/customerList",method = RequestMethod.POST)
    List<Map<String, Object>> findCustomerListByParams(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/getTotalPremium",method = RequestMethod.POST)
    Map<String, BigDecimal> getTotalPremium(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/insurance_policy/selectInsProductAndDept",method = RequestMethod.POST)
    Map<String, Object> selectInsProductAndDept(@RequestParam Map<String, Object> par);
    @RequestMapping(value = "/insurance_policy/insertImportList",method = RequestMethod.POST)
    Map<String, Object> insertImportList(@RequestBody Map<String, Object> p);
    @RequestMapping(value = "/insurance_policy/selectProtocolId",method = RequestMethod.POST)
    Map<String, Object> selectProtocolId(@RequestParam Map<String, Object> par);

    @RequestMapping(value = "/insurance_policy/exist", method = RequestMethod.GET)
    @ResponseBody
    boolean findExistInsuranceSlipByCompanyCode(@RequestParam("companyCode") String companyCode);
    @RequestMapping(value = "/insurance_policy/insertImportPolicyList",method = RequestMethod.POST)
    Map<String, Object> insertImportPolicyList(@RequestBody Map<String, Object> p);
}
