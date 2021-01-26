package com.hzcf.core.controller;

import com.hzcf.core.service.QuartzJobService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.basic.QuartzJobModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qin lina on 2020/4/20.
 */
@Controller
@RequestMapping("/quartzJob")
public class QuartzJobController {
    @Autowired
    private QuartzJobService quartzJobService;

    @RequestMapping("/findList")
    @ResponseBody
    public PageModel findList(@RequestParam Map<String,Object> params){

        return quartzJobService.findList(params);
    }

    @RequestMapping("/addJob")
    @ResponseBody
    public Map<String ,Object> addJob (@RequestBody QuartzJobModel jobModel) {
        Map<String, Object> map = new HashMap<>();
        try {
            quartzJobService.addJob(jobModel);
            map.put("result",true);
            return map;
        } catch (Exception e) {
             map.put("result",false);
             map.put("msg",e.getMessage());
             e.printStackTrace();
             return map;
        }
    }



    @RequestMapping("/deleteJob")
    @ResponseBody
    public Map<String, Object> deleteJob (@RequestParam("jobIds") int[] jobIds) {
        Map<String, Object> map = new HashMap<>();
        try {
            quartzJobService.deleteJob(jobIds);
            map.put("result",true);
            return map;
        } catch (Exception e) {
             map.put("result",false);
             map.put("msg",e.getMessage());
             e.printStackTrace();
             return map;
        }
    }


     @RequestMapping("/getJobById")
    @ResponseBody
    public QuartzJobModel getJobById (@RequestParam(value = "jobId") int jobId) throws Exception{
        return  quartzJobService.getJobById(jobId);
    }


    @RequestMapping("/updateJob")
    @ResponseBody
    public  Map<String, Object> updateJob (@RequestBody QuartzJobModel jobModel){
        Map<String, Object> map = new HashMap<>();
        try {
            quartzJobService.updateJob(jobModel);
            map.put("result",true);
            return map;
        } catch (Exception e) {
             map.put("result",false);
             map.put("msg",e.getMessage());
             e.printStackTrace();
             return map;
        }
    }


    @RequestMapping("/pauseJob")
    @ResponseBody
    public  Map<String, Object> pauseJob (@RequestParam("jobId") int jobId,@RequestParam("status") String status) {
         Map<String, Object> map = new HashMap<>();
        try {
            quartzJobService.pauseJob(jobId,status);
             map.put("result",true);
            return map;
        } catch (Exception e) {
             map.put("result",false);
             map.put("msg",e.getMessage());
             e.printStackTrace();
             return map;
        }
    }


}
