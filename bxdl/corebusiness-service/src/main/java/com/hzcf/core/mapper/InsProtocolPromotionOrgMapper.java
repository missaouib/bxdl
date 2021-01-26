package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>寿险协议推动奖励机构dao层</p>
 *
 * @author kongqingbao
 */
public interface InsProtocolPromotionOrgMapper {

    /**
     * <p>批量插入推动奖励机构</p>
     * @param insProtocolPromotionOrg
     */
    void insertPromotionOrgList(@Param("list") List<InsProtocolPromotionOrg> insProtocolPromotionOrg);

    /**
     * <p>根据id集合删除推动奖励机构</p>
     * @param ids   id集合
     * @return  int
     */
    int deletePromotionOrg(@Param("ids") List ids);

    /**
     * <p>根据业务推动id查询相关机构</p>
     * @param promotionId   业务推动id
     * @return List
     */
    List<InsProtocolPromotionOrg> queryPromotionOrgs(@Param("promotionId") Long promotionId);

    /**
     * <p>根据业务推动id查询机构关系表id集合</p>
     * @param promotionId
     * @return
     */
    List<Long> queryPromotionOrgIds(@Param("promotionId") Long promotionId);

    /**
     * <p>根据业务推动id查询机构id</p>
     * @param promotionId   业务推动id
     * @return
     */
    List<Long> queryOrgIdsByPromotionId(@Param("promotionId") Long promotionId);


}
