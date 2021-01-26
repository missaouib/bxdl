package com.hzcf.core.quartz;

import com.hzcf.core.service.HKDataAccessService;
import com.hzcf.core.service.InsProtocolCostService;
import com.hzcf.core.service.InsProtocolPromotionService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 汇康数据打通定时器
 *
 */
public class HKDataAccessTask extends QuartzJobBean{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
	HKDataAccessService hkDataAccessService;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("汇康数据打通定时器HKDataAccessTask开始执行===========");
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	hKDataAccess();
    	logger.info("汇康数据打通定时器HKDataAccessTask执行结束===========");
	}
	//实现方法
	private void hKDataAccess() {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("type", "1");
			hkDataAccessService.hKSaleOrgsDataAccess(params);
		}catch (Exception e){
			logger.error("机构数据同步异常", e);
		}

		try {
			Map<String, Object> params = new HashMap<>();
			params.put("type", "0");
			hkDataAccessService.hKSalersDataAccess(params);
		}catch (Exception e){
			logger.error("销售人员同步异常", e);
		}



	}


}
