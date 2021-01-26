package com.hzcf.plantform.controller.protocol;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hzcf.plantform.feign.protocol.LifeProcotolFeignClient;

/**
 * 协议费用
 *
 */
@Component
public class InsProtocolCostTask {

	@Autowired
	private LifeProcotolFeignClient lifeProcotolFeignClient;
    
    //@Scheduled(cron = "0 0 0 20 * ? ")
	public void insProtocolCostStart() {
    	System.out.println("InsProtocolCostTask Start...");
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	lifeProcotolFeignClient.addProtocolCost(sdf.format(new Date()), "ALL");
    	System.out.println("InsProtocolCostTask End...");
	}
}
