package com.hzcf.plantform.controller.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.account.CashAccountFeignClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.DateTimeUtil;
import com.hzcf.plantform.util.ExportExcel;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.util.StringUtil;

/**
 * 现金账户管理
 * @author stming
 *
 */
@Controller
@RequestMapping("/account")
public class CashAccountController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private CashAccountFeignClient cashAccountFeignClient;
	
    /**
	 * 跳转到现金账户管理列表页面
	 * @return
	 */
	//@RequiresPermissions("accountManager:list")//权限管理;
	@RequestMapping("/goAccountPage")
	public String goAccountPage(){
		return "account/cashAccount/cashAccountList";
	}
	
	/**
	 * 跳转到现金账明细列表页面
	 * @return
	 */
	//@RequiresPermissions("cashTransDetailManager:list")//权限管理;
	@RequestMapping("/getCashTransDetailPage")
	public String getCashTransDetailPage(Model model, HttpServletRequest request){
		   Map<String, Object> map = new HashMap<String, Object>();
		   String cashAccountId = StringUtil.trim(request.getParameter("cashAccountId"));//渠道
			if (StringUtil.isNotBlank(cashAccountId)) {
				map.put("cashAccountId", cashAccountId);
			}
	        model.addAttribute("map", map);
		return "account/cashAccount/cashAccountDetailList";
	}
	
	/**
	 * 分页查询现金账户
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCashAccountList")
	public DataMsg getCashAccountList(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String channel = StringUtil.trim(request.getParameter("channel"));//渠道
			if (StringUtil.isNotBlank(channel)) {
				paramsCondition.put("channel", channel);
			}
			
			String accountStatus =  StringUtil.trim(request.getParameter("accountStatus"));//账户状态
			if (StringUtil.isNotBlank(accountStatus)) {
				paramsCondition.put("accountStatus", accountStatus);
			}
			
			String customerName =  StringUtil.trim(request.getParameter("customerName"));//客户姓名
			if (StringUtil.isNotBlank(customerName)) {
				paramsCondition.put("customerName", customerName);
			}
			
			String cardNo =  StringUtil.trim(request.getParameter("cardNo"));//身份证号
			if (StringUtil.isNotBlank(cardNo)) {
				paramsCondition.put("cardNo", cardNo);
			}
			
			String maxCreateTime = request.getParameter("maxCreateTime");
			if(StringUtil.isNotBlank(maxCreateTime)){
				paramsCondition.put("maxCreateTime", maxCreateTime.trim());
			}
			
			String minCreateTime = request.getParameter("minCreateTime");
			if(StringUtil.isNotBlank(minCreateTime)){
				paramsCondition.put("minCreateTime", minCreateTime.trim());
			}
	         
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			PageModel pageModel =cashAccountFeignClient.getCashAccountList(paramsCondition);
			
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("现金账户管理数据异常：");
		}
		return dataMsg;
	}
	
	/**
	 * 导出现金账户管理数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportCashAccountList")
	public void exportCashAccountList(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String channel = StringUtil.trim(request.getParameter("channel"));//渠道
			if (StringUtil.isNotBlank(channel)) {
				paramsCondition.put("channel", channel);
			}
			
			String accountStatus =  StringUtil.trim(request.getParameter("accountStatus"));//账户状态
			if (StringUtil.isNotBlank(accountStatus)) {
				paramsCondition.put("accountStatus", accountStatus);
			}
			
			String customerName =  StringUtil.trim(request.getParameter("customerName"));//客户姓名
			if (StringUtil.isNotBlank(customerName)) {
				paramsCondition.put("customerName", customerName);
			}
			
			String cardNo =  StringUtil.trim(request.getParameter("cardNo"));//身份证号
			if (StringUtil.isNotBlank(cardNo)) {
				paramsCondition.put("cardNo", cardNo);
			}
			
			String maxCreateTime = request.getParameter("maxCreateTime");
			if(StringUtil.isNotBlank(maxCreateTime)){
				paramsCondition.put("maxCreateTime", maxCreateTime.trim());
			}
			
			String minCreateTime = request.getParameter("minCreateTime");
			if(StringUtil.isNotBlank(minCreateTime)){
				paramsCondition.put("minCreateTime", minCreateTime.trim());
			}
		//得到查询的数据
		 List<Map<String, Object>> result = cashAccountFeignClient.exportCashAccountList(paramsCondition);
			String title = "现金账户管理数据列表" + DateTimeUtil.getDateNormalString(new Date());
			String[] rowsName = new String[] { "序号", "客户姓名", "身份证号", "银行卡号", "开户行", "银行预留手机号", "账户总额（元）", "账户可用余额（元）", "账户冻结金额（元）",
			"账户状态","账户创建时间","渠道" };
			List<Object[]> dataList = new ArrayList<Object[]>();
			Object[] objs = null;
			Map<String, Object> promotionMap = null;
			for (int i = 0; i < result.size(); i++) {
				promotionMap = result.get(i);
				objs = new Object[rowsName.length];
				
				objs[0] = i + 1;
				objs[1] = promotionMap.get("customerName");
				objs[2] = promotionMap.get("cardNo");
				objs[3] = promotionMap.get("bank_card_no");
				objs[4] = promotionMap.get("open_bank_name");
				objs[5] = promotionMap.get("bank_reserve_mobile");
				objs[6] = formatFloatNumber((Double) promotionMap.get("total_money"));
				objs[7] = formatFloatNumber((Double) promotionMap.get("available_money"));
				objs[8] = formatFloatNumber((Double)promotionMap.get("frozen_money"));
				objs[9] = promotionMap.get("account_status");
				objs[10] = promotionMap.get("create_time");
				objs[11] = promotionMap.get("channel");
				dataList.add(objs);
			}
			
	            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
	        	ex.export(response);
	        } catch (Exception e) {
	            logger.error("导出账户管理列表异常", e);
	        }
	}

	public static String formatFloatNumber(Double value) {
		if(value != null){
			if(value.doubleValue() != 0.00){
				java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
				return df.format(value.doubleValue());
			}else{
				return "0.00";
			}
		}
		return "";
	}
	/**
	 * 分页查询现金账户明细列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */ 
	@ResponseBody
	@RequestMapping("/getCashTransDetailList")
	public DataMsg getCashTransDetailList(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String cashAccountId = StringUtil.trim(request.getParameter("cashAccountId"));//现金账户id cashAccountId
			if (StringUtil.isNotBlank(cashAccountId)) {
				paramsCondition.put("cashAccountId", cashAccountId);
			}
			
			String detailOddNo = StringUtil.trim(request.getParameter("detailOddNo"));//明细单号
			if (StringUtil.isNotBlank(detailOddNo)) {
				paramsCondition.put("detailOddNo", detailOddNo);
			}
			
			String createTime =  StringUtil.trim(request.getParameter("createTime"));//创建时间
			if (StringUtil.isNotBlank(createTime)) {
				paramsCondition.put("createTime", createTime);
			}
			
			String detailType =  StringUtil.trim(request.getParameter("detailType"));//明细类别
			if (StringUtil.isNotBlank(detailType)) {
				paramsCondition.put("detailType", detailType);
			}
			
			String transStatus =  StringUtil.trim(request.getParameter("transStatus"));//账户状态
			if (StringUtil.isNotBlank(transStatus)) {
				paramsCondition.put("transStatus", transStatus);
			}
			
			String maxCreateTime = request.getParameter("maxCreateTime");
			if(StringUtil.isNotBlank(maxCreateTime)){
				paramsCondition.put("maxCreateTime", maxCreateTime.trim());
			}
			
			String minCreateTime = request.getParameter("minCreateTime");
			if(StringUtil.isNotBlank(minCreateTime)){
				paramsCondition.put("minCreateTime", minCreateTime.trim());
			}
			
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			PageModel pageModel =cashAccountFeignClient.getCashTransDetailList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("现金账户明细数据异常：");
		}
		return dataMsg;
	}
	/**
	 * 导出现金账户明细数据
	 * @param response
	 */
	
	@RequestMapping("/exportCashTransDetailList")
	public void exportCashTransDetailList(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String cashAccountId = StringUtil.trim(request.getParameter("cashAccountId"));//现金账户id cashAccountId
			if (StringUtil.isNotBlank(cashAccountId)) {
				paramsCondition.put("cashAccountId", cashAccountId);
			}
			
			String detailOddNo = StringUtil.trim(request.getParameter("detailOddNo"));//明细单号
			if (StringUtil.isNotBlank(detailOddNo)) {
				paramsCondition.put("detailOddNo", detailOddNo);
			}
			
			String createTime =  StringUtil.trim(request.getParameter("createTime"));//创建时间
			if (StringUtil.isNotBlank(createTime)) {
				paramsCondition.put("createTime", createTime);
			}
			
			String detailType =  StringUtil.trim(request.getParameter("detailType"));//明细类别
			if (StringUtil.isNotBlank(detailType)) {
				paramsCondition.put("detailType", detailType);
			}
			
			String transStatus =  StringUtil.trim(request.getParameter("transStatus"));//账户状态
			if (StringUtil.isNotBlank(transStatus)) {
				paramsCondition.put("transStatus", transStatus);
			}
			
			String maxCreateTime = request.getParameter("maxCreateTime");
			if(StringUtil.isNotBlank(maxCreateTime)){
				paramsCondition.put("maxCreateTime", maxCreateTime.trim());
			}
			
			String minCreateTime = request.getParameter("minCreateTime");
			if(StringUtil.isNotBlank(minCreateTime)){
				paramsCondition.put("minCreateTime", minCreateTime.trim());
			}
		// 得到查询的数据
		List<Map<String, Object>> result = cashAccountFeignClient.exportCashTransDetailList(paramsCondition);
			String title = "现金账户明细数据列表" + DateTimeUtil.getDateNormalString(new Date());
			String[] rowsName = new String[] { "序号", "明细单号", "生成时间", "明细类别", "金额（元）", "状态"};
			List<Object[]> dataList = new ArrayList<Object[]>();
			Object[] objs = null;
			Map<String, Object> promotionMap = null;
			for (int i = 0; i < result.size(); i++) {
				promotionMap = result.get(i);
				objs = new Object[rowsName.length];
				objs[0] = i + 1;
				objs[1] = promotionMap.get("detail_odd_no");
				objs[2] = promotionMap.get("create_time");
				objs[3] = promotionMap.get("detail_type");
				objs[4] = formatFloatNumber((Double)promotionMap.get("amount"));
				objs[5] = promotionMap.get("trans_status");
				
				dataList.add(objs);
			}
			
			ExportExcel ex = new ExportExcel(title, rowsName, dataList);
			ex.export(response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
