package com.hzcf.plantform.controller.Insurance;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.insurance.InsuranceSalesInfoClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.feign.insurance.SalesTeamInfoClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.DateTimeUtil;
import com.hzcf.plantform.util.ExportExcel;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.SalesOrgDetail;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.SalesTeamInfo;
import com.hzcf.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销售组织管理
 *
 * @author sxm
 */
@Controller
@RequestMapping("/salesOrgInfo")
public class SalesOrgInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesOrgInfoClient salesOrgInfoClient;
    
    @Autowired
    private InsuranceSalesInfoClient insuranceSalesInfoClient;

    @Autowired
    private SalesTeamInfoClient salesTeamInfoClient;


    @Autowired
    EmployeeFeignClient employeeFeignClient;
    @Value("${fdfs.ip}")
	private String fastInnerUrl;


    /**
     * 跳转到列表页面
     */
    @RequestMapping("/toListPath")
    public String toListPath(Model model) {
        return "salesOrgInfoPage/salesOrgInfoList";
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/toAddPath")
    public String toAddPath(Model model) {
         model.addAttribute("fastInnerUrl",fastInnerUrl);
        return "salesOrgInfoPage/salesOrgInfoAdd";
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/toUpdatePath")
    public String toUpdatePath(Model model, HttpServletRequest request) {
        String salesOrgId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("salesOrgId", salesOrgId);
        SalesOrgInfo salesOrgInfo = salesOrgInfoClient.selectById(paras);
        
        paras.put("deleteFlag","0");
        List<DirectorAllowanceStandardPojo> zjjtList = insuranceSalesInfoClient.findZjjt(paras);
        model.addAttribute("fastInnerUrl",fastInnerUrl);
        model.addAttribute("salesOrgInfo", salesOrgInfo);
        model.addAttribute("zjjtList", JSONObject.toJSONString(zjjtList));
        return "salesOrgInfoPage/salesOrgInfoUpdate";
    }

    /**
     * 增加部门
     */
    @RequiresPermissions("salesOrgInfo:add")//权限管理;
    @RequestMapping("addSalesOrgInfo")
    @ResponseBody
    public DataMsg addSalesOrgInfo(HttpServletRequest request, SalesOrgInfo salesOrgInfo, SalesOrgDetail salesOrgDetail, DataMsg msg) {
        try {
            String saleOrgLevel = salesOrgInfo.getSalesOrgLevel();
            if("01".equals(saleOrgLevel)) {
                Map<String, Object> params = new HashMap<>();
                if(isExistTopOrg(params)){
                    msg.setMessageCode("500");
                    msg.setRequestState("已存在顶级机构，顶级机构有且唯一");
                    return msg;
                }
            }
        	Map<String, Object> paras = new HashMap<>();
        	salesOrgInfo.setCooperationType("0");
            String parentSalesOrgCode = salesOrgInfo.getParentSalesOrgCode();
            String PTcode = request.getParameter("PTcode");
            
            String salesOrgCode = salesOrgInfo.getSalesOrgCode();
            paras.put("salesOrgCode", salesOrgCode);
            List<SalesOrgInfo> haveNum = salesOrgInfoClient.findSalesOrgs(paras);
            if(haveNum!=null && haveNum.size()>0){
            	msg.setMessageCode("999");
            	msg.setRequestState("组织机构代码已经存在，请修改！");
            	return msg;
            }
            paras.clear();

            Map<String, Object> map = new HashMap<>();
            map.put("salesOrgId",salesOrgInfo.getSalesOrgId());
            map.put("salesOrgName",salesOrgInfo.getSalesOrgName());
            List<SalesOrgInfo> salesOrgInfos = salesOrgInfoClient.findSalesOrgsByNames(map);
            if (null!=salesOrgInfos&&salesOrgInfos.size()>0) {
                msg.setMessageCode("999");
            	msg.setRequestState("组织机构名称已经存在，请修改！");
            	return msg;
            }
            
            String zjjtList = request.getParameter("zjjtList");
            
            if (!StringUtil.isNotBlank(PTcode)) {
                PTcode = "";
            }
            if (StringUtil.isNotBlank(parentSalesOrgCode)) {
                parentSalesOrgCode = salesOrgInfo.getParentSalesOrgCode();
            } else {
                parentSalesOrgCode = "";
            }
            String orgLevel = salesOrgInfo.getSalesOrgLevel();
            
            if (StringUtil.isNotBlank(orgLevel)) {
                int treeCodeLen = 0;
                treeCodeLen = PTcode.length()+4;
                paras.put("treeCodeLen", treeCodeLen);
                paras.put("parentSalesOrgCode", parentSalesOrgCode);
                int preTreeCode = salesOrgInfoClient.findMaxTreeCode(paras);
                if (preTreeCode == 0) {
                    salesOrgInfo.setTreeCode(PTcode + "1000");
                } else {
                    salesOrgInfo.setTreeCode(PTcode + (preTreeCode + 1) + "");
                }
            }
            salesOrgInfo.setCreatedTime(new Date());
            if(!StringUtil.isNotEmpty(salesOrgInfo.getOrgStatus())){
                salesOrgInfo.setOrgStatus("0");
            }
            /** 新增机构设置默认基本法及基本法机构 */
            SalesOrgInfo parentSalesOrg = salesOrgInfoClient.getSalesOrgInfoByTreeCode(PTcode);
            if(parentSalesOrg == null) {
                msg.setMessageCode("999");
                msg.setRequestState("查询上级机构失败，请联系管理员，稍后再试！");
                logger.error("通过treeCode={}查询机构信息失败", PTcode);
                return msg;
            }
            salesOrgInfo.setIsDefaultCal(CalParamRuleEnum.CAL_DEFAULT.getCode());
            salesOrgInfo.setCalOrgId(parentSalesOrg.getCalOrgId());

            salesOrgInfo.setCreatedTime(new Date());
            int salesOrgId = salesOrgInfoClient.addSalesOrgInfo(salesOrgInfo);
            salesOrgDetail.setCreatedTime(new Date());
            salesOrgDetail.setSalesOrgId(Long.valueOf(salesOrgId));
            salesOrgInfoClient.addSalesOrgDetail(salesOrgDetail);
            
	    	if(StringUtil.isNotBlank(zjjtList)){
	    		List<DirectorAllowanceStandardPojo> zjjtLists = toList(zjjtList,DirectorAllowanceStandardPojo.class);
	    		if(zjjtLists.size()>0){
		    		for(DirectorAllowanceStandardPojo directorAllowanceStandardPojo:zjjtLists){
		    			directorAllowanceStandardPojo.setSalesOrgId(Long.valueOf(salesOrgId));
		    		}
		    		insuranceSalesInfoClient.addZjjt(zjjtLists);
	    		}
	    	}
            
            msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            msg.setRequestState("保险组织机构[新增]异常！");
            logger.info("保险组织架构[新增]异常",e);
        }
        return msg;
    }

    public static List toList(String s,Class clazz){
        List<Class> list = JSONArray.parseArray(s,clazz);
        return list;
    }    
    
    /**
     * 增加部门
     */
    @RequiresPermissions("salesOrgInfo:update")//权限管理;
    @RequestMapping("updateSalesOrgInfo")
    @ResponseBody
    public DataMsg updateSalesOrgInfo(HttpServletRequest request, SalesOrgInfo salesOrgInfo,
                                      String preSalesOrgLevel, String preParentSalesOrgCode,
                                      SalesOrgDetail salesOrgDetail, DataMsg msg,String isNoticy) {
        try {
            String saleOrgLevel = salesOrgInfo.getSalesOrgLevel();
            if("01".equals(saleOrgLevel)) {
                Map<String, Object> params = new HashMap<>();
                params.put("salesOrgId", salesOrgInfo.getSalesOrgId());
                if(isExistTopOrg(params)){
                    msg.setMessageCode("500");
                    msg.setRequestState("已存在顶级机构，顶级机构有且唯一");
                    return msg;
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("salesOrgId",salesOrgInfo.getSalesOrgId());
            map.put("salesOrgName",salesOrgInfo.getSalesOrgName());
            List<SalesOrgInfo> salesOrgInfos = salesOrgInfoClient.findSalesOrgsByNames(map);
            if (null!=salesOrgInfos&&salesOrgInfos.size()>0) {
                msg.setMessageCode("999");
            	msg.setRequestState("组织机构名称已经存在，请修改！");
            	return msg;
            }
            String salesOrgLevel = salesOrgInfo.getSalesOrgLevel();
            //组织机构发生变化时
            if(!preParentSalesOrgCode.equals(salesOrgInfo.getParentSalesOrgCode())) {

                if("02".equals(preSalesOrgLevel) && !"02".equals(salesOrgLevel)){
                    msg.setMessageCode("999");
                    msg.setRequestState("省级机构，不允许修改组织机构级别");
                    return msg;
                }
                if(!"02".equals(preSalesOrgLevel) && "02".equals(salesOrgLevel)){
                    msg.setMessageCode("999");
                    msg.setRequestState("非省级机构不允许，修改为省级机构级别");
                    return msg;
                }

                if(!"02".equals(salesOrgLevel)){
                    Map<String, Object> params = new HashMap<>();
                    Map<String, String> calOrgsMap = salesOrgInfoClient.getCalOrgsBySalesOrgInfo(salesOrgInfo);

                    String preCalOrgId = calOrgsMap.get("preCalOrgId");
                    String nextCalOrgId = calOrgsMap.get("nextCalOrgId");
                    if(!preCalOrgId.equals(nextCalOrgId)) {
                        msg.setRequestState("组织机构不允许跨省修改");
                        msg.setMessageCode("1000");
                        logger.error("销售机构'salesOrgId={}'不能跨省修改销售机构", salesOrgInfo.getSalesOrgId());
                        return msg;
                    }
                }
            }

            salesOrgInfo.setCooperationType("0");
        	salesOrgInfo.setUpdatedTime(new Date());
            salesOrgInfoClient.updateSalesOrgInfo(salesOrgInfo,isNoticy);
            salesOrgInfoClient.updateSalesOrgDetail(salesOrgDetail);
            
            String zjjtList = request.getParameter("zjjtList");
            if(StringUtil.isNotBlank(zjjtList)){
	    		List<DirectorAllowanceStandardPojo> zjjtLists = toList(zjjtList,DirectorAllowanceStandardPojo.class);
    			List<DirectorAllowanceStandardPojo> zjjtListsAdd = new ArrayList<DirectorAllowanceStandardPojo>();
    			List<DirectorAllowanceStandardPojo> zjjtListsUpd = new ArrayList<DirectorAllowanceStandardPojo>();
	    		String updateIds = "";
	    		for(DirectorAllowanceStandardPojo zjjt:zjjtLists){
	    			zjjt.setSalesOrgId(salesOrgInfo.getSalesOrgId());
	    			if(zjjt.getAllowanceId()!=null){
	    				zjjtListsUpd.add(zjjt);
	    				updateIds = updateIds+zjjt.getAllowanceId()+",";
	    			}else{
	    				zjjtListsAdd.add(zjjt);	
	    			}
	    		}
	    		//修改titles
	    		if(zjjtListsUpd.size()>0){
	    			insuranceSalesInfoClient.updateZjjts(zjjtListsUpd);
	    			
	    		}
	    		//将被删除的titles删除
	    		Map<String,Object> paras = new HashMap<>();
	    		if(updateIds.endsWith(",")){
	    			updateIds = updateIds.substring(0,updateIds.length()-1);
	    		}
    			paras.put("updateIds", updateIds);
    			paras.put("salesOrgId", salesOrgInfo.getSalesOrgId());
    			insuranceSalesInfoClient.deleteZjjtByIds(paras);
    			//新增新加入的titles
	    		if(zjjtListsAdd.size()>0){
	    			insuranceSalesInfoClient.addZjjt(zjjtListsAdd);
	    		}
	    		
	    	}
            
            msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            logger.info("保险组织架构[修改]异常",e);
        }
        return msg;
    }


    /**
     * 查询部门基础信息List
     */
    @RequestMapping("/salesOrgInfoList")
    @ResponseBody
    public DataMsg salesOrgInfoList(HttpServletRequest request, DataMsg dataMsg) {

        try {
            Map<String, Object> paras = new HashMap<>();
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                paras.put("pageNo", 1);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                paras.put("pageSize", 10);
            }
            String salesOrgLevel = request.getParameter("salesOrgLevel");
            if (StringUtil.isNotBlank(salesOrgLevel)) {
                paras.put("salesOrgLevel", salesOrgLevel);
            }
            String salesOrgName = request.getParameter("salesOrgName");
            if (StringUtil.isNotBlank(salesOrgName)) {
                paras.put("salesOrgName", salesOrgName);
            }
            String salesOrgCode = request.getParameter("salesOrgCode");
            if (StringUtil.isNotBlank(salesOrgCode)) {
                paras.put("salesOrgCode", salesOrgCode);
            } 
            String cooperationType = request.getParameter("cooperationType");
            if (StringUtil.isNotBlank(cooperationType)) {
                paras.put("cooperationType", cooperationType);
            }
            //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());

            PageModel pageModel = salesOrgInfoClient.getSalesOrgInfoList(paras);
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
     * 查询组织机构不分页
     */
    @RequestMapping("/findSalesOrgs")
    @ResponseBody
    public DataMsg findSalesOrgs(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> paras = new HashMap<>();
            String salesOrgLevel = request.getParameter("salesOrgLevel");
            String treeCode = request.getParameter("treeCode");
            String salesOrgName = request.getParameter("salesOrgName");
            String cooperationType = request.getParameter("cooperationType");
            String dataAuthFlag = request.getParameter("dataAuthFlag");
            String salesOrgId = request.getParameter("salesOrgId");
            String basicOrg = request.getParameter("basicOrg");
            if (StringUtil.isNotBlank(salesOrgLevel)) {
                paras.put("salesOrgLevel", salesOrgLevel);
            }
            if (StringUtil.isNotBlank(treeCode)) {
                paras.put("treeCode", treeCode);
            }
            if (StringUtil.isNotBlank(salesOrgName)) {
                paras.put("salesOrgName", salesOrgName);
            }
            if (StringUtil.isNotBlank(cooperationType)) {
                paras.put("cooperationType", cooperationType);
            }
            //当salesOrgLevel 为 团队时，查询结果排除自己
            if (StringUtil.isNotBlank(salesOrgId) && "05".equals(salesOrgLevel)) {
                paras.put("salesOrgId", salesOrgId);
            }
            //员工异动不能跨省 所以只查询该代理人所在省份下的组织机构
             if (StrUtil.isNotBlank(basicOrg)){
                 paras.put("basicOrg", basicOrg);
            }
            if("1".equals(dataAuthFlag)) { //需要数据权限
                //数据权限相关查询条件
                Subject subject = SecurityUtils.getSubject();
                Employee employeeShiro = (Employee) subject.getPrincipal();
                employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
                paras.put("isAdmin", employeeShiro.getEmployeeNo());
                paras.put("myDeptNo", employeeShiro.getDeptNo());
                paras.put("dataAuthFlag", dataAuthFlag);
            }
            List<SalesOrgInfo> list = salesOrgInfoClient.findSalesOrgs(paras);
            dataMsg.setRows(list);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("保险组织架构[查询]异常异常",e);
        }
        return dataMsg;
    }


    @RequestMapping("/findSubordinateOrgList")
    @ResponseBody
    public DataMsg findSubordinateOrgList(HttpServletRequest request, DataMsg dataMsg) {
        try {
            String salesOrgId = request.getParameter("salesOrgId");
            if(!StringUtils.isNotBlank(salesOrgId)){
                return dataMsg;
            }
            List<SalesOrgInfo> list = salesOrgInfoClient.findSubordinateOrgList(Long.valueOf(salesOrgId));
            dataMsg.setRows(list);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            e.printStackTrace();
            logger.error("根据机构id查询机构及下属机构异常");
        }
        return dataMsg;
    }



    /**
     * 根据条件查询父辈组织机构
     */
    @RequestMapping("/getParents")
    @ResponseBody
    public DataMsg getParents(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> paras = new HashMap<>();
            String pTreeCode = request.getParameter("pTreeCode");
            String accountingOrg = request.getParameter("accountingOrg");
            if (StringUtil.isNotBlank(pTreeCode)) {
                paras.put("pTreeCode", pTreeCode);
            }
            if (StringUtil.isNotBlank(accountingOrg)) {
                paras.put("accountingOrg", accountingOrg);
            }
            List<SalesOrgInfo> list = salesOrgInfoClient.getParents(paras);
            //logger.info(list.toString());
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
        	String salesOrgId = request.getParameter("salesOrgId");
        	String orgStatus = request.getParameter("orgStatus");
        	paras.put("salesOrgId", salesOrgId);
        	SalesOrgInfo so = salesOrgInfoClient.selectById(paras);
        	so.setOrgStatus(orgStatus);
        	String isNoticy = "0";
        	salesOrgInfoClient.updateSalesOrgInfo(so,isNoticy);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("保险组织架构[操作]异常异常",e);
        }
        return dataMsg;
    }
    
    @RequestMapping("/orgExport")
    //@ResponseBody
    public void orgExport(HttpServletRequest request, HttpServletResponse response,DataMsg dataMsg) {
        try {
            Map<String, Object> params = new HashMap<>();
            String ids = request.getParameter("ids");
            if(StringUtil.isNotBlank(ids) && !ids.equals("all")){
            	params.put("ids",ids);
            }
            getAuthDataParams(params);
            List<SalesOrgInfo> resultList = salesOrgInfoClient.findSalesOrgs(params);
            List<Object[]> dataList = Lists.newArrayListWithExpectedSize(resultList.size());
            String title = "组织机构导出-" + DateTimeUtil.getNowDateChinaString();
            Object[] objs = null;
            SalesOrgInfo map = new SalesOrgInfo();
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] rowsName = new String[]{"序号", "组织机构代码","组织机构名称", "机构级别", "是否有上级", "上级机构","经营状态", "创建时间"};
            for (int i = 0; i < resultList.size(); i++) {
                map = resultList.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i + 1;//序号
                objs[1] = map.getSalesOrgCode();
                objs[2] = map.getSalesOrgName();
                if(map.getSalesOrgLevel().equals("01")){
                	objs[3] = "总公司";
                	objs[4] = "否";
                	objs[5] = "-";
                }else{
                	objs[4] = "是";
                	if(map.getSalesOrgLevel().equals("02")){
                		objs[3] = "省级分公司";
                	}else if(map.getSalesOrgLevel().equals("03")){
                		objs[3] = "地市分公司";
                	}else if(map.getSalesOrgLevel().equals("04")){
                		objs[3] = "区县分公司";
                	}else if(map.getSalesOrgLevel().equals("05")){
                        objs[3] = "团队级别";
                    }
                    params.clear();
                    params.put("salesOrgCode", map.getParentSalesOrgCode());
                    SalesOrgInfo pOrg = salesOrgInfoClient.selectById(params);
                    objs[5] = pOrg.getSalesOrgName();
                }
                if(map.getOrgStatus().equals("0")){
                	objs[6] = "筹建";
                }else if(map.getOrgStatus().equals("1")){
                	objs[6] = "正常";
                }else if(map.getOrgStatus().equals("2")){
                	objs[6] = "整改";
                }else{
                	objs[6] = "撤销";
                }
                objs[7] = date.format(map.getCreatedTime());
                dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            /*if(org.apache.commons.collections.CollectionUtils.isNotEmpty(resultList)) {
                ex.exportToCache(response, request.getSession().getServletContext().getRealPath("/download/"));
                dataMsg.setMessageCode("200");
                dataMsg.setData(title);
            }else{
                dataMsg.setMessageCode("300");
            }*/
            dataMsg.setMessageCode("200");
            ex.exportBigData(response);
        } catch (Exception e) {
            logger.error("导出[机构列表] | 异常"+e);
            dataMsg.setMessageCode("400");
        }        
    }

    /**
     * 构建组织机构树
     */
    @RequiresPermissions("salesOrgInfo:tree")//权限管理;
    @RequestMapping("/orgTree")
    public String orgTree(Model model, HttpServletRequest request) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("salesOrgLevel", "01");
        List<SalesOrgInfo> list = salesOrgInfoClient.findSalesOrgs(paras);
        if (list != null) {
            readTree(list);
        }
        model.addAttribute("salesOrgList", JSON.toJSONString(list));
        return "salesOrgInfoPage/salesOrgInfoTree";
    }

    public boolean isExistTopOrg(Map<String, Object> params) {
        boolean flag = false;
        int count = salesOrgInfoClient.getTopOrgNum(params);
        if(count > 0) {
            flag = true;
        }
        return flag;
    }

    void readTree(List<SalesOrgInfo> lists) {
        for (SalesOrgInfo org : lists) {
            Map<String, Object> paras = new HashMap<>();
            paras.put("parentSalesOrgCode", org.getSalesOrgCode());
            List<SalesOrgInfo> childList = salesOrgInfoClient.findSalesOrgs(paras);
            //查询该组织机构下的团队
            paras.clear();
            paras.put("salesOrgId",org.getSalesOrgId());
            paras.put("teamTreeFlag","1");
            List<SalesTeamInfo> salesTeamInfos = salesTeamInfoClient.selectByCondition(paras);
            if (CollUtil.isNotEmpty(salesTeamInfos)){
                org.setOrgTeamInfos(salesTeamInfos);
                readTeamTree(salesTeamInfos);
            }

            if (childList != null) {
                org.setOrgChildrens(childList);
                readTree(childList);
            }
        }
    }

    private void readTeamTree(List<SalesTeamInfo> salesTeamInfos) {
        for(SalesTeamInfo salesTeamInfo :salesTeamInfos){
             Map<String, Object> paras = new HashMap<>();
             paras.put("salesOrgId",salesTeamInfo.getSalesOrgId());
             paras.put("parentSalesTeamCode",salesTeamInfo.getSalesTeamCode());
            List<SalesTeamInfo> chileTeamList =  salesTeamInfoClient.selectByCondition(paras);
            if (CollUtil.isNotEmpty(chileTeamList)){
                salesTeamInfo.setChildTeamInfoList(chileTeamList);
                readTeamTree(chileTeamList);
            }
        }
    }

    public void getAuthDataParams(Map<String,Object> paras){
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
        //数据权限相关
        paras.put("isAdmin", employee.getEmployeeNo());
        String myDeptNo =  employee.getDeptNo();
        paras.put("myDeptNo", myDeptNo);
        paras.put("dataAuthFlag", "1");

    }
}
