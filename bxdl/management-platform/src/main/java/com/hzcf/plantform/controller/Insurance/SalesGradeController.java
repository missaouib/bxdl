package com.hzcf.plantform.controller.Insurance;

import com.hzcf.plantform.feign.insurance.SalesGradeClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.SalesGrade;
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
 * @author sxm
 *
 */
@Controller
@RequestMapping("/salesGrade")
public class SalesGradeController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesGradeClient salesGradeClient; 
    
    /**
     * 跳转到列表页面
     *
     * */
	@RequestMapping("/toListPath")
    public String toListPath(){return "salesTeamInfoPage/salesGradeList";} 

    /**
     * 获取分页列表
     *
     * */
	@ResponseBody
	@RequestMapping("/getSalesGradePage")
	public DataMsg getSalesGradePage(HttpServletRequest request,DataMsg dataMsg){
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
	    	PageModel pageModel = salesGradeClient.getSalesGradePage(params);
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
	@RequestMapping("/getSalesGradeList")
	public DataMsg getSalesGradeList(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String salesGradeCode = request.getParameter("salesGradeCode");
			String salesGradeId = request.getParameter("salesGradeId");
			String rankSequenceId = request.getParameter("rankSequenceId");
			if(StringUtil.isNotBlank(salesGradeCode)){
				params.put("salesGradeCode",salesGradeCode);
			}
			if(StringUtil.isNotBlank(salesGradeId)){
				params.put("checkSalesGradeId",salesGradeId);
			}
			if(StringUtil.isNotBlank(rankSequenceId)){
				params.put("rankSequenceId",rankSequenceId);
			}			
	    	List<SalesGrade> list = salesGradeClient.getSalesGradeList(params);
			dataMsg.setRows(list);
			dataMsg.setMessageCode("200");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMsg;
	}	
	
	/**
	 * 添加
	 * @param salesGrade
	 * @return
	 */
	@RequiresPermissions("salesGrade:add")
	@ResponseBody
	@RequestMapping("/addSalesGrade")
	public boolean addSalesGrade(SalesGrade salesGrade){
		try {
			salesGradeClient.addSalesGrade(salesGrade);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}	
	
	/**
	 * 修改
	 * @param salesGrade
	 * @return
	 */
	@RequiresPermissions("salesGrade:update")
	@ResponseBody
	@RequestMapping("/updateSalesGrade")
	public boolean updateSalesGrade(SalesGrade salesGrade){
		try {
			salesGradeClient.updateSalesGrade(salesGrade);
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
			map.put("salesGradeId",id);
			SalesGrade salesGrade = salesGradeClient.selectById(map);
			map.clear();
			map.put("salesGrade", salesGrade);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
