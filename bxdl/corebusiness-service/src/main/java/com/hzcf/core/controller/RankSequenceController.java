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

import com.hzcf.core.service.RankSequenceService;
import com.hzcf.core.service.SalesTeamInfoService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.RankSequence;

/**
 * 职级序列管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/rankSequence")
public class RankSequenceController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private RankSequenceService rankSequenceService;
    
    /**
     * 添加序列
     * */
    @RequestMapping("/addRankSequence")
    @ResponseBody
    public void addRankSequence(@RequestBody RankSequence rankSequence){
    	rankSequenceService.addRankSequence(rankSequence);
    }
    
    /**
     * 修改基础信息
     * */
    @RequestMapping("/updateRankSequence")
    @ResponseBody
    public void updateRankSequence(@RequestBody RankSequence rankSequence){
    	rankSequenceService.updateRankSequence(rankSequence);
    }
    
    /**
     * 分页查询职级序列
     * */
    @RequestMapping("/getRankSequencePage")
    @ResponseBody
    public PageModel getRankSequencePage(@RequestParam Map<String,Object> params){
    	return rankSequenceService.getRankSequencePage(params);
    }
    
    /**
     * 不分页查询职级序列
     * */
    @RequestMapping("/selectById")
    @ResponseBody
    public RankSequence selectById(@RequestParam Map<String,Object> params){
    	return rankSequenceService.selectById(params);
    }    
    
	 /**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/getRankSequenceList")
    @ResponseBody
    public List<RankSequence> getRankSequenceList(@RequestParam Map<String,Object> params){
    	return rankSequenceService.getRankSequenceList(params);
    }
}
