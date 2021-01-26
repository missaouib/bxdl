package com.hzcf.plantform.controller.message;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.domain.StorePath;
import com.hzcf.plantform.fastdfs.FdfsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hzcf.plantform.feign.message.MessageLogClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.message.MessageLog;

@Controller
@RequestMapping("/doMessageLog")
public class MessageLogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageLogClient messageLogClient;
    @Autowired
    private FdfsClient dfsClient;
    @Value("${fdfs.ip}")
    private String fdfsIp;
	
	@RequestMapping("/to_meesageLog_list")
	public String toMessageLog(){
		
		return "message/messageLog/messageLogList";
	}
	
	@ResponseBody
	@RequestMapping("/search_meesageLog_list")
	public DataMsg searchMessageLog(HttpServletRequest request,DataMsg dataMsg){
		try {
			
			String id = request.getParameter("messageLog_id");
			logger.debug("messageLog_id:"+id);
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramMap.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			paramMap.put("business_line",request.getParameter("business_line"));
			paramMap.put("terminal",request.getParameter("terminal"));
			paramMap.put("trigger_type",request.getParameter("trigger_type"));
			paramMap.put("content",request.getParameter("content"));
			if(!StringUtils.isEmpty(request.getParameter("content"))){
				paramMap.put("content", request.getParameter("content").trim());
			}

			PageModel  pageModel= messageLogClient.searchMessageLogList(paramMap);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error("短信息列表数据异常："+e);
		}
		return dataMsg;
	}
	@ResponseBody
	@RequestMapping("/get_messageLog_by_id")
	public JSONObject getMessageLogById(HttpServletRequest request){
		MessageLog  messageLog = null;
		JSONObject jSONObject = null;
		try {
			request.setAttribute("fdfsIp",fdfsIp);
			String id = request.getParameter("id");
			logger.info("messageLog_id:"+id);
			messageLog = messageLogClient.getMessageLogById(id);
			jSONObject = (JSONObject)JSONObject.toJSON(messageLog);
			jSONObject.put("fdfsIp",fdfsIp);
		} catch (Exception e) {
			logger.error("短信列表数据异常："+e);
		}
		return jSONObject;
	}

	@ResponseBody
	@RequestMapping("/insert_messageLog")
	public boolean insertMessageLog(MessageLog messageLog){
		boolean isTure = true;
		try {
			if (StringUtils.isEmpty(messageLog.getTrigger_type())){
				messageLog.setTrigger_type("000");
			}
			if(messageLogClient.insertMessageLog(messageLog)==0){
				isTure = false;
			}
			
		} catch (Exception e) {
			logger.error("短信列表添加异常："+e);
			e.printStackTrace();
		}
		return isTure;
	}


	@ResponseBody
	@RequestMapping("/upload")
    public Object upload(@RequestParam(value = "fileObject",required = false,defaultValue = "")MultipartFile file){
        StorePath storePath = null;
		String originalFilename = "";
		Map<String,Object> map = new HashMap<>();
		map.put("originalFilename","");
		map.put("fullPath","");
		map.put("status",false);
        try {
			originalFilename = file.getOriginalFilename();
			map.put("originalFilename",originalFilename);
			storePath = this.dfsClient.uploadFile(file);
			map.put("status",true);
        } catch (IOException e) {
            logger.error("上传文件异常："+e);
            e.printStackTrace();
        }
        if (storePath != null){
			map.put("fullPath",storePath.getFullPath());
		}
        return map;
    }

	@ResponseBody
	@RequestMapping("/update_messageLog")
	public boolean updateMessageLogById(MessageLog messageLog){
		boolean isTure = true;
		try {
			if(messageLogClient.updateMessageLogById(messageLog)==0){
				isTure = false;
			};
			
		} catch (Exception e) {
			logger.error("短信列表修改异常："+e);
		}
		return isTure;
	}
	@ResponseBody
	@RequestMapping("/delete_messageLog")
	public boolean updateMessageLogById(@RequestParam("id")String id){
		boolean isTure = true;
		try {
			if(messageLogClient.deleteMessageLogById(id)==0){
				isTure = false;
			};
			
		} catch (Exception e) {
			logger.error("短信列表删除异常："+e);
		}
		return isTure;
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
										   @RequestParam(value = "filename") String filename,
										   HttpServletResponse response) throws IOException {
		try {
			//下载文件路径
			String realPath = request.getServletContext().getRealPath("/upload/files/");
			File file = new File(realPath + File.separator + filename);
			//下载显示的文件名，解决中文名称乱码问题
			int index = filename.indexOf("_");
			String downloadFielName = new String(filename.substring(index+1).getBytes("UTF-8"), "iso-8859-1");
			HttpHeaders headers = new HttpHeaders();
			//通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment",downloadFielName);
			//application/octet-stream ： 二进制流数据（最常见的文件下载）
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),headers, HttpStatus.CREATED);
		} catch (IOException e) {
			logger.error("下载异常："+e);
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println("对不起，您访问的文件不存在！");
			return null;
		}
	}

	@RequestMapping("/downloadFile")
	public ResponseEntity<byte[]> download2(@RequestParam(value = "fullPath") String fullPath,
											@RequestParam(value = "originalFilename",required = false,defaultValue = "") String originalFilename,
                          					HttpServletResponse response) throws IOException {
		try {
			int index = fullPath.indexOf("/"),lastIndex = fullPath.lastIndexOf("/");
			if (index < 0){
				index = fullPath.indexOf("\"");
			}
			if (lastIndex < 0){
				lastIndex = fullPath.lastIndexOf("\"");
			}
			String groupName = fullPath.substring(0,index<0?0:index);
			String path = fullPath.substring(index+1);
			String fileName = fullPath.substring(lastIndex+1);
			if (!StringUtils.isEmpty(originalFilename)){
				fileName = originalFilename;
			}
			byte[] bytes = dfsClient.downloadByteArray(groupName, path);
			if (bytes == null || bytes.length <= 0){
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().println("对不起，您访问的文件不存在！");
				return null;
			}
			String downloadFielName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			HttpHeaders headers = new HttpHeaders();
			//通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment",downloadFielName);
			//application/octet-stream ： 二进制流数据（最常见的文件下载）
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.CREATED);
		}catch (IOException e){
			logger.error("下载异常："+e);
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println("对不起，您访问的文件不存在！");
			return null;
		}
	}

}
