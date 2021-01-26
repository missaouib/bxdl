package com.hzcf.plantform.controller.insurancePolicy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.domain.StorePath;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.exception.CheckException;
import com.hzcf.plantform.fastdfs.FdfsClient;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.SystemDictFeignClient;
import com.hzcf.plantform.feign.insurance.InsuranceDeptClient;
import com.hzcf.plantform.feign.insurance.InsuranceSalesInfoClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.feign.insurancePolicy.InsPolicyInsuredPersonFeign;
import com.hzcf.plantform.feign.parameter.PartnershipCommissionClient;
import com.hzcf.plantform.util.*;
import com.hzcf.pojo.Params;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurancePolicy.InsPolicyAttEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyHolderEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyInsuredPersonEntity;
import com.hzcf.pojo.insurancePolicy.InsPolicyProfitsPersonEntity;
import com.hzcf.util.StringUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.hzcf.util.DateUtil.addDayBySomeDay;

@SuppressWarnings("ALL")
@Controller
@RequestMapping("/insurance_policy")
public class InsPolicyInsuredPersonController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InsPolicyInsuredPersonFeign insPolicyInsuredPersonFeign;
    @Autowired
    InsuranceDeptClient insuranceDeptClient;
    @Autowired
    private SystemDictFeignClient systemDictFeignClient;
    @Autowired
    private FdfsClient fdfsClient;
    @Autowired
    private InsuranceSalesInfoClient insuranceSalesInfoClient;
    @Autowired
    SalesOrgInfoClient salesOrgInfoClient;
    @Autowired
    EmployeeFeignClient employeeFeignClient;
    @Autowired
    PartnershipCommissionClient partnershipCommissionClient;
    @Value("${fdfs.ip}")
	private String fastInnerUrl;

    /**
     * 投保单生成批次
     * 跳转生成批次页面：投保单
     */
    @RequestMapping("/to_generate_batches")
    public String toGenerateBatches(HttpServletRequest httpServletRequest, Model model) {
        String ids = httpServletRequest.getParameter("id");
        model.addAttribute("ids",ids);
        return "insurancePolicy/GenerateBatches";
    }

    /**
     * 保单下发
     * 跳转生成批次页面：保单
     */
    @RequestMapping("/to_generate_batches_become")
    public String toGenerateBatchesBecome(HttpServletRequest httpServletRequest, Model model) {
        String id = httpServletRequest.getParameter("id");
        model.addAttribute("ids", id);
        return "insurancePolicy/GenerateBatchesBecome";
    }

    /**
     * 跳转到 保单审核详情页面
     */
    @RequestMapping("/to_become_insurance_policy_examine")
    public String toBecomeInsurancePolicyExamine(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        getAuthDataParams(paras);
        paras.put("id", id);
        paras.put("type", Constants.POLICY_ATT_TYPE_2);
        List<InsPolicyAttEntity> ipa = insPolicyInsuredPersonFeign.selectInsPolicyAttEntityByKey(paras);
        paras.remove("type");
        List<Map<String, Object>> infor = insPolicyInsuredPersonFeign.selectInsInformationEntityByKey(paras);
        String inforJson = JSONObject.toJSONString(infor);

        InsPolicyHolderEntity iph = insPolicyInsuredPersonFeign.selectInsPolicyHolderEntityByKey(paras);
        InsPolicyInsuredPersonEntity pip = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(paras);
        List<InsPolicyProfitsPersonEntity> ppp = insPolicyInsuredPersonFeign.selectInsPolicyProfitsPersonEntityByKey(paras);
        Map<String, Object> ipp = insPolicyInsuredPersonFeign.selectInsPolicyProposalEntityByKey(paras);
        String s = JSONObject.toJSONString(ppp);
        String ipaJson = JSONObject.toJSONString(ipa);
        //查询保险公司信息
        Long insuranceCompanyId = Long.valueOf(ipp.get("insurance_company_id").toString());
        paras.clear();
        paras.put("id", insuranceCompanyId);
        InsuranceDept dept = insuranceDeptClient.selectInsuranceBasicDept(paras);
        model.addAttribute("inforJson", inforJson);
        model.addAttribute("ipa", ipa);
        model.addAttribute("ipasize", ipa.size());
        model.addAttribute("iph", iph);
        model.addAttribute("pip", pip);
        model.addAttribute("ppp", ppp);
        model.addAttribute("pppSize", ppp.size());
        model.addAttribute("ipp", ipp);
        model.addAttribute("dept", dept);
        model.addAttribute("ipaJson", ipaJson);
        model.addAttribute("pppJson", s);
        return "insurancePolicy/InsBecomeInsurancePolicyExamine";
    }

    /**
     * 保单录入
     */
    @RequestMapping("to_insurance_policy_entry")
    public String to_insurance_policy_entry(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        getAuthDataParams(paras);
        paras.put("id", id);
        paras.put("type", Constants.POLICY_ATT_TYPE_1);
        List<InsPolicyAttEntity> ipa = insPolicyInsuredPersonFeign.selectInsPolicyAttEntityByKey(paras);
        paras.remove("type");
        List<Map<String, Object>> infor = insPolicyInsuredPersonFeign.selectInsInformationEntityByKey(paras);
        String inforJson = JSONObject.toJSONString(infor);


        InsPolicyHolderEntity iph = insPolicyInsuredPersonFeign.selectInsPolicyHolderEntityByKey(paras);
        InsPolicyInsuredPersonEntity pip = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(paras);
        List<InsPolicyProfitsPersonEntity> ppp = insPolicyInsuredPersonFeign.selectInsPolicyProfitsPersonEntityByKey(paras);
        String s = JSONObject.toJSONString(ppp);
        String ipaJson = JSONObject.toJSONString(ipa);
        Map<String, Object> ipp = insPolicyInsuredPersonFeign.selectInsPolicyProposalEntityByKey(paras);
        //查询保险公司信息
        Long insuranceCompanyId = Long.valueOf(ipp.get("insurance_company_id").toString());
        paras.clear();
        paras.put("id", insuranceCompanyId);
        InsuranceDept dept = insuranceDeptClient.selectInsuranceBasicDept(paras);
        model.addAttribute("inforJson", inforJson);
        model.addAttribute("ipa", ipa);
        model.addAttribute("ipasize", ipa.size());
        model.addAttribute("iph", iph);
        model.addAttribute("pip", pip);
        model.addAttribute("ppp", ppp);
        model.addAttribute("pppSize", ppp.size());
        model.addAttribute("ipp", ipp);
        model.addAttribute("dept", dept);
        model.addAttribute("ipaJson", ipaJson);
        model.addAttribute("pppJson", s);

        return "insurancePolicy/InsurancePolicyEntry";
    }

    /**
     * 保单审核
     */
    @RequestMapping("/to_become_insurance_policy")
    public String toBecomeInsurancePolicy() {
        return "insurancePolicy/InsBecomeInsurancePolicy";
    }

    /**
     * 跳转投保照会/保单录入 页面
     */
    @RequestMapping("/to_insurance_policy_note_or_entry")
    public String toInsurancePolicyNoteOrEntry() {

        return "insurancePolicy/InsurancePolicyNoteOrEntry";
    }

    /**
     * 投保单照会回销
     */
    @RequestMapping("/to_insurance_policy_note_cancellation")
    public String toInsurancePolicyNoteCancellation() {
        return "insurancePolicy/InsurancePolicyNoteCancellation";
    }

    /**
     * 跳转到照会页面
     */
    @RequestMapping("/to_submit_note")
    public String submitNote(String id, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        logger.info("查询条件：" + id);
        params.put("policyId", id);
        //id为保单号，通过保单号查询相应信息
        Map<String, Object> msg = insPolicyInsuredPersonFeign.toSubmitNote(params);
        logger.info("返回结果：" + msg);
        model.addAttribute("msg", msg);
        return "insurancePolicy/InsurancePolicyNote";

    }

    /**
     * 跳转投保单页面
     */
    @RequestMapping("/to_insurance_policy_list")
    public String toInsurancePolicyList() {
        return "insurancePolicy/InsurancePolicyList";
    }

    /**
     * 跳转投保单审核页面
     */
    @RequestMapping("/to_insurance_policy_examine")
    public String toInsurancePolicyExamine() {
        return "insurancePolicy/InsurancePolicyExamine";
    }

    /**
     * 跳转投保单下发页面
     */
    @RequestMapping("/to_insurance_policy_grant")
    public String toInsurancePolicyGrant() {
        return "insurancePolicy/InsurancePolicyGrant";
    }

    /**
     * 跳转保单下发页面
     */
    @RequestMapping("/to_become_insurance_policy_grant")
    public String toBecomeInsurancePolicyGrant() {
        return "insurancePolicy/InsurancePolicyBecomeGrant";
    }

    /**
     * 跳转投保单添加页面
     */
    @RequestMapping("/to_add_ins_path")
    public String toAddInsPath(Model model)
    {
        model.addAttribute("fastInnerUrl",fastInnerUrl);
        return "insurancePolicy/AddInsurancePolicy";
    }

    /**
     * 跳转投保单修改
     */
    @RequestMapping("/to_update_ins_path")
    public String toUpdateInsPath(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        getAuthDataParams(paras);
        paras.put("id", id);
        paras.put("type", Constants.POLICY_ATT_TYPE_1);
        List<InsPolicyAttEntity> ipa = insPolicyInsuredPersonFeign.selectInsPolicyAttEntityByKey(paras);
        paras.remove("type");
        List<Map<String, Object>> infor = insPolicyInsuredPersonFeign.selectInsInformationEntityByKey(paras);
        String inforJson = JSONObject.toJSONString(infor);
        String ipaJson = JSONObject.toJSONString(ipa);
        InsPolicyHolderEntity iph = insPolicyInsuredPersonFeign.selectInsPolicyHolderEntityByKey(paras);
        InsPolicyInsuredPersonEntity pip = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(paras);
        List<InsPolicyProfitsPersonEntity> ppp = insPolicyInsuredPersonFeign.selectInsPolicyProfitsPersonEntityByKey(paras);
        String s = JSONObject.toJSONString(ppp);
        Map<String, Object> ipp = insPolicyInsuredPersonFeign.selectInsPolicyProposalEntityByKey(paras);
        //查询保险公司信息
        Long insuranceCompanyId = Long.valueOf(ipp.get("insurance_company_id").toString());
        paras.clear();
        paras.put("id", insuranceCompanyId);
        InsuranceDept dept = insuranceDeptClient.selectInsuranceBasicDept(paras);
        model.addAttribute("infor", infor);
        model.addAttribute("ipa", ipa);
        model.addAttribute("ipasize", ipa.size());
        model.addAttribute("iph", iph);
        model.addAttribute("pip", pip);
        model.addAttribute("ppp", ppp);
        model.addAttribute("pppSize", ppp.size());
        model.addAttribute("ipp", ipp);
        model.addAttribute("dept", dept);
        model.addAttribute("ipaJson", ipaJson);
        model.addAttribute("pppJson", s);
        model.addAttribute("inforJson",inforJson);
        model.addAttribute("fastInnerUrl",fastInnerUrl);
        return "insurancePolicy/UpdateInsurancePolicy";
    }

    /**
     * 投保单下发分页查询
     */
    @RequestMapping("/do_insurance_policy_grant")
    @ResponseBody
    public DataMsg doInsurancePolicyGrant(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String not = request.getParameter("not");
            if(not != null && "not".equals(not)){
                //第一次加载页面进入该判断 将查询条件（保单号重新定义）手动更改 使其查不到值
                        if(params.get("selectListIds")==null || "".equals(params.get("selectListIds"))){
                            params.put("policyId", "!@#$%^)(((((*");
                        }

            }

                String pageNo = request.getParameter("pageNo");
                if (StringUtil.isNotBlank(pageNo)) {
                    params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
                } else {
                    params.put("pageNo", 0);
                }
                String pageSize = request.getParameter("pageSize");
                if (StringUtil.isNotBlank(pageSize)) {
                    params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
                } else {
                    params.put("pageSize", 10);
                }
                String principalDeputySign = request.getParameter("principalDeputySign");
                params.put("principalDeputySign", principalDeputySign);
                String type = request.getParameter("type");
                String insState = request.getParameter("insState");
                params.put("insState", insState);
                params.put("type", type);
                PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);

                List list = model.getList();
                dataMsg.setTotal(model.getTotalRecords());
                dataMsg.setRows(model.getList());
                dataMsg.setMessageCode("200");

        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     * 保单下发分页查询
     */
    @RequestMapping("/do_insurance_policy_become_grant")
    @ResponseBody
    public DataMsg doInsurancePolicyBecomeGrant(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);

            String not = request.getParameter("not");
            if(not != null && "not".equals(not)){
                //第一次加载页面进入该判断 将查询条件（保单号重新定义）手动更改 使其查不到值
                if(params.get("selectListIds")==null || "".equals(params.get("selectListIds"))){
                    params.put("policyId", "!@#$%^)(((((*");
                }

            }
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String type = request.getParameter("type");
            String insState = request.getParameter("insState");
            params.put("type", type);
            params.put("insState", insState);
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign",principalDeputySign);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    @RequestMapping("/do_insurance_policy_examine")
    @ResponseBody
    public DataMsg doInsurancePolicyExamine(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign", principalDeputySign);
            String type = request.getParameter("type");
            params.put("type", type);
            String state = request.getParameter("state");
            params.put("state", state);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     * 分页：投保单照会回销
     */
    @RequestMapping("/do_insurance_policy_note_cancellation")
    @ResponseBody
    public DataMsg doInsurancePolicyNoteCancellation(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);

            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign",principalDeputySign);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
           // logger.info(list.toString());
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     * 分页：投保照会/保单录入
     */
    @RequestMapping("/do_insurance_policy_note_or_entry")
    @ResponseBody
    public DataMsg doInsurancePolicyNoteOrEntry(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign",principalDeputySign);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }
    /**
    *Author:孙旭明
    *@Description:导入投保单
    *@Class:
    *@Param:[file]
    *@return:java.util.Map<java.lang.String,java.lang.Object>
    *@Date:2019/11/6
    */
    @RequestMapping(value = "/import_note_or_entry", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importNoteOrEntry(MultipartFile file) throws Exception {
    Map<String,Object> params = new HashMap<String,Object>();
    Map<String,Object> msg =new HashMap<String,Object>();
    DataMsg dataMsg = new DataMsg();
    //从shiro的session中取Employee
    Subject subject = SecurityUtils.getSubject();
    //取身份信息
    Employee employee = (Employee) subject.getPrincipal();

    Map<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("employeeId", employee.getEmployeeId());
    SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
    String now = format.format(new Date());
    params.put("now",now);
    params.put("emp",stringObjectMap);
    try {
        if (!file.isEmpty()) {
            FileInputStream in = (FileInputStream) file.getInputStream();
            Workbook workbook = WorkbookFactory.create(in);  //兼容xls和xlsx
            Map<String,Sheet> sheetMap = new HashMap<String,Sheet>();
            for(int i = 0; i<workbook.getNumberOfSheets();i++){
                String sheetName = workbook.getSheetName(i);
                Sheet sheetAt = workbook.getSheetAt(i);
                sheetMap.put(sheetName,sheetAt);
            }
            List<List<String>> inspolicyAttRowList = new ArrayList<>();//---保单数据集---
            Sheet att = sheetMap.get("Beneficiary");
            int attNumberOfCells = att.getRow(0).getPhysicalNumberOfCells();
            for (int j = 1; j < att.getPhysicalNumberOfRows(); j++){
                Row row=att.getRow(j);
                if (row != null) {
                    List<String> productCellList = new ArrayList<>();
                    for (int k = 0; k <= attNumberOfCells; k++) {//获取每个单元格
                        productCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                    }
                     List<String> filtered=productCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                    if (CollUtil.isNotEmpty(filtered)){
                         inspolicyAttRowList.add(productCellList);
                    }else {
                        logger.info("整行单元格都是空值，忽略======");
                    }

                }
            }
            List<List<String>> productRowList = new ArrayList<>();//---产品数据集---
            Sheet product = sheetMap.get("product");
             int productNumberOfCells = product.getRow(0).getPhysicalNumberOfCells();
            for (int j = 1; j < product.getPhysicalNumberOfRows(); j++){
                Row row=product.getRow(j);
                if (row != null) {
                    List<String> productCellList = new ArrayList<>();
                    for (int k = 0; k <= productNumberOfCells; k++) {//获取每个单元格
                        productCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                    }
                     List<String> filtered=productCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                    if (CollUtil.isNotEmpty(filtered)){
                         productRowList.add(productCellList);
                    }else {
                        logger.info("整行单元格都是空值，忽略======");
                    }


                }
            }
            List<List<String>> inspolicyRowList = new ArrayList<>();//---保单数据集---
            Sheet insPolicy = sheetMap.get("inspolicy");
            int insPolicyNumberOfCells = insPolicy.getRow(0).getPhysicalNumberOfCells();
            for (int j = 1; j < insPolicy.getPhysicalNumberOfRows(); j++){
                Row row=insPolicy.getRow(j);
                if (row != null) {
                    List<String> insPolicyCellList = new ArrayList<>();
                    for (int k = 0; k <= insPolicyNumberOfCells; k++) {//获取每个单元格
                        insPolicyCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                    }
                     List<String> filtered=insPolicyCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                    if (CollUtil.isNotEmpty(filtered)){
                         inspolicyRowList.add(insPolicyCellList);
                    }else {
                        logger.info("整行单元格都是空值，忽略======");
                    }

                }
            }
            if (!CollectionUtils.isEmpty(productRowList) && !CollectionUtils.isEmpty(inspolicyRowList)) {
                List<Map<String, Object>> sexDictList = systemDictFeignClient.findDictNameByDictType("SEX");
                List<Map<String, Object>> cardDictList = systemDictFeignClient.findDictNameByDictType("CARD");
                List<Map<String, Object>> relationshipDictList = systemDictFeignClient.findDictNameByDictType("RELATIONSHIP");
                List<Map<String,Object>> payTypeDictList = systemDictFeignClient.findDictNameByDictType("PAY_TYPE");
                //保单号 对应 产品名称
                Map<String,Object> insIDProduName = new HashMap<String,Object>();
                List<Map<String,Object>> ins_policy_insured_person_List = new LinkedList<Map<String,Object>>();
                List<Map<String,Object>> ins_policy_holder_List = new LinkedList<Map<String,Object>>();
                List<Map<String,Object>> insInformationList = new LinkedList<Map<String,Object>>();
                List<Map<String,Object>> insertList = new LinkedList<Map<String,Object>>();
                Map<String,Object> policyCodeEmpNo = new HashMap<String,Object>();
                Set<String> policyIdSet = new HashSet<String>();
                //保单入库
                importInsertInsPolicy(inspolicyRowList,sexDictList,cardDictList,relationshipDictList,payTypeDictList,params,ins_policy_insured_person_List,ins_policy_holder_List,policyCodeEmpNo,Constants.IMPL_TYPE_NOTE_OR_ENTRY,policyIdSet);
                //产品入库
                importInsertInsProduct(productRowList,sexDictList,cardDictList,relationshipDictList,params,policyCodeEmpNo,insInformationList,msg,policyIdSet);
                //导入受益人
                if (!CollectionUtils.isEmpty(inspolicyAttRowList)){
                    importInsertInsAtt(inspolicyAttRowList,sexDictList,cardDictList,relationshipDictList,params,insertList);
                }

                //入库
                String ins_policy_insured_person_string = JSONObject.toJSONString(ins_policy_insured_person_List);
                String ins_policy_holder_string = JSONObject.toJSONString(ins_policy_holder_List);
                String insInformationString = JSONObject.toJSONString(insInformationList);
                String insertString = JSONObject.toJSONString(insertList);
                Map<String,Object> p = new HashMap<String,Object>();
                p.put("ins_policy_insured_person_string", ins_policy_insured_person_string);
                p.put("ins_policy_holder_string",ins_policy_holder_string );
                p.put("insInformationString",insInformationString );
                p.put("insertString", insertString);
                msg = insPolicyInsuredPersonFeign.insertImportPolicyList(p);
            }else{
                msg.put("code","0000");
            }
        } else {
            msg.put("code","0000");
        }
    }catch (CheckException e){
         String message = e.getMessage();
         msg.put("code","500");
         msg.put("error",message);
//       String message = e.getMessage();
//        String[] split = message.split(":");
//        if("InsuranceSalesInfoIsNullErroe".equals(split[0])){
//            msg.put("code","500");
//            msg.put("error","未查询到员工："+split[1]);
//        }else if("EmpNoIsNullErroe".equals(split[0])){
//            msg.put("code","500");
//            msg.put("error","员工编号不能为空："+split[1]);
//        }else if("ProIdMapIsNullError".equals(split[0])){
//            msg.put("code","500");
//            msg.put("error","未查询到协议Id："+split[1]);
//        }else if("totalPremiumIsNullErroe".equals(split[0])){
//            msg.put("code","500");
//            msg.put("error","总保费不能为空："+split[1]);
//        }else if("orgNullErroe".equals(split[0])){
//            msg.put("code","500");
//            msg.put("error","未查询到保险公司："+split[1]);
//        }
        logger.error("导入承保清单异常|异常：{}", e);
    }
    catch (Exception e) {
        logger.error("导入承保清单异常|异常：{}", e);
        msg.put("code","400");
    }
    return msg;
}
  /*  public DataMsg importInsuranceNoteOrEntry(MultipartFile file) throws Exception {
        DataMsg dataMsg = new DataMsg();
        //从shiro的session中取Employee
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
        try {
            File toFile = null;
            if (!file.isEmpty()) {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
                List<List<Object>> excelList = ImportUtil.readExcel(toFile);
                if (CollectionUtils.isEmpty(excelList)) {
                    dataMsg.setMessageCode("200001");
                } else {

                    List<Map<String, Object>> sexDictList = systemDictFeignClient.findDictNameByDictType("SEX");
                    List<Map<String, Object>> cardDictList = systemDictFeignClient.findDictNameByDictType("CARD");
                    List<Map<String, Object>> relationshipDictList = systemDictFeignClient.findDictNameByDictType("RELATIONSHIP");
                    List<Map<String, Object>> periodOfList = systemDictFeignClient.findDictNameByDictType("BXQJ");
                    List<Map<String, Object>> paymentMethodList = systemDictFeignClient.findDictNameByDictType("JFFS");
                    List<Map<String, Object>> paymentPeriodSignList = systemDictFeignClient.findDictNameByDictType("NQNL");

                    SimpleDateFormat fmat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                    String now = fmat.format(new Date());
                    excelList.forEach(a -> {

                        String compostteCode = a.get(1).toString();  // 产品代码
                        String affirmNo = a.get(2).toString();  // 确认书编号
                        String policyId = a.get(3).toString(); // 投保单号
                        String proposeDate = a.get(4).toString();  // 投保日期
                        String resoldNo = a.get(5).toString();  // 回销编号
                        String empNo = a.get(6).toString();  // 员工编号
                        String insuredAmount = a.get(8).toString();  // 保额
                        String premium = a.get(9).toString();  // 保费
                        String periodOf = a.get(10).toString();  // 保险期间类型
                        for (Map periodOfDict : periodOfList) {
                            if (periodOf.equals(periodOfDict.get("dict_name"))) {
                                periodOf = periodOfDict.get("dict_code").toString();
                            }
                        }
                        String periodOfInsurance = a.get(11).toString();  // 保险期间
                        String paymentMethod = a.get(12).toString();  // 缴费方式
                        for (Map paymentMethodDict : paymentMethodList) {
                            if (paymentMethod.equals(paymentMethodDict.get("dict_name"))) {
                                paymentMethod = paymentMethodDict.get("dict_code").toString();
                            }
                        }
                        String paymentPeriodSign = a.get(13).toString();  // 缴费期间类型
                        for (Map paymentPeriodSignDict : paymentPeriodSignList) {
                            if (paymentPeriodSign.equals(paymentPeriodSignDict.get("dict_name"))) {
                                paymentPeriodSign = paymentPeriodSignDict.get("dict_code").toString();
                            }
                        }
                        String payment = a.get(14).toString();  // 缴费期间
                        String policyHolderName = a.get(15).toString();  // 投保人姓名
                        String policyHolderSex = a.get(16).toString();  // 投保人性别
                        for (Map sexDict : sexDictList) {
                            if (policyHolderSex.equals(sexDict.get("dict_name"))) {
                                policyHolderSex = sexDict.get("dict_code").toString();
                            }
                        }
                        String policyHolderCardType = a.get(17).toString();  // 投保人证件类型
                        for (Map cardDict : cardDictList) {
                            if (policyHolderCardType.equals(cardDict.get("dict_name"))) {
                                policyHolderCardType = cardDict.get("dict_code").toString();
                            }
                        }
                        String policyHolderCardNo = a.get(18).toString();  // 投保人身份证号
                        String policyHolderBirthday = a.get(19).toString();  // 投保人生日
                        String relationship = a.get(20).toString();  // 被保险人与投保人关系
                        for (Map relationshipDict : relationshipDictList) {
                            if (relationship.equals(relationshipDict.get("dict_name"))) {
                                relationship = relationshipDict.get("dict_code").toString();
                            }
                        }
                        String accountHolder = a.get(21).toString();  // 开户人姓名
                        String bankName = a.get(22).toString();  // 开户行名称
                        String bankAccount = a.get(23).toString();  // 开户行账号
                        String policyHolderMobile = a.get(24).toString();  // 投保人联系方式
                        String policyHolderAddress = a.get(25).toString();  // 投保人地址
                        String policyHolderOccupation = a.get(26).toString();  // 投保人职业
                        String insuredName = a.get(27).toString();  // 被保险人姓名
                        String insuredSex = a.get(28).toString();  // 被保险人性别
                        for (Map sexDict : sexDictList) {
                            if (insuredSex.equals(sexDict.get("dict_name"))) {
                                insuredSex = sexDict.get("dict_code").toString();
                            }
                        }
                        String insuredCardType = a.get(29).toString();  // 被保险人证件类型
                        for (Map cardDict : cardDictList) {
                            if (insuredCardType.equals(cardDict.get("dict_name"))) {
                                insuredCardType = cardDict.get("dict_code").toString();
                            }
                        }
                        String insuredCardNo = a.get(30).toString();  // 被保险人身份证号
                        String insuredBirthday = a.get(31).toString();  // 被保险人生日
                        String insuredMobile = a.get(32).toString();  // 被保险人联系电话
                        String insuredAddress = a.get(33).toString();  // 被保险人地址
                        String insuredOccupation = a.get(34).toString();  // 被保险人职业

                        List<Map<String, Object>> ins_policy_profits_person_list = new ArrayList<>();
                        String profitsName = a.get(35).toString();  // 受益人姓名
                        if (StringUtils.isNotBlank(profitsName)) {
                            HashMap<String, Object> profitsMap = Maps.newHashMapWithExpectedSize(7);
                            profitsMap.put("name", profitsName);
                            profitsMap.put("profitsRelationship", a.get(36).toString()); // 与保险人关系
                            profitsMap.put("level", a.get(37).toString()); // 顺位
                            profitsMap.put("benefitRatio", a.get(38).toString()); // 比例
                            for (Map sexDict : sexDictList) {
                                if (a.get(39).toString().equals(sexDict.get("dict_name"))) {
                                    profitsMap.put("sex", sexDict.get("dict_code").toString()); // 性别
                                }
                            }
                            for (Map cardDict : cardDictList) {
                                if (a.get(40).toString().equals(cardDict.get("dict_name"))) {
                                    profitsMap.put("cardType", cardDict.get("dict_code").toString()); // 证件类型
                                }
                            }
                            profitsMap.put("cardNo", a.get(41).toString()); // 证件号
                            ins_policy_profits_person_list.add(profitsMap);
                        }
                        if (a.size() > 42) {
                            String profitsName2 = a.get(42).toString();  // 受益人姓名
                            if (StringUtils.isNotBlank(profitsName2)) {
                                HashMap<String, Object> profitsMap = Maps.newHashMapWithExpectedSize(7);
                                profitsMap.put("name", profitsName2);
                                profitsMap.put("profitsRelationship", a.get(43).toString()); // 与保险人关系
                                profitsMap.put("level", a.get(44).toString()); // 顺位
                                profitsMap.put("benefitRatio", a.get(45).toString()); // 比例
                                for (Map sexDict : sexDictList) {
                                    if (a.get(46).toString().equals(sexDict.get("dict_name"))) {
                                        profitsMap.put("sex", sexDict.get("dict_code").toString()); // 性别
                                    }
                                }
                                for (Map cardDict : cardDictList) {
                                    if (a.get(47).toString().equals(cardDict.get("dict_name"))) {
                                        profitsMap.put("cardType", cardDict.get("dict_code").toString()); // 证件类型
                                    }
                                }
                                profitsMap.put("cardNo", a.get(48).toString()); // 证件号
                                ins_policy_profits_person_list.add(profitsMap);
                            }
                        }
                        if (a.size() > 49) {
                            String profitsName3 = a.get(49).toString();  // 受益人姓名
                            if (StringUtils.isNotBlank(profitsName3)) {
                                HashMap<String, Object> profitsMap = Maps.newHashMapWithExpectedSize(7);
                                profitsMap.put("name", profitsName3);
                                profitsMap.put("profitsRelationship", a.get(50).toString()); // 与保险人关系
                                profitsMap.put("level", a.get(51).toString()); // 顺位
                                profitsMap.put("benefitRatio", a.get(52).toString()); // 比例
                                for (Map sexDict : sexDictList) {
                                    if (a.get(53).toString().equals(sexDict.get("dict_name"))) {
                                        profitsMap.put("sex", sexDict.get("dict_code").toString()); // 性别
                                    }
                                }
                                for (Map cardDict : cardDictList) {
                                    if (a.get(54).toString().equals(cardDict.get("dict_name"))) {
                                        profitsMap.put("cardType", cardDict.get("dict_code").toString()); // 证件类型
                                    }
                                }
                                profitsMap.put("cardNo", a.get(55).toString()); // 证件号
                                ins_policy_profits_person_list.add(profitsMap);
                            }
                        }
                        if (a.size() > 56) {
                            String profitsName4 = a.get(56).toString();  // 受益人姓名
                            if (StringUtils.isNotBlank(profitsName4)) {
                                HashMap<String, Object> profitsMap = Maps.newHashMapWithExpectedSize(7);
                                profitsMap.put("name", profitsName4);
                                profitsMap.put("profitsRelationship", a.get(57).toString()); // 与保险人关系
                                profitsMap.put("level", a.get(58).toString()); // 顺位
                                profitsMap.put("benefitRatio", a.get(59).toString()); // 比例
                                for (Map sexDict : sexDictList) {
                                    if (a.get(60).toString().equals(sexDict.get("dict_name"))) {
                                        profitsMap.put("sex", sexDict.get("dict_code").toString()); // 性别
                                    }
                                }
                                for (Map cardDict : cardDictList) {
                                    if (a.get(61).toString().equals(cardDict.get("dict_name"))) {
                                        profitsMap.put("cardType", cardDict.get("dict_code").toString()); // 证件类型
                                    }
                                }
                                profitsMap.put("cardNo", a.get(62).toString()); // 证件号
                                ins_policy_profits_person_list.add(profitsMap);
                            }
                        }
                        if (a.size() > 63) {
                            String profitsName5 = a.get(63).toString();  // 受益人姓名
                            if (StringUtils.isNotBlank(profitsName5)) {
                                HashMap<String, Object> profitsMap = Maps.newHashMapWithExpectedSize(7);
                                profitsMap.put("name", profitsName5);
                                profitsMap.put("profitsRelationship", a.get(64).toString()); // 与保险人关系
                                profitsMap.put("level", a.get(65).toString()); // 顺位
                                profitsMap.put("benefitRatio", a.get(66).toString()); // 比例
                                for (Map sexDict : sexDictList) {
                                    if (a.get(67).toString().equals(sexDict.get("dict_name"))) {
                                        profitsMap.put("sex", sexDict.get("dict_code").toString()); // 性别
                                    }
                                }
                                for (Map cardDict : cardDictList) {
                                    if (a.get(68).toString().equals(cardDict.get("dict_name"))) {
                                        profitsMap.put("cardType", cardDict.get("dict_code").toString()); // 证件类型
                                    }
                                }
                                profitsMap.put("cardNo", a.get(69).toString()); // 证件号
                                ins_policy_profits_person_list.add(profitsMap);
                            }
                        }

                        Map<String, Object> ins_policy_insured_person = Maps.newHashMapWithExpectedSize(12);
                        ins_policy_insured_person.put("policyId", policyId);//保单单号
                        ins_policy_insured_person.put("cardType", insuredCardType);//证件号
                        ins_policy_insured_person.put("cardNo", insuredCardNo);//证件号
                        ins_policy_insured_person.put("name", insuredName);//NAME
                        ins_policy_insured_person.put("relationship", relationship);//与投保人关系
                        ins_policy_insured_person.put("sex", insuredSex);// 性别
                        ins_policy_insured_person.put("birthday", insuredBirthday);// 生日
                        ins_policy_insured_person.put("mobile", insuredMobile);// 联系电话
                        ins_policy_insured_person.put("homeAddress", insuredAddress);// 地址
                        ins_policy_insured_person.put("occupation", insuredOccupation);// 职业
                        ins_policy_insured_person.put("createTime", now);//创建时间
                        ins_policy_insured_person.put("createBy", employee.getEmployeeId());//创建人
                        ins_policy_insured_person.put("type", Constants.POLICY_TYPT_1);// 类型
                        ins_policy_insured_person.put("source", "2");// 来源 2 导入
                        ins_policy_insured_person.put("state", Constants.POLICY_STATUS_5);// 保单状态
                        ins_policy_insured_person.put("insure", proposeDate);// 投保日期
                        ins_policy_insured_person.put("affirmNo", affirmNo);// 生效日期
                        ins_policy_insured_person.put("createTime", now);
                        ins_policy_insured_person.put("updateTime", now);

                        Map<String, Object> ins_policy_holder = Maps.newHashMapWithExpectedSize(4);
                        ins_policy_holder.put("type", Constants.POLICY_TYPT_2);
                        ins_policy_holder.put("policyId", policyId);
                        ins_policy_holder.put("name", policyHolderName);
                        ins_policy_holder.put("sex", policyHolderSex);
                        ins_policy_holder.put("cardType", policyHolderCardType);
                        ins_policy_holder.put("cardNo", policyHolderCardNo);
                        ins_policy_holder.put("birthday", policyHolderBirthday);
                        ins_policy_holder.put("accountHolder", accountHolder);
                        ins_policy_holder.put("bankName", bankName);
                        ins_policy_holder.put("bankAccount", bankAccount);
                        ins_policy_holder.put("mobile", policyHolderMobile);
                        ins_policy_holder.put("homeAddress", policyHolderAddress);
                        ins_policy_holder.put("occupation", policyHolderOccupation);
                        ins_policy_holder.put("createTime", now);
                        ins_policy_holder.put("updateTime", now);

                        Map<String, Object> ins_information = Maps.newHashMapWithExpectedSize(3);
                        ins_information.put("productId", compostteCode);
                        ins_information.put("insuredAmount", insuredAmount);
                        ins_information.put("premium", premium);
                        ins_information.put("policyId", policyId);
                        ins_information.put("periodOf", periodOf);
                        ins_information.put("periodOfInsurance", periodOfInsurance);
                        ins_information.put("paymentMethod", paymentMethod);
                        ins_information.put("paymentPeriodSign", paymentPeriodSign);
                        ins_information.put("payment", payment);
                        ins_information.put("createTime", now);
                        ins_information.put("updateTime", now);

                        Map<String, Object> ins_policy_proposal = Maps.newHashMapWithExpectedSize(5);
                        ins_policy_proposal.put("policyId", policyId);
                        ins_policy_proposal.put("agentId", empNo);
                        ins_policy_proposal.put("totalPremium", premium);
                        ins_policy_proposal.put("createdBy", employee.getEmployeeId());
                        ins_policy_proposal.put("createdTime", now);
                        ins_policy_proposal.put("updateTime", now);

                        Map<String, Object> ins_policy_insured_examine = Maps.newHashMapWithExpectedSize(5);
                        ins_policy_insured_examine.put("policyId", policyId);
                        ins_policy_insured_examine.put("resoldNo", resoldNo);
                        ins_policy_insured_examine.put("createdTime", now);
                        ins_policy_insured_examine.put("updateTime", now);

                        Params params = new Params();
                        params.setIns_policy_insured_person(ins_policy_insured_person);
                        params.setIns_policy_holder(ins_policy_holder);
                        params.setIns_information(ins_information);
                        params.setIns_policy_proposal(ins_policy_proposal);
                        params.setIns_policy_profits_person_list(ins_policy_profits_person_list);
                        params.setIns_policy_insured_examine(ins_policy_insured_examine);

                        Map<String, Object> insParams = Maps.newHashMapWithExpectedSize(1);
                        insParams.put("id", policyId);
                        //根据保单号查询是否存在 不存在
                        InsPolicyInsuredPersonEntity insPolicyInsuredPersonEntity = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(insParams);
                        if (insPolicyInsuredPersonEntity == null) {
                            Map<String, Object> map = insPolicyInsuredPersonFeign.addInsurancePolicy(params);
                            dataMsg.setMessageCode(map.get("code").toString());
                        } else {
                            Map<String, Object> map = insPolicyInsuredPersonFeign.updateInsurancePolicy(params);
                            dataMsg.setMessageCode(map.get("code").toString());
                        }
                    });
                }
            } else {
                // 上传空文件
                dataMsg.setMessageCode("200002");
            }
        } catch (Exception e) {
            logger.error("导入承保清单异常|异常：{}", e);
            dataMsg.setMessageCode("400");
        }
        return dataMsg;
    }*/


    @RequestMapping("/do_insurance_policy")
    @ResponseBody
    public DataMsg doInsurancePolicy(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign",principalDeputySign);
            String type = request.getParameter("type");
            params.put("type", type);
            //数据权限相关
            String myDeptNo = "";
            if(params.get("myDeptNo") != null){
                myDeptNo = String.valueOf(params.get("myDeptNo"));
            }
            String myAllOrgIds = salesOrgInfoClient.findEmployeeAllOrgs(myDeptNo);
            params.put("myAllOrgIds", myAllOrgIds);


            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            if(!CollectionUtils.isEmpty(list)){
                Map<String,BigDecimal> totalPremium = insPolicyInsuredPersonFeign.getTotalPremium(params);
                Object total_premium = totalPremium.get("TOTAL_PREMIUM");
                dataMsg.setData(total_premium.toString());
            }
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(list);
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     *@描述 批量导入新投保单（初始状态的还）
     *@创建人 qin lina
     *@创建时间 2020/3/3
     */
    @RequestMapping(value = "/importInsurancePolicy", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importInsurancePolicy(MultipartFile file) throws Exception {
   Map<String,Object> params = new HashMap<String,Object>();
    Map<String,Object> msg =new HashMap<String,Object>();
    DataMsg dataMsg = new DataMsg();
    //从shiro的session中取Employee
    Subject subject = SecurityUtils.getSubject();
    //取身份信息
    Employee employee = (Employee) subject.getPrincipal();

    Map<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("employeeId", employee.getEmployeeId());
    SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
    String now = format.format(new Date());
    params.put("now",now);
    params.put("emp",stringObjectMap);
    try {
        if (!file.isEmpty()) {
            FileInputStream in = (FileInputStream) file.getInputStream();
           Workbook workbook = WorkbookFactory.create(in);  //兼容xls和xlsx
            Map<String,Sheet> sheetMap = new HashMap<String,Sheet>();
            for(int i = 0; i<workbook.getNumberOfSheets();i++){
                String sheetName = workbook.getSheetName(i);
                Sheet sheetAt = workbook.getSheetAt(i);
                sheetMap.put(sheetName,sheetAt);
            }
            List<List<String>> inspolicyAttRowList = new ArrayList<>();//---保单数据集---
            Sheet att = sheetMap.get("Beneficiary");
            //TODO:测试 获取第一行的不为空的的列个数
            //获取第一行的不为空的的列个数
            int attNumberOfCells = att.getRow(0).getPhysicalNumberOfCells();
            for (int j = 1; j < att.getPhysicalNumberOfRows(); j++){
                Row row = att.getRow(j);
                //TODO:新加row非空判断
                if(row!=null){
                    List<String> productCellList = new ArrayList<>();
                    for (int k = 0; k <= attNumberOfCells; k++) {//获取每个单元格
                        productCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                    }
                     List<String> filtered=productCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                   if(CollUtil.isNotEmpty(filtered)){
                        inspolicyAttRowList.add(productCellList);
                    }else {
                        logger.info("整个单元格都是空值，忽略======");
                    }

                }

            }
            List<List<String>> productRowList = new ArrayList<>();//---产品数据集---
            Sheet product = sheetMap.get("product");
             //获取第一行的不为空的的列个数
            int productNumberOfCells = product.getRow(0).getPhysicalNumberOfCells();
            for (int j = 1; j < product.getPhysicalNumberOfRows(); j++){
                Row row=product.getRow(j);
                if(row!=null) {
                    List<String> productCellList = new ArrayList<>();
                    for (int k = 0; k <= productNumberOfCells; k++) {//获取每个单元格
                        productCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                    }
                     List<String> filtered=productCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                    if(CollUtil.isNotEmpty(filtered)){
                        productRowList.add(productCellList);
                    }else {
                        logger.info("整个单元格都是空值，忽略======");
                    }

                }

            }
            List<List<String>> inspolicyRowList = new ArrayList<>();//---保单数据集---
            Sheet insPolicy = sheetMap.get("inspolicy");
            //获取第一行的不为空的的列个数
            int insPolicyNumberOfCells = insPolicy.getRow(0).getPhysicalNumberOfCells();
            for (int j = 1; j < insPolicy.getPhysicalNumberOfRows(); j++){
                Row row=insPolicy.getRow(j);
                 if(row!=null) {
                    List<String> insPolicyCellList = new ArrayList<>();
                    for (int k = 0; k <= insPolicyNumberOfCells; k++) {//获取每个单元格
                        insPolicyCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                    }
                     List<String> filtered=insPolicyCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                    if(CollUtil.isNotEmpty(filtered)){
                        inspolicyRowList.add(insPolicyCellList);
                    }else {
                        logger.info("整个单元格都为空，忽略======");
                    }

                }
            }
            if (!CollectionUtils.isEmpty(productRowList) && !CollectionUtils.isEmpty(inspolicyRowList)) {
                List<Map<String, Object>> sexDictList = systemDictFeignClient.findDictNameByDictType("SEX");
                List<Map<String, Object>> cardDictList = systemDictFeignClient.findDictNameByDictType("CARD");
                List<Map<String, Object>> relationshipDictList = systemDictFeignClient.findDictNameByDictType("RELATIONSHIP");
                List<Map<String,Object>> payTypeDictList = systemDictFeignClient.findDictNameByDictType("PAY_TYPE");
                //保单号 对应 产品名称
                Map<String,Object> insIDProduName = new HashMap<String,Object>();
                List<Map<String,Object>> ins_policy_insured_person_List = new LinkedList<Map<String,Object>>();
                List<Map<String,Object>> ins_policy_holder_List = new LinkedList<Map<String,Object>>();
                List<Map<String,Object>> insInformationList = new LinkedList<Map<String,Object>>();
                List<Map<String,Object>> insertList = new LinkedList<Map<String,Object>>();
                Map<String,Object> policyCodeEmpNo = new HashMap<String,Object>();
                Set<String> policyIdSet = new HashSet<String>();
                //保单入库
                importInsertInsPolicy(inspolicyRowList,sexDictList,cardDictList,relationshipDictList,payTypeDictList,params,ins_policy_insured_person_List,ins_policy_holder_List,policyCodeEmpNo,Constants.IMPL_TYPE_NEW_POLICY,policyIdSet);
                //产品入库
                importInsertInsProduct(productRowList,sexDictList,cardDictList,relationshipDictList,params,policyCodeEmpNo,insInformationList,msg,policyIdSet);
                //导入受益人
                if (!CollectionUtils.isEmpty(inspolicyAttRowList)){
                    importInsertInsAtt(inspolicyAttRowList,sexDictList,cardDictList,relationshipDictList,params,insertList);
                }
                //入库
                String ins_policy_insured_person_string = JSONObject.toJSONString(ins_policy_insured_person_List);
                String ins_policy_holder_string = JSONObject.toJSONString(ins_policy_holder_List);
                String insInformationString = JSONObject.toJSONString(insInformationList);
                String insertString = JSONObject.toJSONString(insertList);
                Map<String,Object> p = new HashMap<String,Object>();
                p.put("ins_policy_insured_person_string", ins_policy_insured_person_string);
                p.put("ins_policy_holder_string",ins_policy_holder_string );
                p.put("insInformationString",insInformationString );
                p.put("insertString", insertString);
                msg = insPolicyInsuredPersonFeign.insertImportPolicyList(p);
            }else{
                msg.put("code","0000");
            }
        } else {
            msg.put("code","0000");
        }
    }catch (CheckException e){
       String message = e.getMessage();
       msg.put("code","500");
       msg.put("error",message);
       logger.error("导入投保单异常|异常：{}", e);
    } catch (Exception e) {
        logger.error("导入投保单异常|异常：{}", e);
        msg.put("code","400");
    }
    return msg;
}

   /**
    *@描述 投保单承保清单导出
    *@创建人 qin lina
    *@创建时间 2020/9/3
    */
     @RequestMapping("/export")
    public void exportInsurancePolicy(HttpServletRequest request, HttpServletResponse response) {
       try {
           DecimalFormat df = new DecimalFormat("0.00");
    	   Map<String,Object> msg = new HashMap<String,Object>();
            Map<String, Object> params = getParams(request);
            String type = request.getParameter("type");
            params.put("type", type);
            String myDeptNo = "";
            if(params.get("myDeptNo") != null){
                myDeptNo = String.valueOf(params.get("myDeptNo"));
            }
            String myAllOrgIds = salesOrgInfoClient.findEmployeeAllOrgs(myDeptNo);
            params.put("myAllOrgIds", myAllOrgIds);
            List<Map<String, Object>> insurancePolicyAll = insPolicyInsuredPersonFeign.findInsurancePolicyAll(params);
            logger.info(insurancePolicyAll.toString());
            List<Object[]> dataList = Lists.newArrayListWithExpectedSize(insurancePolicyAll.size());
            //月数据导出
           String title = (Constants.POLICY_TYPT_2.equals(type)?"承保清单导出":"投保单导出")+DateTimeUtil.getDateNormalString(new Date());
            Object[] objs = null;
            Map<String, Object> map = null;
            String[] rowsName = new String[]{"投保公司", "保单号", "投保单号", "投保日期", "承保日期", "生效日期",
                    "产品名称", "保额", "保费", "缴费方式", "缴费年期", "保险期间", "代理人工号", "投保人姓名",
            "投保人性别","投保人证件类型","投保人证件号码","投保人移动电话","投保人出生日期","投保人常驻地址","被保人与投保人关系",
                    "被保人姓名","被保人证件类型","被保人证件号码","被投保人移动电话","被保人性别","被保人出生日期","被保人常驻地址"};
            // 查出字典值
            List<Map<String, Object>> payTypeMap = systemDictFeignClient.findDictNameByDictType(Constants.DICT_TYPE_JFTYPE);
           List<Map<String, Object>> sexMap = systemDictFeignClient.findDictNameByDictType(Constants.DICT_TYPE_SEX);
           List<Map<String, Object>> cardMap = systemDictFeignClient.findDictNameByDictType(Constants.DICT_TYPE_CARD);
           List<Map<String, Object>> relationMap = systemDictFeignClient.findDictNameByDictType(Constants.DICT_TYPE_RELATIONSHIP);
           for (int i = 0; i < insurancePolicyAll.size(); i++) {
                map = insurancePolicyAll.get(i);
                objs = new Object[rowsName.length];
                objs[0] = map.get("INSURANCE_COMPANY_NAME");//投保公司
                objs[1] = Constants.POLICY_TYPT_2.equals(type)?map.get("POLICY_ID"):""; //保单号
                objs[2] = Constants.POLICY_TYPT_2.equals(type)?map.get("CORRESPONDING"):map.get("POLICY_ID"); //投保单号
                objs[3] = map.get("INSURE");  //投保日期
                objs[4] = map.get("UNDERWRITING_DATA");  //承保日期
                objs[5] = map.get("TAKE_EFFECT_DATA");  //生效日期
                objs[6] = map.get("PRODUCT_NAME");    //产品名称
                objs[7] = map.get("INSURED_AMOUNT");   //保额
                objs[8] = map.get("PREMIUM"); //保费
                String payTpye = map.get("PAYMENT_METHOD").toString();
                 if (StrUtil.isNotBlank(payTpye)) {
                    for (Map map1: payTypeMap) {
                       if (payTpye.equals(map1.get("dict_code"))){
                          payTpye = map1.get("dict_name").toString();
                          break;
                        }
                    }
                }

                objs[9] = payTpye;   //缴费方式
                objs[10] = map.get("PAYMENT");  //缴费年期
                objs[11] = map.get("PERIOD_OF_INSURANCE");    //保险期间
                objs[12] = map.get("EMPLOYEE_NO");   //代理人工号
                objs[13] = map.get("NAME1");   //投保人姓名
                String sex1 = map.get("SEX1").toString();
                if (StrUtil.isNotBlank(sex1)) {
                    for (Map map1: sexMap) {
                       if (sex1.equals(map1.get("dict_code"))){
                          sex1 = map1.get("dict_name").toString();
                          break;
                        }
                    }
                }

                 objs[14] = sex1;   //投保人性别
                String cardType1 = map.get("CARD_TYPE1").toString();
                if (StrUtil.isNotBlank(cardType1)) {
                    for (Map map1: cardMap) {
                       if (cardType1.equals(map1.get("dict_code"))){
                          cardType1 = map1.get("dict_name").toString();
                          break;
                        }
                    }
                }
                 objs[15] = cardType1;   //投保人证件类型
                 objs[16] = map.get("CARD_NO1");   //投保人证件号码
                 objs[17] = map.get("MOBILE1");   //投保人移动电话
                 objs[18] = map.get("BIRTHDAY1");   //投保人出生日期
                 objs[19] = map.get("HOME_ADDRESS1");   //投保人常驻地址
                String relation = map.get("RELATIONSHIP").toString();
                if (StrUtil.isNotBlank(relation)) {
                    for (Map map1: relationMap) {
                       if (relation.equals(map1.get("dict_code"))){
                          relation = map1.get("dict_name").toString();
                          break;
                        }
                    }
                }
                 objs[20] = relation;   //被保人与投保人关系
                 objs[21] = map.get("NAME2");   //被保人姓名
                String cardType2 = map.get("CARD_TYPE2").toString();
                if (StrUtil.isNotBlank(cardType2)) {
                    for (Map map1: cardMap) {
                       if (cardType2.equals(map1.get("dict_code"))){
                          cardType2 = map1.get("dict_name").toString();
                          break;
                        }
                    }
                }
                 objs[22] = cardType2;   //被保人证件类型
                 objs[23] = map.get("CARD_NO2");   //被保人证件号码
                 objs[24] = map.get("MOBILE2");   //被投保人移动电话
                String sex2 = map.get("SEX2").toString();
                if (StrUtil.isNotBlank(sex2)) {
                    for (Map map1: sexMap) {
                       if (sex2.equals(map1.get("dict_code"))){
                          sex2 = map1.get("dict_name").toString();
                          break;
                        }
                    }
                }
                 objs[25] = sex2;   //被保人性别
                 objs[26] = map.get("BIRTHDAY2");   //被保人出生日期
                 objs[27] = map.get("HOME_ADDRESS2");   //被保人常驻地址
                dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            ex.exportBigData(response);
       } catch (Exception e) {
   		logger.error("导出异常=============：",e);
       }
    }



    private String convertState(String state) {
        switch (state) {
            case "1":
                state = "待审核";
                break;
            case "2":
                state = "审核成功";
                break;
            case "3":
                state = "审核失败";
                break;
            case "4":
                state = "待移交";
                break;
            case "5":
                state = "已移交";
                break;
            case "6":
                state = "待发放";
                break;
            case "7":
                state = "已发放";
                break;
            case "8":
                state = "已回复";
                break;
            case "9":
                state = "已回销";
                break;
            case "10":
                state = "异常回销";
                break;
            case "11":
                state = "已录入";
                break;
            case "0000":
                state = "暂存";
                break;
            default:
                break;
        }
        return state;
    }

    @RequestMapping("/do_become_insurance_policy")
    @ResponseBody
    public DataMsg doBecomeInsurancePolicy(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String type = request.getParameter("type");
            if (StringUtil.isNotBlank(type)) {
                params.put("type", request.getParameter("type"));
            }
            String insState = request.getParameter("insState");
            params.put("insState", insState);
            String do_become_insurance_policy = request.getParameter("do_become_insurance_policy");
            params.put("do_become_insurance_policy",do_become_insurance_policy);
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign",principalDeputySign);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    @RequestMapping("select_search_params")
    @ResponseBody
    public ReturnMsgData selectSearchParams() {
        ReturnMsgData msg = new ReturnMsgData();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            Map<String, Object> data = insPolicyInsuredPersonFeign.selectSearchParams(params);
            msg.setData(data);
            msg.setReturnCode("200");
            logger.info("返回数据：" + data);
        } catch (RuntimeException e) {
            msg.setReturnCode("400");
            e.printStackTrace();
            logger.info("***selectSearchParams***异常");
        }
        return msg;
    }

    /**
     * 保单审核
     */
    @RequestMapping("/insurance_policy_eaxmine")
    @ResponseBody
    public Map<String, Object> insurancePolicyEaxmine(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> msg = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
//
        Object employeeId = employee.getEmployeeId();
        params.clear();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());

        try {
            params.put("employeeId", employeeId);
            params.put("time", format);
            String sta = request.getParameter("sta");//审核是否通过
            if ("1".equals(sta)) {
                params.put("insState", Constants.INSURANCE_POLICY_STATUS_1002);//通过
            } else {
                params.put("insState", Constants.INSURANCE_POLICY_STATUS_1003);//不通过
            }

            String exampleInsName1 = request.getParameter("exampleInsName1");//保单号
            String exampleInsName2 = request.getParameter("exampleInsName2");//承保日期
            String exampleInsName3 = request.getParameter("exampleInsName3");//生效日期

            String audit_opinions = request.getParameter("audit_opinions");//生效日期
            params.put("audit_opinions", audit_opinions);
            params.put("exampleInsName1", exampleInsName1);
            params.put("exampleInsName2", exampleInsName2);
            params.put("exampleInsName3", exampleInsName3);
            msg = insPolicyInsuredPersonFeign.insurancePolicyEaxmine(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 保单新增，修改，投保单新增修改
     */
    @RequestMapping("/add_insurance_policy")
    @ResponseBody
    public Map<String, Object> AddInsurancePolicy(HttpServletRequest request) {
        Map<String, Object> retParams = new HashMap<>();
        try {
        //从shiro的session中取Employee
        Subject subject = SecurityUtils.getSubject();

        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();

        Object employeeId = employee.getEmployeeId();
            retParams.clear();
            //获取当前时间
        SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = fmat.format(new Date());
        Map<String, Object> ins_policy_insured_person = new HashMap<String, Object>();//投保单/保单被保人
        Map<String, Object> ins_policy_holder = new HashMap<String, Object>();//投保人
      //  Map<String, Object> ins_policy_proposal = new HashMap<String, Object>();//代理人以及保险公司

        Map<String, Object> setIns_policy_insured_examine = new HashMap<String, Object>();//投保人
        List<Map<String, Object>> ins_policy_profits_person_list = new ArrayList<>();
        List<Map<String, Object>> ins_policy_att_list = new ArrayList<>();
        List<Map<String, Object>> insInformationList = new ArrayList<Map<String, Object>>();
        Map<String,Object> policyMap = new HashMap<String,Object>();
        Map<String, Object> paramsMap = new HashMap<>();
        String changeImageFlag = request.getParameter("changeImageFlag");
        //防止修改时，未删除/新增影像文件导致删除后新增导致投保单附件无限增长
        policyMap.put("changeImageFlag", changeImageFlag);
        //*************投保单基本信息***************
        String exampleInputName1 = "";
        String exampleInsName1 = request.getParameter("exampleInsName1");
        if (exampleInsName1 != null) {
            String corresponding = request.getParameter("CORRESPONDING");
            ins_policy_insured_person.put("corresponding", corresponding);
            exampleInputName1 = request.getParameter("exampleInsName1");//保单号
            String exampleInsName2 = request.getParameter("exampleInsName2");//承保日期
            String exampleInsName3 = request.getParameter("exampleInsName3");//生效日期
            ins_policy_insured_person.put("underwritingData", exampleInsName2);
            ins_policy_insured_person.put("takeEffectData", exampleInsName3);
            ins_policy_holder.put("type", Constants.POLICY_TYPT_2);//类型（1-投保单、2-保单）
            ins_policy_insured_person.put("type", Constants.POLICY_TYPT_2);//保单状态
            ins_policy_insured_person.put("insState", Constants.INSURANCE_POLICY_STATUS_1001);//保单状态


            policyMap.put("policyId", request.getParameter("exampleInputName1"));
            policyMap.put("state", Constants.POLICY_STATUS_11);
            policyMap.put("updateBy", employeeId);
            policyMap.put("updateTime", now);
        } else {
            exampleInputName1 = request.getParameter("exampleInputName1");//投保单号
            ins_policy_holder.put("type", Constants.POLICY_TYPT_1);//类型（1-投保单、2-保单）
            ins_policy_insured_person.put("type", Constants.POLICY_TYPT_1);//投保单状态
            String commitType = request.getParameter("commitType");//提交类型   1：提交 2：暂存
            String state =  "1".equals(commitType)?  Constants.POLICY_STATUS_1:  Constants.POLICY_STATUS_0000;
            ins_policy_insured_person.put("state",state);//投保单状态
        }
        final String policyId = StringUtil.trim(exampleInputName1);//支持lambda 最终值协议
        setIns_policy_insured_examine.put("policyId",policyId);
        String insuranceCompany = request.getParameter("insuranceCompany");//保险公司
        String exampleInputName3 = request.getParameter("exampleInputName3");//总保费
        String exampleInputName4 = request.getParameter("exampleInputName4");//投保日期
        String card_no = request.getParameter("card_no");//证件号码
        String card_type = request.getParameter("card_type");//证件类型
        String policy_holder_name = request.getParameter("policy_holder_name");// 投保人姓名
        String policy_holder_tel = request.getParameter("policy_holder_tel");//移动电话
        String policy_holder_sex = request.getParameter("policy_holder_sex");//性别
        String policy_holder_birthday = request.getParameter("policy_holder_birthday");//投保人出生日期
        String policy_holder_annual_income = request.getParameter("policy_holder_annual_income");// 年收入
        String policy_holder_permanent_address = request.getParameter("policy_holder_permanent_address");// 投保人常驻地址

        String pay_type = request.getParameter("pay_type");// 付款方式
        String policy_holder_opener_name = request.getParameter("policy_holder_opener_name");//开户人姓名
        String policy_holder_bank_name = request.getParameter("policy_holder_bank_name");//银行名称
        String policy_holder_bank_account = request.getParameter("policy_holder_bank_account");//银行账号
        String policy_holder_education = request.getParameter("policy_holder_education");//学历
        String policy_holder_marital_status = request.getParameter("policy_holder_marital_status");//婚姻状态
        String policy_holder_company_address = request.getParameter("policy_holder_company_address");//投保人公司地址
        String policy_holder_home_phone = request.getParameter("policy_holder_home_phone");//办公/家庭电话
        String policy_holder_email = request.getParameter("policy_holder_email");//电子邮箱
        String policy_holder_zip_code = request.getParameter("policy_holder_zip_code");//家庭住址邮编
        String policy_holder_permanent_address_add = request.getParameter("policy_holder_permanent_address_add");//户籍地址
        String policy_holder_liabilities = request.getParameter("policy_holder_liabilities");//负债和贷款（万元）
        String policy_holder_occupation_code = request.getParameter("policy_holder_occupation_code");//投保人执业编号
        String policy_holder_occupation = request.getParameter("policy_holder_occupation");//投保人职业
        //**************被投保人资料*******************
        String insured_relationship = request.getParameter("insured_relationship");//与投保人关系--
        String insured_card_no = request.getParameter("insured_card_no");//证件号码
        String insured_card_type = request.getParameter("insured_card_type");//证件类型
        String insured_name = request.getParameter("insured_name");//被保人姓名
        String insured_tel = request.getParameter("insured_tel");//移动电话
        String insured_sex = request.getParameter("insured_sex");//性别
        String insured_birthday = request.getParameter("insured_birthday");//投保人出生日期
        String insured_annual_income = request.getParameter("insured_annual_income");//年收入
        String insured_permanent_address = request.getParameter("insured_permanent_address");//投保人常住住址
        String multiple_insurance_coverage = request.getParameter("multiple_insurance_coverage");//是否多次投保
        String health_report = request.getParameter("health_report");//是否有健康告知
        String insured_education = request.getParameter("insured_education");//学历
        String insured_marital_status = request.getParameter("insured_marital_status");//婚姻状态
        String insured_company_address = request.getParameter("insured_company_address");//被保人公司地址
        String insured_home_phone = request.getParameter("insured_home_phone");//办公/家庭电话
        String insured_email = request.getParameter("insured_email");//电子邮箱
        String insured_zip_code = request.getParameter("insured_zip_code");//家庭住址邮编
        String insured_permanent_address_add = request.getParameter("insured_permanent_address_add");//户籍地址
        String insured_liabilities = request.getParameter("insured_liabilities");//负债和贷款
        String insured_occupation_code = request.getParameter("insured_occupation_code");//投保人执业编号
        String insured_occupation = request.getParameter("insured_occupation");//投保人职业
        //************代理人信息****************
        String channels_of_cooperation = request.getParameter("channels_of_cooperation");//合作渠道代码
        String emp_no = request.getParameter("emp_no");//人员工号
        String emp_name = request.getParameter("emp_name");//代理人姓名
        String order_taking_mechanism = request.getParameter("order_taking_mechanism");//接单机构
        String sales_team = request.getParameter("sales_team");//销售团队
        //**************投保单影像信息******************
        String[] fileNames = request.getParameterValues("fileName");//文件名称
        String[] fastPath = request.getParameterValues("fastPath");//文件名称
        String[] ipaIds = request.getParameterValues("ipaId");
        //***********险种信息******************
        String[] a1 = request.getParameterValues("a1");//产品代码
        String[] a2 = request.getParameterValues("a2");//产品名称
        String[] a3 = request.getParameterValues("a3");//保额
        String[] a4 = request.getParameterValues("a4");//保费
       /* String[] a5 = request.getParameterValues("a5");//份数*/
        String[] a6 = request.getParameterValues("a6");//缴费方式
        String[] a7 = request.getParameterValues("a7");//缴费 年期/年龄标志
        String[] a8 = request.getParameterValues("a8");//缴费 年期/年龄
        String[] a9 = request.getParameterValues("a9");//保险期间标志
        String[] a10 = request.getParameterValues("a10");//保险期间
        String[] a11 = request.getParameterValues("a11");//主附险标志
        String[] a12 = request.getParameterValues("a12");//险种类别
        //判断保单产品有且只能有一个主险
        List<String> strings1 = Arrays.asList(a11);
        int frequency = Collections.frequency(strings1, "0");
        if (1 != frequency){
            retParams.put("code","0001");
            retParams.put("error","险种信息中必须有且只能有一个主险产品");
            return retParams;
        }
        //*******险种受益人******************
        String[] b1s = request.getParameterValues("b1");//险种受益人
        String[] b2s = request.getParameterValues("b2");//证件类型
        String[] b3s = request.getParameterValues("b3");//证件号码

        String[] b4s = request.getParameterValues("b4");//性别
        String[] b5s = request.getParameterValues("b5");//出生日期
        String[] b6s = request.getParameterValues("b6");//受益顺序
        String[] b7s = request.getParameterValues("b7");//受益比例
        String[] b8s = request.getParameterValues("b8");//与被保人关系
        String[] profitsPersonIds = request.getParameterValues("profitsPersonId");//ID
            String temp = "";
            if (b3s != null) {
                List<String> list =Arrays.asList(b3s);
            for (int i = 0; i < list.size() - 1; i++)
            {
                temp = list.get(i);
                for (int j = i + 1; j < list.size(); j++)
                {
                    if (temp.equals(list.get(j)))
                    {
                       throw  new RuntimeException("CardNoidenticalError:"+list.get(j));
                    }
                }
            }
            }

        ins_policy_insured_person.put("policyId", exampleInputName1);//投保单单号
        ins_policy_insured_person.put("relationship", insured_relationship);//与被保人关系
        ins_policy_insured_person.put("cardType", insured_card_type);//证件类型
        ins_policy_insured_person.put("cardNo", insured_card_no);//证件号
        ins_policy_insured_person.put("name", insured_name);//NAME
        ins_policy_insured_person.put("mobile", insured_tel);//移动电话
        ins_policy_insured_person.put("sex", insured_sex);//性别
        ins_policy_insured_person.put("birthday", insured_birthday);//出生日期
        ins_policy_insured_person.put("annualIncome", insured_annual_income);//年收入（万元）
        ins_policy_insured_person.put("homeAddress", insured_permanent_address);//家庭住址
        ins_policy_insured_person.put("isMultipleInsure", multiple_insurance_coverage);//是否多次投保
        ins_policy_insured_person.put("isInformHealth", health_report);//是否健康告知
        ins_policy_insured_person.put("eduBackground", insured_education);//学历
        ins_policy_insured_person.put("maritalStatus", insured_marital_status);//婚姻状态
        ins_policy_insured_person.put("companyAddress", insured_company_address);//公司地址
        ins_policy_insured_person.put("telephone", insured_home_phone);//办公/家庭电话
        ins_policy_insured_person.put("email", insured_email);//电子邮箱
        ins_policy_insured_person.put("homePostalCode", insured_zip_code);//家庭地址邮编
        ins_policy_insured_person.put("domicileAddress", insured_permanent_address_add);//户籍地址
        ins_policy_insured_person.put("debts", insured_liabilities);//负债（万元）
        ins_policy_insured_person.put("occupationCode", insured_occupation_code);//职业编号
        ins_policy_insured_person.put("occupation", insured_occupation);//职业
        ins_policy_insured_person.put("createTime", now);//创建时间
        ins_policy_insured_person.put("createBy", employeeId);//创建人
        ins_policy_insured_person.put("insuranceCompanyId", insuranceCompany);//保险公司ID（insurance_company_org表主键）
        ins_policy_insured_person.put("totalPremium", exampleInputName3);//总保费（元）
        ins_policy_insured_person.put("proposeDate", exampleInputName4);//投保日期





        ins_policy_holder.put("policyId", exampleInputName1);//投保单/保单编号
        ins_policy_holder.put("cardType", card_type);////证件类型
        ins_policy_holder.put("cardNo", card_no);//证件号
        ins_policy_holder.put("name", policy_holder_name);//姓名
        ins_policy_holder.put("mobile", policy_holder_tel);//移动电话
        ins_policy_holder.put("sex", policy_holder_sex);//性别
        ins_policy_holder.put("birthday", policy_holder_birthday);//出生日期
        ins_policy_holder.put("annualIncome", policy_holder_annual_income);//年收入（万元）
        ins_policy_holder.put("homeAddress", policy_holder_permanent_address);//家庭住址
        ins_policy_holder.put("payType", pay_type);//付款方式（字典）
        ins_policy_holder.put("accountHolder", policy_holder_opener_name);//开户人姓名
        ins_policy_holder.put("bankName", policy_holder_bank_name);//银行名称
        ins_policy_holder.put("bankAccount", policy_holder_bank_account);//银行账号
        ins_policy_holder.put("eduBackground", policy_holder_education);//学历
        ins_policy_holder.put("maritalStatus", policy_holder_marital_status);//婚姻状态
        ins_policy_holder.put("companyAddress", policy_holder_company_address);//公司地址
        ins_policy_holder.put("telephone", policy_holder_home_phone);//办公/家庭电话
        ins_policy_holder.put("email", policy_holder_email);////电子邮箱
        ins_policy_holder.put("homePostalCode", policy_holder_zip_code);//家庭地址邮编
        ins_policy_holder.put("domicileAddress", policy_holder_permanent_address_add);//户籍地址
        ins_policy_holder.put("debts", policy_holder_liabilities);//负债（万元）
        ins_policy_holder.put("occupationCode", policy_holder_occupation_code);//职业编号
        ins_policy_holder.put("occupation", policy_holder_occupation);//职业






        Map<String, Object> pipParams = new HashMap<>();
        if(StringUtil.isBlank(emp_no)){
            throw new RuntimeException("EmpNoIsNullErroe:"+policyId);
        }else{
            pipParams.put("insuranceSalerNo", emp_no);
        }
        List<InsuranceSalesInfo> insuranceSalesInfos = insuranceSalesInfoClient.insuranceSalesList(pipParams);
        if(CollectionUtils.isEmpty(insuranceSalesInfos)){
            throw new RuntimeException("InsuranceSalesInfoIsNullErroe:"+policyId);
        }else{
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfos.get(0);
            ins_policy_insured_person.put("teamId", insuranceSalesInfo.getSalesTeamId());
            ins_policy_insured_person.put("orgId",insuranceSalesInfo.getSalesOrgId() );
            ins_policy_insured_person.put("agentId",insuranceSalesInfo.getInsuranceSalesId());
            ins_policy_insured_person.put("salesGradeId",insuranceSalesInfo.getSalesGradeId());

        }

        if (fileNames != null) {
            final List size = new ArrayList();
            List<String> strings = Arrays.asList(fileNames);
            strings.forEach(fname -> {
                Map<String, Object> ins_policy_att = new HashMap<String, Object>();
                ins_policy_att.put("policyId", policyId);//投保单/保单编号
                ins_policy_att.put("createBy", employeeId);//创建人
                ins_policy_att.put("createTime", now);//创建时间
                if (exampleInsName1 != null) {
                    ins_policy_att.put("type", Constants.POLICY_TYPT_2);//类型（1-投保单、2-保单）
                } else {
                    ins_policy_att.put("type", Constants.POLICY_TYPT_1);//类型（1-投保单、2-保单）
                }
                ins_policy_att.put("attName", fname);//附件名称
                ins_policy_att.put("attAdd", fastPath[size.size()]);//附件地址（fastDFS）
                if(ipaIds != null && size.size()<ipaIds.length){
                        ins_policy_att.put("ipaId",ipaIds[size.size()]);//id
                }
                size.add("1");
                ins_policy_att_list.add(ins_policy_att);
            });
        }
        //校验产品是否设置基础佣金率
        if (a1 != null){
            List<String> a1List = Arrays.asList(a1);
            a1List.forEach(prodoctCode -> {
                Map<String, Object> pare = new HashMap<String, Object>();
                pare.put("product_code", prodoctCode);
                pare.put("orgid", order_taking_mechanism);
                Map<String, Object> rate = partnershipCommissionClient.selectCommissionRate(pare);
                if (ObjectUtil.isEmpty(rate.get("CommissionRate"))) {
                    throw new RuntimeException("CommissionRateNotFind:" + policyId);
                }
            });
        }
        if(a2 != null) {
            List<String> a2List = Arrays.asList(a2);

            a2List.forEach(productId -> {
                Map<String, Object> ins_information = new HashMap<String, Object>();
                ins_information.put("productId", productId);//产品表主键
              //  if ("2".equals(ins_policy_insured_person.get("type"))) {
                    Map<String, Object> par = new HashMap<String, Object>();
                    par.put("orgid", order_taking_mechanism);
                    par.put("products_ratio_id", productId);
                    Map<String, Object> proId = insPolicyInsuredPersonFeign.selectProtocolId(par);
                    if ("".equals(proId.get("PROTOCOL_ID"))) {
                        throw new RuntimeException("protocolIdNotFind:" + policyId);
                    }
                    ins_information.put("protocolId", proId.get("PROTOCOL_ID"));
              //  }
                ins_information.put("insuredAmount", a3[insInformationList.size()]);//保额
               // ins_information.put("size", a5[insInformationList.size()]);//保额
                ins_information.put("size", 1);//份数默认1
                ins_information.put("paymentPeriodSign", a7[insInformationList.size()]);//缴费 年期/年龄 标识（主键待定）
                ins_information.put("periodOf", a9[insInformationList.size()]);//保险期间标识（待定主键）
                ins_information.put("principalDeputySign", a11[insInformationList.size()]);//主附险标识
                ins_information.put("insuranceType", a12[insInformationList.size()]);//险种类别
                ins_information.put("premium", a4[insInformationList.size()]);////保费
                ins_information.put("paymentMethod", a6[insInformationList.size()]);//缴费方式
                ins_information.put("payment", a8[insInformationList.size()]);//缴费
                ins_information.put("periodOfInsurance", a10[insInformationList.size()]);//保险期间
                ins_information.put("compositeCode", a1[insInformationList.size()]);//组合代码
                ins_information.put("policyId", policyId);//投保单主键
                insInformationList.add(ins_information);
            });
        }
        if (b1s != null) {

            List<String> b1sList = Arrays.asList(b1s);

            for (int i = 0; i < b1sList.size(); i++) {
                Map<String, Object> ins_policy_profits_person = new HashMap<String, Object>();
                ins_policy_profits_person.put("createTime", now);//创建时间
                ins_policy_profits_person.put("createBy", employeeId);//创建人
                ins_policy_profits_person.put("name", b1sList.get(i));//受益人姓名
                /*if(profitsPersonIds != null){
                    ins_policy_profits_person.put("profitsPersonId", Arrays.asList(profitsPersonIds).get(i));
                }*/
                if (b2s != null && b2s.length > i)
                    ins_policy_profits_person.put("cardType", Arrays.asList(b2s).get(i));//受益人证件类型
                else
                    ins_policy_profits_person.put("cardType", "");//受益人证件类型
                if (b3s != null && b3s.length > i)
                    ins_policy_profits_person.put("cardNo", Arrays.asList(b3s).get(i));//受益人证件号
                else
                    ins_policy_profits_person.put("cardNo", "-");//受益人证件号
                if (b4s != null && b4s.length > i)
                    ins_policy_profits_person.put("sex", Arrays.asList(b4s).get(i));//受益人性别
                else
                    ins_policy_profits_person.put("sex", "-");//受益人性别
                if (b5s != null && b5s.length > i) {
                    String birthday = Arrays.asList(b5s).get(i);
                    birthday = "".equals(birthday)?null:birthday;
                    ins_policy_profits_person.put("birthday", birthday);//受益人出生日期
                }else
                    ins_policy_profits_person.put("birthday", "-");//受益人出生日期
                if (b6s != null && b6s.length > i)
                    ins_policy_profits_person.put("level", Arrays.asList(b6s).get(i));//受益人基本-第一受益人-第二受益人等
                else
                    ins_policy_profits_person.put("level", "-");//受益人基本-第一受益人-第二受益人等
                if (b7s != null && b7s.length > i)
                    ins_policy_profits_person.put("benefitRatio", Arrays.asList(b7s).get(i));//受益比例
                else
                    ins_policy_profits_person.put("benefitRatio", "-");//受益比例
                if (b8s != null && b8s.length > i)
                    ins_policy_profits_person.put("relationship", Arrays.asList(b8s).get(i));//与被保护人的关系
                else
                    ins_policy_profits_person.put("relationship", "-");//与被保护人的关系
                ins_policy_profits_person.put("policyId", exampleInputName1);//投保单主键


                ins_policy_profits_person_list.add(ins_policy_profits_person);
            }

        }
        Params params = new Params();
        params.setIns_policy_insured_examine(setIns_policy_insured_examine);
        params.setMap(policyMap);
        params.setIns_information(insInformationList);
        params.setIns_policy_att_list(ins_policy_att_list);
        params.setIns_policy_holder(ins_policy_holder);
        params.setIns_policy_insured_person(ins_policy_insured_person);
        params.setIns_policy_profits_person_list(ins_policy_profits_person_list);
      //  params.setIns_policy_proposal(ins_policy_proposal);
        ins_policy_insured_person.put("insure", exampleInputName4);
        //   String a = insPolicyInsuredPersonFeign.addInsPol();



            Map<String, Object> insParams = new HashMap<String, Object>();
            insParams.put("id", exampleInputName1);
            if("update".equals(request.getParameter("updateType"))){
                retParams = insPolicyInsuredPersonFeign.updateInsurancePolicy(params);
                String code = retParams.get("code").toString();
            }else{
                //根据保单号查询是否存在 不存在
             //   InsPolicyInsuredPersonEntity insPolicyInsuredPersonEntity = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(insParams);
                retParams = insPolicyInsuredPersonFeign.addInsurancePolicy(params);
                String code = retParams.get("code").toString();
            }

        }catch (RuntimeException e){
            String message = e.getMessage();
            String[] split = message.split(":");
            String s = split[0];

            if("protocolIdNotFind".equals(s)){
                retParams.put("code","0001");
                retParams.put("error","未查询到协议ID："+split[1]);
            }else if("EmpNoIsNullErroe".equals(split[0])){
                retParams.put("code","0001");
                retParams.put("error","员工编号不能为空："+split[1]);
            }else if("CardNoidenticalError".equals(s)){
                retParams.put("code","0001");
                retParams.put("error","受益人存在重复证件号,请核对后再次提交："+split[1]);
            }else if("CommissionRateNotFind".equals(s)){
                retParams.put("code","0001");
                retParams.put("error","未设置基础佣金率"+split[1]);
            }else {
                retParams.put("code","0001");
                retParams.put("error",e.getMessage());
            }
            logger.error("新增投保单运行时异常：",e);
        }
        catch (Exception e) {
            retParams.put("code","400");
            logger.error("新增投保单异常：",e);
        }
        return retParams;
    }
/**
 * 上传文件
 * */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> dataUpload(MultipartFile file, HttpServletRequest request, HttpSession session) throws Exception {
        Map<String, Object> para = new HashMap<String, Object>();
        try {
            if (!file.isEmpty()) {
                String realPath = request.getSession().getServletContext().getRealPath("\\common");
                //修改文件名称
                int index = (file).getOriginalFilename().indexOf('.');
                String prefix = (file).getOriginalFilename().substring(index);
                //给上传的文件一个新的名称
                String fn = DateTimeUtil.getNowTimeShortString() + prefix;
               /* // 设置存放文件的路径
                String path = realPath + "\\" + fileName;
                // 转存文件到指定的路径
                file.transferTo(new File(path));*/
                String fileName = (file).getOriginalFilename();
                StorePath storePath = fdfsClient.uploadFile(file);
                String fastPath = storePath.getFullPath();
                para.clear();
                para.put("newFileName", fn);
                para.put("fastPath", fastPath);
                para.put("code", "200");
            }
        } catch (Exception e) {
            para.put("code", "400");
            e.printStackTrace();
        }
        return para;
    }

    @RequestMapping("/submit_examine")
    @ResponseBody
    public   Map<String,Object> submitExamine(HttpServletRequest httpServletRequest) {

        Map<String,Object> msgData = new HashMap<String,Object>();
        List<Map<String, Object>> listParams = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> params = getParams(httpServletRequest);
            String ex_exampleInputName1 = httpServletRequest.getParameter("ex_exampleInputName1");//保险公司
            if (ex_exampleInputName1 != null && !"".equals(ex_exampleInputName1)) {
                params.put("policyId", ex_exampleInputName1);
            }
            String ex_exampleInputName2 = httpServletRequest.getParameter("ex_exampleInputName2");//保险公司
            if (ex_exampleInputName2 != null && !"".equals(ex_exampleInputName2)) {
                params.put("insuranceCompanyId", ex_exampleInputName2);
            }
            String ex_exampleInputName3 = httpServletRequest.getParameter("ex_exampleInputName3");//产品名称
            if (ex_exampleInputName3 != null && !"".equals(ex_exampleInputName3)) {
                params.put("productName", ex_exampleInputName3);
            }
            String ex_exampleInputName4 = httpServletRequest.getParameter("ex_exampleInputName4");//产品代码
            if (ex_exampleInputName4 != null && !"".equals(ex_exampleInputName4)) {
                params.put("productCode", ex_exampleInputName4);
            }
            String ex_exampleInputName5 = httpServletRequest.getParameter("ex_exampleInputName5");//组织机构
            if (ex_exampleInputName5 != null && !"".equals(ex_exampleInputName5)) {
                params.put("salesOrgId", ex_exampleInputName5);
            }
            String ex_exampleInputName6 = httpServletRequest.getParameter("ex_exampleInputName6");//营销团队
            if (ex_exampleInputName6 != null && !"".equals(ex_exampleInputName6)) {
                params.put("salesTeamId", ex_exampleInputName6);
            }
            String ex_exampleInputName7 = httpServletRequest.getParameter("ex_exampleInputName7");//投保人
            if (ex_exampleInputName7 != null && !"".equals(ex_exampleInputName7)) {
                params.put("iphName", ex_exampleInputName7);
            }
            String ex_exampleInputName8 = httpServletRequest.getParameter("ex_exampleInputName8");//被保人
            if (ex_exampleInputName8 != null && !"".equals(ex_exampleInputName8)) {
                params.put("pipName", ex_exampleInputName8);
            }
            String ex_exampleInputName9 = httpServletRequest.getParameter("ex_exampleInputName9");//员工工号
            if (ex_exampleInputName9 != null && !"".equals(ex_exampleInputName9)) {
                params.put("empNo", ex_exampleInputName9);
            }
            String ex_exampleInputName10 = httpServletRequest.getParameter("ex_exampleInputName10");//状态
            if (ex_exampleInputName10 != null && !"".equals(ex_exampleInputName10)) {
                params.put("state", ex_exampleInputName10);
            }
            String state = httpServletRequest.getParameter("state");//状态
            if (state != null && !"".equals(state)) {
                params.put("state", state);
            }
            String ex_exampleInputName11 = httpServletRequest.getParameter("ex_exampleInputName11");//投保日期
            if (ex_exampleInputName11 != null && !"".equals(ex_exampleInputName11)) {
                params.put("insure", ex_exampleInputName11);
            }

            String ex_exampleInputName20 = httpServletRequest.getParameter("ex_exampleInputName20");// 投保日期-开始
            if (StringUtils.isNotBlank(ex_exampleInputName20)) {
                params.put("startInsure", ex_exampleInputName20);
            }
            String ex_exampleInputName21 = httpServletRequest.getParameter("ex_exampleInputName21");// 投保日期-结束
            if (StringUtils.isNotBlank(ex_exampleInputName21)) {
                params.put("endInsure", ex_exampleInputName21);
            }
            msgData = insPolicyInsuredPersonFeign.submitExamine(params);

        } catch (RuntimeException e) {
            msgData.put("code", "200");
            e.printStackTrace();
        }
        return msgData;
    }

    public Map<String, Object> getParams(HttpServletRequest httpServletRequest) {
        Map<String,Object> paras = new HashMap<String,Object>();

        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
        employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        //通过员工编号查询销售人员id
        Map<String, Object> params = new HashMap<String, Object>();
        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
//        params.clear();
//            params.put("insuranceSalesId",employeeId);
//        params.put("employeeId",employeeId);

        //数据权限相关查询条件
        params.put("isAdmin", employee.getEmployeeNo());
        params.put("myDeptNo", employee.getDeptNo());
        params.put("employeeId",employee.getEmployeeId());

        params.put("now", format);
        params.put("admin", employeeNo);
        String examine_exampleInputName1 = httpServletRequest.getParameter("examine_exampleInputName1");//投保单号
        if (examine_exampleInputName1 != null) {
            params.put("examine_exampleInputName1", examine_exampleInputName1);
        }
        String examine_exampleInputName2 = httpServletRequest.getParameter("examine_exampleInputName2");//保险公司
        if (examine_exampleInputName2 != null) {
            params.put("examine_exampleInputName2", examine_exampleInputName2);
        }
        String examine_exampleInputName3 = httpServletRequest.getParameter("examine_exampleInputName3");//产品名称
        if (examine_exampleInputName3 != null) {
            params.put("examine_exampleInputName3", examine_exampleInputName3);
        }
        String examine_exampleInputName4 = httpServletRequest.getParameter("examine_exampleInputName4");//产品代码
        if (examine_exampleInputName4 != null) {
            params.put("examine_exampleInputName4", examine_exampleInputName4);
        }
        String examine_exampleInputName5 = httpServletRequest.getParameter("examine_exampleInputName5");//组织机构
        if (examine_exampleInputName5 != null) {
            params.put("examine_exampleInputName5", examine_exampleInputName5);
        }
        String examine_exampleInputName6 = httpServletRequest.getParameter("examine_exampleInputName6");//营销团队
        if (examine_exampleInputName6 != null) {
            params.put("examine_exampleInputName6", examine_exampleInputName1);
        }
        String examine_exampleInputName7 = httpServletRequest.getParameter("examine_exampleInputName7");//投保人
        if (examine_exampleInputName7 != null) {
            params.put("examine_exampleInputName7", examine_exampleInputName7);
        }
        String examine_exampleInputName8 = httpServletRequest.getParameter("examine_exampleInputName8");//被保人
        if (examine_exampleInputName8 != null) {
            params.put("examine_exampleInputName8", examine_exampleInputName8);
        }
        String examine_exampleInputName9 = httpServletRequest.getParameter("examine_exampleInputName9");//员工工号
        if (examine_exampleInputName9 != null) {
            params.put("examine_exampleInputName9", examine_exampleInputName9);
        }
        String examine_exampleInputName10 = httpServletRequest.getParameter("examine_exampleInputName10");//状态
        if (examine_exampleInputName10 != null) {
            params.put("examine_exampleInputName10", examine_exampleInputName10);
        }
        String examine_exampleInputName11 = httpServletRequest.getParameter("examine_exampleInputName11");//投保日期
        if (examine_exampleInputName11 != null) {
            params.put("examine_exampleInputName11", examine_exampleInputName11);
        }
        String examine_auditresults = httpServletRequest.getParameter("examine_auditresults");//审核结果
        if (examine_auditresults != null) {
            params.put("examine_auditresults", examine_auditresults);
        }
        String examine_auditopinions = httpServletRequest.getParameter("examine_auditopinions");//审核意见
        if (examine_auditopinions != null) {
            params.put("examine_auditopinions", examine_auditopinions);
        }
        String tab = httpServletRequest.getParameter("Tab");//审核意见
        if (tab != null) {
            params.put("tab", tab);
        }
        String ids = httpServletRequest.getParameter("ids");//投保单id
        if (ids != null) {
            Object[] list = JSONArray.parseArray(ids).toArray();
            StringBuffer buffer = new StringBuffer();
            for(Object o : list){
                buffer.append("'");
                buffer.append(o.toString());
                buffer.append("'");
                buffer.append(",");
            }
            ids = buffer.toString();
            params.put("ids", ids.substring(0, ids.length()-1));
        }

        //********************************
        String exampleInputName1 = httpServletRequest.getParameter("exampleInputName1");
        //投保单号
        if (exampleInputName1 != null && !"".equals(exampleInputName1)) {
            params.put("policyId", exampleInputName1);
        }
        String exampleInputName2 = httpServletRequest.getParameter("exampleInputName2");//保险公司
        if (exampleInputName2 != null && !"".equals(exampleInputName2)) {
            params.put("insuranceCompanyId", exampleInputName2);
        }
        String exampleInputName3 = httpServletRequest.getParameter("exampleInputName3");//产品名称
        if (exampleInputName3 != null && !"".equals(exampleInputName3)) {
            params.put("productName", exampleInputName3);
        }
        String exampleInputName4 = httpServletRequest.getParameter("exampleInputName4");//产品代码
        if (exampleInputName4 != null && !"".equals(exampleInputName4)) {
            params.put("productCode", exampleInputName4);
        }
        String exampleInputName5 = httpServletRequest.getParameter("exampleInputName5");//组织机构
        if (exampleInputName5 != null && !"".equals(exampleInputName5)) {
            //params.put("salesOrgId", exampleInputName5);
            String salesOrgId = exampleInputName5;
            if (StringUtil.isNotBlank(salesOrgId)) {
                String salesAllOrgs = salesOrgInfoClient.findEmployeeAllOrgs(salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(salesOrgId).get(0).get("SALES_ORG_CODE").toString());
                params.put("salesAllOrgs", salesAllOrgs);
            }
        }
        String exampleInputName6 = httpServletRequest.getParameter("exampleInputName6");//营销团队
        if (exampleInputName6 != null && !"".equals(exampleInputName6)) {
            params.put("salesTeamId", exampleInputName6);
        }
        String exampleInputName7 = httpServletRequest.getParameter("exampleInputName7");//投保人
        if (exampleInputName7 != null && !"".equals(exampleInputName7)) {
            params.put("iphName", exampleInputName7);
        }
        String exampleInputName8 = httpServletRequest.getParameter("exampleInputName8");//被保人
        if (exampleInputName8 != null && !"".equals(exampleInputName8)) {
            params.put("pipName", exampleInputName8);
        }
        String exampleInputName9 = httpServletRequest.getParameter("exampleInputName9");//员工工号
        if (exampleInputName9 != null && !"".equals(exampleInputName9)) {
            params.put("empNo", exampleInputName9);
        }

        String state = httpServletRequest.getParameter("state");//状态
        if (state != null && !"".equals(state)) {
            params.put("state", state);
        }
        String exampleInputName10 = httpServletRequest.getParameter("exampleInputName10");//状态
        if (exampleInputName10 != null && !"".equals(exampleInputName10)) {
            params.put("state", exampleInputName10);
        }
        String insexampleInputName10 = httpServletRequest.getParameter("insexampleInputName10");//状态
        if (insexampleInputName10 != null && !"".equals(insexampleInputName10)) {
            params.put("insState", insexampleInputName10);
        }
        String exampleInputName11 = httpServletRequest.getParameter("exampleInputName11");//投保日期
        if (exampleInputName11 != null && !"".equals(exampleInputName11)) {
            params.put("insure", exampleInputName11);
        }

        String exampleInputName20 = httpServletRequest.getParameter("exampleInputName20");// 投保日期-开始
        if (StringUtils.isNotBlank(exampleInputName20)) {
            params.put("startInsure", exampleInputName20);
        }
        String exampleInputName21 = httpServletRequest.getParameter("exampleInputName21");// 投保日期-结束
        if (StringUtils.isNotBlank(exampleInputName21)) {
            params.put("endInsure", exampleInputName21);
        }
        String exampleInputName22 = httpServletRequest.getParameter("exampleInputName22");// 生效日期-开始
        if (StringUtils.isNotBlank(exampleInputName22)) {
            params.put("startTakeEffectData", exampleInputName22);
        }
        String exampleInputName23 = httpServletRequest.getParameter("exampleInputName23");// 生效日期-结束
        if (StringUtils.isNotBlank(exampleInputName23)) {
            params.put("endTakeEffectData", exampleInputName23);
        }
        String exampleInputName24 = httpServletRequest.getParameter("exampleInputName24");// 承保日期-开始
        if (StringUtils.isNotBlank(exampleInputName24)) {
            params.put("startUnderwritingData", exampleInputName24);
        }
        String exampleInputName25 = httpServletRequest.getParameter("exampleInputName25");// 承保日期-结束
        if (StringUtils.isNotBlank(exampleInputName25)) {
            params.put("endUnderwritingData", exampleInputName25);
        }
        /**
         * 查询保单号list
         * like 1,2,3,4， 后台 in （$()）
         */
        String selectListIds = httpServletRequest.getParameter("selectListIds");
        if(!StringUtil.isEmpty(selectListIds)){
            String[] split = selectListIds.split(",");
            selectListIds="";
            for (String s:
                    split ) {
                selectListIds+="'"+s+"',";
            }
            selectListIds = selectListIds.substring(0,selectListIds.length()-1);
            params.put("selectListIds", selectListIds);
        }
        String notSelectListIds = httpServletRequest.getParameter("notSelectListIds");
        if(!StringUtil.isEmpty(notSelectListIds)){
            String[] split = notSelectListIds.split(",");
            notSelectListIds="";
            for (String s:
                    split ) {
                notSelectListIds+="'"+s+"',";
            }
            notSelectListIds = notSelectListIds.substring(0,notSelectListIds.length()-1);
            params.put("notSelectListIds", notSelectListIds);
        }
        return params;
    }

    private Date stringToDate(String timeStr) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dataFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 确认生成批次：投保单
     */
    @RequestMapping("/commit_batch")
    @ResponseBody
    public ReturnMsgData commitBatch(HttpServletRequest httpServletRequest) {
        ReturnMsgData msg = new ReturnMsgData();
        Params pList = new Params();
        try {
            Subject subject = SecurityUtils.getSubject();
            Map<String, Object> params = new HashMap<String, Object>();
            //取身份信息
            Employee employee = (Employee) subject.getPrincipal();
//            String employeeNo = employee.getEmployeeNo();
//            params.put("insuranceSalerNo",employeeNo);
//            Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//            Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
            Object employeeId = employee.getEmployeeId();
            params.clear();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());

            String grant_forTdVal1_son = httpServletRequest.getParameter("grant_forTdVal1_son");
            String[] split = grant_forTdVal1_son.split(",");
            List<String> grant_forTdVal1_sons = Arrays.asList(split);
            String gen_name = httpServletRequest.getParameter("gen_name");
            String gen_org = httpServletRequest.getParameter("gen_org");
            String gen_type = httpServletRequest.getParameter("gen_type");
            String gen_date = httpServletRequest.getParameter("gen_date");
            pList.setList(grant_forTdVal1_sons);
            params.put("grant_forTdVal1_sons", grant_forTdVal1_sons);
            params.put("gen_name", gen_name);
            params.put("gen_org", gen_org);
            params.put("gen_type", gen_type);
            params.put("gen_date", gen_date);
            params.put("state", Constants.POLICY_STATUS_5);
            params.put("time", format);
            params.put("employeeId", employeeId);
            pList.setMap(params);
            Map<String, Object> msgData = insPolicyInsuredPersonFeign.commitBatch(pList);
            msg.setReturnCode("200");
        } catch (RuntimeException e) {
            msg.setReturnCode("400");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 确认生成批次：保单
     */
    @RequestMapping("/commit_batch_become")
    @ResponseBody
    public ReturnMsgData commitBatchBecome(HttpServletRequest httpServletRequest) {
        ReturnMsgData msg = new ReturnMsgData();
        Params pList = new Params();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            Subject subject = SecurityUtils.getSubject();
            //取身份信息
            Employee employee = (Employee) subject.getPrincipal();
//            String employeeNo = employee.getEmployeeNo();
//            params.put("insuranceSalerNo",employeeNo);
//            Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//            Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
            Object employeeId = employee.getEmployeeId();
            params.clear();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());

            String grant_forTdVal1_son = httpServletRequest.getParameter("grant_forTdVal1_son");
            String[] split = grant_forTdVal1_son.split(",");
            List<String> grant_forTdVal1_sons = Arrays.asList(split);
            String gen_name = httpServletRequest.getParameter("gen_name");
            String gen_org = httpServletRequest.getParameter("gen_org");
            String gen_type = httpServletRequest.getParameter("gen_type");
            String gen_date = httpServletRequest.getParameter("gen_date");
            pList.setList(grant_forTdVal1_sons);
            params.put("grant_forTdVal1_sons", grant_forTdVal1_sons);
            params.put("gen_name", gen_name);
            params.put("gen_org", gen_org);
            params.put("gen_type", gen_type);
            params.put("gen_date", gen_date);
            params.put("insState", Constants.INSURANCE_POLICY_STATUS_1004);
            params.put("time", format);
            params.put("employeeId", employeeId);
            pList.setMap(params);
            Map<String, Object> msgData = insPolicyInsuredPersonFeign.commitBatch(pList);
            msg.setReturnCode("200");
        } catch (RuntimeException e) {
            msg.setReturnCode("400");
        }
        return msg;
    }

    /**
     * 照会回销
     */
    @RequestMapping("add_insurance_note")
    @ResponseBody
    public Map<String, Object> addInsuranceNot(HttpServletRequest request) {
        Map<String, Object> msg = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        try {
            String note_policy = request.getParameter("note_policy");
            String note_problem_type = request.getParameter("note_problem_type");
            String note_sell_back = request.getParameter("note_sell_back");
            String note_problem_explain = request.getParameter("note_problem_explain");
            params.put("note_policy", note_policy);
            params.put("note_problem_type", note_problem_type);
            params.put("note_sell_back", note_sell_back);
            params.put("note_problem_explain", note_problem_explain);
            params.put("employeeId", employeeId);
            params.put("time", format);
            params.put("state", Constants.POLICY_STATUS_6);
            String[] note_fileNames = request.getParameterValues("note_fileName");
            String[] note_fastPaths = request.getParameterValues("note_fastPath");
            Params param = new Params();
            if (note_fileNames != null) {
                List<String> list = Arrays.asList(note_fileNames);
                List<Map<String,Object>> size = new ArrayList();
                list.forEach(string ->{
                    Map<String, Object> attMap = new HashMap<String, Object>();
                    attMap.put("attName",string);
                    attMap.put("attAdd",note_fastPaths[size.size()]);
                    size.add(attMap);
                });

                param.setIns_policy_att_list(size);
            }
            param.setMap(params);
            msg = insPolicyInsuredPersonFeign.addInsuranceNote(param);

        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;

    }

    @RequestMapping("/note_cancellation_grant")
    @ResponseBody
    public Map<String, Object> noteCancellationGrant(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> msg = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
//        String employeeNo = employee.getEmployeeNo();
//        map.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(map);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        map.clear();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        try {
            String noteCan_org = request.getParameter("noteCan_org");
            String noteCan_team = request.getParameter("noteCan_team");
            String noteCan_empNo = request.getParameter("noteCan_empNo");
            String noteCan_name = request.getParameter("noteCan_name");
            String noteCan_policy_id = request.getParameter("noteCan_policy_id");
            map.put("noteCan_org", noteCan_org);
            map.put("noteCan_team", noteCan_team);
            map.put("noteCan_empNo", noteCan_empNo);
            map.put("noteCan_name", noteCan_name);
            map.put("noteCan_policy_id", noteCan_policy_id);
            map.put("employeeId", employeeId);
            map.put("time", format);
            map.put("state", Constants.POLICY_STATUS_7);
            msg = insPolicyInsuredPersonFeign.noteCancellationGrant(map);
        } catch (RuntimeException e) {
            msg.put("code", "400");
        }
        return msg;
    }

    @RequestMapping("/note_cancellation_reply")
    @ResponseBody
    public Map<String, Object> noteCancellationReply(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> msg = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());

        try {
            String noteCan_reply = request.getParameter("noteCan_Reply");//回复内容
            String noteCan_policy_id = request.getParameter("noteCan_policy_id");//保单号

            params.put("employeeId", employeeId);
            params.put("time", format);
            params.put("state", Constants.POLICY_STATUS_8);
            params.put("noteCan_reply", noteCan_reply);
            params.put("noteCan_policy_id", noteCan_policy_id);

            msg = insPolicyInsuredPersonFeign.noteCancellationReply(params);

        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/note_cancellation_sell_back")
    @ResponseBody
    public Map<String, Object> noteCancellationSellBack(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> msg = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());

        try {
            String noteCan_policy_id = request.getParameter("noteCan_policy_id");
            params.put("noteCan_policy_id", noteCan_policy_id);
            params.put("employeeId", employeeId);
            params.put("time", format);
            params.put("state", Constants.POLICY_STATUS_9);
            msg = insPolicyInsuredPersonFeign.noteCancellationSellBack(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/note_cancellation_Abnormal")
    @ResponseBody
    public Map<String, Object> noteCancellationAbnormal(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> msg = new HashMap<String, Object>();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        try {
            String noteCan_policy_id = request.getParameter("noteCan_policy_id");
            String noteCan_abnormal = request.getParameter("noteCan_Abnormal");
            params.put("noteCan_policy_id", noteCan_policy_id);
            params.put("employeeId", employeeId);
            params.put("time", format);
            params.put("state", Constants.POLICY_STATUS_10);
            params.put("noteCan_abnormal", noteCan_abnormal);
            msg = insPolicyInsuredPersonFeign.noteCancellationAbnormal(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
        }
        return msg;
    }

    /**
     * 跳转到保单接收列表
     */
    @RequestMapping("/to_become_insurance_policy_receive")
    public String toBecomeInsurancePolicyReceive() {
        return "insurancePolicy/InsurancePolicyBecomeReceive";
    }

    /**
     * 保单接收列表分页查询
     */
    @RequestMapping("/do_become_insurance_policy_receive")
    @ResponseBody
    public DataMsg toBecomeInsurancePolicyReceive(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            params.put("insState", Constants.INSURANCE_POLICY_STATUS_1004);
            PageModel model = insPolicyInsuredPersonFeign.selectBecomeInsurancePolicyReceiveList(params);
            List list = model.getList();
            //TODO 发送机构和发送合作伙伴 两个字段从何处取值，待产品确认
            //获取发送机构及发送人名称
            if(!(list == null || list.isEmpty())) {
                getSendOrgAndName(list);
            }
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     * 跳转到确认接收页面
     */
    @RequestMapping("/to_become_insurance_policy_receive_commit")
    public String toBecomeInsurancePolicyReceiveCommit(HttpServletRequest request, Model model) {
        String parameter = request.getParameter("id");
        String[] split = parameter.split(",");
        String BATCH_NAME = split[0];
        String dept_name = split[1];
        String BATCH_TYPE = split[2];
        String INSURANCE_SALER = split[3];
        String INSURANCE_COMPANY_NAME = split[4];
        String BATCH_DATE = split[5];
        model.addAttribute("BATCH_NAME", BATCH_NAME);
        model.addAttribute("dept_name", dept_name);
        model.addAttribute("BATCH_TYPE", BATCH_TYPE);
        model.addAttribute("INSURANCE_SALER", INSURANCE_SALER);
        model.addAttribute("INSURANCE_COMPANY_NAME", INSURANCE_COMPANY_NAME);
        model.addAttribute("BATCH_DATE", BATCH_DATE);
        return "/insurancePolicy/InsurancePolicyBecomeReceiveCommit";

    }

    @RequestMapping("/do_become_insurance_policy_receive_commit")
    @ResponseBody
    public DataMsg doBecomeInsurancePolicyReceiveCommit(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String batchName = request.getParameter("batchName");
            params.put("batchName", batchName);

            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            e.printStackTrace();
        }
        return dataMsg;
    }

    @RequestMapping("/becomeRecCommit")
    @ResponseBody
    public Map<String, Object> becomeRecCommit(String id) {
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Map<String, Object> msg = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        try {
            params.put("employeeId", employeeId);
            params.put("time", format);
            params.put("id", id);
            params.put("insState", Constants.INSURANCE_POLICY_STATUS_1005);
            msg = insPolicyInsuredPersonFeign.becomeRecCommit(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 跳转到回执审核
     */
    @RequestMapping("/to_receipt")
    public String to_receipt_audit() {
        return "insurancePolicy/ReceiptList";
    }

    /**
     * 跳转到录入回执页面
     */
    @RequestMapping("update_receipt_one")
    public String updateUeceiptOne(HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        //getWorkGroupParams(map);
        getAuthDataParams(map);
        String id = request.getParameter("id");
        map.put("policyId", id);
        Map<String, Object> insurancePolicyAllList = insPolicyInsuredPersonFeign.getPipList(map);
        model.addAttribute("obj", insurancePolicyAllList);
        return "/insurancePolicy/ReceiptCommit";
    }

    /**
     * 保存回执信息
     */
    @RequestMapping("add_insurance_receipt")
    @ResponseBody
    public Map<String, Object> addInsuranceReceipt(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Map<String, Object> msg = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        try {
            params.put("employeeId", employeeId);
            params.put("time", format);
            params.put("insState", Constants.INSURANCE_POLICY_STATUS_1006);
            String policy_id = request.getParameter("RECE_POLICY_ID");
            String rece_customer_signature = request.getParameter("RECE_Customer_Signature");


            String[] recei_fileNames = request.getParameterValues("recei_fileName");
            String[] recei_fastPaths = request.getParameterValues("recei_fastPath");


            if(recei_fileNames != null){
                params.put("recei_fileNames",Arrays.toString(recei_fileNames));
                params.put("recei_type","4");
            }
            if(recei_fastPaths != null){
                params.put("recei_fastPaths",Arrays.toString(recei_fastPaths));
            }
            if (rece_customer_signature != null) {
                params.put("rece_customer_signature", rece_customer_signature);
            }
            String rece_org_date = request.getParameter("RECE_ORG_DATE");
            if (rece_org_date != null) {
                params.put("rece_org_date", rece_org_date);
            }
            String rece_explain = request.getParameter("RECE_Explain");
            if (rece_explain != null) {
                params.put("rece_explain", rece_explain);
            }
            params.put("policy_id", policy_id);
            msg = insPolicyInsuredPersonFeign.addInsuranceReceipt(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }

        return msg;
    }

    /**
     * 跳转到回执审核页面
     */
    @RequestMapping("to_receipt_examine")
    public String toReceiptExamine() {
        return "/insurancePolicy/ReceiptExamineList";
    }

    /**
     * 回执审核 分页查询
     */
    @RequestMapping("/do_receipt_examine")
    @ResponseBody
    public DataMsg doReceiptExamine(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String type = request.getParameter("type");
            if (StringUtil.isNotBlank(type)) {
                params.put("type", request.getParameter("type"));
            }
            String insState = request.getParameter("insState");
            params.put("insState", insState);
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign",principalDeputySign);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     * 保存审核，全部审核，批量审核
     */
    @RequestMapping("/submit_receipt_examine")
    @ResponseBody
    public Map<String, Object> submitReceiptExamine(HttpServletRequest httpServletRequest) {
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Map<String, Object> msg = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        try {
            params.put("employeeId", employeeId);
            params.put("time", format);
            String examine_auditresults = httpServletRequest.getParameter("examine_auditresults");//审核结果 2通过3不通过
            String examine_auditopinions = httpServletRequest.getParameter("examine_auditopinions");//审核意见
            String tab = httpServletRequest.getParameter("Tab");
            params.put("examine_auditresults", examine_auditresults);
            params.put("examine_auditopinions", examine_auditopinions);
            params.put("tab", tab);
            if ("2".equals(examine_auditresults)) {
                params.put("insState", Constants.INSURANCE_POLICY_STATUS_1007);
            } else {
                params.put("insState", Constants.INSURANCE_POLICY_STATUS_1008);
            }
            msg = insPolicyInsuredPersonFeign.submitReceiptExamine(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 跳转到回执移交页面
     */
    @RequestMapping("/to_receipt_grant")
    public String toReceiptGrant() {
        return "/insurancePolicy/InsurancePolicyReceiptGrant";
    }

    /**
     * *
     * *跳转生成批次页面：回执
     * *
     */

    @RequestMapping("/to_receipt_generate_batches")
    public String toReceiptGenerateBatches(HttpServletRequest httpServletRequest, Model model) {
        String id = httpServletRequest.getParameter("id");
        model.addAttribute("ids", id);
        return "insurancePolicy/GenerateBatchesReceIpt";
    }

    /**
     * 回执 下发 提交
     */
    @RequestMapping("/commit_batch_receipt")
    @ResponseBody
    public ReturnMsgData commitBatchReceipt(HttpServletRequest httpServletRequest) {
        ReturnMsgData msg = new ReturnMsgData();
        Params pList = new Params();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            Subject subject = SecurityUtils.getSubject();
            //取身份信息
            Employee employee = (Employee) subject.getPrincipal();
//            String employeeNo = employee.getEmployeeNo();
//            params.put("insuranceSalerNo",employeeNo);
//            Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//            Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
            Object employeeId = employee.getEmployeeId();
            params.clear();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());

            String grant_forTdVal1_son = httpServletRequest.getParameter("grant_forTdVal1_son");
            String[] split = grant_forTdVal1_son.split(",");
            List<String> grant_forTdVal1_sons = Arrays.asList(split);
            String gen_name = httpServletRequest.getParameter("gen_name");
            String gen_org = httpServletRequest.getParameter("gen_org");
            String gen_type = httpServletRequest.getParameter("gen_type");
            String gen_date = httpServletRequest.getParameter("gen_date");
            pList.setList(grant_forTdVal1_sons);
            params.put("grant_forTdVal1_sons", grant_forTdVal1_sons);
            params.put("gen_name", gen_name);
            params.put("gen_org", gen_org);
            params.put("gen_type", gen_type);
            params.put("gen_date", gen_date);
            params.put("insState", Constants.INSURANCE_POLICY_STATUS_1009);
            params.put("time", format);
            params.put("employeeId", employeeId);
            pList.setMap(params);
            Map<String, Object> msgData = insPolicyInsuredPersonFeign.commitBatchReceipt(pList);
            msg.setReturnCode("200");
        } catch (RuntimeException e) {
            msg.setReturnCode("400");
        }
        return msg;
    }

    /**
     * 跳转到回访录入页面
     */
    @RequestMapping("/to_return_visit")
    public String toReturnVisit() {
        return "insurancePolicy/ReturnVisit";
    }

    /**
     * 回访录入 分页查询
     */
    @RequestMapping("/do_return_visit")
    @ResponseBody
    public DataMsg doReturnVisit(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String type = request.getParameter("type");
            if (StringUtil.isNotBlank(type)) {
                params.put("type", request.getParameter("type"));
            }
            String insState = request.getParameter("insState");
            params.put("insState", insState);
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign",principalDeputySign);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     * 跳转到录入回访页面
     */
    @RequestMapping("/to_return_visit_commit")
    public String toReturnVisitCommit(HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        //getWorkGroupParams(map);
        getAuthDataParams(map);
        String id = request.getParameter("id");
        map.put("policyId", id);
        Map<String, Object> insurancePolicyAllList = insPolicyInsuredPersonFeign.getPipList(map);
        model.addAttribute("obj", insurancePolicyAllList);
        return "/insurancePolicy/ReturnVisitCommit";
    }

    /**
     * 保存回访信息
     */
    @RequestMapping("add_return_visit")
    @ResponseBody
    public Map<String, Object> addReturnVisit(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        SimpleDateFormat format_ymd = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        Map<String, Object> msg = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        try {
            params.put("employeeId", employeeId);
            params.put("time", format);
            params.put("insState", Constants.INSURANCE_POLICY_STATUS_1010);
            String policy_id = request.getParameter("RETURN_POLICY_ID");
            String RETURN_type = request.getParameter("RETURN_type");
            if (RETURN_type != null) {
                params.put("RETURN_type", RETURN_type);
            }
            String RETURN_ORG_DATE = request.getParameter("RETURN_ORG_DATE");
            if (RETURN_ORG_DATE != null) {
                params.put("RETURN_ORG_DATE", RETURN_ORG_DATE);
                Date parse = format_ymd.parse(RETURN_ORG_DATE);
                String s = addDayBySomeDay(parse, 15);
                params.put("statistMonth",s);
            }
            String RETURN_Explain = request.getParameter("RETURN_Explain");
            if (RETURN_Explain != null) {
                params.put("RETURN_Explain", RETURN_Explain);
            }
            String return_employee_no = request.getParameter("RETURN_EMPLOYEE_NO");
            if(StringUtil.isNotBlanks(return_employee_no)){
                params.put("return_employee_no", return_employee_no);
            }
            params.put("policy_id", policy_id);
            msg = insPolicyInsuredPersonFeign.addReturnVisit(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return msg;
    }

    /**
     * 跳转到录入回访页面
     *
     */
    @RequestMapping("/to_return_visit_detailed")
    public String toReturnVisitDetailed(HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        //getWorkGroupParams(map);
        getAuthDataParams(map);
        String id = request.getParameter("id");
        map.put("policyId", id);
        Map<String, Object> insurancePolicyAllList = insPolicyInsuredPersonFeign.getPipList(map);
        model.addAttribute("obj", insurancePolicyAllList);
        return "/insurancePolicy/ReturnVisitDetailed";
    }

    /**
     * 跳转到保单明细页面
     */
    @RequestMapping("/ins_detailed_page")
    public String insDetailedPage(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        //getWorkGroupParams(paras);
        getAuthDataParams(paras);
        paras.put("id", id);
        //2-保单,3-投保单照会,4-回执
        List<InsPolicyAttEntity> ipa = insPolicyInsuredPersonFeign.selectInsPolicyAttEntityByKey(paras);
        List<InsPolicyAttEntity> ipaType1 = new LinkedList<InsPolicyAttEntity>();//投保单 影像list
        List<InsPolicyAttEntity> ipaType2 = new LinkedList<InsPolicyAttEntity>();//保单 影像list
        List<InsPolicyAttEntity> ipaType3 = new LinkedList<InsPolicyAttEntity>();//投保单照会 影像list
        List<InsPolicyAttEntity> ipaType4 = new LinkedList<InsPolicyAttEntity>();//回执 影像list
        ipa.forEach(insPolicyAttEntity->{
            String type = insPolicyAttEntity.getType();
            switch (type) {
                case "1":
                    ipaType1.add(insPolicyAttEntity);
                    break;
                 case "2":
                    ipaType2.add(insPolicyAttEntity);
                     break;
                case "3":
                    ipaType3.add(insPolicyAttEntity);
                    break;
                case "4":
                    ipaType4.add(insPolicyAttEntity);
                    break;
            }
        });
        String ipaJson1 = JSONObject.toJSONString(ipaType1);
        String ipaJson2 = JSONObject.toJSONString(ipaType2);
        String ipaJson3 = JSONObject.toJSONString(ipaType3);
        String ipaJson4 = JSONObject.toJSONString(ipaType4);
        paras.remove("type");
        List<Map<String, Object>> infor = insPolicyInsuredPersonFeign.selectInsInformationEntityByKey(paras);
        String inforJson = JSONObject.toJSONString(infor);

        String ipaJson = JSONObject.toJSONString(ipa);
        InsPolicyHolderEntity iph = insPolicyInsuredPersonFeign.selectInsPolicyHolderEntityByKey(paras);
        InsPolicyInsuredPersonEntity pip = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(paras);
        List<InsPolicyProfitsPersonEntity> ppp = insPolicyInsuredPersonFeign.selectInsPolicyProfitsPersonEntityByKey(paras);
        String s = JSONObject.toJSONString(ppp);
        Map<String, Object> ipp = insPolicyInsuredPersonFeign.selectInsPolicyProposalEntityByKey(paras);
        //查询保险公司信息
        Long insuranceCompanyId = Long.valueOf(ipp.get("insurance_company_id").toString());
        paras.clear();
        paras.put("id", insuranceCompanyId);
        InsuranceDept dept = insuranceDeptClient.selectInsuranceBasicDept(paras);
        paras.clear();
        paras.put("policyId", id);
        //getWorkGroupParams(paras);
        getAuthDataParams(paras);
        Map<String, Object> insurancePolicyAllList = insPolicyInsuredPersonFeign.getPipList(paras);
        paras.put("principalDeputySign", "0");
        Map<String, Object> net = insPolicyInsuredPersonFeign.selectNetMap(paras);
        paras.remove("principalDeputySign");
        List<Map<String, Object>> stateMap = insPolicyInsuredPersonFeign.selectStateMap(paras);
        getOperators(stateMap);
        String str = JSON.toJSONString(stateMap);
        if (stateMap != null) {
            model.addAttribute("staSize", stateMap.size());
        } else {
            model.addAttribute("staSize", 0);
        }

        model.addAttribute("ipaType1",ipaJson1);
        model.addAttribute("ipaType2",ipaJson2);
        model.addAttribute("ipaType3",ipaJson3);
        model.addAttribute("ipaType4",ipaJson4);

        model.addAttribute("sta", str);
        model.addAttribute("net", net);
        model.addAttribute("obj", insurancePolicyAllList);
        model.addAttribute("inforJson", inforJson);
        model.addAttribute("ipa", ipa);
        model.addAttribute("ipasize", ipa.size());
        model.addAttribute("iph", iph);
        model.addAttribute("pip", pip);
        model.addAttribute("ppp", ppp);
        model.addAttribute("pppSize", ppp.size());
        model.addAttribute("ipp", ipp);
        model.addAttribute("dept", dept);
        model.addAttribute("ipaJson", ipaJson);
        model.addAttribute("pppJson", s);
        return "/insurancePolicy/InsDetailedPage";
    }

    /**
     * 获取操作人名称
     * @param stateMap
     */
    private void getOperators(List<Map<String, Object>> stateMap){
        if(stateMap == null || stateMap.isEmpty()) {
            return ;
        }
        try {
            StringBuffer employeeIdsBuf = new StringBuffer();
            stateMap.forEach(obj ->{
                Map<String, Object> map = (Map<String, Object>)obj;
                String employeeId =  map.get("create_by") == null ? "" : String .valueOf(map.get("create_by"));
                employeeIdsBuf.append(employeeId).append(",");

            });
            String employeeIds = employeeIdsBuf.toString();
            if(employeeIds.lastIndexOf(",") != -1) {
                employeeIds = employeeIds.substring(0, employeeIds.length() -1);
            }
            List<Map<String, Object>> employeeInfoList = employeeFeignClient.getEmployeeInfoByEmployeeIds(employeeIds);
            stateMap.forEach(obj ->{
                Map<String, Object> map = (Map<String, Object>)obj;
                for(Map<String, Object> empMap : employeeInfoList) {
                    if(map.get("create_by").equals(String.valueOf(empMap.get("employee_id")))) {
                        map.put("name", empMap.get("name"));
                        break;
                    }
                }
                if(map.get("dept_name") == null){
                    map.put("dept_name", "-");
                }
            });
        }catch(Exception e){
            logger.error("获取操作人失败{}", e);
        }
    }

    /**
     * 跳转到回执详情页面
     */
    @RequestMapping("select_receipt_det")
    public String selectReceiptDet(HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();

        return "/insurancePolicy/InsDetailedPage";
    }

    /**
     * 跳转到中止，终止，撤保，退保页面
     */
    @RequestMapping("/to_policy_operation")
    public String toPolicyOperation() {
        return "insurancePolicy/Operation";
    }

    /**
     * 中止，终止，撤保，退保 分页查询
     */
    @RequestMapping("/do_operation")
    @ResponseBody
    public DataMsg doOperation(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String type = request.getParameter("type");
            if (StringUtil.isNotBlank(type)) {
                params.put("type", request.getParameter("type"));
            }
            String principalDeputySign = request.getParameter("principalDeputySign");
            params.put("principalDeputySign",principalDeputySign);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("总监津贴[查询]异常");
            e.printStackTrace();
        }
        return dataMsg;
    }

    @RequestMapping("/operationCommit")
    @ResponseBody
    public Map<String, Object> operationCommit(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Map<String, Object> msg = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Object employeeId = employee.getEmployeeId();
        params.clear();
        try {
            String id = request.getParameter("id");
            String insStar = request.getParameter("insStar");
            params.put("employeeId", employeeId);
            params.put("time", format);
            if ("1".equals(insStar)) {
                params.put("insState", Constants.INSURANCE_POLICY_STATUS_1011);
            } else if ("2".equals(insStar)) {
                params.put("insState", Constants.INSURANCE_POLICY_STATUS_1012);
            } else if ("3".equals(insStar)) {
                params.put("insState", Constants.INSURANCE_POLICY_STATUS_1013);
            } else if ("4".equals(insStar)) {
                params.put("insState", Constants.INSURANCE_POLICY_STATUS_1014);
            }
            params.put("policy_id", id);
            msg = insPolicyInsuredPersonFeign.addOperation(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 跳转到承保清单导入
     */
    @RequiresPermissions("insuranceImport:list")
    @RequestMapping("/to_import_insurance")
    public String toImportInsurance() {
        return "/insurancePolicy/InsuranceImport";
    }

    /**
     * 下载承保清单模板
     */
    @RequestMapping("/download_template")
    public String toDownloadTemplate() {
        return "/insurancePolicy/InsuranceImport";
    }
    /**
     * 导入承保清单
     * */
    @RequestMapping(value = "/importInsurance", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importInsurance(MultipartFile file) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        Map<String,Object> msg =new HashMap<String,Object>();
        DataMsg dataMsg = new DataMsg();
        //从shiro的session中取Employee
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
//        String employeeNo = employee.getEmployeeNo();
//        params.put("insuranceSalerNo",employeeNo);
//        Map<String, Object> stringObjectMap = insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
//        Object employeeId = stringObjectMap.get("INSURANCE_SALES_ID");
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("employeeId", employee.getEmployeeId());
        params.clear();
        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(new Date());
        params.put("now",now);
        params.put("emp",stringObjectMap);
        try {
            if (!file.isEmpty()) {
                FileInputStream in = (FileInputStream) file.getInputStream();
                Workbook workbook = WorkbookFactory.create(in);  //兼容xls和xlsx
                Map<String,Sheet> sheetMap = new HashMap<String,Sheet>();
                for(int i = 0; i<workbook.getNumberOfSheets();i++){
                    String sheetName = workbook.getSheetName(i);
                    Sheet sheetAt = workbook.getSheetAt(i);
                    sheetMap.put(sheetName,sheetAt);
                }
                List<List<String>> inspolicyAttRowList = new ArrayList<>();//---保单数据集---
                Sheet att = sheetMap.get("Beneficiary");
                int attNumberOfCells = att.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < att.getPhysicalNumberOfRows(); j++){
                    Row row=att.getRow(j);
                    if (row != null){
                        List<String> productCellList = new ArrayList<>();
                        for (int k = 0; k <= attNumberOfCells; k++) {//获取每个单元格
                            productCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=productCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                           inspolicyAttRowList.add(productCellList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                List<List<String>> productRowList = new ArrayList<>();//---产品数据集---
                Sheet product = sheetMap.get("product");
                 int productNumberOfCells = product.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < product.getPhysicalNumberOfRows(); j++){
                    Row row=product.getRow(j);
                    if (row != null) {
                        List<String> productCellList = new ArrayList<>();
                        for (int k = 0; k <= productNumberOfCells; k++) {//获取每个单元格
                            productCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=productCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                           productRowList.add(productCellList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                List<List<String>> inspolicyRowList = new ArrayList<>();//---保单数据集---
                Sheet insPolicy = sheetMap.get("inspolicy");
                int insPolicyNumberOfCells = insPolicy.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < insPolicy.getPhysicalNumberOfRows(); j++){
                    Row row=insPolicy.getRow(j);
                    if (row != null) {
                        List<String> insPolicyCellList = new ArrayList<>();
                        for (int k = 0; k <= insPolicyNumberOfCells; k++) {//获取每个单元格
                            insPolicyCellList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=insPolicyCellList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                          inspolicyRowList.add(insPolicyCellList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(productRowList) && !CollectionUtils.isEmpty(inspolicyRowList)) {
                    List<Map<String, Object>> sexDictList = systemDictFeignClient.findDictNameByDictType("SEX");
                    List<Map<String, Object>> cardDictList = systemDictFeignClient.findDictNameByDictType("CARD");
                    List<Map<String, Object>> relationshipDictList = systemDictFeignClient.findDictNameByDictType("RELATIONSHIP");
                    List<Map<String,Object>> payTypeDictList = systemDictFeignClient.findDictNameByDictType("PAY_TYPE");

                    //保单号 对应 产品名称
                    Map<String,Object> insIDProduName = new HashMap<String,Object>();
                    List<Map<String,Object>> ins_policy_insured_person_List = new LinkedList<Map<String,Object>>();
                    List<Map<String,Object>> ins_policy_holder_List = new LinkedList<Map<String,Object>>();
                    List<Map<String,Object>> insInformationList = new LinkedList<Map<String,Object>>();
                    List<Map<String,Object>> insertList = new LinkedList<Map<String,Object>>();
                    Map<String,Object> policyCodeEmpNo = new HashMap<String,Object>();
                    Set<String> policyIdSet = new HashSet<String>();

                    //保单入库
                    importInsert(inspolicyRowList,sexDictList,cardDictList,relationshipDictList,payTypeDictList,params,ins_policy_insured_person_List,ins_policy_holder_List,policyCodeEmpNo,Constants.IMPL_TYPE_POLICY,policyIdSet);
                    //产品入库
                    importInsertInsProduct(productRowList,sexDictList,cardDictList,relationshipDictList,params,policyCodeEmpNo,insInformationList,msg,policyIdSet);
                    //导入受益人
                    if (!CollectionUtils.isEmpty(inspolicyAttRowList)){
                        importInsertInsAtt(inspolicyAttRowList, sexDictList, cardDictList, relationshipDictList, params, insertList);
                    }
                    //入库
                    String ins_policy_insured_person_string = JSONObject.toJSONString(ins_policy_insured_person_List);
                    String ins_policy_holder_string = JSONObject.toJSONString(ins_policy_holder_List);
                    String insInformationString = JSONObject.toJSONString(insInformationList);
                    String insertString = JSONObject.toJSONString(insertList);
                    Map<String,Object> p = new HashMap<String,Object>();
                    p.put("ins_policy_insured_person_string", ins_policy_insured_person_string);
                    p.put("ins_policy_holder_string",ins_policy_holder_string );
                    p.put("insInformationString",insInformationString );
                    p.put("insertString", insertString);
                    msg = insPolicyInsuredPersonFeign.insertImportList(p);
                }else{
                    msg.put("code","0000");
                }
            } else {
                msg.put("code","0000");
            }
        }catch (CheckException e){
            String message = e.getMessage();
            msg.put("code","500");
            msg.put("error",message);
//            String message = e.getMessage();
//            String[] split = message.split(":");
//                    if("InsuranceSalesInfoIsNullErroe".equals(split[0])){
//                        msg.put("code","500");
//                        msg.put("error","未查询到员工："+split[1]);
//                    }else if("EmpNoIsNullErroe".equals(split[0])){
//                        msg.put("code","500");
//                        msg.put("error","员工编号不能为空："+split[1]);
//                    }else if("ProIdMapIsNullError".equals(split[0])){
//                        msg.put("code","500");
//                        msg.put("error","未查询到协议Id："+split[1]);
//                    }else if("totalPremiumIsNullErroe".equals(split[0])){
//                        msg.put("code","500");
//                        msg.put("error","总保费不能为空："+split[1]);
//                    }else {
//                         msg.put("code","400");
//                    }
            logger.error("导入承保清单异常|异常：{}", e);
        }
        catch (Exception e) {
            logger.error("导入承保清单异常|异常：{}", e);
            msg.put("code","400");
        }
        return msg;
    }
    private void importInsertInsAtt( List<List<String>> inspolicyAttRowList,List<Map<String, Object>> sexDictList, List<Map<String, Object>> cardDictList,
                                         List<Map<String, Object>> relationshipDictList,Map<String,Object> params,List<Map<String,Object>> insertList){

        Map<String, Object> emp = (Map<String, Object>) params.get("emp");
        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(new Date());
        inspolicyAttRowList.forEach(attBeanList->{
            String policyId = attBeanList.get(0);  // 保单号
            String profitsName = attBeanList.get(1);  // 受益人姓名
            if (StringUtils.isNotBlank(profitsName)) {
                HashMap<String, Object> profitsMap = Maps.newHashMapWithExpectedSize(9);
                profitsMap.put("policyId", policyId);
                profitsMap.put("name", profitsName);
                profitsMap.put("relationship", attBeanList.get(2)); // 与保险人关系
                profitsMap.put("level", attBeanList.get(3)); // 顺位
                profitsMap.put("benefitRatio", attBeanList.get(4)); // 比例
                profitsMap.put("createTime",now);
                profitsMap.put("createBy",emp.get("employeeId"));
//                profitsMap.put("createBy",emp.get("INSURANCE_SALES_ID"));
                String sex= attBeanList.get(5);
                if (StrUtil.isNotBlank(sex)) {
                    for (Map sexDict : sexDictList) {
                        if (sex.equals(sexDict.get("dict_name"))) {
                            sex = sexDict.get("dict_code").toString();
                            break;
                        }
                    }
                    if (sex.equals(attBeanList.get(5))){
                        throw new CheckException(policyId+" :受益人性别不正确,请参考字典值");
                    }
                    profitsMap.put("sex", sex); // 性别
                }
               String cardType = attBeanList.get(6);
                if (StrUtil.isNotBlank(cardType)) {
                    for (Map cardDict : cardDictList) {
                        if (cardType.equals(cardDict.get("dict_name"))) {
                            cardType = cardDict.get("dict_code").toString();
                            break;
                        }
                    }
                    if (cardType.equals(attBeanList.get(6))){
                        throw new CheckException(policyId+",受益人证件类型不存在，请参考字典值");
                    }
                    profitsMap.put("cardType", cardType); // 证件类型
                }


                profitsMap.put("cardNo", attBeanList.get(7)); // 证件号
                String birthday = attBeanList.get(8);
                birthday = "".equals(birthday)?null:birthday;
                profitsMap.put("birthday", birthday); // 受益人出生日期
                insertList.add(profitsMap);
            }
        });
    }

    private void importInsertInsProduct( List<List<String>> productRowList,List<Map<String, Object>> sexDictList, List<Map<String, Object>> cardDictList,
                                     List<Map<String, Object>> relationshipDictList,Map<String,Object> params, Map<String,Object> policyCodeEmpNo, List<Map<String,Object>> insInformationList,Map<String,Object> msg,Set<String> policyIdSet
                                     ){
          List<Map<String, Object>> jfTypeMap = systemDictFeignClient.findDictNameByDictType(Constants.DICT_TYPE_JFTYPE);
        //新建一个map 保存保单号和主附险标识   用于判断一个保单只能有一个主险
        Map<String, Integer> zfxmap = new HashMap<>();
        productRowList.forEach(productBeanList->{
         int[] filedindexs = {0,1,2,3,4,5,6};
          String[] filedNames = {"投保单号","产品名称","保额","保费","缴费方式","缴费年期","保险期间"};
          List<String> validateMust = ImportUtil.validateMust(productBeanList, filedindexs, filedNames);
          if (!CollectionUtils.isEmpty(validateMust)){
              throw new CheckException("投保单号:"+productBeanList.get(0)+",请录入必填项："+validateMust.toString());
          }
        Map<String,Object> productMap = new HashMap<String,Object>();
        String policyId = productBeanList.get(0);  // 保单号
         if (zfxmap.get(policyId) == null) {
            zfxmap.put(policyId, 0);
        }
        String productName = productBeanList.get(1);//产品名称
        String insuredAmount = productBeanList.get(2);  // 保额
        String premium = productBeanList.get(3);  // 保费
        String size = "1";//份数 默认1份
        String paymentMethod = productBeanList.get(4);  // 缴费方式
        boolean isDict = false;
        for (Map map:jfTypeMap) {
            if (paymentMethod.equals(map.get("dict_code"))){
                isDict = true;
                break;
            }
        }
        if (!isDict){
            throw  new CheckException("投保单号:"+productBeanList.get(0)+"的缴费方式填写不正确,请参考字典值填写对应的编码");
        }
        String payment = productBeanList.get(5);  // 缴费期间
        String periodOfInsurance = productBeanList.get(6);  // 保险期间
        Map<String, Object> ins_information = Maps.newHashMapWithExpectedSize(3);
        ins_information.put("productName", productName);
        ins_information.put("insuredAmount", insuredAmount);
        ins_information.put("premium", premium);
        ins_information.put("policyId", policyId);
        ins_information.put("periodOfInsurance", periodOfInsurance);
        ins_information.put("paymentMethod", paymentMethod);
        ins_information.put("payment", payment);
        ins_information.put("size", size);
        Map<String,Object> par = new HashMap<String,Object>();
        par.put("productName",productName);
        par.put("productsStatus","0");
        par.put("insuranceCompanyId",params.get(policyId+"insuranceCompanyId"));//保险公司id
        Map<String,Object> product = insPolicyInsuredPersonFeign.selectInsProductAndDept(par);
        if (MapUtils.isEmpty(product)){
            throw new CheckException("保单号："+policyId+", 产品："+productName+", 未查到该产品或未在该保险公司下");
        }
        ins_information.put("paymentPeriodSign",product.get("RENEWAL_PERIOD_TYPE"));//缴费 年期/年龄 标识
        ins_information.put("periodOf",product.get("INSURANCE_PERIOD_TYPE") );//保险期间标识
        ins_information.put("principalDeputySign",product.get("MAIN_OR_ADDITIONAL") );//主附险标识
            //校验主附险
        if ("0".equals(product.get("MAIN_OR_ADDITIONAL"))) {
            //主险个数
            Integer count = zfxmap.get(policyId);
            zfxmap.put(policyId,count+1);
        }


        ins_information.put("insuranceType",product.get("INSURANCE_TYPE") );//险种类别
        ins_information.put("productId",product.get("PRODUCTS_RATIO_ID") );//产品ID
        Object products_ratio_id = product.get("PRODUCTS_RATIO_ID");
        Object orgId = params.get(policyId+"orgId");
        par.clear();
            par.put("orgid",orgId);
            par.put("products_ratio_id",products_ratio_id);
            //查询产品协议
            Map<String,Object> proId = insPolicyInsuredPersonFeign.selectProtocolId(par);
            if(MapUtils.isEmpty(proId)){
                throw new CheckException("保单号："+policyId+",产品："+productName+",未查询到协议");
            }
            if("".equals(proId.get("PROTOCOL_ID")) || proId.get("PROTOCOL_ID") == null){
                throw new CheckException("保单号："+policyId+",产品："+productName+",未查询到该代理人组织下签署的协议");
            }
            ins_information.put("protocolId",proId.get("PROTOCOL_ID"));
            //查询基础佣金率是否设置
            par.clear();
            par.put("product_code", product.get("PRODUCTS_CODE"));
            par.put("orgid", orgId);
            Map<String, Object> rate = partnershipCommissionClient.selectCommissionRate(par);
            if (ObjectUtil.isEmpty(rate.get("CommissionRate"))) {
                throw new CheckException("保单号："+policyId+",产品："+productName+",该代理人组织下未设置基础佣金率");
            }

        insInformationList.add(ins_information);
        if(policyIdSet.contains(policyId)){
            policyIdSet.remove(policyId);
        }
    });
        //检验主险是否有且只有一个
        zfxmap.keySet().forEach(key -> {
            if (1 != zfxmap.get(key)){
                throw  new CheckException("保单号："+key+",没有或者存在多个主险产品，请核实！");
            }
        });
        if(policyIdSet.size()>0){
            StringBuilder str = new StringBuilder();
            for (String s : policyIdSet) {
                str.append(s).append(",");
            }
            throw new CheckException("这部分保单号："+str.toString()+"未有对应的产品，请核实！");
        }

}

    /**
     * 解析投保单数据导入
     * @param inspolicyRowList
     * @param sexDictList
     * @param cardDictList
     * @param relationshipDictList
     * @param payTypeDictList
     * @param params
     * @param ins_policy_insured_person_List
     * @param ins_policy_holder_List
     * @param policyCodeEmpNo
     * @param type
     */
    private void importInsertInsPolicy (List<List<String>> inspolicyRowList,List<Map<String, Object>> sexDictList, List<Map<String, Object>> cardDictList,
                                    List<Map<String, Object>> relationshipDictList,List<Map<String,Object>> payTypeDictList,Map<String,Object> params,List<Map<String,Object>> ins_policy_insured_person_List,
                                    List<Map<String,Object>> ins_policy_holder_List,Map<String,Object>policyCodeEmpNo,String type,Set<String> policyIdSet){
            //校验唯一性
        ArrayList<String> policyIdList = new ArrayList<>();
        inspolicyRowList.forEach(insBeanList->{
                     int[] filedindexs = {1,2,3,4,5,6,7,8,9,10,11,12,13,28,29,30,31,32,33,34,35,36};
                     String[] filedNames = {"投保单号","投保公司","总保费（元）","投保日期","代理人工号","投保人姓名","投保人性别","投保人证件类型","投保人证件号码","投保人移动电话","投保人出生日期","投保人年收入","投保人常驻地址","被保人与投保人关系","被保人姓名","被保人证件类型","被保人证件号码","被投保人移动电话","被保人性别","被保人生日","被保人年收入","被保人常驻地址"};
                     List<String> validateMust = ImportUtil.validateMust(insBeanList, filedindexs, filedNames);
                     if (!CollectionUtils.isEmpty(validateMust)){
                           throw new CheckException("投保单号:"+insBeanList.get(1)+",请录入必填项："+validateMust.toString());
                     }

                    String affirmNo = insBeanList.get(0);  // 确认书编号

                    String policyId = insBeanList.get(1); // 投保单号
                    String insuranceCompanyName  = insBeanList.get(2);  //保险公司名称
                    String totalPremium = insBeanList.get(3);//总保费
                    boolean matches = totalPremium.matches(Constants.DATA_FORMAT_NUMBER);
                    if(!matches){
                        throw new CheckException("投保单号:"+policyId+"总保费"+totalPremium+",不为数字");
                    }

                    String proposeDate = insBeanList.get(4);  // 投保日期
                    if (!DateTimeUtil.isLegalDate(proposeDate)){
                        throw new CheckException("投保单号："+policyId+"投保日期格式不正确,请填写yyyy-MM-dd格式的时间");
                    }

                    String empNo = insBeanList.get(5);  // 员工编号
                    String policyHolderName = insBeanList.get(6);  // 投保人姓名
                    String policyHolderSex = insBeanList.get(7);  // 投保人性别
                    for (Map sexDict : sexDictList) {
                        if (policyHolderSex.equals(sexDict.get("dict_name"))) {
                            policyHolderSex = sexDict.get("dict_code").toString();
                            break;
                        }
                    }
                    if (insBeanList.get(7).equals(policyHolderSex)){
                        throw new CheckException("投保单号："+policyId+" 投保人性别不正确,请参考字典值");
                    }
                    String policyHolderCardType = insBeanList.get(8);  // 投保人证件类型
                    for (Map cardDict : cardDictList) {
                        if (policyHolderCardType.equals(cardDict.get("dict_name"))) {
                            policyHolderCardType = cardDict.get("dict_code").toString();
                            break;
                        }
                    }
                    if (insBeanList.get(8).equals(policyHolderCardType)){
                        throw new CheckException("投保单号："+policyId+" 投保人证件类型不正确,请参考字典值");
                    }
                    String policyHolderCardNo = insBeanList.get(9);  // 投保人身份证号
                    boolean regFlag = true;
                    if("1".equals(policyHolderCardType) || "5".equals(policyHolderCardType)){
                        regFlag = policyHolderCardNo.matches(Constants.DATA_FORMAT_IDCARD);
                    }else if("2".equals(policyHolderCardType)){
                        regFlag = policyHolderCardNo.matches(Constants.DATA_FORMAT_HKCARD);
                    }else if("3".equals(policyHolderCardType)){
                        regFlag = policyHolderCardNo.matches(Constants.DATA_FORMAT_PASSPORTCARD);
                    }else if("4".equals(policyHolderCardType)){
                        regFlag = policyHolderCardNo.matches(Constants.DATA_FORMAT_OFFICERCARD);
                    }
                    if(!regFlag){
                        throw new CheckException("投保单号:"+policyId+"总保费"+totalPremium+",证件号格式不正确");
                    }
                    String policyHolderMobile = insBeanList.get(10);  // 投保人联系方式
                    boolean matches1 = policyHolderMobile.matches(Constants.DATA_FORMAT_MOBLIE);
                    if(!matches1){
                        throw new CheckException("投保单号:"+policyId+"投保人联系方式"+policyHolderMobile+",投保人联系方式格式不正确");
                    }
                    String policyHolderBirthday = insBeanList.get(11);  // 投保人生日
                     if (!DateTimeUtil.isLegalDate(policyHolderBirthday)){
                         throw new CheckException("投保单号："+policyId+"投保人出生日期格式不正确,请填写yyyy-MM-dd格式的时间");
                     }
                    String annualIncome_t = insBeanList.get(12);  // 投保人年收入
                    boolean matches2 = annualIncome_t.matches(Constants.DATA_FORMAT_NUMBER);
                    if(!matches2){
                        throw new CheckException("投保单号:"+policyId+"投保人年收入"+annualIncome_t+",不为数字");
                    }
                    String policyHolderAddress = insBeanList.get(13);  // 投保人地址
                    /**投保人新增字段*/
                    String policyHolderPayType = insBeanList.get(14);//付款方式
                    if (StringUtils.isNotEmpty(policyHolderPayType)) {
                            for (Map payTypeDict : payTypeDictList) {
                            if (policyHolderPayType.equals(payTypeDict.get("dict_name"))) {
                                policyHolderPayType = payTypeDict.get("dict_code").toString();
                                break;
                            }
                        }
                        if (insBeanList.get(14).equals(policyHolderPayType)){
                             throw new CheckException("投保单号："+policyId+" 付款方式不正确,请参考字典值");
                            }
                    }
                    String policyHolderAccountHolder = insBeanList.get(15);//投保人开户人姓名
                    String policyHolerdBankName = insBeanList.get(16);//投保人银行名称
                    String policyHolderBankAccount = insBeanList.get(17);//投保人银行账号
                    if(StringUtils.isNotEmpty(policyHolderBankAccount)){
                        boolean matches3 = policyHolderBankAccount.matches(Constants.DATA_FORMAT_NUMBER);
                        if(!matches3){
                            throw new CheckException("投保单号:"+policyId+"投保人银行账号"+policyHolderBankAccount+",不为数字");
                        }
                        if (policyHolderBankAccount.length()>20) {
                            throw new CheckException("投保单号:"+policyId+"投保人银行账号"+policyHolderBankAccount+",超过20位");
                        }
                    }

                    String policyHolderEduBackground = insBeanList.get(18);//投保人学历
                    String policyHolderMaritalStatus = insBeanList.get(19);//投保人婚姻状态
                    String policyHolderCompanyAddress = insBeanList.get(20);//投保人公司地址
                    String policyHolderTelephone = insBeanList.get(21);//投保人办公/家庭电话
                    String policyHolderDomicileAddress = insBeanList.get(22);//投保人户籍地址
                    String policyHolderDebts = insBeanList.get(23);//投保人负债与贷款（万元）
                    String policyHolderOccupationCode = insBeanList.get(24);//投保人执业编号
                    String policyHolderOccupation = insBeanList.get(25);//投保人职业
                    String policyHolderEmail = insBeanList.get(26);//投保人邮箱
                    String policyHolderHomePostalCode = insBeanList.get(27);//投保人家庭住址邮编
                    if (StringUtils.isNotEmpty(policyHolderDebts)) {
                        boolean matches4 = policyHolderDebts.matches(Constants.DATA_FORMAT_NUMBER);
                        if(!matches4){
                            throw new CheckException("投保单号:"+policyId+"投保人负债与贷款（万元）"+policyHolderDebts+",不为数字");
                        }
                    }
                    if (StringUtils.isNotEmpty(policyHolderHomePostalCode)) {
                        boolean matches5 = policyHolderHomePostalCode.matches(Constants.DATA_FORMAT_NUMBER);
                        if(!matches5){
                            throw new CheckException("投保单号:"+policyId+"投保人家庭住址邮编"+policyHolderHomePostalCode+",不为数字");
                        }
                    }
//                    String policyHolderOccupation = insBeanList.get(19);  // 投保人职业
//                    String accountHolder = insBeanList.get(14);  // 开户人姓名
//                    String bankName = insBeanList.get(15);  // 开户行名称
//                    String bankAccount = insBeanList.get(16);  // 开户行账号

                    String relationship = insBeanList.get(28);  // 被保险人与投保人关系
                    for (Map relationshipDict : relationshipDictList) {
                        if (relationship.equals(relationshipDict.get("dict_name"))) {
                            relationship = relationshipDict.get("dict_code").toString();
                            break;
                        }
                    }
                    if (insBeanList.get(28).equals(relationship)){
                        throw new CheckException("投保单号："+policyId+" 被保险人与投保人关系不正确,请参考字典值");
                    }
                    String insuredName = insBeanList.get(29);  // 被保险人姓名

                    String insuredCardType = insBeanList.get(30);  // 被保险人证件类型
                    for (Map cardDict : cardDictList) {
                        if (insuredCardType.equals(cardDict.get("dict_name"))) {
                            insuredCardType = cardDict.get("dict_code").toString();
                            break;
                        }
                    }
                    if (insBeanList.get(30).equals(insuredCardType)){
                        throw  new CheckException("投保单号："+policyId+" 被保险人证件类型,请参考字典值");
                    }
                    String insuredCardNo = insBeanList.get(31);  // 被保险人身份证号
                    boolean regFlag1 = true;
                    if("1".equals(insuredCardType) || "5".equals(insuredCardType)){
                        regFlag1 = insuredCardNo.matches(Constants.DATA_FORMAT_IDCARD);
                    }else if("2".equals(insuredCardType)){
                        regFlag1 = insuredCardNo.matches(Constants.DATA_FORMAT_HKCARD);
                    }else if("3".equals(insuredCardType)){
                        regFlag1 = insuredCardNo.matches(Constants.DATA_FORMAT_PASSPORTCARD);
                    }else if("4".equals(insuredCardType)){
                        regFlag1 = insuredCardNo.matches(Constants.DATA_FORMAT_OFFICERCARD);
                    }
                    if(!regFlag1){
                        throw new CheckException("投保单号:"+policyId+"证件号"+insuredCardNo+",被保险人证件号格式不正确");
                    }

                    String insuredMobile = insBeanList.get(32);  // 被保险人联系电话
                    boolean  insuredmatches1 = insuredMobile.matches(Constants.DATA_FORMAT_MOBLIE);
                    if(!insuredmatches1){
                        throw new CheckException("投保单号:"+policyId+"被保险人联系电话"+insuredMobile+",被保险人联系电话格式不正确");
                    }

                    String insuredSex = insBeanList.get(33);  // 被保险人性别
                    for (Map sexDict : sexDictList) {
                        if (insuredSex.equals(sexDict.get("dict_name"))) {
                            insuredSex = sexDict.get("dict_code").toString();
                            break;
                        }
                    }
                    if (insBeanList.get(33).equals(insuredSex)){
                        throw  new CheckException("投保单号："+policyId+" 被保险人性别,请参考字典值");
                    }
                    String insuredBirthday = insBeanList.get(34);  // 被保险人生日
                   if (!DateTimeUtil.isLegalDate(insuredBirthday)){
                         throw new CheckException("投保单号："+policyId+"被保人出生日期格式不正确,请填写yyyy-MM-dd格式的时间");
                     }
                    String annualIncome_b = insBeanList.get(35);  // 被保人年收入
                    boolean insuredmatches2 = annualIncome_b.matches(Constants.DATA_FORMAT_NUMBER);
                    if(!insuredmatches2){
                        throw new CheckException("投保单号:"+policyId+"投保人年收入"+annualIncome_b+",不为数字");
                    }
                    String insuredAddress = insBeanList.get(36);  // 被保险人地址
                    String isMultipleInsure = insBeanList.get(37);  // 是否多次投保
                    String isInformHealth = insBeanList.get(38);  // 是否有健康须知
                    if(Constants.YES.equals(isMultipleInsure)){
                        isMultipleInsure=Constants.YESCODE;
                    }else{
                        isMultipleInsure=Constants.NOCODE;

                    }
                    if(Constants.YES.equals(isInformHealth)){
                        isInformHealth=Constants.YESCODE;
                    }else{
                        isInformHealth=Constants.NOCODE;

                    }
                    /**被投保人新增字段*/
                    String insEduBackground = insBeanList.get(39);  // 被保人学历
                    String insMaritalStatus = insBeanList.get(40);  // 被保人婚姻状态
                    String insCompanyAddress = insBeanList.get(41);  // 被保人公司地址
                    String insTelephone = insBeanList.get(42);  // 被保人办公/家庭电话
                    String insEmail = insBeanList.get(43);  // 被保人电子邮箱
                    String insHomePostalCode = insBeanList.get(44);  // 被保人家庭地址邮编
                    String insDomicileAddress = insBeanList.get(45);  // 被保人户籍地址
                    String insDebts = insBeanList.get(46);  // 被保人负债与贷款（万元）
                    String insOccupationCode = insBeanList.get(47);  // 被保人执业编号
                    if (StringUtils.isNotEmpty(insDebts)) {
                        boolean insuredmatches3 = insDebts.matches(Constants.DATA_FORMAT_NUMBER);
                        if(!insuredmatches3){
                            throw new CheckException("投保单号:"+policyId+"被保人负债与贷款（万元）"+insDebts+",不为数字");
                        }
                    }
                    if (StringUtils.isNotEmpty(insHomePostalCode)) {
                        boolean insuredmatches4 = insHomePostalCode.matches(Constants.DATA_FORMAT_NUMBER);
                        if(!insuredmatches4){
                            throw new CheckException("投保单号:"+policyId+"被保人家庭地址邮编"+insHomePostalCode+",不为数字");
                        }
                    }


                    String insuredOccupation = insBeanList.get(48);  // 被保险人职业
//                    String staticMonth  = insBeanList.get(28);  // 统计月份
//                    String insuranceCompanyName  = insBeanList.get(29);  //保险公司名称
//                    String totalPremium = insBeanList.get(30);//总保费

                    Map<String, Object> ins_policy_insured_person = Maps.newHashMapWithExpectedSize(16);
//                    ins_policy_insured_person.put("staticMonth",staticMonth);
                    Map<String,Object> emp = (Map<String, Object>) params.get("emp");
                    ins_policy_insured_person.put("policyId", policyId);//保单单号
                    ins_policy_insured_person.put("cardType", insuredCardType);//证件号
                    ins_policy_insured_person.put("cardNo", insuredCardNo);//证件号
                    ins_policy_insured_person.put("name", insuredName);//NAME
                    ins_policy_insured_person.put("relationship", relationship);//与投保人关系
                    ins_policy_insured_person.put("sex", insuredSex);// 性别
                    ins_policy_insured_person.put("birthday", insuredBirthday);// 生日
                    ins_policy_insured_person.put("mobile", insuredMobile);// 联系电话
                    ins_policy_insured_person.put("homeAddress", insuredAddress);// 地址
                    ins_policy_insured_person.put("occupation", insuredOccupation);// 职业
                    ins_policy_insured_person.put("createTime", params.get("now"));//创建时间
                    ins_policy_insured_person.put("createBy", emp.get("employeeId"));//创建人(内勤人员)

//                    ins_policy_insured_person.put("annualIncome","无".equals(annualIncome_b)?null:annualIncome_b);
//                    ins_policy_insured_person.put("isMultipleInsure","无".equals(isMultipleInsure)?null:isMultipleInsure);
//                    ins_policy_insured_person.put("isInformHealth","无".equals(isInformHealth)?null:isInformHealth);
//                    ins_policy_insured_person.put("createBy", emp.get("INSURANCE_SALES_ID"));//创建人
                    ins_policy_insured_person.put("annualIncome",annualIncome_b);
                    ins_policy_insured_person.put("isMultipleInsure",isMultipleInsure);
                    ins_policy_insured_person.put("isInformHealth",isInformHealth);

                    ins_policy_insured_person.put("eduBackground",insEduBackground);// 被保人学历
                    ins_policy_insured_person.put("maritalStatus",insMaritalStatus);// 被保人婚姻状态
                    ins_policy_insured_person.put("companyAddress",insCompanyAddress);// 被保人公司地址
                    ins_policy_insured_person.put("telephone",insTelephone);// 被保人办公/家庭电话
                    ins_policy_insured_person.put("email",insEmail);// 被保人电子邮箱
                    ins_policy_insured_person.put("homePostalCode",insHomePostalCode);// 被保人家庭地址邮编
                    ins_policy_insured_person.put("domicileAddress",insDomicileAddress);// 被保人户籍地址
                    if(insDebts.isEmpty()){
                        ins_policy_insured_person.put("debts",0); // 被保人负债与贷款（万元）
                    }else {
                        ins_policy_insured_person.put("debts",insDebts); // 被保人负债与贷款（万元）
                    }
                    ins_policy_insured_person.put("occupationCode",insOccupationCode);// 被保人执业编号


                    ins_policy_insured_person.put("source", "2");// 来源 2 导入
                    /**
                     * type=1 保单也就是承保清单
                     * type=0 投保单 已经审核移交过的
                     * type = 2 投保单 新录入的投保单
                     * 承保清单导入的数据作为保单数据
                     * 投保单导入作为投保单
                     * 通过Constants.INSURANCE_POLICY_STATUS_1010 控制
                     * */
                    if(Constants.IMPL_TYPE_POLICY.equals(type)){
                        ins_policy_insured_person.put("type", Constants.POLICY_TYPT_2);// 类型 2 保单
                        ins_policy_insured_person.put("insState", Constants.INSURANCE_POLICY_STATUS_1010);// 保单状态
                    }else  if (Constants.IMPL_TYPE_NOTE_OR_ENTRY.equals(type)){
                        ins_policy_insured_person.put("type", Constants.POLICY_TYPT_1);// 类型 1 新保单
                        ins_policy_insured_person.put("state", Constants.POLICY_STATUS_5);// 保单状态
                    }else if(Constants.IMPL_TYPE_NEW_POLICY.equals(type)){
                        ins_policy_insured_person.put("type", Constants.POLICY_TYPT_1);// 类型 1 新保单
                        ins_policy_insured_person.put("state", Constants.POLICY_STATUS_1);// 保单状态
                    }
                    ins_policy_insured_person.put("proposeDate", proposeDate);
                    ins_policy_insured_person.put("insure", proposeDate);// 投保日期
                    ins_policy_insured_person.put("affirmNo", affirmNo);// 生效日期
                    ins_policy_insured_person.put("totalPremium", totalPremium);// 总保费
                    Map<String,Object> pra = new HashMap<String,Object>();
                    pra.put("insuranceSalerNo",empNo);

                    List<InsuranceSalesInfo> insuranceSalesInfos = insuranceSalesInfoClient.insuranceSalesList(pra);
                    if(CollectionUtils.isEmpty(insuranceSalesInfos)){
                        throw new CheckException("投保单号:"+policyId+"未查询到代理人"+empNo);
                    }else{
                        InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfos.get(0);
                        ins_policy_insured_person.put("teamId", insuranceSalesInfo.getSalesTeamId());
                        ins_policy_insured_person.put("orgId",insuranceSalesInfo.getSalesOrgId() );
                        ins_policy_insured_person.put("agentId",insuranceSalesInfo.getInsuranceSalesId());
                        ins_policy_insured_person.put("salesGradeId",insuranceSalesInfo.getSalesGradeId());
                        params.put(policyId+"orgId", insuranceSalesInfo.getSalesOrgId() );//保单号对应的代理人所属组织机构
                    }
                    pra.clear();
                    pra.put("insuranceCompanyName",insuranceCompanyName);
                    Map<String,Object> insDept = insuranceDeptClient.selectOrg(pra);
                    if(MapUtils.isEmpty(insDept)){
                        throw new CheckException("投保单号:"+policyId+"未查询到投保公司"+insuranceCompanyName);
                    }
                    if (policyIdList.contains(policyId)){
                        throw new CheckException("导入数据中,投保单号："+policyId+"存在重复值,请核查!");
                    }
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("id", policyId);
                    String policytype = (String)ins_policy_insured_person.get("type");
                    map.put("type", policytype);
                    InsPolicyInsuredPersonEntity pip = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(map);
                    if(null!=pip){
                         throw new CheckException("投保单号:"+policyId+"，系统中已存在");
                    }
                    Object insurance_company_id = insDept.get("INSURANCE_COMPANY_ID");
                    ins_policy_insured_person.put("insuranceCompanyId",insurance_company_id);
                    params.put(policyId+"insuranceCompanyId", insurance_company_id );//保单号对应的保险公司

                    ins_policy_insured_person_List.add(ins_policy_insured_person);//保单主表List
                    Map<String, Object> ins_policy_holder = Maps.newHashMapWithExpectedSize(13);
                     if(Constants.IMPL_TYPE_POLICY.equals(type)){
                        ins_policy_holder.put("type", Constants.POLICY_TYPT_2);// 类型 2 保单
                    }else  if (Constants.IMPL_TYPE_NOTE_OR_ENTRY.equals(type)){
                        ins_policy_holder.put("type", Constants.POLICY_TYPT_1);// 类型 1 新保单
                    }else if(Constants.IMPL_TYPE_NEW_POLICY.equals(type)){
                        ins_policy_holder.put("type", Constants.POLICY_TYPT_1);// 类型 1 新保单
                    }
                   // ins_policy_holder.put("type", Constants.POLICY_TYPT_2);
                   policyIdList.add(policyId);
                     policyIdSet.add(policyId);
                    ins_policy_holder.put("policyId", policyId);
                    ins_policy_holder.put("name", policyHolderName);
                    ins_policy_holder.put("sex", policyHolderSex);
                    ins_policy_holder.put("cardType", policyHolderCardType);
                    ins_policy_holder.put("cardNo", policyHolderCardNo);
                    ins_policy_holder.put("birthday", policyHolderBirthday);
                    ins_policy_holder.put("accountHolder", policyHolderAccountHolder);
                    ins_policy_holder.put("bankName", policyHolerdBankName);
                    ins_policy_holder.put("bankAccount", policyHolderBankAccount);
                    ins_policy_holder.put("mobile", policyHolderMobile);
                    ins_policy_holder.put("homeAddress",policyHolderAddress);
                    ins_policy_holder.put("occupation", policyHolderOccupation);
                    ins_policy_holder.put("annualIncome",annualIncome_t);

                    ins_policy_holder.put("payType",policyHolderPayType);//
                    ins_policy_holder.put("eduBackground",policyHolderEduBackground);
                    ins_policy_holder.put("maritalStatus",policyHolderMaritalStatus);
                    ins_policy_holder.put("companyAddress",policyHolderCompanyAddress);
                    ins_policy_holder.put("telephone",policyHolderTelephone);
                    ins_policy_holder.put("domicileAddress",policyHolderDomicileAddress);
                    if(policyHolderDebts.isEmpty()){
                        ins_policy_holder.put("debts",0);
                    }else {
                        ins_policy_holder.put("debts",policyHolderDebts);
                    }
                    ins_policy_holder.put("occupationCode",policyHolderOccupationCode);
                    ins_policy_holder.put("email",policyHolderEmail);
                    ins_policy_holder.put("homePostalCode",policyHolderHomePostalCode);
                    ins_policy_holder_List.add(ins_policy_holder);//投保人List
                    policyCodeEmpNo.put(policyId,empNo);

                });

}

    /**
     * 解析承保清单数据导入
     * @param inspolicyRowList
     * @param sexDictList
     * @param cardDictList
     * @param relationshipDictList
     * @param payTypeDictList
     * @param params
     * @param ins_policy_insured_person_List
     * @param ins_policy_holder_List
     * @param policyCodeEmpNo
     * @param type
     */
    private void importInsert (List<List<String>> inspolicyRowList,List<Map<String, Object>> sexDictList, List<Map<String, Object>> cardDictList,
                                        List<Map<String, Object>> relationshipDictList,List<Map<String,Object>> payTypeDictList,Map<String,Object> params,List<Map<String,Object>> ins_policy_insured_person_List,
                                        List<Map<String,Object>> ins_policy_holder_List,Map<String,Object>policyCodeEmpNo,String type,Set<String> policyIdSet){
      //校验唯一性
        ArrayList<String> policyIdList = new ArrayList<>();
         ArrayList<String> correspondingList = new ArrayList<>();
        inspolicyRowList.forEach(insBeanList->{
                     int[] filedindexs = {1,2,3,5,6,7,8,9,10,11,12,13,14,15,16,31,32,33,34,35,36,37,38,39};
                     String[] filedNames = {"保单号","承保日期","生效日期","投保公司","总保费（元）","投保日期","代理人工号","投保人姓名","投保人性别","投保人证件类型","投保人证件号码","投保人移动电话","投保人出生日期","投保人年收入","投保人常驻地址","被保人与投保人关系","被保人姓名","被保人证件类型","被保人证件号码","被投保人移动电话","被保人性别","被保人生日","被保人年收入","被保人常驻地址"};
                     List<String> validateMust = ImportUtil.validateMust(insBeanList, filedindexs, filedNames);
                     if (!CollectionUtils.isEmpty(validateMust)){
                           throw new CheckException("保单号:"+insBeanList.get(1)+",请录入必填项："+validateMust.toString());
                     }

            String affirmNo = insBeanList.get(0).toString();  // 确认书编号
            String policyId = insBeanList.get(1).toString();  // 保单号
            String underwritingData = insBeanList.get(2).toString();  // 承保日期
            if (!DateTimeUtil.isLegalDate(underwritingData)){
                throw new CheckException("保单号:"+insBeanList.get(1)+",承保日期格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            String takeEffectData = insBeanList.get(3).toString();  // 生效日期
            if (!DateTimeUtil.isLegalDate(takeEffectData)){
                throw new CheckException("保单号:"+insBeanList.get(1)+",生效日期格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            String corresponding = insBeanList.get(4).toString(); // 投保单号
            String insuranceCompanyName  = insBeanList.get(5).toString();  //保险公司名称
            String totalPremium =insBeanList.get(6).toString();//总保费
             boolean matches = totalPremium.matches(Constants.DATA_FORMAT_NUMBER);
             if(!matches){
                 throw new CheckException("保单号:"+policyId+"总保费"+totalPremium+",不为数字");
              }
            String proposeDate = insBeanList.get(7).toString();  // 投保日期
             if (!DateTimeUtil.isLegalDate(proposeDate)){
                throw new CheckException("保单号:"+insBeanList.get(1)+",投保日期格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            String empNo = insBeanList.get(8).toString();  // 员工编号
            String policyHolderName = insBeanList.get(9).toString();  // 投保人姓名
            String policyHolderSex = insBeanList.get(10).toString();  // 投保人性别
            for (Map sexDict : sexDictList) {
                if (policyHolderSex.equals(sexDict.get("dict_name"))) {
                    policyHolderSex = sexDict.get("dict_code").toString();
                    break;
                }
            }
            if (insBeanList.get(10).toString().equals(policyHolderSex)){
                 throw new CheckException("保单号:"+insBeanList.get(1)+",投保人性别填写不正确,请参考字典值");
            }

            String policyHolderCardType = insBeanList.get(11).toString();  // 投保人证件类型
            for (Map cardDict : cardDictList) {
                if (policyHolderCardType.equals(cardDict.get("dict_name"))) {
                    policyHolderCardType = cardDict.get("dict_code").toString();
                    break;
                }
            }
             if (insBeanList.get(11).toString().equals(policyHolderCardType)){
                 throw new CheckException("保单号:"+insBeanList.get(1)+",投保人证件类型填写不正确,请参考字典值");
            }
            String policyHolderCardNo = insBeanList.get(12).toString();  // 投保人身份证号
             boolean regFlag = true;
             if("1".equals(policyHolderCardType) || "5".equals(policyHolderCardType)){
                 regFlag = policyHolderCardNo.matches(Constants.DATA_FORMAT_IDCARD);
             }else if("2".equals(policyHolderCardType)){
                 regFlag = policyHolderCardNo.matches(Constants.DATA_FORMAT_HKCARD);
             }else if("3".equals(policyHolderCardType)){
                 regFlag = policyHolderCardNo.matches(Constants.DATA_FORMAT_PASSPORTCARD);
             }else if("4".equals(policyHolderCardType)){
                 regFlag = policyHolderCardNo.matches(Constants.DATA_FORMAT_OFFICERCARD);
             }
             if(!regFlag){
                 throw new CheckException("保单号:"+policyId+"投保人证件号"+totalPremium+",证件号格式不正确");
             }
            String policyHolderMobile = insBeanList.get(13).toString();  // 投保人联系方式
            boolean matches1 = policyHolderMobile.matches(Constants.DATA_FORMAT_MOBLIE);
            if(!matches1){
                throw new CheckException("保单号:"+policyId+"投保人联系方式"+policyHolderMobile+",投保人联系方式格式不正确");
            }
            String policyHolderBirthday = insBeanList.get(14).toString();  // 投保人生日
              if (!DateTimeUtil.isLegalDate(policyHolderBirthday)){
                throw new CheckException("保单号:"+insBeanList.get(1)+",投保人出生日期格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            String annualIncome_t = insBeanList.get(15);  // 投保人年收入
            boolean matches2 = annualIncome_t.matches(Constants.DATA_FORMAT_NUMBER);
            if(!matches2){
                throw new CheckException("保单号:"+policyId+"投保人年收入"+annualIncome_t+",不为数字");
            }
            String policyHolderAddress = insBeanList.get(16).toString();  // 投保人地址
            /**投保人新增字段*/
            String policyHolderPayType = insBeanList.get(17);//付款方式
            if (StringUtils.isNotEmpty(policyHolderPayType)) {
                for (Map payTypeDict : payTypeDictList) {
                    if (policyHolderPayType.equals(payTypeDict.get("dict_name"))) {
                        policyHolderPayType = payTypeDict.get("dict_code").toString();
                        break;
                    }
                }
                if (insBeanList.get(17).equals(policyHolderPayType)){
                    throw new CheckException("保单号:"+insBeanList.get(1)+",付款方式填写不正确,请参考字典值");
                }
            }

            String policyHolderAccountHolder = insBeanList.get(18);//投保人开户人姓名
            String policyHolerdBankName = insBeanList.get(19);//投保人银行名称
            String policyHolderBankAccount = insBeanList.get(20);//投保人银行账号
            if (StringUtils.isNotEmpty(policyHolderBankAccount)) {
                boolean matches3 = policyHolderBankAccount.matches(Constants.DATA_FORMAT_NUMBER);
                if(!matches3){
                    throw new CheckException("保单号:"+policyId+"投保人银行账号"+policyHolderBankAccount+",不为数字");
                }
                if (policyHolderBankAccount.length()>20) {
                    throw new CheckException("保单号:"+policyId+"投保人银行账号"+policyHolderBankAccount+",超过20位");
                }
            }
            String policyHolderEduBackground = insBeanList.get(21);//投保人学历
            String policyHolderMaritalStatus = insBeanList.get(22);//投保人婚姻状态
            String policyHolderCompanyAddress = insBeanList.get(23);//投保人公司地址
            String policyHolderTelephone = insBeanList.get(24);//投保人办公/家庭电话
            String policyHolderDomicileAddress = insBeanList.get(25);//投保人户籍地址
            String policyHolderDebts = insBeanList.get(26);//投保人负债与贷款（万元）
            String policyHolderOccupationCode = insBeanList.get(27);//投保人执业编号
            String policyHolderOccupation = insBeanList.get(28).toString();  // 投保人职业
            String policyHolderEmail = insBeanList.get(29);//投保人邮箱
            String policyHolderHomePostalCode = insBeanList.get(30);//投保人家庭住址邮编
            if (StringUtils.isNotEmpty(policyHolderDebts)) {
                boolean matches4 = policyHolderDebts.matches(Constants.DATA_FORMAT_NUMBER);
                 if(!matches4){
                     throw new CheckException("保单号:"+policyId+"投保人负债与贷款（万元）"+policyHolderDebts+",不为数字");
                 }
            }
            if (StringUtils.isNotEmpty(policyHolderHomePostalCode)) {
                boolean matches5 = policyHolderHomePostalCode.matches(Constants.DATA_FORMAT_NUMBER);
                 if(!matches5){
                     throw new CheckException("保单号:"+policyId+"投保人家庭住址邮编"+policyHolderHomePostalCode+",不为数字");
                 }
            }
            /**被投保人信息*/
            String relationship = insBeanList.get(31).toString();  // 被保险人与投保人关系
            for (Map relationshipDict : relationshipDictList) {
                if (relationship.equals(relationshipDict.get("dict_name"))) {
                    relationship = relationshipDict.get("dict_code").toString();
                    break;
                }
            }
            if (insBeanList.get(31).toString().equals(relationship)){
                throw new CheckException("保单号:"+policyId+"被保险人与投保人关系填写不正确,请参考字典值");
            }
            String insuredName = insBeanList.get(32).toString();  // 被保险人姓名
            String insuredCardType = insBeanList.get(33).toString();  // 被保险人证件类型
            for (Map cardDict : cardDictList) {
                if (insuredCardType.equals(cardDict.get("dict_name"))) {
                    insuredCardType = cardDict.get("dict_code").toString();
                    break;
                }
            }
            if (insBeanList.get(33).toString().equals(insuredCardType)){
                throw new CheckException("保单号:"+policyId+"被保险人证件类型填写不正确,请参考字典值");
            }
            String insuredCardNo = insBeanList.get(34).toString();  // 被保险人身份证号
            boolean regFlag1 = true;
            if("1".equals(insuredCardType) || "5".equals(insuredCardType)){
                regFlag1 = insuredCardNo.matches(Constants.DATA_FORMAT_IDCARD);
            }else if("2".equals(insuredCardType)){
                regFlag1 = insuredCardNo.matches(Constants.DATA_FORMAT_HKCARD);
            }else if("3".equals(insuredCardType)){
                regFlag1 = insuredCardNo.matches(Constants.DATA_FORMAT_PASSPORTCARD);
            }else if("4".equals(insuredCardType)){
                regFlag1 = insuredCardNo.matches(Constants.DATA_FORMAT_OFFICERCARD);
            }
            if(!regFlag1){
                throw new CheckException("保单号:"+policyId+"证件号"+insuredCardNo+",被保险人证件号格式不正确");
            }

            String insuredMobile = insBeanList.get(35).toString();  // 被保险人联系电话
           boolean  insuredmatches1 = insuredMobile.matches(Constants.DATA_FORMAT_MOBLIE);
           if(!insuredmatches1){
               throw new CheckException("保单号:"+policyId+",被保险人联系电话:"+insuredMobile+"格式不正确");
           }
            String insuredSex = insBeanList.get(36).toString();  // 被保险人性别
            for (Map sexDict : sexDictList) {
                if (insuredSex.equals(sexDict.get("dict_name"))) {
                    insuredSex = sexDict.get("dict_code").toString();
                    break;
                }
            }
             if (insBeanList.get(36).toString().equals(insuredSex)){
                throw new CheckException("保单号:"+policyId+"被保人性别填写不正确,请参考字典值");
            }
            String insuredBirthday = insBeanList.get(37).toString();  // 被保险人生日
             if (!DateTimeUtil.isLegalDate(insuredBirthday)){
                throw new CheckException("保单号:"+insBeanList.get(1)+",被保人出生日期格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            String annualIncome_b = insBeanList.get(38);  // 被保人年收入
             boolean insuredmatches2 = annualIncome_b.matches(Constants.DATA_FORMAT_NUMBER);
             if(!insuredmatches2){
                 throw new CheckException("保单号:"+policyId+"被保人年收入"+annualIncome_b+",不为数字");
             }
            String insuredAddress = insBeanList.get(39).toString();  // 被保险人地址
            String isMultipleInsure = insBeanList.get(40);  // 是否多次投保
            String isInformHealth = insBeanList.get(41);  // 是否有健康须知
            if(Constants.YES.equals(isMultipleInsure)){
                isMultipleInsure=Constants.YESCODE;
            }else{
                isMultipleInsure=Constants.NOCODE;

            }
            if(Constants.YES.equals(isInformHealth)){
                isInformHealth=Constants.YESCODE;
            }else{
                isInformHealth=Constants.NOCODE;

            }
            /**被投保人新增字段**/
            String insEduBackground = insBeanList.get(42);  // 被保人学历
            String insMaritalStatus = insBeanList.get(43);  // 被保人婚姻状态
            String insCompanyAddress = insBeanList.get(44);  // 被保人公司地址
            String insTelephone = insBeanList.get(45);  // 被保人办公/家庭电话
            String insEmail = insBeanList.get(46);  // 被保人电子邮箱
            String insHomePostalCode = insBeanList.get(47);  // 被保人家庭地址邮编
            String insDomicileAddress = insBeanList.get(48);  // 被保人户籍地址
            String insDebts = insBeanList.get(49);  // 被保人负债与贷款（万元）
            String insOccupationCode = insBeanList.get(50);  // 被保人执业编号
            String insuredOccupation = insBeanList.get(51);  // 被保险人职业
            if (StringUtils.isNotEmpty(insDebts)) {
                boolean insuredmatches3 = insDebts.matches(Constants.DATA_FORMAT_NUMBER);
                if(!insuredmatches3){
                    throw new CheckException("投保单号:"+policyId+"被保人负债与贷款（万元）"+insDebts+",不为数字");
                }
            }
            if (StringUtils.isNotEmpty(insHomePostalCode)) {
                boolean insuredmatches4 = insHomePostalCode.matches(Constants.DATA_FORMAT_NUMBER);
                if(!insuredmatches4){
                    throw new CheckException("投保单号:"+policyId+"被保人家庭地址邮编"+insHomePostalCode+",不为数字");
                }
            }
//            String accountHolder = insBeanList.get(17).toString();  // 开户人姓名
//            String bankName = insBeanList.get(18).toString();  // 开户行名称
//            String bankAccount = insBeanList.get(19).toString();  // 开户行账号
//            String insuredOccupation = insBeanList.get(30).toString();  // 被保险人职业
//            String staticMonth  = insBeanList.get(31).toString();  // 统计月份



            Map<String, Object> ins_policy_insured_person = Maps.newHashMapWithExpectedSize(12);
//            ins_policy_insured_person.put("staticMonth","无".equals(staticMonth)?null:staticMonth);
            Map<String,Object> emp = (Map<String, Object>) params.get("emp");
            ins_policy_insured_person.put("policyId", policyId );//保单单号
            ins_policy_insured_person.put("cardType", insuredCardType);//证件号
            ins_policy_insured_person.put("cardNo", insuredCardNo );//证件号
            ins_policy_insured_person.put("name", insuredName);//NAME
            ins_policy_insured_person.put("relationship", relationship );//与投保人关系
            ins_policy_insured_person.put("sex",insuredSex );// 性别
            ins_policy_insured_person.put("birthday",insuredBirthday );// 生日
            ins_policy_insured_person.put("mobile",insuredMobile);// 联系电话
            ins_policy_insured_person.put("homeAddress",insuredAddress);// 地址
            ins_policy_insured_person.put("occupation",insuredOccupation );// 职业
            ins_policy_insured_person.put("createTime", params.get("now"));//创建时间
            ins_policy_insured_person.put("createBy", emp.get("employeeId"));//创建人
            ins_policy_insured_person.put("corresponding", corresponding);// 保单关联投保单号
            ins_policy_insured_person.put("type", Constants.POLICY_TYPT_2);// 类型 2 保单
            ins_policy_insured_person.put("annualIncome",annualIncome_b );
            ins_policy_insured_person.put("isMultipleInsure",isMultipleInsure );
            ins_policy_insured_person.put("isInformHealth",isInformHealth );

            ins_policy_insured_person.put("eduBackground",insEduBackground);// 被保人学历
            ins_policy_insured_person.put("maritalStatus",insMaritalStatus);// 被保人婚姻状态
            ins_policy_insured_person.put("companyAddress",insCompanyAddress);// 被保人公司地址
            ins_policy_insured_person.put("telephone",insTelephone);// 被保人办公/家庭电话
            ins_policy_insured_person.put("email",insEmail);// 被保人电子邮箱
            ins_policy_insured_person.put("homePostalCode",insHomePostalCode);// 被保人家庭地址邮编
            ins_policy_insured_person.put("domicileAddress",insDomicileAddress);// 被保人户籍地址
            if(insDebts.isEmpty()){
               ins_policy_insured_person.put("debts",0); // 被保人负债与贷款（万元）
            }else {
                ins_policy_insured_person.put("debts",insDebts); // 被保人负债与贷款（万元）
            }

            ins_policy_insured_person.put("occupationCode",insOccupationCode);// 被保人执业编号

            ins_policy_insured_person.put("source", "2");// 来源 2 导入
            /**
             * 承保清单导入的数据作为保单数据
             * 投保单导入作为投保单
             * 通过Constants.INSURANCE_POLICY_STATUS_1010 控制
             * */
            if("1".equals(type)){
                ins_policy_insured_person.put("insState", Constants.INSURANCE_POLICY_STATUS_1009);// 保单状态
            }else{
                ins_policy_insured_person.put("state", Constants.POLICY_STATUS_5);// 保单状态
            }
            ins_policy_insured_person.put("proposeDate", proposeDate);
            ins_policy_insured_person.put("insure",proposeDate);// 投保日期
            ins_policy_insured_person.put("underwritingData",underwritingData);// 承保日期
            ins_policy_insured_person.put("takeEffectData",takeEffectData );// 生效日期
            ins_policy_insured_person.put("affirmNo",affirmNo );// 生效日期
            ins_policy_insured_person.put("totalPremium",totalPremium );// 总保费
            if(StringUtil.isEmpty(totalPremium)){
                throw new  RuntimeException("totalPremiumIsNullErroe:"+policyId);
            }
            Map<String,Object> pra = new HashMap<String,Object>();
            if(StringUtil.isBlank(empNo)){
                throw new RuntimeException("EmpNoIsNullErroe:"+policyId);
            }else{
                pra.put("insuranceSalerNo",empNo);
            }

            List<InsuranceSalesInfo> insuranceSalesInfos = insuranceSalesInfoClient.insuranceSalesList(pra);
            if(CollectionUtils.isEmpty(insuranceSalesInfos)){
               throw new CheckException("投保单号:"+policyId+"未查询到代理人"+empNo);
//                throw new RuntimeException("InsuranceSalesInfoIsNullErroe:"+policyId);
            }else{
                InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfos.get(0);
                ins_policy_insured_person.put("teamId", insuranceSalesInfo.getSalesTeamId());
                ins_policy_insured_person.put("orgId",insuranceSalesInfo.getSalesOrgId() );
                ins_policy_insured_person.put("agentId",insuranceSalesInfo.getInsuranceSalesId());
                ins_policy_insured_person.put("salesGradeId",insuranceSalesInfo.getSalesGradeId());
                params.put(policyId+"orgId", insuranceSalesInfo.getSalesOrgId() );
//                params.put("orgId", insuranceSalesInfo.getSalesOrgId() );
            }
            pra.clear();
            pra.put("insuranceCompanyName",insuranceCompanyName);
            Map<String,Object> insDept = insuranceDeptClient.selectOrg(pra);
            if (CollUtil.isEmpty(insDept)){
                throw new CheckException("保单号："+policyId+"的投保公司："+insuranceCompanyName+"不存在,请核实！");
            }
            Object insurance_company_id = insDept.get("INSURANCE_COMPANY_ID");
            ins_policy_insured_person.put("insuranceCompanyId",insurance_company_id);
            params.put(policyId+"insuranceCompanyId", insurance_company_id );//保单号对应的保险公司
             if (policyIdList.contains(policyId)){
                throw new CheckException("导入数据中保单号"+policyId+"存在重复值,请核查!");
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id", policyId);
            String policytype = (String)ins_policy_insured_person.get("type");
            map.put("type", policytype);
            InsPolicyInsuredPersonEntity pip = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(map);
            if(null!=pip){
                throw new CheckException("保单号:"+policyId+",系统中已存在");
            }

             if (StrUtil.isNotBlank(corresponding)) {
                 if (correspondingList.contains(corresponding)) {
                     throw new CheckException("导入数据中投保单号" + corresponding + "存在重复值,请核查!");
                 }
                 map.clear();
                 map.put("corresponding", corresponding);
                 map.put("type", policytype);
                 InsPolicyInsuredPersonEntity pipp = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByCorresponding(map);
                 if (null != pipp) {
                     throw new CheckException("投保单号:" + corresponding + ",系统中已存在");
                 }
                 correspondingList.add(corresponding);
             }
            policyIdList.add(policyId);
            policyIdSet.add(policyId);


            ins_policy_insured_person_List.add(ins_policy_insured_person);//保单主表List
            Map<String, Object> ins_policy_holder = Maps.newHashMapWithExpectedSize(13);
            ins_policy_holder.put("type", Constants.POLICY_TYPT_2);
            ins_policy_holder.put("policyId", policyId);
            ins_policy_holder.put("name", policyHolderName );
            ins_policy_holder.put("sex", policyHolderSex );
            ins_policy_holder.put("cardType", policyHolderCardType );
            ins_policy_holder.put("cardNo",  policyHolderCardNo );
            ins_policy_holder.put("birthday",policyHolderBirthday );
            ins_policy_holder.put("accountHolder", policyHolderAccountHolder);
            ins_policy_holder.put("bankName", policyHolerdBankName);
            ins_policy_holder.put("bankAccount",policyHolderBankAccount);
            ins_policy_holder.put("mobile", policyHolderMobile );
            ins_policy_holder.put("homeAddress",policyHolderAddress );
            ins_policy_holder.put("occupation", policyHolderOccupation );
            ins_policy_holder.put("annualIncome",annualIncome_t );

            ins_policy_holder.put("payType",policyHolderPayType);//
            ins_policy_holder.put("eduBackground",policyHolderEduBackground);
            ins_policy_holder.put("maritalStatus",policyHolderMaritalStatus);
            ins_policy_holder.put("companyAddress",policyHolderCompanyAddress);
            ins_policy_holder.put("telephone",policyHolderTelephone);
            ins_policy_holder.put("domicileAddress",policyHolderDomicileAddress);
            if(policyHolderDebts.isEmpty()){
                ins_policy_holder.put("debts",0);
            }else {
                ins_policy_holder.put("debts",policyHolderDebts);
            }

            ins_policy_holder.put("occupationCode",policyHolderOccupationCode);
            ins_policy_holder.put("email",policyHolderEmail);
            ins_policy_holder.put("homePostalCode",policyHolderHomePostalCode);

            ins_policy_holder_List.add(ins_policy_holder);//投保人List
            policyCodeEmpNo.put(policyId,empNo);

        });
    }


public void inputStreamToFile(InputStream ins, File file) throws IOException {
    OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            //os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != os) {
                os.close();
            }
        }
    }





    /**
     * 承保清单导入列表
     */
    @RequestMapping("/insurance_import_page")
    @ResponseBody
    public DataMsg findInsuranceImportPage(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            String type = request.getParameter("type");
            String source = request.getParameter("source");
            params.put("type", type);
            params.put("source", source);
            PageModel model = insPolicyInsuredPersonFeign.getInsurancePolicyList(params);
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("分页查询承保清单导入异常|{}", e);
        }
        return dataMsg;
    }

    /**
     * 删除投保单
     */
    @RequestMapping("/deleteInsByIds")
    @ResponseBody
    public Map<String, Object> deleteInsByIds(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> msg = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        params.put("ids", ids);
        try {
            msg = insPolicyInsuredPersonFeign.deleteInsByIds(params);
        } catch (RuntimeException e) {
            msg.put("code", "400");
            e.printStackTrace();
        }
        return msg;
    }
    /**
     * 查看头版单详情
     */
    @RequestMapping("to_select_policy_by_pid")
    public String toSelectPolicyByPid(Model model, HttpServletRequest request) {
        try{
            String id = request.getParameter("id");
            Map<String, Object> paras = new HashMap<>();
            getAuthDataParams(paras);
            //getWorkGroupParams(paras);
            paras.put("id", id);
            paras.put("type", Constants.POLICY_ATT_TYPE_1);
            List<InsPolicyAttEntity> ipa = insPolicyInsuredPersonFeign.selectInsPolicyAttEntityByKey(paras);
            logger.info("&&&&&&&&&&&&&&&&&&&&&&&&##########################%%%%%%%%%%%%%%%%%%1"+ipa);
            paras.remove("type");
            List<Map<String, Object>> infor = insPolicyInsuredPersonFeign.selectInsInformationEntityByKey(paras);
            logger.info("&&&&&&&&&&&&&&&&&&&&&&&&##########################%%%%%%%%%%%%%%%%%%2"+infor);
            String inforJson = JSONObject.toJSONString(infor);
            String ipaJson = JSONObject.toJSONString(ipa);
            InsPolicyHolderEntity iph = insPolicyInsuredPersonFeign.selectInsPolicyHolderEntityByKey(paras);
            logger.info("&&&&&&&&&&&&&&&&&&&&&&&&##########################%%%%%%%%%%%%%%%%%%3"+iph);
            InsPolicyInsuredPersonEntity pip = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(paras);
            logger.info("&&&&&&&&&&&&&&&&&&&&&&&&##########################%%%%%%%%%%%%%%%%%%4"+pip);
            List<InsPolicyProfitsPersonEntity> ppp = insPolicyInsuredPersonFeign.selectInsPolicyProfitsPersonEntityByKey(paras);
            logger.info("&&&&&&&&&&&&&&&&&&&&&&&&##########################%%%%%%%%%%%%%%%%%%5"+ppp);
            String s = JSONObject.toJSONString(ppp);
            Map<String, Object> ipp = insPolicyInsuredPersonFeign.selectInsPolicyProposalEntityByKey(paras);
            logger.info("&&&&&&&&&&&&&&&&&&&&&&&&##########################%%%%%%%%%%%%%%%%%%6"+ipp);
            Map<String, Object> pie = insPolicyInsuredPersonFeign.selectInsPolicyInsuredExamineByKey(paras);
            logger.info("&&&&&&&&&&&&&&&&&&&&&&&&##########################%%%%%%%%%%%%%%%%%%7"+pie);
            //查询保险公司信息
            Long insuranceCompanyId = Long.valueOf(ipp.get("insurance_company_id").toString());
            paras.clear();
            paras.put("id", insuranceCompanyId);
            InsuranceDept dept = insuranceDeptClient.selectInsuranceBasicDept(paras);
            model.addAttribute("inforJson", inforJson);
            model.addAttribute("ipa", ipa);
            model.addAttribute("ipasize", ipa.size());
            model.addAttribute("iph", iph);
            model.addAttribute("pip", pip);
            model.addAttribute("pie", pie);
            model.addAttribute("ppp", ppp);
            model.addAttribute("pppJson", s);
            model.addAttribute("pppSize", ppp.size());
            model.addAttribute("ipp", ipp);
            model.addAttribute("dept", dept);
            model.addAttribute("ipaJson", ipaJson);
            model.addAttribute("fastInnerUrl",fastInnerUrl);

        }catch (RuntimeException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "insurancePolicy/SelectOneByPid";
    }

    /**
     * <p>跳转客户列表</p>
     */
    @RequestMapping("/customerList")
    public String toCustomerList() {
        return "insurancePolicy/CustomerList";
    }

    /**
     * <p>客户列表</p>
     */
    @RequestMapping(value = "/customerPage", method = RequestMethod.POST)
    @ResponseBody
    public DataMsg findCustomerList(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            Map<String, Object> params = setCustomerParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            params.put("isAdmin", employeeShiro.getEmployeeNo());
            params.put("myDeptNo", employeeShiro.getDeptNo());
            PageModel model = insPolicyInsuredPersonFeign.findCustomerPageByParams(params);
            dataMsg.setTotal(model.getTotalRecords());
            if(!CollectionUtils.isEmpty(model.getList())){
                List<Map<String, Object>> list = model.getList();
                setCardType(list);
            }
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("客户分页查询异常"+e);
        }
        return dataMsg;
    }

    private void setCardType(List<Map<String, Object>> list) {
        List<Map<String, Object>> cardList = systemDictFeignClient.findDictNameByDictType("CARD");
        for(Map map : list){
            cardList.forEach(a->{
                if(a.get("dict_code").equals(map.get("cardType"))){
                    map.put("cardType", a.get("dict_name"));
                }
            });
        }
    }

    private Map<String, Object> setCustomerParams(HttpServletRequest request) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(12);
        String name = request.getParameter("name");
        if (StringUtils.isNotBlank(name)) {
            params.put("name", name);
        }
        String cardNo = request.getParameter("cardNo");
        if (StringUtils.isNotBlank(cardNo)) {
            params.put("cardNo", cardNo);
        }
        String cardType = request.getParameter("cardType");
        if (StringUtils.isNotBlank(cardType)) {
            params.put("cardType", cardType);
        }
        String mobile = request.getParameter("mobile");
        if (StringUtils.isNotBlank(mobile)) {
            params.put("mobile", mobile);
        }
        String saleName = request.getParameter("saleName");
        if (StringUtils.isNotBlank(saleName)) {
            params.put("saleName", saleName);
        }
        String employeeNo = request.getParameter("employeeNo");
        if (StringUtils.isNotBlank(employeeNo)) {
            params.put("employeeNo", employeeNo);
        }
        String salesOrgId = request.getParameter("salesOrgId");
        if (StringUtils.isNotBlank(salesOrgId)) {
            //params.put("salesOrgId", salesOrgId);
            String salesAllOrgs = salesOrgInfoClient.findEmployeeAllOrgs(salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(salesOrgId).get(0).get("SALES_ORG_CODE").toString());
            params.put("salesAllOrgs", salesAllOrgs);
        }
        String salesTeamId = request.getParameter("salesTeamId");
        if (StringUtils.isNotBlank(salesTeamId)) {
            params.put("salesTeamId", salesTeamId);
        }
        String cardNos = request.getParameter("cardNos");
        if (StringUtils.isNotBlank(cardNos)) {
            Object[] list = JSONArray.parseArray(cardNos).toArray();
            StringBuffer buffer = new StringBuffer();
            for(Object o : list){
                buffer.append("'");
                buffer.append(o.toString());
                buffer.append("'");
                buffer.append(",");
            }
            cardNos = buffer.toString();
            params.put("cardNos", cardNos.substring(0, cardNos.length()-1));
        }
        return params;
    }


    @RequestMapping("/customerExport")
    @ResponseBody
    public void exportCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> params = setCustomerParams(request);
            getAuthDataParams(params);
            List<Map<String, Object>> resultList = insPolicyInsuredPersonFeign.findCustomerListByParams(params);
            if(!CollectionUtils.isEmpty(resultList)){
                setCardType(resultList);
            }
            List<Object[]> dataList = Lists.newArrayListWithExpectedSize(resultList.size());
            String title = "客户列表导出-" + DateTimeUtil.getNowDateChinaString();
            Object[] objs = null;
            Map<String, Object> map = null;
            String[] rowsName = new String[]{"序号", "姓名", "证件类型", "证件号码", "手机号", "首次员工姓名","首次员工编号", "首次所属组织机构",
                    "首次所属营销团队", "最新员工姓名", "最新员工编号", "最新所属组织机构", "最新所属营销团队", "创建时间"};
            for (int i = 0; i < resultList.size(); i++) {
                map = resultList.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i + 1;//序号
                objs[1] = map.get("name");
                objs[2] = map.get("cardType");
                objs[3] = map.get("cardNo");
                objs[4] = map.get("mobile");
                objs[5] = map.get("firstSaleName");
                objs[6] = map.get("firstEmployeeNo");
                objs[7] = map.get("firstSalesOrgName");
                objs[8] = map.get("firstSalesTeamName");
                objs[9] = map.get("newSaleName");
                objs[10] = map.get("newEmployeeNo");
                objs[11] = map.get("newSalesOrgName");
                objs[12] = map.get("newSalesTeamName");
                objs[13] = map.get("createdTime");
                dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            ex.exportBigData(response);
        } catch (Exception e) {
            logger.error("导出[客户列表] | 异常"+e);
            try {
            	response.setCharacterEncoding("UTF-8");
            	response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("导出数据异常 请联系管理员");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
    }

    /**
     * <p>查询客户详情</p>
     */
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String findCustomerDetails(Model model, HttpServletRequest request){
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        HashMap<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("id", id);
        //getWorkGroupParams(map);
        getAuthDataParams(map);
        List<Map<String, Object>> cardList = systemDictFeignClient.findDictNameByDictType("CARD");
        List<Map<String, Object>> sexList = systemDictFeignClient.findDictNameByDictType("SEX");
        if("1".equals(type)){
            InsPolicyInsuredPersonEntity insPolicyInsuredPersonEntity = insPolicyInsuredPersonFeign.selectInsPolicyInsuredPersonEntityByKey(map);
            if(null != insPolicyInsuredPersonEntity){
                cardList.forEach(a->{
                    if(a.get("dict_code").equals(insPolicyInsuredPersonEntity.getCardType())){
                        insPolicyInsuredPersonEntity.setCardType(a.get("dict_name").toString());
                    }
                });
                sexList.forEach(a->{
                    if(a.get("dict_code").equals(insPolicyInsuredPersonEntity.getSex())){
                        insPolicyInsuredPersonEntity.setSex(a.get("dict_name").toString());
                    }
                });
                insPolicyInsuredPersonEntity.setAnnualIncome(insPolicyInsuredPersonEntity.getAnnualIncome().setScale(2, BigDecimal.ROUND_HALF_UP));
                model.addAttribute("customer", insPolicyInsuredPersonEntity);
                HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(3);
                params.put("cardNo", insPolicyInsuredPersonEntity.getCardNo());
                params.put("isAdmin", map.get("isAdmin"));
                params.put("myAllOrgIds", map.get("myAllOrgIds"));
                List<Map<String, Object>> resultList = insPolicyInsuredPersonFeign.findCustomerListByParams(params);
                model.addAttribute("sales", resultList.get(0));
            }

        }else if("2".equals(type)){
            InsPolicyHolderEntity insPolicyHolderEntity = insPolicyInsuredPersonFeign.selectInsPolicyHolderEntityByKey(map);
            if(null != insPolicyHolderEntity){
                cardList.forEach(a->{
                    if(a.get("dict_code").equals(insPolicyHolderEntity.getCardType())){
                        insPolicyHolderEntity.setCardType(a.get("dict_name").toString());
                    }
                });
                sexList.forEach(a->{
                    if(a.get("dict_code").equals(insPolicyHolderEntity.getSex())){
                        insPolicyHolderEntity.setSex(a.get("dict_name").toString());
                    }
                });
                HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(3);
                insPolicyHolderEntity.setAnnualIncome(insPolicyHolderEntity.getAnnualIncome().setScale(2, BigDecimal.ROUND_HALF_UP));
                model.addAttribute("customer", insPolicyHolderEntity);
                params.put("cardNo", insPolicyHolderEntity.getCardNo());
                params.put("isAdmin", map.get("isAdmin"));
                params.put("myAllOrgIds", map.get("myAllOrgIds"));
                List<Map<String, Object>> resultList = insPolicyInsuredPersonFeign.findCustomerListByParams(params);
                model.addAttribute("sales", resultList.get(0));
            }
        }
        return "insurancePolicy/Customer";
    }
    //TODO zgw 2020-04-08 涉及到数据权限未修改
@RequestMapping("/getList")
@ResponseBody
    public DataMsg getList (HttpServletRequest request,DataMsg dataMsg){
       try {
           Map<String,Object> params  = getParams(request);
           String principalDeputySign = request.getParameter("principalDeputySign");
           params.put("principalDeputySign",principalDeputySign);
           List<Map<String,Object>> list = insPolicyInsuredPersonFeign.getList(params);
           dataMsg.setRows(list);
           dataMsg.setMessageCode("200");
       }catch (Exception e){
           e.printStackTrace();
           dataMsg.setMessageCode("400");
            logger.info("insurance_policy/getList方法异常");
       }
       return  dataMsg;
}
//public void getWorkGroupParams(Map<String,Object> paras){
//    Subject subject = SecurityUtils.getSubject();
//    Employee employee = (Employee) subject.getPrincipal();
//    String employeeNo = employee.getEmployeeNo();
//    //通过员工编号查询销售人员id
//    paras.put("insuranceSalerNo",employeeNo );
//    if(!"admin".equals(employeeNo)){
//        List<InsuranceSalesInfo> insuranceSalesInfos = insuranceSalesInfoClient.insuranceSalesList(paras);
//        InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfos.get(0);
//        Long insuranceSalesId = insuranceSalesInfo.getInsuranceSalesId();
//        paras.put("insuranceSalesId",insuranceSalesId);
//    }
//    paras.put("admin", employeeNo);
//
//}

    public void getAuthDataParams(Map<String,Object> paras){
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
        //数据权限相关
        paras.put("isAdmin", employee.getEmployeeNo());
        String myDeptNo =  employee.getDeptNo();

        String myAllOrgIds = null;
        if(!"admin".equals(employee.getEmployeeNo())&&!"superAdmin".equals(employee.getEmployeeNo())) {
            myAllOrgIds = salesOrgInfoClient.findEmployeeAllOrgs(myDeptNo);
        }

        paras.put("myAllOrgIds", myAllOrgIds);

    }

    /**
     * 获取保单下发列表展示数据
     * @param list
     */
    private void getSendOrgAndName(List list) {
        StringBuffer employeeIdsBuf = new StringBuffer();
        StringBuffer saleOrgIdsBuf = new StringBuffer();
        String saleOrgIds = null;
        list.forEach(obj ->{
            Map<String, Object> map = (Map<String, Object>)obj;
            String employeeId =  map.get("create_by") == null ? "" : String .valueOf(map.get("create_by"));
            employeeIdsBuf.append(employeeId).append(",");

        });

        String employeeIds = employeeIdsBuf.toString();
        if(employeeIds.lastIndexOf(",") != -1) {
            employeeIds = employeeIds.substring(0, employeeIds.length() -1);
        }
        List<Map<String, Object>> employeeInfoList = employeeFeignClient.getEmployeeInfoByEmployeeIds(employeeIds);
        if(!(employeeInfoList == null || employeeInfoList.isEmpty())) {

            employeeInfoList.forEach(obj ->{
                Map<String, Object> map = (Map<String, Object>)obj;
                String saleOrgId =  map.get("dept_id") == null ? "" : String .valueOf(map.get("dept_id"));
                saleOrgIdsBuf.append(saleOrgId).append(",");

            });
            saleOrgIds = saleOrgIdsBuf.toString();
            if(saleOrgIds.lastIndexOf(",") != -1) {
                saleOrgIds = saleOrgIds.substring(0, saleOrgIds.length() -1);
            }
        }
        List<Map<String, Object>> saleOrgInfoList = salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(saleOrgIds) == null ?
                new ArrayList<>() : salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(saleOrgIds);


        list.forEach(obj ->{
            Map<String, Object> map = (Map<String, Object>)obj;
            for(Map<String, Object> empMap : employeeInfoList) {
                for(Map<String, Object> saleOrgMap : saleOrgInfoList) {
                    if(empMap.get("dept_id") == saleOrgMap.get("SALES_ORG_ID")) {
                        map.put("dept_name", saleOrgMap.get("SALES_ORG_NAME"));
                        break;
                    }
                }
                if(map.get("create_by").equals(String.valueOf(empMap.get("employee_id")))) {
                    map.put("name", empMap.get("name"));
                    break;
                }

            }
            if(map.get("dept_name") == null){
                map.put("dept_name", "-");
            }
        });

    }
}
