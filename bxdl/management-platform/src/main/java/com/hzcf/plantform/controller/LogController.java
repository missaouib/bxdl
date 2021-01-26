package com.hzcf.plantform.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.LogFeignClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.util.StringUtil;


/**
*<dl>
*<dt>类名：LogController.java</dt>
*<dd>描述: 接口日志管理</dd>
*<dd>创建时间： 2018年11月14日 下午2:07:37</dd>
*<dd>创建人：tie</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2018年11月14日 下午2:07:37 tie 1.0 1.0 Version
* </pre>
*</dl>
*/
@Controller
@RequestMapping("/adminLog")
public class LogController {

	@Autowired
	private LogFeignClient  logFeignClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 跳转到日志列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/goLogsPage")
	public String goMenuPage() {
		return "system/log/logsList";
	}

	

	
	/**
	 * 分页查询所有日志
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLogList")
	public DataMsg getLogList(HttpServletRequest request,DataMsg dataMsg){
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			String requestURL = StringUtil.trim(request.getParameter("requestURL"));
			if (StringUtil.isNotBlank(requestURL)) {
				paramsCondition.put("requestURL", requestURL);
			}
			String startTime = StringUtil.trim(request.getParameter("startTime"));
			if (StringUtil.isNotBlank(startTime)) {
				paramsCondition.put("startTime", startTime);
			}
			String endTime = StringUtil.trim(request.getParameter("endTime"));
			if (StringUtil.isNotBlank(endTime)) {
				paramsCondition.put("endTime", endTime);
			}
			PageModel pageModel =logFeignClient.getLogList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error("分页查询所有日志异常"+e);
		}
		return dataMsg;
	}
	
}
