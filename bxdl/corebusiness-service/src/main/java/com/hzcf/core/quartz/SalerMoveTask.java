package com.hzcf.core.quartz;

import com.hzcf.core.service.InsuranceSalesInfoService;
import com.hzcf.core.service.SalesTeamLeadersService;
import com.hzcf.pojo.insurance.SalesTeamLeaders;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurance.sales.SalesMoveLogs;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工变化 暂时没用
 * @author yl
 *
 */
public class SalerMoveTask extends QuartzJobBean {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InsuranceSalesInfoService insuranceSalesInfoService;
    
    @Autowired
    private SalesTeamLeadersService salesTeamLeadersService;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("员工变化异动定时器开始执行=================");
		try {
			Map<String, Object> paras = new HashMap<>();
			paras.put("currDateDo","currDateDo");//今日待执行
			paras.put("checkStatus","1");//审核状态：0 待审核  1审核通过
			paras.put("changeFlag","0");//是否执行
			List<SalesMoveLogs> smls = insuranceSalesInfoService.getSalesMoveList(paras);
			if(smls!=null && smls.size()>0){
				for(SalesMoveLogs sml:smls){
					Long insuranceSalerId = sml.getSalerId();
					paras.clear();
					paras.put("insuranceSalesId",insuranceSalerId);
					InsuranceSalesInfo isi = insuranceSalesInfoService.selectById(paras);
					isi.setSalesOrgId(Long.valueOf(sml.getNextOrgId()));
					isi.setSalesTeamId(Long.valueOf(sml.getNextTeamId()));
					isi.setSalesGradeId(Long.valueOf(sml.getNextSalesGradeId()));
					isi.setRankSequenceId(Long.valueOf(sml.getNextRankSeqId()));
					//这个地方再人员关系变更的时候  育成人改为上级领导  但是sql有问题 字段不为null 才修改
					//isi.setYcSalesId(isi.getSjSalesId());
					 boolean isNoticy = true;
					insuranceSalesInfoService.updateInsuranceSales(isi,isNoticy);

					sml.setChangeFlag("1");
					sml.setUpdatedTime(new Date());
					insuranceSalesInfoService.updateSalerMove(sml);
				}
			}

			paras.clear();
			paras.put("leaderStatus","0");
			paras.put("currDateDo","currDateDo");//今日待执行
			List<SalesTeamLeaders> havNum = salesTeamLeadersService.getSalesTeamLeadersList(paras);
			if(havNum!=null && havNum.size()>0){
				for(SalesTeamLeaders stl:havNum){
					stl.setLeaderStatus("1");
					salesTeamLeadersService.updateSalesTeamLeaders(stl);
				}
			}
		}catch (Exception e) {
			logger.error("员工变化异动定时器异常："+e.getMessage());
			e.printStackTrace();
		}
		logger.info("员工变化异动定时器执行结束=================");
	}
}
