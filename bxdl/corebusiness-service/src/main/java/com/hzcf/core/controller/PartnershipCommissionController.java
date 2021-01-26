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

import com.hzcf.core.service.PartnershipCommissionSetService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.PartnershipCommissionSet;
import com.hzcf.pojo.parameter.PartnershipCommissionSub;

/**
 * 合伙组织机构佣金率管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/partnership_commission")
public class PartnershipCommissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Autowired
    private PartnershipCommissionSetService partnershipCommissionSetService;
    
    /**
     * 分页合伙组织机构佣金率
     * */	
    @RequestMapping("/do_partnership_commission")
    @ResponseBody
    public PageModel doPartnershipCommissionSet(@RequestParam Map<String,Object> params){
    
    return partnershipCommissionSetService.getPartnershipCommissionSetList(params);
    }
    /**
     * 分页查询子产品
     * */	
    @RequestMapping("/do_partnership_commission_sub")
    @ResponseBody
    public PageModel getPartnershipListSub(@RequestParam Map<String,Object> params){
    	
    return partnershipCommissionSetService.getPartnershipListSub(params);
    }
    
    /**
     * 分页查询子产品
     * */	
    @RequestMapping("/do_partnership_commission_sub_edit")
    @ResponseBody
    public PageModel getPartnershipListSubEdit(@RequestParam Map<String,Object> params){
    	
    return partnershipCommissionSetService.getPartnershipListSubEdit(params);
    }
  
    /**
     * 添加合伙组织机构佣金率
     * */
    @RequestMapping("/add_partnership_commission")
    @ResponseBody
    public Long addPartnershipCommissionSet(@RequestBody PartnershipCommissionSet partnershipCommissionSet){
    	
    	partnershipCommissionSetService.addPartnershipCommissionSet(partnershipCommissionSet);
    	return partnershipCommissionSet.getPartnershipId();
    	
    }
    
    /**
     * 添加子产品
     * */
    @RequestMapping("/add_partnership_commission_sub")
    @ResponseBody
    public void addPartnershipCommissionSub(@RequestBody PartnershipCommissionSub partnershipCommissionSub){
    	
    	
    	 partnershipCommissionSetService.addPartnershipCommissionSub(partnershipCommissionSub);
    	
    }
    /**
     * 修改产品
     * */
    @RequestMapping("/update_partnership_commission")
    @ResponseBody
    public void updatePartnershipCommissionSet(@RequestBody PartnershipCommissionSet partnershipCommissionSet){
    	partnershipCommissionSetService.updatePartnershipCommissionSet(partnershipCommissionSet);
    }
    /**
     * 修改子产品产品
     * */
    @RequestMapping("/update_partnership_commission_sub")
    @ResponseBody
    public void updatePartnershipCommissionSub(@RequestBody PartnershipCommissionSub partnershipCommissionSub){
    	partnershipCommissionSetService.updatePartnershipCommissionSub(partnershipCommissionSub);
    }
    /**
     * 查询主表信息
     */
    @RequestMapping("/select_partnership_commission_info")
    @ResponseBody
    public PartnershipCommissionSet selectPartnershipCommissionSetDetail(@RequestParam Map<String,Object> params){
       return partnershipCommissionSetService.selectPartnershipCommissionSetDetail(params);
    }
    
    /**
     * 
     */
    @RequestMapping("/findPartnershipCommissionSet")
    @ResponseBody
    public List<PartnershipCommissionSet> findPartnershipCommissionSet(@RequestParam Map<String,Object> params){
       return partnershipCommissionSetService.findPartnershipCommissionSet(params);
    }
    
    /**
     * 查询子产品信息
     */
    @RequestMapping("/select_partnership_commission_list")
    @ResponseBody
    public List<PartnershipCommissionSub> getPartnershipCommissionSubList(@RequestParam Map<String,Object> params){
       return partnershipCommissionSetService.getPartnershipCommissionSubList(params);
    }
    @RequestMapping("/selectCommissionRate")
    @ResponseBody
    public  Map<String,Object> selectCommissionRate(@RequestParam Map<String, Object> pare){
        return partnershipCommissionSetService.selectCommissionRate(pare);
    }
}
