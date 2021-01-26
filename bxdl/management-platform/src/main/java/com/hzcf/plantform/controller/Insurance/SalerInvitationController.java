package com.hzcf.plantform.controller.Insurance;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.insurance.InsuranceSalesInfoClient;
import com.hzcf.plantform.feign.insurance.SalerInvitationClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.SalerInvitation;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurance.sales.SalesContract;
import com.hzcf.pojo.insurance.sales.SalesEduJob;
import com.hzcf.pojo.insurance.sales.SalesRelative;
import com.hzcf.pojo.insurance.sales.SalesTitles;
import com.hzcf.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 职级管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/salerInvitation")
public class SalerInvitationController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalerInvitationClient salerInvitationClient; 
    @Autowired
    private InsuranceSalesInfoClient insuranceSalesInfoClient;
    @Autowired
	EmployeeFeignClient employeeFeignClient;
    
    /**
     * 跳转到列表页面
     *
     * */
	@RequestMapping("/toListPath")
    public String toListPath(){return "salerInvitation/salerInvitationList";} 

    /**
     * 获取分页列表
     *
     * */
	@ResponseBody
	@RequestMapping("/getSalerInvitationPage")
	public DataMsg getSalerInvitationPage(HttpServletRequest request,DataMsg dataMsg){
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
	        String likeSalerNo = request.getParameter("likeSalerNo");
	        if (StringUtil.isNotBlank(likeSalerNo)) {
	           params.put("likeSalerNo", likeSalerNo);
	        }
	        String personMobile = request.getParameter("personMobile");
	        if (StringUtil.isNotBlank(personMobile)) {
	           params.put("personMobile",personMobile);
	        }	
	        String personName = request.getParameter("personName");
	        if (StringUtil.isNotBlank(personName)) {
	           params.put("personName",personName);
	        }

			//数据权限相关查询条件
			Subject subject = SecurityUtils.getSubject();
			Employee employeeShiro = (Employee) subject.getPrincipal();
			employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
			params.put("isAdmin", employeeShiro.getEmployeeNo());
			params.put("myDeptNo", employeeShiro.getDeptNo());

	    	PageModel pageModel = salerInvitationClient.getSalerInvitationPage(params);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMsg;
	}
	
	/**
	 * 添加
	 * @param salerInvitation
	 * @return
	 */
	@RequiresPermissions("salerInvitation:add")
	@ResponseBody
	@RequestMapping("/addSalerInvitation")
	public boolean addSalerInvitation(SalerInvitation salerInvitation){
		try {
			salerInvitationClient.addSalerInvitation(salerInvitation);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 添加
	 * @param salerInvitation
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/renzhengSave")
	public boolean renzhengSave(HttpServletRequest request){
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String invitedId = request.getParameter("invitedId");
			String checkStatus = request.getParameter("checkStatus");
			String checkMark = request.getParameter("checkMark");
			SalerInvitation salerInvitation = new SalerInvitation();
			if(StringUtil.isNotBlank(invitedId)){
				map.put("invitedId",invitedId);
				salerInvitation = salerInvitationClient.selectById(map);
			}
			salerInvitation.setCheckStatus(checkStatus);
			salerInvitation.setCheckMark(checkMark);
			salerInvitationClient.updateSalerInvitation(salerInvitation);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 修改
	 * @param salerInvitation
	 * @return
	 */
	@RequiresPermissions("salerInvitation:toCreateSaler")
	@RequestMapping("/toCreateSaler")
	public String toCreateSaler(Model model, HttpServletRequest request){
		try {
	        String invitedId = request.getParameter("id");
	        Map<String, Object> paras = new HashMap<>();
	        paras.put("invitedId", invitedId);
	        SalerInvitation salerInvitation = salerInvitationClient.selectById(paras);
	        model.addAttribute("salerInvitation", salerInvitation);
			return "salerInvitation/toCreateSaler";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * 修改
	 * @param salerInvitation
	 * @return
	 */
	@RequiresPermissions("salerInvitation:toUpdateSaler")
	@RequestMapping("/toUpdateSaler")
	public String toUpdateSaler(Model model, HttpServletRequest request){
		try {
	        String invitedId = request.getParameter("id");
	        Map<String, Object> paras = new HashMap<>();
	        paras.put("invitedId", invitedId);
	        SalerInvitation salerInvitation = salerInvitationClient.selectById(paras);
	        paras.clear();
	        paras.put("insuranceSaler",salerInvitation.getPersonName());
	        paras.put("mobile",salerInvitation.getPersonMobile());
			InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoClient.selectBySalerInvitation(paras);
			paras.clear();
			paras.put("salesId", insuranceSalesInfo.getInsuranceSalesId());
			paras.put("deleteFlag","0");
			List<SalesTitles> salesTitles = insuranceSalesInfoClient.findSalesTitles(paras);
			List<SalesEduJob> salesEduJobs = insuranceSalesInfoClient.findSalesEduJobs(paras);
			List<SalesRelative> salesRelatives = insuranceSalesInfoClient.findSalesRelatives(paras);
			List<SalesContract> salesContracts = insuranceSalesInfoClient.findSalesContracts(paras);
			List<DirectorAllowanceStandardPojo> zjjtList = insuranceSalesInfoClient.findZjjt(paras);
			model.addAttribute("insuranceSalesInfo",insuranceSalesInfo);
			model.addAttribute("salesTitles",JSONObject.toJSONString(salesTitles));
			model.addAttribute("salesEduJobs",JSONObject.toJSONString(salesEduJobs));
			model.addAttribute("salesRelatives",JSONObject.toJSONString(salesRelatives));
			model.addAttribute("salesContracts",JSONObject.toJSONString(salesContracts));
			model.addAttribute("zjjtList",JSONObject.toJSONString(zjjtList));
			return "salesTeamInfoPage/insuranceSalesInfoUpdate";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * 修改
	 * @param salerInvitation
	 * @return
	 */
	@RequiresPermissions("salerInvitation:update")
	@ResponseBody
	@RequestMapping("/updateSalerInvitation")
	public boolean updateSalerInvitation(SalerInvitation salerInvitation){
		try {
			salerInvitationClient.updateSalerInvitation(salerInvitation);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 查详细
	 */	
	@ResponseBody
	@RequestMapping("/selectById")
	public Map<String,Object> selectById(@RequestParam("id")int id,Model model){
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("invitedId",id);
			SalerInvitation salerInvitation = salerInvitationClient.selectById(map);
			map.clear();
			map.put("salerInvitation", salerInvitation);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
