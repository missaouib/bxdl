package com.hzcf.plantform.controller.Insurance;

import com.hzcf.plantform.feign.insurance.InsuranceSalesInfoClient;
import com.hzcf.plantform.feign.insurance.SalesTeamLeadersClient;
import com.hzcf.pojo.insurance.SalesTeamLeaders;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurance.sales.SalesMoveLogs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 员工变化
 * @author yl
 *
 */
@Component
public class SalerMoveTask {

    @Autowired
    private InsuranceSalesInfoClient insuranceSalesInfoClient; 
    
    @Autowired
    private SalesTeamLeadersClient salesTeamLeadersClient; 
	
	/**
	 * 添加
	 * @param(salesDaysCommission)
	 * @return
	 */
	//@Scheduled(cron = "0 5 0 * * ?")
	@ResponseBody
	public boolean addSalesDaysCommission(){
		try {
			Map<String, Object> paras = new HashMap<>();
			paras.put("currDateDo","currDateDo");//今日待执行
			paras.put("checkStatus","1");//审核状态：0 待审核  1审核通过
			paras.put("changeFlag","0");//是否执行
			List<SalesMoveLogs> smls = insuranceSalesInfoClient.getSalesMoveList(paras);
			if(smls!=null && smls.size()>0){
				for(SalesMoveLogs sml:smls){
					Long insuranceSalerId = sml.getSalerId();
					paras.clear();
					paras.put("insuranceSalesId",insuranceSalerId);
					InsuranceSalesInfo isi = insuranceSalesInfoClient.selectById(paras);
					isi.setSalesOrgId(Long.valueOf(sml.getNextOrgId()));
					isi.setSalesTeamId(Long.valueOf(sml.getNextTeamId()));
					isi.setSalesGradeId(Long.valueOf(sml.getNextSalesGradeId()));
					isi.setRankSequenceId(Long.valueOf(sml.getNextRankSeqId()));
					//这个地方再人员关系变更的时候  育成人改为上级领导  但是sql有问题 字段不为null 才修改
					isi.setYcSalesId(isi.getSjSalesId());
					 boolean isNoticy = true;
					insuranceSalesInfoClient.updateInsuranceSales(isi,isNoticy);
					
					sml.setChangeFlag("1");
					sml.setUpdatedTime(new Date());
					insuranceSalesInfoClient.updateSalerMove(sml);
				}		
			}
			
			paras.clear();
			paras.put("leaderStatus","0");
			paras.put("currDateDo","currDateDo");//今日待执行
			List<SalesTeamLeaders> havNum = salesTeamLeadersClient.getSalesTeamLeadersList(paras);
			if(havNum!=null && havNum.size()>0){
				for(SalesTeamLeaders stl:havNum){
					stl.setLeaderStatus("1");
					salesTeamLeadersClient.updateSalesTeamLeaders(stl);
				}
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
