package com.hzcf.core.controller;


import com.hzcf.core.service.InsPolicyInsuredPersonService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.Params;
import com.hzcf.pojo.insurancePolicy.InsPolicyAttEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyHolderEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyInsuredPersonEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyProfitsPersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/insurance_policy")
public class InsPolicyInsuredPersonController {
    private Logger logger = LoggerFactory.getLogger(InsPolicyInsuredPersonController.class);
    @Autowired
    private InsPolicyInsuredPersonService insPolicyInsuredPersonService;

    /**
     * 分页查询 投保单 list
     */
    @RequestMapping("/do_insurance_policy")
    @ResponseBody
    public PageModel doInsurancePolicy(@RequestParam Map<String, Object> params) {
        return insPolicyInsuredPersonService.getInsurancePolicyList(params);
    }

    /**
     * 与分页查询 投保单 list同步 不分页
     * return:map
     */
    @RequestMapping("/get_ins_pol_all_list")
    @ResponseBody
    public Map<String, Object> getInsurancePolicyAllList(@RequestParam Map<String, Object> params) {
        return insPolicyInsuredPersonService.getInsurancePolicyAllList(params);
    }
    /**
     * 与分页查询 投保单 list同步 不分页
     * return:list
     */
    @RequestMapping("/getList")
    @ResponseBody
    public List<Map<String, Object>> getList(@RequestParam Map<String, Object> params) {
        return insPolicyInsuredPersonService.getList(params);
    }
    /**
     * 不分页查询投保单列表
     */
    @RequestMapping("/insurance_policy_all")
    @ResponseBody
    public List<Map<String, Object>> findInsurancePolicyAll(@RequestParam Map<String, Object> params) {
        return insPolicyInsuredPersonService.findInsurancePolicyAll(params);
    }

    /**
     * 查询 添加页面的回显参数
     */
    @RequestMapping("/select_search_params")
    @ResponseBody
    public Map<String, Object> selectSearchParams(@RequestParam Map<String, Object> params) {
        return insPolicyInsuredPersonService.selectSearchParams(params);
    }


    /**
     * 添加投保单
     */
    @RequestMapping("/add_ins_policy")
    @ResponseBody
    public Map<String, Object> addInsPolicy(@RequestBody Params paramsMap) {
        Map<String, Object> retParams = new HashMap<>();
        try {
            insPolicyInsuredPersonService.addInsPolicy(paramsMap);
            retParams.put("code", 200);
        } catch (RuntimeException e) {
            if(e instanceof DuplicateKeyException){
                retParams.put("code", 4001);
            }else{
                retParams.put("code", 400);
            }
            e.printStackTrace();
        }
        return retParams;
    }

    /**
     * 添加投保单
     */
    @RequestMapping("/update_ins_policy")
    @ResponseBody
    public Map<String, Object> updateInsPolicy(@RequestBody Params paramsMap) {
        Map<String, Object> retParams = new HashMap<>();
        try {
            insPolicyInsuredPersonService.updateInsPolicy(paramsMap);
            retParams.put("code", 200);
        } catch (RuntimeException e) {
            e.printStackTrace();
            retParams.put("code", 400);
        }
        return retParams;
    }

    /**
     * 根据保单号 查询字标信息
     */
    @RequestMapping("/selectInsInformationEntityByKey")
    @ResponseBody
    public List<Map<String, Object>> selectInsInformationEntityByKey(@RequestParam Map<String, Object> params) {
        try{ return insPolicyInsuredPersonService.selectInsInformationEntityByKey(params);}catch (RuntimeException e){e.printStackTrace(); return null;}catch (Exception e){e.printStackTrace();return null;}

    }

    /**
     * 根据保单号 查询字标信息
     */
    @RequestMapping("/selectInsPolicyAttEntityByKey")
    @ResponseBody
    public List<InsPolicyAttEntity> selectInsPolicyAttEntityByKey(@RequestParam Map<String, Object> params) {
        try{ return insPolicyInsuredPersonService.selectInsPolicyAttEntityByKey(params);}
        catch (RuntimeException e){e.printStackTrace(); return null;}
        catch (Exception e){e.printStackTrace();return null;}

    }

    /**
     * 根据保单号 查询字标信息
     */
    @RequestMapping("/selectInsPolicyHolderEntityByKey")
    @ResponseBody
    public InsPolicyHolderEntity selectInsPolicyHolderEntityByKey(@RequestParam Map<String, Object> params) {

        try{return insPolicyInsuredPersonService.selectInsPolicyHolderEntityByKey(params);}catch (RuntimeException e){e.printStackTrace(); return null;}catch (Exception e){e.printStackTrace();return null;}

    }

    /**
     * 根据保单号 查询字标信息
     */
    @RequestMapping("/selectInsPolicyInsuredPersonEntityByKey")
    @ResponseBody
    public InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByKey(@RequestParam Map<String, Object> params) {

        try{ return insPolicyInsuredPersonService.selectInsPolicyInsuredPersonEntityByKey(params);}catch (RuntimeException e){e.printStackTrace(); return null;}catch (Exception e){e.printStackTrace();return null;}

    }

    /**
     * 承保清单根据投保单号， 查询字标信息
     */
    @RequestMapping("/selectInsPolicyInsuredPersonEntityByCorresponding")
    @ResponseBody
    public InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByCorresponding(@RequestParam Map<String, Object> params) {

        try{ return insPolicyInsuredPersonService.selectInsPolicyInsuredPersonEntityByCorresponding(params);}catch (RuntimeException e){e.printStackTrace(); return null;}catch (Exception e){e.printStackTrace();return null;}

    }

    /**
     * 根据保单号 查询字标信息
     */
    @RequestMapping("/selectInsPolicyProfitsPersonEntityByKey")
    @ResponseBody
    public List<InsPolicyProfitsPersonEntity> selectInsPolicyProfitsPersonEntityByKey(@RequestParam Map<String, Object> params) {

        try{ return insPolicyInsuredPersonService.selectInsPolicyProfitsPersonEntityByKey(params);}catch (RuntimeException e){e.printStackTrace(); return null;}catch (Exception e){e.printStackTrace();return null;}

    }

    /**
     * 根据保单号 查询字标信息
     */
    @RequestMapping("/selectInsPolicyProposalEntityByKey")
    @ResponseBody
    public Map<String, Object> selectInsPolicyProposalEntityByKey(@RequestParam Map<String, Object> params) {

        try{ return insPolicyInsuredPersonService.selectInsPolicyProposalEntityByKey(params);}catch (RuntimeException e){e.printStackTrace(); return null;}catch (Exception e){e.printStackTrace();return null;}

    }

    @RequestMapping("/insPol")
    @ResponseBody
    public String ins(@RequestBody Params params) {
        try {
            insPolicyInsuredPersonService.updateInsPolicy(params);
        } catch (RuntimeException e) {

        }
        return "1";
    }

    @RequestMapping("/commit_batch")
    @ResponseBody
    private Map<String, Object> commitBatch(@RequestBody Params params) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.commitBatch(params);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/to_submit_note")
    @ResponseBody
    private Map<String, Object> toSubmitNote(@RequestParam Map<String, Object> params) {
        return insPolicyInsuredPersonService.selectNote(params);
    }

    @RequestMapping("add_insurance_note")
    @ResponseBody
    public Map<String, Object> addInsuranceNot(@RequestBody Params param) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.addInsuranceNot(param);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("note_cancellation_grant")
    @ResponseBody
    public Map<String, Object> noteCancellationGrant(@RequestParam Map<String, Object> map) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.noteCancellationGrant(map);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("note_cancellation_reply")
    @ResponseBody
    public Map<String, Object> noteCancellationReply(@RequestParam Map<String, Object> params) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.noteCancellationReply(params);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping(value = "/note_cancellation_sell_back", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> noteCancellationSellBack(@RequestParam Map<String, Object> params) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.noteCancellationSellBack(params);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
        }
        return msg;
    }

    @RequestMapping(value = "/note_cancellation_Abnormal", method = RequestMethod.POST)
    Map<String, Object> noteCancellationAbnormal(@RequestParam Map<String, Object> params) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.noteCancellationAbnormal(params);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
        }
        return msg;

    }

    @RequestMapping("/insurance_policy_eaxmine")
    @ResponseBody
    Map<String, Object> insurancePolicyEaxmine(@RequestParam Map<String, Object> params) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.insurancePolicyEaxmine(params);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
        }
        return msg;
    }

    @RequestMapping("/do_become_insurance_policy_receive")
    @ResponseBody
    PageModel selectBecomeInsurancePolicyReceiveList(@RequestParam Map<String, Object> params) {
        return insPolicyInsuredPersonService.selectBecomeInsurancePolicyReceiveList(params);
    }

    @RequestMapping(value = "/becomeRecCommit", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> becomeRecCommit(@RequestParam Map<String, Object> params) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.becomeRecCommit(params);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
        }
        return msg;
    }

    @RequestMapping(value = "/getPipList", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> getPipList(@RequestParam Map<String, Object> map) {
        return insPolicyInsuredPersonService.getPipList(map);
    }

    /**
     * 保存回执信息
     */
    @RequestMapping(value = "/add_insurance_receipt", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> addInsuranceReceipt(@RequestParam Map<String, Object> params) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.addInsuranceReceipt(params);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
        }
        return msg;
    }

    /**
     * 保存审核，全部审核，批量审核
     */
    @RequestMapping(value = "/submit_receipt_examine", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> submitReceiptExamine(@RequestParam Map<String, Object> params) {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.submitReceiptExamine(params);
            msg.put("code", "200");
        } catch (RuntimeException e) {
            msg.put("code", "400");
        }
        return msg;
    }

    /**
     * 回执 下发 提交
     */
    @RequestMapping(value = "/commit_batch_receipt", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> commitBatchReceipt(@RequestBody Params pList) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.commitBatchReceipt(pList);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 回访信息 提交
     */
    @RequestMapping(value = "/add_return_visit", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> addReturnVisit(@RequestParam Map<String, Object> params) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.addReturnVisit(params);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping(value = "/selectNetMap",method = RequestMethod.POST )
    @ResponseBody
    Map<String, Object> selectNetMap(@RequestParam Map<String, Object> paras){
        return  insPolicyInsuredPersonService.selectNetMap(paras);
    }

    @RequestMapping(value = "/selectStateMap",method = RequestMethod.POST )
    @ResponseBody
    List<Map<String, Object>> selectStateMap(@RequestParam Map<String, Object> paras){
        return  insPolicyInsuredPersonService.selectStateMap(paras);
    }

    @RequestMapping(value = "/addOperation",method = RequestMethod.POST )
    @ResponseBody
    Map<String, Object> addOperation(@RequestParam Map<String, Object> params){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.addOperation(params);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
        }
        return map;
    }
    /**
     * s删除投保单
     * */
    @RequestMapping(value = "/deleteInsByIds",method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> deleteInsByIds(@RequestParam Map<String, Object> params){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.deleteInsByIds(params);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 查询审核pie等信息
     * */
    @RequestMapping(value = "/selectInsPolicyInsuredExamineByKey",method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> selectInsPolicyInsuredExamineByKey(@RequestParam Map<String, Object> paras){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = insPolicyInsuredPersonService.selectInsPolicyInsuredExamineByKey(paras);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping(value = "/submitExamine",method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> submitExamine(@RequestParam Map<String, Object> params){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.submitExamine(params);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/customerPage", method = RequestMethod.POST)
    @ResponseBody
    public PageModel findCustomerPageByParams(@RequestParam Map<String, Object> params){
        return insPolicyInsuredPersonService.findCustomerPageByParams(params);
    }

    @RequestMapping(value = "/customerList", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> findCustomerListByParams(@RequestParam Map<String, Object> params){
        return insPolicyInsuredPersonService.findCustomerListByParams(params);
    }

    @RequestMapping(value = "/getTotalPremium",method = RequestMethod.POST)
    @ResponseBody
    Map<String, BigDecimal> getTotalPremium(@RequestParam Map<String, Object> params){
        return insPolicyInsuredPersonService.getTotalPremium(params);
    }
    @RequestMapping(value = "/selectInsProductAndDept",method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> selectInsProductAndDept(@RequestParam Map<String, Object> par){
        return  insPolicyInsuredPersonService.selectInsProductAndDept(par);
    }
/**
*Author:孙旭明
*@Description:导入保单
*@Class:
*@Param:[p]
*@return:java.util.Map<java.lang.String,java.lang.Object>
*@Date:2019/11/11
*/

    @RequestMapping(value = "/insertImportList",method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> insertImportList(@RequestBody Map<String, Object> p){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.insertImportList(p);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
            logger.error("批量导入失败,",e);
        }
        return map;
    }
/**
*Author:孙旭明
*@Description:导入投保单
*@Class:
*@Param:[p]
*@return:java.util.Map<java.lang.String,java.lang.Object>
*@Date:2019/11/11
*/
    @RequestMapping(value = "/insertImportPolicyList",method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> insertImportPolicyList(@RequestBody Map<String, Object> p){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            insPolicyInsuredPersonService.insertImportPolicyList(p);
            map.put("code", 200);
        } catch (Exception e) {
            map.put("code", 400);
          logger.error("批量导入失败,",e);
        }
        return map;
    }
    @RequestMapping(value = "/selectProtocolId",method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> selectProtocolId(@RequestParam Map<String, Object> par){
        return insPolicyInsuredPersonService.selectProtocolId(par);
    }

    @RequestMapping(value = "/exist", method = RequestMethod.GET)
    @ResponseBody
    boolean findExistInsuranceSlipByCompanyCode(@RequestParam("companyCode") String companyCode){
        return insPolicyInsuredPersonService.findExistInsuranceSlipByCompanyCode(companyCode);
    }
}
