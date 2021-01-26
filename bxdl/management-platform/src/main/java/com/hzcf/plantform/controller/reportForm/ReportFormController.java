package com.hzcf.plantform.controller.reportForm;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.exception.CheckException;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.ReportFormClient;
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
@RequestMapping("/reportForm")
public class ReportFormController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReportFormClient reportFormClient;

    @Autowired
    private EmployeeFeignClient employeeFeignClient;

    /**
     * 跳转到页面
     */
    @RequestMapping("/toSettleRequireIndexPage")
    public String toSettleRequireIndexPage() {
        return "reportForm/SettleRequireIndexList";
    }

    @RequestMapping("/getSettleRequireIndexData")
    @ResponseBody
    public DataMsg getSettleRequireIndexData(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> paras = new HashMap<>();

            int pageNo = StringUtil.isNotBlank(request.getParameter("pageNo")) ? Integer.valueOf(request.getParameter("pageNo")) : 1;
            paras.put("pageNo",pageNo);
            int pageSize = StringUtil.isNotBlank(request.getParameter("pageSize")) ? Integer.valueOf(request.getParameter("pageSize")) : 10;
            paras.put("pageSize",pageSize);

            paras.put("salesOrgId", request.getParameter("salesOrgId"));
            paras.put("companyOrgId", request.getParameter("companyOrgId"));
            paras.put("settleMonth", request.getParameter("settleMonth"));

            // 组织机构数据权限处理
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());

            PageModel pageModel = reportFormClient.getSettleRequireIndexData(paras);

            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("报表-结算需求指标，查询异常",e);
        }

        return dataMsg;
    }

}
