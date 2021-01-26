package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.protocol.InsProtocolProduct;
import com.hzcf.pojo.insurance.protocol.InsProtocolProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InsProtocolProductMapper {
    long countByExample(InsProtocolProductExample example);

    int deleteByExample(InsProtocolProductExample example);

    int deleteByPrimaryKey(Long protocolProductId);

    int insert(InsProtocolProduct record);

    int insertSelective(InsProtocolProduct record);

    List<InsProtocolProduct> selectByExample(InsProtocolProductExample example);

    InsProtocolProduct selectByPrimaryKey(Long protocolProductId);

    int updateByExampleSelective(@Param("record") InsProtocolProduct record, @Param("example") InsProtocolProductExample example);

    int updateByExample(@Param("record") InsProtocolProduct record, @Param("example") InsProtocolProductExample example);

    int updateByPrimaryKeySelective(InsProtocolProduct record);

    int updateByPrimaryKey(InsProtocolProduct record);
    
	void insertBatch(List<InsProtocolProduct> pList);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	/**
	 * <p>根据协议id查询相关产品信息</p>
	 * @param protocolId	协议id
	 * @return
	 */
	List<InsProtocolProduct> queryInsProtocolProductByProtocolId(@Param("protocolId")Long protocolId);

	/**
	 * <p>根据协议id查询产品id</p>
	 * @param protocolId	协议id
	 * @return	产品id集合
	 */
	List<Long> queryProductIdsByProtocolId(@Param("protocolId")Long protocolId);

	/**
	 * 查询协议产品公共列表
	 * @param paramsCondition
	 * @return
	 */
	List<Map<String, Object>> getProtocolProductList(Map<String, Object> paramsCondition);

	/**
	 * 查寻协议产品公共条数
	 * @param paramsCondition
	 * @return
	 */
	Long getProtocolProductCount(Map<String, Object> paramsCondition);

    long updateProrocolProduct(InsProtocolProduct insProtocolProduct);
}