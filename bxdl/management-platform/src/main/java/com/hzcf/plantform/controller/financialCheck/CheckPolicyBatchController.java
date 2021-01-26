package com.hzcf.plantform.controller.financialCheck;

import cn.hutool.core.collection.CollUtil;
import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.financialCheck.CheckPolicyBatchClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.financialCheck.CheckPolicyBatch;
import com.hzcf.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * 对账批次 控制器
 * Created by qin lina on 2020/12/10.
 */
@Controller
@RequestMapping("check_policy_batch")
public class CheckPolicyBatchController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CheckPolicyBatchClient checkPolicyBatchClient;
    @Autowired
    private EmployeeFeignClient employeeFeignClient;
      /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("/to_check_policy_batch_page")
    @RequiresPermissions("checkPolicyBatch:list")
    public String toInspolicyBatchPage(){
        return "financialCheck/CheckPolicyBatchList";
    }

    /**
     * 跳转到新增批次页面
     * @return
     */
     @RequestMapping("/to_add_batch_path")
    @RequiresPermissions("checkPolicyBatch:add")
    public String toAddBatchPage(Model model){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        model.addAttribute("checkNum",newDate+result);
         return "financialCheck/CheckPolicyBatchAdd";
    }

    @RequestMapping(value = "/to_batch_detail_path")
     @RequiresPermissions("checkPolicyBatchDetail:list")
    public String toBatchDetailPath(HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        List<CheckPolicyBatch> batchByCondition = checkPolicyBatchClient.getCheckPolicyBatchByCondition(map);
        model.addAttribute("checkPolicyBatch",batchByCondition.get(0));
        return "financialCheck/CheckPolicyBatchDetail";
    }

     /**
     * 列表数据分页展示
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getCheckPolicyBatchPage")
    @ResponseBody
    public DataMsg getCheckPolicyBatchPage(HttpServletRequest request, DataMsg dataMsg){
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
		       String checkMonth = request.getParameter("checkMonth");
		       if (StringUtil.isNotBlank(checkMonth)) {
		    	   paras.put("checkMonth",checkMonth);
		       }
             String salesOrgId = request.getParameter("salesOrgId");
             if (StringUtil.isNotBlank(salesOrgId)) {
			   paras.put("salesOrgId",salesOrgId);
		   }

            String companyOrgId = request.getParameter("companyOrgId");
            if (StringUtil.isNotBlank(companyOrgId)) {
                paras.put("companyOrgId", companyOrgId);
            }
            String minCreateTime = request.getParameter("minCreateTime");
            if (StringUtil.isNotBlank(minCreateTime)) {
                paras.put("minCreateTime", minCreateTime);
            }
             String maxCreateTime = request.getParameter("maxCreateTime");
            if (StringUtil.isNotBlank(maxCreateTime)) {
                paras.put("maxCreateTime", maxCreateTime);
            }
             String batchType = request.getParameter("batchType");
            if (StringUtil.isNotBlank(batchType)) {
                paras.put("batchType", batchType);
            }
             String status = request.getParameter("status");
            if (StringUtil.isNotBlank(status)) {
                paras.put("status", status);
            }
              String batchNum = request.getParameter("batchNum");
            if (StringUtil.isNotBlank(batchNum)) {
                paras.put("batchNum", batchNum);
            }
              String batchName = request.getParameter("batchName");
            if (StringUtil.isNotBlank(batchName)) {
                paras.put("batchName", batchName);
            }

             //数据权限相关查询条件
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            paras.put("isAdmin", employeeShiro.getEmployeeNo());
            paras.put("myDeptNo", employeeShiro.getDeptNo());


            PageModel pageModel = checkPolicyBatchClient.getCheckPolicyBatchPage(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("财务对账批次列表展示异常",e);
        }
        return dataMsg;
    }

    @RequestMapping("/addCheckPolicyBatch")
    @ResponseBody
    public DataMsg addCheckPolicyBatch(CheckPolicyBatch checkPolicyBatch,DataMsg dataMsg){
        //查询批次号是否重复
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("batchName",checkPolicyBatch.getBatchName());
            List<CheckPolicyBatch> checkPolicyBatcheName= checkPolicyBatchClient.getCheckPolicyBatchByCondition(map);
            if (CollUtil.isNotEmpty(checkPolicyBatcheName)){
                 dataMsg.setMessageCode("300");
                 dataMsg.setData("批次名称已存在!");
                 return dataMsg;
            }
            map.put("batchNum",checkPolicyBatch.getBatchNum());
            List<CheckPolicyBatch> checkPolicyBatcheNum = checkPolicyBatchClient.getCheckPolicyBatchByCondition(map);
            if (CollUtil.isNotEmpty(checkPolicyBatcheNum)){
                 dataMsg.setMessageCode("300");
                 dataMsg.setData("批次号已存在!");
                 return dataMsg;
            }
            Subject subject = SecurityUtils.getSubject();
            Employee employeeShiro = (Employee) subject.getPrincipal();
            employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
            checkPolicyBatch.setBatchType(Constants.BATCH_TYPE_0);
            checkPolicyBatch.setStatus(Constants.BATCH_STATUS_0);
            checkPolicyBatch.setCreateBy(employeeShiro.getEmployeeId().toString());
            checkPolicyBatch.setCreateByName(employeeShiro.getName());
            checkPolicyBatchClient.addCheckPolicyBatch(checkPolicyBatch);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
           dataMsg.setMessageCode("400");
           dataMsg.setData(e.getMessage());
           logger.error("保存对账批次异常",e);
        }
         return dataMsg;
    }
}
