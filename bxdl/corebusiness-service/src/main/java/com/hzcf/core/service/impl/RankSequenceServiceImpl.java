package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.RankSequenceMapper;
import com.hzcf.core.service.RankSequenceService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.RankSequence;
@Service
public class RankSequenceServiceImpl implements RankSequenceService {

	@Autowired
    private RankSequenceMapper rankSequenceMapper;

    @Override
    public void addRankSequence(RankSequence rankSequence) {
    	rankSequenceMapper.addRankSequence(rankSequence);
    } 
    
    @Override
    public PageModel getRankSequencePage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getPageSize());
        List<Map<String,Object>> list = rankSequenceMapper.getRankSequencePage(params);
        long size = rankSequenceMapper.getRankSequenceListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

	@Override
	public RankSequence selectById(Map<String, Object> params) {
		RankSequence rankSequence = rankSequenceMapper.selectById(params);
		return rankSequence;
	}

	@Override
	public void updateRankSequence(RankSequence rankSequence) {
		rankSequenceMapper.updateRankSequence(rankSequence);
	}

	@Override
	public List<RankSequence> getRankSequenceList(Map<String, Object> params) {
		return rankSequenceMapper.getRankSequenceList(params);
	}	
	
	
	
}

