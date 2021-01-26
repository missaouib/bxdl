package com.hzcf.core.config;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.simpl.SimpleJobFactory;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.Properties;

/**
 * Created by qin lina on 2020/4/20.
 */
@Configuration
public class QuartzTimerConfig {

    @Autowired
    private MyJobFactory myJobFactory;
     @Value("${quartz.name}")
    private String quartz_name ;
   /*  @Bean
    public JobFactory jobFactory() {
        JobFactory jobFactory = new SimpleJobFactory();
        return jobFactory;
    }*/

    @Bean(name ="quartzTimer_bxdl" )
    public SchedulerFactoryBean quartzTimer() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
       // Trigger[] triggers ={initQuartzJobTrigger()};
       // schedulerFactoryBean.setTriggers(triggers);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        schedulerFactoryBean.setJobFactory( myJobFactory);
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 延时启动(秒)
        schedulerFactoryBean.setStartupDelay(10);

        return schedulerFactoryBean;
    }
    public Properties quartzProperties(){
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("/"+quartz_name+".properties"));
        try {
            factoryBean.afterPropertiesSet();
            return factoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


     /*
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    @Bean(name = "Scheduler")
    public Scheduler scheduler() {

        return quartzTimer().getScheduler();
    }
}
