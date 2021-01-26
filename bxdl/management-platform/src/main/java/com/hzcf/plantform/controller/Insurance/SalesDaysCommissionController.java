package com.hzcf.plantform.controller.Insurance;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.insurance.InsuranceSalesInfoClient;
import com.hzcf.plantform.feign.insurance.SalesDaysCommissionClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.feign.parameter.BenchmarkingDiscountCoefficientClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.DateTimeUtil;
import com.hzcf.plantform.util.ExportExcel;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.insurance.sales.SalesMonthlyCommission;
import com.hzcf.pojo.parameter.BenchmarkingDiscountCoefficient;
import com.hzcf.util.StringUtil;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 职级管理
 * @author yl
 *
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/salesDaysCommission")
public class SalesDaysCommissionController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesDaysCommissionClient salesDaysCommissionClient; 
    @Autowired
    private InsuranceSalesInfoClient insuranceSalesInfoClient;
    @Autowired
    private BenchmarkingDiscountCoefficientClient benchmarkingDiscountCoefficientClient;
    @Autowired
	EmployeeFeignClient employeeFeignClient;
	@Autowired
	SalesOrgInfoClient salesOrgInfoClient;
    
    /**
     * 跳转到列表页面
     *
     * */
	@RequestMapping("/toListPath")
    public String toListPath(){return "salesCommission/salesDaysCommissionList";} 
	
    /**
     * 跳转到列表页面
     *
     * */
	@RequestMapping("/toMListPath")
    public String toMListPath(){return "salesCommission/salesMonthlyCommissionList";} 

    /**
     * 获取分页列表
     *
     * */
	@ResponseBody
	@RequestMapping("/getCommissionPage")
	public DataMsg getCommissionPage(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
	        String pageNo = request.getParameter("pageNo");
	        if (StringUtil.isNotBlank(pageNo)) {
	           params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
	        }else{
	           params.put("pageNo",1);
	        }
	        String pageSize = request.getParameter("pageSize");
	        if (StringUtil.isNotBlank(pageSize)) {
	           params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
	        }else{
	           params.put("pageSize",10);
	        }
	        String salesOrgId = request.getParameter("salesOrgId");
	        if (StringUtil.isNotBlank(salesOrgId)) {
	           //params.put("salesOrgId",salesOrgId);
				String salesAllOrgs = salesOrgInfoClient.findEmployeeAllOrgs(salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(salesOrgId).get(0).get("SALES_ORG_CODE").toString());
				params.put("salesAllOrgs", salesAllOrgs);
	        }
	        String salesTeamId = request.getParameter("salesTeamId");
	        if (StringUtil.isNotBlank(salesTeamId)) {
	           params.put("salesTeamId",salesTeamId);
	        }
	        String salesGradeId = request.getParameter("salesGradeId");
	        if (StringUtil.isNotBlank(salesGradeId)) {
	           params.put("salerGrade",salesGradeId);
	        }
	        String insuranceSalerNo = request.getParameter("insuranceSalerNo");
	        if (StringUtil.isNotBlank(insuranceSalerNo)) {
	           params.put("insuranceSalerNo",insuranceSalerNo);
	        }	  
	        String insuranceSaler = request.getParameter("insuranceSaler");
	        if (StringUtil.isNotBlank(insuranceSaler)) {
	           params.put("insuranceSaler",insuranceSaler);
	        }
	        String startTime = request.getParameter("startTime");
	        if (StringUtil.isNotBlank(startTime)) {
	           params.put("startTime",startTime+" 00:00:00");
	        }
	        String endTime = request.getParameter("endTime");
	        if (StringUtil.isNotBlank(endTime)) {
	           params.put("endTime",endTime+" 23:59:59");
	        }

			//数据权限相关查询条件
			Subject subject = SecurityUtils.getSubject();
			Employee employeeShiro = (Employee) subject.getPrincipal();
			employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
			params.put("isAdmin", employeeShiro.getEmployeeNo());
			params.put("myDeptNo", employeeShiro.getDeptNo());

	    	PageModel pageModel = salesDaysCommissionClient.getCommissionPage(params);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMsg;
	}
	
	 /**
     * 获取分页列表
     *
     * */
	@ResponseBody
	@RequestMapping("/getMonthlyCommissionPage")
	public DataMsg getMonthlyCommissionPage(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
	        String pageNo = request.getParameter("pageNo");
	        if (StringUtil.isNotBlank(pageNo)) {
	           params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
	        }else{
	           params.put("pageNo",1);
	        }
	        String pageSize = request.getParameter("pageSize");
	        if (StringUtil.isNotBlank(pageSize)) {
	           params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
	        }else{
	           params.put("pageSize",10);
	        }
	        String salesOrgId = request.getParameter("salesOrgId");
	        if (StringUtil.isNotBlank(salesOrgId)) {
	           //params.put("salesOrgId",salesOrgId);
				String salesAllOrgs = salesOrgInfoClient.findEmployeeAllOrgs(salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(salesOrgId).get(0).get("SALES_ORG_CODE").toString());
				params.put("salesAllOrgs", salesAllOrgs);
	        }
	        String salesTeamId = request.getParameter("salesTeamId");
	        if (StringUtil.isNotBlank(salesTeamId)) {
	           params.put("salesTeamId",salesTeamId);
	        }
	        String salesGradeId = request.getParameter("salesGradeId");
	        if (StringUtil.isNotBlank(salesGradeId)) {
	           params.put("salerGrade",salesGradeId);
	        }
	        String insuranceSalerNo = request.getParameter("insuranceSalerNo");
	        if (StringUtil.isNotBlank(insuranceSalerNo)) {
	           params.put("insuranceSalerNo",insuranceSalerNo);
	        }	
	        String insuranceSaler = request.getParameter("insuranceSaler");
	        if (StringUtil.isNotBlank(insuranceSaler)) {
	           params.put("insuranceSaler",insuranceSaler);
	        }
	        String commissionStatus = request.getParameter("commissionStatus");
	        if (StringUtil.isNotBlank(commissionStatus)) {
	           params.put("commissionStatus",commissionStatus);
	        }
	        String commissionMonth_s = request.getParameter("commissionMonth_s");
	        if (StringUtil.isNotBlank(commissionMonth_s)) {
	           params.put("commissionMonth_s",commissionMonth_s);
	        }
	        String commissionMonth_e = request.getParameter("commissionMonth_e");
	        if (StringUtil.isNotBlank(commissionMonth_e)) {
	           params.put("commissionMonth_e",commissionMonth_e);
	        }
	        String pushTime_s = request.getParameter("pushTime_s");
	        if (StringUtil.isNotBlank(pushTime_s)) {
	           params.put("pushTime_s",pushTime_s+" 00:00:00");
	        }
	        String pushTime_e = request.getParameter("pushTime_e");
	        if (StringUtil.isNotBlank(pushTime_e)) {
	           params.put("pushTime_e",pushTime_e+" 23:59:59");
	        }

			//数据权限相关查询条件
			Subject subject = SecurityUtils.getSubject();
			Employee employeeShiro = (Employee) subject.getPrincipal();
			employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
			params.put("isAdmin", employeeShiro.getEmployeeNo());
			params.put("myDeptNo", employeeShiro.getDeptNo());

	    	PageModel pageModel = salesDaysCommissionClient.getMonthlyCommissionPage(params);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMsg;
	}
	
	/**
	 * 添加
	 * @param salesDaysCommission
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addSalesDaysCommission")
	public boolean addSalesDaysCommission(HttpServletRequest request){
		try {
			List<SalesDaysCommission> salesDaysCommissions = new ArrayList<SalesDaysCommission>();
			Map<String, Object> params = new HashMap<String, Object>();
			/*获取正常员工列表*/
			params.put("effective","effective");
			List<InsuranceSalesInfo> sales = insuranceSalesInfoClient.insuranceSalesList(params);
			if(sales!=null && sales.size()>0){
				for(InsuranceSalesInfo saler:sales){
					SalesDaysCommission sdc = calculationCommission(saler);					
					salesDaysCommissions.add(sdc);
				}
			}						
			salesDaysCommissionClient.addSalesDaysCommission(salesDaysCommissions);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 *@描述 跳转到员工佣金明细
	 *@创建人 qin lina
	 *@创建时间 2020/5/7
	 */
	@RequestMapping("/toSalesCommissionDetailPage")
    public String toSalesCommissionDetailPage(String id, Model model){
        model.addAttribute("salerId",id);
        return "salesCommission/salesCommissionDetail";
    }


     /**
     * 获取分页列表
     *
     * */
	@ResponseBody
	@RequestMapping("/getSalesCommissionDetail")
	public DataMsg getSalesCommissionDetail(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
	        String pageNo = request.getParameter("pageNo");
	        if (StringUtil.isNotBlank(pageNo)) {
	           params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
	        }else{
	           params.put("pageNo",1);
	        }
	        String pageSize = request.getParameter("pageSize");
	        if (StringUtil.isNotBlank(pageSize)) {
	           params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
	        }else{
	           params.put("pageSize",10);
	        }
	        String salesId = request.getParameter("salerId");
	        if (StringUtil.isNotBlank(salesId)) {
	           params.put("salesId",salesId);
	        }
	    	PageModel pageModel = salesDaysCommissionClient.getSalesCommissionDetail(params);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMsg;
	}
	
	/**
	 * 查详情
	 * @param salesDaysCommission findDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findDetail")
	public String findDetail(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		try {			
	        String commissionId = request.getParameter("commissionId");
	        if (StringUtil.isNotBlank(commissionId)) {
	           params.put("commissionId",commissionId);
	        }	
			params = salesDaysCommissionClient.findDetail(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(params);
	}
	
	/**
	 * 查详情
	 * @param salesDaysCommission checkStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkStatus")
	public DataMsg checkStatus(HttpServletRequest request,DataMsg dataMsg){
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			//佣金审核表主键
	        String commissionId = request.getParameter("checkCommissionId");
	        if (StringUtil.isNotBlank(commissionId)) {
	           params.put("commissionId",commissionId);
	        }
	        //审核结果
	        String commissionStatus = request.getParameter("commission_status");
	        if (StringUtil.isNotBlank(commissionStatus)) {
	           params.put("commissionStatus",commissionStatus);
	        }
	        //加扣款原因
	        String cutReason = request.getParameter("cut_reason");
	        if (StringUtil.isNotBlank(cutReason)) {
	           params.put("cutReason",cutReason);
	        }
	        //加扣款类型
	         String cutCommissionStatus = request.getParameter("cut_commission_status");
	        String zf = "";
	        if (StringUtil.isNotBlank(cutCommissionStatus) && "1".equals(cutCommissionStatus)) {
	          zf = "-";
	        }
	        //获取加扣款和冻结税额之前的应发金额
			String afterTaxCommission = request.getParameter("afterTaxCommission");
	        BigDecimal newAfterTaxCommission = BigDecimal.ZERO;
            if (StringUtil.isNotBlank(afterTaxCommission)) {
            	newAfterTaxCommission = new BigDecimal(afterTaxCommission);
	           params.put("afterTaxCommission",afterTaxCommission);
	        }
	        //加扣款
	         String cutCommission = request.getParameter("cut_commission");
	        if (StringUtil.isNotBlank(cutCommission)) {
	        	if (StringUtil.isNotBlank(cutCommissionStatus) && "1".equals(cutCommissionStatus)){
	        		newAfterTaxCommission = newAfterTaxCommission.subtract(new BigDecimal(cutCommission)) ;
				}else {
	        		newAfterTaxCommission = newAfterTaxCommission.add(new BigDecimal(cutCommission)) ;
				}

	           params.put("cutCommission",zf+cutCommission);
	        }
	        //冻结税额
	         String freezingTaxes = request.getParameter("freezing_taxes");
	        if (StringUtil.isNotBlank(freezingTaxes)) {
	        	newAfterTaxCommission = newAfterTaxCommission.subtract(new BigDecimal(freezingTaxes));
	           params.put("freezingTaxes",freezingTaxes);
	        }
	        //结算后的应发金额
	        params.put("afterTaxCommission",newAfterTaxCommission);
	         String remark = request.getParameter("remark");
	        if (StringUtil.isNotBlank(remark)) {
	           params.put("remark",remark);
	        }
			salesDaysCommissionClient.checkStatus(params);
			dataMsg.setMessageCode("200");
		} catch (Exception e) {
			e.printStackTrace();
			dataMsg.setMessageCode("400");
		}
		return dataMsg;
	}
	/**
	 *@描述 批量发放
	 *@创建人 qin lina
	 *@创建时间 2020/5/8
	 */
	@RequestMapping("/batchRelease")
	@ResponseBody
	public Map<String,Object> batchRelease(HttpServletRequest request){
	    Map<String, Object> map = new HashMap<>();
        try {
            String commissionId = request.getParameter("commissionId");
            if (StringUtil.isNotBlank(commissionId)){
              map.put("commissionId",commissionId);
            }
            salesDaysCommissionClient.batchRelease(map);
            map.put("result",true);
             return map;
        } catch (Exception e) {
             map.put("result",false);
             map.put("msg",e.getMessage());
             logger.info("批量发放异常"+e.getMessage());
              e.printStackTrace();
            return map;
        }


    }
	
	@RequestMapping("/commissionExport")
    /*@ResponseBody*/
    public void commissionExport(HttpServletRequest request, HttpServletResponse response,DataMsg dataMsg) {
        try {
            Map<String, Object> params = new HashMap<>();
            String ids = request.getParameter("ids");
            if(StringUtil.isNotBlank(ids) && !ids.equals("all")){
            	params.put("ids",ids);
            }
			getAuthDataParams(params);
            List<Object> resultList = salesDaysCommissionClient.findMonthlyCommissions(params);
            List<Object[]> dataList = Lists.newArrayListWithExpectedSize(resultList.size());
            String title = "员工佣金审核导出-" + DateTimeUtil.getNowDateChinaString();
            Object[] objs = null;
            Object map = new Object();
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String[] rowsName = new String[]{"序号", "系统编号", "佣金月","员工姓名", "员工工号", "组织机构","销售团队", "员工职级","佣金收入", "佣金加扣", "加扣原因", "冻结税额", "税后佣金", "状态", "审核时间", "发放时间"};
            for (int i = 0; i < resultList.size(); i++) {
                map = resultList.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i + 1;//序号
                objs[1] = net.sf.json.JSONObject.fromObject(map).get("COMMISSION_ID")+"";
                objs[2] = net.sf.json.JSONObject.fromObject(map).get("COMMISSION_MONTH")+"";
                objs[3] = net.sf.json.JSONObject.fromObject(map).get("insurance_saler")+"";
                objs[4] = net.sf.json.JSONObject.fromObject(map).get("insurance_saler_no")+"";
                objs[5] = net.sf.json.JSONObject.fromObject(map).get("sales_org_name")+"";
                objs[6] = net.sf.json.JSONObject.fromObject(map).get("sales_team_name")+"";
                objs[7] = net.sf.json.JSONObject.fromObject(map).get("sales_grade_name")+"";
                objs[8] = net.sf.json.JSONObject.fromObject(map).get("TOTAL_COMMISSION")+"";
                objs[9] = net.sf.json.JSONObject.fromObject(map).get("CUT_COMMISSION")+"";
                objs[10] = net.sf.json.JSONObject.fromObject(map).get("CUT_REASON")+"";
                objs[11] = net.sf.json.JSONObject.fromObject(map).get("FREEZING_TAXES")+"";
                objs[12] = net.sf.json.JSONObject.fromObject(map).get("AFTER_TAX_COMMISSION")+"";
                String commissionStatus = net.sf.json.JSONObject.fromObject(map).get("COMMISSION_STATUS")+"";
                if(StringUtil.isNotEmpty(commissionStatus)){
                	if(commissionStatus.equals("0")){commissionStatus = "待审核";}else if(commissionStatus.equals("1")){commissionStatus = "审核待发放";}
                	else if(commissionStatus.equals("-1")){commissionStatus = "审核不通过";}else if(commissionStatus.equals("2")){commissionStatus = "已发放";}
                }
                objs[13] = commissionStatus;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String shsj = net.sf.json.JSONObject.fromObject(map).get("CHECK_TIME")+"";
                if(StringUtils.isNotBlank(shsj) && !shsj.equals("null")){
                	objs[14] = String.valueOf(sdf.format(new Date(Long.valueOf(shsj))));
                }else{
                	objs[14] = "-";
                }
                String ffsj = net.sf.json.JSONObject.fromObject(map).get("PUSH_TIME")+"";
                if(StringUtils.isNotBlank(ffsj) && !ffsj.equals("null")){
                	objs[15] = String.valueOf(sdf.format(new Date(Long.valueOf(ffsj))));
                }else{
                	objs[15] = "-";
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
            logger.error("导出[机构列表] | 异常"+e);
            dataMsg.setMessageCode("400");
        }
        
    }
	
	/**
	 * 查详情
	 * @param salesMonthlyCommission findMDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findMDetail")
	public String findMDetail(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		try {			
	        String commissionId = request.getParameter("commissionId");
	        if (StringUtil.isNotBlank(commissionId)) {
	           params.put("commissionId",commissionId);
	        }	
			params = salesDaysCommissionClient.findMDetail(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(params);
	}
	
	public SalesDaysCommission calculationCommission(InsuranceSalesInfo saler){
		Map<String, Object> params = new HashMap<String, Object>();
		SalesDaysCommission sdc = new SalesDaysCommission();		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date();
			/*统计周期*/
			String uwStart = "";
	        Calendar calendar = Calendar.getInstance();
	        if(calendar.get(Calendar.DAY_OF_MONTH)==1){
		        calendar.add(Calendar.MONTH,-1);
		        calendar.set(Calendar.DAY_OF_MONTH, 1); 
		        uwStart = format.format(calendar.getTime())+" 00:00:00";//上月第一天	        	
	        }else{
		        calendar.add(Calendar.MONTH, 0);
		        calendar.set(Calendar.DAY_OF_MONTH, 1); 
		        uwStart = format.format(calendar.getTime())+" 00:00:00";//当月第一天
	        }
	        String uwEnd = format.format(d)+" 00:00:00";//今天零点
			/*查询统计周期内员工完成的保单*/
			params.put("agentId",saler.getInsuranceSalesId());
			params.put("type","2");
			params.put("uwStart",uwStart);
			params.put("uwEnd",uwEnd);
			params.put("insState", Constants.INSURANCE_POLICY_STATUS_1010);
			List<Object> commissionData = salesDaysCommissionClient.findMapList(params);
			/*获取标保折标系数信息*/
			List<BenchmarkingDiscountCoefficient> zbxss = benchmarkingDiscountCoefficientClient.getBenchmarkingDiscountCoefficientList();
			
			for(Object ocd:commissionData){
				/*计算初年度佣金	初年度佣金=标保佣金+价值佣金
				标保佣金 = 内部标保佣金系数（产品中定义） * 标保折标系数（参数管理） * 规模保费 
				价值佣金FYC = 价值佣金系数（产品中定义）* 标保折标系数（参数管理） * 规模保费*/
				BigDecimal totalPremium = (BigDecimal)net.sf.json.JSONObject.fromObject(ocd).get("TOTAL_PREMIUM");//规模保费
				BigDecimal jzyjxs = (BigDecimal)net.sf.json.JSONObject.fromObject(ocd).get("VALUE_COMMISSION_COEFFICIENT");//价值佣金系数
				BigDecimal nbbbyjxs = (BigDecimal)net.sf.json.JSONObject.fromObject(ocd).get("IN_STANDARD_COMMISSION_COEFFICIENT");//内部标保佣金系数
				String jffs = net.sf.json.JSONObject.fromObject(ocd).get("PAYMENT_METHOD")+"";//缴费方式
//				BigDecimal bbzbxs = new BigDecimal(0.00);
				BigDecimal bbzbxs = BigDecimal.valueOf(0.00);
				for(BenchmarkingDiscountCoefficient zbxs:zbxss){
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sdc;		
	}
	
    /**
     * 导入
     * */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importExcel(MultipartFile file) throws Exception {
    	Map<String,Object> msg =new HashMap<String,Object>();
    	try {
    		File toFile = null;
            if (!file.isEmpty()) {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
                XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(toFile));
                List<SalesMonthlyCommission> cutCellList = new ArrayList<>();
                for(int i = 0; i<workbook.getNumberOfSheets();i++){
                    XSSFSheet att = workbook.getSheetAt(i);
                    for (int j = 1; j < att.getPhysicalNumberOfRows(); j++){
                        XSSFRow row = att.getRow(j);
                        SalesMonthlyCommission lines = new SalesMonthlyCommission();
                        lines.setCommissionId(row.getCell(1).equals("")?null:(new BigDecimal(row.getCell(1)+"")).longValue());
                        lines.setCommissionMonth(row.getCell(2).equals("")?null:(row.getCell(2)+""));
//                        lines.setCutCommission(row.getCell(9).equals("")?(new BigDecimal(0.00)):(new BigDecimal(row.getCell(9)+"")));
						lines.setCutCommission(row.getCell(9).equals("")?(BigDecimal.valueOf(0.00)):(new BigDecimal(row.getCell(9)+"")));
                        lines.setCutReason(row.getCell(10).equals("")?null:(row.getCell(10)+""));
//                        lines.setFreezingTaxes(row.getCell(11).equals("")?(new BigDecimal(0.00)):(new BigDecimal(row.getCell(11)+"")));
						lines.setFreezingTaxes(row.getCell(11).equals("")?(BigDecimal.valueOf(0.00)):(new BigDecimal(row.getCell(11)+"")));
//                        BigDecimal afterTaxCommission = new BigDecimal(0.00);
						BigDecimal afterTaxCommission = BigDecimal.valueOf(0.00);
                        afterTaxCommission = (new BigDecimal(row.getCell(8)+"")).add(new BigDecimal(row.getCell(9)+"")).subtract(new BigDecimal(row.getCell(11)+""));
                        lines.setAfterTaxCommission(afterTaxCommission);
                        /*String statusstr = "";
                        statusstr = row.getCell(13)+"";
                        if(statusstr.equals("待审核") || statusstr.equals("审核不通过")){*/
                        cutCellList.add(lines);
                        //}
                    }
                }
                if(cutCellList!=null && cutCellList.size()>0){
                	salesDaysCommissionClient.updateMonthlyCommissions(cutCellList);
                }
                msg.put("code","200");
            }else {
                msg.put("code","0000");
            }     
    	}catch (Exception e) {
			e.printStackTrace();
			msg.put("code","500");
		}
    	return msg;
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
			if (null != os) {
				os.close();
			}
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

		paras.put("myAllOrgIds", myAllOrgIds);

	}
	
}
