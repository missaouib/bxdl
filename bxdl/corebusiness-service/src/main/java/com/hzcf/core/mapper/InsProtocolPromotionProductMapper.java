package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>寿险协议推动奖励产品dao层</p>
 *
 * @author kongqingbao
 */
public interface InsProtocolPromotionProductMapper {

    /**
     * <p>批量新增新增推动奖励产品</p>
     * @param insProtocolPromotionProduct
     */
    void insertPromotionProductList(@Param("list") List<InsProtocolPromotionProduct> insProtocolPromotionProduct);

    /**
     * <p>根据id集合删除推动奖励产品</p>
     * @param ids   id集合
     * @return  int
     */
    int deletePromotionProduct(@Param("ids") List ids);

    /**
     * <p>根据业务推动id查询相关产品</p>
     * @param promotionId   业务推动id
     * @return List
     */
    List<InsProtocolPromotionProduct> queryPromotionProducts(@Param("promotionId") Long promotionId);

    /**
     * <p>根据业务推动id查询产品关系表id集合</p>
     * @param promotionId   业务推动id
     * @return
     */
    List<Long> queryPromotionProductIds(@Param("promotionId") Long promotionId);

    /**
     * <p>根据业务推动id查询产品id集合</p>
     * @param promotionId   业务推动id
     * @return
     */
    List<Long> queryProductIdsByPromotionId(@Param("promotionId") Long promotionId);


}
