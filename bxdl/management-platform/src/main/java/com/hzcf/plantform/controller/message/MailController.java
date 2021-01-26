package com.hzcf.plantform.controller.message;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hzcf.plantform.feign.message.MailClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.message.Mail;

@Controller
@RequestMapping("/doMail")
public class MailController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MailClient mailClient;
	@Value("${fdfs.ip}")
	private String fdfsIp;
	
	@RequestMapping("/to_mail_list")
	public String toMailList(){	
		return "message/mail/mailList";
	}
	
	@ResponseBody
	@RequestMapping("/search_mail_list")
	public DataMsg searchMailList(HttpServletRequest request,DataMsg dataMsg){
		try {
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramMap.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			paramMap.put("business_line", request.getParameter("business_line"));
			paramMap.put("terminal", request.getParameter("terminal"));
			paramMap.put("trigger_type", request.getParameter("trigger_type"));
			paramMap.put("mail_title", request.getParameter("mail_title"));
			if(!StringUtils.isEmpty(request.getParameter("mail_title"))){
				paramMap.put("mail_title", request.getParameter("mail_title").trim());
			}
			PageModel  pageModel= mailClient.searchMailList(paramMap);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());

			
		} catch (Exception e) {
			logger.error("站内信列表数据异常：");
			e.printStackTrace();
		}
		return dataMsg;
	}
	
	@ResponseBody
	@RequestMapping("/get_mail_by_id")
	public JSONObject getMailById(@RequestParam("id")String id){
		Mail  mail = null;
		JSONObject jSONObject = null;
		try {
			logger.debug("mail_id:"+id);		
			mail = mailClient.getMailById(id);
			jSONObject = (JSONObject)JSONObject.toJSON(mail);
			jSONObject.put("fdfsIp",fdfsIp);
		} catch (Exception e) {
			logger.error("站内信列表数据异常：");
			e.printStackTrace();
		}
		return jSONObject;
	}

	@ResponseBody
	@RequestMapping("/insert_mail")
	public boolean insertMail(Mail mail){
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:"+mail.getTitle());
		boolean isTrue = true;
		try {
			if(StringUtils.isEmpty(mail.getTrigger_type())){
				mail.setTrigger_type("5");
			}
			if(mailClient.insertMail(mail)==0){
				isTrue = false;
			}
			
		} catch (Exception e) {
			logger.error("站内信添加数据异常："+e);
			e.printStackTrace();
		}
		return isTrue;
	}
	@ResponseBody
	@RequestMapping("/update_mail")
	public boolean updateMailById(Mail mail){
		boolean isTrue = true;;
		try {
	
			if(mailClient.updateMailById(mail)==0){
				isTrue = false;
			};
			
		} catch (Exception e) {
			logger.error("站内信修改数据异常：");
			e.printStackTrace();
		}
		return isTrue;
	}
	@ResponseBody
	@RequestMapping("/delete_mail")
	public boolean deleteMailById(@RequestParam("id")String id){
		boolean isTrue = true;;
		try {
	
			if(mailClient.deleteMailById(id)==0){
				isTrue = false;
			};
			
		} catch (Exception e) {
			logger.error("站内信删除数据异常：");
			e.printStackTrace();
		}
		return isTrue;
	}

}
