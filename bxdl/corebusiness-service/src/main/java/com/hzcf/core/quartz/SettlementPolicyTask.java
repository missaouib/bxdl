package com.hzcf.core.quartz;

import com.hzcf.core.service.SettlementPolicyService;
import com.hzcf.pojo.insurancePolicy.SettlementPolicy;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 可结算佣金保单taks
 */
public class SettlementPolicyTask extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SettlementPolicyService settlementPolicyService;

    /**
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        logger.info("可结算佣金保单定时器开始执行========");
        try{
            executeTask();
        }catch(Exception e){
            logger.error("可结算佣金保单定时器执行报错：", e);
        }

        logger.info("可结算佣金保单定时器结束========");
    }
    private void executeTask() {
        //获取可结算佣金保单集合
        List<SettlementPolicy> settlementPolicies = settlementPolicyService.getSettlementPolicyList();

        if(settlementPolicies == null || settlementPolicies.isEmpty()) {
            logger.info("查询出当天需要执行的数量{}", 0);
            return;
        }
        logger.info("查询出当天需要执行的数量{}", settlementPolicies.size());
        settlementPolicyService.batchAddSettlementPolicy(settlementPolicies);
    }
}
