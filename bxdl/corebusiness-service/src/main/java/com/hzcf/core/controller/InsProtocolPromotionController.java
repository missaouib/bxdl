package com.hzcf.core.controller;

import com.hzcf.core.service.InsProtocolPromotionService;
import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionListPojo;
import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>寿险协议-业务推动controller</p>
 * @author kongqingbao
 */
@RestController
@RequestMapping("/lifeProtocol")
public class InsProtocolPromotionController {

    @Autowired
    private InsProtocolPromotionService insProtocolPromotionService;

    /**
     * <p>新增一条推动奖励</p>
     * @param insProtocolPromotionPojo
     */
    @RequestMapping(value = "/insProtocolPromotion", method = RequestMethod.POST)
    public void saveInsProtocolPromotion(@RequestBody InsProtocolPromotionPojo insProtocolPromotionPojo){
        insProtocolPromotionService.saveInsProtocolPromotion(insProtocolPromotionPojo);
    }

    /**
     * <p>更新推动奖励</p>
     * @param insProtocolPromotionPojo
     */
    @RequestMapping(value = "/insProtocolPromotion", method = RequestMethod.PUT)
    public boolean updateInsProtocolPromotion(@RequestBody InsProtocolPromotionPojo insProtocolPromotionPojo){
        return insProtocolPromotionService.updateInsProtocolPromotion(insProtocolPromotionPojo);
    }

    /**
     * <p>更新推动奖励状态</p>
     * @param state 状态
     * @param lastModifier 操作人
     * @param id    id
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotion/state", method = RequestMethod.POST)
    public boolean updatePromotionState(@RequestParam("state") Byte state, @RequestParam("lastModifier") String lastModifier, @RequestParam("id") Long id){
        return insProtocolPromotionService.updatePromotionState(state, lastModifier, id);
    }

    /**
     * <p>删除推动奖励</p>
     * @param lastModifier 操作人
     * @param id    id
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotion", method = RequestMethod.DELETE)
    public boolean deletePromotion(@RequestParam("lastModifier") String lastModifier, @RequestParam("id") Long id){
        return insProtocolPromotionService.deletePromotion(lastModifier, id);
    }

    /**
     * <p>根据id查询推动奖励</p>
     * @param id
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotion", method = RequestMethod.GET)
    public InsProtocolPromotionPojo queryPromotion(@RequestParam("id")Long id){
        return insProtocolPromotionService.queryPromotion(id);
    }

    /**
     * <p>根据寿险协议id查询推动奖励</p>
     * @param protocolId    寿险协议id
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotions", method = RequestMethod.GET)
    public List<InsProtocolPromotionListPojo> queryPromotions(@RequestParam("protocolId") Long protocolId){
        return insProtocolPromotionService.queryPromotions(protocolId);
    }

    /**
     * <p>根据寿险协议id查询产品信息</p>
     * @param protocolId    寿险协议id
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotionProducts", method = RequestMethod.GET)
    public List<Map> findProductsByProtocolId(@RequestParam("protocolId") Long protocolId){
        return insProtocolPromotionService.findProductsByProtocolId(protocolId);
    }

    /**
     * <p>根据寿险协议id查询组织机构id</p>
     * @param protocolId    协议id
     * @return
     */
    @RequestMapping(value = "/getSalesOrgId", method = RequestMethod.GET)
    public Long findSalesOrgIdByProtocolId(@RequestParam("protocolId") Long protocolId){
        return insProtocolPromotionService.findSalesOrgIdByProtocolId(protocolId);
    }

}
