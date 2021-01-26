package com.hzcf.plantform.controller.Insurance;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.exception.CheckException;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.feign.insurance.SalesTeamInfoClient;
import com.hzcf.plantform.feign.insurancePolicy.InsPolicyInsuredPersonFeign;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.DateTimeUtil;
import com.hzcf.plantform.util.ImportUtil;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.SalesTeamInfo;
import com.hzcf.util.DateUtil;
import com.hzcf.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 销售组织管理
 * @author yl
 *
 */
@Controller
@RequestMapping("/salesTeamInfo")
public class SalesTeamInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesTeamInfoClient salesTeamInfoClient;
    
    @Autowired
    private SalesOrgInfoClient salesOrgInfoClient;
	 @Autowired
    private InsPolicyInsuredPersonFeign insPolicyInsuredPersonFeign;
	@Autowired
	EmployeeFeignClient employeeFeignClient;

    /**
     * 跳转到列表页面
     *
     * */
	@RequestMapping("/toListPath")
    public String toListPath(){return "salesTeamInfoPage/salesTeamInfoList";}  

    /**
     * 跳转到新增页面
     *
     * */
	@RequestMapping("/toAddPath")
    public String toAddPath(Model model,HttpServletRequest request){
        String salesOrgId = request.getParameter("id");
        Subject subject = SecurityUtils.getSubject();
		Employee employee = (Employee) subject.getPrincipal();
		model.addAttribute("userName", employee.getEmployeeNo());
		if (StringUtil.isNotBlanks(salesOrgId)){
		   model.addAttribute("salesOrgId", salesOrgId);
        }
		return "salesTeamInfoPage/salesTeamInfoAdd";
	}
	
    /**
     * 跳转到新增页面
     *
     * */
	@RequestMapping("/toLeadersPath")
    public String toLeadersPath(Model model,HttpServletRequest request){
		String salesTeamId = request.getParameter("id");
		Map<String,Object> paras = new HashMap<>();
		paras.put("salesTeamId",salesTeamId);
		SalesTeamInfo salesTeamInfo = salesTeamInfoClient.selectById(paras);
		model.addAttribute("salesTeamInfo",salesTeamInfo);
		return "salesTeamInfoPage/salesTeamLeadersList";		
	}
	
    /**
     * 跳转到新增页面
     *
     * */
	@RequestMapping("/toUpdatePath")
    public String toUpdatePath(Model model,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		Employee employee = (Employee) subject.getPrincipal();
		model.addAttribute("userName", employee.getEmployeeNo());

		String salesTeamId = request.getParameter("id");
		Map<String,Object> paras = new HashMap<>();
		paras.put("salesTeamId",salesTeamId);
		SalesTeamInfo salesTeamInfo = salesTeamInfoClient.selectById(paras);
		paras.clear();
		paras.put("salesOrgId",salesTeamInfo.getSalesOrgId());
		SalesOrgInfo salesOrgInfo = salesOrgInfoClient.selectById(paras);
		SalesTeamInfo psalesTeamInfo = null;
		if(StringUtil.isNotBlank(salesTeamInfo.getParentSalesTeamCode())){
			paras.clear();
			paras.put("treeCode", salesTeamInfo.getTreeCode().substring(0,salesTeamInfo.getTreeCode().length()-4));
			List<SalesTeamInfo> plist = salesTeamInfoClient.getParents(paras);
			if(plist.size()>0){
				psalesTeamInfo = plist.get(0);
			}
		}		
		model.addAttribute("salesTeamInfo",salesTeamInfo);
		model.addAttribute("salesOrgInfo",salesOrgInfo);
		model.addAttribute("psalesTeamInfo",psalesTeamInfo);
		return "salesTeamInfoPage/salesTeamInfoUpdate";
	}  

    /**
     *增加部门
     *
     * */
    @RequiresPermissions("salesTeamInfo:add")//权限管理;
    @RequestMapping("addSalesTeamInfo")
    @ResponseBody
    public DataMsg addSalesTeamInfo(HttpServletRequest request,SalesTeamInfo salesTeamInfo,DataMsg msg){
	    try{
	    	Map<String,Object> paras = new HashMap<>();
	    	String salesTeamName = salesTeamInfo.getSalesTeamName();
	    	paras.put("salesTeamName",salesTeamName);
	    	paras.put("salesOrgId",salesTeamInfo.getSalesOrgId());
	    	List<SalesTeamInfo> haveTeamName = salesTeamInfoClient.selectByCondition(paras);
	    	if(haveTeamName!=null && haveTeamName.size()>0){
	    		msg.setMessageCode("999");
	    		msg.setRequestState("团队名称在所选组织机构下已存在，请修改！");
	    		return msg;
	    	}
	    	paras.clear();
	    	String salesTeamCode = salesTeamInfo.getSalesTeamCode();
	    	paras.put("salesTeamCode",salesTeamCode);
	    	List<SalesTeamInfo> haveNum = salesTeamInfoClient.getParents(paras);
	    	if(haveNum!=null && haveNum.size()>0){
	    		msg.setMessageCode("999");
	    		msg.setRequestState("团队编号已存在，请修改！");
	    		return msg;
	    	}
	    	paras.clear();
	    	String PTcode = request.getParameter("PTcode");
	    	if(!StringUtil.isNotBlank(PTcode)){PTcode="";}
    		int treeCodeLen = 0;
    		treeCodeLen = PTcode.length()+4;
    		paras.put("treeCodeLen",treeCodeLen);
    		paras.put("PTcode",PTcode);
    		int preTreeCode = salesTeamInfoClient.findMaxTreeCode(paras);
    		if(preTreeCode==0){
    			salesTeamInfo.setTreeCode(PTcode+"1000");
    		}else{
    			salesTeamInfo.setTreeCode(PTcode+(preTreeCode+1)+"");
    		}
			salesTeamInfo.setCreatedTime(new Date());
    		salesTeamInfoClient.addSalesTeamInfo(salesTeamInfo);
	        msg.setMessageCode("200");
	    }catch (Exception e){
	        msg.setMessageCode("400");
	        logger.info("保险组织架构[新增]异常",e);
	        msg.setRequestState("保险组织架构[新增]异常!");
	    }
		return msg;
	}
    
    
    /**
     *增加部门
     *
     * */
    @RequiresPermissions("salesTeamInfo:update")//权限管理;
    @RequestMapping("updateSalesTeamInfo")
    @ResponseBody
    public DataMsg updateSalesTeamInfo(HttpServletRequest request,SalesTeamInfo salesTeamInfo,DataMsg msg){
        try{
        	Map<String, Object> paras = new HashMap<>();
        	paras.put("salesTeamId", salesTeamInfo.getSalesTeamId());
        	SalesTeamInfo st = salesTeamInfoClient.selectById(paras);
        	Map<String,Object> map = new HashMap<>();
        	if(!st.getSalesTeamCode().equals(salesTeamInfo.getSalesTeamCode())){
				String salesTeamCode = salesTeamInfo.getSalesTeamCode();
				map.put("salesTeamCode",salesTeamCode);
				List<SalesTeamInfo> haveNum = salesTeamInfoClient.getParents(map);
				if(haveNum!=null && haveNum.size()>0){
					msg.setMessageCode("999");
					msg.setRequestState("团队编号已存在，请修改！");
					return msg;
				}
	    			map.clear();
			}
			if (!st.getSalesTeamName().equals(salesTeamInfo.getSalesTeamName())){
        		String salesTeamName = salesTeamInfo.getSalesTeamName();
				map.put("salesTeamName",salesTeamName);
				map.put("salesOrgId",salesTeamInfo.getSalesOrgId());
				List<SalesTeamInfo> haveTeamName = salesTeamInfoClient.selectByCondition(map);
				if(haveTeamName!=null && haveTeamName.size()>0){
					msg.setMessageCode("999");
					msg.setRequestState("团队名称在所选组织机构下已存在，请修改！");
					return msg;
				}
	    			map.clear();
			}
			if(salesTeamInfo.getSalesTeamCode().equals(salesTeamInfo.getParentSalesTeamCode())){
				msg.setMessageCode("999");
				msg.setRequestState("上级销售团队不能选择销售团队本身");
				return msg;
			}
        	salesTeamInfoClient.updateSalesTeamInfo(salesTeamInfo);
            msg.setMessageCode("200");
        }catch (Exception e){
            msg.setMessageCode("400");
            logger.info("保险组织架构[修改]异常",e);
        }
        return msg;
    }
    
    
    /**
    *
    *查询部门基础信息List
    * */
	@RequestMapping("/salesTeamInfoList")
	@ResponseBody
   public DataMsg salesTeamInfoList(HttpServletRequest request,DataMsg dataMsg){

	   try {
		       Map<String,Object> paras = new HashMap<>();
		       String pageNo = request.getParameter("pageNo");
		       if (StringUtil.isNotBlank(pageNo)) {
		           paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
		       }else{
		    	   paras.put("pageNo",1);
		       }
		       String pageSize = request.getParameter("pageSize");
		       if (StringUtil.isNotBlank(pageSize)) {
		           paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
		       }else{
		    	   paras.put("pageSize",10);
		       }
		       String salesOrgId = request.getParameter("salesOrgId");
               if (StringUtil.isNotBlank(salesOrgId)) {

                   String salesAllOrgs = salesOrgInfoClient.findEmployeeAllOrgs(salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(salesOrgId).get(0).get("SALES_ORG_CODE").toString());
                   paras.put("salesAllOrgs", salesAllOrgs);
               }
               String salesTeamName = request.getParameter("salesTeamName");
		       if (StringUtil.isNotBlank(salesTeamName)) {
		           paras.put("salesTeamName",salesTeamName);
		       }
		       String salesTeamCode = request.getParameter("salesTeamCode");
		       if (StringUtil.isNotBlank(salesTeamCode)) {
		           paras.put("salesTeamCode",salesTeamCode);
		       }
		       String salesTeamType = request.getParameter("salesTeamType");
		       if (StringUtil.isNotBlank(salesTeamType)) {
		           paras.put("salesTeamType",salesTeamType);
		       }

			   //数据权限相关查询条件
			   Subject subject = SecurityUtils.getSubject();
			   Employee employeeShiro = (Employee) subject.getPrincipal();
			   employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
			   paras.put("isAdmin", employeeShiro.getEmployeeNo());
			   paras.put("myDeptNo", employeeShiro.getDeptNo());

		       PageModel pageModel = salesTeamInfoClient.getSalesTeamInfoList(paras);
		       List list = pageModel.getList();
		       dataMsg.setTotal(pageModel.getTotalRecords());
		       dataMsg.setRows(pageModel.getList());
		       dataMsg.setMessageCode("200");
		   } catch (Exception e) {
		       dataMsg.setMessageCode("400");
		        logger.error("保险组织架构[查询]异常异常",e);
		    }
		    return dataMsg;
	}	
	
	 /**
    *
    *根据条件查询父辈组织机构
    * */
	@RequestMapping("/getParents")
	@ResponseBody
   public DataMsg getParents(HttpServletRequest request,DataMsg dataMsg){
		   try {
		       Map<String,Object> paras = new HashMap<>();
		       String salesTeamName = request.getParameter("salesTeamName");
		       if (StringUtil.isNotBlank(salesTeamName)) {
		    	   paras.put("salesTeamName",salesTeamName); 
		       }
		       String salesOrgId = request.getParameter("salesOrgId");
		       if (StringUtil.isNotBlank(salesOrgId)) {
		    	   paras.put("salesOrgId",salesOrgId); 
		       }
		        String teamTreeFlag = request.getParameter("teamTreeFlag");
		       if (StringUtil.isNotBlank(teamTreeFlag)) {
		    	   paras.put("teamTreeFlag",teamTreeFlag);
		       }
		       List<SalesTeamInfo> list = salesTeamInfoClient.getParents(paras);
		       dataMsg.setRows(list);
		       dataMsg.setMessageCode("200");
		   } catch (Exception e) {
		       dataMsg.setMessageCode("400");
		        logger.error("保险组织架构[查询]异常异常",e);
		    }
		    return dataMsg;
	}
	
    /**
     * 修改状态
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public DataMsg updateStatus(HttpServletRequest request, DataMsg dataMsg) {
        try {
        	Map<String, Object> paras = new HashMap<>();
        	String salesTeamId = request.getParameter("salesTeamId");
        	String teamStatus = request.getParameter("teamStatus");
        	paras.put("salesTeamId", salesTeamId);
        	SalesTeamInfo st = salesTeamInfoClient.selectById(paras);
        	st.setTeamStatus(teamStatus);
        	salesTeamInfoClient.updateSalesTeamInfo(st);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("保险组织架构[操作]异常异常",e);
        }
        return dataMsg;
    }

    /**
     *@描述   销售团队批量导入
     *@参数
     *@返回值
     *@创建人 qin lina
     *@创建时间 2020/3/9
     */
	@RequestMapping(value = "/importSalesTeam", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importSalesTeam(MultipartFile file) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
         Map<String,Object> msg =new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
         Employee employee = (Employee) subject.getPrincipal();
		Map<String, Object> stringObjectMap = new HashMap<>();
		stringObjectMap.put("employeeId", employee.getEmployeeId());
		params.put("now", DateUtil.getTimeNormalString(new Date()));
        params.put("emp",stringObjectMap);
        try {
            if (!file.isEmpty()) {
                 FileInputStream in = (FileInputStream) file.getInputStream();
                  Workbook workbook = WorkbookFactory.create(in);  //兼容xls和xlsx
                 Map<String,Sheet> sheetMap = new HashMap<String,Sheet>();
                   for(int i = 0; i<workbook.getNumberOfSheets();i++){
                    String sheetName = workbook.getSheetName(i);
                    Sheet sheetAt = workbook.getSheetAt(i);
                    sheetMap.put(sheetName,sheetAt);
                }
                //销售团队数据集
                 List<List<String>> salesTeamList = new ArrayList<>();
                Sheet att = sheetMap.get("salesTeam");
                int numberOfCells = att.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < att.getPhysicalNumberOfRows(); j++){
                    Row row = att.getRow(j);
                    if (row != null) {
                        List<String> teamList = new ArrayList<>();
                        for (int k = 0; k <= numberOfCells; k++) {//获取每个单元格
                            //teamList.add(String.valueOf(row.getCell(k)));
                            teamList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=teamList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                          salesTeamList.add(teamList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(salesTeamList)){
                    //组装保险公司数据
                    List<Map<String,Object>> sales_team_List = new LinkedList<Map<String,Object>>();
                    packsalesTeam(salesTeamList,sales_team_List,params);
                    //转换为json串
                    String salesTeam_string = JSONObject.toJSONString(sales_team_List);
                     Map<String,Object> p = new HashMap<String,Object>();
                    p.put("salesTeam_string", salesTeam_string);
                    msg = salesTeamInfoClient.insertImportList(p);

                }else {
                    msg.put("code","0000");
                }
            }else {
                msg.put("code","0000");
            }
        } catch (CheckException e) {
            String message = e.getMessage();
             msg.put("code","500");
             msg.put("error",message);
            logger.error("导入销售团队|异常：{}", e);
        } catch (Exception e) {
            logger.error("导入销售团队|异常：{}", e);
            msg.put("code","400");
    }
        return msg;
}

	private void packsalesTeam(List<List<String>> salesTeamList, List<Map<String, Object>> sales_team_list, Map<String, Object> params) {
        ArrayList<String> teamCodeList  = new ArrayList<>();
        ArrayList<String> teamNameList = new ArrayList<>();
         Map<String, Integer> treeCodemap = new HashMap<>();
        salesTeamList.forEach(teamList -> {
	      	//必填项校验
			  int[] filedindexs = {0,2,3,4,5,6,7};
			  String[] filedNames = {"组织机构名称","销售团队名称","销售团队代码","销售团队简称","销售团队状态","销售团队级别","成立时间"};
              List<String> validateMust = ImportUtil.validateMust(teamList, filedindexs, filedNames);
              if (!CollectionUtils.isEmpty(validateMust)){
                  throw new CheckException("请录入必填项："+validateMust.toString());
              }

              Map<String,Object> salesTeamMap = Maps.newHashMapWithExpectedSize(15);
              Map<String,Object> paras = new HashMap<>();
             //校验团队编码
              if (teamCodeList.contains(teamList.get(3))){
                  throw new CheckException("导入的数据中,团队编码："+teamList.get(3)+"存在重复值,请核查!");
              }
              paras.clear();
              //校验团队编码
              paras.put("salesTeamCode",teamList.get(3));
	    	  List<SalesTeamInfo> haveNum = salesTeamInfoClient.getParents(paras);
	    	  if (!CollectionUtils.isEmpty(haveNum)){
	    	      throw new CheckException("销售团队编码名称："+teamList.get(3)+"已存在");
              }
             //根据组织机构名称查询ID
              paras.clear();
              paras.put("salesOrgName",teamList.get(0));
              List<SalesOrgInfo> salesOrgs = salesOrgInfoClient.getParents(paras);
              if (CollectionUtils.isEmpty(salesOrgs)){
                 throw new CheckException("组织机构名称："+teamList.get(0)+"不存在");
              }
              salesTeamMap.put("salesOrgId",salesOrgs.get(0).getSalesOrgId());//组织机构名称
			 //校验团队名称
              if (teamNameList.contains(teamList.get(0)+teamList.get(2))){
              	 throw new CheckException("导入的数据中,组织机构"+teamList.get(0)+"下的团队名称："+teamList.get(2)+"存在重复值,请核查!");
			  }
			  paras.clear();
			  paras.put("salesTeamName",teamList.get(2));
              paras.put("salesOrgId",salesOrgs.get(0).getSalesOrgId());
              List<SalesTeamInfo> haveTeamName = salesTeamInfoClient.selectByCondition(paras);
			 if(!CollectionUtils.isEmpty(haveTeamName)){
				 throw new CheckException("销售团队名称："+teamList.get(2)+"在组织机构"+teamList.get(0)+"下已存在");
			 }
              String PTcode = "";
              //判断上级销售团队
              String parentSalesTeamName = teamList.get(1);
              if (StringUtils.isNoneEmpty(parentSalesTeamName) && !"null".equals(parentSalesTeamName) && !"无".equals(parentSalesTeamName)){
                  paras.clear();
                  paras.put("salesTeamName",parentSalesTeamName);
                   paras.put("salesOrgId",salesOrgs.get(0).getSalesOrgId());
                  List<SalesTeamInfo> parents = salesTeamInfoClient.selectByCondition(paras);
                  if (CollectionUtils.isEmpty(parents)){
                       throw new CheckException("上级销售团队名称："+teamList.get(1)+"在组织机构:"+teamList.get(0)+"下不存在");
                   }
                   if (StringUtil.isNotBlank(parents.get(0).getParentSalesTeamCode())){
                  	throw new CheckException("所填写的上级销售团队："+teamList.get(1)+"存在上级,不可再作为上级团队");
				   }
                   PTcode = parents.get(0).getTreeCode();
                   salesTeamMap.put("parentSalesTeamCode",parents.get(0).getSalesTeamCode());//上级销售团队
              }else {
				  if ("03".equals(teamList.get(6))){
					 throw new CheckException("团队名称："+teamList.get(2)+"的团队级别为处长级，请填写上级团队");
				  }
                  salesTeamMap.put("parentSalesTeamCode",null);//上级销售团队
              }
              //treeCode
              int treeCodeLen = 0;
    		treeCodeLen = PTcode.length()+4;
    		paras.put("treeCodeLen",treeCodeLen);
    		paras.put("PTcode",PTcode);
    		if (treeCodemap.get(PTcode) != null ){
    		    salesTeamMap.put("treeCode",PTcode+(treeCodemap.get(PTcode) + 1) + "");//treeCode
            }else {
    		    int preTreeCode = salesTeamInfoClient.findMaxTreeCode(paras);
                if(preTreeCode==0){
                   salesTeamMap.put("treeCode",PTcode+"1000");//treeCode
                }else {
                    salesTeamMap.put("treeCode",PTcode + (preTreeCode + 1) + "");//treeCode
                }
            }

	      	salesTeamMap.put("salesTeamName",teamList.get(2));//销售团队名称
	      	salesTeamMap.put("salesTeamCode",teamList.get(3));//销售团队代码
	      	salesTeamMap.put("salesTeamNameLess",teamList.get(4));//销售团队简称
            if (!"0".equals(teamList.get(5)) && !"1".equals(teamList.get(5)) ){
                throw new CheckException("团队名称："+teamList.get(2)+"的销售团队状态填写不正确,请参考字典值");
            }
            salesTeamMap.put("teamStatus",teamList.get(5));//销售团队状态
            if (!"02".equals(teamList.get(6)) && !"03".equals(teamList.get(6)) && !"04".equals(teamList.get(6)) ){
                throw new CheckException("团队名称："+teamList.get(2)+"的销售团队级别填写不正确,请参考字典值");
            }
	      	salesTeamMap.put("teamLevel",teamList.get(6));//销售团队级别
	      	//salesTeamMap.put("salesTeamType",teamList.get(7));//销售团队类型
	      	salesTeamMap.put("dateOfEstablishment",teamList.get(7));//成立时间
            if (!DateTimeUtil.isLegalDate(teamList.get(7))){
                throw new CheckException("团队名称："+teamList.get(2)+"成立时间格式不正确,请填写yyyy-MM-dd格式的时间");
            }
			salesTeamMap.put("fax",teamList.get(8));//职场传真
			salesTeamMap.put("tel",teamList.get(9));//职场电话
			if(StringUtils.isNotEmpty(teamList.get(10))) {
				boolean regflag2 = teamList.get(10).matches(Constants.DATA_FORMAT_NUMBER);
            if (!regflag2) {
                throw new CheckException("团队名称："+teamList.get(2)+"邮政编码："+teamList.get(10)+"不为数字");
            }
			}
			salesTeamMap.put("postCode",teamList.get(10));
			salesTeamMap.put("address",teamList.get(11));
             salesTeamMap.put("createdTime", params.get("now"));
            Map<String,Object> emp = (Map<String, Object>) params.get("emp");
            salesTeamMap.put("createdBy", emp.get("employeeId"));//创建人
            String treeCode = salesTeamMap.get("treeCode").toString();
            teamCodeList.add(teamList.get(3));
            teamNameList.add(teamList.get(0)+teamList.get(2));
            treeCodemap.put(PTcode,Integer.valueOf(treeCode.substring(treeCode.length()-4,treeCode.length())));



            sales_team_list.add(salesTeamMap);
		  });
	}

}
