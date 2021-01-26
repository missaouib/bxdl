package com.hzcf.plantform.controller.quartz;

import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.feign.quartz.JobClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.QuartzJobModel;
import com.hzcf.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/4/20.
 */
@Controller
@RequestMapping(value = "/quartz")
public class QuartzJobController {
     private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobClient jobClient;
    /**
     * 跳转到页面
     */
    @RequestMapping("/to_quartz_job_path")
    public String toInsuranceDeptPath() {
        return "system/quartz/quartzList";
    }

    @RequestMapping("/job_list")
    @ResponseBody
    public DataMsg quartzjobList(HttpServletRequest request, DataMsg dataMsg,String jobName) {
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
       paras.put("jobName",jobName.trim());
        PageModel pageModel =   jobClient.findList(paras);
        List<Map<String, Object>> list = pageModel.getList();
         dataMsg.setTotal(pageModel.getTotalRecords());
		 dataMsg.setRows(list);
		 dataMsg.setMessageCode("200");

         return dataMsg;
   }


    /**
     * 添加任务
     *
     * @param jobClassName   任务类名称
     * @param jobGroupName   任务群组名称
     * @param cronExpression cron表达式
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/addJob")
    public Map<String,Object> addJob(QuartzJobModel jobModel) {
        Map<String, Object> map = new HashMap<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();
            jobModel.setOperator(employeeNo);
           return  jobClient.addJob(jobModel);
        } catch (Exception e) {
            map.put("result",false);
            map.put("msg",e.getMessage());
            logger.info("添加定时任务异常",e);
            e.printStackTrace();
            return map;
        }
    }
    /**
     *@描述  删除定时任务
     *@创建人 qin lina
     *@创建时间 2020/4/21
     */
    @RequestMapping("/deleteJob")
    @ResponseBody
    public Map<String,Object> deleteJob(@RequestParam("jobId") String jobId){
         Map<String, Object> map = new HashMap<>();
          try {
              String[] split = jobId.split(",");
              int[] jobIds = Arrays.asList(split).stream().mapToInt(Integer::parseInt).toArray();
              return jobClient.deleteJob(jobIds);
        } catch (Exception e) {
            map.put("result",false);
            map.put("msg",e.getMessage());
            logger.info("删除定时任务异常",e);
            e.printStackTrace();
            return map;
        }
    }

       /**
     *@描述  修改回显
     *@创建人 qin lina
     *@创建时间 2020/4/21
     */
    @RequestMapping("/getJobById")
    @ResponseBody
    public Map<String, Object> getJobById(@RequestParam("jobId") int jobId){
          try {
              Map<String, Object> map = new HashMap<>();
              QuartzJobModel model = jobClient.getJobById(jobId);
              map.put("model", model);
              return map;
          } catch (Exception e) {
              logger.error("修改回显异常",e);
             e.printStackTrace();
             return null;
        }
    }

    /**
     *@描述  修改定时任务
     *@创建人 qin lina
     *@创建时间 2020/4/21
     */
    @RequestMapping("/updateJob")
    @ResponseBody
    public Map<String,Object> updateJob(QuartzJobModel model){
         Map<String, Object> map = new HashMap<>();
          try {
               Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();
            model.setOperator(employeeNo);
            return jobClient.updateJob(model);
          } catch (Exception e) {
             map.put("result",false);
            map.put("msg",e.getMessage());
            logger.info("修改定时任务异常",e);
            e.printStackTrace();
            return map;
        }
    }

    /**
     *@描述  暂停或恢复任务
     *@创建人 qin lina
     *@创建时间 2020/4/22
     */
    @ResponseBody
    @PostMapping(value = "/pauseJob")
    public Map<String,Object> pauseJob(@RequestParam(value = "jobId") int jobId,@RequestParam("content") String content) {
        Map<String, Object> map = new HashMap<>();
        try {
            String status = "";
            switch (content){
                case Constants.STATE_START_CONTENT :
                    status = QuartzJobModel.STATE_START;
                    break;
                case Constants.STATE_PAUSE_CONTENT:
                    status = QuartzJobModel.STATE_PAUSE;
                    break;
            }
        return jobClient.pauseJob(jobId,status);
        } catch (Exception e) {
            map.put("result",false);
            map.put("msg",e.getMessage());
            logger.info("操作定时任务异常",e);
            e.printStackTrace();

        }
         return map;

    }



}
