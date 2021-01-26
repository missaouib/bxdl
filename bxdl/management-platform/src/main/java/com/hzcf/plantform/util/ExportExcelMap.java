package com.hzcf.plantform.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:封装多出excel表格导出时,个列的颜色起止位置
 * @project:lender-crm-util
 * @file:ExportExcelMap.java
 * @package:com.huizhongcf.lender.util
 * @Class:ExportExcelMap
 * @date:2018年7月20日下午1:42:18
 * @author:sxm
 */
public class ExportExcelMap {
	private static ExportExcelMap exportexcelmap;
	public static final Integer teamMonthlistType=0;//团队月度导出查询列表标识符
	public static final Integer teamMonthstatement=1;//团队月度导出报表标识符
	public static final Integer personteamMonthlistType=2;//个人月度导出查询列表标识符
	public static final Integer personteamMonthstatement=3;//个人月度导出报表标识符
	public static final Integer teamYearlistType=4;//团队年度导出查询列表标识符
	public static final Integer teamYearstatement=5;//团队年度导出报表标识符
	public static final Integer teamDay=6;
	public static final Integer personDay =7;
	
	public static Integer getTeamday() {
		return teamDay;
	}
	public static Integer getPersonday() {
		return personDay;
	}
	public static Integer getPersonteammonthlisttype() {
		return personteamMonthlistType;
	}
	public static Integer getPersonteammonthstatement() {
		return personteamMonthstatement;
	}
	public static Integer getTeamyearlisttype() {
		return teamYearlistType;
	}
	public static Integer getTeamyearstatement() {
		return teamYearstatement;
	}
	public static Integer getTeamMonthListType() {
		return teamMonthlistType;
	}
	public static Integer getTeamMonthStatement() {
		return teamMonthstatement;
	}
	static {
		exportexcelmap = new ExportExcelMap();
	}

	private ExportExcelMap() {

	}
	public static ExportExcelMap getexportexcelmap() {
		return exportexcelmap;
	}
	//Monthly team list starting and ending position （团队月度查询结果）
	public Map<String, Integer> getStyleMapTeamMonthSelectList(){
		Map<String,Integer> styleMap = new HashMap<String,Integer>();
		styleMap.put("base", 0);
		styleMap.put("base_deptLength_null", 6);
		styleMap.put("base_deptLength_0", 11);
		styleMap.put("base_deptLength_1", 6);
		styleMap.put("base_deptLength_2", 7);
		styleMap.put("base_deptLength_3", 8);
		styleMap.put("base_deptLength_4", 9);
		styleMap.put("base_deptLength_5", 10);
		styleMap.put("base_deptLength_6", 11);
		styleMap.put("task",4) ;
		styleMap.put("gushou",2) ;
		styleMap.put("gushou_c",4) ;
		styleMap.put("weixin",3) ;
		styleMap.put("weixin_c",5) ;
		styleMap.put("yunbaobei",3) ;
		styleMap.put("yunbaobei_c",5) ;
		styleMap.put("jijin",2) ;
		styleMap.put("jijin_c",4) ;
		styleMap.put("hyk",3) ;
		styleMap.put("hyk_c",5) ;
		styleMap.put("jjs",3) ;
		styleMap.put("jjs_c",4) ;
		styleMap.put("yxhh_c",4) ;
		styleMap.put("yxhh",2) ;
		return styleMap;
	}
	//Monthly team statement starting and ending position（团队月度报表结果）
	public Map<String, Integer> getStyleMapTeamMonthstatement(){
		Map<String,Integer> styleMap = new HashMap<String,Integer>();
		styleMap.put("base", 0);
		styleMap.put("base_deptLength_1", 13);
		styleMap.put("base_deptLength_2", 14);
		styleMap.put("base_deptLength_3", 15);
		styleMap.put("base_deptLength_4", 16);
		styleMap.put("base_deptLength_5", 17);
		styleMap.put("base_deptLength_6", 17);
		styleMap.put("task",9) ;
		styleMap.put("gushou",15) ;
		styleMap.put("gushou_c",17) ;
		styleMap.put("weixin",6) ;
		styleMap.put("weixin_c",8) ;
		styleMap.put("yunbaobei",6) ;
		styleMap.put("yunbaobei_c",8) ;
		styleMap.put("jijin",5);
		styleMap.put("jijin_c",7);
		styleMap.put("hyk",6);
		styleMap.put("hyk_c",8);
		styleMap.put("jjs",5);
		styleMap.put("jjs_c",7);
		styleMap.put("yxhh_c",7);
		
		styleMap.put("yxhh",5);
		styleMap.put("other_deptLength_1",18);
		styleMap.put("other_deptLength_2",15);
		styleMap.put("other_deptLength_3",12);
		styleMap.put("other_deptLength_4",9);
		styleMap.put("other_deptLength_5",7);
		styleMap.put("other_deptLength_6",3);
		styleMap.put("XyXt", 10);
		return styleMap;
	}

	//Year team list starting and ending position（团队年度查询结果）
	public Map<String, Integer> getStyleMapTeamYearSelectList(){
		Map<String,Integer> styleMap = new HashMap<String,Integer>();
		styleMap.put("base", 0);
		styleMap.put("base_deptLength_null", 6);
		styleMap.put("task",4) ;
		styleMap.put("gushou",2) ;
		styleMap.put("weixin",3) ;
		styleMap.put("yunbaobei",3) ;
		styleMap.put("jijin",2) ;
		styleMap.put("hyk",3) ;
		styleMap.put("jjs",2) ;
		styleMap.put("yxhh",2) ;
		return styleMap;
	}

	//Year team statement starting and ending position（团队年度报表结果）
	public Map<String, Integer> getStyleMapTeamYearstatement(){
		Map<String,Integer> styleMap = new HashMap<String,Integer>();
		styleMap.put("base", 0);
		styleMap.put("base_deptLength_1", 7);
		styleMap.put("base_deptLength_2", 8);
		styleMap.put("base_deptLength_3", 9);
		styleMap.put("base_deptLength_4", 10);
		styleMap.put("task",4) ;
		styleMap.put("gushou",4) ;
		styleMap.put("weixin",5) ;
		styleMap.put("yunbaobei",5) ;
		styleMap.put("jijin",4);
		styleMap.put("hyk",5);
		styleMap.put("jjs",4);
		styleMap.put("yxhh",4);
		return styleMap;
	}
	//Person team list starting and ending position（个人月度查询结果）
	public Map<String, Integer> getStyleMapTeamPersonlist(){
		Map<String,Integer> styleMap = new HashMap<String,Integer>();
		styleMap.put("base",11);
		styleMap.put("task",4) ;
		styleMap.put("gushou",2) ;
		styleMap.put("weixin",3) ;
		styleMap.put("yunbaobei",3) ;
		styleMap.put("jijin",2);
		styleMap.put("hyk",3);
		styleMap.put("jjs",2);
		styleMap.put("yxhh",2);
		return styleMap;
	}
	//Person team statement starting and ending position（个人月度报表结果）
	public Map<String, Integer> getStyleMapTeamPersonStatement(){
		Map<String,Integer> styleMap = new HashMap<String,Integer>();
		styleMap.put("base",12);
		styleMap.put("task",5) ;
		styleMap.put("gushou",4) ;
		styleMap.put("weixin",6) ;
		styleMap.put("yunbaobei",6) ;
		styleMap.put("jijin",5);
		styleMap.put("hyk",6);
		styleMap.put("jjs",5);
		styleMap.put("yxhh",5);
		return styleMap;
	}
	//team day statement
	public Map<String, Integer> teamDayMap(){
		Map<String,Integer> styleMap = new HashMap<String,Integer>();
		styleMap.put("base", 0);
		styleMap.put("base_deptLength_1", 7);
		styleMap.put("base_deptLength_2", 8);
		styleMap.put("base_deptLength_3", 9);
		styleMap.put("base_deptLength_4", 10);
		styleMap.put("base_deptLength_5", 11);
		styleMap.put("base_deptLength_6", 12);
		styleMap.put("task",2) ;
		styleMap.put("gushou",4) ;
		
		styleMap.put("weixin",5) ;
		
		styleMap.put("yunbaobei",5) ;
		styleMap.put("jijin",4);
		styleMap.put("hyk",5);
		styleMap.put("jjs",4);
		styleMap.put("yxhh",4);
		return styleMap;
	}
	//person day statement
	public Map<String,Integer> personDayMap(){
		Map<String,Integer> styleMap = new HashMap<String,Integer>();
		styleMap.put("base",12);
		styleMap.put("task",2) ;
		styleMap.put("gushou",4) ;
		styleMap.put("weixin",5) ;
		styleMap.put("yunbaobei",5) ;
		styleMap.put("jijin",4);
		styleMap.put("hyk",5);
		styleMap.put("jjs",4);
		styleMap.put("yxhh",4);
		return styleMap;
	}
}
