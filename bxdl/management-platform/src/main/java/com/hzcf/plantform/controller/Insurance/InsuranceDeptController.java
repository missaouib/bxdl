package com.hzcf.plantform.controller.Insurance;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.exception.CheckException;
import com.hzcf.plantform.feign.SystemDictFeignClient;
import com.hzcf.plantform.feign.insurance.InsuranceDeptClient;
import com.hzcf.plantform.feign.insurancePolicy.InsPolicyInsuredPersonFeign;
import com.hzcf.plantform.util.*;
import com.hzcf.pojo.basic.District;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.InsuranceDept;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 保险部门管理
 *
 * @author sxm
 */
@Controller
@RequestMapping("/insurance_dept")
public class InsuranceDeptController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InsuranceDeptClient insuranceDeptClient;
    @Autowired
    private InsPolicyInsuredPersonFeign insPolicyInsuredPersonFeign;
    @Autowired
    private SystemDictFeignClient systemDictFeignClient;

    /**
     * 跳转到页面
     */
    @RequestMapping("/to_insurance_dept_path")
    public String toInsuranceDeptPath() {
        return "insuranceDeptPage/InsuranceDeptList";
    }

    /**
     * 跳转到页面
     *
     * */
	@RequestMapping("/toAddPath")
    public String toAddPath(){return "insuranceDeptPage/InsuranceDeptAdd";}
	
	/**
	 * 跳转到修改
	 */
	@RequestMapping("/toUpdate")
	public String toUpdateProcess(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String,Object> paras = new HashMap<>();
		paras.put("id",id);
        InsuranceDept pageModel =insuranceDeptClient.selectInsuranceBasicDept(paras);
		model.addAttribute("insurancedept",pageModel);
		return "insuranceDeptPage/InsuranceDeptUpdate";
	}
	/**
	 * 跳转到查看
	 */
	@RequestMapping("/toSelect")
	public String toSelectProcess(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String,Object> paras = new HashMap<>();
		paras.put("id",id);
		InsuranceDept pageModel =insuranceDeptClient.selectInsuranceBasicDept(paras);
		model.addAttribute("insurancedept",pageModel);
		return "insuranceDeptPage/InsuranceDeptSelect";
	}
    /**
     * 跳转到组织机构页面
     */
    @RequestMapping("/to_insurance_org")
    public String toInsuranceOrg() {
        return "insuranceDeptPage/InsuranceOrgList";
    }

    /**
     * 跳转到组织机构新增
     *
     * */
	@RequestMapping("/toAddOrg")
    public String toAddOrg(){return "insuranceDeptPage/InsuranceOrgAdd";}
	
	/**
	 * 跳转到组织机构修改
	 */
	@RequestMapping("/toUpdateOrg")
	public String toUpdateOrg(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String,Object> paras = new HashMap<>();
		paras.put("id",id);
        InsuranceDept pageModel =insuranceDeptClient.selectInsuranceBasicDept(paras);
		model.addAttribute("insurancedept",pageModel);
		return "insuranceDeptPage/InsuranceOrgUpdate";
	}
	/**
	 * 跳转到组织机构查看
	 */
	@RequestMapping("/toSelectOrg")
	public String toSelectOrg(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String,Object> paras = new HashMap<>();
		paras.put("id",id);
		InsuranceDept pageModel =insuranceDeptClient.selectInsuranceBasicDept(paras);
		model.addAttribute("insurancedept",pageModel);
		return "insuranceDeptPage/InsuranceOrgSelect";
	}

    @RequestMapping("/test")
    @ResponseBody
    public Map<String, Object> test() {
        Map<String, Object> s = insuranceDeptClient.getTest();
        return s;
    }

    /**
     * 查询部门基础信息List
     */
    @RequestMapping("/do_insurance_dept")
    @ResponseBody
    public DataMsg doInsuranceDept(HttpServletRequest request, DataMsg dataMsg) {



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
		       String orgLevel = request.getParameter("orgLevel");
		       if (StringUtil.isNotBlank(orgLevel)) {
		    	   paras.put("orgLevel",orgLevel); 
		       }
		   String noOrgLevel = request.getParameter("noOrgLevel");
		   if (StringUtil.isNotBlank(noOrgLevel)) {
			   paras.put("noOrgLevel",noOrgLevel);
		   }

            String insuranceCompanyCode = request.getParameter("insuranceCompanyCode");
            if (StringUtil.isNotBlank(insuranceCompanyCode)) {
                paras.put("insuranceCompanyCode", insuranceCompanyCode);
            }
            String insuranceCompanyName = request.getParameter("insuranceCompanyName");
            if (StringUtil.isNotBlank(insuranceCompanyName)) {
                paras.put("insuranceCompanyName", insuranceCompanyName);
            }
            String insuranceCompanyNameLess = request.getParameter("insuranceCompanyNameLess");
            if (StringUtil.isNotBlank(insuranceCompanyNameLess)) {
                paras.put("insuranceCompanyNameLess", insuranceCompanyNameLess);
            }
            String businessType = request.getParameter("businessType");
            if (StringUtil.isNotBlank(businessType)) {
                paras.put("businessType", businessType);
            }
            String createdTimeStat = request.getParameter("createdTimeStat");
            if (StringUtil.isNotBlank(createdTimeStat)) {
                paras.put("createdTimeStat", createdTimeStat + " 00:00:00");
            }
            String createdTimeEnd = request.getParameter("createdTimeEnd");
            if (StringUtil.isNotBlank(createdTimeEnd)) {
                paras.put("createdTimeEnd", createdTimeEnd + " 23:59:59");
            }
            String c_insuranceCompanyName = request.getParameter("c_insuranceCompanyName");//顶级
            if (StringUtil.isNotBlank(c_insuranceCompanyName)) {
                paras.put("c_insuranceCompanyName", c_insuranceCompanyName);
            }
            String b_insuranceCompanyName = request.getParameter("b_insuranceCompanyName");//上级
            if (StringUtil.isNotBlank(b_insuranceCompanyName)) {
                paras.put("b_insuranceCompanyName", b_insuranceCompanyName);
            }


            PageModel pageModel = insuranceDeptClient.getInsuranceDeptList(paras);
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
     * 查询部门基础信息List
     */
    @RequestMapping("/findInsurCompanys")
    @ResponseBody
    public DataMsg findInsurCompanys(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> paras = new HashMap<>();
            String orgLevel = request.getParameter("orgLevel");
            String indexCode = request.getParameter("indexCode");
            String insuranceCompanyName = request.getParameter("insuranceCompanyName");
            if (StringUtil.isNotBlank(orgLevel)) {
                paras.put("orgLevel", orgLevel);
            }
            if (StringUtil.isNotBlank(indexCode)) {
                paras.put("indexCode", indexCode);
            }
            if (StringUtil.isNotBlank(insuranceCompanyName)) {
                paras.put("insuranceCompanyName", insuranceCompanyName);
            }

            List<InsuranceDept> list = insuranceDeptClient.findInsurCompanys(paras);
            dataMsg.setRows(list);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("保险组织架构[查询]异常异常",e);
        }
        return dataMsg;
    }


    /**
     * 增加部门
     */
    @RequiresPermissions("insCompany:add")//权限管理;
    @RequestMapping("add_insurance_dept")
    @ResponseBody
    public DataMsg addInsuranceDept(InsuranceDept insuranceDept, DataMsg msg) {
        try {
            String insuranceCompanyName = insuranceDept.getInsuranceCompanyName();
            insuranceCompanyName = insuranceCompanyName.replaceAll(" ", "");
            boolean result = insuranceDeptClient.findExistOrgByNameOrCode(insuranceDept.getInsuranceCompanyId(), insuranceCompanyName, insuranceDept.getInsuranceCompanyCode().replaceAll(" ", ""));
            if (result) {
                msg.setMessageCode("200001");
                msg.setData("名称或代码已存在");
                return msg;
            }
            if(insuranceDept.getInsuranceCompanyCode().equals(insuranceDept.getParentCompanyCode())){
                msg.setMessageCode("200002");
                msg.setData("不能与上级机构代码一致");
                return msg;
            }
            insuranceDeptClient.addInsuranceDept(insuranceDept);
            msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            logger.info("保险组织架构[新增]异常",e);
        }
        return msg;
    }

    /**
     * 修改部门
     */
    @RequiresPermissions("insCompany:update")//权限管理;
    @RequestMapping("update_insurance_dept")
    @ResponseBody
    public DataMsg updateInsuranceDept(InsuranceDept insuranceDept, DataMsg msg) {
        try {
            String insuranceCompanyName = insuranceDept.getInsuranceCompanyName();
            insuranceCompanyName = insuranceCompanyName.replaceAll(" ", "");
            boolean result = insuranceDeptClient.findExistOrgByNameOrCode(insuranceDept.getInsuranceCompanyId(), insuranceCompanyName, insuranceDept.getInsuranceCompanyCode().replaceAll(" ", ""));
            if (result) {
                msg.setMessageCode("200001");
                msg.setData("名称或代码已存在");
                return msg;
            }
            if(insuranceDept.getInsuranceCompanyCode().equals(insuranceDept.getParentCompanyCode())){
                msg.setMessageCode("200002");
                msg.setData("不能与上级机构代码一致");
                return msg;
            }
			insuranceDeptClient.updateInsuranceDept(insuranceDept);
			msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            msg.setData(e.getMessage());
            logger.info("保险组织架构[修改]异常",e);
        }
        return msg;
    }

    /**
     * 删除部门
     */
    @RequiresPermissions(value = "insCompany:delete")//权限管理;
    @RequestMapping("delete_insurance_dept")
    @ResponseBody
    public ReturnMsgData deleteInsuranceDept(@RequestParam("ids") String ids) {
        ReturnMsgData msg = new ReturnMsgData();
        try {
            String[] split = ids.split(",");
            List<String> idList = Arrays.asList(split);
            List<String> errorList = new ArrayList<String>();
            idList.forEach(id -> {
                /**
                 * 根据选中部门ID，查询该部门是否有子集.
                 * 	1.如果存在子集，不执行删除操作，返回提示信息.
                 * 	2.全部不存在，执行删除操作.
                 * */
                InsuranceDept dept = insuranceDeptClient.selectSubsetById(id);//查询部门子集
                if (dept != null) {
                    String insuranceCompanyName = dept.getInsuranceCompanyName();
                    errorList.add(insuranceCompanyName);
                }

            });
            if (errorList.size() < 1) {
                insuranceDeptClient.deleteInsuranceDept(ids);
                msg.setReturnCode("200");
            } else {
                msg.setReturnCode("201");
                msg.setData(errorList);
            }
        } catch (Exception e) {
            msg.setReturnCode("400");
            logger.info("保险组织架构[删除]异常",e);
        }
        return msg;
    }

    /**
     * 查看详情 基本信息详情
     */
    @RequestMapping("/select_insurance_basic_dept")
    @ResponseBody
    public Map<String, Object> selectInsuranceBasicDept(@RequestParam("id") String id) {
        Map<String, Object> paras = new HashMap<>();
        try {
            paras.put("id", id);
            InsuranceDept pageModel = insuranceDeptClient.selectInsuranceBasicDept(paras);
            paras.clear();
            paras.put("data", pageModel);
            paras.put("code", "200");
        } catch (Exception e) {
            paras.put("code", "400");
            logger.error("保险组织架构[查看详情]异常异常",e);
        }
        return paras;
    }

    /**
     * 删除保险机构
     */
    @RequiresPermissions(value = "insCompanyOrg:delete")//权限管理;
    @RequestMapping("delete_insurance_org")
    @ResponseBody
    public ReturnMsgData deleteInsuranceOrg(@RequestParam("ids") String ids) {
        ReturnMsgData msg = new ReturnMsgData();
        try {
            String[] split = ids.split(",");
            String[] codeList = split;
            List<String> errorList = new ArrayList<String>();

            for(String code : codeList){
                // 删除前校验保险公司是否存在投保单
                boolean result = insPolicyInsuredPersonFeign.findExistInsuranceSlipByCompanyCode(code);
                if(result){
                    msg.setReturnCode("202");
                    msg.setData("存在投保单，不能删除操作");
                    return msg;
                }
                /**
                 * 根据选中部门ID，查询该部门是否有子集.
                 * 	1.如果存在子集，不执行删除操作，返回提示信息.
                 * 	2.全部不存在，执行删除操作.
                 * */
                InsuranceDept dept = insuranceDeptClient.selectSubsetOrgById(code);//查询部门子集
                if (dept != null) {
                    String insuranceCompanyName = dept.getInsuranceCompanyName();
                    errorList.add(insuranceCompanyName);
                }
            }

            if (errorList.size() < 1) {
                insuranceDeptClient.deleteInsuranceDept(ids);
                msg.setReturnCode("200");
            } else {
                msg.setReturnCode("201");
                msg.setData(errorList);
            }
        } catch (Exception e) {
            msg.setReturnCode("400");
            logger.info("保险组织架构[删除]异常",e);
        }
        return msg;
    }

    /**
     * 构建组织机构树
     */
    //权限管理
    @RequiresPermissions("insCompanyOrg:tree")
    @RequestMapping("/orgTree")
    public String orgTree(Model model) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("orgLevel", "01");
        List<InsuranceDept> list = insuranceDeptClient.findInsurCompanys(paras);
        if (list != null) {
            readTree(list);
        }
        model.addAttribute("companyOrgList", JSON.toJSONString(list));
        return "insuranceDeptPage/InsuranceOrgTree";
    }

    void readTree(List<InsuranceDept> lists) {
        for (InsuranceDept org : lists) {
            Map<String, Object> paras = new HashMap<>();
            paras.put("parentCompanyCode", org.getInsuranceCompanyCode());
            List<InsuranceDept> childList = insuranceDeptClient.findInsurCompanys(paras);
            if (childList != null) {
                org.setOrgChildren(childList);
                readTree(childList);
            }
        }
    }

    /**
     * 查询所有组织机构
     */
    @RequestMapping("/select_all_org")
    @ResponseBody
    public ReturnMsgData selectAllOrg() {
        ReturnMsgData data = new ReturnMsgData();
        try {
            List<Map<String, Object>> list = insuranceDeptClient.selectAllOrg();
            data.setData(list);
            data.setReturnCode("200");
        } catch (RuntimeException e) {
            data.setReturnCode("400");
            e.printStackTrace();
        }
        return data;
    }



    /**
     *@描述 批量导入保险公司
     *@参数
     *@返回值
     *@创建人 qin lina
     *@创建时间 2020/3/5
     */
    @RequestMapping(value = "/importInsuranceDept", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importInsuranceDept(MultipartFile file) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
         Map<String,Object> msg =new HashMap<String,Object>();
         //从shiro的session中取Employee
        Subject subject = SecurityUtils.getSubject();
         Employee employee = (Employee) subject.getPrincipal();
        String employeeNo = employee.getEmployeeNo();

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("employeeId", employee.getEmployeeId());
        params.clear();
        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(new Date());
        params.put("now",now);
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
                //保险信息数据集
                 List<List<String>> insDeptAttRowList = new ArrayList<>();
                Sheet att = sheetMap.get("insurerDept");
                int attNumberOfCells = att.getRow(0).getPhysicalNumberOfCells();
                for (int j = 1; j < att.getPhysicalNumberOfRows(); j++){
                    Row row = att.getRow(j);
                    if (row != null) {
                        List<String> insDeptList = new ArrayList<>();
                        for (int k = 0; k <= attNumberOfCells; k++) {//获取每个单元格
                            insDeptList.add(ImportUtil.cellFormat(row.getCell(k)));
                        }
                         List<String> filtered=insDeptList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(filtered)){
                           insDeptAttRowList.add(insDeptList);
                        }else {
                            logger.info("整行都是空值，忽略======");
                        }

                    }
                }
                if (!CollectionUtils.isEmpty(insDeptAttRowList)){
                    //组装保险公司数据
                    List<Map<String,Object>> insurance_dept_List = new LinkedList<Map<String,Object>>();
                    packInsuranceDept(insDeptAttRowList,insurance_dept_List,params);
                    //转换为json串
                    String insurance_dept_string = JSONObject.toJSONString(insurance_dept_List);
                     Map<String,Object> p = new HashMap<String,Object>();
                    p.put("insurance_dept_string", insurance_dept_string);
                    msg = insuranceDeptClient.insertImportList(p);

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
            logger.error("导入保险公司|异常：{}", e);
        } catch (Exception e) {
            logger.error("导入保险公司|异常：{}", e);
            msg.put("code","400");
    }
        return msg;
}

    private void packInsuranceDept(List<List<String>> insDeptAttRowList, List<Map<String, Object>> insurance_dept_list, Map<String, Object> params) {
        ArrayList<String> companyNameList = new ArrayList<>();
        ArrayList<String> companyCodeList = new ArrayList<>();
        insDeptAttRowList.forEach(insDeptBeanList ->{
             int[] filedindexs = {0,1,4,5,6,8,9};
			  String[] filedNames = {"保险公司名称","保险公司简称","保险公司代码","保险公司财寿标志","保险公司成立日期","公司传真","公司电话"};
              List<String> validateMust = ImportUtil.validateMust(insDeptBeanList, filedindexs, filedNames);
              if (!CollectionUtils.isEmpty(validateMust)){
                  throw new CheckException("请录入必填项："+validateMust.toString());
              }
              if (companyNameList.contains(insDeptBeanList.get(0))){
                  throw new CheckException("导入数据中保险公司名称"+insDeptBeanList.get(0)+"存在重复值,请核查!");
              }
              if (companyCodeList.contains(insDeptBeanList.get(4))){
                  throw new CheckException("导入数据中保险代码"+insDeptBeanList.get(4)+"存在重复值,请核查!");
              }

            boolean result = insuranceDeptClient.findExistOrgByNameOrCode(null, insDeptBeanList.get(0), insDeptBeanList.get(4));
            if (result){
                throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"保险公司代码："+insDeptBeanList.get(4)+"名称或代码已存在");
            }
            Map<String,Object> insDeptMap = Maps.newHashMapWithExpectedSize(15);
            insDeptMap.put("insuranceCompanyName",insDeptBeanList.get(0));//保险公司名称
            insDeptMap.put("insuranceCompanyNameLess",insDeptBeanList.get(1));//保险公司简称
            if (StringUtils.isNotEmpty(insDeptBeanList.get(2))) {
                boolean regflag = insDeptBeanList.get(2).matches(Constants.DATA_FORMAT_LETTER);
                if (!regflag) {
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"保险公司英文名："+insDeptBeanList.get(2)+"不为英文");
                }
            }
            insDeptMap.put("insuranceCompanyEnName",insDeptBeanList.get(2));//保险公司英文名
            if (StringUtils.isNotEmpty(insDeptBeanList.get(3))) {
                boolean regflag1 = insDeptBeanList.get(3).matches(Constants.DATA_FORMAT_LETTER);
                if (!regflag1) {
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"保险公司英文简称："+insDeptBeanList.get(3)+"不为英文");
                }
            }
            insDeptMap.put("insuranceCompanyEnNameLess",insDeptBeanList.get(3));//保险公司英文简称
            insDeptMap.put("insuranceCompanyCode",insDeptBeanList.get(4));//保险公司代码
            if (insDeptBeanList.get(5).equals("0")||insDeptBeanList.get(5).equals("1")) {
                insDeptMap.put("businessType",insDeptBeanList.get(5));//保险公司财寿标志
            }else {
                throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"保险公司财寿标志："+insDeptBeanList.get(5)+"不为可支持保险公司财寿标志");
            }
             if (!DateTimeUtil.isLegalDate(insDeptBeanList.get(6))){
                throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"保险公司成立日期格式不正确,请填写yyyy-MM-dd格式的时间");
            }
            insDeptMap.put("dateOfEstablishment",insDeptBeanList.get(6));//保险公司成立日期
            if (StringUtils.isNotEmpty(insDeptBeanList.get(7))) {
                boolean regflag2 = insDeptBeanList.get(7).matches(Constants.DATA_FORMAT_NUMBER);
                if (!regflag2) {
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"注册资本（万元）："+insDeptBeanList.get(7)+"不为数字");
                }
                if (insDeptBeanList.get(7).length()>10) {
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"注册资本（万元）："+insDeptBeanList.get(7)+"超过10位");
                }
            }


            insDeptMap.put("registeredCapital",insDeptBeanList.get(7));//注册资本（万元）
            boolean regflag3 = insDeptBeanList.get(8).matches(Constants.DATA_FORMAT_FAX);
            if (!regflag3) {
                throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"传真格式："+insDeptBeanList.get(8)+"不为正确传真格式");
            }
            insDeptMap.put("fax",insDeptBeanList.get(8));//公司传真
            insDeptMap.put("tel",insDeptBeanList.get(9));//公司电话
            if(StringUtils.isNotEmpty(insDeptBeanList.get(10))){
                boolean regflag4 = insDeptBeanList.get(10).matches(Constants.DATA_FORMAT_NUMBER);
                if (!regflag4) {
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"邮政编码："+insDeptBeanList.get(10)+"不为数字");
                }
            }
            insDeptMap.put("postCode",insDeptBeanList.get(10));//邮政编码
            String province = insDeptBeanList.get(11);//所在省
            String city = insDeptBeanList.get(12);//所在市
            String county = insDeptBeanList.get(13);//所在区县
            String areaCode = "";
            if(StringUtil.isBlank(province)&&StringUtil.isBlank(city)&&StringUtil.isBlank(county)){
                areaCode = "";
            }else if(!province.equals("")&&!city.equals("")&&!county.equals("")){
                District districtProvince = systemDictFeignClient.findDistrictByDistrict(province);
                if(null==districtProvince){
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"所在省份未查找到");
                }
                District districtCity = systemDictFeignClient.findDistrictByDistrictAndParentId(city, String.valueOf(districtProvince.getDistrictid()));
                if (districtCity == null) {
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"所在市未查找到");
                }
                District districtCounty = systemDictFeignClient.findDistrictByDistrictAndParentId(county, String.valueOf(districtCity.getDistrictid()));
                if (districtCounty == null) {
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"所在区县未查找到");
                }
                areaCode = districtCounty.getSortcode();
            }else if(!province.equals("")&&!city.equals("")){
                District districtProvince = systemDictFeignClient.findDistrictByDistrict(province);
                if(null==districtProvince){
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"所在省份未查找到");
                }
                District districtCity = systemDictFeignClient.findDistrictByDistrictAndParentId(city, String.valueOf(districtProvince.getDistrictid()));
                if (districtCity == null) {
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"所在市未查找到");
                }
                areaCode = districtCity.getSortcode();
            }else if(!province.equals("")){
                District districtProvince = systemDictFeignClient.findDistrictByDistrict(province);
                if(null==districtProvince){
                    throw new CheckException("保险公司名称："+insDeptBeanList.get(0)+"所在省份未查找到");
                }
                areaCode = districtProvince.getSortcode();
            }

            insDeptMap.put("areaCode",areaCode);
            insDeptMap.put("address",insDeptBeanList.get(14));//详细地址
            insDeptMap.put("webSite",insDeptBeanList.get(15));//公司网址
            insDeptMap.put("mainBusiness",insDeptBeanList.get(16));//主营业务
            insDeptMap.put("createdTime", params.get("now"));
            Map<String,Object> emp = (Map<String, Object>) params.get("emp");
            insDeptMap.put("createdBy", emp.get("employeeId"));//创建人
            insDeptMap.put("orgLevel","01");//组织机构级别
            companyCodeList.add(insDeptBeanList.get(4));
            companyNameList.add(insDeptBeanList.get(0));
            insurance_dept_list.add(insDeptMap);
        });
    }



}
