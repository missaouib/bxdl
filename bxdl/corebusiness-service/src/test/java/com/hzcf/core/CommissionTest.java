package com.hzcf.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.hzcf.CorebusinessServiceApplication;
import com.hzcf.core.quartz.SalesDaysCommissionTask;
import com.hzcf.core.service.ExhibitionAllowanceService;
import com.hzcf.core.service.SalerMonthlyRelationService;
import com.hzcf.core.service.SalerRelationChangeService;
import com.hzcf.core.service.SalesDaysCommissionService;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.parameter.ExhibitionAllowance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobExecutionContextImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by qin lina on 2020/5/8.
 */
@SpringBootTest(classes = CorebusinessServiceApplication.class)
@RunWith(SpringRunner.class)
public class CommissionTest {
    @Autowired
    private SalesDaysCommissionService salesDaysCommissionService;
    @Autowired
    private SalerRelationChangeService salerRelationChangeService;
    @Autowired
    private SalerMonthlyRelationService salerMonthlyRelationService;
    @Autowired
    private ExhibitionAllowanceService exhibitionAllowanceService;
    @Autowired

    @Test
    public void test1() throws JobExecutionException {

    }


    public static void main(String[] args) {
      
        Integer a = null;
        Integer b = null;
        Integer c = a==null?b:a.intValue();
        System.out.println(c);

    }
}
