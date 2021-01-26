package com.hzcf.plantform.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Created by lx916 on 2016/2/3.
 */
public class ExportExcel {
	//显示的导出表的标题
	private String title;
	//导出表的列名
	private String[] rowName ;

	private List<Object[]> dataList = new ArrayList<Object[]>();

	private HSSFWorkbook workbook =null;
	HttpServletResponse response;

	//构造方法，传入要导出的数据
	public ExportExcel(String title, String[] rowName, List<Object[]>  dataList){
		this.dataList = dataList;
		this.rowName = rowName;
		this.title = title;
	}
	/**
	 * 多出excel表格：列的颜色style
	 * */
	public Map<String,HSSFCellStyle> getColorList(){
		Map<String,HSSFCellStyle> colormap = new HashMap<String,HSSFCellStyle>(20);
		workbook = new HSSFWorkbook();
		HSSFCellStyle stylepink = this.getStyle(workbook,"pink");
		HSSFCellStyle stylered = this.getStyle(workbook,"red");
		HSSFCellStyle styleblue = this.getStyle(workbook,"blue");
		HSSFCellStyle styleyellow = this.getStyle(workbook,"yellow");
		HSSFCellStyle stylewhite = this.getStyle(workbook,"white");
		HSSFCellStyle styleorange = this.getStyle(workbook,"orange");
		HSSFCellStyle stylebrown = this.getStyle(workbook,"brown");
		HSSFCellStyle stylepurple = this.getStyle(workbook,"purple");
		//sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
		HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
		colormap.put("stylepink", stylepink);
		colormap.put("stylered", stylered);
		colormap.put("styleblue", styleblue);
		colormap.put("styleyellow", styleyellow);
		colormap.put("stylewhite", stylewhite);
		colormap.put("styleorange", styleorange);
		colormap.put("stylebrown", stylebrown);
		colormap.put("columnTopStyle", columnTopStyle);
		colormap.put("stylepurple", stylepurple);
		return colormap;
	}

	/*
	 * 导出数据
	 * */
	public void export(HttpServletResponse response){
		try{
			HSSFWorkbook workbook = makeHSSFWorkbook();
			//让列宽随着导出的列长自动适应
			/*for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if(colNum == 0){
                    sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
                }else{
                    sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
                }
            }*/

			if(workbook !=null){
				try{
					//String fileName =  title + ".xls";
					//fileName=URLEncoder.encode(fileName,"UTF-8");
					//fileName = new String(fileName.getBytes(), "ISO8859-1");
					String fileName = new String(title.getBytes(Charset.forName("GBK")),Charset.forName("ISO-8859-1"))+".xls";
					String headStr = "attachment; filename=\"" + fileName + "\"";
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition", headStr);
					OutputStream out = response.getOutputStream();
					workbook.write(out);
				}catch (IOException e){
					e.printStackTrace();
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	 /*
     * 导出数据
     * */
    public void exportBigData(HttpServletResponse response){
        try{
        	  XSSFWorkbook workbook = new XSSFWorkbook();
        	// 在内存中保持100行，超过100行将被刷新到磁盘
        	  SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook, 100);  
        	  Sheet sheet = sxssfWorkbook.createSheet(title);
        	 //设置列头样式
        	  CellStyle columnTopStyle = getBigDataColumnTopStyle(sxssfWorkbook);//列头单元格样式
        	 CellStyle style = this.getBigDataStyle(sxssfWorkbook); 
        	 
        	 // 定义所需列数
             int columnNum = rowName.length;
             Row rowRowName = sheet.createRow(0);
              
           // 将列头设置到sheet的单元格中
              for(int n=0;n<columnNum;n++){
                   Cell cellRowName = rowRowName.createCell(n);
                   //创建列头对应个数的单元格
                  cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
                  HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                  cellRowName.setCellValue(text);                                 //设置列头单元格的值
                 // cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样式

                  int length = (rowName[n].getBytes().length+6) * 256;
                  sheet.setColumnWidth(n, length);
              }
              
              
              //将查询出的数据设置到sheet对应的单元格中
              for(int i=0;i<dataList.size();i++){

                  Object[] obj = dataList.get(i);//遍历每个对象
                  Row row = sheet.createRow(i+1);//创建所需的行数

                  for(int j=0; j<obj.length; j++){
                      Cell  cell = null;   //设置单元格的数据类型
                          cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
                          if(!"".equals(obj[j]) && obj[j] != null){
                              cell.setCellValue(obj[j].toString());                       //设置单元格的值
                          }
                     // cell.setCellStyle(style);                                   //设置单元格样式
                  }
              }
              
              
              if(workbook !=null){
                  try{
                      String fileName =  title + ".xlsx";
                      String headStr = "attachment; filename=\""+
                      new String( fileName.getBytes( "gb2312" ), "ISO8859-1" ) + "\"";
                      response.setContentType("APPLICATION/OCTET-STREAM;charset=utf-8");
                      response.setHeader("Content-Disposition", headStr);
                      OutputStream out = response.getOutputStream();
                      sxssfWorkbook.write(out);
                  }catch (IOException e){
                      e.printStackTrace();
                  }

              }

          }catch(Exception e){
              e.printStackTrace();
          } finally {
  		}

    }

    
    /**解决百万级数据导出问题
     * 列头单元格样式
     * @param sxssfWorkbook
     * @return
     */
     private CellStyle getBigDataColumnTopStyle(SXSSFWorkbook sxssfWorkbook){
    	// 设置字体
    	  Font font = sxssfWorkbook.createFont();
    	 //设置字体大小
         font.setFontHeightInPoints((short)11);
         //字体加粗
         font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         //设置字体名字
         font.setFontName("宋体");
          
         //设置样式;
         CellStyle style = sxssfWorkbook.createCellStyle();
         //设置底边框;
         style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
         //设置底边框颜色;
         style.setBottomBorderColor(HSSFColor.BLACK.index);
         //设置左边框;
         style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         //设置左边框颜色;
         style.setLeftBorderColor(HSSFColor.BLACK.index);
         //设置右边框;
         style.setBorderRight(HSSFCellStyle.BORDER_THIN);
         //设置右边框颜色;
         style.setRightBorderColor(HSSFColor.BLACK.index);
         //设置顶边框;
         style.setBorderTop(HSSFCellStyle.BORDER_THIN);
         //设置顶边框颜色;
         style.setTopBorderColor(HSSFColor.BLACK.index);
         //在样式用应用设置的字体;
         style.setFont(font);
         //设置自动换行;
         style.setWrapText(false);
         //设置水平对齐的样式为居中对齐;
         style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         //设置垂直对齐的样式为居中对齐;
         style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
         //设置背景色
         style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

         return style;
     }
     
     public CellStyle getBigDataStyle(SXSSFWorkbook sxssfWorkbook) {
         // 设置字体
         Font font = sxssfWorkbook.createFont();
         //设置字体大小
         //font.setFontHeightInPoints((short)10);
         //字体加粗
         //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         //设置字体名字
         font.setFontName("宋体");
         //设置样式;
         CellStyle style = sxssfWorkbook.createCellStyle();
         //设置底边框;
         style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
         //设置底边框颜色;
         style.setBottomBorderColor(HSSFColor.BLACK.index);
         //设置左边框;
         style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         //设置左边框颜色;
         style.setLeftBorderColor(HSSFColor.BLACK.index);
         //设置右边框;
         style.setBorderRight(HSSFCellStyle.BORDER_THIN);
         //设置右边框颜色;
         style.setRightBorderColor(HSSFColor.BLACK.index);
         //设置顶边框;
         style.setBorderTop(HSSFCellStyle.BORDER_THIN);
         //设置顶边框颜色;
         style.setTopBorderColor(HSSFColor.BLACK.index);
         //在样式用应用设置的字体;
         style.setFont(font);
         //设置自动换行;
         style.setWrapText(false);
         //设置水平对齐的样式为居中对齐;
         style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         //设置垂直对齐的样式为居中对齐;
         style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

         return style;

     }
     
  
     
     
	/*
	 * 导出数据到缓存文件
	 * */
	public void exportToCache(HttpServletResponse response, String filePath) throws IOException{
		try{
			HSSFWorkbook workbook = makeHSSFWorkbook();
			if(workbook !=null){
				try{
					File files = new File(filePath);
					if(!files.exists()){
						files.mkdirs();
					}
					String fileName = new String(title.getBytes(Charset.forName("GBK")),Charset.forName("ISO-8859-1"))+".xls";
					String headStr = "attachment; filename=\"" + fileName + "\"";
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition", headStr);
					FileOutputStream out = new FileOutputStream(filePath + "/" + title + ".xls");
					workbook.write(out);
					out.flush();
					out.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	/*
	 * 导出数据到缓存文件
	 * */
	public void exportToCache(HttpServletResponse response, String filePath, Integer type, Integer deptLength, Integer size2) throws IOException{
		try{
			HSSFWorkbook workbook = makeHSSFWorkbook(type,deptLength,size2);
			if(workbook !=null){
				try{
					File files = new File(filePath);
					if(!files.exists()){
						files.mkdirs();
					}
					String fileName = new String(title.getBytes(Charset.forName("GBK")),Charset.forName("ISO-8859-1"))+".xls";
					String headStr = "attachment; filename=\"" + fileName + "\"";
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition", headStr);
					FileOutputStream out = new FileOutputStream(filePath + "/" + title + ".xls");
					workbook.write(out);
					out.flush();
					out.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	/**
	 * Description:封装excel工作薄
	 * Class:ExportExcel
	 * Name:makeHSSFWorkbook
	 * Date:2018年2月7日上午11:08:04
	 * Return:HSSFWorkbook
	 * Author:ygl
	 */
	private HSSFWorkbook makeHSSFWorkbook() {
		String sheetTitle = title;
		int cnt = countInnerStr(title,"-");
		if (StringUtil.isNotBlank(title)) {
			switch (cnt) {
				case 0:
					if (title.length() > 4) {
						sheetTitle = title.substring(0, title.length() - 4);
					}
					break;
				case 1:
					if (title.length() > 7) {
						sheetTitle = title.substring(0, title.length() - 7);
					}
					break;
				case 2:
					if (title.length() > 10) {
						sheetTitle = title.substring(0, title.length() - 10);
					}
					break;
			}
		} else {
			sheetTitle = "tmpSheet";
		}
		/*String sheetTitle = "";
		if(StringUtil.isNotBlank(title) && title.length() >= 10 && 0 < title.indexOf("-")){
			sheetTitle = title.substring(0, title.length() - 10);
		}*/
		HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象

		//sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
		HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
		HSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象

		// 定义所需列数
		int columnNum = rowName.length;
		HSSFSheet sheet = null;

		//将查询出的数据设置到sheet对应的单元格中
		int rowCount = -50000;
		for(int i=0;i<dataList.size();i++){
			if(i%50000 == 0){
				rowCount += 50000;
				sheet = workbook.createSheet(50000 < dataList.size() ? sheetTitle+"(第"+(i+1)+"~"+(i/50000+1)*50000+"条)" : sheetTitle);                  // 创建工作表
				sheet.createFreezePane(0, 1);//冻结窗格
				HSSFRow rowRowName = sheet.createRow(0); //创建表头行

				// 将列头设置到sheet的单元格中
				for(int n=0;n<columnNum;n++){
					HSSFCell  cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格
					cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
					HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
					cellRowName.setCellValue(text);                                 //设置列头单元格的值
					cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样式

					int length = (rowName[n].getBytes().length+6) * 256;
					sheet.setColumnWidth(n, length);
				}
			}
			Object[] obj = dataList.get(i);//遍历每个对象

			HSSFRow row = sheet.createRow(i+1-rowCount);//创建所需的行数

			for(int j=0; j<obj.length; j++){
				HSSFCell  cell = null;   //设置单元格的数据类型
				if (obj[j] instanceof BigDecimal) {//金额（decimal对应BigDecimal）
					cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
					if(!"".equals(obj[j]) && obj[j] != null){
						double doubleVal = ((BigDecimal) obj[j]).doubleValue();
						DataFormat format = workbook.createDataFormat();
						style.setDataFormat(format.getFormat("#,##0.00"));//保留两位小数点
						cell.setCellValue(doubleVal);                       //设置单元格的值
					}
				} else {
					cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
					if(!"".equals(obj[j]) && obj[j] != null){
						cell.setCellValue(obj[j].toString());                       //设置单元格的值
					}
				}

				/*if(j == 0){
		            cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
		            cell.setCellValue(i+1);
		        }else{*/
				/*cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
				if(!"".equals(obj[j]) && obj[j] != null){
					cell.setCellValue(obj[j].toString());                       //设置单元格的值
				}*/
				//                    }
				cell.setCellStyle(style);                                   //设置单元格样式
			}

		}
		return workbook;
	}
	/**
	 * Description:封装excel工作薄
	 * Class:ExportExcel
	 * Name:makeHSSFWorkbook
	 * Date:2018年7月20日上午10:018:21
	 * Return:HSSFWorkbook
	 * Author:sxm
	 */
	public HSSFWorkbook makeHSSFWorkbook(Integer type,Integer deptLength,Integer size2 ) {
		String sheetTitle = title;
		int cnt = countInnerStr(title,"-");
		if (StringUtil.isNotBlank(title)) {
			switch (cnt) {
				case 0:
					if (title.length() > 4) {
						sheetTitle = title.substring(0, title.length() - 4);
					}
					break;
				case 1:
					if (title.length() > 7) {
						sheetTitle = title.substring(0, title.length() - 7);
					}
					break;
				case 2:
					if (title.length() > 10) {
						sheetTitle = title.substring(0, title.length() - 10);
					}
					break;
			}
		} else {
			sheetTitle = "tmpSheet";
		}
		/* String sheetTitle = "";
			if(StringUtil.isNotBlank(title) && title.length() >= 10 && 0 < title.indexOf("-")){
			sheetTitle = title.substring(0, title.length() - 10);
		}*/
		// 定义所需列数
		int columnNum = rowName.length;
		HSSFSheet sheet = null;
		workbook = new HSSFWorkbook();
		//将查询出的数据设置到sheet对应的单元格中
		int rowCount = -50000;
		Map<String,HSSFCellStyle> colormap =getColorList();
		for(int i=0;i<dataList.size();i++){
			if(i%50000 == 0){
				rowCount += 50000;
				sheet = workbook.createSheet(50000 < dataList.size() ? sheetTitle+"(第"+(i+1)+"~"+(i/50000+1)*50000+"条)" : sheetTitle);                  // 创建工作表
				sheet.createFreezePane(0, 1);//冻结窗格
				HSSFRow rowRowName = sheet.createRow(0); //创建表头行

				// 将列头设置到sheet的单元格中
				for(int n=0;n<columnNum;n++){
					HSSFCell  cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格
					cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
					HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
					cellRowName.setCellValue(text);                                 //设置列头单元格的值
					cellRowName.setCellStyle(colormap.get("columnTopStyle"));                       //设置列头单元格样式
					int length = (rowName[n].getBytes().length+6) * 256;
					sheet.setColumnWidth(n, length);
				}
			}
			Object[] obj = dataList.get(i);//遍历每个对象
			HSSFRow row = sheet.createRow(i+1-rowCount);//创建所需的行数
			for(int j=0; j<obj.length; j++){
				HSSFCell  cell = null;
				if (obj[j] instanceof BigDecimal) {//金额（decimal对应BigDecimal）
					cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
					if(!"".equals(obj[j]) && obj[j] != null){
						double doubleVal = ((BigDecimal) obj[j]).doubleValue();
						DataFormat format = workbook.createDataFormat();
						colormap.get("stylepink").setDataFormat(format.getFormat("#,##0.00"));//保留两位小数点
						colormap.get("stylered").setDataFormat(format.getFormat("#,##0.00"));
						colormap.get("styleblue").setDataFormat(format.getFormat("#,##0.00"));
						colormap.get("styleyellow").setDataFormat(format.getFormat("#,##0.00"));
						colormap.get("stylewhite").setDataFormat(format.getFormat("#,##0.00"));
						colormap.get("styleorange").setDataFormat(format.getFormat("#,##0.00"));
						colormap.get("stylebrown").setDataFormat(format.getFormat("#,##0.00"));
						cell.setCellValue(doubleVal);                       //设置单元格的值
					}
				} else {
					cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
					if(!"".equals(obj[j]) && obj[j] != null){
						cell.setCellValue(obj[j].toString());                       //设置单元格的值
					}
				}
				if(0 == type){//--------------------------团队月度,导出查询列表-------------------------------------
					ExportExcelMap getexportexcelmap = ExportExcelMap.getexportexcelmap();
					Map<String, Integer> styleMapTeamMonthSelectList = getexportexcelmap.getStyleMapTeamMonthSelectList();
					int base = styleMapTeamMonthSelectList.get("base");
					if(deptLength==1){
						base = styleMapTeamMonthSelectList.get("base_deptLength_1");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthSelectList.get("task"),
								styleMapTeamMonthSelectList.get("gushou_c"), styleMapTeamMonthSelectList.get("weixin_c"),
								styleMapTeamMonthSelectList.get("yunbaobei_c"), styleMapTeamMonthSelectList.get("jijin_c"), null,styleMapTeamMonthSelectList.get("hyk_c"),styleMapTeamMonthSelectList.get("jjs_c"),null,styleMapTeamMonthSelectList.get("yxhh_c"));
					}
					else if(deptLength==2){
						base = styleMapTeamMonthSelectList.get("base_deptLength_2");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthSelectList.get("task"),
								styleMapTeamMonthSelectList.get("gushou_c"), styleMapTeamMonthSelectList.get("weixin_c"),
								styleMapTeamMonthSelectList.get("yunbaobei_c"), styleMapTeamMonthSelectList.get("jijin_c"), null,styleMapTeamMonthSelectList.get("hyk_c"),styleMapTeamMonthSelectList.get("jjs_c"),null,styleMapTeamMonthSelectList.get("yxhh_c"));
					}
					else if(deptLength==3){
						base = styleMapTeamMonthSelectList.get("base_deptLength_3");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthSelectList.get("task"),
								styleMapTeamMonthSelectList.get("gushou_c"), styleMapTeamMonthSelectList.get("weixin_c"),
								styleMapTeamMonthSelectList.get("yunbaobei_c"), styleMapTeamMonthSelectList.get("jijin_c"), null,styleMapTeamMonthSelectList.get("hyk_c"),styleMapTeamMonthSelectList.get("jjs_c"),null,styleMapTeamMonthSelectList.get("yxhh_c"));
					}
					else if(deptLength==4){
						base = styleMapTeamMonthSelectList.get("base_deptLength_4");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthSelectList.get("task"),
								styleMapTeamMonthSelectList.get("gushou_c"), styleMapTeamMonthSelectList.get("weixin_c"),
								styleMapTeamMonthSelectList.get("yunbaobei_c"), styleMapTeamMonthSelectList.get("jijin_c"), null,styleMapTeamMonthSelectList.get("hyk_c"),styleMapTeamMonthSelectList.get("jjs_c"),null,styleMapTeamMonthSelectList.get("yxhh_c"));
					}
					else if(deptLength==5){
						base = styleMapTeamMonthSelectList.get("base_deptLength_5");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthSelectList.get("task"),
								styleMapTeamMonthSelectList.get("gushou"), styleMapTeamMonthSelectList.get("weixin"),
								styleMapTeamMonthSelectList.get("yunbaobei"), styleMapTeamMonthSelectList.get("jijin"), null,styleMapTeamMonthSelectList.get("hyk"),styleMapTeamMonthSelectList.get("jjs"),null,styleMapTeamMonthSelectList.get("yxhh"));
					}
					else if(deptLength==6){
						base = styleMapTeamMonthSelectList.get("base_deptLength_6");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthSelectList.get("task"),
								styleMapTeamMonthSelectList.get("gushou"), styleMapTeamMonthSelectList.get("weixin"),
								styleMapTeamMonthSelectList.get("yunbaobei"), styleMapTeamMonthSelectList.get("jijin"), null,styleMapTeamMonthSelectList.get("hyk"),styleMapTeamMonthSelectList.get("jjs"),null,styleMapTeamMonthSelectList.get("yxhh"));
					}

				}else if(1 == type){//--------------------------团队月度，导出报表-------------------------------------
					ExportExcelMap getexportexcelmap = ExportExcelMap.getexportexcelmap();
					Map<String, Integer> styleMapTeamMonthstatement = getexportexcelmap.getStyleMapTeamMonthstatement();

					int base = styleMapTeamMonthstatement.get("base");
					int other = 0;
					if(deptLength==1){
						base = styleMapTeamMonthstatement.get("base_deptLength_1");
						other = styleMapTeamMonthstatement.get("other_deptLength_1");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthstatement.get("task"),
								styleMapTeamMonthstatement.get("gushou_c"), styleMapTeamMonthstatement.get("weixin_c"),
								styleMapTeamMonthstatement.get("yunbaobei_c"), styleMapTeamMonthstatement.get("jijin_c"), other,styleMapTeamMonthstatement.get("hyk_c"),styleMapTeamMonthstatement.get("jjs_c"),styleMapTeamMonthstatement.get("XyXt"),styleMapTeamMonthstatement.get("yxhh_c"));
					}
					else if(deptLength==2){
						base = styleMapTeamMonthstatement.get("base_deptLength_2");
						other = styleMapTeamMonthstatement.get("other_deptLength_2");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthstatement.get("task"),
								styleMapTeamMonthstatement.get("gushou_c"), styleMapTeamMonthstatement.get("weixin_c"),
								styleMapTeamMonthstatement.get("yunbaobei_c"), styleMapTeamMonthstatement.get("jijin_c"), other,styleMapTeamMonthstatement.get("hyk_c"),styleMapTeamMonthstatement.get("jjs_c"),styleMapTeamMonthstatement.get("XyXt"),styleMapTeamMonthstatement.get("yxhh_c"));
					}
					else if(deptLength==3){
						base = styleMapTeamMonthstatement.get("base_deptLength_3");
						other = styleMapTeamMonthstatement.get("other_deptLength_3");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthstatement.get("task"),
								styleMapTeamMonthstatement.get("gushou_c"), styleMapTeamMonthstatement.get("weixin_c"),
								styleMapTeamMonthstatement.get("yunbaobei_c"), styleMapTeamMonthstatement.get("jijin_c"), other,styleMapTeamMonthstatement.get("hyk_c"),styleMapTeamMonthstatement.get("jjs_c"),styleMapTeamMonthstatement.get("XyXt"),styleMapTeamMonthstatement.get("yxhh_c"));
					}
					else if(deptLength==4){
						base = styleMapTeamMonthstatement.get("base_deptLength_4");
						other = styleMapTeamMonthstatement.get("other_deptLength_4");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthstatement.get("task"),
								styleMapTeamMonthstatement.get("gushou_c"), styleMapTeamMonthstatement.get("weixin_c"),
								styleMapTeamMonthstatement.get("yunbaobei_c"), styleMapTeamMonthstatement.get("jijin_c"), other,styleMapTeamMonthstatement.get("hyk_c"),styleMapTeamMonthstatement.get("jjs_c"),styleMapTeamMonthstatement.get("XyXt"),styleMapTeamMonthstatement.get("yxhh_c"));
					}
					else if(deptLength==5){
						base = styleMapTeamMonthstatement.get("base_deptLength_5");
						other = styleMapTeamMonthstatement.get("other_deptLength_5");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthstatement.get("task"),
								styleMapTeamMonthstatement.get("gushou"), styleMapTeamMonthstatement.get("weixin"),
								styleMapTeamMonthstatement.get("yunbaobei"), styleMapTeamMonthstatement.get("jijin"), other,styleMapTeamMonthstatement.get("hyk"),styleMapTeamMonthstatement.get("jjs"),styleMapTeamMonthstatement.get("XyXt"),styleMapTeamMonthstatement.get("yxhh"));
					}
					else if(deptLength==6){
						base = styleMapTeamMonthstatement.get("base_deptLength_6");
						other = styleMapTeamMonthstatement.get("other_deptLength_6");
						loopAddStyle(j, base, cell, colormap, styleMapTeamMonthstatement.get("task"),
								styleMapTeamMonthstatement.get("gushou"), styleMapTeamMonthstatement.get("weixin"),
								styleMapTeamMonthstatement.get("yunbaobei"), styleMapTeamMonthstatement.get("jijin"), other,styleMapTeamMonthstatement.get("hyk"),styleMapTeamMonthstatement.get("jjs"),styleMapTeamMonthstatement.get("XyXt"),styleMapTeamMonthstatement.get("yxhh"));
					}

				}else if(4 == type){//--------------------------年度,导出查询列表-------------------------------------
					ExportExcelMap getexportexcelmap = ExportExcelMap.getexportexcelmap();
					Map<String, Integer> styleMapTeamYearSelectList = getexportexcelmap.getStyleMapTeamYearSelectList();
					loopAddStyle(j, styleMapTeamYearSelectList.get("base_deptLength_null"),
							cell, colormap, styleMapTeamYearSelectList.get("task"), styleMapTeamYearSelectList.get("gushou"),
							styleMapTeamYearSelectList.get("weixin"),styleMapTeamYearSelectList.get("yunbaobei"), styleMapTeamYearSelectList.get("jijin"), null,styleMapTeamYearSelectList.get("hyk"),styleMapTeamYearSelectList.get("jjs"),null,styleMapTeamYearSelectList.get("yxhh"));
				}else if(5 == type){//--------------------------年度,导出报表------------------------------
					ExportExcelMap getexportexcelmap = ExportExcelMap.getexportexcelmap();
					Map<String, Integer> styleMapTeamYearstatement = getexportexcelmap.getStyleMapTeamYearstatement();
					int base = styleMapTeamYearstatement.get("base");
					if(deptLength==1999){
						base = styleMapTeamYearstatement.get("base_deptLength_1");
					}
					else if(deptLength==1998){
						base = styleMapTeamYearstatement.get("base_deptLength_2");
					}
					else if(deptLength==1997){
						base = styleMapTeamYearstatement.get("base_deptLength_3");
					}
					else if(deptLength==1996){
						base = styleMapTeamYearstatement.get("base_deptLength_4");
					}
					loopAddStyle(j, base, cell, colormap,  styleMapTeamYearstatement.get("task"),
							styleMapTeamYearstatement.get("gushou"), styleMapTeamYearstatement.get("weixin"),
							styleMapTeamYearstatement.get("yunbaobei"),styleMapTeamYearstatement.get("jijin"), null,styleMapTeamYearstatement.get("hyk"),styleMapTeamYearstatement.get("jjs"),null,styleMapTeamYearstatement.get("yxhh"));
				} else if(2 == type){//--------------------------个人,导出查询列表------------------------------
					ExportExcelMap getexportexcelmap = ExportExcelMap.getexportexcelmap();
					Map<String, Integer> styleMapTeamPersonlist = getexportexcelmap.getStyleMapTeamPersonlist();
					loopAddStyle(j, styleMapTeamPersonlist.get("base"), cell, colormap, styleMapTeamPersonlist.get("task"),
							styleMapTeamPersonlist.get("gushou"), styleMapTeamPersonlist.get("weixin"),
							styleMapTeamPersonlist.get("yunbaobei"), styleMapTeamPersonlist.get("jijin"), null,styleMapTeamPersonlist.get("hyk"),styleMapTeamPersonlist.get("jjs"),null,styleMapTeamPersonlist.get("yxhh"));
				}else if(3 == type){//--------------------------个人,导出报表------------------------------
					ExportExcelMap getexportexcelmap = ExportExcelMap.getexportexcelmap();
					Map<String, Integer> styleMapTeamPersonStatement = getexportexcelmap.getStyleMapTeamPersonStatement();
					loopAddStyle(j, styleMapTeamPersonStatement.get("base"), cell, colormap, styleMapTeamPersonStatement.get("task"),
							styleMapTeamPersonStatement.get("gushou")+size2, styleMapTeamPersonStatement.get("weixin"),
							styleMapTeamPersonStatement.get("yunbaobei"), styleMapTeamPersonStatement.get("jijin"), null,styleMapTeamPersonStatement.get("hyk"),styleMapTeamPersonStatement.get("jjs"),null,styleMapTeamPersonStatement.get("yxhh"));
				}else if(6 == type){//团队日报表
					ExportExcelMap getexportexcelmap = ExportExcelMap.getexportexcelmap();
					Map<String, Integer> styleMapTeamYearstatement = getexportexcelmap.teamDayMap();
					int base = styleMapTeamYearstatement.get("base");
					if(deptLength==1){
						base = styleMapTeamYearstatement.get("base_deptLength_1");
					}
					else if(deptLength==2){
						base = styleMapTeamYearstatement.get("base_deptLength_2");
					}
					else if(deptLength==3){
						base = styleMapTeamYearstatement.get("base_deptLength_3");
					}
					else if(deptLength==4){
						base = styleMapTeamYearstatement.get("base_deptLength_4");
					}
					else if(deptLength==5){
						base = styleMapTeamYearstatement.get("base_deptLength_5");
					}
					else if(deptLength==6){
						base = styleMapTeamYearstatement.get("base_deptLength_6");
					}
					loopAddStyle(j, base, cell, colormap,  styleMapTeamYearstatement.get("task"),
							styleMapTeamYearstatement.get("gushou"), styleMapTeamYearstatement.get("weixin"),
							styleMapTeamYearstatement.get("yunbaobei"),styleMapTeamYearstatement.get("jijin"), null,styleMapTeamYearstatement.get("hyk"),styleMapTeamYearstatement.get("jjs"),null,styleMapTeamYearstatement.get("yxhh"));
				}else if(7 == type){//个人日报表
					ExportExcelMap getexportexcelmap = ExportExcelMap.getexportexcelmap();
					Map<String, Integer> styleMapTeamPersonStatement = getexportexcelmap.personDayMap();
					loopAddStyle(j, styleMapTeamPersonStatement.get("base"), cell, colormap, styleMapTeamPersonStatement.get("task"),
							styleMapTeamPersonStatement.get("gushou")+size2, styleMapTeamPersonStatement.get("weixin"),
							styleMapTeamPersonStatement.get("yunbaobei"), styleMapTeamPersonStatement.get("jijin"), null,styleMapTeamPersonStatement.get("hyk"),styleMapTeamPersonStatement.get("jjs"),null,styleMapTeamPersonStatement.get("yxhh"));
				}

			}

		}
		return workbook;
	}

	/*
	 * 列头单元格样式
	 */
	public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		//设置字体大小
		font.setFontHeightInPoints((short)11);
		//字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//设置字体名字
		font.setFontName("宋体");
		//设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		//设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		//设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		//设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		//设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		//设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		//在样式用应用设置的字体;
		style.setFont(font);
		//设置自动换行;
		style.setWrapText(false);
		//设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

		return style;

	}
	/*
	 * 列数据信息单元格样式（颜色动态）
	 */
	public HSSFCellStyle getStyle(HSSFWorkbook workbook,String color) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		//设置字体大小
		//font.setFontHeightInPoints((short)10);
		//字体加粗
		//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//设置字体名字
		font.setFontName("宋体");
		//设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		//设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		//设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		//设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		//设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		//设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		//在样式用应用设置的字体;
		style.setFont(font);
		//设置自动换行;
		style.setWrapText(false);
		//设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		if("red".equals(color)){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		}
		else if("blue".equals(color)){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		}
		else if("pink".equals(color)){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
		}
		else if("yellow".equals(color)){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		}
		else if("white".equals(color)){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.LIME.index);
		}
		else if("orange".equals(color)){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.TAN.index);
		}
		else if("brown".equals(color)){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		}
		else if("purple".equals(color)){
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.CORAL.index);
		}


		return style;

	}
	/*
	 * 列数据信息单元格样式
	 */
	public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		//设置字体大小
		//font.setFontHeightInPoints((short)10);
		//字体加粗
		//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//设置字体名字
		font.setFontName("宋体");
		//设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		//设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		//设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		//设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		//设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		//设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		//在样式用应用设置的字体;
		style.setFont(font);
		//设置自动换行;
		style.setWrapText(false);
		//设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;

	}

	public static void main(String[] args) throws Exception {
		String title = "导出";
		String[] rowsName = new String[]{"序号","货物运输批次号","提运单号","状态","录入人","录入时间","其他"};
		List<Object[]>  dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (int i = 0; i < 10; i++) {
			objs = new Object[rowsName.length];
			objs[0] = i;
			objs[1] = "";
			objs[2] = "abcd";
			objs[3] = "正常";
			objs[4] = "liang";
			objs[6] = "123";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			objs[5] = date;
			dataList.add(objs);
		}
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);
		ex.export(null);
	}

	/**
	 * 循环列，添加样式
	 * */
	public void loopAddStyle(int j,int base,HSSFCell  cell,Map<String,HSSFCellStyle> colormap,int task,int gushou,int weixin,int yunbaobei,int jijin,Integer other,int hyk,int jjs,Integer xyxt,int yxhh){
		if(j>=0 && j<=base-1 ){
			cell.setCellStyle(colormap.get("stylered"));
		}else if(j>=base-1 && j<=base-1+task){
			cell.setCellStyle(colormap.get("styleblue"));
		}else if(j>=base-1+task && j<=base-1+task+gushou){
			cell.setCellStyle(colormap.get("stylepink"));
		}else if(j>=base-1+task+gushou && j<=base-1+task+gushou+weixin){
			cell.setCellStyle(colormap.get("stylewhite"));
		}else if(j>=base-1+task+gushou+weixin && j<=base-1+task+gushou+weixin+yunbaobei){
			cell.setCellStyle(colormap.get("styleorange"));
		}else if(j>=base-1+task+gushou+weixin+yunbaobei && j<=base-1+task+gushou+weixin+yunbaobei+jijin){
			cell.setCellStyle(colormap.get("stylebrown"));
		}else if(j>=base-1+task+gushou+weixin+yunbaobei+jijin && j<base-1+task+gushou+weixin+yunbaobei+jijin+hyk){
			cell.setCellStyle(colormap.get("stylered"));
		}else if(j>=base-1+task+gushou+weixin+yunbaobei+jijin+hyk && j<base-1+task+gushou+weixin+yunbaobei+jijin+hyk+jjs){
			cell.setCellStyle(colormap.get("stylepurple"));
		}else if(j>=base-1+task+gushou+weixin+yunbaobei+jijin+hyk+jjs && j<base-1+task+gushou+weixin+yunbaobei+jijin+hyk+jjs+yxhh){
			cell.setCellStyle(colormap.get("stylered"));
		}
		if(other != null){
			if(j>=base-1+task+gushou+weixin+yunbaobei+jijin+hyk+jjs+yxhh && j<base-1+task+gushou+weixin+yunbaobei+jijin+hyk+jjs+yxhh+other){
				cell.setCellStyle(colormap.get("styleyellow"));
			}
		}
		if(xyxt != null){
			if(j>=base-1+task+gushou+weixin+yunbaobei+jijin+hyk+jjs+yxhh+other && j<base-1+task+gushou+weixin+yunbaobei+jijin+hyk+jjs+yxhh+other+xyxt){
				cell.setCellStyle(colormap.get("stylepink"));
			}
		}

	}
	//查找字符串里与指定字符串相同的个数
	private int countInnerStr(final String str,final String strRes) {
		int count = 0;
		String tmpStr = str;
		while(tmpStr.indexOf(strRes)!=-1) {
			int i = tmpStr.indexOf(strRes);
			count++;
			tmpStr = tmpStr.substring(i+1);
		}
		return count;
	}


}
