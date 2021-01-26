package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.RankSequence;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "corebusiness-service", fallback =FeignDisableHystrixConfiguration.class)
public interface RankSequenceClient {
	
	/**
	 *序列分页
	 * @param RankSequence 
	 * */
	@RequestMapping(value = "/rankSequence/getRankSequencePage",method = RequestMethod.POST)
	PageModel getRankSequencePage(@RequestParam Map<String, Object> params);
	/**
	 *序列列表
	 * @param RankSequence 
	 * */
	@RequestMapping(value = "/rankSequence/getRankSequenceList",method = RequestMethod.POST)
	List<RankSequence> getRankSequenceList(@RequestParam Map<String, Object> params);
	/**
	 *新增序列
	 * @param RankSequence 
	 * */
	@RequestMapping(value = "/rankSequence/addRankSequence",method = RequestMethod.POST)
	void addRankSequence(RankSequence rankSequence);
	/**
	 *修改序列
	 * @param RankSequence 
	 * */
	@RequestMapping(value = "/rankSequence/updateRankSequence",method = RequestMethod.POST)
	void updateRankSequence(RankSequence rankSequence);
	/**
	 *序列明细
	 * @param RankSequence 
	 * */
	@RequestMapping(value = "/rankSequence/selectById",method = RequestMethod.POST)
	RankSequence selectById(@RequestParam Map<String, Object> map);	
}
