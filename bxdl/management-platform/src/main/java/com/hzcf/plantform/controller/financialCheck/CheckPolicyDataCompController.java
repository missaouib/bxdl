package com.hzcf.plantform.controller.financialCheck;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.exception.CheckException;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.financialCheck.CheckPolicyDataCompClient;
import com.hzcf.plantform.feign.financialCheck.CheckPolicyDataHkClient;
import com.hzcf.plantform.feign.insurance.InsuranceDeptClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.feign.product.ProductBasicInformationClient;
import com.hzcf.plantform.util.*;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.util.DateUtil;
import com.hzcf.util.StringUtil;
import org.apache.commons.collections.MapUtils;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 手续费对账  保险公司对账数据上传 对比
 * Created by qin lina on 2020/12/10.
 */
@Controller
@RequestMapping("check_policy_data_comp")
public class CheckPolicyDataCompController {
     private Logger logger = LoggerFactory.getLogger(this.getClass());
     @Autowired
     private EmployeeFeignClient employeeFeignClient;
     @Autowired
     private CheckPolicyDataCompClient checkPolicyDataCompClient;
     @Autowired
     private SalesOrgInfoClient salesOrgInfoClient;
     @Autowired
     private InsuranceDeptClient insuranceDeptClient;
     @Autowired
     private ProductBasicInformationClient productBasicInformationClient;
     @Autowired
     private CheckPolicyDataHkClient checkPolicyDataHkClient;
      /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("/to_check_policy_data_comp_page")
    @RequiresPermissions("checkPolicyDataComp:list")
    public String toInspolicyDataCompPage(){
        return "financialCheck/CheckPolicyDataCompList";
    }

     /**
     * 列表数据分页展示
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getCheckPolicyDataCompPage")
    @ResponseBody
    public DataMsg getCheckPolicyDataCompPage(HttpServletRequest request, DataMsg dataMsg){
         try {
		       Map<String,Object> paras = new HashMap<>();
		       String pageNo = request.getParameter("pageNo");
		       if (StringUtil.isNotBlank(pageNo)) {
		           paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
		       }else{
		    	   paras.put("pageNo",1);
		       }
		       String pageSize = request.getParameter("pageSize");
		       if (StringUtil.isNotBlank(pageSize)) {
		           paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
		       }else{
		    	   paras.put("pageSize",10);
		       }
		       String batchNum = request.getParameter("batchNum");
		       paras.put("batchNum","-1");
		       if (StringUtil.isNotBlank(batchNum)) {
		    	   paras.put("batchNum",batchNum);
		       }
             //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());


            PageModel pageModel = checkPolicyDataCompClient.getCheckPolicyDataCompPage(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("财务对账保险公司上传数据列表展示异常",e);
        }
        return dataMsg;
    }

    /**
     * 导入保险公司对账数据
     * @param file
     * @return
     */
    @RequestMapping(value = "importPolicyDataComp",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importPolicyDataComp(MultipartFile file,HttpServletRequest request){
         Map<String,Object> params = new HashMap<String,Object>();
         Map<String,Object> msg =new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
         Employee employee = (Employee) subject.getPrincipal();
		params.put("now", DateUtil.getTimeNormalString(new Date()));
        params.put("emp", employee.getEmployeeId());
        String batchNum = request.getParameter("batchNum_file");
        params.put("batchNum",batchNum);
         //TODO 根据组织机构校验权限
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
                //对账数据集
                 List<List<String>> checkPolicyDataCompList = new ArrayList<>();
                Sheet att = sheetMap.get("checkData");
                int numberOfCells = att.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < att.getPhysicalNumberOfRows(); j++){
                    Row row = att.getRow(j);
                    if (row != null) {
                        List<String> dataList = new ArrayList<>();
                        for (int k = 0; k <= numberOfCells; k++) {//获取每个单元格
                            dataList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=dataList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                          checkPolicyDataCompList.add(dataList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(checkPolicyDataCompList)){
                    //组装对账数据
                    List<Map<String,Object>> check_policy_List = new LinkedList<Map<String,Object>>();
                    packcheckPolicy(checkPolicyDataCompList,check_policy_List,params, myAllOrgIdList);
                    //转换为json串
                    String checkPolicy_string = JSONObject.toJSONString(check_policy_List);
                     Map<String,Object> p = new HashMap<String,Object>();
                    p.put("checkPolicy_string", checkPolicy_string);
                    msg = checkPolicyDataCompClient.insertImportList(p);
                }else {
                    msg.put("code","0000");
                }
            }else {
                msg.put("code","0000");
            }
        } catch (CheckException e) {
            String message = e.getMessage();
             msg.put("code","500");
             msg.put("error",message);
            logger.error("导入保险公司对账数据|异常：{}", e);
        } catch (Exception e) {
            logger.error("导入保险公司对账数据|异常：{}", e);
            msg.put("code","400");
    }
        return msg;
    }


     private void packcheckPolicy(List<List<String>> checkPolicyDataCompList, List<Map<String, Object>> check_policy_list, Map<String, Object> params, List<String> myAllOrgIdList) {
          checkPolicyDataCompList.forEach(checkDataList -> {
	     //必填项校验
			  int[] filedindexs = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,15};
			  String[] filedNames = {"结算月","机构","保险公司","保单号","投保险种","险种类别","投保日期","生效日期","规模保费","保险期间","缴费方式","缴费期间","首期/续期","手续费","合计"};
              List<String> validateMust = ImportUtil.validateMust(checkDataList, filedindexs, filedNames);
              if (!CollectionUtils.isEmpty(validateMust)){
                  throw new CheckException("请录入必填项："+validateMust.toString());
              }

              Map<String,Object> checkDataMap = Maps.newHashMapWithExpectedSize(20);
              Map<String,Object> paras = new HashMap<>();
              String check_month = checkDataList.get(0);
              if (!DateTimeUtil.isLegalDateym(check_month)){
                throw new CheckException("保单号："+checkDataList.get(3)+"存在结算月格式不正确的数据,请填写yyyy-MM格式的时间");
              }
              checkDataMap.put("checkMonth",check_month);

             //根据组织机构名称查询ID 存入id目的是为了列表权限控制
              paras.clear();
              paras.put("salesOrgName",checkDataList.get(1));
              List<SalesOrgInfo> salesOrgs = salesOrgInfoClient.getParents(paras);
              if (CollectionUtils.isEmpty(salesOrgs)){
                 throw new CheckException("组织机构名称："+checkDataList.get(1)+"不存在");
              }
            if (myAllOrgIdList != null) {
                //判断所录人员是否在登录人的组织机构下
                if (!myAllOrgIdList.contains(salesOrgs.get(0).getSalesOrgId().toString())) {
                    throw new CheckException("组织机构名称：" + checkDataList.get(1) + ",不在操作人的权限范围内，请核查");
                }
            }
              checkDataMap.put("salesOrgName",checkDataList.get(1));
              checkDataMap.put("salesOrgId",salesOrgs.get(0).getSalesOrgId());//组织机构名称
              paras.clear();
              paras.put("insuranceCompanyName",checkDataList.get(2));
              Map<String,Object> insDept = insuranceDeptClient.selectOrg(paras);
              if(MapUtils.isEmpty(insDept)){
                  throw new CheckException("保险公司名称:"+checkDataList.get(2)+"不存在");
              }
              checkDataMap.put("companyOrgName",checkDataList.get(2));//保险公司
              checkDataMap.put("companyOrgId",insDept.get("INSURANCE_COMPANY_ID"));
              checkDataMap.put("policyId",checkDataList.get(3));//保单号
              paras.clear();
              paras.put("productName",checkDataList.get(4));
              List<InsuranceProductInfo> insurProducts = productBasicInformationClient.findInsurProducts(paras);
              if (CollUtil.isEmpty(insurProducts)){
                   throw new CheckException("投保险种:"+checkDataList.get(4)+"不存在");
              }
              checkDataMap.put("productName",checkDataList.get(4));//投保险种
              checkDataMap.put("productId",insurProducts.get(0).getProductId());//产品id
              checkDataMap.put("insuranceType",checkDataList.get(5));//险种类别
               if (!DateTimeUtil.isLegalDate(checkDataList.get(6))){
                throw new CheckException("保单号："+checkDataList.get(3)+"存在投保日期格式不正确的数据,请填写yyyy-MM-dd格式的时间");
              }
              checkDataMap.put("propostDate",checkDataList.get(6));//投保日期
               if (!DateTimeUtil.isLegalDate(checkDataList.get(7))){
                throw new CheckException("保单号："+checkDataList.get(3)+"存在生效日期格式不正确的数据,请填写yyyy-MM-dd格式的时间");
              }
              checkDataMap.put("underwritingDate",checkDataList.get(7));//生效日期
              if (!checkDataList.get(8).matches(Constants.DATA_FORMAT_NUMBER)){
                  throw new CheckException("规模保费："+checkDataList.get(8)+"填写有误,请填写金额");
              }
              checkDataMap.put("premium",checkDataList.get(8));//规模保费
              checkDataMap.put("insurancePeriod",checkDataList.get(9));//保险期间
              checkDataMap.put("paymentMethod",checkDataList.get(10));//缴费方式
              checkDataMap.put("paymentPeriod",checkDataList.get(11));//缴费期间
               if (!checkDataList.get(12).matches(Constants.DATA_FORMAT_NUMBER)){
                  throw new CheckException("首期/续期："+checkDataList.get(11)+"填写有误,请按照批注填写数字");
              }
              checkDataMap.put("paymentNum",checkDataList.get(12));//首期/续期
              if (!checkDataList.get(13).matches(Constants.DATA_FORMAT_NUMBER)){
                  throw new CheckException("手续费："+checkDataList.get(13)+"填写有误,请填写金额");
              }
              checkDataMap.put("processCost",checkDataList.get(13));//手续费
              if (StrUtil.isNotBlank(checkDataList.get(14)) && !checkDataList.get(14).matches(Constants.DATA_FORMAT_NUMBER)){
                   throw new CheckException("推动费："+checkDataList.get(14)+"填写有误,请填写金额");
              }
              checkDataMap.put("pustCost","".equals(checkDataList.get(14)) ?null:checkDataList.get(14));//推动费
               if (!checkDataList.get(15).matches(Constants.DATA_FORMAT_NUMBER)){
                  throw new CheckException("合计："+checkDataList.get(15)+"填写有误,请填写金额");
              }
              checkDataMap.put("totalCost",checkDataList.get(15));//合计
              checkDataMap.put("remark",checkDataList.get(16));//备注
              checkDataMap.put("createTime", params.get("now"));
              checkDataMap.put("createBy", params.get("emp"));//创建人
              checkDataMap.put("batchNum",params.get("batchNum"));//批次号
              check_policy_list.add(checkDataMap);
		  });
    }

    /**
     * 开始匹对
     * @param request
     * @return
     */
    @RequestMapping("/beginComparison")
    @ResponseBody
    public Map<String,Object> beginComparison(HttpServletRequest request) {
        String checkMonth = request.getParameter("checkMonth");
        String salesOrgId = request.getParameter("salesOrgId");
        String companyOrgId = request.getParameter("companyOrgId");
        String batchNum = request.getParameter("batchNum");
        Map<String, Object> paras = new HashMap<>();
        paras.put("checkMonth",checkMonth);
        paras.put("salesOrgId",salesOrgId);
        paras.put("companyOrgId",companyOrgId);
        paras.put("batchNum",batchNum);
        Map<String,Object> map = checkPolicyDataCompClient.beginComparison(paras);
        return map;
    }

    @RequestMapping("/getCheckResultPage")
    @ResponseBody
    public Map<String,Object> getCheckResultPage(HttpServletRequest request) {
        String batchNum = request.getParameter("batchNum");
        String checkReustStatus = request.getParameter("checkReustStatus");
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
        paras.put("batchNum", batchNum);

        //数据权限相关查询条件
        Subject subject = SecurityUtils.getSubject();
        Employee employeeShiro = (Employee) subject.getPrincipal();
        employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
        paras.put("isAdmin", employeeShiro.getEmployeeNo());
        paras.put("myDeptNo", employeeShiro.getDeptNo());
        //保单结算不一致
        PageModel pageModel = null;
        if ("1".equals(checkReustStatus)) {
            paras.put("checkStatus", Constants.CHECK_STATUS_4);
            pageModel = checkPolicyDataCompClient.getCheckPolicyDataCompPage(paras);

        } else if ("2".equals(checkReustStatus)) {
            paras.put("checkStatus", Constants.CHECK_STATUS_3);
            pageModel = checkPolicyDataHkClient.getCheckPolicyDataHkForBatchPage(paras);
        } else if ("3".equals(checkReustStatus)) {
            paras.put("checkStatus", Constants.CHECK_STATUS_3);
            pageModel = checkPolicyDataCompClient.getCheckPolicyDataCompPage(paras);
        } else if ("4".equals(checkReustStatus)) {
            paras.put("checkStatus", Constants.CHECK_STATUS_1);
            pageModel = checkPolicyDataCompClient.getCheckPolicyDataCompPage(paras);
        }
        //获取条数
        Map<String,Object> map = checkPolicyDataCompClient.getResultNumber(paras);
        List list = pageModel.getList();
        map.put("total",pageModel.getTotalRecords());
        map.put("rows",pageModel.getList());

        map.put("messageCode","200");
        return map;

    }

    /*导出对账结果*/
     @RequestMapping("/exportExtractData")
    public void exportExtractData(HttpServletRequest request, HttpServletResponse response) {
         // 设置response的Header
          String batchNum = request.getParameter("batchNum");
             String checkReustStatus = request.getParameter("checkReustStatus");
         try {
             Map<String, Object> paras = new HashMap<>();
             paras.put("batchNum", batchNum);
             //数据权限相关查询条件
             Subject subject = SecurityUtils.getSubject();
             Employee employeeShiro = (Employee) subject.getPrincipal();
             employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
             paras.put("isAdmin", employeeShiro.getEmployeeNo());
             paras.put("myDeptNo", employeeShiro.getDeptNo());
             //保单结算不一致
             List<Map<String, Object>> extractDatas = new ArrayList<>();
             String title = "";
             if ("1".equals(checkReustStatus)) {
                 paras.put("checkStatus", Constants.CHECK_STATUS_4);
                 extractDatas = checkPolicyDataCompClient.getCheckPolicyDataCompAll(paras);
                 title = "批次号" + batchNum + ",保单结算不一致数据" + DateTimeUtil.getNowDateChinaString();
             } else if ("2".equals(checkReustStatus)) {
                 paras.put("checkStatus", Constants.CHECK_STATUS_3);
                 extractDatas = checkPolicyDataHkClient.getCheckPolicyDataHkForBatchAll(paras);
                 title = "批次号" + batchNum + ",我司存在,保司不存在数据" + DateTimeUtil.getNowDateChinaString();
             } else if ("3".equals(checkReustStatus)) {
                 paras.put("checkStatus", Constants.CHECK_STATUS_3);
                 extractDatas = checkPolicyDataCompClient.getCheckPolicyDataCompAll(paras);
                 title = "批次号" + batchNum + ",保司存在,我司不存在数据" + DateTimeUtil.getNowDateChinaString();
             } else if ("4".equals(checkReustStatus)) {
                 paras.put("checkStatus", Constants.CHECK_STATUS_1);
                 extractDatas = checkPolicyDataCompClient.getCheckPolicyDataCompAll(paras);
                 title = "批次号" + batchNum + ",核对无误数据" + DateTimeUtil.getNowDateChinaString();
             }

             List<Object[]> dataList = Lists.newArrayListWithExpectedSize(extractDatas.size());
             String[] rowsName = new String[]{"结算月", "机构", "保险公司", "保单号", "投保险种", "险种类别", "投保日期", "生效日期", "规模保费", "保险期间", "缴费方式", "缴费期间", "首期/续期", "手续费", "推动费", "合计", "备注"};
             Object[] objs = null;
             Map<String, Object> map = null;
             for (int i = 0; i < extractDatas.size(); i++) {
                 map = extractDatas.get(i);
                 objs = new Object[rowsName.length];
                 objs[0] = map.get("check_month");
                 objs[1] = map.get("sales_org_name");
                 objs[2] = map.get("company_org_name");
                 objs[3] = map.get("policy_id");
                 objs[4] = map.get("product_name");
                 objs[5] = map.get("insurance_type");
                 objs[6] = map.get("propost_date");
                 objs[7] = map.get("underwriting_date");
                 objs[8] = map.get("premium");
                 objs[9] = map.get("insurance_period");
                 objs[10] = map.get("payment_method");
                 objs[11] = map.get("payment_period");
                 objs[12] = map.get("payment_num");
                 objs[13] = map.get("process_cost");
                 objs[14] = map.get("pust_cost");
                 objs[15] = map.get("total_cost");
                  objs[16] = map.get("remark");
                 dataList.add(objs);
             }
             ExportExcel ex = new ExportExcel(title, rowsName, dataList);
             ex.exportBigData(response);
         } catch (Exception e) {
             logger.error("批次号："+batchNum+"核对结果导出异常=============：", e);
         }
     }
}
