package com.hzcf.plantform.controller.Insurance;

import com.hzcf.plantform.feign.insurance.SalesTeamLeadersClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.SalesTeamLeaders;
import com.hzcf.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @author yl
 *
 */
@Controller
@RequestMapping("/salesTeamLeaders")
public class SalesTeamLeadersController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesTeamLeadersClient salesTeamLeadersClient; 
    
    /**
     * 跳转到列表页面
     *
     * */
	@RequestMapping("/toListPath")
    public String toListPath(){return "salesTeamInfoPage/salesTeamLeadersList";} 

    /**
     * 获取分页列表
     *
     * */
	@ResponseBody
	@RequestMapping("/getSalesTeamLeadersPage")
	public DataMsg getSalesTeamLeadersPage(HttpServletRequest request,DataMsg dataMsg){
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
	        String salesTeamId = request.getParameter("salesTeamId");
	        if (StringUtil.isNotBlank(salesTeamId)) {
	           params.put("salesTeamId",salesTeamId);
	        }
	        
	    	PageModel pageModel = salesTeamLeadersClient.getSalesTeamLeadersPage(params);
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
	@RequestMapping("/getSalesTeamLeadersList")
	public DataMsg getSalesTeamLeadersList(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String salesTeamLeadersCode = request.getParameter("salesTeamLeadersCode");
			String salesTeamLeadersId = request.getParameter("salesTeamLeadersId");
			String rankSequenceId = request.getParameter("rankSequenceId");
			if(StringUtil.isNotBlank(salesTeamLeadersCode)){
				params.put("salesTeamLeadersCode",salesTeamLeadersCode);
			}
			if(StringUtil.isNotBlank(salesTeamLeadersId)){
				params.put("checkSalesTeamLeadersId",salesTeamLeadersId);
			}
			if(StringUtil.isNotBlank(rankSequenceId)){
				params.put("rankSequenceId",rankSequenceId);
			}			
	    	List<SalesTeamLeaders> list = salesTeamLeadersClient.getSalesTeamLeadersList(params);
			dataMsg.setRows(list);
			dataMsg.setMessageCode("200");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMsg;
	}	
	
	/**
	 * 添加
	 * @param salesTeamLeaders
	 * @return
	 */
	@RequiresPermissions("salesTeamLeaders:add")
	@ResponseBody
	@RequestMapping("/addSalesTeamLeaders")
	public DataMsg addSalesTeamLeaders(HttpServletRequest request,SalesTeamLeaders salesTeamLeaders,DataMsg msg){
		try {
				Map<String, Object> params = new HashMap<>();
				String salesTeamId = request.getParameter("salesTeamId");
				if(StringUtil.isNotEmpty(salesTeamId)){
					params.put("salesTeamId",salesTeamId);
				}
				String leaderType = request.getParameter("leaderType");
				if(StringUtil.isNotEmpty(leaderType)){
					params.put("leaderType",leaderType);
				}
				String salerNo = request.getParameter("salerNo");
				if(StringUtil.isNotEmpty(salerNo)){
					params.put("salerNo",salerNo);
				}
				params.put("leaderStatus","0");
				List<SalesTeamLeaders> havNum = salesTeamLeadersClient.getSalesTeamLeadersList(params);
				if(havNum!=null && havNum.size()>0){
					msg.setMessageCode("500");
					msg.setRequestState("该员工已存在同等职位任命，请勿重复添加！");
				}else{
					salesTeamLeadersClient.addSalesTeamLeaders(salesTeamLeaders);
					msg.setMessageCode("200");
				}
		} catch (Exception e) {
            msg.setMessageCode("400");
            e.printStackTrace();
            msg.setRequestState("主管任命[新增]异常！");
            logger.info("主管任命[新增]异常");
		}
		return msg;
	}
	
	/**
	 * 终止
	 * @param salesTeamLeaders
	 * @return
	 */
	@RequiresPermissions("salesTeamLeaders:disable")
	@ResponseBody
	@RequestMapping("/disableLeaders")
	public boolean disableLeaders(HttpServletRequest request){
		try {
			
			Map<String, Object> params = new HashMap<>();
			String salesTeamId = request.getParameter("salesTeamId");
			String leaderIds = request.getParameter("leaderIds");
			if(leaderIds.endsWith(",")){
				leaderIds = leaderIds.substring(0,leaderIds.length()-1);
			}
			params.put("salesTeamId", salesTeamId);
			params.put("leaderIds", leaderIds);
			params.put("leaderStatus", "2");
			salesTeamLeadersClient.disableLeaders(params);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}	
	
	/**
	 * 修改
	 * @param salesTeamLeaders
	 * @return
	 */
	@RequiresPermissions("salesTeamLeaders:update")
	@ResponseBody
	@RequestMapping("/updateSalesTeamLeaders")
	public boolean updateSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders){
		try {
			salesTeamLeadersClient.updateSalesTeamLeaders(salesTeamLeaders);
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
			map.put("salesTeamLeadersId",id);
			SalesTeamLeaders salesTeamLeaders = salesTeamLeadersClient.selectById(map);
			map.clear();
			map.put("salesTeamLeaders", salesTeamLeaders);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
