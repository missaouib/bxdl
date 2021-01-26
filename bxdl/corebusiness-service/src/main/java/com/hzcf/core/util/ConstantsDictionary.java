package com.hzcf.core.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * @description:系统外部接口字典产量类
 * @project:lender-crm-admin
 * @file:ConstantsDictionary.java
 * @package:com.huizhongcf.lender.admin.constant
 * @Class:ConstantsDictionary
 * @date:2019年1月8日上午11:17:42
 * @author:sxm
 */
public class ConstantsDictionary {
	//系统
	public static String KEY;//秘钥
	public static int CONNECTTIMEOUT;//接口请求响应超时时间
	public static int READTIMEOUT;//从主机读取数据超时时间
	//金领代
	public static String jldStaffLoad ;//接口url
	public static String systemIdentification;//系统标识
	//oa
	 public static String oaStaffLoad;//接口url
	 public static String systemIdentificationOa;//系统标识
	static{
		Properties props = new Properties();
		InputStreamReader in = null;
		try {
			System.out.println("------------------------------------------------------------------");
			in = new InputStreamReader(ConstantsDictionary.class
					.getClassLoader()
					.getResourceAsStream("/externalURL.properties"), "UTF-8");
			props.load(in);
			//金领贷推送员工信息
			KEY = props.getProperty("KEY");
			jldStaffLoad = props.getProperty("jld.load.jldStaffLoad.url");
			systemIdentification = props.getProperty("systemIdentification");
			CONNECTTIMEOUT = Integer.valueOf(props.getProperty("CONNECTTIMEOUT"));
			READTIMEOUT = Integer.valueOf(props.getProperty("READTIMEOUT"));
			//oa查询员工信息接口
			oaStaffLoad=props.getProperty("oa.load.oaStaffLoad.url");
			systemIdentificationOa=props.getProperty("systemIdentificationOa");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	

}
