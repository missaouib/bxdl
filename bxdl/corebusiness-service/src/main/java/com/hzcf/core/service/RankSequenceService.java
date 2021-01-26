package com.hzcf.core.service;
import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.RankSequence;

public interface RankSequenceService{

	void addRankSequence(RankSequence rankSequence);

	void updateRankSequence(RankSequence rankSequence);

	PageModel getRankSequencePage(Map<String, Object> params);

	RankSequence selectById(Map<String, Object> params);

	List<RankSequence> getRankSequenceList(Map<String, Object> params);
   
}
