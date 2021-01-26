package com.hzcf.core.service;

import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionListPojo;
import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionPojo;

import java.util.List;
import java.util.Map;

/**
 * <p>寿险协议-推动奖励接口</p>
 */
public interface InsProtocolPromotionService {

    /**
     * <p>新增一条推动奖励</p>
     * @param insProtocolPromotionPojo
     */
    void saveInsProtocolPromotion(InsProtocolPromotionPojo insProtocolPromotionPojo);

    /**
     * <p>更新推动奖励</p>
     * @param insProtocolPromotionPojo
     */
    boolean updateInsProtocolPromotion(InsProtocolPromotionPojo insProtocolPromotionPojo);

    /**
     * <p>更新推动奖励状态</p>
     * @param state 状态
     * @param lastModifier 操作人
     * @param id    id
     * @return
     */
    boolean updatePromotionState(Byte state, String lastModifier, Long id);

    /**
     * <p>删除推动奖励</p>
     * @param lastModifier 操作人
     * @param id    id
     * @return
     */
    boolean deletePromotion(String lastModifier, Long id);

    /**
     * <p>根据id查询推动奖励</p>
     * @param id
     * @return
     */
    InsProtocolPromotionPojo queryPromotion(Long id);

    /**
     * <p>根据寿险协议id查询推动奖励</p>
     * @param protocolId    寿险协议id
     * @return
     */
    List<InsProtocolPromotionListPojo> queryPromotions(Long protocolId);

    /**
     * <p>根据寿险协议id查询产品信息</p>
     * @param protocolId    协议id
     * @return
     */
    List<Map> findProductsByProtocolId(Long protocolId);

    /**
     * <p>查询业务推动费用</p>
     * @param protocolId    协议id
     * @return
     */
    void findPromoteCost(Long protocolId);

    /**
     * <p>根据寿险协议id查询组织机构id</p>
     * @param protocolId    协议id
     * @return
     */
    Long findSalesOrgIdByProtocolId(Long protocolId);
    
    void testCalculatePromoteCost(String date);
}
