package com.hzcf.plantform.controller.Insurance;


import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.feign.insurancePolicy.InsPolicyInsuredPersonFeign;
import com.hzcf.plantform.feign.insurancePolicy.SettlementPolicyFeignClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 结算保单Controller
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/settlementPolicy")
public class SettlementPloicyController {

    @Autowired
    SalesOrgInfoClient salesOrgInfoClient;
    @Autowired
    InsPolicyInsuredPersonFeign insPolicyInsuredPersonFeign;
    @Autowired
    EmployeeFeignClient employeeFeignClient;
    @Autowired
    SettlementPolicyFeignClient settlementPolicyFeignClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/toListPath")
    public String toListPath(String type, Model model) throws Exception{

        model.addAttribute("settlementType", type);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date nowTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if(dayOfMonth < 25) { //当月25号之前审核时间为上个月和未来月
            calendar.add(Calendar.MONTH, -1);
        }
        //设置最小审核月份
        String minMonth = dateFormat.format(calendar.getTime());
        model.addAttribute("minMonth", minMonth);
        return "salesCommission/settlementPolicy";
    }

    @RequestMapping(value = "/getSettlementPolicyList",method = {RequestMethod.POST})
    @ResponseBody
    public DataMsg getSettlementPolicyList(HttpServletRequest request, DataMsg dataMsg) {
        String settlementType = request.getParameter("settlementType");
        if(StringUtil.isBlanks(settlementType)) {
            dataMsg.setMessageCode("500");
            return dataMsg;
        }
        try{
            Map<String, Object> params = getParams(request);
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                params.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            } else {
                params.put("pageNo", 0);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                params.put("pageSize", 10);
            }
            //数据权限相关
            String myDeptNo = "";
            if(params.get("myDeptNo") != null){
                myDeptNo = String.valueOf(params.get("myDeptNo"));
            }
            String myAllOrgIds = salesOrgInfoClient.findEmployeeAllOrgs(myDeptNo);
            params.put("myAllOrgIds", myAllOrgIds);

            String auditStatus = params.get("exampleAuditStatus") == null ? null : String.valueOf(params.get("exampleAuditStatus"));

            if("1".equals(settlementType)) {
                if(auditStatus == null) {
                    auditStatus = "0,1,3";
                }
            }else{
                auditStatus = "2";
            }
            params.put("auditStatus" ,auditStatus);
            PageModel model = settlementPolicyFeignClient.searchSettlementPolicyList(params);
            List list = model.getList();
            dataMsg.setTotal(model.getTotalRecords());
            dataMsg.setRows(list);
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("查询结算保单列表异常", e);
        }
        return dataMsg;
    }

    @RequestMapping(value = "/submitAuditResult", method = RequestMethod.POST)
    @ResponseBody
    public DataMsg submitAuditResult(HttpServletRequest request, DataMsg dataMsg) {
        dataMsg.setMessageCode("200");
        String aduitResult = request.getParameter("examine_auditresults");
        String remark = request.getParameter("remark");
        String settlementMonth = request.getParameter("datetimepicker");
        String policyId = request.getParameter("auditPolicyId");
        String oldAduitStatus = request.getParameter("auditStatus");
        Map<String, Object> params = new HashMap<>();
        params.put("aduitResult", aduitResult);
        params.put("settlementMonth", settlementMonth);
        params.put("remark", remark);
        params.put("policyId", policyId);
        params.put("oldAduitStatus", oldAduitStatus);
        logger.info("结算保单审核提交数据={}", params);
        try{
            //取身份信息
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
            params.put("updateBy", employee.getEmployeeId());
            params.put("updateTime", new Date());
            int updateCount = settlementPolicyFeignClient.submitAuditResult(params);
            if(updateCount < 1) {
                dataMsg.setMessageCode("500");
            }

        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("结算保单审核失败", e);
        }
        return dataMsg;
    }


    public Map<String, Object> getParams(HttpServletRequest httpServletRequest) {
        Map<String,Object> paras = new HashMap<String,Object>();

        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        Employee employee = (Employee) subject.getPrincipal();
        employee = employeeFeignClient.getEmployeeById(employee.getEmployeeId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        //通过员工编号查询销售人员id
        Map<String, Object> params = new HashMap<String, Object>();
        String employeeNo = employee.getEmployeeNo();
        //数据权限相关查询条件
        params.put("isAdmin", employee.getEmployeeNo());
        params.put("myDeptNo", employee.getDeptNo());
        params.put("employeeId",employee.getEmployeeId());

        //********************************组装查询参数******************************//

        String exampleInputName1 = httpServletRequest.getParameter("exampleInputName1");
        if (exampleInputName1 != null && !"".equals(exampleInputName1)) { //保单号
            params.put("policyId", exampleInputName1);
        }
        String exampleInputName2 = httpServletRequest.getParameter("exampleInputName2");//保险公司
        if (exampleInputName2 != null && !"".equals(exampleInputName2)) {
            params.put("insuranceCompanyId", exampleInputName2);
        }
        String exampleInputName3 = httpServletRequest.getParameter("exampleInputName3");//产品名称
        if (exampleInputName3 != null && !"".equals(exampleInputName3)) {
            params.put("productName", exampleInputName3);
        }
        String exampleInputName4 = httpServletRequest.getParameter("exampleInputName4");//产品代码
        if (exampleInputName4 != null && !"".equals(exampleInputName4)) {
            params.put("productCode", exampleInputName4);
        }
        String exampleInputName5 = httpServletRequest.getParameter("exampleInputName5");//组织机构
        if (exampleInputName5 != null && !"".equals(exampleInputName5)) {
            //params.put("salesOrgId", exampleInputName5);
            String salesOrgId = exampleInputName5;
            if (StringUtil.isNotBlank(salesOrgId)) {
                String salesAllOrgs = salesOrgInfoClient.findEmployeeAllOrgs(salesOrgInfoClient.getSaleOrgInfoListBySaleOrgIds(salesOrgId).get(0).get("SALES_ORG_CODE").toString());
                params.put("salesAllOrgs", salesAllOrgs);
            }
        }
        String exampleInputName6 = httpServletRequest.getParameter("exampleInputName6");//营销团队
        if (exampleInputName6 != null && !"".equals(exampleInputName6)) {
            params.put("salesTeamId", exampleInputName6);
        }
        String exampleInputName7 = httpServletRequest.getParameter("exampleInputName7");//投保人
        if (exampleInputName7 != null && !"".equals(exampleInputName7)) {
            params.put("iphName", exampleInputName7);
        }
        String exampleInputName8 = httpServletRequest.getParameter("exampleInputName8");//被保人
        if (exampleInputName8 != null && !"".equals(exampleInputName8)) {
            params.put("pipName", exampleInputName8);
        }
        String exampleInputName9 = httpServletRequest.getParameter("exampleInputName9");//员工工号
        if (exampleInputName9 != null && !"".equals(exampleInputName9)) {
            params.put("empNo", exampleInputName9);
        }
        String exampleInputName20 = httpServletRequest.getParameter("exampleInputName20");// 投保日期-开始
        if (StringUtils.isNotBlank(exampleInputName20)) {
            params.put("startInsure", exampleInputName20);
        }
        String exampleInputName21 = httpServletRequest.getParameter("exampleInputName21");// 投保日期-结束
        if (StringUtils.isNotBlank(exampleInputName21)) {
            params.put("endInsure", exampleInputName21);
        }
        String exampleInputName22 = httpServletRequest.getParameter("exampleInputName22");// 生效日期-开始
        if (StringUtils.isNotBlank(exampleInputName22)) {
            params.put("startTakeEffectData", exampleInputName22);
        }
        String exampleInputName23 = httpServletRequest.getParameter("exampleInputName23");// 生效日期-结束
        if (StringUtils.isNotBlank(exampleInputName23)) {
            params.put("endTakeEffectData", exampleInputName23);
        }
        String exampleInputName24 = httpServletRequest.getParameter("exampleInputName24");// 承保日期-开始
        if (StringUtils.isNotBlank(exampleInputName24)) {
            params.put("startUnderwritingData", exampleInputName24);
        }
        String exampleInputName25 = httpServletRequest.getParameter("exampleInputName25");// 承保日期-结束
        if (StringUtils.isNotBlank(exampleInputName25)) {
            params.put("endUnderwritingData", exampleInputName25);
        }
        String exampleAuditStatus = httpServletRequest.getParameter("ex_auditStatus");// 承保日期-结束
        if (StringUtils.isNotBlank(exampleAuditStatus)) {
            params.put("exampleAuditStatus", exampleAuditStatus);
        }
        return params;
    }
}
