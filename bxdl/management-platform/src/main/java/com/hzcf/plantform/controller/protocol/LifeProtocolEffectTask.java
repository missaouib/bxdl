package com.hzcf.plantform.controller.protocol;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hzcf.plantform.feign.protocol.LifeProcotolFeignClient;

/**
 * 协议费用
 *
 */
@Component
public class LifeProtocolEffectTask {

	@Autowired
	private LifeProcotolFeignClient lifeProcotolFeignClient;
    
	//@Scheduled(cron = "0 0 2 * * ? ")
	public void changeProtocolEffectTask() {
		System.out.println("LifeProtocolEffectTask Start...");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		lifeProcotolFeignClient.changeLifeProtocolEffect(date);
		System.out.println("LifeProtocolEffectTask End...");
	}
}
