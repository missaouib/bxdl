package com.hzcf.plantform.controller.Insurance;

import com.hzcf.plantform.feign.insurance.RankSequenceClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.RankSequence;
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
 * 职级序列管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/rankSequence")
public class RankSequenceController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RankSequenceClient rankSequenceClient; 
    
    /**
     * 跳转到列表页面
     *
     * */
	@RequestMapping("/toListPath")
    public String toListPath(){return "salesTeamInfoPage/rankSequenceList";} 

    /**
     * 获取分页列表
     *
     * */
	@ResponseBody
	@RequestMapping("/getRankSequencePage")
	public DataMsg getRankSequencePage(HttpServletRequest request,DataMsg dataMsg){
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
	    	PageModel pageModel = rankSequenceClient.getRankSequencePage(params);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error("获取分页列表",e);
			e.printStackTrace();
		}
		return dataMsg;
	}
	
    /**
     * 获取分页列表
     *
     * */
	@ResponseBody
	@RequestMapping("/getRankSequenceList")
	public DataMsg getRankSequenceList(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String sequenceCode = request.getParameter("sequenceCode");
			if(StringUtil.isNotBlank(sequenceCode)){
				params.put("sequenceCode",sequenceCode);
			}
			String checkSequenceId = request.getParameter("checkSequenceId");
			if(StringUtil.isNotBlank(checkSequenceId)){
				params.put("checkSequenceId",checkSequenceId);
			}			
	    	List<RankSequence> list = rankSequenceClient.getRankSequenceList(params);
			dataMsg.setRows(list);
			dataMsg.setMessageCode("200");
		} catch (Exception e) {
			logger.error("获取分页列表",e);
			e.printStackTrace();
		}
		return dataMsg;
	}	
	
	/**
	 * 添加序列
	 * @param rankSequence
	 * @return
	 */
	@RequiresPermissions("rankSequence:add")
	@ResponseBody
	@RequestMapping("/addRankSequence")
	public boolean addRankSequence(RankSequence rankSequence){
		try {
			rankSequenceClient.addRankSequence(rankSequence);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加序列",e);
			return false;
		}
		
	}	
	
	/**
	 * 修改序列
	 * @param rankSequence
	 * @return
	 */
	@RequiresPermissions("rankSequence:update")
	@ResponseBody
	@RequestMapping("/updateRankSequence")
	public boolean updateRankSequence(RankSequence rankSequence){
		try {
			rankSequenceClient.updateRankSequence(rankSequence);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改序列",e);
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
			map.put("sequenceId",id);
			RankSequence rankSequence = rankSequenceClient.selectById(map);
			map.clear();
			map.put("rankSequence", rankSequence);
			return map;
		} catch (Exception e) {
			logger.error("查详细",e);
			e.printStackTrace();
			return null;
		}
	}	
}
