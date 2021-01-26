package com.hzcf.core.quartz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.hzcf.core.constants.Constants;
import com.hzcf.core.service.SalesDaysCommissionService;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.insurance.sales.SalesMonthlyCommission;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigDecimal;
import java.util.*;

/**
 * 佣金发放定时器  每月25日执行上个结算月的数据。
 * Created by qin lina on 2020/5/6.
 */
public class SalesMonthCommissionTask extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(SalesMonthCommissionTask.class);
    @Autowired
    private SalesDaysCommissionService salesDaysCommissionService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("佣金发放定时器开始执行==========");
        //首先判断 可结算列表中是否有已审核未结算的保单 如果有请先执行佣金明细定时器
        /*查询审核通过的未结算的上个月保单*/
        Map<String, Object> params = new HashMap<>();
        String last_month = DateUtil.format(DateUtil.offsetMonth(DateUtil.date(), -1), "yyyy-MM");
        //审核通过
        params.put("auditStatus", Constants.AUDIT_STATUS_1);
        //未结算
        params.put("settlementStatus", Constants.SETTLEMENT_STATUS_0);

        //TODO:从已审核可计算列表中获取结算月为上月和本月的未结算的保单信息（需要的数据为规模保费，结算月，产品编码，销售组织机构）
        //结算月为上月
        params.put("settlementMonth", last_month);
        List<Object> last_commissionData = salesDaysCommissionService.findSettleableMapList(params);
        if (CollUtil.isNotEmpty(last_commissionData)){
            logger.info("存在已审核通过还未结算的保单，请先执行佣金明细定时器=========");
            return;
        }else {
            //判断定时器是否执行过
           /* params.clear();
            params.put("commissionMonth",last_month);
            List<Map<String, Object>> salesMonthlyCommissionList =salesDaysCommissionService.findMonthCommission(params);
            if (CollUtil.isNotEmpty(salesMonthlyCommissionList)){
                logger.info("佣金发放定时器已执行，上月的佣金审核发放数据已经产生，请勿重复执行==========");
                return;
            }*/
            //从佣金明细表中找到结算月为上个月的明细
            params.clear();
            params.put("settlementMonth",last_month);
             params.put("settlementStatus",Constants.SETTLEMENT_STATUS_0);
            List<SalesDaysCommission> salesDaysCommissionList =salesDaysCommissionService.findDaysCommission(params);
            List<SalesMonthlyCommission> smcs = new ArrayList<>();
            salesDaysCommissionList.forEach(salesDaysCommission -> {
                SalesMonthlyCommission smc = new SalesMonthlyCommission();
                smc.setSalerId(salesDaysCommission.getSalerId());
                smc.setSalesOrgId(salesDaysCommission.getSalesOrgId());
                smc.setSalesTeamId(salesDaysCommission.getSalesTeamId());
                smc.setSalerGrade(salesDaysCommission.getSalerGrade());
                smc.setCommissionMonth(salesDaysCommission.getSettlementMonth());
                smc.setTotalCommission(salesDaysCommission.getTotalCommission());
                smc.setCutCommission(BigDecimal.valueOf(0.00));
                smc.setCutReason("");
                smc.setFreezingTaxes(BigDecimal.valueOf(0.00));
                smc.setAfterTaxCommission(salesDaysCommission.getTotalCommission().subtract(smc.getCutCommission()).subtract(smc.getFreezingTaxes()));
                smc.setCommissionStatus(Constants.COMMISSION_STATUS_0);
                smc.setCheckMark("");
                smc.setInitialAnnualCommission(salesDaysCommission.getInitialAnnualCommission());
                smc.setExhibitionAllowance(salesDaysCommission.getExhibitionAllowance());
                smc.setIncreaseAllowance(salesDaysCommission.getIncreaseAllowance());
                smc.setGroupRunAllowance(salesDaysCommission.getGroupRunAllowance());
                smc.setDirectorCultureAward(salesDaysCommission.getDirectorCultureAward());
                smc.setDepRunAllowance(salesDaysCommission.getDepRunAllowance());
                smc.setMinisterCultureAward(salesDaysCommission.getMinisterCultureAward());
                smc.setChiefInspectorAllowance(salesDaysCommission.getChiefInspectorAllowance());
                smc.setCreatedTime(new Date());
                smcs.add(smc);
            });
            salesDaysCommissionService.addSalesMonthlyCommission(smcs,salesDaysCommissionList);
        }

          logger.info("佣金发放定时器执行完毕==========");
    }
}
