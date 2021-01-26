package com.hzcf.plantform.util;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ImportUtil {

	public static List<List<Object>> readExcel(File file) throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}
	/**
	 * 读取execel中第二个sheet页开关信息
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<List<Object>> readExcelSwitch(File file) throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003ExcelSwitch(file);
		} else if ("xlsx".equals(extension)) {
			return read2007ExcelSwitch(file);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}
	/**
	 * 读取Office 2007 excel
	 */
	private static List<List<Object>> read2007Excel(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		//sheet.getPhysicalNumberOfRows() 得到excel总行数
		for (int i = 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("#.##");// 格式化 number String
				// 字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
				/*if (value == null || "".equals(value)) {
					continue;
				}*/
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}

	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<List<Object>> read2003Excel(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int i = 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("#.##");
														
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
			/*	if (value == null || "".equals(value)) {
					continue;
				}*/
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}
	
	/**
	 * 批量插入工具
	 * @param list
	 * @param len
	 * @return
	 */
	public static List<List<?>> splitList(List<?> list, int len) {
		if (list == null || list.size() == 0 || len < 1) {
		return null;
		}
		List<List<?>> result = new ArrayList<List<?>>();
		int size = list.size();
		int count = (size + len - 1) / len;
		for (int i = 0; i < count; i++) {
		List<?> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
		result.add(subList);
		}
		return result;
	}
	/**
	 * 读取Office 2007 excel  读取第二个sheet页开关信息
	 */
	private static List<List<Object>> read2007ExcelSwitch(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(1);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		//sheet.getPhysicalNumberOfRows() 得到excel总行数
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("#.##");// 格式化 number String
				// 字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
				/*if (value == null || "".equals(value)) {
					continue;
				}*/
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}
	/**
	 * 读取 office 2003 excel 读取第二个sheet页开关信息
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<List<Object>> read2003ExcelSwitch(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(1);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("#.##");
														
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
			/*	if (value == null || "".equals(value)) {
					continue;
				}*/
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}

    /**
     *@描述  解决读取Excel内容科学计数法问题
     *@创建人 qin lina
     *@创建时间 2020/3/20
     */
	public static String  cellFormat(Cell cell){
		String value = null;
		if(ObjectUtil.isNull(cell)){
			value = "";
		}else if (ObjectUtil.isNotNull(cell) && cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			short format = cell.getCellStyle().getDataFormat();
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = null;
                if (format == 20 || format == 32) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double cellValue = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(cellValue);
                    value = sdf.format(date);
                }else {// 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                try {
                    value = sdf.format(cell.getDateCellValue());// 日期
                } catch (Exception e) {
                    try {
                        throw new Exception("exception on get date data !".concat(e.toString()));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }finally{
                    sdf = null;
                }
            }  else {
				DecimalFormat df = new DecimalFormat("#.##");
				value = df.format(cell.getNumericCellValue());
			}
		}else {
			value = String.valueOf(cell).trim();
		}
		return  value;

	}



	/**
	 *@描述 批量导入必填项校验
	 *@创建人 qin lina
	 *@创建时间 2020/4/13
	 */
	public static List<String> validateMust(List<String> list, int[] filedindexs, String[] filedNames)  {
		Assert.isTrue(filedindexs.length == filedNames.length, "角标名与翻译名的个数不一致");
		  List<String> errorList = new ArrayList();
			  for(int i =0;i<filedindexs.length;i++){
                  int filedindex = filedindexs[i];
                  String filedName = filedNames[i];
                  if (StringUtils.isEmpty(list.get(filedindex).trim())){
                      errorList.add("【"+filedName+"】");
                  }
              }
              return errorList;
	 }
}
