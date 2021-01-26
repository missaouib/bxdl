package com.hzcf.plantform.controller.financialCheck;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.exception.CheckException;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.financialCheck.CheckPolicyDataHkClient;
import com.hzcf.plantform.feign.insurance.InsuranceDeptClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.feign.product.ProductBasicInformationClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.DateTimeUtil;
import com.hzcf.plantform.util.ImportUtil;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.financialCheck.CheckPolicyBatch;
import com.hzcf.pojo.financialCheck.CheckPolicyDataHk;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.util.DateUtil;
import com.hzcf.util.StringUtil;
import com.netflix.ribbon.proxy.annotation.Http;
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
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 财务对账---汇康数据上传
 * Created by qin lina on 2020/12/9.
 */
@Controller
@RequestMapping("check_policy_data_hk")
public class CheckPolicyDataHkController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CheckPolicyDataHkClient checkPolicyDataHkClient;
    @Autowired
    private EmployeeFeignClient employeeFeignClient;
    @Autowired
    private SalesOrgInfoClient salesOrgInfoClient;
    @Autowired
    private InsuranceDeptClient insuranceDeptClient;
    @Autowired
    private ProductBasicInformationClient productBasicInformationClient;

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("/to_check_policy_data_Hk_page")
    @RequiresPermissions("checkPolicyDataHk:list")
    public String toInspolicyDataHkPage(){
        return "financialCheck/CheckPolicyDataHkList";
    }

      /**
     * 跳转到结算清单列表页面
     * @return
     */
    @RequestMapping("/to_settle_detail_page")
    @RequiresPermissions("settleDetail:list")
    public String toSettleDetailPage(){
        return "financialCheck/SettleDetailList";
    }

    /**
     * 列表数据分页展示
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getCheckPolicyDataHkPage")
    @ResponseBody
    public DataMsg getCheckPolicyDataHkPage(HttpServletRequest request,DataMsg dataMsg){
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
		       String checkMonth = request.getParameter("checkMonth");
		       if (StringUtil.isNotBlank(checkMonth)) {
		    	   paras.put("checkMonth",checkMonth);
		       }
              String salesOrgId = request.getParameter("salesOrgId");
               if (StringUtil.isNotBlank(salesOrgId)) {
                   paras.put("salesOrgId",salesOrgId);
               }

            String companyOrgId = request.getParameter("companyOrgId");
            if (StringUtil.isNotBlank(companyOrgId)) {
                paras.put("companyOrgId", companyOrgId);
            }
            String productId = request.getParameter("productId");
            if (StringUtil.isNotBlank(productId)) {
                paras.put("productId", productId);
            }
               String salesOrgName = request.getParameter("salesOrgName");
               if (StringUtil.isNotBlank(salesOrgName)) {
                   paras.put("salesOrgName",salesOrgName);
               }

            String companyOrgName = request.getParameter("companyOrgName");
            if (StringUtil.isNotBlank(companyOrgName)) {
                paras.put("companyOrgName", companyOrgName);
            }
            String productName = request.getParameter("productName");
            if (StringUtil.isNotBlank(productName)) {
                paras.put("productName", productName);
            }
             String checkStatus = request.getParameter("checkStatus");
            if (StringUtil.isNotBlank(checkStatus)) {
                paras.put("checkStatus", checkStatus);
            }
             String settleStatus = request.getParameter("settleStatus");
            if (StringUtil.isNotBlank(settleStatus)) {
                paras.put("settleStatus", settleStatus);
            }
             String cancelDelayCheck = request.getParameter("cancelDelayCheck");
            if (StringUtil.isNotBlank(cancelDelayCheck)){
                 paras.put("cancelDelayCheck", cancelDelayCheck);
            }
             String batchNum = request.getParameter("batchNum");
            if (StringUtil.isNotBlank(batchNum)){
                 paras.put("batchNum", batchNum);
            }

             //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());


            PageModel pageModel = checkPolicyDataHkClient.getCheckPolicyDataHkPage(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("财务对账汇康上传数据列表展示异常",e);
        }
        return dataMsg;
    }

    /**
     * 批次详情之提取批次对应汇康数据
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getDataHkBatchPage")
    @ResponseBody
    public DataMsg getDataHkBatchPage(HttpServletRequest request,DataMsg dataMsg){
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
            if (StringUtil.isNotBlank(batchNum)){
                 paras.put("batchNum", batchNum);
            }
            PageModel pageModel = checkPolicyDataHkClient.getCheckPolicyDataHkForBatchPage(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("批次详情之提取批次对应汇康数据",e);
        }
        return dataMsg;
    }


     /**
     * 结算清单列表数据展示
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getSettleDetailPage")
    @ResponseBody
    public Map<String,Object> getSettleDetailPage(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
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
            String checkMonth = request.getParameter("checkMonth");
            if (StringUtil.isNotBlank(checkMonth)) {
                paras.put("checkMonth", checkMonth);
            }
            String salesOrgId = request.getParameter("salesOrgId");
            if (StringUtil.isNotBlank(salesOrgId)) {
                paras.put("salesOrgId", salesOrgId);
            }

            String companyOrgId = request.getParameter("companyOrgId");
            if (StringUtil.isNotBlank(companyOrgId)) {
                paras.put("companyOrgId", companyOrgId);
            }
            //展示已核对数据
            paras.put("checkStatus", Constants.CHECK_STATUS_1);

             String settleStatus = request.getParameter("settleStatus");
            if (StringUtil.isNotBlank(settleStatus)) {
                paras.put("settleStatus", settleStatus);
            }

            //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());


            PageModel pageModel = checkPolicyDataHkClient.getCheckPolicyDataHkPage(paras);
            List list = pageModel.getList();

            if (!CollectionUtils.isEmpty(list)) {
                Map<String, BigDecimal> totalCost = checkPolicyDataHkClient.getTotalCost(paras);
                map.put("total_premium", totalCost.get("total_premium"));
                map.put("total_process_cost", totalCost.get("total_process_cost"));
                map.put("total_pust_cost", totalCost.get("total_pust_cost"));
                map.put("total_total_cost", totalCost.get("total_total_cost"));
            }
            map.put("total", pageModel.getTotalRecords());
            map.put("rows", pageModel.getList());
            map.put("messageCode", "200");
        } catch (Exception e) {
            map.put("messageCode", "400");
            logger.error("结算清单数据展示异常", e);
        }
        return map;
    }

    @RequestMapping(value = "/confirmSettle",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> confirmSettle(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paras = new HashMap<>();
            String checkMonth = request.getParameter("checkMonth");
            if (StringUtil.isNotBlank(checkMonth)) {
                paras.put("checkMonth", checkMonth);
            }
            String salesOrgId = request.getParameter("salesOrgId");
            if (StringUtil.isNotBlank(salesOrgId)) {
                paras.put("salesOrgId", salesOrgId);
            }

            String companyOrgId = request.getParameter("companyOrgId");
            if (StringUtil.isNotBlank(companyOrgId)) {
                paras.put("companyOrgId", companyOrgId);
            }
            //展示已核对数据
            paras.put("checkStatus", Constants.CHECK_STATUS_1);

            String settleStatus = request.getParameter("settleStatus");
            if (StringUtil.isNotBlank(settleStatus)) {
                paras.put("settleStatus", settleStatus);
            }

            //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());
            checkPolicyDataHkClient.confirmSettle(paras);
            map.put("code","200");
        } catch (Exception e) {
            map.put("code","400");
            logger.error("结算保单确认操作异常：",e);
        }
        return map;
    }

    @RequestMapping(value = "importPolicyDataHk",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importPolicyDataHk(MultipartFile file,String alertFileType){
         Map<String,Object> params = new HashMap<String,Object>();
         Map<String,Object> msg =new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
         Employee employee = (Employee) subject.getPrincipal();
		params.put("now", DateUtil.getTimeNormalString(new Date()));
        params.put("emp", employee.getEmployeeId());
        params.put("alertFileType",alertFileType);
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
                 List<List<String>> checkPolicyDataHKList = new ArrayList<>();
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
                          checkPolicyDataHKList.add(dataList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(checkPolicyDataHKList)){
                    //组装对账数据
                    List<Map<String,Object>> check_policy_List = new LinkedList<Map<String,Object>>();
                    packcheckPolicy(checkPolicyDataHKList,check_policy_List,params,myAllOrgIdList);
                    //转换为json串
                    String checkPolicy_string = JSONObject.toJSONString(check_policy_List);
                     Map<String,Object> p = new HashMap<String,Object>();
                    p.put("checkPolicy_string", checkPolicy_string);
                    if ("0".equals(alertFileType)){
                         msg = checkPolicyDataHkClient.insertImportList(p);
                    }else if ("1".equals(alertFileType)){
                         msg = checkPolicyDataHkClient.updateImportList(p);
                    }

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
            logger.error("导入汇康对账数据|异常：{}", e);
        } catch (Exception e) {
            logger.error("导入汇康对账数据|异常：{}", e);
            msg.put("code","400");
    }
        return msg;
    }

    private void packcheckPolicy(List<List<String>> checkPolicyDataHKList, List<Map<String, Object>> check_policy_list, Map<String, Object> params, List<String> myAllOrgIdList ) {
        List<String> dataHKexcelList = new ArrayList<>();
        checkPolicyDataHKList.forEach(checkDataList -> {
	      	//必填项校验
			  int[] filedindexs = {0,1,2,3,4,5,6,7,8,9,10,11,12};
			  String[] filedNames = {"结算月","机构","保险公司","保单号","投保险种","险种类别","投保日期","生效日期","规模保费","保险期间","缴费方式","缴费期间","首期/续期"};
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
              //根据保单号+险种名称+首期/续期 来判断是否重复数据
              paras.clear();
              paras.put("policyId",checkDataList.get(3));
              paras.put("productName",checkDataList.get(4));
              paras.put("paymentNum",checkDataList.get(12));
              List<Map<String,Object>> checkPolicyDataHkList =  checkPolicyDataHkClient.getCheckPolicyDataHkByCondition(paras);
            String alertFileType = params.get("alertFileType").toString();
            if ("0".equals(alertFileType) && CollUtil.isNotEmpty(checkPolicyDataHkList)){
                   throw new CheckException("保单号："+checkDataList.get(3)+",险种名称:"+checkDataList.get(4)+",首续期:"+checkDataList.get(12)+"已存在");
              }
              if ("1".equals(alertFileType)){
                if (CollUtil.isEmpty(checkPolicyDataHkList)){
                   throw new CheckException("保单号："+checkDataList.get(3)+",险种名称:"+checkDataList.get(4)+",首续期:"+checkDataList.get(12)+"不存在,不可修改");
                }
                if ("1".equals(checkPolicyDataHkList.get(0).get("settle_status"))){
                   throw new CheckException("保单号："+checkDataList.get(3)+",险种名称:"+checkDataList.get(4)+",首续期:"+checkDataList.get(12)+"已结算不可修改");
                }

              }
              //判断表格中是否存在重复数据
             String dataHkExcel = checkDataList.get(3)+"_"+checkDataList.get(4)+"_"+checkDataList.get(12);
              if (dataHKexcelList.contains(dataHkExcel)){
                   throw new CheckException("保单号："+checkDataList.get(3)+",险种名称:"+checkDataList.get(4)+",首续期:"+checkDataList.get(12)+"导入文件中存在重复值");
              }
              dataHKexcelList.add(dataHkExcel);
              checkDataMap.put("remark",checkDataList.get(13));//备注
              checkDataMap.put("createTime", params.get("now"));
              checkDataMap.put("createBy", params.get("emp"));//创建人
              check_policy_list.add(checkDataMap);
		  });
    }

   @RequestMapping("/delayCheck")
   @ResponseBody
    public DataMsg delayCheck (HttpServletRequest request,DataMsg dataMsg){
        try {
            String id = request.getParameter("id");
            Map<String, Object> paras = new HashMap<>();
            paras.put("id",id);
            paras.put("checkStatus",Constants.CHECK_STATUS_2);
            checkPolicyDataHkClient.updateDataHK(paras);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            dataMsg.setData(e.getMessage());
            logger.error("延迟对账操作异常",e);
        }
        return dataMsg;

    }
}
