package com.hzcf.plantform.controller.Insurance;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.exception.CheckException;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.SystemDictFeignClient;
import com.hzcf.plantform.feign.employeeCode.EmployeeCodeRuleClient;
import com.hzcf.plantform.feign.insurance.*;
import com.hzcf.plantform.feign.insurancePolicy.InsPolicyInsuredPersonFeign;
import com.hzcf.plantform.util.*;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.basic.District;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.EmployeeCodeRulePojo;
import com.hzcf.pojo.insurance.RankSequence;
import com.hzcf.pojo.insurance.SalesGrade;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.SalesTeamInfo;
import com.hzcf.pojo.insurance.sales.*;
import com.hzcf.util.DateUtil;
import com.hzcf.util.StringUtil;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 销售组织管理
 *
 * @author sxm
 */
@Controller
@RequestMapping("/insuranceSalesInfo")
public class InsuranceSalesInfoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InsuranceSalesInfoClient insuranceSalesInfoClient;
    @Autowired
    private RankSequenceClient rankSequenceClient;
    @Autowired
    private SalesGradeClient salesGradeClient;
    @Autowired
    private EmployeeFeignClient employeeFeignClient;
    @Autowired
    private SalesOrgInfoClient salesOrgInfoClient;
    @Autowired
    private SalesTeamInfoClient salesTeamInfoClient;
    @Autowired
    private EmployeeCodeRuleClient employeeCodeRuleClient;
    @Autowired
    private InsPolicyInsuredPersonFeign insPolicyInsuredPersonFeign;
    @Autowired
    private SystemDictFeignClient systemDictFeignClient;
    @Autowired
    private SalerMonthlyRelationClient salerMonthlyRelationClient;

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(HttpServletRequest request,@RequestBody JSONObject  jsonObject){
        Object userParm = jsonObject.get("saleRanks");
        String jsonStr = JSONUtil.toJsonStr(userParm);
        List<Map> maps = JSONArray.parseArray(jsonStr, Map.class);
        List<Object> arrayList = new ArrayList<>();
        maps.forEach(map->{
            Object jobNumber = map.get("salesOrgId");
            arrayList.add(jobNumber);

        });

        return  JSONUtil.toJsonStr(arrayList);
    }

    /**
     * 跳转到列表页面
     */
    @RequestMapping("/toListPath")
    public String toListPath() {
        return "salesTeamInfoPage/insuranceSalesInfoList";
    }

    /**
     * 离职列表页面
     */
    @RequestMapping("/toQuitListPath")
    public String toQuitListPath() {
        return "salesTeamInfoPage/salerQuitList";
    }

    /**
     * 离职列表页面
     */
    @RequestMapping("/toBlackListPath")
    public String toBlackListPath() {
        return "salesTeamInfoPage/salerBlackList";
    }

    /**
     * 员工调整列表页面
     */
    @RequestMapping("/toMoveListPath")
    public String toMoveListPath() {
        return "salesTeamInfoPage/salesMoveList";
    }

    /**
     * 详细页面
     */
    @RequestMapping("/toDetail")
    public String toDetail(Model model, HttpServletRequest request) {
    	String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalesId", insuranceSalesId);
        InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
        paras.clear();
        paras.put("salesId", insuranceSalesInfo.getInsuranceSalesId());
        paras.put("deleteFlag", "0");
        List<SalesTitles> salesTitles = insuranceSalesInfoClient.findSalesTitles(paras);
        List<SalesEduJob> salesEduJobs = insuranceSalesInfoClient.findSalesEduJobs(paras);
        List<SalesRelative> salesRelatives = insuranceSalesInfoClient.findSalesRelatives(paras);
        List<SalesContract> salesContracts = insuranceSalesInfoClient.findSalesContracts(paras);
        List<DirectorAllowanceStandardPojo> zjjtList = insuranceSalesInfoClient.findZjjt(paras);
        model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);
        model.addAttribute("salesTitles", JSONObject.toJSONString(salesTitles));
        model.addAttribute("salesEduJobs", JSONObject.toJSONString(salesEduJobs));
        model.addAttribute("salesRelatives", JSONObject.toJSONString(salesRelatives));
        model.addAttribute("salesContracts", JSONObject.toJSONString(salesContracts));
        model.addAttribute("zjjtList", JSONObject.toJSONString(zjjtList));
        return "salesTeamInfoPage/insuranceSalesInfoView";
    }

    /**
     * 关系
     */
    @RequestMapping("/toShip")
    public String toShip() {
        return "salesTeamInfoPage/salesShip";
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/toAddPath")
    public String toAddPath(Model model, HttpServletRequest request) {
         String salesTeamId = request.getParameter("id");
         if (StringUtil.isNotBlanks(salesTeamId) && !"-1".equals(salesTeamId)) {
             Map<String, Object> map = new HashMap<>();
             map.put("salesTeamId", salesTeamId);
             SalesTeamInfo salesTeamInfo = salesTeamInfoClient.selectById(map);
             model.addAttribute("salesTeamId", salesTeamId);
             model.addAttribute("salesOrgId", salesTeamInfo.getSalesOrgId());
         }
        return "salesTeamInfoPage/insuranceSalesInfoAdd";
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/toUpdatePath")
    public String toUpdatePath(Model model, HttpServletRequest request) {
        String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalesId", insuranceSalesId);
        InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
        paras.clear();
        paras.put("salesId", insuranceSalesInfo.getInsuranceSalesId());
        paras.put("deleteFlag", "0");
        List<SalesTitles> salesTitles = insuranceSalesInfoClient.findSalesTitles(paras);
        List<SalesEduJob> salesEduJobs = insuranceSalesInfoClient.findSalesEduJobs(paras);
        List<SalesRelative> salesRelatives = insuranceSalesInfoClient.findSalesRelatives(paras);
        List<SalesContract> salesContracts = insuranceSalesInfoClient.findSalesContracts(paras);
        List<DirectorAllowanceStandardPojo> zjjtList = insuranceSalesInfoClient.findZjjt(paras);
        model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);
        model.addAttribute("salesTitles", JSONObject.toJSONString(salesTitles));
        model.addAttribute("salesEduJobs", JSONObject.toJSONString(salesEduJobs));
        model.addAttribute("salesRelatives", JSONObject.toJSONString(salesRelatives));
        model.addAttribute("salesContracts", JSONObject.toJSONString(salesContracts));
        model.addAttribute("zjjtList", JSONObject.toJSONString(zjjtList));
        return "salesTeamInfoPage/insuranceSalesInfoUpdate";
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/toQuit")
    public String toQuit(Model model, HttpServletRequest request) {
        String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalesId", insuranceSalesId);
        InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
        model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);
        return "salesTeamInfoPage/salerQuit";
    }


    /**
     * 跳转到新增页面
     */
    @RequestMapping("/quitDetail")
    public String quitDetail(Model model, HttpServletRequest request) {
        String salerId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("salerId", salerId);
        Map<String, Object> salerQuit = insuranceSalesInfoClient.selectQuit(paras);
        model.addAttribute("salerQuit", salerQuit);
        return "salesTeamInfoPage/salerQuitDetail";
    }

    /**
     * 跳转到黑名单
     */
    @RequestMapping("/toBlack")
    public String toBlack(Model model, HttpServletRequest request) {
        String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalesId", insuranceSalesId);
        InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
        model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);
        return "salesTeamInfoPage/salerBlack";
    }

    /**
     * 跳转到异动页面
     */
    @RequestMapping("/toMove")
    public String toMove(Model model, HttpServletRequest request) {
        String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalesId", insuranceSalesId);
       // InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
        List<Map<String,Object>> insuranceSalesInfos= insuranceSalesInfoClient.findSalesInfoMessage(paras);
        model.addAttribute("insuranceSalesInfo", insuranceSalesInfos.get(0));
        return "salesTeamInfoPage/salerMove";
    }

    public static void main(String[] args) {
        String a = "0000";
        System.out.println(Long.parseLong(a));
    }

    /**
     * 增加
     */
    @RequiresPermissions("insuranceSalesInfo:add")//权限管理;
    @RequestMapping("/addInsuranceSales")
    @ResponseBody
    public DataMsg addInsuranceSales(HttpServletRequest request, InsuranceSalesInfo insuranceSalesInfo,String directDeptB,String directGroupC, DataMsg msg) {
        try {
        	DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String titleList = request.getParameter("titleList");
            String eduJobsList = request.getParameter("eduJobsList");
            String relativeList = request.getParameter("relativeList");
            String contractList = request.getParameter("contractList");
            String zjjtList = request.getParameter("zjjtList");

            Map<String, Object> paras = new HashMap<>();
            //暂时去掉网际关系字段
           /* paras.put("insuranceSalesId", insuranceSalesInfo.getTjSalesId());
            InsuranceSalesInfo pinsuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            paras.clear();
            paras.put("pTreeCode", pinsuranceSalesInfo.getNowTreeCode());
            paras.put("treeCodeLen", pinsuranceSalesInfo.getNowTreeCode().length() + 4);
            int preTreeCode = insuranceSalesInfoClient.findMaxTreeCode(paras);
            if (preTreeCode == 0) {
                insuranceSalesInfo.setNowTreeCode(pinsuranceSalesInfo.getNowTreeCode() + "1000");
            } else {
                insuranceSalesInfo.setNowTreeCode(pinsuranceSalesInfo.getNowTreeCode() + (preTreeCode + 1) + "");
            }*/
            String mobile = insuranceSalesInfo.getMobile();
            paras.put("mobile", mobile);
            int num1 = insuranceSalesInfoClient.findMaxSalerNo(paras);
            if(num1>0){
                msg.setMessageCode("200001");
                msg.setData("手机号已存在");
                return msg;
            }
            /*生成工号*/
            String insuranceSalerNo = this.creatSalerNo(insuranceSalesInfo.getSalesOrgId(), mobile,null);
            /*校验工号是否存在*/
            paras.clear();
            paras.put("insuranceSalerNo", insuranceSalerNo);
            int haveNum = insuranceSalesInfoClient.findMaxSalerNo(paras);
            if (haveNum < 1) {
                insuranceSalesInfo.setInsuranceSalerNo(insuranceSalerNo);
            } else {
                insuranceSalerNo = toCheck(insuranceSalerNo);
                insuranceSalesInfo.setInsuranceSalerNo(insuranceSalerNo);
            }
            paras.clear();
            paras.put("cardNo",insuranceSalesInfo.getCardNo());
            int num = insuranceSalesInfoClient.findMaxSalerNo(paras);
            if(num>0){
                msg.setMessageCode("200001");
                msg.setData("证件号码已存在");
                return msg;
            }
            paras.clear();


            insuranceSalesInfo.setCreatedTime(new Date());
            insuranceSalesInfo.setPassDate(insuranceSalesInfo.getJoinDate());
            insuranceSalesInfo.setSalesStatus("0");
            int salesId = insuranceSalesInfoClient.addInsuranceSales(insuranceSalesInfo);
            //TODO 销售人员不再同步到t_employee表
//            Employee employee = new Employee();
//            employee.setName(insuranceSalesInfo.getInsuranceSaler());
//            employee.setSex(insuranceSalesInfo.getSex());
//            employee.setEmployeeNo(insuranceSalesInfo.getInsuranceSalerNo());
//            //employee.setEmail(insuranceSalesInfo.getEmail());
//            employee.setDeptId(1);
//            employee.setPassword("123456");
//            employee.setMobile(insuranceSalesInfo.getMobile());
//            //employee.setCardNo(insuranceSalesInfo.getCardNo());
//            String uuid = UUIDUtil.getUUID();
//            String newPs = new SimpleHash("MD5", employee.getPassword(), employee.getEmployeeNo() + uuid, 2).toHex();
//            Subject subject = SecurityUtils.getSubject();
//            Employee employeeShiro = (Employee) subject.getPrincipal();
//
//            employee.setEntryTime(date.parse(insuranceSalesInfo.getJoinDate()));
//            employee.setOperator(employeeShiro.getEmployeeId());
//            employeeFeignClient.saveEmployee(employee, newPs, uuid);

            if (StringUtil.isNotBlank(zjjtList)) {
                List<DirectorAllowanceStandardPojo> zjjtLists = toList(zjjtList, DirectorAllowanceStandardPojo.class);
                if (zjjtLists.size() > 0) {
                    for (DirectorAllowanceStandardPojo directorAllowanceStandardPojo : zjjtLists) {
                        directorAllowanceStandardPojo.setInsSalesId(Long.valueOf(salesId));
                        directorAllowanceStandardPojo.setDeleteFlag("0");
                    }
                    insuranceSalesInfoClient.addZjjt(zjjtLists);
                }
            }

            if (StringUtil.isNotBlank(titleList)) {
                List<SalesTitles> salesTitles = toList(titleList, SalesTitles.class);
                if (salesTitles.size() > 0) {
                    for (SalesTitles salesTitle : salesTitles) {
                        salesTitle.setDeleteFlag("0");
                        salesTitle.setSalesId(Long.valueOf(salesId));
                    }
                    insuranceSalesInfoClient.addSalesTitle(salesTitles);
                }
            }

            if (StringUtil.isNotBlank(eduJobsList)) {
                List<SalesEduJob> salesEduJobs = toList(eduJobsList, SalesEduJob.class);
                if (salesEduJobs.size() > 0) {
                    for (SalesEduJob salesEduJob : salesEduJobs) {
                        salesEduJob.setDeleteFlag("0");
                        salesEduJob.setSalesId(Long.valueOf(salesId));
                    }
                    insuranceSalesInfoClient.addSalesEduJob(salesEduJobs);
                }
            }

            if (StringUtil.isNotBlank(relativeList)) {
                List<SalesRelative> salesRelatives = toList(relativeList, SalesRelative.class);
                if (salesRelatives.size() > 0) {
                    for (SalesRelative salesRelative : salesRelatives) {
                        salesRelative.setDeleteFlag("0");
                        salesRelative.setSalesId(Long.valueOf(salesId));
                    }
                    insuranceSalesInfoClient.addSalesRelative(salesRelatives);
                }
            }

            if (StringUtil.isNotBlank(contractList)) {
                List<SalesContract> salesContracts = toList(contractList, SalesContract.class);
                if (salesContracts.size() > 0) {
                    for (SalesContract salesContract : salesContracts) {
                        salesContract.setDeleteFlag("0");
                        salesContract.setSalesId(Long.valueOf(salesId));
                    }
                    insuranceSalesInfoClient.addSalesContract(salesContracts);
                }
            }

            msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            logger.info("保险组织架构[新增]异常",e);
        }
        return msg;
    }

    public String toCheck(String insuranceSalerNo) {
        insuranceSalerNo = ((Long.valueOf(insuranceSalerNo) + 1) + "").replaceAll("4", "5");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalerNo", insuranceSalerNo);
        int haveNum = insuranceSalesInfoClient.findMaxSalerNo(paras);
        if (haveNum < 1) {
            return insuranceSalerNo;
        } else {
            insuranceSalerNo = toCheck(insuranceSalerNo);
        }
        return insuranceSalerNo;
    }

    public String toCont(String insuranceSalerNo,List<String> salesInfoNos){
        insuranceSalerNo = ((Long.valueOf(insuranceSalerNo) + 1) + "").replaceAll("4", "5");
        if(!salesInfoNos.contains(insuranceSalerNo)){
            return insuranceSalerNo;
        }else {
            insuranceSalerNo = toCont(insuranceSalerNo, salesInfoNos);

        }
        return insuranceSalerNo;
    }

    public static List toList(String s, Class clazz) {
        List<Class> list = JSONArray.parseArray(s, clazz);
        return list;
    }

    public String creatSalerNo(Long salesOrgId, String mobile, Map<Long, Long> map){
        //生成员工编号，判断是否存在汇康数据
        String employeeNo  = insuranceSalesInfoClient.searchHKSalerData(mobile);
        if(StringUtil.isNotBlanks(employeeNo)){
            return  employeeNo;
        }
        String insuranceSalerNo = "";
        if(map != null && map.get(salesOrgId) !=null) {
         insuranceSalerNo = String.valueOf(map.get(salesOrgId)+1).replaceAll("4", "5");
        }else {
            Map<String, Object> paras = new HashMap<>();
            paras.put("salesOrgId", salesOrgId);
            SalesOrgInfo salesOrgInfo = salesOrgInfoClient.selectById(paras);
        /*机构级别编号*/
            String orgLevel = salesOrgInfo.getSalesOrgLevel();
            if (orgLevel.equals("01")) {
                insuranceSalerNo = insuranceSalerNo + "1";
            } else if (orgLevel.equals("02")) {
                insuranceSalerNo = insuranceSalerNo + "8";
            } else if (orgLevel.equals("03")) {
                insuranceSalerNo = insuranceSalerNo + "6";
            } else {
                insuranceSalerNo = insuranceSalerNo + "9";
            }
        /*省份编号*/
            String sheng = salesOrgInfo.getAreaCode().substring(0, 6);
            EmployeeCodeRulePojo employeeCodeRule = employeeCodeRuleClient.findEmployeeCodeRule(sheng, Byte.valueOf("1"));
            if (employeeCodeRule != null && StringUtil.isNotBlank(employeeCodeRule.getCode())) {
                insuranceSalerNo = insuranceSalerNo + employeeCodeRule.getCode();
            } else {
                insuranceSalerNo = insuranceSalerNo + "00";
            }
        /*机构设立编号*/
            String orgDcode = "";
            EmployeeCodeRulePojo employeeCodeRule1 = employeeCodeRuleClient.findEmployeeCodeRule(salesOrgId.toString(), Byte.valueOf("2"));
            if (employeeCodeRule1 != null && StringUtil.isNotBlank(employeeCodeRule1.getCode())) {
                insuranceSalerNo = insuranceSalerNo + employeeCodeRule1.getCode();
            } else {
                insuranceSalerNo = insuranceSalerNo + "00";
            }

       /* int wwbh = insuranceSalesInfoClient.findMaxSalerNo(paras);
        insuranceSalerNo = insuranceSalerNo + ((wwbh + 100001) + "").substring(1, 6);
        insuranceSalerNo = insuranceSalerNo.replaceAll("4", "5");*/

        /*以上方法改为查找该机构下最大的员工编号后五位 前面加1  */
            paras.put("prefixInsuranceSalerNo", insuranceSalerNo);

            Long maxSalerNo = insuranceSalesInfoClient.findMaxSalerNoForOrg(paras);
            insuranceSalerNo = insuranceSalerNo + String.format("%05d", (maxSalerNo + 1));
            insuranceSalerNo = insuranceSalerNo.replaceAll("4", "5");
        }
        if (map != null){
            map.put(salesOrgId,Long.valueOf(insuranceSalerNo));
        }
        return insuranceSalerNo;
    }

    /**
     * 详细
     */
    @RequiresPermissions("insuranceSalesInfo:detail")//权限管理;
    @RequestMapping("/selectShipByNo")
    @ResponseBody
    public DataMsg selectShipByNo(HttpServletRequest request, DataMsg msg) {
        Map<String, Object> paras = new HashMap<>();
        try {           
            String dbSalesId = request.getParameter("dbSalesId");
            String tjSalesId = request.getParameter("tjSalesId");
            String ycCFirstGenerId = request.getParameter("ycCFirstGenerId");
            String ycCSecondGenerId = request.getParameter("ycCSecondGenerId");
            String ycBFirstGenerId = request.getParameter("ycBFirstGenerId");
            String ycBSeconGenerId = request.getParameter("ycBSeconGenerId");
            String sjSalesId = request.getParameter("sjSalesId");
            String jcSalesId = request.getParameter("jcSalesId");
            String fdSalesId = request.getParameter("fdSalesId");
            String directGroupCId = request.getParameter("directGroupCId");
            String directDeptBId = request.getParameter("directDeptBId");
            paras.put("dbSalesId", dbSalesId);
            paras.put("tjSalesId", tjSalesId);
            paras.put("ycCFirstGenerId", ycCFirstGenerId);
            paras.put("ycCSecondGenerId", ycCSecondGenerId);
            paras.put("ycBFirstGenerId", ycBFirstGenerId);
            paras.put("ycBSeconGenerId", ycBSeconGenerId);
            paras.put("sjSalesId", sjSalesId);
            paras.put("jcSalesId", jcSalesId);
            paras.put("fdSalesId", fdSalesId);
            paras.put("directGroupCId", directGroupCId);
            paras.put("directDeptBId", directDeptBId);
            List<Object> maps = findShips(paras);
            msg.setRows(maps);
            msg.setMessageCode("200");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return msg;
    }
    
    public List<Object> findShips(Map<String, Object> paras){
        String dbSalesId = paras.get("dbSalesId")+"";
        String tjSalesId = paras.get("tjSalesId")+"";
        String ycCFirstGenerId = paras.get("ycCFirstGenerId")+"";
        String ycCSecondGenerId = paras.get("ycCSecondGenerId")+"";
        String ycBFirstGenerId = paras.get("ycBFirstGenerId")+"";
        String ycBSeconGenerId = paras.get("ycBSeconGenerId")+"";
        String sjSalesId = paras.get("sjSalesId")+"";
        String jcSalesId = paras.get("jcSalesId")+"";
        String fdSalesId = paras.get("fdSalesId")+"";
        String directGroupCId = paras.get("directGroupCId")+"";
        String directDeptBId = paras.get("directDeptBId")+"";
    	String insuranceSalesIds = "";
        if (StringUtil.isNotBlank(dbSalesId)) {
            insuranceSalesIds = insuranceSalesIds + dbSalesId + ",";
        }
        if (StringUtil.isNotBlank(tjSalesId)) {
            insuranceSalesIds = insuranceSalesIds + tjSalesId + ",";
        }
        if (StringUtil.isNotBlank(ycCFirstGenerId)) {
            insuranceSalesIds = insuranceSalesIds + ycCFirstGenerId + ",";
        }
        if (StringUtil.isNotBlank(ycCSecondGenerId)) {
            insuranceSalesIds = insuranceSalesIds + ycCSecondGenerId + ",";
        }
        if (StringUtil.isNotBlank(ycBFirstGenerId)) {
            insuranceSalesIds = insuranceSalesIds + ycBFirstGenerId + ",";
        }
        if (StringUtil.isNotBlank(ycBSeconGenerId)) {
            insuranceSalesIds = insuranceSalesIds + ycBSeconGenerId + ",";
        }
        if (StringUtil.isNotBlank(sjSalesId)) {
            insuranceSalesIds = insuranceSalesIds + sjSalesId + ",";
        }
        if (StringUtil.isNotBlank(jcSalesId)) {
            insuranceSalesIds = insuranceSalesIds + jcSalesId + ",";
        }
        if (StringUtil.isNotBlank(fdSalesId)) {
            insuranceSalesIds = insuranceSalesIds + fdSalesId + ",";
        }
        if (StringUtil.isNotBlank(directGroupCId)) {
            insuranceSalesIds = insuranceSalesIds + directGroupCId + ",";
        }
        if (StringUtil.isNotBlank(directDeptBId)) {
            insuranceSalesIds = insuranceSalesIds + directDeptBId + ",";
        }
        if (insuranceSalesIds.endsWith(",")) {
            insuranceSalesIds = insuranceSalesIds.substring(0, insuranceSalesIds.length() - 1);
        }
        paras.clear();
        paras.put("insuranceSalesIds", insuranceSalesIds);    	
    	List<Object> maps = new ArrayList<Object>();
    	List<InsuranceSalesInfo> salesInfos = new ArrayList<InsuranceSalesInfo>();
        salesInfos = insuranceSalesInfoClient.insuranceSalesList(paras);
        InsuranceSalesInfo salesinfoDb = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoTj = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoYcCF = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoYcCS = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoYcBF = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoYcBS = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoSj = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoJc = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoFd = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoCz = new InsuranceSalesInfo();
        InsuranceSalesInfo salesinfoBz = new InsuranceSalesInfo();
        paras.clear();
        if (salesInfos.size() > 0) {
             for (int i = 0; i < salesInfos.size(); i++) {
                 InsuranceSalesInfo salesinfoi = salesInfos.get(i);
                 if (salesinfoi.getInsuranceSalesId().toString().equals(dbSalesId)) {
                     salesinfoDb = salesinfoi;
                     paras.put("salesinfoDb", salesinfoDb);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(tjSalesId)) {
                     salesinfoTj = salesinfoi;
                     paras.put("salesinfoTj", salesinfoTj);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(ycCFirstGenerId)) {
                     salesinfoYcCF = salesinfoi;
                     paras.put("salesinfoYcCF", salesinfoYcCF);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(ycCSecondGenerId)) {
                     salesinfoYcCS = salesinfoi;
                     paras.put("salesinfoYcCS", salesinfoYcCS);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(ycBFirstGenerId)) {
                     salesinfoYcBF = salesinfoi;
                     paras.put("salesinfoYcBF", salesinfoYcBF);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(ycBSeconGenerId)) {
                     salesinfoYcBS = salesinfoi;
                     paras.put("salesinfoYcBS", salesinfoYcBS);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(sjSalesId)) {
                     salesinfoSj = salesinfoi;
                     paras.put("salesinfoSj", salesinfoSj);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(jcSalesId)) {
                     salesinfoJc = salesinfoi;
                     paras.put("salesinfoJc", salesinfoJc);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(fdSalesId)) {
                     salesinfoFd = salesinfoi;
                     paras.put("salesinfoFd", salesinfoFd);
                 }
                 if (salesinfoi.getInsuranceSalesId().toString().equals(directGroupCId)) {
                     salesinfoCz = salesinfoi;
                     paras.put("salesinfoCz", salesinfoCz);
                 }
                  if (salesinfoi.getInsuranceSalesId().toString().equals(directDeptBId)) {
                     salesinfoBz = salesinfoi;
                     paras.put("salesinfoBz", salesinfoBz);
                 }
             }
        }    	    	
        maps.add(paras);
    	return maps;
    }

    /**
     * 增加部门
     */
    @RequiresPermissions("insuranceSalesInfo:update")//权限管理;
    @RequestMapping("/updateInsuranceSales")
    @ResponseBody
    public DataMsg updateInsuranceSales(HttpServletRequest request, InsuranceSalesInfo insuranceSalesInfo, DataMsg msg) {
        try {
            Map<String, Object> map = new HashMap<>();
            Long salesId = insuranceSalesInfo.getInsuranceSalesId();
            map.put("insuranceSalesId", salesId);
            InsuranceSalesInfo preInsuranceSalesInfo = insuranceSalesInfoClient.selectById(map);
            if (!preInsuranceSalesInfo.getInsuranceSalerNo().equals(insuranceSalesInfo.getInsuranceSalerNo())) {
                Map<String, Object> paras = new HashMap<>();
                paras.put("insuranceSalerNo", insuranceSalesInfo.getInsuranceSalerNo());
                int haveNum = insuranceSalesInfoClient.findMaxSalerNo(paras);
                if (haveNum > 0) {
                    /*自动重置*/
        			/*String insuranceSalerNo = toCheck(insuranceSalesInfo.getInsuranceSalerNo());
        			insuranceSalesInfo.setInsuranceSalerNo(insuranceSalerNo);*/

                    msg.setMessageCode("999");
                    msg.setRequestState("目标员工工号已存在，请重新修改!");
                    return msg;
                }
                //TODO 销售人员不再同步到t_employee表
//                Map<String, Object> emap = new HashMap<>();
//                emap.put("employeeNo", preInsuranceSalesInfo.getInsuranceSalerNo());
//                Employee employee = employeeFeignClient.getEmployeeByEmployeeNo(emap);
//                employee.setEmployeeNo(insuranceSalesInfo.getInsuranceSalerNo());
//                String newPs = new SimpleHash("MD5", "123456", employee.getEmployeeNo() + employee.getSalt(), 2).toHex();
//                employee.setIsResetPwd("1");
//                employeeFeignClient.updateEmployee(employee, newPs);
            }
            if(!preInsuranceSalesInfo.getCardNo().equals(insuranceSalesInfo.getCardNo())){
                Map<String, Object> paras = new HashMap<>();
                paras.put("cardNo",insuranceSalesInfo.getCardNo());
                int num = insuranceSalesInfoClient.findMaxSalerNo(paras);
                if(num>0){
                    msg.setMessageCode("200001");
                    msg.setData("证件号码已存在");
                    return msg;
                }
                paras.clear();
            }
            if(!preInsuranceSalesInfo.getMobile().equals(insuranceSalesInfo.getMobile())) {
                Map<String, Object> paras = new HashMap<>();
                paras.put("mobile", insuranceSalesInfo.getMobile());
                int num1 = insuranceSalesInfoClient.findMaxSalerNo(paras);
                if (num1 > 0) {
                    msg.setMessageCode("200001");
                    msg.setData("手机号已存在");
                    return msg;
                }
                paras.clear();
                //判断是否在汇康存在
                String employeeNo = insuranceSalesInfoClient.searchHKSalerData(insuranceSalesInfo.getMobile());
                if (StringUtil.isNotBlanks(employeeNo) && !employeeNo.equals(insuranceSalesInfo.getInsuranceSalerNo())) {
                    msg.setMessageCode("200001");
                    msg.setData("手机号在汇康已存在,对应的销售人员是："+employeeNo);
                    return msg;
                }
            }
            insuranceSalesInfo.setUpdatedTime(new Date());
             //开始保存修改日志表
          //  saveSalerRelationChange(preInsuranceSalesInfo,insuranceSalesInfo);
            //判读是否同步给汇康
            boolean isNoticy = false;
            if (!insuranceSalesInfo.getMobile().equals(preInsuranceSalesInfo.getMobile()) || !insuranceSalesInfo.getCardNo().equals(preInsuranceSalesInfo.getCardNo()) || !insuranceSalesInfo.getInsuranceSaler().equals(preInsuranceSalesInfo.getInsuranceSaler()) || !insuranceSalesInfo.getSalesOrgId().equals(preInsuranceSalesInfo.getSalesOrgId()) ){
                insuranceSalesInfo.setSalesStatus(preInsuranceSalesInfo.getSalesStatus());
                isNoticy = true;
            }
            insuranceSalesInfoClient.updateInsuranceSales(insuranceSalesInfo,isNoticy);

            String titleList = request.getParameter("titleList");
            String eduJobsList = request.getParameter("eduJobsList");
            String relativeList = request.getParameter("relativeList");
            String contractList = request.getParameter("contractList");
            String zjjtList = request.getParameter("zjjtList");


            if (StringUtil.isNotBlank(zjjtList)) {
                List<DirectorAllowanceStandardPojo> zjjtLists = toList(zjjtList, DirectorAllowanceStandardPojo.class);
                List<DirectorAllowanceStandardPojo> zjjtListsAdd = new ArrayList<DirectorAllowanceStandardPojo>();
                List<DirectorAllowanceStandardPojo> zjjtListsUpd = new ArrayList<DirectorAllowanceStandardPojo>();
                String updateIds = "";
                for (DirectorAllowanceStandardPojo zjjt : zjjtLists) {
//                    zjjt.setInsSalesId(Long.valueOf(salesId));
                    zjjt.setInsSalesId(salesId);
                    if (zjjt.getAllowanceId() != null) {
                        zjjt.setDeleteFlag("0");
                        zjjtListsAdd.add(zjjt);
                        updateIds = updateIds + zjjt.getAllowanceId() + ",";
                    } else {
                    	zjjt.setDeleteFlag("0");
                        zjjtListsAdd.add(zjjt);
                    }
                }
                //修改titles
                if (zjjtListsUpd.size() > 0) {
                    insuranceSalesInfoClient.updateZjjts(zjjtListsUpd);

                }
                //将被删除的titles删除
                Map<String, Object> paras = new HashMap<>();
                if (updateIds.endsWith(",")) {
                    updateIds = updateIds.substring(0, updateIds.length() - 1);
                }
                paras.put("updateIds", updateIds);
                paras.put("insSalesId", salesId);
                insuranceSalesInfoClient.deleteZjjtByIds(paras);
                //新增新加入的titles
                if (zjjtListsAdd.size() > 0) {
                    insuranceSalesInfoClient.addZjjt(zjjtListsAdd);
                }

            }


            if (StringUtil.isNotBlank(titleList)) {
                List<SalesTitles> salesTitles = toList(titleList, SalesTitles.class);
                List<SalesTitles> salesTitlesAdd = new ArrayList<SalesTitles>();
                List<SalesTitles> salesTitlesUpd = new ArrayList<SalesTitles>();
                String updateIds = "";
                for (SalesTitles salesTitle : salesTitles) {
//                    salesTitle.setSalesId(Long.valueOf(salesId));
                    salesTitle.setSalesId(salesId);
                    if (salesTitle.getTitleId() != null) {
                        salesTitle.setDeleteFlag("0");
                        salesTitlesAdd.add(salesTitle);
                        updateIds = updateIds + salesTitle.getTitleId() + ",";
                    } else {
                    	salesTitle.setDeleteFlag("0");
                        salesTitlesAdd.add(salesTitle);
                    }
                }
                //修改titles
                if (salesTitlesUpd.size() > 0) {
                    insuranceSalesInfoClient.updateSalesTitle(salesTitlesUpd);

                }
                //将被删除的titles删除
                Map<String, Object> paras = new HashMap<>();
                if (updateIds.endsWith(",")) {
                    updateIds = updateIds.substring(0, updateIds.length() - 1);
                }
               // paras.put("updateIds", updateIds);
                paras.put("salesId", salesId);
                insuranceSalesInfoClient.deleteTitleByIds(paras);
                //新增新加入的titles
                if (salesTitlesAdd.size() > 0) {
                    insuranceSalesInfoClient.addSalesTitle(salesTitlesAdd);
                }

            }

            if (StringUtil.isNotBlank(eduJobsList)) {
                List<SalesEduJob> salesEduJobs = toList(eduJobsList, SalesEduJob.class);
                List<SalesEduJob> salesEduJobsAdd = new ArrayList<SalesEduJob>();
                List<SalesEduJob> salesEduJobsUpd = new ArrayList<SalesEduJob>();
                String updateIds = "";
                for (SalesEduJob salesEduJob : salesEduJobs) {
//                    salesEduJob.setSalesId(Long.valueOf(salesId));
                    salesEduJob.setSalesId(salesId);
                    if (salesEduJob.getEduId() != null) {
                        salesEduJob.setDeleteFlag("0");
                        salesEduJobsAdd.add(salesEduJob);
                        updateIds = updateIds + salesEduJob.getEduId() + ",";
                    } else {
                    	salesEduJob.setDeleteFlag("0");
                        salesEduJobsAdd.add(salesEduJob);
                    }
                }
                //修改titles
                if (salesEduJobsUpd.size() > 0) {
                    insuranceSalesInfoClient.updateSalesEduJob(salesEduJobsUpd);

                }
                //将被删除的titles删除
                Map<String, Object> paras = new HashMap<>();
                if (updateIds.endsWith(",")) {
                    updateIds = updateIds.substring(0, updateIds.length() - 1);
                }
                //paras.put("updateIds", updateIds);
                paras.put("salesId", salesId);
                insuranceSalesInfoClient.deleteEduByIds(paras);
                //新增新加入的titles
                if (salesEduJobsAdd.size() > 0) {
                    insuranceSalesInfoClient.addSalesEduJob(salesEduJobsAdd);
                }
            }

            if (StringUtil.isNotBlank(relativeList)) {
                List<SalesRelative> salesRelatives = toList(relativeList, SalesRelative.class);
                List<SalesRelative> salesRelativesAdd = new ArrayList<SalesRelative>();
                List<SalesRelative> salesRelativesUpd = new ArrayList<SalesRelative>();
                String updateIds = "";
                for (SalesRelative salesRelative : salesRelatives) {
//                    salesRelative.setSalesId(Long.valueOf(salesId));
                    salesRelative.setSalesId(salesId);
                    if (salesRelative.getRelativeId() != null) {
                        salesRelative.setDeleteFlag("0");
                        salesRelativesAdd.add(salesRelative);
                        updateIds = updateIds + salesRelative.getRelativeId() + ",";
                    } else {
                    	salesRelative.setDeleteFlag("0");
                        salesRelativesAdd.add(salesRelative);
                    }
                }
                //修改titles
                if (salesRelativesUpd.size() > 0) {
                    insuranceSalesInfoClient.updateSalesRelative(salesRelativesUpd);

                }
                //将被删除的titles删除
                Map<String, Object> paras = new HashMap<>();
                if (updateIds.endsWith(",")) {
                    updateIds = updateIds.substring(0, updateIds.length() - 1);
                }
               // paras.put("updateIds", updateIds);
                paras.put("salesId", salesId);
                insuranceSalesInfoClient.deleteShipByIds(paras);
                //新增新加入的titles
                if (salesRelativesAdd.size() > 0) {
                    insuranceSalesInfoClient.addSalesRelative(salesRelativesAdd);
                }
            }

            if (StringUtil.isNotBlank(contractList)) {
                List<SalesContract> salesContracts = toList(contractList, SalesContract.class);
                List<SalesContract> salesContractsAdd = new ArrayList<SalesContract>();
                List<SalesContract> salesContractsUpd = new ArrayList<SalesContract>();
                String updateIds = "";
                for (SalesContract salesContract : salesContracts) {
//                    salesContract.setSalesId(Long.valueOf(salesId));
                    salesContract.setSalesId(salesId);
                    if (salesContract.getContractId() != null) {
                        salesContract.setDeleteFlag("0");
                        salesContractsAdd.add(salesContract);
                        updateIds = updateIds + salesContract.getContractId() + ",";
                    } else {
                    	salesContract.setDeleteFlag("0");
                        salesContractsAdd.add(salesContract);
                    }
                }
                //insuranceSalesInfoClient.addSalesContract(salesContracts);
                //修改titles
                if (salesContractsUpd.size() > 0) {
                    insuranceSalesInfoClient.updateSalesContracts(salesContractsUpd);

                }
                //将被删除的titles删除
                Map<String, Object> paras = new HashMap<>();
                if (updateIds.endsWith(",")) {
                    updateIds = updateIds.substring(0, updateIds.length() - 1);
                }
               // paras.put("updateIds", updateIds);
                paras.put("salesId", salesId);
                insuranceSalesInfoClient.deleteHtByIds(paras);
                //新增新加入的titles
                if (salesContractsAdd.size() > 0) {
                    insuranceSalesInfoClient.addSalesContract(salesContractsAdd);
                }
            }


            msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            msg.setRequestState(e.getMessage());
            logger.info("保险组织架构[修改]异常",e);
        }
        return msg;
    }




    /**
     * 查询部门基础信息List
     */
    @RequestMapping("/insuranceSalesPage")
    @ResponseBody
    public DataMsg insuranceSalesPage(HttpServletRequest request, DataMsg dataMsg) {

        try {
            Map<String, Object> paras = new HashMap<>();
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                paras.put("pageNo", 1);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                paras.put("pageSize", 10);
            }
            String salesStatus = request.getParameter("salesStatus");
            if (StringUtil.isNotBlank(salesStatus)) {
                paras.put("salesStatus", salesStatus);
            }
            String salesOrgId = request.getParameter("salesOrgId");
            if (StringUtil.isNotBlank(salesOrgId)) {
                String salesAllOrgs = salesOrgInfoClient.findEmployeeAllOrgs(salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(salesOrgId).get(0).get("SALES_ORG_CODE").toString());
                paras.put("salesAllOrgs", salesAllOrgs);
            }
            String salesTeamId = request.getParameter("salesTeamId");
            if (StringUtil.isNotBlank(salesTeamId)) {
                paras.put("salesTeamId", salesTeamId);
            }
            String insuranceSalerNo = request.getParameter("insuranceSalerNo");
            if (StringUtil.isNotBlank(insuranceSalerNo)) {
                paras.put("insuranceSalerNo", insuranceSalerNo);
            }
            String insuranceSaler = request.getParameter("insuranceSaler");
            if (StringUtil.isNotBlank(insuranceSaler)) {
                paras.put("insuranceSaler", insuranceSaler);
            }
            String rankSequenceId = request.getParameter("rankSequenceId");
            if (StringUtil.isNotBlank(rankSequenceId)) {
                paras.put("rankSequenceId", rankSequenceId);
            }
            String salesGradeId = request.getParameter("salesGradeId");
            if (StringUtil.isNotBlank(salesGradeId)) {
                paras.put("salesGradeId", salesGradeId);
            }
            String mobile = request.getParameter("mobile");
            if (StringUtil.isNotBlanks(mobile)){
                paras.put("mobile",mobile);
            }
            String cardNo = request.getParameter("cardNo");
            if (StringUtil.isNotBlanks(cardNo)){
                paras.put("cardNo",cardNo);
            }
            String minJoinDate = request.getParameter("minJoinDate");
            if (StringUtil.isNotBlanks(minJoinDate)) {
                paras.put("minJoinDate",minJoinDate);
            }
            String maxJoinDate = request.getParameter("maxJoinDate");
            if (StringUtil.isNotBlanks(maxJoinDate)) {
                paras.put("maxJoinDate",maxJoinDate);
            }
             String minQuitDate = request.getParameter("minQuitDate");
            if (StringUtil.isNotBlanks(minQuitDate)) {
                paras.put("minQuitDate",minQuitDate);
            }
            String maxQuitDate = request.getParameter("maxQuitDate");
            if (StringUtil.isNotBlanks(maxQuitDate)) {
                paras.put("maxQuitDate",maxQuitDate);
            }

            //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());

            PageModel pageModel = insuranceSalesInfoClient.getInsuranceSalesList(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("保险组织架构[查询]异常异常",e);
        }
        return dataMsg;
    }

    /**
     * 查询部门基础信息List
     */
    @RequestMapping("/insuranceSalesList")
    @ResponseBody
    public DataMsg insuranceSalesList(HttpServletRequest request, DataMsg dataMsg) {

	   try {
		       Map<String,Object> paras = new HashMap<>();
		       String insuranceSalesNo = request.getParameter("insuranceSalesNo");
		       if (StringUtil.isNotBlank(insuranceSalesNo)) {
		           paras.put("insuranceSalerNo", insuranceSalesNo);
		       }
		       String likeSalerNo = request.getParameter("likeSalerNo");
		       if (StringUtil.isNotBlank(likeSalerNo)) {
		           paras.put("likeSalerNo", likeSalerNo);
		       }
		       String salesTeamId = request.getParameter("salesTeamId");
		       if (StringUtil.isNotBlank(salesTeamId)) {
		           paras.put("salesTeamId", salesTeamId);
		       }
		       String salesOrgId = request.getParameter("salesOrgId");
		       if (StringUtil.isNotBlank(salesOrgId)) {
		           paras.put("salesOrgId",salesOrgId);
		       }
		       String dataAuthFlag = request.getParameter("dataAuthFlag");
		       if("1".equals(dataAuthFlag)){
		           getAuthDataParams(paras);
               }
		       String salesStatusList = request.getParameter("salesStatusList");
		       if(StringUtil.isNotBlank(salesStatusList)){
                   paras.put("salesStatusList",salesStatusList);
               }
               String salesAllOrgs = request.getParameter("salesAllOrgs");
               if(StringUtil.isNotBlank(salesAllOrgs)){
                   paras.put("salesAllOrgs",salesAllOrgs);
               }


		       List<InsuranceSalesInfo> list = insuranceSalesInfoClient.insuranceSalesList(paras);
		       dataMsg.setRows(list);
		       dataMsg.setMessageCode("200");
		   } catch (Exception e) {
		       dataMsg.setMessageCode("400");
		        logger.error("保险组织架构[查询]异常异常",e);
		    }
		    return dataMsg;
	}

	  /**
     * 跳转到设置员工关系页面
     */
    @RequestMapping("/toRelationPage")
    public String toRelationPage(Model model, HttpServletRequest request) {
        String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("salesIds",insuranceSalesId);
         //查询销售人员及其员工关系信息
         List<Map<String, Object>> list = new ArrayList<>();
        if (CollUtil.isNotEmpty(paras)) {
             //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());
            list = insuranceSalesInfoClient.insuranceSalesRelationList(paras);
        }
          //InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
       // model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);
        model.addAttribute("insuranceSalesInfo",list.get(0));
        return "salesTeamInfoPage/salerRelation";
    }

    	  /**
     * 跳转到查看员工关系页面
     */
    @RequestMapping("/toRelationViewPage")
    public String toRelationViewPage(Model model, HttpServletRequest request) {
        String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalesId",insuranceSalesId);
         InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
             //数据权限相关查询条件
            paras.clear();
           paras.put("salesIds",insuranceSalesId);
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());
           List<Map<String, Object>>   list = insuranceSalesInfoClient.insuranceSalesRelationList(paras);
             model.addAttribute("list", list.get(0));
           model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);

        return "salesTeamInfoPage/salerRelationView";
    }



    /**
     * 跳转到修改员工关系页面
     */
    @RequestMapping("/toRelationUpdatePage")
    public String toRelationUpdatePage(Model model, HttpServletRequest request) {
        String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
         paras.clear();
           paras.put("salesIds",insuranceSalesId);
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());
           List<Map<String, Object>>   list = insuranceSalesInfoClient.insuranceSalesRelationList(paras);
             model.addAttribute("list", list.get(0));
             paras.clear();
        paras.put("insuranceSalesId",insuranceSalesId);
         InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
          model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);
        return "salesTeamInfoPage/salerRelationUpdate";
    }

     /**
     * 跳转到修改新员工关系页面
     */
    @RequestMapping("/toRelationNewUpdatePage")
    public String toRelationNewUpdatePage(Model model, HttpServletRequest request) {
        String insuranceSalesId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        //查询销售人员次月的职级和团队
        paras.put("insuranceSalesId", insuranceSalesId);
        Map<String, Object> nextMessage = insuranceSalesInfoClient.salerNextMessage(paras);
        model.addAttribute("list", nextMessage);
        //查询次月的员工关系
        paras.clear();
        paras.put("insuranceSalesId", insuranceSalesId);
        String str = DateUtil.date2Str(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(), 1)), DateUtil.TIME_FORMAT_MONTHS);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salerId", insuranceSalesId);
        map.put("relationMonth", str);
        SalerRelationChange relationChange = insuranceSalesInfoClient.selectRelationBySalerIdAndMonth(map);
        if (relationChange != null) {
            InsuranceSalesInfo insuranceSalesInfo = new InsuranceSalesInfo();
            insuranceSalesInfo.setInsuranceSalesId(Long.parseLong(insuranceSalesId));
            insuranceSalesInfo.setTjSalesId(relationChange.getTjAfterSalesId());
            insuranceSalesInfo.setDbSalesId(relationChange.getDbAfterSalesId());
            insuranceSalesInfo.setJcSalesId(relationChange.getJcAfterSalesId());
            insuranceSalesInfo.setSjSalesId(relationChange.getSjAfterSalesId());
            insuranceSalesInfo.setFdSalesId(relationChange.getFdAfterSalesId());
            insuranceSalesInfo.setYcCFirstGener(relationChange.getYcAfterCFirstGener());
            insuranceSalesInfo.setYcCSecondGener(relationChange.getYcAfterCSecondGener());
            insuranceSalesInfo.setYcBFirstGener(relationChange.getYcAfterBFirstGener());
            insuranceSalesInfo.setYcBSecondGener(relationChange.getYcAfterBSecondGener());
            insuranceSalesInfo.setDirectGroupC(relationChange.getDirectAfterGroupC());
            insuranceSalesInfo.setDirectDeptB(relationChange.getDirectAfterDeptB());
            insuranceSalesInfo.setDbSalesDate(relationChange.getDbSalesDate());
            insuranceSalesInfo.setTjSalesDate(relationChange.getTjSalesDate());
            insuranceSalesInfo.setJcSalesDate(relationChange.getJcSalesDate());
            insuranceSalesInfo.setSjSalesDate(relationChange.getSjSalesDate());
            insuranceSalesInfo.setFdSalesDate(relationChange.getFdSalesDate());
            insuranceSalesInfo.setEstablishTime(relationChange.getEstablishTime());
            insuranceSalesInfo.setEstablishTime1(relationChange.getEstablishTime1());
            insuranceSalesInfo.setEstablishTime2(relationChange.getEstablishTime2());
            insuranceSalesInfo.setEstablishTime3(relationChange.getEstablishTime3());
            insuranceSalesInfo.setEstablishTime4(relationChange.getEstablishTime4());
            insuranceSalesInfo.setEstablishTime5(relationChange.getEstablishTime5());

            model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);
            return "salesTeamInfoPage/salerRelationNewUpdate";
        }

        InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
        model.addAttribute("insuranceSalesInfo", insuranceSalesInfo);
        return "salesTeamInfoPage/salerRelationNewUpdate";
    }


	/**
	 *@描述 查询员工关系详细信息
	 *@创建人 qin lina
	 *@创建时间 2020/6/17
	 */
    @RequestMapping("/insuranceSalesRelationList")
    @ResponseBody
    public List<Map<String,Object>> insuranceSalesRelationList(HttpServletRequest request, DataMsg dataMsg) {

        try {
            Map<String, Object> paras = new HashMap<>();
            String query = request.getParameter("query");
            if (StringUtil.isNotBlank(query)){
                paras.put("query", query);
            }
             String queryNo = request.getParameter("queryNo");
            if (StringUtil.isNotBlank(queryNo)){
                paras.put("queryNo", queryNo);
            }
            List<String> salesNos = new ArrayList<>();
             String insuranceSalesId = request.getParameter("insuranceSalesId");
            if (StringUtil.isNotBlank(insuranceSalesId)) {
                salesNos.add(insuranceSalesId);
            }
            String dbSalesId = request.getParameter("dbSalesId");
            if (StringUtil.isNotBlank(dbSalesId)) {
                salesNos.add(dbSalesId);
            }
            String tjSalesId = request.getParameter("tjSalesId");
            if (StringUtil.isNotBlank(tjSalesId)) {
                salesNos.add(tjSalesId);
            }
            String ycCFirstGener = request.getParameter("ycCFirstGener");
            if (StringUtil.isNotBlank(ycCFirstGener)) {
                salesNos.add(ycCFirstGener);
            }
            String ycCSecondGener = request.getParameter("ycCSecondGener");
            if (StringUtil.isNotBlank(ycCSecondGener)) {
                salesNos.add(ycCSecondGener);
            }
            String ycBFirstGener = request.getParameter("ycBFirstGener");
            if (StringUtil.isNotBlank(ycBFirstGener)) {
                salesNos.add(ycBFirstGener);
            }
            String ycBSecondGener = request.getParameter("ycBSecondGener");
            if (StringUtil.isNotBlank(ycBSecondGener)) {
                salesNos.add(ycBSecondGener);
            }
            String ycSalesId = request.getParameter("ycSalesId");
            if (StringUtil.isNotBlank(ycSalesId)) {
                salesNos.add(ycSalesId);
            }
            String sjSalesId = request.getParameter("sjSalesId");
            if (StringUtil.isNotBlank(sjSalesId)) {
                salesNos.add(sjSalesId);
            }
            String jcSalesId = request.getParameter("jcSalesId");
            if (StringUtil.isNotBlank(jcSalesId)) {
                salesNos.add(jcSalesId);
            }
            String fdSalesId = request.getParameter("fdSalesId");
            if (StringUtil.isNotBlank(fdSalesId)) {
                salesNos.add(fdSalesId);
            }
            String secYcSalesId = request.getParameter("secYcSalesId");
            if (StringUtil.isNotBlank(secYcSalesId)) {
                salesNos.add(secYcSalesId);
            }
            String czSalesId = request.getParameter("czSalesId");
            if (StringUtil.isNotBlank(czSalesId)) {
                salesNos.add(czSalesId);
            }
            String bzSalesId = request.getParameter("bzSalesId");
            if (StringUtil.isNotBlank(bzSalesId)) {
                salesNos.add(bzSalesId);
            }
            String directGroupC = request.getParameter("directGroupC");
            if (StringUtil.isNotBlank(directGroupC)) {
                salesNos.add(directGroupC);
            }
            String directDeptB = request.getParameter("directDeptB");
            if (StringUtil.isNotBlank(directDeptB)) {
                salesNos.add(directDeptB);
            }
            String salesIds = Joiner.on(",").join(salesNos);
            if (StrUtil.isNotBlank(salesIds)) {
                paras.put("salesIds", salesIds);
            }
            List<Map<String, Object>> list = new ArrayList<>();
            if (CollUtil.isNotEmpty(paras)) {
                 //数据权限相关查询条件
                Subject subject = SecurityUtils.getSubject();
                Employee employeeShiro = (Employee) subject.getPrincipal();
                employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
                paras.put("isAdmin", employeeShiro.getEmployeeNo());
                paras.put("myDeptNo", employeeShiro.getDeptNo());
                list = insuranceSalesInfoClient.insuranceSalesRelationList(paras);
            }

            return list;

        } catch (Exception e) {
            logger.error("查询员工关系异常", e);
        }
        return null;
    }

    /**
     * 新增 保存员工关系
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/addSalesRelation")
    @ResponseBody
    public DataMsg addSalesRelation(InsuranceSalesInfo insuranceSalesInfo, DataMsg dataMsg) {

        try {
            //获取操作人
             Subject subject = SecurityUtils.getSubject();
             Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();
            //操作日志
            SalerRelationLog salerRelationLog = new SalerRelationLog();
            salerRelationLog.setSalerId(insuranceSalesInfo.getInsuranceSalesId());
            salerRelationLog.setDbAfterSalesId(insuranceSalesInfo.getDbSalesId());
            salerRelationLog.setDbAfterSalesDate(insuranceSalesInfo.getDbSalesDate()==""?null:insuranceSalesInfo.getDbSalesDate());
            salerRelationLog.setTjAfterSalesId(insuranceSalesInfo.getTjSalesId());
            salerRelationLog.setTjAfterSalesDate(insuranceSalesInfo.getTjSalesDate()==""?null:insuranceSalesInfo.getTjSalesDate());
            salerRelationLog.setSjAfterSalesId(insuranceSalesInfo.getSjSalesId());
            salerRelationLog.setSjAfterSalesDate(insuranceSalesInfo.getSjSalesDate()==""?null:insuranceSalesInfo.getSjSalesDate());
            salerRelationLog.setJcAfterSalesId(insuranceSalesInfo.getJcSalesId());
            salerRelationLog.setJcAfterSalesDate(insuranceSalesInfo.getJcSalesDate()==""?null:insuranceSalesInfo.getJcSalesDate());
            salerRelationLog.setFdAfterSalesId(insuranceSalesInfo.getFdSalesId());
            salerRelationLog.setFdAfterSalesDate(insuranceSalesInfo.getFdSalesDate()==""?null:insuranceSalesInfo.getFdSalesDate());
            salerRelationLog.setYcAfterCFirstGener(insuranceSalesInfo.getYcCFirstGener());
            salerRelationLog.setEstablishAfterTime(insuranceSalesInfo.getEstablishTime()==""?null:insuranceSalesInfo.getEstablishTime());
            salerRelationLog.setYcAfterCSecondGener(insuranceSalesInfo.getYcCSecondGener());
            salerRelationLog.setEstablishAfterTime1(insuranceSalesInfo.getEstablishTime1()==""?null:insuranceSalesInfo.getEstablishTime1());
            salerRelationLog.setYcAfterBFirstGener(insuranceSalesInfo.getYcBFirstGener());
            salerRelationLog.setEstablishAfterTime2(insuranceSalesInfo.getEstablishTime2()==""?null:insuranceSalesInfo.getEstablishTime2());
            salerRelationLog.setYcAfterBSecondGener(insuranceSalesInfo.getYcBSecondGener());
            salerRelationLog.setEstablishAfterTime3(insuranceSalesInfo.getEstablishTime3()==""?null:insuranceSalesInfo.getEstablishTime3());
            salerRelationLog.setDirectAfterGroupC(insuranceSalesInfo.getDirectGroupC());
            salerRelationLog.setEstablishAfterTime4(insuranceSalesInfo.getEstablishTime4()==""?null:insuranceSalesInfo.getEstablishTime4());
            salerRelationLog.setDirectAfterDeptB(insuranceSalesInfo.getDirectDeptB());
            salerRelationLog.setEstablishAfterTime5(insuranceSalesInfo.getEstablishTime5()==""?null:insuranceSalesInfo.getEstablishTime5());
            salerRelationLog.setCreateTime(new Date());
            salerRelationLog.setCreateBy(employeeNo);
            salerRelationLog.setEffectTab(Constants.EFFECT_TAB_1);
            salerRelationLog.setOperationTab(Constants.OPERATION_TAB_0);
            Map<String, Object> map = new HashMap<>();
            map.put("insuranceSalesInfo",JSONUtil.toJsonStr(insuranceSalesInfo));
            map.put("salerRelationLog",JSONUtil.toJsonStr(salerRelationLog));
            insuranceSalesInfoClient.addSalesRelation(map);

           dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("500");
            dataMsg.setData(e.getMessage());
            logger.error("设置员工关系异常", e);
        }
        return dataMsg;
    }

        /**
     * 修改保存员工关系
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/updateSalesRelation")
    @ResponseBody
    public DataMsg updateSalesRelation(InsuranceSalesInfo insuranceSalesInfo,String type, DataMsg dataMsg) {
          SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

        try {
             //获取操作人
             Subject subject = SecurityUtils.getSubject();
             Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();

            HashMap<String, Object> map = new HashMap<>();
            map.put("insuranceSalesId",insuranceSalesInfo.getInsuranceSalesId());
            InsuranceSalesInfo preSalesInfo = insuranceSalesInfoClient.selectById(map);
            //立即生效
            if ("1".equals(type)){
                String date = dft.format(new Date());
                 packSalesInfoForUpdateRelation(insuranceSalesInfo,preSalesInfo,date);
                 //组装操作日志表
               SalerRelationLog salerRelationLog =  packSalerRelationLog(insuranceSalesInfo,preSalesInfo,employeeNo,Constants.EFFECT_TAB_1,Constants.OPERATION_TAB_1);
               map.clear();
               map.put("insuranceSalesInfo",JSONUtil.toJsonStr(insuranceSalesInfo));
               map.put("salerRelationLog",JSONUtil.toJsonStr(salerRelationLog));
                insuranceSalesInfoClient.addSalesRelation(map);
            }else if ("2".equals(type)){
                 //次月生效
                Calendar calendar = Calendar.getInstance();
		        calendar.add(Calendar.MONTH, 1);
		       calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                String date = dft.format(calendar.getTime());
                 packSalesInfoForUpdateRelation(insuranceSalesInfo,preSalesInfo,date);
                 SalerRelationLog salerRelationLog =  packSalerRelationLog(insuranceSalesInfo,preSalesInfo,employeeNo,Constants.EFFECT_TAB_2,Constants.OPERATION_TAB_1);
                 saveRelationChangeForNextMonth(insuranceSalesInfo,preSalesInfo,salerRelationLog);

            }


           dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("500");
            dataMsg.setData(e.getMessage());
            logger.error("设置员工关系异常", e);
        }
        return dataMsg;
    }

    /**
     * //组装操作日志表
     * @param insuranceSalesInfo
     * @param preSalesInfo
     * @param employeeNo
     * @param s
     * @param s1
     * @return
     */
    private SalerRelationLog packSalerRelationLog(InsuranceSalesInfo insuranceSalesInfo, InsuranceSalesInfo preSalesInfo, String employeeNo, String effectTab, String operationTab) {
            SalerRelationLog salerRelationLog = new SalerRelationLog();
            salerRelationLog.setSalerId(preSalesInfo.getInsuranceSalesId());
            salerRelationLog.setDbBeforeSalesId(preSalesInfo.getDbSalesId());
            salerRelationLog.setDbAfterSalesId(insuranceSalesInfo.getDbSalesId());
            salerRelationLog.setTjBeforeSalesId(preSalesInfo.getTjSalesId());
            salerRelationLog.setTjAfterSalesId(insuranceSalesInfo.getTjSalesId());
            salerRelationLog.setYcBeforeCFirstGener(preSalesInfo.getYcCFirstGener());
            salerRelationLog.setYcAfterCFirstGener(insuranceSalesInfo.getYcCFirstGener());
            salerRelationLog.setYcBeforeCSecondGener(preSalesInfo.getYcCSecondGener());
            salerRelationLog.setYcAfterCSecondGener(insuranceSalesInfo.getYcCSecondGener());
            salerRelationLog.setYcBeforeBFirstGener(preSalesInfo.getYcBFirstGener());
            salerRelationLog.setYcAfterBFirstGener(insuranceSalesInfo.getYcBFirstGener());
            salerRelationLog.setYcBeforeBSecondGener(preSalesInfo.getYcBSecondGener());
            salerRelationLog.setYcAfterBSecondGener(insuranceSalesInfo.getYcBSecondGener());
            salerRelationLog.setSjBeforeSalesId(preSalesInfo.getSjSalesId());
            salerRelationLog.setSjAfterSalesId(insuranceSalesInfo.getSjSalesId());
            salerRelationLog.setJcBeforeSalesId(preSalesInfo.getJcSalesId());
            salerRelationLog.setJcAfterSalesId(insuranceSalesInfo.getJcSalesId());
            salerRelationLog.setFdBeforeSalesId(preSalesInfo.getFdSalesId());
            salerRelationLog.setFdAfterSalesId(insuranceSalesInfo.getFdSalesId());
            salerRelationLog.setDirectBeforeGroupC(preSalesInfo.getDirectGroupC());
            salerRelationLog.setDirectAfterGroupC(insuranceSalesInfo.getDirectGroupC());
            salerRelationLog.setDirectBeforeDeptB(preSalesInfo.getDirectDeptB());
            salerRelationLog.setDirectAfterDeptB(insuranceSalesInfo.getDirectDeptB());
			  salerRelationLog.setDbBeforeSalesDate(preSalesInfo.getDbSalesDate()==""?null:preSalesInfo.getDbSalesDate());
            salerRelationLog.setTjBeforeSalesDate(preSalesInfo.getTjSalesDate()==""?null:preSalesInfo.getTjSalesDate());
            salerRelationLog.setJcBeforeSalesDate(preSalesInfo.getJcSalesDate()==""?null:preSalesInfo.getJcSalesDate());
            salerRelationLog.setSjBeforeSalesDate(preSalesInfo.getSjSalesDate()==""?null:preSalesInfo.getSjSalesDate());
            salerRelationLog.setFdBeforeSalesDate(preSalesInfo.getFdSalesDate()==""?null:preSalesInfo.getFdSalesDate());
            salerRelationLog.setEstablishBeforeTime(preSalesInfo.getEstablishTime()==""?null:preSalesInfo.getEstablishTime());
            salerRelationLog.setEstablishBeforeTime1(preSalesInfo.getEstablishTime1()==""?null:preSalesInfo.getEstablishTime1());
            salerRelationLog.setEstablishBeforeTime2(preSalesInfo.getEstablishTime2()==""?null:preSalesInfo.getEstablishTime2());
            salerRelationLog.setEstablishBeforeTime3(preSalesInfo.getEstablishTime3()==""?null:preSalesInfo.getEstablishTime3());
            salerRelationLog.setEstablishBeforeTime4(preSalesInfo.getEstablishTime4()==""?null:preSalesInfo.getEstablishTime4());
            salerRelationLog.setEstablishBeforeTime5(preSalesInfo.getEstablishTime5()==""?null:preSalesInfo.getEstablishTime5());

            salerRelationLog.setDbAfterSalesDate(insuranceSalesInfo.getDbSalesDate()==""?null:insuranceSalesInfo.getDbSalesDate());
            salerRelationLog.setTjAfterSalesDate(insuranceSalesInfo.getTjSalesDate()==""?null:insuranceSalesInfo.getTjSalesDate());
            salerRelationLog.setJcAfterSalesDate(insuranceSalesInfo.getJcSalesDate()==""?null:insuranceSalesInfo.getJcSalesDate());
            salerRelationLog.setSjAfterSalesDate(insuranceSalesInfo.getSjSalesDate()==""?null:insuranceSalesInfo.getSjSalesDate());
            salerRelationLog.setFdAfterSalesDate(insuranceSalesInfo.getFdSalesDate()==""?null:insuranceSalesInfo.getFdSalesDate());
            salerRelationLog.setEstablishAfterTime(insuranceSalesInfo.getEstablishTime()==""?null:insuranceSalesInfo.getEstablishTime());
            salerRelationLog.setEstablishAfterTime1(insuranceSalesInfo.getEstablishTime1()==""?null:insuranceSalesInfo.getEstablishTime1());
            salerRelationLog.setEstablishAfterTime2(insuranceSalesInfo.getEstablishTime2()==""?null:insuranceSalesInfo.getEstablishTime2());
            salerRelationLog.setEstablishAfterTime3(insuranceSalesInfo.getEstablishTime3()==""?null:insuranceSalesInfo.getEstablishTime3());
            salerRelationLog.setEstablishAfterTime4(insuranceSalesInfo.getEstablishTime4()==""?null:insuranceSalesInfo.getEstablishTime4());
            salerRelationLog.setEstablishAfterTime5(insuranceSalesInfo.getEstablishTime5()==""?null:insuranceSalesInfo.getEstablishTime5());
            salerRelationLog.setEffectTab(effectTab);
            salerRelationLog.setOperationTab(operationTab);
            salerRelationLog.setCreateBy(employeeNo);
            salerRelationLog.setCreateTime(new Date());
            return salerRelationLog;

    }

    private void saveRelationChangeForNextMonth(InsuranceSalesInfo insuranceSalesInfo, InsuranceSalesInfo preSalesInfo, SalerRelationLog salerRelationLog) {
         SalerRelationChange salerRelationChange= new SalerRelationChange();
        String str = DateUtil.date2Str(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(),1)),DateUtil.TIME_FORMAT_MONTHS);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("salerId",preSalesInfo.getInsuranceSalesId());
        map.put("relationMonth",str);
        SalerRelationChange relationChange = insuranceSalesInfoClient.selectRelationBySalerIdAndMonth(map);
        if (null!=relationChange){
            salerRelationChange.setId(relationChange.getId());
        }
             salerRelationChange.setSalerId(preSalesInfo.getInsuranceSalesId());
            salerRelationChange.setRelationMonth(str);
            salerRelationChange.setDbBeforeSalesId(preSalesInfo.getDbSalesId());
            salerRelationChange.setDbAfterSalesId(insuranceSalesInfo.getDbSalesId());
            salerRelationChange.setTjBeforeSalesId(preSalesInfo.getTjSalesId());
            salerRelationChange.setTjAfterSalesId(insuranceSalesInfo.getTjSalesId());
            salerRelationChange.setYcBeforeCFirstGener(preSalesInfo.getYcCFirstGener());
            salerRelationChange.setYcAfterCFirstGener(insuranceSalesInfo.getYcCFirstGener());
            salerRelationChange.setYcBeforeCSecondGener(preSalesInfo.getYcCSecondGener());
            salerRelationChange.setYcAfterCSecondGener(insuranceSalesInfo.getYcCSecondGener());
            salerRelationChange.setYcBeforeBFirstGener(preSalesInfo.getYcBFirstGener());
            salerRelationChange.setYcAfterBFirstGener(insuranceSalesInfo.getYcBFirstGener());
            salerRelationChange.setYcBeforeBSecondGener(preSalesInfo.getYcBSecondGener());
            salerRelationChange.setYcAfterBSecondGener(insuranceSalesInfo.getYcBSecondGener());
            salerRelationChange.setSjBeforeSalesId(preSalesInfo.getSjSalesId());
            salerRelationChange.setSjAfterSalesId(insuranceSalesInfo.getSjSalesId());
            salerRelationChange.setJcBeforeSalesId(preSalesInfo.getJcSalesId());
            salerRelationChange.setJcAfterSalesId(insuranceSalesInfo.getJcSalesId());
            salerRelationChange.setFdBeforeSalesId(preSalesInfo.getFdSalesId());
            salerRelationChange.setFdAfterSalesId(insuranceSalesInfo.getFdSalesId());
            salerRelationChange.setDirectBeforeGroupC(preSalesInfo.getDirectGroupC());
            salerRelationChange.setDirectAfterGroupC(insuranceSalesInfo.getDirectGroupC());
            salerRelationChange.setDirectBeforeDeptB(preSalesInfo.getDirectDeptB());
            salerRelationChange.setDirectAfterDeptB(insuranceSalesInfo.getDirectDeptB());
            salerRelationChange.setDbSalesDate(insuranceSalesInfo.getDbSalesDate()==""?null:insuranceSalesInfo.getDbSalesDate());
            salerRelationChange.setTjSalesDate(insuranceSalesInfo.getTjSalesDate()==""?null:insuranceSalesInfo.getTjSalesDate());
            salerRelationChange.setJcSalesDate(insuranceSalesInfo.getJcSalesDate()==""?null:insuranceSalesInfo.getJcSalesDate());
            salerRelationChange.setSjSalesDate(insuranceSalesInfo.getSjSalesDate()==""?null:insuranceSalesInfo.getSjSalesDate());
            salerRelationChange.setFdSalesDate(insuranceSalesInfo.getFdSalesDate()==""?null:insuranceSalesInfo.getFdSalesDate());
            salerRelationChange.setEstablishTime(insuranceSalesInfo.getEstablishTime()==""?null:insuranceSalesInfo.getEstablishTime());
            salerRelationChange.setEstablishTime1(insuranceSalesInfo.getEstablishTime1()==""?null:insuranceSalesInfo.getEstablishTime1());
            salerRelationChange.setEstablishTime2(insuranceSalesInfo.getEstablishTime2()==""?null:insuranceSalesInfo.getEstablishTime2());
            salerRelationChange.setEstablishTime3(insuranceSalesInfo.getEstablishTime3()==""?null:insuranceSalesInfo.getEstablishTime3());
            salerRelationChange.setEstablishTime4(insuranceSalesInfo.getEstablishTime4()==""?null:insuranceSalesInfo.getEstablishTime4());
            salerRelationChange.setEstablishTime5(insuranceSalesInfo.getEstablishTime5()==""?null:insuranceSalesInfo.getEstablishTime5());
            salerRelationChange.setIsFinished("0");
            salerRelationChange.setCreateTime(new Date());
            if (null!=relationChange){
                map.clear();
                map.put("salerRelationChange",JSONUtil.toJsonStr(salerRelationChange));
                map.put("salerRelationLog",JSONUtil.toJsonStr(salerRelationLog));
                insuranceSalesInfoClient.updateRelationChange(map);
            }else {
                 map.clear();
                map.put("salerRelationChange",JSONUtil.toJsonStr(salerRelationChange));
                map.put("salerRelationLog",JSONUtil.toJsonStr(salerRelationLog));
                insuranceSalesInfoClient.insertSalerRelationChange(map);
            }
    }

    private void packSalesInfoForUpdateRelation(InsuranceSalesInfo insuranceSalesInfo, InsuranceSalesInfo preSalesInfo, String date) {
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getDbSalesId())) {
            if (!insuranceSalesInfo.getDbSalesId().equals(preSalesInfo.getDbSalesId())) {
                insuranceSalesInfo.setDbSalesDate(date);
            } else {
                insuranceSalesInfo.setDbSalesDate(preSalesInfo.getDbSalesDate());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getJcSalesId())) {
            if (!insuranceSalesInfo.getJcSalesId().equals(preSalesInfo.getJcSalesId())) {
                insuranceSalesInfo.setJcSalesDate(date);
            } else {
                insuranceSalesInfo.setJcSalesDate(preSalesInfo.getJcSalesDate());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getTjSalesId())) {
            if (!insuranceSalesInfo.getTjSalesId().equals(preSalesInfo.getTjSalesId())) {
                insuranceSalesInfo.setTjSalesDate(date);
            } else {
                insuranceSalesInfo.setTjSalesDate(preSalesInfo.getTjSalesDate());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getSjSalesId())){
            if (!insuranceSalesInfo.getSjSalesId().equals(preSalesInfo.getSjSalesId())){
                insuranceSalesInfo.setSjSalesDate(date);
            }else {
                insuranceSalesInfo.setSjSalesDate(preSalesInfo.getSjSalesDate());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getFdSalesId())){
            if (!insuranceSalesInfo.getFdSalesId().equals(preSalesInfo.getFdSalesId())){
                insuranceSalesInfo.setFdSalesDate(date);
            }else {
                insuranceSalesInfo.setFdSalesDate(preSalesInfo.getFdSalesDate());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getYcCFirstGener())){
            if (!insuranceSalesInfo.getYcCFirstGener().equals(preSalesInfo.getYcCFirstGener())){
                insuranceSalesInfo.setEstablishTime(date);
            }else {
                insuranceSalesInfo.setEstablishTime(preSalesInfo.getEstablishTime());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getYcCSecondGener())){
            if (!insuranceSalesInfo.getYcCSecondGener().equals(preSalesInfo.getYcCSecondGener())){
                insuranceSalesInfo.setEstablishTime1(date);
            }else {
                insuranceSalesInfo.setEstablishTime1(preSalesInfo.getEstablishTime1());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getYcBFirstGener())){
            if (!insuranceSalesInfo.getYcBFirstGener().equals(preSalesInfo.getYcBFirstGener())){
                insuranceSalesInfo.setEstablishTime2(date);
            }else {
                insuranceSalesInfo.setEstablishTime2(preSalesInfo.getEstablishTime2());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getYcBSecondGener())){
            if (!insuranceSalesInfo.getYcBSecondGener().equals(preSalesInfo.getYcBSecondGener())){
                insuranceSalesInfo.setEstablishTime3(date);
            }else {
                insuranceSalesInfo.setEstablishTime3(preSalesInfo.getEstablishTime3());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getDirectGroupC())){
            if (!insuranceSalesInfo.getDirectGroupC().equals(preSalesInfo.getDirectGroupC())){
                insuranceSalesInfo.setEstablishTime4(date);
            }else {
                insuranceSalesInfo.setEstablishTime4(preSalesInfo.getEstablishTime4());
            }
        }
        if (ObjectUtil.isNotEmpty(insuranceSalesInfo.getDirectDeptB())){
            if (!insuranceSalesInfo.getDirectDeptB().equals(preSalesInfo.getDirectDeptB())){
                insuranceSalesInfo.setEstablishTime5(date);
            }else {
                insuranceSalesInfo.setEstablishTime5(preSalesInfo.getEstablishTime5());
            }
        }

    }


    /**
     *@描述  根据销售人员ID 获取该人员所有关系人
     *@创建人 qin lina
     *@创建时间 2020/3/13
     */
      @RequestMapping("/insuranceSalesListForSalesNo")
    @ResponseBody
	 public DataMsg insuranceSalesListForSalesNo(HttpServletRequest request, DataMsg dataMsg) {

	   try {
		       List<String> salesNos = new ArrayList<>();
		        String insuranceSalesId = request.getParameter("insuranceSalesId");
		       /*if (StringUtil.isNotBlank(insuranceSalesId)) {
		           salesNos.add(insuranceSalesId);
		       }*/
		       String dbSalesId = request.getParameter("dbSalesId");
		       if (StringUtil.isNotBlank(dbSalesId)) {
		           salesNos.add(dbSalesId);
		       }
		       String tjSalesId = request.getParameter("tjSalesId");
		       if (StringUtil.isNotBlank(tjSalesId)) {
		           salesNos.add(tjSalesId);
		       }
		       String ycCFirstGener= request.getParameter("ycCFirstGener");
		       if(StringUtil.isNotBlank(ycCFirstGener)){
		           salesNos.add(ycCFirstGener);
               }
		       String ycCSecondGener = request.getParameter("ycCSecondGener");
		       if(StringUtil.isNotBlank(ycCSecondGener)){
		           salesNos.add(ycCSecondGener);
               }
		       String ycBFirstGener = request.getParameter("ycBFirstGener");
		       if(StringUtil.isNotBlank(ycBFirstGener)){
		           salesNos.add(ycBFirstGener);
               }
		       String ycBSecondGener = request.getParameter("ycBSecondGener");
		       if(StringUtil.isNotBlank(ycBSecondGener)){
		           salesNos.add(ycBSecondGener);
               }
		       String ycSalesId = request.getParameter("ycSalesId");
		       if (StringUtil.isNotBlank(ycSalesId)) {
		          salesNos.add(ycSalesId);
		       }
		       String sjSalesId = request.getParameter("sjSalesId");
		       if (StringUtil.isNotBlank(sjSalesId)) {
		          salesNos.add(sjSalesId);
		       }
		       String jcSalesId = request.getParameter("jcSalesId");
		       if (StringUtil.isNotBlank(jcSalesId)) {
		          salesNos.add(jcSalesId);
		       }
		       String fdSalesId = request.getParameter("fdSalesId");
		       if (StringUtil.isNotBlank(fdSalesId)) {
		          salesNos.add(fdSalesId);
		       }
		        String secYcSalesId = request.getParameter("secYcSalesId");
		       if (StringUtil.isNotBlank(secYcSalesId)) {
		          salesNos.add(secYcSalesId);
		       }
		        String czSalesId = request.getParameter("czSalesId");
		       if (StringUtil.isNotBlank(czSalesId)) {
		          salesNos.add(czSalesId);
		       }
		        String bzSalesId = request.getParameter("bzSalesId");
		       if (StringUtil.isNotBlank(bzSalesId)) {
		          salesNos.add(bzSalesId);
		       }
		       String directGroupC = request.getParameter("directGroupC");
		       if (StringUtil.isNotBlank(directGroupC)) {
		          salesNos.add(directGroupC);
		       }
		       String directDeptB = request.getParameter("directDeptB");
		       if (StringUtil.isNotBlank(directDeptB)) {
		          salesNos.add(directDeptB);
		       }
		        dataMsg.setRows(null);
		       if (!CollectionUtils.isEmpty(salesNos)) {
                   List<InsuranceSalesInfo> list = insuranceSalesInfoClient.insuranceSalesListForSalesNo(salesNos);
                    dataMsg.setRows(list);
		       }
		       dataMsg.setMessageCode("200");
		   } catch (Exception e) {
		       dataMsg.setMessageCode("400");
		        logger.error("保险组织架构[查询]异常异常",e);
		    }
		    return dataMsg;
	}
	
	/**
	*
	* 员工离职
	* */
	@RequestMapping("/salerQuit")
	@ResponseBody
	public DataMsg salerQuit(HttpServletRequest request,SalerQuitInfo salerQuitInfo,DataMsg dataMsg){
	   try {
		   insuranceSalesInfoClient.insertQuit(salerQuitInfo);
		   dataMsg.setMessageCode("200");
	   }catch(Exception e) {
	       dataMsg.setMessageCode("400");
	       logger.error("保险组织架构[查询]异常异常",e);
	   }
	   return dataMsg;		
	}
	
	/**
	*
	* 员工转正
	* */
	@RequestMapping("/checkPass")
	@ResponseBody
	public DataMsg checkPass(HttpServletRequest request,DataMsg dataMsg){
	   try {
		   Map<String,Object> paras = new HashMap<>();
		   String insuranceSalesId = request.getParameter("insuranceSalerId");
		   if(StringUtil.isNotEmpty(insuranceSalesId)){
			   paras.put("insuranceSalesId", insuranceSalesId);
			   InsuranceSalesInfo salesInfo = insuranceSalesInfoClient.selectById(paras);
			   salesInfo.setSalesStatus("1");
			   DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			   salesInfo.setPassDate(date.format(new Date()));
			    boolean isNoticy = true;
			   insuranceSalesInfoClient.updateInsuranceSales(salesInfo,isNoticy);
			   dataMsg.setMessageCode("200");
		   }else{
			   dataMsg.setMessageCode("400");
			   dataMsg.setRequestState("执行的员工无效！");
		   }
	   }catch(Exception e) {
	       dataMsg.setMessageCode("400");
	       logger.error("保险组织架构[查询]异常异常",e);
	   }
	   return dataMsg;		
	}
	
	/**
	*
	* 员工加入黑名单
	* */
	@RequestMapping("/salerBlack")
	@ResponseBody
	public DataMsg salerBlack(HttpServletRequest request,SalerBlackInfo salerBlackInfo,DataMsg dataMsg){
	   try {
		   
		   Map<String,Object> paras = new HashMap<>();
		   Long salerId = salerBlackInfo.getSalerId();
		   String blackStatus = "0";
		   paras.put("salerId", salerId);
		   paras.put("blackStatus", blackStatus);
		   List<Object> haveNum = insuranceSalesInfoClient.getSalerBlackList(paras);
		   if(haveNum.size()>0){
			   dataMsg.setRows(haveNum);
			   dataMsg.setMessageCode("999");
			   return dataMsg;
		   }	
		   
		   paras.clear();
		   paras.put("insuranceSalesId",salerId);
		   InsuranceSalesInfo salesInfo = insuranceSalesInfoClient.selectById(paras);
		   if(!salesInfo.getSalesStatus().equals("2")){
			   salesInfo.setSalesStatus("3"); 
		   }	
		   salerBlackInfo.setCreatedTime(new Date());
		   insuranceSalesInfoClient.insertBlack(salerBlackInfo);
		   boolean isNoticy =true;
		   insuranceSalesInfoClient.updateInsuranceSales(salesInfo,isNoticy);
		   dataMsg.setMessageCode("200");
	   }catch(Exception e) {
	       dataMsg.setMessageCode("400");
	       logger.error("保险组织架构[查询]异常异常",e);
	   }
	   return dataMsg;		
	}
	
	/**
	*
	* 员工异动
	* */
	@RequestMapping("/salerMove")
	@ResponseBody
	public DataMsg salerMove(HttpServletRequest request,SalesMoveLogs salesMoveLogs,DataMsg dataMsg){
	   try {
           Map<String, Object> paras = new HashMap<>();
           boolean isnoticy = false;
           /**员工异动不能跨省 */
           if (!salesMoveLogs.getNextOrgId().equals(salesMoveLogs.getPreOrgId())) {
               Map<String, Object> params = new HashMap<>();
               params.put("preOrgId", salesMoveLogs.getPreOrgId());
               params.put("nextOrgId", salesMoveLogs.getNextOrgId());
               Map<String, String> calOrgsMap = salesOrgInfoClient.getCalOrgsByCondition(params);

               String preCalOrgId = calOrgsMap.get("preCalOrgId");
               String nextCalOrgId = calOrgsMap.get("nextCalOrgId");
               if (!preCalOrgId.equals(nextCalOrgId)) {
                   dataMsg.setErrorMsg("组织机构不允许跨省修改");
                   dataMsg.setMessageCode("1000");
                   logger.error("销售人员{}不能跨省修改销售机构", salesMoveLogs.getSalerId());
                   return dataMsg;
               }
               isnoticy = true;
           }


           String moveEffect = request.getParameter("moveEffect");
           //立即生效
           if ("1".equals(moveEffect)) {
               logger.info("立即生效");
               paras.put("insuranceSalesId", salesMoveLogs.getSalerId());
               InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
               insuranceSalesInfo.setSalesGradeId(Long.valueOf(salesMoveLogs.getNextSalesGradeId()));
               insuranceSalesInfo.setSalesTeamId(Long.valueOf(salesMoveLogs.getNextTeamId()));
               insuranceSalesInfo.setSalesOrgId(Long.valueOf(salesMoveLogs.getNextOrgId()));
               insuranceSalesInfoClient.updateInsuranceSales(insuranceSalesInfo, isnoticy);

           } else {
               paras.clear();
               Long salerId = salesMoveLogs.getSalerId();
               String changeFlag = "0";
               paras.put("salerId", salerId);
               paras.put("changeFlag", changeFlag);
               List<SalesMoveLogs> haveNum = insuranceSalesInfoClient.getSalesMoveList(paras);
               if (haveNum.size() > 0) {
                   dataMsg.setRows(haveNum);
                   dataMsg.setMessageCode("999");
                   return dataMsg;
               }

               SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
               Calendar calendar = Calendar.getInstance();
               calendar.add(Calendar.MONTH, 1);
               calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
               salesMoveLogs.setMoveDate(dft.format(calendar.getTime()));
               salesMoveLogs.setCreatedTime(new Date());
               insuranceSalesInfoClient.insertMove(salesMoveLogs);
           }
           dataMsg.setMessageCode("200");

       }catch(Exception e) {
	       dataMsg.setMessageCode("400");
	       logger.error("保险组织架构[查询]异常异常",e);
	       return null;
	   }
	   return dataMsg;		
	}
	
	/**
	*
	* 员工异动审核
	* */
	@RequestMapping("/moveShenSave")
	@ResponseBody
	public DataMsg moveShenSave(HttpServletRequest request,DataMsg dataMsg){
	   try {
		   Map<String,Object> paras = new HashMap<>();
		   String moveId = request.getParameter("moveId");
		   if (StringUtil.isNotBlank(moveId)) {
               paras.put("moveId", moveId);
           }
		   SalesMoveLogs salesMove = insuranceSalesInfoClient.selectSalerMoveLogs(paras);
		   String checkStatus = request.getParameter("checkStatus");
		   if (StringUtil.isNotBlank(checkStatus)) {
			   salesMove.setCheckStatus(checkStatus);
           }
		   String checkMark = request.getParameter("checkMark");
		   if (StringUtil.isNotBlank(checkMark)) {
			   salesMove.setCheckMark(checkMark);
           }
		   salesMove.setUpdatedTime(new Date());
		   insuranceSalesInfoClient.updateSalerMove(salesMove);
		   dataMsg.setMessageCode("200");
	   }catch(Exception e) {
	       dataMsg.setMessageCode("400");
	       logger.error("保险组织架构[查询]异常异常",e);
	       return null;
	   }
	   return dataMsg;		
	}
	
    /**
     * 员工部门调整List
     */
    @RequestMapping("/salesMovePage")
    @ResponseBody
    public DataMsg salesMovePage(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> paras = new HashMap<>();
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                paras.put("pageNo", 1);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                paras.put("pageSize", 10);
            }
            String salerId = request.getParameter("salerId");
            if (StringUtil.isNotBlank(salerId)) {
                paras.put("salerId", salerId);
            }
            String insuranceSalerNo = request.getParameter("insuranceSalerNo");
            if (StringUtil.isNotBlank(insuranceSalerNo)) {
                paras.put("insuranceSalerNo",insuranceSalerNo);
            }

            //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());

            PageModel pageModel = insuranceSalesInfoClient.getSalesMovePage(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("保险组织架构[查询]异常异常",e);
        }
        return dataMsg;
    }

    /**
     * 员工黑名单
     */
    @RequestMapping("/salesBlackPage")
    @ResponseBody
    public DataMsg salesBlackPage(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> paras = new HashMap<>();
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                paras.put("pageNo", 1);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                paras.put("pageSize", 10);
            }
            String salerId = request.getParameter("salerId");
            if (StringUtil.isNotBlank(salerId)) {
                paras.put("salerId", salerId);
            }
            String insuranceSalerNo = request.getParameter("insuranceSalerNo");
            if (StringUtil.isNotBlank(insuranceSalerNo)) {
                paras.put("insuranceSalerNo",insuranceSalerNo);
            }

            //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());

            PageModel pageModel = insuranceSalesInfoClient.getSalerBlackPage(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("保险组织架构[查询]异常异常",e);
        }
        return dataMsg;
    }
    
    @RequestMapping("/salerExport")
    // @ResponseBody
    public void salerExport(HttpServletRequest request, HttpServletResponse response,DataMsg dataMsg) {
        try {
            Map<String, Object> params = new HashMap<>();
            String ids = request.getParameter("ids");
            String cols = request.getParameter("cols");
            if(StringUtil.isNotBlank(ids) && !ids.equals("all")){
            	params.put("insuranceSalesIds",ids);
            }
             String salesStatus = request.getParameter("salesStatus");
             if (StringUtil.isNotBlank(salesStatus)) {
                params.put("salesStatus", salesStatus);
            }
            String salesOrgId = request.getParameter("salesOrgId");
            if (StringUtil.isNotBlank(salesOrgId)) {
                String salesAllOrgs = salesOrgInfoClient.findEmployeeAllOrgs(salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(salesOrgId).get(0).get("SALES_ORG_CODE").toString());
                params.put("salesAllOrgs", salesAllOrgs);
            }
            String salesTeamId = request.getParameter("salesTeamId");
            if (StringUtil.isNotBlank(salesTeamId)) {
                params.put("salesTeamId", salesTeamId);
            }
            String insuranceSalerNo = request.getParameter("insuranceSalerNo");
            if (StringUtil.isNotBlank(insuranceSalerNo)) {
                params.put("likeSalerNo", insuranceSalerNo);
            }
            String insuranceSaler = request.getParameter("insuranceSaler");
            if (StringUtil.isNotBlank(insuranceSaler)) {
                params.put("insuranceSaler", insuranceSaler);
            }
            String rankSequenceId = request.getParameter("rankSequenceId");
            if (StringUtil.isNotBlank(rankSequenceId)) {
                params.put("rankSequenceId", rankSequenceId);
            }
            String salesGradeId = request.getParameter("salesGradeId");
            if (StringUtil.isNotBlank(salesGradeId)) {
                params.put("salesGradeId", salesGradeId);
            }
            getAuthDataParams(params);
            List<InsuranceSalesInfo> resultList = insuranceSalesInfoClient.insuranceSalesList(params);
            List<Object[]> dataList = Lists.newArrayListWithExpectedSize(resultList.size());
            String title = "";
            if(cols.equals("full")){
            	title = "销售人员信息-" + DateTimeUtil.getNowDateChinaString();
            }else{
            	title = "销售人员执业证及销售关系-" + DateTimeUtil.getNowDateChinaString();
            }
            Object[] objs = null;
            InsuranceSalesInfo map = new InsuranceSalesInfo();
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String[] rowsName = new String[]{};
            if(cols.equals("full")){
            	rowsName = new String[]{"序号","人员工号","员工姓名","组织机构代码","组织机构名称","营销团队代码","营销团队名称","手机","证件类型","证件号码","职级序列代码"
            			,"职级序列","职级代码","职级","入职日期","性别","银行名称","银行卡号","出生日期","家庭住址","户口所在地","电话","邮箱","QQ","微信","最高学历"
            			,"婚姻状况","上家公司名称","是否内部推荐","执业证号","执业证有效期起","执业证有效期止","紧急联系人","紧急联系人电话","紧急联系人地址","紧急联系人邮箱","紧急联系人关系"
            			,"推荐人工号","推荐人","一级处育成人工号","一级处育成人","二级处育成人工号","二级处育成人","一级部育成人工号","一级部育成人","二级部育成人工号","二级部育成人","上级管理人工号","上级管理人","直辖处处长/业务经理工号","直辖处处长/业务经理","直辖部部长/业务总监工号","直辖部部长/业务总监","备注", "状态"};
            }else{
            	rowsName = new String[]{"序号","人员工号","员工姓名","性别","证件类型","证件号码","执业证号","执业证有效期起","执业证有效期止","推荐人工号","一级处育成人工号","二级处育成人工号","一级部育成人工号","二级部育成人工号","直辖处处长/业务经理工号","直辖部部长/业务总监工号"};
            }
            for (int i = 0; i < resultList.size(); i++) {
                map = resultList.get(i);
                /*员工关系*/
                InsuranceSalesInfo salesinfoDb = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoTj = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoYc = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoSj = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoJc = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoFd = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoYcCF = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoYcCS = new InsuranceSalesInfo();
                 InsuranceSalesInfo salesinfoYcBF = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoYcBS = new InsuranceSalesInfo();
                 InsuranceSalesInfo salesinfoCz = new InsuranceSalesInfo();
                InsuranceSalesInfo salesinfoBz = new InsuranceSalesInfo();
                Map<String, Object> oo = new HashMap<>();
                oo.put("dbSalesId", map.getDbSalesId());
                oo.put("tjSalesId", map.getTjSalesId());
                oo.put("ycSalesId", map.getYcSalesId());
                oo.put("sjSalesId", map.getSjSalesId());
                oo.put("jcSalesId", map.getJcSalesId());
                oo.put("fdSalesId", map.getFdSalesId());
                oo.put("ycCFirstGenerId",map.getYcCFirstGener());
                oo.put("ycCSecondGenerId", map.getYcCSecondGener());
                oo.put("ycBFirstGenerId", map.getYcBFirstGener());
                oo.put("ycBSeconGenerId", map.getYcBSecondGener());
                oo.put("directGroupCId", map.getDirectGroupC());
                oo.put("directDeptBId", map.getDirectDeptB());
                List<Object> ships = findShips(oo);
                if(ships!=null && ships.size()>0){
                	Object ss = ships.get(0);
                	Object salesinfoDbStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoDb");
                	if(salesinfoDbStr != null){
	                	JSONObject salesinfoDbJson = JSON.parseObject(salesinfoDbStr.toString());
	                	salesinfoDb = JSON.toJavaObject(salesinfoDbJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoTjStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoTj");
                	if(salesinfoTjStr != null){
	                	JSONObject salesinfoTjJson = JSON.parseObject(salesinfoTjStr.toString());
	                	salesinfoTj = JSON.toJavaObject(salesinfoTjJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoYcStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoYc");
                	if(salesinfoYcStr != null){
	                	JSONObject salesinfoYcJson = JSON.parseObject(salesinfoYcStr.toString());
	                	salesinfoYc = JSON.toJavaObject(salesinfoYcJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoSjStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoSj");
                	if(salesinfoSjStr != null){
	                	JSONObject salesinfoSjJson = JSON.parseObject(salesinfoSjStr.toString());
	                	salesinfoSj = JSON.toJavaObject(salesinfoSjJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoJcStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoJc");
                	if(salesinfoJcStr != null){
	                	JSONObject salesinfoJcJson = JSON.parseObject(salesinfoJcStr.toString());
	                	salesinfoJc = JSON.toJavaObject(salesinfoJcJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoFdStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoFd");
                	if(salesinfoFdStr != null){
	                	JSONObject salesinfoFdJson = JSON.parseObject(salesinfoFdStr.toString());
	                	salesinfoFd = JSON.toJavaObject(salesinfoFdJson,InsuranceSalesInfo.class);
                	}

                	Object salesinfoYcCFStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoYcCF");
                	if(salesinfoYcCFStr != null){
	                	JSONObject salesinfoYcCFJson = JSON.parseObject(salesinfoYcCFStr.toString());
	                	salesinfoYcCF = JSON.toJavaObject(salesinfoYcCFJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoYcCSStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoYcCS");
                	if(salesinfoYcCSStr != null){
	                	JSONObject salesinfoYcCSJson = JSON.parseObject(salesinfoYcCSStr.toString());
	                	salesinfoYcCS = JSON.toJavaObject(salesinfoYcCSJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoYcBFStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoYcBF");
                	if(salesinfoYcBFStr != null){
	                	JSONObject salesinfoYcBFJson = JSON.parseObject(salesinfoYcBFStr.toString());
	                	salesinfoYcBF = JSON.toJavaObject(salesinfoYcBFJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoYcBSStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoYcBS");
                	if(salesinfoYcBSStr != null){
	                	JSONObject salesinfoYcBSJson = JSON.parseObject(salesinfoYcBSStr.toString());
	                	salesinfoYcBS = JSON.toJavaObject(salesinfoYcBSJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoCzStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoCz");
                	if(salesinfoCzStr != null){
	                	JSONObject salesinfoCzJson = JSON.parseObject(salesinfoCzStr.toString());
	                	salesinfoCz = JSON.toJavaObject(salesinfoCzJson,InsuranceSalesInfo.class);
                	}
                	Object salesinfoBzStr = net.sf.json.JSONObject.fromObject(ss).get("salesinfoBz");
                	if(salesinfoBzStr != null){
	                	JSONObject salesinfoBzJson = JSON.parseObject(salesinfoBzStr.toString());
	                	salesinfoBz = JSON.toJavaObject(salesinfoBzJson,InsuranceSalesInfo.class);
                	}
                }   
                //组织机构-销售团队-职级序列-职级
                params.clear();
                params.put("salesOrgId", map.getSalesOrgId());
                SalesOrgInfo salesOrgInfo = salesOrgInfoClient.selectById(params);
                params.clear();
                params.put("salesTeamId", map.getSalesTeamId());
                if(null!=map.getSalesTeamId()){

                }
                SalesTeamInfo salesTeamInfo = salesTeamInfoClient.selectById(params);
                params.clear();
                params.put("sequenceId", map.getRankSequenceId());
                RankSequence rankSequence = rankSequenceClient.selectById(params);
                params.clear();
                params.put("salesGradeId", map.getSalesGradeId());
                SalesGrade salesGrade = salesGradeClient.selectById(params);   
                //总监
                params.clear();
                params.put("salesOrgId", map.getSalesOrgId());
                params.put("salesGradeId", "1");
                params.put("effective", "effective");
                List<InsuranceSalesInfo> zjList = insuranceSalesInfoClient.insuranceSalesList(params);
                //紧急联系人
                params.clear();
                params.put("salesId", map.getInsuranceSalesId());
                params.put("deleteFlag", "0");
                params.put("shipType", "2");
                List<SalesRelative> jjlxrList = insuranceSalesInfoClient.findSalesRelatives(params);
                
                objs = new Object[rowsName.length];
                objs[0] = i + 1;//序号
                objs[1] = map.getInsuranceSalerNo();//工号
                objs[2] = map.getInsuranceSaler();//姓名
                  HashMap<String, Object> parm = new HashMap<>();
                if(cols.equals("full")){
                	objs[3] = salesOrgInfo.getSalesOrgCode();
                	objs[4] = map.getSalesOrgName();//组织机构名
                	objs[5] = salesTeamInfo.getSalesTeamCode();
                	objs[6] = map.getSalesTeamName();//营销团队名
                	objs[7] = map.getMobile();
                	 parm.clear();
                    parm.put("dictType",Constants.DICT_TYPE_CARD);
                    parm.put("dictId",map.getCardType());
                    Map<String, Object> cardDict = systemDictFeignClient.findSystemDictByTypeAndCode(parm);
                	objs[8] = cardDict == null?map.getCardType():cardDict.get("dictName").toString();
                	objs[9] = map.getCardNo();
                	objs[10] = rankSequence.getSequenceCode();
                	objs[11] = rankSequence.getSequenceName();

                	String salesGradeCode = "";
                	String salesGradeName = "";
                	if(salesGrade != null){
                        salesGradeCode = salesGrade.getSalesGradeCode();
                        salesGradeName = salesGrade.getSalesGradeName();
                    }
                	objs[12] = salesGradeCode;
                	objs[13] = salesGradeName;
                	objs[14] = map.getJoinDate();
                    parm.clear();
                    parm.put("dictType",Constants.DICT_TYPE_SEX);
                    parm.put("dictCode",map.getSex());
                    Map<String, Object> sexDict = systemDictFeignClient.findSystemDictByTypeAndCode(parm);
                    objs[15] = sexDict == null?map.getSex(): sexDict.get("dictName").toString();
                     parm.clear();
                    parm.put("dictType",Constants.DICT_TYPE_BANK);
                    parm.put("dictId",map.getBankChannel());
                    Map<String, Object> bankDict = systemDictFeignClient.findSystemDictByTypeAndCode(parm);
                	objs[16] = bankDict == null ?map.getBankChannel() : bankDict.get("dictName").toString();
                	objs[17] = map.getBankCardNo();
                	objs[18] = map.getBirthday();
                	objs[19] = map.getAddress();
                	objs[20] = map.getHouseholdRegPlace();
                	objs[21] = map.getCellPhone();
//                    String dutyTypeText = "";
//                    if(null != map.getDutyType()) {
//                        dutyTypeText = "0".equals(map.getDutyType()) ? "兼职" : "全职";
//                    }
//                	objs[22] = dutyTypeText;
                	objs[22] = map.getEmail();
                	objs[23] = map.getQqNumber();
                	objs[24] = map.getWechatNumber();
                	 parm.clear();
                    parm.put("dictType",Constants.DICT_TYPE_EDU);
                    parm.put("dictId",map.getEducationalBg());
                    Map<String, Object> eduDict = systemDictFeignClient.findSystemDictByTypeAndCode(parm);
                	objs[25] = eduDict == null?map.getEducationalBg():eduDict.get("dictName").toString();

                    String maritalStatusText = "";
                    if(null != map.getMaritalStatus()) {
                        maritalStatusText = "0".equals(map.getMaritalStatus()) ? "未婚" : "已婚";
                    }

                	objs[26] = maritalStatusText;
                	objs[27] = map.getPreCompany();

                    String internalReferralText = "";
                    if(null != map.getInternalReferral()) {
                        internalReferralText = "0".equals(map.getInternalReferral()) ? "是" : "否";
                    }
                	objs[28] = internalReferralText;
                	objs[29] = map.getCertificateNumber();
                	objs[30] = map.getCertificateStartDate();
                	objs[31] = map.getCertificateEndDate();
                	if(jjlxrList!=null && jjlxrList.size()>0){
	                	objs[32] = jjlxrList.get(0).getPersonName();
	                	objs[33] = jjlxrList.get(0).getShipCellPhone();
	                	objs[34] = jjlxrList.get(0).getShipAddr();
	                	objs[35] = jjlxrList.get(0).getShipEmail();
	                	objs[36] = jjlxrList.get(0).getRelationShip();
                	}else{
	                	objs[32] = "-";
	                	objs[33] = "-";
	                	objs[34] = "-";
	                	objs[35] = "-";
	                	objs[36] = "-";
                	}
                	objs[37] = salesinfoTj.getInsuranceSalerNo();
                	objs[38] = salesinfoTj.getInsuranceSaler();
                	objs[39] = salesinfoYcCF.getInsuranceSalerNo();
                	objs[40] = salesinfoYcCF.getInsuranceSaler();
                	objs[41] = salesinfoYcCS.getInsuranceSalerNo();
                	objs[42] = salesinfoYcCS.getInsuranceSaler();
                	objs[43] = salesinfoYcBF.getInsuranceSalerNo();
                	objs[44] = salesinfoYcBF.getInsuranceSaler();
                	objs[45] = salesinfoYcBS.getInsuranceSalerNo();
                	objs[46] = salesinfoYcBS.getInsuranceSaler();
                	objs[47] = salesinfoSj.getInsuranceSalerNo();
                	objs[48] = salesinfoSj.getInsuranceSaler();
                	objs[49] = salesinfoCz.getInsuranceSalerNo();
                	objs[50] = salesinfoCz.getInsuranceSaler();
                	objs[51] = salesinfoBz.getInsuranceSalerNo();
                	objs[52] = salesinfoBz.getInsuranceSaler();
                	/*if(zjList!=null && zjList.size()>0){
	                	objs[44] = zjList.get(0).getInsuranceSalerNo();
	                	objs[45] = zjList.get(0).getInsuranceSaler();                		
                	}else{
	                	objs[44] = "-";
	                	objs[45] = "-";
                	}*/
                	objs[53] = map.getRemark();
                	String mapSalesStatus = map.getSalesStatus();
                	String salesStatusCh  = "-";
                	if(!StringUtil.isEmpty(mapSalesStatus)) {
                        switch (mapSalesStatus){
                            case "0":
                                salesStatusCh = "试用";
                                break;
                            case "1":
                                salesStatusCh = "正式";
                                break;
                            case "2":
                                salesStatusCh = "离职";
                                break;
                            case "3":
                                salesStatusCh = "黑名单";
                                break;
                        }
                    }
                	objs[54] = salesStatusCh;


                }else{
                	objs[3] = map.getSex();
                	objs[4] = map.getCardType();
                	objs[5] = map.getCardNo();
                	objs[6] = map.getCertificateNumber();
                	objs[7] = map.getCertificateStartDate();
                	objs[8] = map.getCertificateEndDate();
                	objs[9] = salesinfoTj.getInsuranceSalerNo();
                	objs[10] = salesinfoYcCF.getInsuranceSalerNo();
                	objs[11] = salesinfoYcCS.getInsuranceSalerNo();
                	objs[12] = salesinfoYcBF.getInsuranceSalerNo();
                	objs[13] = salesinfoYcBS.getInsuranceSalerNo();
                	objs[14] = salesinfoCz.getInsuranceSalerNo();
                	objs[15] = salesinfoBz.getInsuranceSalerNo();

                	/*if(zjList!=null && zjList.size()>0){
                		objs[12] = zjList.get(0).getInsuranceSalerNo();
                	}else{
                		objs[12] = "-";
                	}*/
                }
                dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            ex.exportBigData(response);
            /*if (org.apache.commons.collections.CollectionUtils.isNotEmpty(resultList)) {
                ex.exportToCache(response, request.getSession().getServletContext().getRealPath("/download/"));
                dataMsg.setMessageCode("200");
                dataMsg.setData(title);
            }else{
                dataMsg.setMessageCode("300");
            }*/
        } catch (Exception e) {
            logger.error("导出[销售人员] | 异常", e);

            dataMsg.setMessageCode("400");
        }
    }    

    @RequiresPermissions("salesAssess:list")
    @RequestMapping("salesAssessPage")
    public String toSalesAssessPage(HttpServletRequest request){
        String insuranceSalesId = request.getParameter("id");
        request.setAttribute("insuranceSalesId", insuranceSalesId);
        return "salesTeamInfoPage/salesAssessList";
    }

    /**
     * <p>根据查询条件分页员工个人考核记录</p>
     * @param insuranceSalesId  员工id
     * @param request
     * @return
     */
    @RequestMapping(value = "/salesAssessPage/{insuranceSalesId}", method = RequestMethod.POST)
    @ResponseBody
    public DataMsg findSalesAssessByInsuranceSalesId(@PathVariable("insuranceSalesId")Long insuranceSalesId,HttpServletRequest request){
        DataMsg dataMsg = new DataMsg();
        try {
            String pageNo = request.getParameter("pageNo");
            String pageSize = request.getParameter("pageSize");
            PageModel model = insuranceSalesInfoClient.findSalesAssessByInsuranceSalesId(insuranceSalesId, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(model.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("员工个人分页查询考核记录异常：" + e);
        }
        return dataMsg;
    }

    @RequestMapping("toSalesAssess")
    public String toSalesAssess(HttpServletRequest request){
        String insuranceSalesId = request.getParameter("id");
        request.setAttribute("insuranceSalesId", insuranceSalesId);
        HashMap<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("insuranceSalesId", insuranceSalesId);
        InsuranceSalesInfo info = insuranceSalesInfoClient.selectById(map);
        if(null != info){
            request.setAttribute("insuranceSalerNo", info.getInsuranceSalerNo());
            request.setAttribute("insuranceSaler", info.getInsuranceSaler());
            request.setAttribute("salesOrgName", info.getSalesOrgName());
            request.setAttribute("salesTeamName", info.getSalesTeamName());
            request.setAttribute("rankSequenceId", info.getRankSequenceId());
            request.setAttribute("salesGradeId", info.getSalesGradeId());
        }
        String salesAssessId = request.getParameter("salesAssessId");
        if(StringUtils.isNotBlank(salesAssessId)){
            SalesAssess salesAssess = insuranceSalesInfoClient.findSalesAssessById(Long.valueOf(salesAssessId));
            if(null != salesAssess){
                request.setAttribute("salesAssessId", salesAssessId);
                request.setAttribute("salesGradeName", salesAssess.getSalesGradeName());
                request.setAttribute("salesGradeId2", salesAssess.getSalesGradeId());
                request.setAttribute("condition1", salesAssess.getCondition1());
                request.setAttribute("condition2", salesAssess.getCondition2());
                request.setAttribute("condition3", salesAssess.getCondition3());
                request.setAttribute("condition4", salesAssess.getCondition4());
                request.setAttribute("condition5", salesAssess.getCondition5());
                request.setAttribute("condition6", salesAssess.getCondition6());
                request.setAttribute("condition7", salesAssess.getCondition7());
                request.setAttribute("condition8", salesAssess.getCondition8());
            }
        }
        return "salesTeamInfoPage/salesAssess";
    }

    /**
     * <p>新增员工考核记录</p>
     * @param salesAssess   考核记录
     */
    @RequestMapping(value = "/salesAssess", method = RequestMethod.POST)
    @ResponseBody
    public DataMsg saveSalesAssess(@RequestBody SalesAssess salesAssess){
        DataMsg dataMsg = new DataMsg();
        try {
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            salesAssess.setCreatedBy(employee.getName());
            insuranceSalesInfoClient.saveSalesAssess(salesAssess);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            logger.error("新增员工考核记录异常"+e);
            dataMsg.setMessageCode("400");
        }
        return dataMsg;
    }

    /**
     * <p>根据考核记录id查询员工考核记录</p>
     * @param salesAssessId   考核记录id
     */
    @RequestMapping(value = "/salesAssess/{salesAssessId}", method = RequestMethod.GET)
    @ResponseBody
    public SalesAssess findSalesAssessById(@PathVariable("salesAssessId") Long salesAssessId){
        return insuranceSalesInfoClient.findSalesAssessById(salesAssessId);
    }

    /**
     * <p>根据考核记录id更新员工考核记录</p>
     * @param salesAssess   考核记录
     */
    @RequestMapping(value = "/salesAssess", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateSalesAssess(@RequestBody SalesAssess salesAssess){
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        salesAssess.setUpdatedBy(employee.getName());
        return insuranceSalesInfoClient.updateSalesAssess(salesAssess);
    }



    /**
     * 查看员工详情
     * */
    @RequestMapping("/selectInsuranceSalesOne")
    @ResponseBody
    public Map<String,Object> selectInsuranceSalesOne (HttpServletRequest request){
        Map<String,Object> params = new HashMap<String,Object>();
        String insuranceSalerNo = request.getParameter("insuranceSalerNo");
        params.put("insuranceSalerNo",insuranceSalerNo);
        return  insPolicyInsuredPersonFeign.selectInsuranceSalesOne(params);
    }

    /**
     *@描述  销售人员批量导入
     *@创建人 qin lina
     *@创建时间 2020/3/10
     */
    @RequestMapping(value = "/importSalesInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importSalesInfo(MultipartFile file) throws Exception {
         Map<String,Object> msg =new HashMap<String,Object>();
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
                //销售人员数据集
                List<List<String>> salesInfoRowList = new ArrayList<>();
                Sheet att = sheetMap.get("salesInfo");
                int attNumberOfCells = att.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < att.getPhysicalNumberOfRows(); j++){
                    Row row = att.getRow(j);
                    if(row!=null){
                        List<String> salesInfoList = new ArrayList<>();
                        for (int k = 0; k <= attNumberOfCells; k++) {//获取每个单元格
                             salesInfoList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=salesInfoList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                           salesInfoRowList.add(salesInfoList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                //紧急联系人数据集
                List<List<String>> relativeRowList = new ArrayList<>();
                Sheet relativ = sheetMap.get("relativeList");
                int relativOfCells = relativ.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < relativ.getPhysicalNumberOfRows(); j++){
                    Row row = relativ.getRow(j);
                    if(row!=null){
                        List<String> relativInfoList = new ArrayList<>();
                        for (int k = 0; k <= relativOfCells; k++) {//获取每个单元格
                            String cellFormat = ImportUtil.cellFormat(row.getCell(k));
                            relativInfoList.add(cellFormat);
                        }
                        List<String> filtered=relativInfoList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                            relativeRowList.add(relativInfoList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(salesInfoRowList)){
                    //组装销售人员数据
                    List<Map<String,Object>> salesInfo_List = new LinkedList<Map<String,Object>>();
                    List<Map<String,Object>> relativeRow_List = new LinkedList<Map<String,Object>>();
                    Map<String,Object> p = new HashMap<String,Object>();
                    //销售人员入库
                    packSalesInfo(salesInfoRowList,salesInfo_List);
                    String salesInfo_string = JSONObject.toJSONString(salesInfo_List);
                    //转换为json串
                    p.put("salesInfo_string", salesInfo_string);
                    if(!CollectionUtils.isEmpty(relativeRowList)){
                        //紧急联系人入库
                        packRelativInfo(relativeRowList,relativeRow_List);
                    }
                      String relativeRow_string = JSONObject.toJSONString(relativeRow_List);
                      p.put("relativeRow_string",relativeRow_string);
                    msg = insuranceSalesInfoClient.insertImportList(p);

                }else {
                    msg.put("code","0000");
                }
            }else {
                msg.put("code","0000");
            }
        } catch (CheckException e) {
            msg.put("code","500");
            msg.put("error",e.getMessage());
            logger.error("导入销售人员|异常：{}", e);
        } catch (Exception e) {
            logger.error("导入销售人员|异常：{}", e);
            msg.put("code","400");
    }
        return msg;
}

    private void packRelativInfo(List<List<String>> relativeRowList, List<Map<String, Object>> srelativeRow_list) {
        relativeRowList.forEach(relativeRow -> {
            Map<String,Object> relativeRowMap = Maps.newHashMapWithExpectedSize(15);
            int[] filedindexs = {0};
            String[] filedNames = {"销售人员证件号码"};
            List<String> validateMust = ImportUtil.validateMust(relativeRow, filedindexs, filedNames);
            if (!CollectionUtils.isEmpty(validateMust)){
                throw new CheckException("证件号码不能为空:"+relativeRow.get(0)+",请录入必填项："+validateMust.toString());
            }
            relativeRowMap.put("cardNo",relativeRow.get(0));//证件号
            relativeRowMap.put("personName",relativeRow.get(1));//联系人姓名
            relativeRowMap.put("shipCellPhone",relativeRow.get(2));//联系人电话
            relativeRowMap.put("relationShip",relativeRow.get(3));//关系
            relativeRowMap.put("shipAddr",relativeRow.get(4));//联系人地址
            relativeRowMap.put("shipEmail",relativeRow.get(5));//联系人邮箱
            relativeRowMap.put("remark",relativeRow.get(6));//备注
            relativeRowMap.put("createdTime",DateUtil.getTimeNormalString(new Date()));//创建时间
            relativeRowMap.put("shipType","2");
            srelativeRow_list.add(relativeRowMap);
            });


    }

    /**
     * 解析新增销售人员数据
     * @param salesInfoRowList
     * @param salesInfo_list
     */
    private void packSalesInfo(List<List<String>> salesInfoRowList, List<Map<String, Object>> salesInfo_list) {
         //校验唯一性
        ArrayList<String> cardNoList = new ArrayList<>();
         ArrayList<String> mobileList = new ArrayList<>();
        HashMap<Long, Long> maxSalerNoForOrg = new HashMap<>();
        salesInfoRowList.forEach(salesInfo -> {
                     Map<String,Object> salesInfoMap = Maps.newHashMapWithExpectedSize(15);
//                      int[] filedindexs = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
                      int[] filedindexs = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
                     String[] filedNames = {"组织机构名称","销售团队名称","员工姓名","入职职级序列","入职职级","入司时间","证件类型","证件号码","手机号码","性别","出生日期","银行名称","开户行","银行卡号","政治面貌","所在省份","所在城市","最高学历"};
                     List<String> validateMust = ImportUtil.validateMust(salesInfo, filedindexs, filedNames);
                     if (!CollectionUtils.isEmpty(validateMust)){
                           throw new CheckException("证件号:"+salesInfo.get(8)+",请录入必填项："+validateMust.toString());
                    }

//            String cooperationType = salesInfo.get(0);
//            if ("直营".equals(cooperationType)){
//                 salesInfoMap.put("cooperationType","0");//所属机构性质
//            }else if ("加盟".equals(cooperationType)){
//                 salesInfoMap.put("cooperationType","1");//所属机构性质
//            }else {
//                throw new CheckException("所属机构性质："+cooperationType+",不存在");
//            }
            //组织机构名称
           Map<String,Object> paras = new HashMap<>();
           paras.put("salesOrgName",salesInfo.get(0));
            List<SalesOrgInfo> salesOrgs = salesOrgInfoClient.getParents(paras);
              if (CollectionUtils.isEmpty(salesOrgs)){
                 throw new CheckException("组织机构名称："+salesInfo.get(0)+",不存在");
              }
              //TODO 根据组织机构校验权限
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
            //数据权限相关
            String myDeptNo = employee.getDeptNo();
            List<String> myAllOrgIdList = null;
            //只有admin没有组织机构 如果是admin登录 myAllOrgIdList = null 不做权限控制
            if (!"admin".equals(employee.getEmployeeNo())&&!"superAdmin".equals(employee.getEmployeeNo())) {
               String myAllOrgIds = salesOrgInfoClient.findEmployeeAllOrgs(myDeptNo);
                 //登录人所在组织机构
               myAllOrgIdList = Arrays.asList(myAllOrgIds.split(","));
            }
            if (myAllOrgIdList != null) {
                //判断所录人员是否在登录人的组织机构下
                if (!myAllOrgIdList.contains(salesOrgs.get(0).getSalesOrgId().toString())) {
                    throw new CheckException("组织机构名称：" + salesInfo.get(0) + ",不在操作人的权限范围内，请核查");
                }
            }
             salesInfoMap.put("salesOrgId", salesOrgs.get(0).getSalesOrgId());//组织机构名称

            //销售团队名称
            paras.clear();
          paras.put("salesTeamName",salesInfo.get(1));
          paras.put("salesOrgId",salesOrgs.get(0).getSalesOrgId());
          List<SalesTeamInfo> parents = salesTeamInfoClient.selectByCondition(paras);
          if (CollectionUtils.isEmpty(parents)){
               throw new CheckException("销售团队名称："+salesInfo.get(1)+",在组织机构："+salesInfo.get(0)+"下不存在");
           }
           salesInfoMap.put("salesTeamId",parents.get(0).getSalesTeamId());//销售团队名称

            salesInfoMap.put("insuranceSaler",salesInfo.get(2));//员工姓名
            //入职职级序列
            paras.clear();
            paras.put("sequenceName",salesInfo.get(3));
            List<RankSequence> rankSequenceList = rankSequenceClient.getRankSequenceList(paras);
            if (CollectionUtils.isEmpty(rankSequenceList)){
                throw new CheckException("入职职级序列："+salesInfo.get(3)+",不存在");
            }
            salesInfoMap.put("rankSequenceId",rankSequenceList.get(0).getSequenceId());//入职职级序列

            //入职职级
             paras.clear();
            String salesGradeName = salesInfo.get(4);
            paras.put("salesGradeName",salesGradeName);
            List<SalesGrade> salesGradeList = salesGradeClient.getSalesGradeList(paras);
            if (CollectionUtils.isEmpty(salesGradeList)){
                throw new CheckException("入职职级："+salesInfo.get(4)+",不存在");
            }
            Long salesGradeId = salesGradeList.get(0).getSalesGradeId();
            salesInfoMap.put("salesGradeId",salesGradeId);//入职职级
            if (!DateTimeUtil.isLegalDate(salesInfo.get(5))){
                throw new CheckException("员工证件号:" + salesInfo.get(7) +"的入司时间格式不正确,请填写yyyy-MM-dd格式的时间");
            }

            salesInfoMap.put("joinDate",salesInfo.get(5));//入司时间
            salesInfoMap.put("passDate",salesInfo.get(5));//入司时间
            //证件类型
             String cardType = salesInfo.get(6);  //证件类型
             paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_CARD);
            paras.put("dictName",cardType);
            Map<String, Object> cardDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(cardDict)){
                throw new CheckException("员工证件号:" + salesInfo.get(7) +"证件类型" + salesInfo.get(6) + ",不存在请参考字典值");
            }
            String cardDictCode = cardDict.get("dictCode").toString();
            salesInfoMap.put("cardType",cardDict.get("dictId").toString());//证件类型
            String cardNo =  salesInfo.get(7);

            boolean regFlag = true;
            if("1".equals(cardDictCode) || "5".equals(cardDictCode)){
                regFlag = cardNo.matches(Constants.DATA_FORMAT_IDCARD);
            }else if("2".equals(cardDictCode)){
                regFlag = cardNo.matches(Constants.DATA_FORMAT_HKCARD);
            }else if("3".equals(cardDictCode)){
                regFlag = cardNo.matches(Constants.DATA_FORMAT_PASSPORTCARD);
            }else if("4".equals(cardDictCode)){
                regFlag = cardNo.matches(Constants.DATA_FORMAT_OFFICERCARD);
            }
            if(!regFlag){
                throw new CheckException("证件号:"+cardNo+"证件号格式不正确");
            }
            if (cardNoList.contains(cardNo)){
                throw new CheckException("所导入的数据中证件号："+cardNo+"存在重复值,请核查！");
            }
            paras.clear();
            paras.put("cardNo",cardNo);
            int num = insuranceSalesInfoClient.findMaxSalerNo(paras);
            if(num>0){
                throw new CheckException("证件号："+cardNo+"系统中已存在");
            }
            salesInfoMap.put("cardNo",cardNo);//证件号码
            cardNoList.add(cardNo);

            boolean matches1 = salesInfo.get(8).matches(Constants.DATA_FORMAT_MOBLIE);
            if(!matches1){
                throw new CheckException("手机号码:"+salesInfo.get(8)+",手机号码格式不正确");
            }
            if (mobileList.contains(salesInfo.get(8))){
                throw  new CheckException("导入数据中手机号码："+salesInfo.get(8)+"存在重复值，请核查!");
            }
            paras.clear();
            paras.put("mobile",salesInfo.get(8));
             int num1 = insuranceSalesInfoClient.findMaxSalerNo(paras);
            if(num1>0){
                throw new CheckException("手机号码:"+salesInfo.get(8)+"手机号码已存在");
            }

            salesInfoMap.put("mobile",salesInfo.get(8));//手机号码
            mobileList.add(salesInfo.get(8));

            String sex = salesInfo.get(9);
             paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_SEX);
            paras.put("dictName",sex);
            Map<String, Object> sexDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(sexDict)){
                throw new CheckException("员工证件号:" + salesInfo.get(7) +"性别" + salesInfo.get(9) + ",不存在请参考字典值");
            }
            salesInfoMap.put("sex",sexDict.get("dictCode").toString());//性别
             if (!DateTimeUtil.isLegalDate(salesInfo.get(10))){
                throw new CheckException("员工证件号:" + salesInfo.get(7) +"的出生日期格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            salesInfoMap.put("birthday",salesInfo.get(10));//出生日期
            String bankChannel = salesInfo.get(11);
             paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_BANK);
            paras.put("dictName",bankChannel);
            Map<String, Object> bankDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(bankDict)){
                throw new CheckException("员工证件号:" + salesInfo.get(7) +"银行名称" + salesInfo.get(11) + ",不存在请参考字典值");
            }
            salesInfoMap.put("bankChannel",bankDict.get("dictId").toString());//银行名称
            salesInfoMap.put("openBank",salesInfo.get(12));//开户行
             boolean matches3 = salesInfo.get(13).matches(Constants.DATA_FORMAT_NUMBER);
             if(!matches3){
                 throw new CheckException("证件号:"+cardNo+"银行卡号"+salesInfo.get(13)+",不为数字");
             }
             if (salesInfo.get(14).length()>20) {
                 throw new CheckException("证件号:"+cardNo+"银行卡号"+salesInfo.get(13)+",超过20位");
             }

            salesInfoMap.put("bankCardNo",salesInfo.get(13));//银行卡号
            String politicalAppearance = salesInfo.get(14);
             paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_FACE);
            paras.put("dictName",politicalAppearance);
            Map<String, Object> politicalAppearanceDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(politicalAppearanceDict)){
                throw new CheckException("员工证件号:" + salesInfo.get(7) +"政治面貌" + salesInfo.get(14) + ",不存在请参考字典值");
            }
            salesInfoMap.put("politicalAppearance",politicalAppearanceDict.get("dictId").toString());//政治面貌
            String province = salesInfo.get(15);//所在省
            String city = salesInfo.get(16);//所在市
           // String county = salesInfo.get(18);//所在区县
            District districtProvince = systemDictFeignClient.findDistrictByDistrict(province);
            if(null==districtProvince){
                throw new CheckException("证件号码："+salesInfo.get(7)+"所在省份未查找到");
            }
            District districtCity = systemDictFeignClient.findDistrictByDistrictAndParentId(city, String.valueOf(districtProvince.getDistrictid()));
            if (districtCity == null) {
                throw new CheckException("证件号码："+salesInfo.get(7)+"所在市未查找到");
            }
          /*  District districtCounty = systemDictFeignClient.findDistrictByDistrictAndParentId(county, String.valueOf(districtCity.getDistrictid()));
            if (districtCounty == null) {
                throw new CheckException("证件号码："+salesInfo.get(8)+"所在区县未查找到");
            }*/
            String areaCode = districtCity.getSortcode();
            salesInfoMap.put("areaCode",areaCode);//地区编码
            String educationalBg = salesInfo.get(17);
            paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_EDU);
            paras.put("dictName",educationalBg);
            Map<String, Object> eduDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(eduDict)){
                throw new CheckException("员工证件号:" + salesInfo.get(7) +"最高学历" + salesInfo.get(17) + ",不存在请参考字典值");
            }
            salesInfoMap.put("educationalBg",eduDict.get("dictId").toString());//最高学历
            salesInfoMap.put("certificateNumber",salesInfo.get(18));//执业证号

            //网级关系 暂时注释
           /*  paras.put("insuranceSalesId", transSalesId(tjSalesName,Constants.RELATION_SALE_NAME_TJ,myAllOrgIdList));
            InsuranceSalesInfo pinsuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            paras.clear();
            paras.put("pTreeCode", pinsuranceSalesInfo.getNowTreeCode());
            paras.put("treeCodeLen", pinsuranceSalesInfo.getNowTreeCode().length() + 4);
            int preTreeCode = insuranceSalesInfoClient.findMaxTreeCode(paras);
            if (preTreeCode == 0) {
                salesInfoMap.put("nowTreeCode",pinsuranceSalesInfo.getNowTreeCode() + "1000");
            } else {
                 salesInfoMap.put("nowTreeCode",pinsuranceSalesInfo.getNowTreeCode() + (preTreeCode + 1) + "");
            }*/
           salesInfoMap.put("nowTreeCode",null);

//            //是否有待业证
//           String leavingCertificate = salesInfo.get(17);
//            if ("否".equals(leavingCertificate)){
//                salesInfoMap.put("leavingCertificate","0");
//            }else if ("是".equals(leavingCertificate)){
//                salesInfoMap.put("leavingCertificate","1");
//            }
//            //是否有工作室
//            String havingWorkGroup = salesInfo.get(18);
//            if ("否".equals(havingWorkGroup)){
//                salesInfoMap.put("havingWorkGroup","0");
//            }else if ("是".equals(havingWorkGroup)){
//                salesInfoMap.put("havingWorkGroup","1");
//            }

            salesInfoMap.put("createdTime", DateUtil.getTimeNormalString(new Date()));//创建时间
            String insuranceSalerNo = this.creatSalerNo(salesOrgs.get(0).getSalesOrgId(), salesInfo.get(9),maxSalerNoForOrg);
            salesInfoMap.put("insuranceSalerNo",insuranceSalerNo);//员工编号
            salesInfoMap.put("salesStatus","0");//状态
            salesInfo_list.add(salesInfoMap);

        });
    }



    private void checkSjSaleId(String salerLevel, String sjSaleName) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalerNo", sjSaleName);
        List<InsuranceSalesInfo> list = insuranceSalesInfoClient.insuranceSalesList(paras);
        if (CollectionUtils.isEmpty(list)) {
            throw new CheckException("关系人员：" + sjSaleName + "不存在");
        }
        InsuranceSalesInfo insuranceSalesInfo = list.get(0);
        String sjLevel = insuranceSalesInfo.getSalesGradeId().toString();
        if (salerLevel.equals("1") || salerLevel .equals( "2") || salerLevel.equals("7")) {
            if (!sjLevel.equals("1")) {
                throw new CheckException("上级职级录入错误，上级职级不能跨级或低于员工职级，上级职级员工编号："+sjSaleName);
            }
        } else if (salerLevel .equals("3")  || salerLevel.equals("6") ) {
            if (!sjLevel.equals( "2") && !sjLevel.equals( "7")) {
                throw new CheckException("上级职级录入错误，上级职级不能跨级或低于员工职级，上级职级员工编号："+sjSaleName);
            }
        } else if (salerLevel.equals("4") || salerLevel.equals("5")) {
            if (!sjLevel.equals("3")  && !sjLevel.equals("6")) {
                throw new CheckException("上级职级录入错误，上级职级不能跨级或低于员工职级，上级职级员工编号："+sjSaleName);
            }
        }
    }

    @RequestMapping(value = "/importUpdateSalesInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importUpdateSalesInfo(MultipartFile file) throws Exception {
        Map<String, Object> msg = new HashMap<String, Object>();
        try {
            if (!file.isEmpty()) {
                FileInputStream in = (FileInputStream) file.getInputStream();
                Workbook workbook = WorkbookFactory.create(in);  //兼容xls和xlsx
                Map<String, Sheet> sheetMap = new HashMap<String, Sheet>();
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    String sheetName = workbook.getSheetName(i);
                    Sheet sheetAt = workbook.getSheetAt(i);
                    sheetMap.put(sheetName, sheetAt);
                }
                //销售人员数据集
                List<List<String>> salesInfoRowList = new ArrayList<>();
                Sheet att = sheetMap.get("salesInfo");
                int attNumberOfCells = att.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < att.getPhysicalNumberOfRows(); j++) {
                    Row row = att.getRow(j);
                    if (row != null) {
                        List<String> salesInfoList = new ArrayList<>();
                        for (int k = 0; k <= attNumberOfCells; k++) {//获取每个单元格
                            salesInfoList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=salesInfoList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                           salesInfoRowList.add(salesInfoList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                //紧急联系人数据集
                List<List<String>> relativeRowList = new ArrayList<>();
                Sheet relativ = sheetMap.get("relativeList");
                int relativOfCells = relativ.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < relativ.getPhysicalNumberOfRows(); j++) {
                    Row row = relativ.getRow(j);
                    if (row != null) {
                        List<String> relativInfoList = new ArrayList<>();
                        for (int k = 0; k <= relativOfCells; k++) {//获取每个单元格
                            String cellFormat = ImportUtil.cellFormat(row.getCell(k));
                             relativInfoList.add(cellFormat);
                        }
                         List<String> filtered=relativInfoList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                            relativeRowList.add(relativInfoList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(salesInfoRowList)) {
                    //组装销售人员数据
                    List<Map<String, Object>> salesInfo_List = new LinkedList<Map<String, Object>>();
                    List<Map<String, Object>> relativeRow_List = new LinkedList<Map<String, Object>>();
                     List<Map<String, Object>> salesNoticy_list = new LinkedList<Map<String, Object>>();
                    Map<String, Object> p = new HashMap<String, Object>();
                    //销售人员入库
                    packUpdateSalesInfo(salesInfoRowList, salesInfo_List,salesNoticy_list);
                    String salesInfo_string = JSONObject.toJSONString(salesInfo_List);
                    String salesNoticy_string = JSONObject.toJSONString(salesNoticy_list);
                    //转换为json串
                    p.put("salesInfo_string", salesInfo_string);
                    p.put("salesNoticy_string", salesNoticy_string);
                    if (!CollectionUtils.isEmpty(relativeRowList)) {
                        //紧急联系人入库
                        packUpdateRelativInfo(relativeRowList, relativeRow_List);
                    }
                    String relativeRow_string = JSONObject.toJSONString(relativeRow_List);
                    p.put("relativeRow_string", relativeRow_string);
                    msg = insuranceSalesInfoClient.updateImportList(p);

                } else {
                    msg.put("code", "0000");
                }
            } else {
                msg.put("code", "0000");
            }
        } catch (CheckException e) {
            msg.put("code", "500");
            msg.put("error", e.getMessage());
            logger.error("导入销售人员|异常：{}", e);
        } catch (Exception e) {
            logger.error("导入销售人员|异常：{}", e);
            msg.put("code", "400");
        }
        return msg;
    }

    private void packUpdateRelativInfo(List<List<String>> relativeRowList, List<Map<String, Object>> relativeRow_list) {
       relativeRowList.forEach(relativeRow -> {
            Map<String,Object> relativeRowMap = Maps.newHashMapWithExpectedSize(15);
            int[] filedindexs = {0};
            String[] filedNames = {"员工编号"};
            List<String> validateMust = ImportUtil.validateMust(relativeRow, filedindexs, filedNames);
            if (!CollectionUtils.isEmpty(validateMust)){
                throw new CheckException("员工编号不能为空:"+relativeRow.get(0)+",请录入必填项："+validateMust.toString());
            }
            Map<String, Object> map = new HashMap<>();
            String insuranceSalerNo = relativeRow.get(0);
            map.put("insuranceSalerNo", insuranceSalerNo);
            List<InsuranceSalesInfo> list = insuranceSalesInfoClient.insuranceSalesList(map);
            if (CollectionUtils.isEmpty(list)) {
                throw new CheckException("员工编号：" + insuranceSalerNo + "不存在");
            }
            InsuranceSalesInfo preInsuranceSalesInfo = list.get(0);
            relativeRowMap.put("salesId",preInsuranceSalesInfo.getInsuranceSalesId());//销售人员Id
            relativeRowMap.put("personName",relativeRow.get(1));//联系人姓名
            relativeRowMap.put("shipCellPhone",relativeRow.get(2));//联系人电话
            relativeRowMap.put("relationShip",relativeRow.get(3));//关系
            relativeRowMap.put("shipAddr",relativeRow.get(4));//联系人地址
            relativeRowMap.put("shipEmail",relativeRow.get(5));//联系人邮箱
            relativeRowMap.put("remark",relativeRow.get(6));//备注
            relativeRowMap.put("createdTime",DateUtil.getTimeNormalString(new Date()));//创建时间
            relativeRowMap.put("shipType","2");
            relativeRow_list.add(relativeRowMap);
            });
    }

    /**
     * 解析修改销售人员导入数据
     * @param salesInfoRowList
     * @param salesInfo_list
     */
    private void packUpdateSalesInfo(List<List<String>> salesInfoRowList, List<Map<String, Object>> salesInfo_list,List<Map<String, Object>> salesNoticy_list) {
         //校验唯一性
        ArrayList<String> cardNoList = new ArrayList<>();
         ArrayList<String> mobileList = new ArrayList<>();
        salesInfoRowList.forEach(salesInfo -> {
            Map<String, Object> salesInfoMap = Maps.newHashMapWithExpectedSize(15);
            Map<String, Object> salerNoticyMap = Maps.newHashMapWithExpectedSize(15);
            int[] filedindexs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
            String[] filedNames = { "员工姓名", "员工工号", "入司时间", "证件类型", "证件号码", "手机号码", "性别", "出生日期", "银行名称", "开户行", "银行卡号", "政治面貌", "所在省份", "所在城市",  "最高学历"};
            List<String> validateMust = ImportUtil.validateMust(salesInfo, filedindexs, filedNames);
            if (!CollectionUtils.isEmpty(validateMust)) {
                throw new CheckException("员工工号:" + salesInfo.get(1) + ",请录入必填项：" + validateMust.toString());
            }
            //员工工号
            Map<String, Object> map = new HashMap<>();
            String insuranceSalerNo = salesInfo.get(1);
            map.put("insuranceSalerNo", insuranceSalerNo);
            List<InsuranceSalesInfo> list = insuranceSalesInfoClient.insuranceSalesList(map);
            if (CollectionUtils.isEmpty(list)) {
                throw new CheckException("员工工号：" + insuranceSalerNo + "不存在");
            }
            InsuranceSalesInfo preInsuranceSalesInfo = list.get(0);
            salesInfoMap.put("insuranceSalerNo",insuranceSalerNo);

//            String cooperationType = salesInfo.get(0);
//            if ("直营".equals(cooperationType)) {
//                salesInfoMap.put("cooperationType", "0");//所属机构性质
//            } else if ("加盟".equals(cooperationType)) {
//                salesInfoMap.put("cooperationType", "1");//所属机构性质
//            } else {
//                throw new CheckException("员工工号:" + salesInfo.get(4) +"所属机构性质：" + cooperationType + ",不存在");
//            }
            //组织机构名称
            Map<String, Object> paras = new HashMap<>();
//            paras.put("salesOrgName", salesInfo.get(1));
//            List<SalesOrgInfo> salesOrgs = salesOrgInfoClient.getParents(paras);
//            if (CollectionUtils.isEmpty(salesOrgs)) {
//                throw new CheckException("员工工号:" + salesInfo.get(4) +"组织机构名称：" + salesInfo.get(1) + ",不存在");
//            }
            //TODO 根据组织机构校验权限
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
            //数据权限相关
            String myDeptNo = employee.getDeptNo();
            List<String> myAllOrgIdList = null;
            //只有admin没有组织机构 如果是admin登录 myAllOrgIdList = null 权限控制
            if (!"admin".equals(employee.getEmployeeNo())&&!"superAdmin".equals(employee.getEmployeeNo())) {
               String myAllOrgIds = salesOrgInfoClient.findEmployeeAllOrgs(myDeptNo);
                 //登录人所在组织机构
               myAllOrgIdList = Arrays.asList(myAllOrgIds.split(","));
            }
            if (myAllOrgIdList != null) {
                //判断所录人员是否在登录人的组织机构下
                if (!myAllOrgIdList.contains(preInsuranceSalesInfo.getSalesOrgId().toString())) {
                    throw new CheckException("员工工号:" + salesInfo.get(1) + ",不在操作人的权限范围内，请核查");
                }
            }
//             salesInfoMap.put("salesOrgId", salesOrgs.get(0).getSalesOrgId());//组织机构名称
            //销售团队名称
//            paras.clear();
//            paras.put("salesTeamName", salesInfo.get(2));
//            paras.put("salesOrgId",salesOrgs.get(0).getSalesOrgId());
//            List<SalesTeamInfo> parents = salesTeamInfoClient.getParents(paras);
//            if (CollectionUtils.isEmpty(parents)) {
//                throw new CheckException("员工工号:" + salesInfo.get(4) +"销售团队名称：" + salesInfo.get(2) + ",在组织机构"+salesInfo.get(1)+"下不存在");
//            }
//            salesInfoMap.put("salesTeamId", parents.get(0).getSalesTeamId());//销售团队名称
            salesInfoMap.put("insuranceSaler", salesInfo.get(0));//员工姓名

            //入职职级序列
//            paras.clear();
//            paras.put("sequenceName", salesInfo.get(5));
//            List<RankSequence> rankSequenceList = rankSequenceClient.getRankSequenceList(paras);
//            if (CollectionUtils.isEmpty(rankSequenceList)) {
//                throw new CheckException("员工工号:" + salesInfo.get(4) +"入职职级序列：" + salesInfo.get(5) + ",不存在");
//            }
//            salesInfoMap.put("rankSequenceId", rankSequenceList.get(0).getSequenceId());//入职职级序列

            //入职职级
//            paras.clear();
//            paras.put("salesGradeName", salesInfo.get(6));
//            List<SalesGrade> salesGradeList = salesGradeClient.getSalesGradeList(paras);
//            if (CollectionUtils.isEmpty(salesGradeList)) {
//                throw new CheckException("员工工号:" + salesInfo.get(4) +"入职职级：" + salesInfo.get(6) + ",不存在");
//            }
            Long salesGradeId = preInsuranceSalesInfo.getSalesGradeId();
            salesInfoMap.put("salesGradeId", salesGradeId);//入职职级
            if (!DateTimeUtil.isLegalDate(salesInfo.get(2))){
                throw new CheckException("员工工号:" + salesInfo.get(1) +"入司时间格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            salesInfoMap.put("joinDate", salesInfo.get(2));//入司时间
            salesInfoMap.put("passDate", salesInfo.get(2));//入司时间
            //证件类型
            String cardType = salesInfo.get(3);  //证件类型
            paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_CARD);
            paras.put("dictName",cardType);
            Map<String, Object> cardDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(cardDict)){
               throw new CheckException("员工工号:" + salesInfo.get(1) +"证件类型" + salesInfo.get(3) + ",不存在请参考字典值");
            }
            String cardDictCode = cardDict.get("dictCode").toString();
            salesInfoMap.put("cardType", cardDict.get("dictId").toString());//证件类型
            String cardNo = salesInfo.get(4);
            boolean regFlag = true;
            if ("1".equals(cardDictCode) || "5".equals(cardDictCode)) {
                regFlag = cardNo.matches(Constants.DATA_FORMAT_IDCARD);
            } else if ("2".equals(cardDictCode)) {
                regFlag = cardNo.matches(Constants.DATA_FORMAT_HKCARD);
            } else if ("3".equals(cardDictCode)) {
                regFlag = cardNo.matches(Constants.DATA_FORMAT_PASSPORTCARD);
            } else if ("4".equals(cardDictCode)) {
                regFlag = cardNo.matches(Constants.DATA_FORMAT_OFFICERCARD);
            }
            if (!regFlag) {
                throw new CheckException("员工工号:" + salesInfo.get(1) +"证件号," + cardNo + "证件号格式不正确");
            }
            if(!cardNo.equals(preInsuranceSalesInfo.getCardNo())){
                paras.clear();
                paras.put("cardNo", cardNo);
                int num = insuranceSalesInfoClient.findMaxSalerNo(paras);
                if (num > 0) {
                    throw new CheckException("员工工号:" + salesInfo.get(1) +"证件号," + cardNo + "证件号码已存在");
                }
            }
            if (cardNoList.contains(cardNo)){
                throw new CheckException("导入数据中证件号:"+cardNo+"存在重复,请核查!");
            }
            salesInfoMap.put("cardNo", cardNo);//证件号码
            cardNoList.add(cardNo);

            boolean matches1 = salesInfo.get(5).matches(Constants.DATA_FORMAT_MOBLIE);
            if (!matches1) {
                throw new CheckException("员工工号:" + salesInfo.get(1) +"手机号码:" + salesInfo.get(5) + ",手机号码格式不正确");
            }
            if(!salesInfo.get(5).equals(preInsuranceSalesInfo.getMobile())){
                paras.clear();
                paras.put("mobile", salesInfo.get(5));
                int num1 = insuranceSalesInfoClient.findMaxSalerNo(paras);
                if (num1 > 0) {
                    throw new CheckException("员工工号:" + salesInfo.get(1) +",手机号码:" + salesInfo.get(5) + "已存在");
                }
                 //判断是否在汇康存在
                String employeeNo = insuranceSalesInfoClient.searchHKSalerData(salesInfo.get(5));
                if (StringUtil.isNotBlanks(employeeNo) && !employeeNo.equals(salesInfo.get(1))) {
                     throw new CheckException("员工工号:" + salesInfo.get(1) +",手机号码:" + salesInfo.get(5) + "在汇康已存在");
                }
            }
            if (mobileList.contains(salesInfo.get(5))){
                throw  new CheckException("导入数据中手机号:"+salesInfo.get(5)+"存在重复值,请核查!");
            }
            salesInfoMap.put("mobile", salesInfo.get(5));//手机号码
            mobileList.add(salesInfo.get(5));

            String sex = salesInfo.get(6);
            paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_SEX);
            paras.put("dictName",sex);
            Map<String, Object> sexDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(sexDict)){
                throw new CheckException("员工工号:" + salesInfo.get(1) +"性别" + salesInfo.get(6) + ",不存在请参考字典值");
            }
            salesInfoMap.put("sex", sexDict.get("dictCode").toString());//性别
            if (!DateTimeUtil.isLegalDate(salesInfo.get(7))){
                throw new CheckException("员工工号:" + salesInfo.get(1) +"出生日期格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            salesInfoMap.put("birthday", salesInfo.get(7));//出生日期
            String bankChannel = salesInfo.get(8);
             paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_BANK);
            paras.put("dictName",bankChannel);
            Map<String, Object> bankDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(bankDict)){
                throw new CheckException("员工工号:" + salesInfo.get(1) +"银行名称" + salesInfo.get(8) + ",不存在请参考字典值");
            }
            salesInfoMap.put("bankChannel", bankDict.get("dictId").toString());//银行名称

            salesInfoMap.put("openBank", salesInfo.get(9));//开户行
            boolean matches3 = salesInfo.get(10).matches(Constants.DATA_FORMAT_NUMBER);
            if (!matches3) {
                throw new CheckException("员工工号:" + salesInfo.get(1) +"银行卡号" + salesInfo.get(10) + ",不为数字");
            }
            if (salesInfo.get(10).length() > 20) {
                throw new CheckException("员工工号:" + salesInfo.get(1) +"银行卡号" + salesInfo.get(10) + ",超过20位");
            }

            salesInfoMap.put("bankCardNo", salesInfo.get(10));//银行卡号
            String politicalAppearance = salesInfo.get(11);
            paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_FACE);
            paras.put("dictName",politicalAppearance);
            Map<String, Object> politicalAppearanceDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(politicalAppearanceDict)){
                 throw new CheckException("员工工号："+salesInfo.get(1)+"政治面貌"+salesInfo.get(11)+"请参考字典值");
            }
            salesInfoMap.put("politicalAppearance", politicalAppearanceDict.get("dictId").toString());//政治面貌
            String province = salesInfo.get(12);//所在省
            String city = salesInfo.get(13);//所在市
            //String county = salesInfo.get(19);//所在区县
            District districtProvince = systemDictFeignClient.findDistrictByDistrict(province);
            if (null == districtProvince) {
                throw new CheckException("员工工号:" + salesInfo.get(1) + "所在省份未查找到"+province);
            }
            District districtCity = systemDictFeignClient.findDistrictByDistrictAndParentId(city, String.valueOf(districtProvince.getDistrictid()));
            if (districtCity == null) {
                throw new CheckException("员工工号:" + salesInfo.get(1) + "所在市未查找到"+city);
            }
           /* District districtCounty = systemDictFeignClient.findDistrictByDistrictAndParentId(county, String.valueOf(districtCity.getDistrictid()));
            if (districtCounty == null) {
                throw new CheckException("员工工号:" + salesInfo.get(4) + "所在区县未查找到"+county);
            }*/
            String areaCode = districtCity.getSortcode();
            salesInfoMap.put("areaCode", areaCode);//地区编码
            String educationalBg = salesInfo.get(14);
             paras.clear();
            paras.put("dictType",Constants.DICT_TYPE_EDU);
            paras.put("dictName",educationalBg);
            Map<String, Object> eduDict = systemDictFeignClient.findSystemDictByTypeAndCode(paras);
            if (CollUtil.isEmpty(eduDict)){
                throw new CheckException("员工工号："+salesInfo.get(1)+"最高学历"+salesInfo.get(14)+"请参考字典值");
            }
            salesInfoMap.put("educationalBg", eduDict.get("dictId").toString());//最高学历
            salesInfoMap.put("certificateNumber", salesInfo.get(15));//执业证号
            String dateStr = DateUtil.date2Str(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(), 1)), null);
            String month = DateUtil.date2Str(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(), 1)), DateUtil.TIME_FORMAT_MONTHS);

            //网级关系
            /*if(flag){
                paras.put("insuranceSalesId", transSalesId(tjSalesName,Constants.RELATION_SALE_NAME_TJ,myAllOrgIdList));
                InsuranceSalesInfo pinsuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
                paras.clear();
                paras.put("pTreeCode", pinsuranceSalesInfo.getNowTreeCode());
                paras.put("treeCodeLen", pinsuranceSalesInfo.getNowTreeCode().length() + 4);
                int preTreeCode = insuranceSalesInfoClient.findMaxTreeCode(paras);
                if (preTreeCode == 0) {
                    salesInfoMap.put("nowTreeCode",pinsuranceSalesInfo.getNowTreeCode() + "1000");
                } else {
                     salesInfoMap.put("nowTreeCode",pinsuranceSalesInfo.getNowTreeCode() + (preTreeCode + 1) + "");
                }
            }*/

//            //是否有待业证
//           String leavingCertificate = salesInfo.get(17);
//            if ("否".equals(leavingCertificate)){
//                salesInfoMap.put("leavingCertificate","0");
//            }else if ("是".equals(leavingCertificate)){
//                salesInfoMap.put("leavingCertificate","1");
//            }
//            //是否有工作室
//            String havingWorkGroup = salesInfo.get(18);
//            if ("否".equals(havingWorkGroup)){
//                salesInfoMap.put("havingWorkGroup","0");
//            }else if ("是".equals(havingWorkGroup)){
//                salesInfoMap.put("havingWorkGroup","1");
//            }

            salesInfoMap.put("updatedTime", DateUtil.getTimeNormalString(new Date()));//创建时间

            salesInfoMap.put("salesStatus", preInsuranceSalesInfo.getSalesStatus());//状态
            salesInfoMap.put("salesOrgId",preInsuranceSalesInfo.getSalesOrgId());//为了推送汇康获取方便
            salesInfoMap.put("insuranceSalesId",preInsuranceSalesInfo.getInsuranceSalesId());
            if (!salesInfoMap.get("mobile").equals(preInsuranceSalesInfo.getMobile()) || !salesInfoMap.get("cardNo").equals(preInsuranceSalesInfo.getCardNo()) || !salesInfoMap.get("insuranceSaler").equals(preInsuranceSalesInfo.getInsuranceSaler())){
               salerNoticyMap.put("mobile",salesInfoMap.get("mobile")) ;
               salerNoticyMap.put("cardNo",salesInfoMap.get("cardNo")) ;
               salerNoticyMap.put("insuranceSaler",salesInfoMap.get("insuranceSaler")) ;
               salerNoticyMap.put("insuranceSalerNo",preInsuranceSalesInfo.getInsuranceSalerNo()) ;
               salerNoticyMap.put("salesOrgId",preInsuranceSalesInfo.getSalesOrgId()) ;
               salerNoticyMap.put("salesStatus",preInsuranceSalesInfo.getSalesStatus());
               salesNoticy_list.add(salerNoticyMap);
            }
            salesInfo_list.add(salesInfoMap);


        });
    }
    /*
    * 销售人员员工关系导入
    *
    * */
    @RequestMapping(value = "/importRelation", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importRelation(MultipartFile file) throws Exception {
        Map<String, Object> msg = new HashMap<String, Object>();
         Subject subject = SecurityUtils.getSubject();
         Employee employee = (Employee) subject.getPrincipal();
        String employeeNo = employee.getEmployeeNo();
        try {
            if (!file.isEmpty()) {
                FileInputStream in = (FileInputStream) file.getInputStream();
                Workbook workbook = WorkbookFactory.create(in);  //兼容xls和xlsx
                Map<String, Sheet> sheetMap = new HashMap<String, Sheet>();
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    String sheetName = workbook.getSheetName(i);
                    Sheet sheetAt = workbook.getSheetAt(i);
                    sheetMap.put(sheetName, sheetAt);
                }
                //销售人员数据集
                List<List<String>> salesRelationRowList = new ArrayList<>();
                Sheet att = sheetMap.get("relation");
                int attNumberOfCells = att.getRow(1).getPhysicalNumberOfCells();
                for (int j = 2; j < att.getPhysicalNumberOfRows(); j++) {
                    Row row = att.getRow(j);
                    if (row != null) {
                        List<String> salesInfoList = new ArrayList<>();
                        for (int k = 0; k <= attNumberOfCells; k++) {//获取每个单元格
                            salesInfoList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=salesInfoList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                           salesRelationRowList.add(salesInfoList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(salesRelationRowList)) {
                    //组长销售人员工关系数据
                    List<InsuranceSalesInfo> relativeRow_List = new LinkedList<InsuranceSalesInfo>();
                    //存放 下月生效的员工关系
                    List<SalerRelationChange> salerRelationChange_List = new LinkedList<SalerRelationChange>();
                    //如果立即生效  同步已经存在的下月生效的数据
                     List<SalerRelationChange> relationChangeUpdate_list = new LinkedList<SalerRelationChange>();
                     //存放操作日志
                     List<SalerRelationLog> salerRelationLog_list = new LinkedList<>();
                    Map<String, Object> p = new HashMap<String, Object>();
                    //销售人员员工关系
                    packSalerRelation(salesRelationRowList,relativeRow_List,salerRelationChange_List,relationChangeUpdate_list,salerRelationLog_list,employeeNo);
                    String relativeRow_string = JSONObject.toJSONString(relativeRow_List);
                    String relationChange_string = JSONObject.toJSONString(salerRelationChange_List);
                     String relationChangeUpdate_string = JSONObject.toJSONString(relationChangeUpdate_list);
                     String salerRelationLog_string = JSONObject.toJSONString(salerRelationLog_list);
                    //转换为json串
                    p.put("relationChange_string",relationChange_string);
                    p.put("relativeRow_string", relativeRow_string);
                     p.put("relationChangeUpdate_string", relationChangeUpdate_string);
                      p.put("salerRelationLog_string", salerRelationLog_string);
                    msg = insuranceSalesInfoClient.saveImportRelation(p);

                } else {
                    msg.put("code", "0000");
                }
            } else {
                msg.put("code", "0000");
            }
        } catch (CheckException e) {
            msg.put("code", "500");
            msg.put("error", e.getMessage());
            logger.error("导入员工关系|异常：{}", e);
        } catch (Exception e) {
            logger.error("导入员工关系|异常：{}", e);
            msg.put("code", "400");
        }
        return msg;
    }

    //销售人员员工关系
    private void packSalerRelation(List<List<String>> salesRelationRowList, List<InsuranceSalesInfo> relativeRow_list, List<SalerRelationChange> salerRelationChange_list, List<SalerRelationChange> relationChangeUpdate_list, List<SalerRelationLog> salerRelationLog_list, String employeeNo) {
        salesRelationRowList.forEach( salesRelation -> {
             int[] filedindexs = {0,12};
            String[] filedNames = {"员工工号","生效时间"};
            List<String> validateMust = ImportUtil.validateMust(salesRelation, filedindexs, filedNames);
            if (!CollectionUtils.isEmpty(validateMust)) {
                throw new CheckException("员工工号:" + salesRelation.get(0) + ",请录入必填项：" + validateMust.toString());
            }

            String salerNo = salesRelation.get(0);
            //查询该员工的信息
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalerNo",salerNo);
            List<InsuranceSalesInfo> list = insuranceSalesInfoClient.insuranceSalesList(paras);
           // paras.put("salerNo", salerNo);
            //List<Map<String, Object>>  list = insuranceSalesInfoClient.insuranceSalesRelationList(paras);
            if (CollUtil.isEmpty(list)){
                throw new CheckException("员工工号【"+salerNo+"】不存在,请核实");
            }
            InsuranceSalesInfo insuranceSalesInfo = list.get(0);
               Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
            //数据权限相关
            String myDeptNo = employee.getDeptNo();
            List<String> myAllOrgIdList = null;
            //只有admin没有组织机构 如果是admin登录 myAllOrgIdList = null 权限控制
            if (!"admin".equals(employee.getEmployeeNo())&&!"superAdmin".equals(employee.getEmployeeNo())) {
               String myAllOrgIds = salesOrgInfoClient.findEmployeeAllOrgs(myDeptNo);
                 //登录人所在组织机构
               myAllOrgIdList = Arrays.asList(myAllOrgIds.split(","));
            }
            if (myAllOrgIdList != null) {
                //判断所录人员是否在登录人的组织机构下
                if (!myAllOrgIdList.contains(insuranceSalesInfo.getSalesOrgId().toString())) {
                    throw new CheckException("员工工号:" + salerNo + ",不在操作人的权限范围内，请核查");
                }
            }
            String updateDate = "";
            String relationDate = salesRelation.get(12);
            String relationFlag = "";
            if ("立即生效".equals(relationDate)){
                relationFlag = "1";
                updateDate = cn.hutool.core.date.DateUtil.format(new Date(),"yyyy-MM-dd");
            }else if("次月生效".equals(relationDate)){
                relationFlag = "2";
               updateDate = cn.hutool.core.date.DateUtil.format(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(),1)),"yyyy-MM-dd");
            }
            //担保人
             String dbsaler = salesRelation.get(1);
             Long dbSalesId =null;
             String dbSalesDate ="";
            if ("".equals(dbsaler)) {
                dbSalesId = insuranceSalesInfo.getDbSalesId();
                dbSalesDate = insuranceSalesInfo.getDbSalesDate();
            }else {
                if ("无".equals(dbsaler)) {
                    dbSalesId = null;
                    dbSalesDate = null;
                } else {
                   dbSalesId  = transSalesId(dbsaler, Constants.RELATION_SALE_NAME_DB, relationFlag);
                   dbSalesDate = updateDate;
                }
            }

             //推荐人
             Long preTjSalesId = insuranceSalesInfo.getTjSalesId();
             String tjsaler = salesRelation.get(2);
             Long tjSalesId =null;
             String tjSalesDate ="";
            if ("".equals(tjsaler)) {
                tjSalesId = insuranceSalesInfo.getTjSalesId();
                tjSalesDate = insuranceSalesInfo.getTjSalesDate();
            }else {
                if ("无".equals(tjsaler)) {
                    tjSalesId = null;
                    tjSalesDate = null;
                } else {
                   tjSalesId  = transSalesId(tjsaler, Constants.RELATION_SALE_NAME_TJ, relationFlag);
                   tjSalesDate = updateDate;
                }
                if (preTjSalesId != null && !ObjectUtil.equal(tjSalesId,preTjSalesId)){
                   // throw new CheckException("员工工号:" + salerNo + ",原推荐人主键："+preTjSalesId+"要改为的推荐人"+tjSalesId+"推荐人不可修改，请核查");
                    throw new CheckException("员工工号:" + salerNo +"推荐人不可修改，请核查");
                }

            }
            /* if (null == tjSalesId ){
                    throw new CheckException("员工工号:" + salerNo +"推荐人为必填项，请核查");
             }*/
             //一代育成人
             String ycCFsaler = salesRelation.get(3);
             Long ycCFirstGener =null;
             String establishTime ="";
            if ("".equals(ycCFsaler)) {
                ycCFirstGener = insuranceSalesInfo.getYcCFirstGener();
                establishTime = insuranceSalesInfo.getEstablishTime();
            }else {
                if ("无".equals(ycCFsaler)) {
                    ycCFirstGener = null;
                    establishTime = null;
                } else {
                   ycCFirstGener  = transSalesId(ycCFsaler, Constants.RELATION_SALE_NAME_CYCF, relationFlag);
                   establishTime = updateDate;
                }
            }

             //二代处育成
             String ycCSsaler = salesRelation.get(4);
             Long ycCSecondGener =null;
             String establishTime1 ="";
            if ("".equals(ycCSsaler)) {
                ycCSecondGener = insuranceSalesInfo.getYcCSecondGener();
                establishTime1 = insuranceSalesInfo.getEstablishTime1();
            }else {
                if ("无".equals(ycCSsaler)) {
                    ycCSecondGener = null;
                    establishTime1 = null;
                } else {
                   ycCSecondGener  = transSalesId(ycCSsaler, Constants.RELATION_SALE_NAME_CYCS, relationFlag);
                   establishTime1 = updateDate;
                }
            }

             //一代部育成
             String ycBFsaler = salesRelation.get(5);
             Long ycBFirstGener =null;
             String establishTime2 ="";
            if ("".equals(ycBFsaler)) {
                ycBFirstGener = insuranceSalesInfo.getYcBFirstGener();
                establishTime2 = insuranceSalesInfo.getEstablishTime2();
            }else {
                if ("无".equals(ycBFsaler)) {
                    ycBFirstGener = null;
                    establishTime2 = null;
                } else {
                   ycBFirstGener  = transSalesId(ycBFsaler, Constants.RELATION_SALE_NAME_BYCF, relationFlag);
                   establishTime2 = updateDate;
                }
            }

             //二代部育成
             String ycBSsaler = salesRelation.get(6);
             Long ycBSecondGener =null;
             String establishTime3 ="";
            if ("".equals(ycBSsaler)) {
                ycBSecondGener = insuranceSalesInfo.getYcBSecondGener();
                establishTime3 = insuranceSalesInfo.getEstablishTime3();
            }else {
                if ("无".equals(ycBSsaler)) {
                    ycBSecondGener = null;
                    establishTime3 = null;
                } else {
                   ycBSecondGener  = transSalesId(ycBSsaler, Constants.RELATION_SALE_NAME_BYCS, relationFlag);
                   establishTime3 = updateDate;
                }
            }

             //直辖组组长
             String czsaler = salesRelation.get(7);
             Long directGroupC =null;
             String establishTime4 ="";
            if ("".equals(czsaler)) {
                directGroupC = insuranceSalesInfo.getDirectGroupC();
                establishTime4 = insuranceSalesInfo.getEstablishTime4();
            }else {
                if ("无".equals(czsaler)) {
                    directGroupC = null;
                    establishTime4 = null;
                } else {
                   directGroupC  = transSalesId(czsaler, Constants.RELATION_SALE_NAME_CZ, relationFlag);
                   establishTime4 = updateDate;
                }
            }

             //直辖部部长
             String bzsaler = salesRelation.get(8);
             Long directDeptB =null;
             String establishTime5 ="";
            if ("".equals(bzsaler)) {
                directDeptB = insuranceSalesInfo.getDirectDeptB();
                establishTime5 = insuranceSalesInfo.getEstablishTime5();
            }else {
                if ("无".equals(bzsaler)) {
                    directDeptB = null;
                    establishTime5 = null;
                } else {
                   directDeptB  = transSalesId(bzsaler, Constants.RELATION_SALE_NAME_BZ, relationFlag);
                   establishTime5 = updateDate;
                }
            }

             //上级管理人
             String sjsaler = salesRelation.get(9);
             Long sjSalesId =null;
             String sjSalesDate ="";
            if ("".equals(sjsaler)) {
                sjSalesId = insuranceSalesInfo.getSjSalesId();
                sjSalesDate = insuranceSalesInfo.getSjSalesDate();
            }else {
                if ("无".equals(sjsaler)) {
                    sjSalesId = null;
                    sjSalesDate = null;
                } else {
                   sjSalesId  = transSalesId(sjsaler, Constants.RELATION_SALE_NAME_SJ, relationFlag);
                   sjSalesDate = updateDate;
                }
            }

             //继承人
             String jcsaler = salesRelation.get(10);
             Long jcSalesId =null;
             String jcSalesDate ="";
            if ("".equals(jcsaler)) {
                jcSalesId = insuranceSalesInfo.getJcSalesId();
                jcSalesDate = insuranceSalesInfo.getJcSalesDate();
            }else {
                if ("无".equals(jcsaler)) {
                    jcSalesId = null;
                    jcSalesDate = null;
                } else {
                   jcSalesId  = transSalesId(jcsaler, Constants.RELATION_SALE_NAME_JC, relationFlag);
                   jcSalesDate = updateDate;
                }
            }

                //辅导人
             String fdsaler = salesRelation.get(11);
             Long fdSalesId =null;
             String fdSalesDate ="";
            if ("".equals(fdsaler)) {
                fdSalesId = insuranceSalesInfo.getFdSalesId();
                fdSalesDate = insuranceSalesInfo.getFdSalesDate();
            }else {
                if ("无".equals(fdsaler)) {
                    fdSalesId = null;
                    fdSalesDate = null;
                } else {
                   fdSalesId  = transSalesId(fdsaler, Constants.RELATION_SALE_NAME_FD, relationFlag);
                   fdSalesDate = updateDate;
                }
            }
            //组装这个本应该放在立即生效判断里  但是为了保存操作日志使用公用的packSalerRelationLog 故提出来
             InsuranceSalesInfo salerRelation = new InsuranceSalesInfo();
                salerRelation.setInsuranceSalesId(insuranceSalesInfo.getInsuranceSalesId());
                salerRelation.setDbSalesId(dbSalesId);
                salerRelation.setDbSalesDate(dbSalesDate);
                salerRelation.setTjSalesId(tjSalesId);
                salerRelation.setTjSalesDate(tjSalesDate);
                salerRelation.setYcCFirstGener(ycCFirstGener);
                salerRelation.setEstablishTime(establishTime);
                salerRelation.setYcCSecondGener(ycCSecondGener);
                salerRelation.setEstablishTime1(establishTime1);
                salerRelation.setYcBFirstGener(ycBFirstGener);
                salerRelation.setEstablishTime2(establishTime2);
                salerRelation.setYcBSecondGener(ycBSecondGener);
                salerRelation.setEstablishTime3(establishTime3);
                salerRelation.setDirectGroupC(directGroupC);
                salerRelation.setEstablishTime4(establishTime4);
                salerRelation.setDirectDeptB(directDeptB);
                salerRelation.setEstablishTime5(establishTime5);
                salerRelation.setSjSalesId(sjSalesId);
                salerRelation.setSjSalesDate(sjSalesDate);
                salerRelation.setJcSalesId(jcSalesId);
                salerRelation.setJcSalesDate(jcSalesDate);
                salerRelation.setFdSalesId(fdSalesId);
                salerRelation.setFdSalesDate(fdSalesDate);
            if ("立即生效".equals(relationDate)){
                relativeRow_list.add(salerRelation);
                //判断这个人是否之前改过次月生效的员工关系  如果存在同步当月新的员工关系
                 String str = DateUtil.date2Str(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(),1)),DateUtil.TIME_FORMAT_MONTHS);
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("salerId",insuranceSalesInfo.getInsuranceSalesId());
                map.put("relationMonth",str);
                SalerRelationChange relationChange = insuranceSalesInfoClient.selectRelationBySalerIdAndMonth(map);
                if (null!=relationChange){
                    packRelationChangeUpdate(relationChangeUpdate_list,salerRelation,relationChange);
                }
                //操作日志
                SalerRelationLog salerRelationLog = packSalerRelationLog(salerRelation, insuranceSalesInfo, employeeNo, Constants.EFFECT_TAB_1, Constants.OPERATION_TAB_2);
                salerRelationLog_list.add(salerRelationLog);
            }else {
                 String str = DateUtil.date2Str(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(),1)),DateUtil.TIME_FORMAT_MONTHS);
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("salerId",insuranceSalesInfo.getInsuranceSalesId());
                map.put("relationMonth",str);
                SalerRelationChange relationChange = insuranceSalesInfoClient.selectRelationBySalerIdAndMonth(map);
                if (null==relationChange) {
                    SalerRelationChange salerRelationChange = new SalerRelationChange();
                    salerRelationChange.setSalerId(insuranceSalesInfo.getInsuranceSalesId());
                    salerRelationChange.setRelationMonth(cn.hutool.core.date.DateUtil.format(DateUtil.addMonths(new Date(), 1), "yyyy-MM"));
                    salerRelationChange.setDbBeforeSalesId(insuranceSalesInfo.getDbSalesId());
                    salerRelationChange.setDbAfterSalesId(dbSalesId);
                    salerRelationChange.setTjBeforeSalesId(insuranceSalesInfo.getTjSalesId());
                    salerRelationChange.setTjAfterSalesId(tjSalesId);
                    salerRelationChange.setYcBeforeCFirstGener(insuranceSalesInfo.getYcCFirstGener());
                    salerRelationChange.setYcAfterCFirstGener(ycCFirstGener);
                    salerRelationChange.setYcBeforeCSecondGener(insuranceSalesInfo.getYcCSecondGener());
                    salerRelationChange.setYcAfterCSecondGener(ycCSecondGener);
                    salerRelationChange.setYcBeforeBFirstGener(insuranceSalesInfo.getYcBFirstGener());
                    salerRelationChange.setYcAfterBFirstGener(ycBFirstGener);
                    salerRelationChange.setYcBeforeBSecondGener(insuranceSalesInfo.getYcBSecondGener());
                    salerRelationChange.setYcAfterBSecondGener(ycBSecondGener);
                    salerRelationChange.setSjBeforeSalesId(insuranceSalesInfo.getSjSalesId());
                    salerRelationChange.setSjAfterSalesId(sjSalesId);
                    salerRelationChange.setJcBeforeSalesId(insuranceSalesInfo.getJcSalesId());
                    salerRelationChange.setJcAfterSalesId(jcSalesId);
                    salerRelationChange.setFdBeforeSalesId(insuranceSalesInfo.getFdSalesId());
                    salerRelationChange.setFdAfterSalesId(fdSalesId);
                    salerRelationChange.setDirectBeforeGroupC(insuranceSalesInfo.getDirectGroupC());
                    salerRelationChange.setDirectAfterGroupC(directGroupC);
                    salerRelationChange.setDirectBeforeDeptB(insuranceSalesInfo.getDirectDeptB());
                    salerRelationChange.setDirectAfterDeptB(directDeptB);
                    salerRelationChange.setDbSalesDate(dbSalesDate == "" ? null : dbSalesDate);
                    salerRelationChange.setTjSalesDate(tjSalesDate == "" ? null : tjSalesDate);
                    salerRelationChange.setJcSalesDate(jcSalesDate == "" ? null : jcSalesDate);
                    salerRelationChange.setSjSalesDate(sjSalesDate == "" ? null : sjSalesDate);
                    salerRelationChange.setFdSalesDate(fdSalesDate == "" ? null : fdSalesDate);
                    salerRelationChange.setEstablishTime(establishTime == "" ? null : establishTime);
                    salerRelationChange.setEstablishTime1(establishTime1 == "" ? null : establishTime1);
                    salerRelationChange.setEstablishTime2(establishTime2 == "" ? null : establishTime2);
                    salerRelationChange.setEstablishTime3(establishTime3 == "" ? null : establishTime3);
                    salerRelationChange.setEstablishTime4(establishTime4 == "" ? null : establishTime4);
                    salerRelationChange.setEstablishTime5(establishTime5 == "" ? null : establishTime5);
                    salerRelationChange.setIsFinished("0");
                    salerRelationChange.setCreateTime(new Date());
                    salerRelationChange_list.add(salerRelationChange);
                }else {
                    if (!"".equals(dbsaler)) {
                       relationChange.setDbAfterSalesId(dbSalesId);
                       relationChange.setDbSalesDate(dbSalesDate);
                    }
                    if (!"".equals(tjsaler)){
                        relationChange.setTjAfterSalesId(tjSalesId);
                        relationChange.setTjSalesDate(tjSalesDate);
                    }
                    if (!"".equals(ycCFsaler)){
                        relationChange.setYcAfterCFirstGener(ycCFirstGener);
                        relationChange.setEstablishTime(establishTime);
                    }
                    if (!"".equals(ycCSecondGener)){
                        relationChange.setYcAfterCSecondGener(ycCSecondGener);
                        relationChange.setEstablishTime1(establishTime1);
                    }
                    if (!"".equals(ycBFsaler)){
                        relationChange.setYcAfterBFirstGener(ycBFirstGener);
                        relationChange.setEstablishTime2(establishTime2);
                    }
                    if (!"".equals(ycBSsaler)){
                        relationChange.setYcAfterBSecondGener(ycBSecondGener);
                        relationChange.setEstablishTime3(establishTime3);
                    }
                    if (!"".equals(czsaler)){
                        relationChange.setDirectAfterGroupC(directGroupC);
                        relationChange.setEstablishTime4(establishTime4);
                    }
                    if (!"".equals(bzsaler)){
                        relationChange.setDirectAfterDeptB(directDeptB);
                        relationChange.setEstablishTime5(establishTime5);
                    }
                    if (!"".equals(sjsaler)){
                        relationChange.setSjAfterSalesId(sjSalesId);
                        relationChange.setSjSalesDate(sjSalesDate);
                    }
                    if (!"".equals(jcsaler)){
                        relationChange.setJcAfterSalesId(jcSalesId);
                        relationChange.setJcSalesDate(jcSalesDate);
                    }
                    if (!"".equals(fdsaler)){
                        relationChange.setFdAfterSalesId(fdSalesId);
                        relationChange.setFdSalesDate(fdSalesDate);
                    }
                    relationChangeUpdate_list.add(relationChange);

                }
                    //操作日志
                    SalerRelationLog salerRelationLog = packSalerRelationLog(salerRelation, insuranceSalesInfo, employeeNo, Constants.EFFECT_TAB_2, Constants.OPERATION_TAB_2);
                    salerRelationLog_list.add(salerRelationLog);
            }


        });

    }

    //同步员工关系日志表数据
    private void packRelationChangeUpdate(List<SalerRelationChange> relationChangeUpdate_list, InsuranceSalesInfo salerRelation,SalerRelationChange relationChange) {
      //  String date =  cn.hutool.core.date.DateUtil.format(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(),1)),"yyyy-MM-dd");
        if (ObjectUtil.equal(relationChange.getDbBeforeSalesId(),relationChange.getDbAfterSalesId())){
            relationChange.setDbAfterSalesId(salerRelation.getDbSalesId());
            relationChange.setDbSalesDate(salerRelation.getDbSalesDate());
        }
        if (ObjectUtil.equal(relationChange.getTjBeforeSalesId(),relationChange.getTjAfterSalesId())){
            relationChange.setTjAfterSalesId(salerRelation.getTjSalesId());
            relationChange.setTjSalesDate(salerRelation.getTjSalesDate());
        }
        if (ObjectUtil.equal(relationChange.getYcBeforeCFirstGener(),relationChange.getYcAfterCFirstGener())){
            relationChange.setYcAfterCFirstGener(salerRelation.getYcCFirstGener());
            relationChange.setEstablishTime(salerRelation.getEstablishTime());
        }
        if (ObjectUtil.equal(relationChange.getYcBeforeCSecondGener(),relationChange.getYcAfterCSecondGener())){
            relationChange.setYcAfterCSecondGener(salerRelation.getYcCSecondGener());
            relationChange.setEstablishTime1(salerRelation.getEstablishTime1());
        }
        if (ObjectUtil.equal(relationChange.getYcBeforeBFirstGener(),relationChange.getYcAfterBFirstGener())){
            relationChange.setYcAfterBFirstGener(salerRelation.getYcBFirstGener());
            relationChange.setEstablishTime2(salerRelation.getEstablishTime2());
        }
        if (ObjectUtil.equal(relationChange.getYcAfterBSecondGener(),relationChange.getYcAfterBSecondGener())){
            relationChange.setYcAfterBSecondGener(salerRelation.getYcBSecondGener());
            relationChange.setEstablishTime3(salerRelation.getEstablishTime3());
        }
        if (ObjectUtil.equal(relationChange.getDirectBeforeGroupC(),relationChange.getDirectAfterGroupC())){
            relationChange.setDirectAfterGroupC(salerRelation.getDirectGroupC());
            relationChange.setEstablishTime4(salerRelation.getEstablishTime4());
        }
        if (ObjectUtil.equal(relationChange.getDirectBeforeDeptB(),relationChange.getDirectAfterDeptB())){
            relationChange.setDirectAfterDeptB(salerRelation.getDirectDeptB());
            relationChange.setEstablishTime5(salerRelation.getEstablishTime5());
        }
        if (ObjectUtil.equal(relationChange.getSjBeforeSalesId(),relationChange.getSjAfterSalesId())){
            relationChange.setSjAfterSalesId(salerRelation.getSjSalesId());
            relationChange.setSjSalesDate(salerRelation.getSjSalesDate());
        }
        if (ObjectUtil.equal(relationChange.getJcBeforeSalesId(),relationChange.getJcAfterSalesId())){
            relationChange.setJcAfterSalesId(salerRelation.getJcSalesId());
            relationChange.setJcSalesDate(salerRelation.getJcSalesDate());
        }
        if (ObjectUtil.equal(relationChange.getFdBeforeSalesId(),relationChange.getFdAfterSalesId())){
            relationChange.setFdAfterSalesId(salerRelation.getFdSalesId());
            relationChange.setFdSalesDate(salerRelation.getFdSalesDate());
        }
        relationChange.setDbBeforeSalesId(salerRelation.getDbSalesId());
        relationChange.setTjBeforeSalesId(salerRelation.getTjSalesId());
        relationChange.setYcBeforeCFirstGener(salerRelation.getYcCFirstGener());
        relationChange.setYcBeforeCSecondGener(salerRelation.getYcCSecondGener());
        relationChange.setYcBeforeBFirstGener(salerRelation.getYcBFirstGener());
        relationChange.setYcBeforeBSecondGener(salerRelation.getYcBSecondGener());
        relationChange.setDirectBeforeGroupC(salerRelation.getDirectGroupC());
        relationChange.setDirectBeforeDeptB(salerRelation.getDirectDeptB());
        relationChange.setSjBeforeSalesId(salerRelation.getSjSalesId());
        relationChange.setJcBeforeSalesId(salerRelation.getJcSalesId());
        relationChange.setFdBeforeSalesId(salerRelation.getFdSalesId());

        relationChangeUpdate_list.add(relationChange);

    }

    private Long transSalesId(String salerNo,String relationName,String relationFlag){
        Map<String, Object> paras = new HashMap<>();
        paras.put("salerNo",salerNo);
         //数据权限相关查询条件
        Subject subject = SecurityUtils.getSubject();
        Employee employeeShiro = (Employee) subject.getPrincipal();
        employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
        paras.put("isAdmin", employeeShiro.getEmployeeNo());
        paras.put("myDeptNo", employeeShiro.getDeptNo());
        List<Map<String,Object>> list =  insuranceSalesInfoClient.insuranceSalesRelationList(paras);
         //List<InsuranceSalesInfo> list = insuranceSalesInfoClient.insuranceSalesList(paras);
         if (CollectionUtils.isEmpty(list)){
              throw new CheckException("关系人员："+salerNo+"不存在");
         }
       String level = list.get(0).get("SALES_GRADE_ID").toString();
       //次月生效获取异动后的职级
       if ("2".equals(relationFlag)){
           level = list.get(0).get("NEXT_SALES_GRADE_ID").toString();
       }
        //异动不能跨省 为了减小改动量关系人的所在省份机构就是代理人省份机构
        String calOrgId = list.get(0).get("cal_org_id").toString();

        if (Constants.RELATION_SALE_NAME_CYCF.equals(relationName) || Constants.RELATION_SALE_NAME_CYCS.equals(relationName)){

            if ("5".equals(calOrgId) ){
                if (!Constants.SALES_GRADE_NAME_3.equals(level) && !Constants.SALES_GRADE_NAME_2.equals(level)){
                     throw new CheckException(relationName+"："+salerNo+"不是处长或部长职级,不能做为处育成人");
                }
            }else if (!Constants.SALES_GRADE_NAME_3.equals(level)){
                 throw new CheckException(relationName+"："+salerNo+"不是处长职级,不能做为处育成人");
             }
         }
         if (Constants.RELATION_SALE_NAME_BYCF.equals(relationName) || Constants.RELATION_SALE_NAME_BYCS.equals(relationName)){
             if (!Constants.SALES_GRADE_NAME_2.equals(level)){
                 throw new CheckException(relationName+"："+salerNo+"不是部长职级,不能做为部育成人");
             }
         }
         if (Constants.RELATION_SALE_NAME_CZ.equals(relationName) ){
             if (!Constants.SALES_GRADE_NAME_3.equals(level) && !Constants.SALES_GRADE_NAME_2.equals(level)){
                 throw new CheckException(relationName+"："+salerNo+"不是处长或部长职级,不能做为直辖处处长");
             }
         }
         if (Constants.RELATION_SALE_NAME_BZ.equals(relationName) ){
             if (!Constants.SALES_GRADE_NAME_2.equals(level)){
                 throw new CheckException(relationName+"："+salerNo+"不是部长职级,不能做为直辖部部长");
             }
         }
         return  Long.parseLong(list.get(0).get("INSURANCE_SALES_ID").toString());
    }

    @RequestMapping(value = "/importQuit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importQuit(MultipartFile file){
         Map<String, Object> msg = new HashMap<String, Object>();
         Subject subject = SecurityUtils.getSubject();
         Employee employee = (Employee) subject.getPrincipal();
         employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
        try {
            if (!file.isEmpty()) {
                FileInputStream in = (FileInputStream) file.getInputStream();
                Workbook workbook = WorkbookFactory.create(in);  //兼容xls和xlsx
                Map<String, Sheet> sheetMap = new HashMap<String, Sheet>();
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    String sheetName = workbook.getSheetName(i);
                    Sheet sheetAt = workbook.getSheetAt(i);
                    sheetMap.put(sheetName, sheetAt);
                }
                //销售人员数据集
                List<List<String>> salesQuitRowList = new ArrayList<>();
                Sheet att = sheetMap.get("quit");
                int attNumberOfCells = att.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < att.getPhysicalNumberOfRows(); j++) {
                    Row row = att.getRow(j);
                    if (row != null) {
                        List<String> salesQuitList = new ArrayList<>();
                        for (int k = 0; k <= attNumberOfCells; k++) {//获取每个单元格
                            salesQuitList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=salesQuitList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                           salesQuitRowList.add(salesQuitList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(salesQuitRowList)) {
                    //需要修改状态的员工编号和离职日期
                    List<Map<String,Object>> quitSaleNo_list = new LinkedList<Map<String,Object>>();
                    //需要添加的离职记录
                    List<SalerQuitInfo> salerQuit_list = new LinkedList<>();

                    Map<String, Object> p = new HashMap<String, Object>();
                    //销售人员员工关系
                    packSalerQuit(salesQuitRowList,quitSaleNo_list,salerQuit_list,employee);
                    String quitSaleNo_str = JSONUtil.toJsonStr(quitSaleNo_list);
                    String salerQuit_str = JSONUtil.toJsonStr(salerQuit_list);
                    Map<String, Object> map = new HashMap<>();
                    map.put("quitSaleNo_str",quitSaleNo_str);
                    map.put("salerQuit_str",salerQuit_str);
                    msg = insuranceSalesInfoClient.saveImportSalerQuit(map);

                } else {
                    msg.put("code", "0000");
                }
            } else {
                msg.put("code", "0000");
            }
        } catch (CheckException e) {
            msg.put("code", "500");
            msg.put("error", e.getMessage());
            logger.error("批量处理离职人员|异常：{}", e);
        } catch (Exception e) {
            logger.error("批量处理离职人员|异常：{}", e);
            msg.put("code", "400");
        }
        return msg;

    }

    /**
     * 组装离职数据
     * @param salesQuitRowList
     * @param quitSaleNo_list
     * @param salerQuit_list
     * @param employeeNo
     */
    private void packSalerQuit(List<List<String>> salesQuitRowList, List<Map<String,Object>> quitSaleNo_list, List<SalerQuitInfo> salerQuit_list, Employee employee) {
        //交接人集合
        List<String> jobHandoverList = new ArrayList<>();
        salesQuitRowList.forEach(salesQuit -> {
            Map<String, Object> pares = new HashMap<>();
            int[] filedindexs = {0,3,4};
			  String[] filedNames = {"员工工号","离职日期","交接人工号"};
              List<String> validateMust = ImportUtil.validateMust(salesQuit, filedindexs, filedNames);
              if (!CollectionUtils.isEmpty(validateMust)){
                  throw new CheckException("请录入必填项："+validateMust.toString());
              }
            String salesNo = salesQuit.get(0);
              if (quitSaleNo_list.contains(salesNo)){
                  throw  new CheckException("员工编号："+salesNo+"在导入数据中存在重复值,请核实");
              }
             pares.put("insuranceSalerNo", salesNo);
            List<InsuranceSalesInfo> list = insuranceSalesInfoClient.insuranceSalesList(pares);
            if (CollectionUtils.isEmpty(list)) {
                throw new CheckException("员工工号：" + salesNo + "不存在");
            }
            InsuranceSalesInfo insuranceSalesInfo = list.get(0);
            //权限控制
             List<String> myAllOrgIdList = null;
            //只有admin没有组织机构 如果是admin登录 myAllOrgIdList = null 权限控制
            if (!"admin".equals(employee.getEmployeeNo())&&!"superAdmin".equals(employee.getEmployeeNo())) {
               String myAllOrgIds = salesOrgInfoClient.findEmployeeAllOrgs(employee.getDeptNo());
                 //登录人所在组织机构
               myAllOrgIdList = Arrays.asList(myAllOrgIds.split(","));
            }
            if (myAllOrgIdList != null) {
                //判断所录人员是否在登录人的组织机构下
                if (!myAllOrgIdList.contains(insuranceSalesInfo.getSalesOrgId().toString())) {
                    throw new CheckException("员工工号:" + salesNo + ",不在操作人的权限范围内，请核查");
                }
            }

            if (Constants.SALES_STATUS_2.equals(insuranceSalesInfo.getSalesStatus())) {
                throw new CheckException("员工工号：" + salesNo + "已是离职状态,请核实");
            }

            SalerQuitInfo salerQuitInfo = new SalerQuitInfo();
            salerQuitInfo.setCreatedBy(employee.getEmployeeNo());
            salerQuitInfo.setSalerId(list.get(0).getInsuranceSalesId());
            salerQuitInfo.setQuitMark(salesQuit.get(1));
            String quitTo = salesQuit.get(2);
            if (StrUtil.isNotBlank(quitTo)) {
                switch (quitTo) {
                    case "同行业":
                        quitTo = "01";
                        break;
                    case "其他行业":
                        quitTo = "02";
                        break;
                    default:
                        throw new CheckException("员工编号:" + salesNo + ",离职去向不存在,请根据字典值填写");
                }
            }
            salerQuitInfo.setQuitTo(quitTo);
            if (!DateTimeUtil.isLegalDate(salesQuit.get(3))){
                throw new CheckException("员工编号:"+salesNo+"离职时间格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            salerQuitInfo.setQuitDate(salesQuit.get(3));
            String jobHandoverNo = salesQuit.get(4);
            if (salesNo.equals(jobHandoverNo)){
                throw new CheckException("员工编号："+salesNo+"工作交接人不能是本人,请核实!");
            }
            pares.clear();
             pares.put("insuranceSalerNo", jobHandoverNo);
            List<InsuranceSalesInfo> jobHandoverNos = insuranceSalesInfoClient.insuranceSalesList(pares);
            if (CollectionUtils.isEmpty(jobHandoverNos)) {
                throw new CheckException("员工工号：" + salesNo + "的工作交接人"+jobHandoverNo+"不存在");
            }
            InsuranceSalesInfo jobHandoverInfo = jobHandoverNos.get(0);
            if (myAllOrgIdList != null) {
                //判断所录人员是否在登录人的组织机构下
                if (!myAllOrgIdList.contains(jobHandoverInfo.getSalesOrgId().toString())) {
                    throw new CheckException("员工工号:" + salesNo + "的工作交接人:"+jobHandoverNo+",不在操作人的权限范围内，请核查");
                }
            }
            if (Constants.SALES_STATUS_2.equals(jobHandoverInfo.getSalesStatus() )|| Constants.SALES_STATUS_3 .equals(jobHandoverInfo.getSalesStatus()) ) {
                throw new CheckException("员工工号：" + salesNo + "的工作交接人"+jobHandoverNo+"是离职或黑名单状态,请核实");
            }
            jobHandoverList.add(jobHandoverNo);
            salerQuitInfo.setJobHandoverId(jobHandoverNos.get(0).getInsuranceSalesId().toString());
            salerQuit_list.add(salerQuitInfo);
            //需要修改的销售人员信息
            Map<String, Object> sales_update = new HashMap<>();
            sales_update.put("salesNo",salesNo);
            sales_update.put("quitDate",salesQuit.get(3));
            sales_update.put("salesStatus",Constants.SALES_STATUS_2);
            quitSaleNo_list.add(sales_update);
        });
        //判读交接人中是否有离职的人
        jobHandoverList.retainAll(quitSaleNo_list);
        if (CollUtil.isNotEmpty(jobHandoverList)){
            throw new CheckException("交接人:"+StringUtil.join(jobHandoverList,",")+"是要处理的离职人员,不可再作为交接人");
        }

    }

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
        paras.put("dataAuthFlag", "1");
        paras.put("myAllOrgIds", myAllOrgIds);

    }

    @ResponseBody
	@RequestMapping("/getInsuranceSalesShip")
    public DataMsg getInsuranceSalesShip(HttpServletRequest request, DataMsg dataMsg) {
        try {
            String insuranceSalesId = request.getParameter("insuranceSalesId");

            String str = DateUtil.date2Str(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(), 1)), DateUtil.TIME_FORMAT_MONTHS);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("salerId", insuranceSalesId);
            map.put("relationMonth", str);
            SalerRelationChange relationChange = insuranceSalesInfoClient.selectRelationBySalerIdAndMonth(map);
            if (null != relationChange) {
                List<Map> list = assembleInsuranceSales(relationChange);
                dataMsg.setRows(list);
                dataMsg.setTotal(Long.valueOf(list.size()));
            }

            return dataMsg;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return dataMsg;

    }

    private List<Map> assembleInsuranceSales(SalerRelationChange relationChange) {
        List<Map> list = new ArrayList<Map>();

//        List<String> salesNos = new ArrayList<>();
        if (null!=relationChange.getDbAfterSalesId()&&StringUtil.isNotBlank(relationChange.getDbAfterSalesId().toString())) {
            String dbSalesId = relationChange.getDbAfterSalesId().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", dbSalesId);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "担保人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getDbSalesDate());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "担保人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);

        }
        if (null!=relationChange.getTjAfterSalesId()&&StringUtil.isNotBlank(relationChange.getTjAfterSalesId().toString())) {
            String tjSalesId = relationChange.getTjAfterSalesId().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", tjSalesId);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "推荐人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getTjSalesDate());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "推荐人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getYcAfterCFirstGener()&&StringUtil.isNotBlank(relationChange.getYcAfterCFirstGener().toString())) {
            String ycCFirstGener = relationChange.getYcAfterCFirstGener().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", ycCFirstGener);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "一代处育成人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getEstablishTime());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "一代处育成人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getYcAfterCSecondGener()&&StringUtil.isNotBlank(relationChange.getYcAfterCSecondGener().toString())) {
            String ycCSecondGener = relationChange.getYcAfterCSecondGener().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", ycCSecondGener);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "二代处育成人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getEstablishTime1());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "二代处育成人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getYcAfterBFirstGener()&&StringUtil.isNotBlank(relationChange.getYcAfterBFirstGener().toString())) {
            String ycBFirstGener = relationChange.getYcAfterBFirstGener().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", ycBFirstGener);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "一代部育成人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getEstablishTime2());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "一代部育成人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getYcAfterBSecondGener()&&StringUtil.isNotBlank(relationChange.getYcAfterBSecondGener().toString())) {
            String ycBSecondGener = relationChange.getYcAfterBSecondGener().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", ycBSecondGener);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "二代部育成人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getEstablishTime3());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "二代部育成人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getSjAfterSalesId()&&StringUtil.isNotBlank(relationChange.getSjAfterSalesId().toString())) {
            String sjSalesId = relationChange.getSjAfterSalesId().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", sjSalesId);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "上级管理人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getSjSalesDate());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "上级管理人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getJcAfterSalesId()&&StringUtil.isNotBlank(relationChange.getJcAfterSalesId().toString())) {
            String jcSalesId = relationChange.getJcAfterSalesId().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", jcSalesId);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "继承人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getJcSalesDate());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "继承人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getFdAfterSalesId()&&StringUtil.isNotBlank(relationChange.getFdAfterSalesId().toString())) {
            String fdSalesId = relationChange.getFdAfterSalesId().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", fdSalesId);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "辅导人");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getFdSalesDate());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "辅导人");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getDirectAfterGroupC()&&StringUtil.isNotBlank(relationChange.getDirectAfterGroupC().toString())) {
            String directGroupC = relationChange.getDirectAfterGroupC().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", directGroupC);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "直属组处长");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getEstablishTime4());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "直属组处长");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
        if (null!=relationChange.getDirectAfterDeptB()&&StringUtil.isNotBlank(relationChange.getDirectAfterDeptB().toString())) {
            String directDeptB = relationChange.getDirectAfterDeptB().toString();
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceSalesId", directDeptB);
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectById(paras);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "直属部部长");
            map.put("insurance_saler", insuranceSalesInfo.getInsuranceSaler());
            map.put("insurance_salerno", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("sales_org", insuranceSalesInfo.getSalesOrgName());
            map.put("sales_team", insuranceSalesInfo.getSalesTeamName());
            map.put("determine_date", relationChange.getEstablishTime5());
            list.add(map);
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relationship", "直属部部长");
            map.put("insurance_saler", "");
            map.put("insurance_salerno", "");
            map.put("sales_org", "");
            map.put("sales_team", "");
            map.put("determine_date", "");
            list.add(map);
        }
//        List<InsuranceSalesInfo> rlist = null;
//        if (!CollectionUtils.isEmpty(salesNos)) {
//            rlist = insuranceSalesInfoClient.insuranceSalesListForSalesNo(salesNos);
//        }
//        if(rlist!=null&&rlist.size()>0){
//            rlist.forEach(saleInfo->{
//                if(StringUtil.isNotBlank(insuranceSalesInfo.getDbSalesId().toString())&&insuranceSalesInfo.getDbSalesId().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getTjSalesId().toString())&&insuranceSalesInfo.getTjSalesId().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getYcCFirstGener().toString())&&insuranceSalesInfo.getYcCFirstGener().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getDbSalesId().toString())&&insuranceSalesInfo.getYcCSecondGener().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getYcBFirstGener().toString())&&insuranceSalesInfo.getYcBFirstGener().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getYcBSecondGener().toString())&&insuranceSalesInfo.getYcBSecondGener().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getSjSalesId().toString())&&insuranceSalesInfo.getSjSalesId().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getJcSalesId().toString())&&insuranceSalesInfo.getJcSalesId().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getFdSalesId().toString())&&insuranceSalesInfo.getFdSalesId().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getDirectGroupC().toString())&&insuranceSalesInfo.getDirectGroupC().equals(saleInfo.getInsuranceSalesId())) {
//
//                }else if(StringUtil.isNotBlank(insuranceSalesInfo.getDirectDeptB().toString())&&insuranceSalesInfo.getDirectDeptB().equals(saleInfo.getInsuranceSalesId())) {
//
//                }
//            });
//        }
        return list;
    }

}
