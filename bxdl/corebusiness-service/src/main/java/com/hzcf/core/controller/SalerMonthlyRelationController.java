package com.hzcf.core.controller;

import com.hzcf.core.service.SalerMonthlyRelationService;
import com.hzcf.pojo.insurance.sales.SalerMonthlyRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: liuweichen
 * @Date: 2020/05/18/11:08
 * @Description:
 */
@Controller
@RequestMapping("/salerMonthlyRelation")
public class SalerMonthlyRelationController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalerMonthlyRelationService salerMonthlyRelationService;

    @RequestMapping("/insertSalerMonthlyRelation")
    @ResponseBody
    public int insertSalerMonthlyRelation(@RequestBody SalerMonthlyRelation salerMonthlyRelation){
        return salerMonthlyRelationService.insertSalerMonthlyRelation(salerMonthlyRelation);
    }
}
