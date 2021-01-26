package com.hzcf.plantform.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.hzcf.plantform.fastdfs.FdfsClient;
import com.hzcf.plantform.util.DataMsg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fdfs")
public class FdfsController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private FdfsClient fdfsClient;

	/**
	 * 影像图片上传
	 */
	@RequestMapping("/uploadImage")
	@ResponseBody
	public DataMsg uploadImage(MultipartFile file,HttpServletRequest request,DataMsg msg) {
		try {
			String fileName = (file).getOriginalFilename();
			StorePath storePath = fdfsClient.uploadFile(file);
			String fastPath = storePath.getFullPath();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileName",fileName);
			map.put("fileUrl",fastPath);
			List<Object> oa = new ArrayList<Object>();
			oa.add(map);
			msg.setRows(oa);
			msg.setMessageCode("200");
		} catch (Exception e) {
			logger.error(e.getMessage());
			msg.setMessageCode("400");
		}
		return msg;
	}
}
