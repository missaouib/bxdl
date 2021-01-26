package com.hzcf.core.quartz;

import com.hzcf.core.service.InsProtocolCostService;
import com.hzcf.core.service.InsProtocolPromotionService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 协议费用
 *
 */
public class InsProtocolCostTask extends QuartzJobBean{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InsProtocolCostService insProtocolCostService;
	@Autowired
	private InsProtocolPromotionService insProtocolPromotionService;

    

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("协议费用定时器InsProtocolCostTask开始执行===========");
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	addProtocolCost(sdf.format(new Date()), "ALL");
    	logger.info("协议费用定时器InsProtocolCostTask执行结束===========");
	}

	private void addProtocolCost(String date, String type) {
		try {
			if ("SERVICECHARGE".equals(type) || "ALL".equals(type)) {
				// 手续费
				insProtocolCostService.getInsProtocolCost(date, "SERVICECHARGE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if ("RATEADJUST".equals(type) || "ALL".equals(type)) {
				// 费率调节
				insProtocolCostService.getInsProtocolCost(date, "RATEADJUST");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if ("PROMOTION".equals(type) || "ALL".equals(type)) {
				// 业务推动
				insProtocolPromotionService.testCalculatePromoteCost(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
