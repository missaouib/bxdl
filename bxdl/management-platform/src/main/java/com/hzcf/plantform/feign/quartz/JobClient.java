package com.hzcf.plantform.feign.quartz;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.QuartzJobModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by qin lina on 2020/4/20.
 */
@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface JobClient {
     /**
     * 添加任务
     *
     * @param scheduler      Scheduler的实例
     * @param jobClassName   任务类名称
     * @param jobGroupName   任务群组名称
     * @param cronExpression cron表达式
     * @throws Exception
     */
     @RequestMapping(value = "/quartzJob/addJob", method = RequestMethod.POST)
    Map<String,Object> addJob( @RequestBody QuartzJobModel jobModel);

    /**
     * 暂停或重启任务
     *
     * @param scheduler    Scheduler的实例
     * @param jobClassName 任务类名称
     * @param jobGroupName 任务群组名称
     * @throws Exception
     */
      @RequestMapping(value = "/quartzJob/pauseJob", method = RequestMethod.POST)
    Map<String,Object> pauseJob( @RequestParam("jobId") int jobId, @RequestParam("status") String status);


    /**
     * 删除任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
     @RequestMapping(value = "/quartzJob/deleteJob", method = RequestMethod.POST)
    Map<String,Object> deleteJob( @RequestParam("jobIds") int[] jobIds);

    /**
     * 获取所有任务
     *
     * @return
     */
     @RequestMapping(value = "/quartzJob/findList", method = RequestMethod.POST)
    PageModel findList(@RequestParam Map<String, Object> paras);

    @RequestMapping(value = "/quartzJob/getJobById", method = RequestMethod.POST)
    QuartzJobModel getJobById(@RequestParam("jobId") int jobId);

    @RequestMapping(value = "/quartzJob/updateJob", method = RequestMethod.POST)
    Map<String,Object> updateJob(@RequestBody QuartzJobModel model);
}
