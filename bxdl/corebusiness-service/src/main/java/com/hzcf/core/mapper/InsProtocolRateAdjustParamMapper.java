package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjustParam;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjustParamExample;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjustParamWithBLOBs;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface InsProtocolRateAdjustParamMapper {
	   long countByExample(InsProtocolRateAdjustParamExample example);

	    int deleteByExample(InsProtocolRateAdjustParamExample example);

	    int deleteByPrimaryKey(Long adjustParamId);

	    int insert(InsProtocolRateAdjustParamWithBLOBs record);

	    int insertSelective(InsProtocolRateAdjustParamWithBLOBs record);

	    List<InsProtocolRateAdjustParamWithBLOBs> selectByExampleWithBLOBs(InsProtocolRateAdjustParamExample example);

	    List<InsProtocolRateAdjustParam> selectByExample(InsProtocolRateAdjustParamExample example);

	    InsProtocolRateAdjustParamWithBLOBs selectByPrimaryKey(Long adjustParamId);

	    int updateByExampleSelective(@Param("record") InsProtocolRateAdjustParamWithBLOBs record, @Param("example") InsProtocolRateAdjustParamExample example);

	    int updateByExampleWithBLOBs(@Param("record") InsProtocolRateAdjustParamWithBLOBs record, @Param("example") InsProtocolRateAdjustParamExample example);

	    int updateByExample(@Param("record") InsProtocolRateAdjustParam record, @Param("example") InsProtocolRateAdjustParamExample example);

	    int updateByPrimaryKeySelective(InsProtocolRateAdjustParamWithBLOBs record);

	    int updateByPrimaryKeyWithBLOBs(InsProtocolRateAdjustParamWithBLOBs record);

	    int updateByPrimaryKey(InsProtocolRateAdjustParam record);

		void insertBatch(List<InsProtocolRateAdjustParamWithBLOBs> batchList);
	
		List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
	
		Long findAllByPageCount(Map<String, Object> paramsCondition);
	
		Map<String, Object> findAdjustByPidAndAid(Map<String, Object> map);
	
		void deleteAdjustByPidAndAid(Map<String, Object> map);

		Map<String, Object> findParamById(Long valueOf);

		List<Map<String, Object>> getAdjustIsRepeat(Map<String, Object> map);

}