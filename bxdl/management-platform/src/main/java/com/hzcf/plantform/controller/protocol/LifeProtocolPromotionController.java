package com.hzcf.plantform.controller.protocol;

import com.hzcf.plantform.feign.protocol.LifeProcotolFeignClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionListPojo;
import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionPojo;
import com.hzcf.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>寿险协议-业务推动Controller</p>
 */
@Controller
public class LifeProtocolPromotionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LifeProcotolFeignClient lifeProcotolFeignClient;


    @RequiresPermissions("protocolPromotion:list")
    @RequestMapping("lifeProtocol/toPromotionPage")
    public ModelAndView toPromotionPage(@RequestParam("id") String id){
        ModelAndView view = new ModelAndView();
        String look = id.split(":")[0];
        if(StringUtil.isNotEmpty(look) && look.equals("look")){
            view.addObject("isLook", "yes");
            view.addObject("protocolId", id.split(":")[1]);
        } else {
            view.addObject("isLook", "no");
            view.addObject("protocolId", id);

        }
        view.addObject("promotionLookProtocolId", id);
        view.setViewName("lifeProtocol/promotionList");
        return view;
    }

    @RequiresPermissions("protocolPromotion:update")
    @RequestMapping("lifeProtocol/toUpdatePromotion")
    public ModelAndView toPromotionUpdate(@RequestParam("id") Long promotionId, @RequestParam("protocolId") Long protocolId){
        ModelAndView view = new ModelAndView();
        view.addObject("protocolId",protocolId);
        view.addObject("promotionId",promotionId);
        Long salesOrgId = lifeProcotolFeignClient.findSalesOrgIdByProtocolId(protocolId);
        view.addObject("salesOrgId", salesOrgId);
        view.setViewName("lifeProtocol/updatePromotion");
        return view;
    }

    @RequiresPermissions("protocolPromotion:add")
    @RequestMapping("lifeProtocol/toAddPromotion")
    public ModelAndView toAddPromotion(@RequestParam("id") Long protocolId){
        ModelAndView view = new ModelAndView();
        view.addObject("protocolId", protocolId);
        Long salesOrgId = lifeProcotolFeignClient.findSalesOrgIdByProtocolId(protocolId);
        view.addObject("salesOrgId", salesOrgId);
        view.setViewName("lifeProtocol/addPromotion");
        return view;
    }

    /**
     * <p>根据寿险协议id查询产品信息</p>
     * @param protocolId    寿险协议id
     * @return
     */
    @RequestMapping(value = "/lifeProtocol/insProtocolProducts", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> findProductsByProtocolId(@RequestParam("protocolId") Long protocolId){
        return lifeProcotolFeignClient.findProductsByProtocolId(protocolId);
    }

    /**
     * <p>新增一条推动奖励</p>
     * @param insProtocolPromotionPojo
     */
    @RequestMapping(value = "/insProtocolPromotion", method = RequestMethod.POST)
    @ResponseBody
    public DataMsg saveInsProtocolPromotion(@RequestBody InsProtocolPromotionPojo insProtocolPromotionPojo){
        DataMsg msg = new DataMsg();
        try {
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            insProtocolPromotionPojo.setLastModifier(employee.getName());
            lifeProcotolFeignClient.saveInsProtocolPromotion(insProtocolPromotionPojo);
            msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            logger.error("寿险协议-业务推动[新增]异常"+e);
        }
        return msg;
    }

    /**
     * <p>更新推动奖励</p>
     * @param insProtocolPromotionPojo
     */
    @RequestMapping(value = "/insProtocolPromotion", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateInsProtocolPromotion(@RequestBody InsProtocolPromotionPojo insProtocolPromotionPojo){
        return lifeProcotolFeignClient.updateInsProtocolPromotion(insProtocolPromotionPojo);
    }

    /**
     * <p>更新推动奖励状态</p>
     * @param state 状态
     * @param id    id
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotion/state", method = RequestMethod.PUT)
    @ResponseBody
    public DataMsg updatePromotionState(@RequestParam("state") Byte state, @RequestParam("id") Long id){
        DataMsg dataMsg = new DataMsg();
        try {
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            boolean result = lifeProcotolFeignClient.updatePromotionState(state, employee.getName(), id);
            if(result){
                dataMsg.setMessageCode("200");
            }
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("寿险协议-业务推动[更新状态]异常"+e);
        }
        return dataMsg;
    }

    /**
     * <p>删除推动奖励</p>
     * @param id    id
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotion/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public DataMsg deletePromotion(@PathVariable("id") Long id){
        DataMsg dataMsg = new DataMsg();
        try {
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            boolean result = lifeProcotolFeignClient.deletePromotion(employee.getName(), id);
            if(result){
                dataMsg.setMessageCode("200");
            }
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("寿险协议-业务推动[删除]异常"+e);
        }
        return dataMsg;
    }

    /**
     * <p>根据id查询推动奖励</p>
     * @param promotionId
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotion/{promotionId}", method = RequestMethod.GET)
    @ResponseBody
    public InsProtocolPromotionPojo queryPromotion(@PathVariable("promotionId")Long promotionId){
        return lifeProcotolFeignClient.queryPromotion(promotionId);
    }

    /**
     * <p>根据寿险协议id查询推动奖励</p>
     * @param protocolId    寿险协议id
     * @return
     */
    @RequestMapping(value = "/insProtocolPromotions", method = RequestMethod.GET)
    @ResponseBody
    public List<InsProtocolPromotionListPojo> queryPromotions(@RequestParam("protocolId") Long protocolId){
        return lifeProcotolFeignClient.queryPromotions(protocolId);
    }

}
