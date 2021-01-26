package com.hzcf.core.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzcf.constant.InsurancePolicyComstant;
import com.hzcf.core.mapper.*;
import com.hzcf.core.service.InsPolicyInsuredPersonService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.Params;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.insurancePolicy.InsPolicyAttEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyHolderEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyInsuredPersonEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyProfitsPersonEntity;
import com.hzcf.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InsPolicyInsuredPersonServiceImpl implements InsPolicyInsuredPersonService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InsPolicyInsuredPersonMapper insPolicyInsuredPersonMapper;//投保单mapper
    @Autowired
    private InsInformationMapper insInformationMapper;
    @Autowired
    private InsPolicyAttMapper insPolicyAttMapper;
    @Autowired
    private InsPolicyHolderMapper insPolicyHolderMapper;
    @Autowired
    private InsPolicyInsuredPersonMapper insPolicyInsuredPerson;
    @Autowired
    private InsPolicyProfitsPersonMapper insPolicyProfitsPersonMapper;
    @Autowired
    private InsPolicyProposalMapper insPolicyProposalMapper;
    @Autowired
    private InsuranceDeptMapper insuranceDeptMapper;
    @Autowired
    private InsPolicyInsuredExamineMapper insPolicyInsuredExamineMapper;
    @Autowired
    private InsStateMapper insStateMapper;

@Autowired
private InsuranceSalesInfoMapper insuranceSalesInfoMapper;

    @Autowired
    SalesOrgInfoMapper salesOrgInfoMapper;
    /**
     * 分页查询保单list
     */
    @Override
    public PageModel getInsurancePolicyList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSizeInt", Integer.valueOf(params.get("pageSize").toString()));
        params.put("pageNoInt", Integer.valueOf(params.get("pageNo").toString()));
        logger.info("查询条件：" + params);

        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String, Object>> list = insPolicyInsuredPersonMapper.getInsurancePolicyList(params);
        long size = insPolicyInsuredPersonMapper.getInsurancePolicyListSize(params);
        logger.info("查询数据"  + "条数" + size);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }
    @Override
    public Map<String, BigDecimal> getTotalPremium(Map<String, Object> params) {
        return insPolicyInsuredPersonMapper.getTotalPremium(params);

    }
    /**
     * 通过 员工编号 产品名称 查询协议Id' 以及员工所属 org 和 team
     * */
    @Override
    public Map<String, Object> selectInsProductAndDept(Map<String, Object> par) {
        return insPolicyInsuredPersonMapper.selectInsProductAndDept(par);
    }
    @Transactional
    @Override
    public void insertImportList(Map<String, Object> p) {

        Object ins_policy_insured_person_string = p.get("ins_policy_insured_person_string");
        Object ins_policy_holder_string = p.get("ins_policy_holder_string");
        Object insInformationString = p.get("insInformationString");
        Object insertString = p.get("insertString");
        JSONArray objects = JSONObject.parseArray(ins_policy_insured_person_string.toString());
        List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
        lists.forEach(list->{
            logger.info("插入数据集：");
            int successPipSize = insPolicyInsuredPersonMapper.batchAddInsPolicyInsuredPerson(list);
            logger.info("插入数据集成功条数："+successPipSize);
        });
        JSONArray phObjects = JSONObject.parseArray(ins_policy_holder_string.toString());
        List<List<Map>> phLists = PublicUtil.splitListReturnListMap(phObjects, 100);
        phLists.forEach(list->{
            logger.info("插入数据集：");
            int successPhSize = insPolicyHolderMapper.batchAddInsPolicyHolder(list);
            logger.info("插入数据集成功条数："+successPhSize);
        });
        if(insertString != null) {
            JSONArray ipppObjects = JSONObject.parseArray(insertString.toString());
             if (!ipppObjects.isEmpty() && ipppObjects.size() > 0) {
                 List<List<Map>> ipppLists = PublicUtil.splitListReturnListMap(ipppObjects, 100);
                 ipppLists.forEach(list -> {
                     logger.info("插入数据集：");
                     int successIpppSize = insPolicyProfitsPersonMapper.beathAddInsPolicyProfitsPerson(list);
                     logger.info("插入数据集成功条数：" + successIpppSize);

                 });
             }
        }

        JSONArray objects2 = JSONObject.parseArray(insInformationString.toString());
        List<Map<String,Object>> inforMapList = new ArrayList<Map<String,Object>>();
        objects2.forEach(infor->{
            Map inforMap = (Map) infor;
            inforMapList.add(inforMap);
        });
        List<List<?>> inforLists = PublicUtil.splitList(inforMapList, 100);
        inforLists.forEach(list ->{
            List<Map<String,Object>> inforMap = (List<Map<String,Object>>) list;
            logger.info("插入数据集："+inforMap.toString());
            insInformationMapper.addInsInformation(inforMap);
        });

    }

    @Override
    public Map<String, Object> selectProtocolId(Map<String, Object> par) {
        return insPolicyProfitsPersonMapper.selectProtocolId(par);
    }

    @Override
    public List<Map<String, Object>> getList(Map<String, Object> params) {
        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

      return   insPolicyInsuredPersonMapper.getInsurancePolicyAllList(params);
    }

    @Override
    public Map<String, Object> getPipList(Map<String, Object> params) {
        List<Map<String, Object>> insurancePolicyAllList = insPolicyInsuredPersonMapper.getInsurancePolicyAllList(params);
        if (insurancePolicyAllList.get(0) != null) {
            return insurancePolicyAllList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> getInsurancePolicyAllList(Map<String, Object> params) {
        Map<String, Object> model = new HashMap<String, Object>();
        List<Map<String, Object>> list = insPolicyInsuredPersonMapper.getInsurancePolicyAllList(params);
        model.put("list", list);
        return model;
    }

    @Override
    public List<Map<String, Object>> findInsurancePolicyAll(Map<String, Object> params) {
        return insPolicyInsuredPersonMapper.getInsurancePolicyAllList(params);
    }
/**
     * 添加页面：回显部门条件
     */
    @Override
    public Map<String, Object> selectSearchParams(Map<String, Object> params) {
        Map<String, Object> re = new HashMap<String, Object>();
        //保险公司
        List<InsuranceDept> insurCompanys = insuranceDeptMapper.findInsurCompanys(params);
        re.put("org", insurCompanys);
        return re;
    }

    /**
     * 新增投保单
     */
    @Override
    @Transactional
    public void addInsPolicy(Params paramsMap) {
        //添加数据到保单子表
        Map<String, Object> ins_policy_holder = paramsMap.getIns_policy_holder();
        if (!CollectionUtils.isEmpty(ins_policy_holder)) {
            insPolicyHolderMapper.addInsPolicyHolder(ins_policy_holder);
        }
        //添加数据到保单子表
        List<Map<String, Object>> ins_policy_att_list = paramsMap.getIns_policy_att_list();
        if (!CollectionUtils.isEmpty(ins_policy_att_list)) {
            ins_policy_att_list.forEach(ipal -> {
                insPolicyAttMapper.addInsPolicyAtt(ipal);
            });
        }
        //添加数据到保单子表
        Map<String, Object> ins_policy_insured_person = paramsMap.getIns_policy_insured_person();
        if (!(CollectionUtils.isEmpty(ins_policy_insured_person))) {
            insPolicyInsuredPerson.addInsPolicyInsuredPerson(ins_policy_insured_person);
        }
        //添加数据到保单子表
        List<Map<String, Object>> ins_policy_profits_person_list = paramsMap.getIns_policy_profits_person_list();
        if (!(CollectionUtils.isEmpty(ins_policy_profits_person_list))) {
            ins_policy_profits_person_list.forEach(ippp -> {
                insPolicyProfitsPersonMapper.addInsPolicyProfitsPerson(ippp);
            });
        }
        //添加数据到保单子表

/*   Map<String, Object> ins_policy_proposal = paramsMap.getIns_policy_proposal();
        if (!(CollectionUtils.isEmpty(ins_policy_proposal))) {
            insPolicyProposalMapper.addInsPolicyProposal(ins_policy_proposal);
        }*/
        //添加数据到保单子表
        List<Map<String, Object>> ins_information = paramsMap.getIns_information();
        if (!(CollectionUtils.isEmpty(ins_information))) {
            insInformationMapper.addInsInformation(ins_information);
        }

        // 添加到审核
        Map<String, Object> ins_policy_insured_examine = paramsMap.getIns_policy_insured_examine();
        if (!(CollectionUtils.isEmpty(ins_policy_insured_examine))) {
            insPolicyInsuredExamineMapper.addInsPolicyInsuredExamine(ins_policy_insured_examine);
        }
        //添加状态到历史状态表
        Map<String,Object> ins_state = new  HashMap<String,Object>();
        Object createTime = ins_policy_insured_person.get("createTime");
        Object createBy = ins_policy_insured_person.get("createBy");
        Object type = ins_policy_holder.get("type");
        Object policyId = ins_policy_insured_person.get("policyId");

        if("1".equals(type)){
            Object state = ins_policy_insured_person.get("state");
            ins_state.put("state",state );//状态
        }else{
            Object insState = ins_policy_insured_person.get("insState");
            ins_state.put("state",insState );//状态
        }

        ins_state.put("createTime",createTime );//创建人
        ins_state.put("createBy",createBy );// 创建时间
        ins_state.put("policyId",policyId );//保单号
        ins_state.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);//类型
        insStateMapper.addInsState(ins_state);



        if ("2".equals(ins_policy_insured_person.get("type"))) {
            Map<String, Object> map = paramsMap.getMap();
            insPolicyInsuredPerson.updateInsPolicyInsuredPerson(map);

            ins_state.put("createTime",createTime );//创建人
            ins_state.put("createBy",createBy );// 创建时间
            ins_state.put("policyId",map.get("policyId") );//保单号
            ins_state.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);//类型
            ins_state.put("state",map.get("state") );//状态
            insStateMapper.updateTypeByPolicyId(map.get("policyId"),InsurancePolicyComstant.INS_STATE_HIS_1);
            insStateMapper.addInsState(ins_state);

        }
    }


    /**
     * 根据保单号 查询子表数据
     */
    @Override
    public List<Map<String, Object>> selectInsInformationEntityByKey(Map<String, Object> params) {
        return insInformationMapper.selectInsInformationEntityByKey(params);//产品中间表
    }

    /**
     * 根据保单号 查询子表数据
     */
    @Override
    public List<InsPolicyAttEntity> selectInsPolicyAttEntityByKey(Map<String, Object> params) {
        return insPolicyAttMapper.selectInsPolicyAttEntityByKey(params);//保单影像附件
    }

    /**
     * 根据保单号 查询子表数据
     */
    @Override
    public InsPolicyHolderEntity selectInsPolicyHolderEntityByKey(Map<String, Object> params) {
        return insPolicyHolderMapper.selectInsPolicyHolderEntityByKey(params);//投保人
    }

    /**
     * 根据保单号 查询子表数据
     */
    @Override
    public InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByKey(Map<String, Object> params) {
        return insPolicyInsuredPerson.selectInsPolicyInsuredPersonEntityByKey(params);//投保单/保单被保人
    }

    /**
    * @Description: 承保清单根据投保单号， 查询字标信息
    * @Param: [params]
    * @return: com.hzcf.pojo.insurancePolicy.InsPolicyInsuredPersonEntity
    * @Author: liuweichen
    * @Date: 2020/5/13 0013
    */
    @Override
    public InsPolicyInsuredPersonEntity selectInsPolicyInsuredPersonEntityByCorresponding(Map<String, Object> params) {
        return insPolicyInsuredPerson.selectInsPolicyInsuredPersonEntityByCorresponding(params);//投保单/保单被保人
    }

    /**
     * 根据保单号 查询子表数据
     */
    @Override
    public List<InsPolicyProfitsPersonEntity> selectInsPolicyProfitsPersonEntityByKey(Map<String, Object> params) {
        return insPolicyProfitsPersonMapper.selectInsPolicyProfitsPersonEntityByKey(params);//受益人
    }



    /**
     * 根据保单号 查询子表数据
     */
    @Override
    public Map<String, Object> selectInsPolicyProposalEntityByKey(Map<String, Object> params) {
        return insPolicyInsuredPerson.selectInsPolicyProposalEntityByKey(params);//代理人
    }

    @Override
    @Transactional
    public void updateInsPolicy(Params paramsMap) {
            Map<String, Object> ins_policy_insured_person = paramsMap.getIns_policy_insured_person();
            //添加数据到保单子表
            Map<String, Object> ins_policy_holder = paramsMap.getIns_policy_holder();
            if (!CollectionUtils.isEmpty(ins_policy_holder)) {
                insPolicyHolderMapper.updateInsPolicyHolder(ins_policy_holder);
            }
            //添加数据到保单子表
            List<Map<String, Object>> ins_policy_att_list = paramsMap.getIns_policy_att_list();

            String policyId = String.valueOf(ins_policy_insured_person.get("policyId"));
            String type = String.valueOf(ins_policy_insured_person.get("type"));
            Map<String,Object> map = paramsMap.getMap() == null ? new HashMap<>() : paramsMap.getMap();
            String changeImageFlag = map.get("changeImageFlag") == null ? "" : String.valueOf(map.get("changeImageFlag"));
            if("1".equals(changeImageFlag)){ //
                logger.info("保单号/投保单号[{}]对应的影像文件有变动", policyId);
                insPolicyAttMapper.deleteByPolicyIdAndType(policyId, type);
                if (!CollectionUtils.isEmpty(ins_policy_att_list)) {
                    ins_policy_att_list.forEach(ipal -> {
                        insPolicyAttMapper.addInsPolicyAtt(ipal);
                    });
                }
            }

            //添加数据到保单子表

            if (!CollectionUtils.isEmpty(ins_policy_insured_person)) {
                insPolicyInsuredPerson.updateInsPolicyInsuredPerson(ins_policy_insured_person);
            }
            //添加数据到保单子表
            List<Map<String, Object>> ins_policy_profits_person_list = paramsMap.getIns_policy_profits_person_list();
             insPolicyProfitsPersonMapper.deleteInsPolicyProfitsPerson(ins_policy_insured_person);
            if (!CollectionUtils.isEmpty(ins_policy_profits_person_list)) {
                ins_policy_profits_person_list.forEach(ippp -> {
                    insPolicyProfitsPersonMapper.addInsPolicyProfitsPerson(ippp);
                });
            }
         /*   //添加数据到保单子表
            Map<String, Object> ins_policy_proposal = paramsMap.getIns_policy_proposal();
            if (!CollectionUtils.isEmpty(ins_policy_proposal)) {
                insPolicyProposalMapper.updateInsPolicyProposal(ins_policy_proposal);
            }*/
            //添加数据到保单子表
            List<Map<String, Object>> ins_information = paramsMap.getIns_information();
            insInformationMapper.deleteInformation(ins_policy_insured_person);
            if (!CollectionUtils.isEmpty(ins_information)) {
                insInformationMapper.addInsInformation(ins_information);
            }
            // 添加到审核
          /*  Map<String, Object> ins_policy_insured_examine = paramsMap.getIns_policy_insured_examine();
            if (!(CollectionUtils.isEmpty(ins_policy_insured_examine))) {
                insPolicyInsuredExamineMapper.updateInsExamine(ins_policy_insured_examine);
            }*/
            if ("2".equals(ins_policy_insured_person.get("type").toString()) && "2".equals(ins_policy_insured_person.get("source").toString())) {
                insPolicyInsuredPerson.updateInsPolicyInsuredPerson(map);
            }


    }

    @Override
    public void commitBatch(Params pa) {
        List<String> list = pa.getList();
        Map<String, Object> params = pa.getMap();
        Object insState = params.get("insState");
        Object state = params.get("state");
        Object gen_name = params.get("gen_name");
        Object gen_org = params.get("gen_org");
        Object gen_type = params.get("gen_type");
        Object gen_date = params.get("gen_date");
        Object time = params.get("time");
        Object employeeId = params.get("employeeId");
        list.forEach(policyId -> {
            Map<String,Object> ins_state = new HashMap<String,Object>();
            //修改订单状态
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("policyId", policyId);
            if (insState == null) {
                map.put("state", state);
                ins_state.put("state",state);

            } else {
                map.put("insState", insState);
                ins_state.put("state",insState);
            }

            insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(map);
            //订单装填表新增曾状态
            map.put("createTime", time);
            map.put("createBy", employeeId);
        //    insStateMapper.addInsState(map);
            //修改状态信息 ins_policy_insured_examine
            map.clear();
            map.put("policyId", policyId);
            map.put("batchName", gen_name);
            map.put("batchOrgId", gen_org);
            map.put("batchType", gen_type);
            map.put("batchDate", gen_date);

            insPolicyInsuredExamineMapper.updateInsExamine(map);



            ins_state.put("createTime",time );//创建人
            ins_state.put("createBy",employeeId );// 创建时间
            ins_state.put("policyId",policyId );//保单号
            ins_state.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);//类型
            insStateMapper.updateTypeByPolicyId(policyId,InsurancePolicyComstant.INS_STATE_HIS_1);
            insStateMapper.addInsState(ins_state);

        });


    }

    @Override
    public Map<String, Object> selectNote(Map<String, Object> params) {
        return insPolicyInsuredPersonMapper.selectNote(params);
    }

    @Override
    public void addInsuranceNot(Params param) {
        List<Map<String, Object>> ins_policy_att_list = param.getIns_policy_att_list();
        Map<String, Object> map = param.getMap();
        Object state = map.get("state");
        Object note_policy = map.get("note_policy");
        Object note_problem_type = map.get("note_problem_type");
        Object note_sell_back = map.get("note_sell_back");
        Object note_problem_explain = map.get("note_problem_explain");
        Object time = map.get("time");
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = map.get("employeeId");

        Map<String, Object> mas = new HashMap<String, Object>();
        if ("1".equals(note_sell_back.toString())) {
            //修改订单状态
            mas.put("policyId", note_policy);
            mas.put("state", state);
            insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
            //订单装填表新增曾状态
            mas.put("createTime", time);
            mas.put("createBy", employeeId);
            mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);//类型
            insStateMapper.updateTypeByPolicyId(note_policy,InsurancePolicyComstant.INS_STATE_HIS_1);
            insStateMapper.addInsState(mas);
        }
        //修改状态信息 ins_policy_insured_examine
        mas.clear();
        mas.put("policyId", note_policy);
        mas.put("noteType", note_problem_type);
        mas.put("noteIsOrNot", note_sell_back);
        mas.put("noteExplain", note_problem_explain);

        insPolicyInsuredExamineMapper.updateInsExamine(mas);
        if (ins_policy_att_list != null) {
            ins_policy_att_list.forEach(string -> {
                //无文件服务器
                string.put("type", 3);
                string.put("createTime",time);
                string.put("createBy",employeeId);
                string.put("policyId",note_policy);
                insPolicyAttMapper.addInsPolicyAtt(string);
            });
        }


    }

    @Override
    public void noteCancellationGrant(Map<String, Object> map) {
        Map<String, Object> mas = new HashMap<String, Object>();
        Object noteCan_org = map.get("noteCan_org");
        Object noteCan_team = map.get("noteCan_team");
        Object noteCan_empNo = map.get("noteCan_empNo");
        Object noteCan_name = map.get("noteCan_name");
        Object noteCan_policy_id = map.get("noteCan_policy_id");
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = map.get("employeeId");
        Object time = map.get("time");
        Object state = map.get("state");
        //修改订单状态
        mas.put("policyId", noteCan_policy_id);
        mas.put("state", state);
        insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
        //订单装填表新增曾状态
        mas.put("createTime", time);
        mas.put("createBy", employeeId);
        mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
        insStateMapper.updateTypeByPolicyId(noteCan_policy_id,InsurancePolicyComstant.INS_STATE_HIS_1);
        insStateMapper.addInsState(mas);
        //修改状态信息 ins_policy_insured_examine
        mas.clear();
        mas.put("policyId", noteCan_policy_id);
        mas.put("grantOrg", noteCan_org);
        mas.put("grantTeam", noteCan_team);
        mas.put("grantEmpNo", noteCan_empNo);
        mas.put("grantEmpName", noteCan_name);
        insPolicyInsuredExamineMapper.updateInsExamine(mas);
    }

    @Override
    public void noteCancellationReply(Map<String, Object> params) {
        Map<String, Object> mas = new HashMap<String, Object>();
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object state = params.get("state");
        Object noteCan_reply = params.get("noteCan_reply");
        Object noteCan_policy_id = params.get("noteCan_policy_id");
        //修改订单状态
        mas.put("policyId", noteCan_policy_id);
        mas.put("state", state);
        insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
        //订单装填表新增曾状态
        mas.put("createTime", time);
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        mas.put("createBy", employeeId);
        mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
        insStateMapper.updateTypeByPolicyId(noteCan_policy_id,InsurancePolicyComstant.INS_STATE_HIS_1);
        insStateMapper.addInsState(mas);
        //修改状态信息 ins_policy_insured_examine
        mas.clear();
        mas.put("policyId", noteCan_policy_id);
        mas.put("reasonsForReply", noteCan_reply);
        insPolicyInsuredExamineMapper.updateInsExamine(mas);
    }

    @Override
    public void noteCancellationSellBack(Map<String, Object> params) {
        Map<String, Object> mas = new HashMap<String, Object>();
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object state = params.get("state");
        Object noteCan_policy_id = params.get("noteCan_policy_id");
        //修改订单状态
        mas.put("policyId", noteCan_policy_id);
        mas.put("state", state);
        insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
        //订单装填表新增曾状态
        mas.put("createTime", time);
        mas.put("createBy", employeeId);
        mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
        insStateMapper.updateTypeByPolicyId(noteCan_policy_id,InsurancePolicyComstant.INS_STATE_HIS_1);
        insStateMapper.addInsState(mas);
    }

    //
    @Override
    public void noteCancellationAbnormal(Map<String, Object> params) {
        Map<String, Object> mas = new HashMap<String, Object>();
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object state = params.get("state");
        Object noteCan_abnormal = params.get("noteCan_abnormal");
        Object noteCan_policy_id = params.get("noteCan_policy_id");
        //修改订单状态
        mas.put("policyId", noteCan_policy_id);
        mas.put("state", state);
        insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
        //订单装填表新增曾状态
        mas.put("createTime", time);
        mas.put("createBy", employeeId);
        mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
        insStateMapper.updateTypeByPolicyId(noteCan_policy_id,InsurancePolicyComstant.INS_STATE_HIS_1);
        insStateMapper.addInsState(mas);
        mas.clear();
        mas.put("policyId", noteCan_policy_id);
        mas.put("abnormalCauaes", noteCan_abnormal);
        insPolicyInsuredExamineMapper.updateInsExamine(mas);
    }

    @Override
    public void insurancePolicyEaxmine(Map<String, Object> params) {
        Map<String, Object> mas = new HashMap<String, Object>();
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object state = params.get("insState");
        Object exampleInsName1 = params.get("exampleInsName1");
        Object exampleInsName2 = params.get("exampleInsName2");
        Object exampleInsName3 = params.get("exampleInsName3");
        Object audit_opinions = params.get("audit_opinions");
        //修改订单状态
        mas.put("policyId", exampleInsName1);
        mas.put("insState", state);
        mas.put("underwritingData", exampleInsName2);
        mas.put("takeEffectData", exampleInsName3);
        insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
        //订单装填表新增曾状态
        mas.put("createTime", time);
        //TODO zgw 2020-04-08 此处employeeId 为内勤人员id而非销售人员id
        mas.put("createBy", employeeId);
        mas.put("state", state);
        mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
        insStateMapper.updateTypeByPolicyId(exampleInsName1,InsurancePolicyComstant.INS_STATE_HIS_1);
        insStateMapper.addInsState(mas);
        mas.remove("state");
        //修改状态信息 ins_policy_insured_examine
        mas.clear();
        mas.put("policyId", exampleInsName1);
        mas.put("auditOpinions", audit_opinions);
        Map<String, Object> objectMap = insPolicyInsuredExamineMapper.selectSizeByPolicy(exampleInsName1.toString());
        if (objectMap == null) {
            insPolicyInsuredExamineMapper.addInsPolicyInsuredExamine(mas);
        } else {
            insPolicyInsuredExamineMapper.updateInsExamine(mas);
        }

    }

    /**
     * 保存回执信息
     */
    @Override
    public void addInsuranceReceipt(Map<String, Object> params) {
        Map<String, Object> mas = new HashMap<String, Object>();
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object insState = params.get("insState");
        Object policy_id = params.get("policy_id");
        Object rece_customer_signature = params.get("rece_customer_signature");
        Object rece_org_date = params.get("rece_org_date");
        Object rece_explain = params.get("rece_explain");
        Object recei_fileNames = params.get("recei_fileNames");
        Object recei_fastPaths = params.get("recei_fastPaths");
        Object fileType = params.get("recei_type");
        if(recei_fileNames!=null){
            String a = recei_fileNames.toString();
            a=a.substring(0,a.length()-1);
            a=a.substring(1);
            String s = recei_fastPaths.toString();
            s=s.substring(0,s.length()-1);
            s=s.substring(1);
            List<Object> recei_fileNamesList = Arrays.asList(a.split(","));
            List<Object> recei_fastPathsList = Arrays.asList(s.split(","));
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            recei_fileNamesList.forEach(string -> {
                Map<String,Object> stringObjectMap = new HashMap<>();
                //无文件服务器
                stringObjectMap.put("type", fileType);
                stringObjectMap.put("createTime",time);
                stringObjectMap.put("createBy",employeeId);
                stringObjectMap.put("policyId",policy_id);
                stringObjectMap.put("attName",string);
                stringObjectMap.put("attAdd",recei_fastPathsList.get(list.size()));
                list.add(stringObjectMap);
                insPolicyAttMapper.addInsPolicyAtt(stringObjectMap);
            });
        }



        //修改订单状态
        mas.put("policyId", policy_id);
        mas.put("insState", insState);
        insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
        //订单装填表新增曾状态
        mas.put("state", insState);
        mas.put("createTime", time);
        mas.put("createBy", employeeId);
        mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
        insStateMapper.updateTypeByPolicyId(policy_id,InsurancePolicyComstant.INS_STATE_HIS_1);
        insStateMapper.addInsState(mas);
        //修改状态信息 ins_policy_insured_examine
        mas.clear();
        mas.put("policyId", policy_id);
        mas.put("receCustomerSignature", rece_customer_signature);
        mas.put("receOrgDate", rece_org_date);
        mas.put("receExplain", rece_explain);
        Map<String, Object> objectMap = insPolicyInsuredExamineMapper.selectSizeByPolicy(policy_id.toString());
        if (objectMap == null) {
            insPolicyInsuredExamineMapper.addInsPolicyInsuredExamine(mas);
        } else {
            insPolicyInsuredExamineMapper.updateInsExamine(mas);
        }
    }

    /**
     * 保存审核，全部审核，批量审核
     */
    @Override
    public void submitReceiptExamine(Map<String, Object> params) {
        Map<String, Object> mas = new HashMap<String, Object>();
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object insState = params.get("insState");
        Object examine_auditresults = params.get("examine_auditresults");
        Object examine_auditopinions = params.get("examine_auditopinions");
        Object tab = params.get("tab");
        String[] split1 = tab.toString().split(",");
        List<String> list1 = Arrays.asList(split1);

        list1.forEach(pid -> {
            //修改订单状态
            mas.put("policyId", pid);
            mas.put("insState", insState);
            insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
            //订单装填表新增曾状态
            mas.put("createTime", time);
            mas.put("createBy", employeeId);
            mas.put("state", insState);
            mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
            insStateMapper.updateTypeByPolicyId(pid,InsurancePolicyComstant.INS_STATE_HIS_1);
            insStateMapper.addInsState(mas);
            mas.remove("state");
            mas.remove("type");
            //修改状态信息 ins_policy_insured_examine
            mas.clear();
            mas.put("policyId", pid);
            mas.put("receExpAuditresults", examine_auditresults);
            mas.put("receExpAuditopinions", examine_auditopinions);
            Map<String, Object> objectMap = insPolicyInsuredExamineMapper.selectSizeByPolicy(pid);
            if (objectMap == null) {
                insPolicyInsuredExamineMapper.addInsPolicyInsuredExamine(mas);
            } else {
                insPolicyInsuredExamineMapper.updateInsExamine(mas);
            }
        });
    }

    @Override
    public void commitBatchReceipt(Params pa) {
        List<String> list = pa.getList();
        Map<String, Object> params = pa.getMap();
        Object insState = params.get("insState");
        Object state = params.get("state");
        Object gen_name = params.get("gen_name");
        Object gen_org = params.get("gen_org");
        Object gen_type = params.get("gen_type");
        Object gen_date = params.get("gen_date");
        Object time = params.get("time");
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = params.get("employeeId");
        list.forEach(policyId -> {
            //修改订单状态
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> stateMap = new HashMap<String, Object>();
            map.put("policyId", policyId);
            if (insState == null) {
                map.put("state", state);
                stateMap.put("state",state);
            } else {
                map.put("insState", insState);
                stateMap.put("state",insState);
            }

            insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(map);
            //订单装填表新增曾状态
            stateMap.put("createTime", time);
            stateMap.put("createBy", employeeId);
            stateMap.put("policyId", policyId);
            stateMap.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
            insStateMapper.updateTypeByPolicyId(policyId,InsurancePolicyComstant.INS_STATE_HIS_1);
            insStateMapper.addInsState(stateMap);
            //修改状态信息 ins_policy_insured_examine
            map.clear();
            map.put("policyId", policyId);
            map.put("batchNameReceipt", gen_name);
            map.put("batchOrgIdReceipt", gen_org);
            map.put("batchTypeReceipt", gen_type);
            map.put("batchDateReceipt", gen_date);

            insPolicyInsuredExamineMapper.updateInsExamine(map);
        });


    }
/**
 * 添加回访信息
 * */
    @Override
    public void addReturnVisit(Map<String, Object> params) {
        Map<String, Object> mas = new HashMap<String, Object>();
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object insState = params.get("insState");
        Object policy_id = params.get("policy_id");
        Object return_type = params.get("RETURN_type");
        Object return_org_date = params.get("RETURN_ORG_DATE");
        Object return_explain = params.get("RETURN_Explain");
        Object statistMonth = params.get("statistMonth");
        //职级管理-回访后保留当前员工职级
        Object return_employee_no = params.get("return_employee_no");
        mas.put("insuranceSalerNo",return_employee_no);
        Map<String, Object> map = insuranceSalesInfoMapper.selectInsuranceSalesOne(mas);
        mas.clear();
        Object sales_grade_id = map.get("SALES_GRADE_ID");
        //修改订单状态
        mas.put("policyId", policy_id);
        mas.put("insState", insState);
        mas.put("statistMonth", statistMonth);
        mas.put("salesGradeId", sales_grade_id);
        insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
        //订单装填表新增曾状态
        mas.put("createTime", time);
        mas.put("createBy", employeeId);
        mas.put("state", insState);
        mas.put("type",InsurancePolicyComstant.INS_STATE_NOW_2);
        insStateMapper.updateTypeByPolicyId(policy_id,InsurancePolicyComstant.INS_STATE_HIS_1);
        insStateMapper.addInsState(mas);
        mas.remove("state");
        //修改状态信息 ins_policy_insured_examine
        mas.clear();
        mas.put("policyId", policy_id);
        mas.put("returnType", return_type);
        mas.put("returnOrgDate", return_org_date);
        mas.put("returnExplain", return_explain);
        Map<String, Object> objectMap = insPolicyInsuredExamineMapper.selectSizeByPolicy(policy_id.toString());
        if (objectMap == null) {
            insPolicyInsuredExamineMapper.addInsPolicyInsuredExamine(mas);
        } else {
            insPolicyInsuredExamineMapper.updateInsExamine(mas);
        }
    }

    @Override
    public Map<String, Object> selectNetMap(Map<String, Object> paras) {
        return insPolicyInsuredPersonMapper.selectNetMap(paras);
    }

    @Override
    public List<Map<String, Object>> selectStateMap(Map<String, Object> paras) {
        return insPolicyInsuredPersonMapper.selectStateMap(paras);
    }

    @Override
    public void addOperation(Map<String, Object> params) {
        Map<String,Object> mas = new HashMap<String,Object>();
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object insState = params.get("insState");
        Object policy_id = params.get("policy_id");

        //修改订单状态
        mas.put("policyId", policy_id);
        mas.put("insState", insState);
        insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
        //订单装填表新增曾状态
        mas.put("createTime", time);
        mas.put("createBy", employeeId);
        mas.put("state", insState);
        insStateMapper.addInsState(mas);

        /*Map<String,Object> objectMap = insPolicyInsuredExamineMapper.selectSizeByPolicy(policy_id.toString());
        if(objectMap == null){
            insPolicyInsuredExamineMapper .addInsPolicyInsuredExamine(mas);
        }else{
            insPolicyInsuredExamineMapper .updateInsExamine(mas);
        }*/
    }

    @Override
    public PageModel selectBecomeInsurancePolicyReceiveList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSizeInt", Integer.valueOf(params.get("pageSize").toString()));
        params.put("pageNoInt", Integer.valueOf(params.get("pageNo").toString()));
        logger.info("查询条件：" + params);
        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);
        List<Map<String, Object>> list = insPolicyInsuredPersonMapper.selectBecomeInsurancePolicyReceiveList(params);
        long size = insPolicyInsuredPersonMapper.selectBecomeInsurancePolicyReceiveListSize(params);
        logger.info("查询数据"  + "条数" + size);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public void becomeRecCommit(Map<String, Object> params) {
        //TODO zgw 2020-04-08 此处employeeId为内勤人员employeeId
        Object employeeId = params.get("employeeId");
        Object time = params.get("time");
        Object state = params.get("insState");
        String id = params.get("id").toString();
        String[] split = id.split(",");
        List<String> list = Arrays.asList(split);
        list.forEach(policy -> {
            Map<String, Object> mas = new HashMap<String, Object>();
            mas.put("policyId", policy);
            mas.put("insState", state);
            insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(mas);
            //订单装填表新增曾状态
            mas.put("createTime", time);
            mas.put("createBy", employeeId);
            mas.put("state", state);
            mas.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);
            insStateMapper.updateTypeByPolicyId(policy,InsurancePolicyComstant.INS_STATE_HIS_1);
            insStateMapper.addInsState(mas);

        });

    }

    @Override
    public PageModel findInsPolicyProfitsPersonPageByParams(Map<String, Object> params) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(params.get("pageNo").toString()));
        pageModel.setPageSize(Integer.valueOf(params.get("pageSize").toString()));
        long count = insPolicyProfitsPersonMapper.queryInsPolicyProfitsPersonCountByParams(params);
        pageModel.setTotalRecords(count);
        if (count > 0) {
            List<InsPolicyProfitsPersonEntity> resultList = insPolicyProfitsPersonMapper.queryInsPolicyProfitsPersonByParams(params);
            pageModel.setList(resultList);
        }
        return pageModel;
    }

    @Override
    public void deleteInsByIds(Map<String, Object> params) {
        Object ids = params.get("ids");
        String[] split = ids.toString().split(",");
        List<String> list = Arrays.asList(split);
        //删除投保单时，删除所有的与其有关系的表
        /*1.Ins_policy_insured_examine保单操作状态表  2.insInformation保单产品信息表  3.ins_policy_att 投保单附件
        * 4.ins_policy_holder  投保人  5.ins_policy_insured_person 投保单/保单被保人基础信息表  6.ins_policy_profits_person  保单受益人
        *
        * */
        //insPolicyInsuredPersonMapper.deleteInsByIds(list);
         insPolicyInsuredPersonMapper.deleteByIds(list);




    }

    @Override
    public Map<String, Object> selectInsPolicyInsuredExamineByKey(Map<String, Object> paras) {
        return insPolicyInsuredExamineMapper.selectSizeByPolicy(paras.get("id").toString());
    }

    @Override
    public void submitExamine(Map<String, Object> params) {
        Map<String, Object> forEh = new HashMap<String, Object>();
        String tab = params.get("tab").toString();//标识当前操作是 全部审批 还是 选中审批
        if ("".equals(tab)) {    //等于空 为 全部选中
            List<Map<String, Object>> list = insPolicyInsuredPersonMapper.getInsurancePolicyAllList(params);
            list.forEach(map -> {
                //1.修改原订单状态
                Object policy_id = map.get("POLICY_ID");//保单号
                Object examine_auditresults = params.get("examine_auditresults");//审核状态
                forEh.put("policyId", policy_id);
                forEh.put("state", examine_auditresults);
                insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(forEh);
                //2.审核历史表添加本次审核记录
                Object examine_auditopinions = params.get("examine_auditopinions");//审核建议
                Object employeeId = params.get("employeeId");//审核人
                Object now = params.get("now");//审核时间
                forEh.put("auditresults", examine_auditresults);
                forEh.put("auditopinions", examine_auditopinions);
                forEh.put("examinePerson", employeeId);
                forEh.put("examineTime", now);
                insPolicyInsuredExamineMapper.updateInsExamine(forEh);
                //状态历史表  添加次额  状态

                Map<String,Object> ins_state = new HashMap<String,Object>();
                ins_state.put("createTime",now );//创建人
                ins_state.put("createBy",employeeId );// 创建时间
                ins_state.put("policyId",policy_id );//保单号
                ins_state.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);//类型
                ins_state.put("state",examine_auditresults);
                insStateMapper.updateTypeByPolicyId(policy_id,InsurancePolicyComstant.INS_STATE_HIS_1);

                insStateMapper.addInsState(ins_state);

            });
        } else {
            String[] split = tab.split(",");
            Arrays.asList(split).forEach(string -> {
                Object examine_auditresults = params.get("examine_auditresults");//审核状态
                forEh.put("policyId", string);
                forEh.put("state", examine_auditresults);
                insPolicyInsuredPersonMapper.updateInsPolicyInsuredPerson(forEh);
                //2.审核历史表添加本次审核记录
                Object examine_auditopinions = params.get("examine_auditopinions");//审核建议
                Object employeeId = params.get("employeeId");//审核人
                Object now = params.get("now");//审核时间
                forEh.put("auditresults", examine_auditresults);
                forEh.put("auditopinions", examine_auditopinions);
                forEh.put("examinePerson", employeeId);
                forEh.put("examineTime", now);
                insPolicyInsuredExamineMapper.updateInsExamine(forEh);

                //状态历史表  添加次额  状态

                Map<String,Object> ins_state = new HashMap<String,Object>();
                ins_state.put("createTime",now );//创建人
                ins_state.put("createBy",employeeId );// 创建时间
                ins_state.put("policyId",string );//保单号
                ins_state.put("type", InsurancePolicyComstant.INS_STATE_NOW_2);//类型
                ins_state.put("state",examine_auditresults);

                insStateMapper.updateTypeByPolicyId(string,InsurancePolicyComstant.INS_STATE_HIS_1);
                insStateMapper.addInsState(ins_state);
            });

        }
    }

    @Override
    public PageModel findCustomerPageByParams(Map<String, Object> params) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(params.get("pageNo").toString()));
        pageModel.setPageSize(Integer.valueOf(params.get("pageSize").toString()));
        params.put("startRow", pageModel.getStartIndex());
        params.put("rows", pageModel.getPageSize());
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

//        String salesOrgId = params.get("salesOrgId") == null ? null :
//                String.valueOf(params.get("salesOrgId"));
//        if(StringUtil.isNotBlanks(salesOrgId)) {
//            salesOrgId = salesOrgInfoMapper.getChildOrgsBySaleOrgId(salesOrgId);
//            params.put("salesOrgId", salesOrgId);
//        }
        long count = insPolicyInsuredPersonMapper.queryCustomerTotalCount(params);
        pageModel.setTotalRecords(count);
        if (count > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            List<Map<String, Object>> resultList = insPolicyInsuredPersonMapper.queryCustomerPage(params);
            for(Map m : resultList){
                Object cardType = m.get("cardType");
                Object cardNo = m.get("cardNo");
                if(null != cardType && null != cardNo){
                    setFirstSalesInfo(m, cardType, cardNo);
                }
                m.put("createdTime", simpleDateFormat.format(m.get("createdTime")));
            }
            pageModel.setList(resultList);
        }else {
            pageModel.setList(Collections.EMPTY_LIST);
        }
        return pageModel;
    }

    private void setFirstSalesInfo(Map m, Object cardType, Object cardNo) {
        Map<String, Object> sales = insPolicyInsuredPersonMapper.queryFirstSales(cardType.toString(), cardNo.toString());
        if (null != sales) {
            m.put("firstSaleName", sales.get("firstSaleName"));
            m.put("firstEmployeeNo", sales.get("firstEmployeeNo"));
            m.put("firstSalesOrgName", sales.get("firstSalesOrgName"));
            m.put("firstSalesTeamName", sales.get("firstSalesTeamName"));
        }
    }

    @Override
    public List<Map<String, Object>> findCustomerListByParams(Map<String, Object> params) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        List<Map<String, Object>> resultList = insPolicyInsuredPersonMapper.queryCustomerList(params);
        if(CollectionUtils.isEmpty(resultList)){
            return Collections.EMPTY_LIST;
        }
        for(Map m : resultList){
            Object cardType = m.get("cardType");
            Object cardNo = m.get("cardNo");
            if(null != cardType && null != cardNo){
                setFirstSalesInfo(m, cardType, cardNo);
            }
            m.put("createdTime", simpleDateFormat.format(m.get("createdTime")));
        }
        return resultList;
    }

    @Override
    public boolean findExistInsuranceSlipByCompanyCode(String companyCode) {
        int count = insPolicyInsuredPersonMapper.queryInsuranceSlipCountByCompanyCode(companyCode);
        return count > 0;
    }
    @Transactional
    @Override
    public void insertImportPolicyList(Map<String, Object> p) {
    Object ins_policy_insured_person_string = p.get("ins_policy_insured_person_string");
    Object ins_policy_holder_string = p.get("ins_policy_holder_string");
    Object insInformationString = p.get("insInformationString");
    Object insertString = p.get("insertString");
    JSONArray objects = JSONObject.parseArray(ins_policy_insured_person_string.toString());
    List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
    lists.forEach(list->{
        logger.info("插入数据集：");
        int successPipSize = insPolicyInsuredPersonMapper.batchAddPolicyInsuredPerson(list);
        logger.info("插入数据集成功条数："+successPipSize);
    });
    JSONArray phObjects = JSONObject.parseArray(ins_policy_holder_string.toString());
    List<List<Map>> phLists = PublicUtil.splitListReturnListMap(phObjects, 100);
    phLists.forEach(list->{
        logger.info("插入数据集：");
        int successPhSize = insPolicyHolderMapper.batchAddInsPolicyHolder(list);
        logger.info("插入数据集成功条数："+successPhSize);
    });
    //插入受益人信息
        if(insertString != null) {
            JSONArray ipppObjects = JSONObject.parseArray(insertString.toString());
            if (!ipppObjects.isEmpty() && ipppObjects.size() > 0) {
                List<List<Map>> ipppLists = PublicUtil.splitListReturnListMap(ipppObjects, 100);
                ipppLists.forEach(list -> {
                    logger.info("插入数据集：");
                    int successIpppSize = insPolicyProfitsPersonMapper.beathAddInsPolicyProfitsPerson(list);
                    logger.info("插入数据集成功条数：" + successIpppSize);

                });
            }

        }


    JSONArray objects2 = JSONObject.parseArray(insInformationString.toString());
    List<Map<String,Object>> inforMapList = new ArrayList<Map<String,Object>>();
    objects2.forEach(infor->{
        Map inforMap = (Map) infor;
        inforMapList.add(inforMap);
    });
    List<List<?>> inforLists = PublicUtil.splitList(inforMapList, 100);
    inforLists.forEach(list ->{
        List<Map<String,Object>> inforMap = (List<Map<String,Object>>) list;
        logger.info("插入数据集："+inforMap.toString());
        insInformationMapper.addInsInformation(inforMap);
    });


    }
}
