package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.insurance.protocol.InsProtocolImage;

public interface InsProtocolImageMapper {

    int deleteByPrimaryKey(Long protocolImageId);

    int insert(InsProtocolImage record);

    int insertSelective(InsProtocolImage record);

    InsProtocolImage selectByPrimaryKey(Long protocolImageId);

    int updateByPrimaryKeySelective(InsProtocolImage record);

    int updateByPrimaryKey(InsProtocolImage record);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
}