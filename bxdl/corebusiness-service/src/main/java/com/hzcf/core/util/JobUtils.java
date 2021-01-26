package com.hzcf.core.util;

import com.hzcf.pojo.basic.QuartzJobModel;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * Created by qin lina on 2020/4/20.
 */
@Service
public class JobUtils {
    @Autowired
     @Qualifier("Scheduler")
     private Scheduler scheduler;

    public static String QUARTZ_TIMER_GROUP = "bxdl-timerGroup";


     public  QuartzJobBean getClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return (QuartzJobBean) clazz.newInstance();
    }
     /**
      * 新建一个任务
      *
      */
     public  void addJob(QuartzJobModel jobModel) throws Exception  {
         if (!CronExpression.isValidExpression(jobModel.getCron())) {
             throw new SchedulerException("Illegal cron expression");
         }
         JobDetail jobDetail = JobBuilder.newJob(this.getClass(jobModel.getJobClassName()).getClass())
                 .withIdentity(jobModel.getJobName(), QUARTZ_TIMER_GROUP)
                 .build();
         //表达式调度构建器(即任务执行的时间)
         // CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(jobModel.getCron());
         //表达式调度构建器(即任务执行的时间,不立即执行)
         CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(jobModel.getCron()).withMisfireHandlingInstructionDoNothing();
         //按新的cronExpression表达式构建一个新的trigger
         CronTrigger trigger = TriggerBuilder.newTrigger()
                 .withIdentity(jobModel.getJobName(), QUARTZ_TIMER_GROUP)
                 .withSchedule(builder)
                 .build();
         // 配置scheduler相关参数
         scheduler.scheduleJob(jobDetail, trigger);

            /*//传递参数
            if(appQuartz.getInvokeParam()!=null && !"".equals(appQuartz.getInvokeParam())) {
                trigger.getJobDataMap().put("invokeParam",appQuartz.getInvokeParam());
            }*/
        }
         /**
          * 获取Job状态
          * @param jobName
          * @param jobGroup
          * @return
          * @throws SchedulerException
          */
         public String getJobState(String jobName, String jobGroup) throws SchedulerException {
             TriggerKey triggerKey = new TriggerKey(jobName, jobGroup);
             return scheduler.getTriggerState(triggerKey).name();
           }

         //暂停所有任务
         public void pauseAllJob() throws SchedulerException {
             scheduler.pauseAll();
          }

        //暂停任务
        public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null) {
                throw new SchedulerException("jobKey is not exists");
            }else {
                 scheduler.pauseJob(jobKey);
            }

        }

        //恢复所有任务
        public void resumeAllJob() throws SchedulerException {
            scheduler.resumeAll();
        }

        // 恢复某个任务
        public void resumeJob(String jobName, String jobGroup) throws SchedulerException {

            JobKey jobKey = new JobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null) {
                throw new SchedulerException("jobKey is not exists");
            }else {
                scheduler.resumeJob(jobKey);
            }
        }

        //删除某个任务
        public void   deleteJob(QuartzJobModel jobModel) throws SchedulerException {
            JobKey jobKey = new JobKey(jobModel.getJobName(), QUARTZ_TIMER_GROUP);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null ) {
                throw new SchedulerException("jobDetail is null");
            }else if(!scheduler.checkExists(jobKey)) {
                  throw new SchedulerException("jobKey is null");
            }else {
                 scheduler.deleteJob(jobKey);
            }

        }

        //修改任务
        public void  modifyJob(QuartzJobModel jobModel) throws SchedulerException {
            if (!CronExpression.isValidExpression(jobModel.getCron())) {
                 throw new SchedulerException("Illegal cron expression");
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobModel.getJobName(),QUARTZ_TIMER_GROUP);
            JobKey jobKey = new JobKey(jobModel.getJobName(),QUARTZ_TIMER_GROUP);
            if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                //表达式调度构建器,不立即执行
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobModel.getCron()).withMisfireHandlingInstructionDoNothing();
                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }else {
                 throw new SchedulerException("job or trigger not exists");
            }

        }
}
