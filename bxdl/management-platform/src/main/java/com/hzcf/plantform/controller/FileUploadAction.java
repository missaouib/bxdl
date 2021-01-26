/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: AttachmentController.java 
 *
 * Created: [2017年6月27日 下午2:49:14] by yaodawei
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: lender-admin 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.hzcf.plantform.controller;

import com.alibaba.fastjson.JSONArray;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("/fileUpload")
public class FileUploadAction{
	@RequestMapping("/downloadExcel")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response){
		String realPath = request.getSession().getServletContext().getRealPath("/download/");
		String fileName = request.getParameter("fileName");
		File file = new File(realPath+"/"+fileName+".xls");
		if(file.exists() && file.isFile()){
			try {
				String headStr = "attachment; filename=\"" + new String(fileName.getBytes(Charset.forName("GBK")),Charset.forName("ISO-8859-1")) + ".xls\"";
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				FileInputStream in = new FileInputStream(realPath+"/"+fileName+".xls");
				HSSFWorkbook workbook = new HSSFWorkbook(in);
				//FileOutputStream out = new FileOutputStream(fileName+".xls");//禁止使用FileOutputStream，防止文件异常
				ServletOutputStream out = response.getOutputStream();
				workbook.write(out);
				out.flush();
				out.close();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			file.delete();
		}
	}

}
