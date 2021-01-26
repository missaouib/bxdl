package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.protocol.InsProtocol;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface InsProtocolMapper {

    int deleteByPrimaryKey(Long protocolId);

    int insert(InsProtocol record);

    int insertSelective(InsProtocol record);

    InsProtocol selectByPrimaryKey(Long protocolId);

    int updateByPrimaryKeySelective(InsProtocol record);

    int updateByPrimaryKey(InsProtocol record);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	/**
	 * <p>查询当前生效和指定时间月失效的</p>
	 * 一般查询当前生效的和上个月失效的
	 * @param terminationDate 失效月
	 * @return
	 */
	List<Long> queryValidProtocolId(@Param("terminationDate") Date terminationDate);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	Map<String, Object> checkForDuplicates(InsProtocol protocol);

	long insertSelectiveAndReturnId(InsProtocol protocol);

	Map<String, Object> findProtocolInfoById(Long valueOf);

	Map<String, Object> checkUpdateForDuplicates(InsProtocol protocol);

	List<Map<String, Object>> checkSalesProductIsRepeat(Map<String, Object> map);
	
	List<Map<String, Object>> getEffectOrTerminationProtocol(@Param("nowDate") String nowDate, @Param("flag") Integer flag);
	
	int updateBatchEffectOrTerminationProtocol(@Param("list") List<Map<String, Object>> list, @Param("flag") Integer flag);
}