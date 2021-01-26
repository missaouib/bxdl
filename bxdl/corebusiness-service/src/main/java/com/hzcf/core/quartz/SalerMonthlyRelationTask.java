package com.hzcf.core.quartz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.hzcf.core.constants.Constants;
import com.hzcf.core.service.InsuranceSalesInfoService;
import com.hzcf.core.service.SalerMonthlyRelationService;
import com.hzcf.core.service.SalerRelationChangeService;
import com.hzcf.core.service.SettlementPolicyService;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurance.sales.SalesMoveLogs;
import com.hzcf.pojo.insurancePolicy.SettlementPolicy;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 生成员工-月度-关系定时器，员工关系调整，员工异动
 */
public class SalerMonthlyRelationTask extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SalerMonthlyRelationService salerMonthlyRelationService;
    @Autowired
    SalerRelationChangeService salerRelationChangeService;
    @Autowired
    private InsuranceSalesInfoService insuranceSalesInfoService;


    /**
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        logger.info("生成员工-月度-关系定时器开始执行========");
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            String month = dateFormat.format(calendar.getTime());
            logger.info("待生成{}月的销售人员关系", month);
            //生成上月的销售人员关系
            salerMonthlyRelationService.addSalerMonthlyRelation(month);
            logger.info("开始进行员工关系调整========");
            String relationMonth = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), "yyyy-MM");
            salerRelationChangeService. executeRelationChange(relationMonth);
            logger.info("开始进行员工异动=====");
            insuranceSalesInfoService.salesMove();
            //logger.info("查询出当天需要执行的数量{}", settlementPolicies.size());
        }catch(Exception e){
            logger.error("生成员工-月度-关系定定时器报错：", e);
        }

        logger.info("生成员工-月度-关系定定时器执行结束========");
    }
}
