package com.hzcf.plantform.controller.message;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.message.NoticeClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.message.Notice;

@Controller
@RequestMapping("/doNotice")
public class NoticeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	NoticeClient noticeClient;
	
	@RequestMapping("/to_notice_list")
	public String toNoticeList(){
		
		return "message/notice/noticeList";
	}
	@ResponseBody
	@RequestMapping("/search_notice_list")
	public DataMsg searchNoticeList(HttpServletRequest request,DataMsg dataMsg){
		try {
			
			String id = request.getParameter("notice_id");
			logger.debug("notice_id:"+id);
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramMap.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			paramMap.put("business_line",request.getParameter("business_line"));
			paramMap.put("terminal",request.getParameter("terminal"));
			paramMap.put("title",request.getParameter("title"));
			if(!StringUtils.isEmpty(request.getParameter("title"))){
				paramMap.put("title", request.getParameter("title").trim());
			}

			PageModel  pageModel= noticeClient.searchNoticeList(paramMap);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error("公告列表数据异常："+e);
		}
		return dataMsg;
	}
	@ResponseBody
	@RequestMapping("/get_notice_by_id")
	public Notice getNoticeById(HttpServletRequest request){
		Notice  notice = null;
		try {
			
			String id = request.getParameter("id");
			logger.debug("notice_id:"+id);		
			notice = noticeClient.getNoticeById(id);
			
		} catch (Exception e) {
			logger.error("公告列表数据异常："+e);
		}
		return notice;
	}
	@ResponseBody
	@RequestMapping("/insert_notice")
	public boolean insertNotice(Notice notice){
		boolean  isTrue = true;
		try {
	
			if(noticeClient.insertNotice(notice) == 0){
				isTrue = false;
			};
			
		} catch (Exception e) {
			logger.error("公告列表添加异常："+e);
		}
		return isTrue;
	}
	@ResponseBody
	@RequestMapping("/update_notice")
	public boolean updateNoticeById(Notice notice){
		boolean  isTrue = true;
		try {
	
			if(noticeClient.updateNoticeById(notice) == 0){
				isTrue = false;
			};
			
		} catch (Exception e) {
			logger.error("公告列表修改异常："+e);
		}
		return isTrue;
	}
	@ResponseBody
	@RequestMapping("/delete_notice")
	public boolean deleteNoticeById(@RequestParam("id")String id){
		boolean  isTrue = true;
		try {
	
			if(noticeClient.deleteNoticeById(id) == 0){
				isTrue = false;
			};
			
		} catch (Exception e) {
			logger.error("公告列表删除异常："+e);
		}
		return isTrue;
	}

}
