package com.hzcf.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.api.service.RealNameService;
import com.hzcf.util.StringUtil;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.controller</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：下午 14:23 2018/10/29 0029 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Api(value = "实名相关", description ="实名认证")
@SuppressWarnings("ALL")
@RestController
public class RealNameController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RealNameService realNameService;


    @ApiOperation(value = "用户实名认证", notes = "请求header:{channel: THB; timeStamp:1541986715717} ；加密前请求体：{\"realNameInfo\":{\"crmMobile\":\"crm手机号\",\"userNo\":\"用户编号\",\"cardNo\":\"证件号\",\"customerName\":\"客户姓名\",\"maritalStatus\":\"婚姻状态（0：未婚；1：已婚））\"},\"signature\":\"签名数据\"}")
    @RequestMapping(value = "/customerRealName", method = RequestMethod.POST)
    public AppReturnMsgData customerRealName(
            @ApiParam(required = true, name = "realNameParam", value = "用户实名认证信息加密串")
            @RequestParam("realNameParam") String param,
            HttpServletRequest request) {
        logger.info("========请求用户实名认证接口=========");
        AppCommonParm appCommonParm = new AppCommonParm();
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject realNameInfo = object.getJSONObject("realNameInfo");

        if(realNameInfo == null){
            return new AppReturnMsgData("9000","实名数据为空");
        }
        String customerName = realNameInfo.getString("customerName");
        String cardNo = realNameInfo.getString("cardNo");
        String crmMobile = realNameInfo.getString("crmMobile");
        String userNo = realNameInfo.getString("userNo");
        String maritalStatus = realNameInfo.getString("maritalStatus");
        if(StringUtil.isBlanks(realNameInfo.get("customerName"))) {
            return new AppReturnMsgData("2000", "客户姓名不能为空");
        }
        if(StringUtil.isBlanks(realNameInfo.get("cardNo"))) {
            return new AppReturnMsgData("2000", "证件号不能为空");
        }
        if(StringUtil.isBlanks(realNameInfo.get("crmMobile"))) {
            return new AppReturnMsgData("2000", "crm手机号不能为空");
        }
        if(StringUtil.isBlanks(realNameInfo.get("userNo"))) {
            return new AppReturnMsgData("2000", "用户编号不能为空");
        }
        appCommonParm.setCustomerName(customerName);
        appCommonParm.setCardNo(cardNo);
        appCommonParm.setCrmMobile(crmMobile);
        appCommonParm.setUserNo(userNo);
        appCommonParm.setMaritalStatus(maritalStatus);
        logger.info("用户实名认证数据", JSONObject.toJSONString(appCommonParm));
        return realNameService.customerRealName(appCommonParm);
    }

    /**
     * 修改实名信息
     * @param param
     * @return
     */
    @ApiOperation(value = "修改实名信息", notes = "请求header:{channel: THB; timeStamp:1541986894701} ；加密前请求体：{\"realNameInfo\":{\"crmMobile\":\"crm手机号\",\"maritalStatus\":\"婚姻状态（0：未婚；1：已婚）\",\"customerNo\":\"客户编号\"},\"signature\":\"签名数据\"}")
    @RequestMapping(value = "/modifyRealNameInfo", method = RequestMethod.POST)
    public AppReturnMsgData modifyRealNameInfo(
            @ApiParam(required = true, name = "realNameParam", value = "修改实名信息加密串")
            @RequestParam("realNameParam") String param,
            HttpServletRequest request) {
        logger.info("========请求修改实名信息接口=========");
        AppCommonParm appCommonParm = new AppCommonParm();
        //校验签名数据
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject realNameInfo = object.getJSONObject("realNameInfo");

        if(realNameInfo == null){
            return new AppReturnMsgData("9000","实名数据为空");
        }
        String customerNo = realNameInfo.getString("customerNo");
        String crmMobile = realNameInfo.getString("crmMobile");
        String maritalStatus = realNameInfo.getString("maritalStatus");
        if(StringUtil.isBlanks(customerNo) ) {
            return new AppReturnMsgData("2000", "客户编号不能为空");
        }
        if((StringUtil.isBlanks(crmMobile) && StringUtil.isBlanks(maritalStatus))) {
            return new AppReturnMsgData("2000", "crm手机号和婚姻状态不能都为空");
        }
        if(StringUtil.isNotBlanks(maritalStatus) && !(("0").equals(maritalStatus)|| "1".equals(maritalStatus))) {
            return new AppReturnMsgData("2000", "婚姻状况只能为未婚或已婚");
        }
        appCommonParm.setCustomerNo(customerNo);
        appCommonParm.setCrmMobile(crmMobile);
        appCommonParm.setMaritalStatus(maritalStatus);
        logger.info("修改实名信息数据", JSONObject.toJSONString(appCommonParm));
        return realNameService.modifyRealNameInfo(appCommonParm);
    }
}
