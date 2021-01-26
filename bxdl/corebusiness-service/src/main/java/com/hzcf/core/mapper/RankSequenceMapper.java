package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.insurance.RankSequence;
import com.hzcf.pojo.insurance.SalesTeamInfo;

/**
 *保险部门管理
 */
public  interface RankSequenceMapper {

	void addRankSequence(RankSequence rankSequence);

	List<Map<String, Object>> getRankSequencePage(Map<String, Object> params);

	long getRankSequenceListSize(Map<String, Object> params);

	void updateRankSequence(RankSequence rankSequence);

	List<RankSequence> getRankSequenceList(Map<String, Object> params);

	RankSequence selectById(Map<String, Object> params);
		
}
