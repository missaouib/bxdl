package com.hzcf.core.quartz;

import com.hzcf.core.service.LifeProtocolService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 协议费用
 *
 */
public class LifeProtocolEffectTask extends QuartzJobBean{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LifeProtocolService lifeProcotolService;
    

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) {
		logger.info("LifeProtocolEffectTask Start...");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		lifeProcotolService.changeProtocolStatusTask(date);
		logger.info("LifeProtocolEffectTask End...");
	}
}
