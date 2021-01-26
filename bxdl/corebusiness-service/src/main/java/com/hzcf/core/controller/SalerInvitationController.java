package com.hzcf.core.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.SalerInvitationService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalerInvitation;

/**
 * 职级序列管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/salerInvitation")
public class SalerInvitationController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SalerInvitationService salerInvitationService;
    
    /**
     * 添加序列
     * */
    @RequestMapping("/addSalerInvitation")
    @ResponseBody
    public void addSalerInvitation(@RequestBody SalerInvitation salerInvitation){
    	salerInvitationService.addSalerInvitation(salerInvitation);
    }
    
    /**
     * 修改基础信息
     * */
    @RequestMapping("/updateSalerInvitation")
    @ResponseBody
    public void updateSalerInvitation(@RequestBody SalerInvitation salerInvitation){
    	salerInvitationService.updateSalerInvitation(salerInvitation);
    }
    
    /**
     * 分页查询职级序列
     * */
    @RequestMapping("/getSalerInvitationPage")
    @ResponseBody
    public PageModel getSalerInvitationPage(@RequestParam Map<String,Object> params){
    	return salerInvitationService.getSalerInvitationPage(params);
    }
    
    /**
     * 不分页查询职级序列
     * */
    @RequestMapping("/selectById")
    @ResponseBody
    public SalerInvitation selectById(@RequestParam Map<String,Object> params){
    	return salerInvitationService.selectById(params);
    }    
}
