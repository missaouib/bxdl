package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.promotion.InsProtocolPromotion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>寿险协议推动奖励dao层</p>
 *
 * @author kongqingbao
 */
public interface InsProtocolPromotionMapper {

    /**
     * <p>新增一条推动奖励</p>
     * @param insProtocolPromotion
     */
    void insertPromotion(InsProtocolPromotion insProtocolPromotion);

    /**
     * <p>更新推动奖励</p>
     * @param insProtocolPromotion
     */
    int updatePromotion(@Param("insProtocolPromotion") InsProtocolPromotion insProtocolPromotion);

    /**
     * <p>更新推动奖励状态</p>
     * @param state 状态
     * @param lastModifier 操作人
     * @param id    id
     * @return
     */
    int updatePromotionState(@Param("state")Byte state, @Param("lastModifier")String lastModifier, @Param("id")Long id);

    /**
     * <p>删除推动奖励</p>
     * @param lastModifier 操作人
     * @param id    id
     * @return
     */
    int deletePromotion(@Param("lastModifier")String lastModifier, @Param("id")Long id);

    /**
     * <p>根据id查询推动奖励</p>
     * @param id
     * @return
     */
    InsProtocolPromotion queryPromotion(@Param("id")Long id);

    /**
     * <p>根据寿险协议id查询推动奖励</p>
     * @param protocolId    寿险协议id
     * @return
     */
    List<InsProtocolPromotion> queryPromotions(@Param("protocolId")Long protocolId);

    /**
     * <p>根据寿险协议查询未删除推动业务</p>
     * @param protocolId    寿险协议id
     * @return
     */
    List<InsProtocolPromotion> queryNotDeletedPromotions(@Param("protocolId")Long protocolId);


}
