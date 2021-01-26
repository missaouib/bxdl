package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.pojo.insurance.protocol.InsServiceChargeProduct;

public interface InsServiceChargeProductMapper {

    int deleteByPrimaryKey(Long serviceProductId);

    int insert(InsServiceChargeProduct record);

    int insertSelective(InsServiceChargeProduct record);

    InsServiceChargeProduct selectByPrimaryKey(Long serviceProductId);

    int updateByPrimaryKeySelective(InsServiceChargeProduct record);

    int updateByPrimaryKey(InsServiceChargeProduct record);

	void insertServiceProductBatch(List<InsServiceChargeProduct> list);

	Map<String, Object> findChargeByProtocolId(Long protocolId);

	void deleteServiceByProtocolId(Long protocolId);
	/**
	 * 查询手续费列表 - 修改
	 * @param paramsCondition
	 * @return
	 */
	List<InsServiceChargeProduct> getUpdateProtocolServiceList(Map<String, Object> paramsCondition);
	
	Map<String, Object> getServiceChargeByProductAndProtocol(@Param("productId") Long productId, @Param("protocolId") Long protocolId);

}