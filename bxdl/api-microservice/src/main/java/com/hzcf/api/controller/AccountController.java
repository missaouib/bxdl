package com.hzcf.api.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.api.service.AccountService;
import com.hzcf.pojo.account.CashAccount;
import com.hzcf.pojo.account.CashTransDetail;
import com.hzcf.util.StringUtil;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.controller</dt>
 * <dd>描述: 账户相关controller</dd>
 * <dd>创建时间：下午 12:46 2018/10/30 0030 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Api(value = "积分账户及现金账户", description ="积分账户及现金账户")
@RequestMapping("/account")
@RestController
public class AccountController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountService accountService;

    /**
     * 开通现金账户
     * @param param
     * @return
     */
    @ApiOperation(value = "开通现金账户", notes = "请求header:{channel: THB; timeStamp:1541986987100} ；加密前请求体：{\"accountInfo\":{\"bankCode\":\"银行编号\",\"bankCardNo\":\"银行卡号\",\"bankReserveMobile\":\"银行预留手机号\",\"customerNo\":\"客户编号\",\"openBankName\":\"开户网点\"},\"signature\":\"签名数据\"}")
    @RequestMapping(value = "openCashAccount", method = RequestMethod.POST)
    public AppReturnMsgData openCashAccount(
            @ApiParam(required = true, name = "openCashAccountParam", value = "开通现金账户信息加密串")
            @RequestParam("openCashAccountParam") String param,
            HttpServletRequest request){
        logger.info("========请求开通现金账户接口=========");
        AppCommonParm appCommonParm = new AppCommonParm();
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject accountInfo = object.getJSONObject("accountInfo");
        if(accountInfo == null){
            return new AppReturnMsgData("9000","账户数据为空");
        }
        String customerNo = accountInfo.getString("customerNo");
        if(StringUtil.isBlanks(customerNo)) {
            return new AppReturnMsgData("3000", "客户编号不能为空");
        }
        CashAccount cashAccount = JSONObject.parseObject(accountInfo.toJSONString(), CashAccount.class);
        String channel = request.getHeader("channel");
        cashAccount.setChannel(channel);
        if(StringUtil.isBlanks(cashAccount.getBankCode())) {
            return new AppReturnMsgData("3000", "银行编号不能为空");
        }
        if(StringUtil.isBlanks(cashAccount.getBankCardNo())) {
            return new AppReturnMsgData("3000", "银行卡号不能为空");
        }
        if(StringUtil.isBlanks(cashAccount.getOpenBankName())) {
            return new AppReturnMsgData("3000", "开户网点不能为空");
        }
        if(StringUtil.isBlanks(cashAccount.getBankReserveMobile())) {
            return new AppReturnMsgData("3000", "银行预留手机号不能为空");
        }
        logger.info("开通现金账户数据===", JSONObject.toJSONString(cashAccount) + ",客户编号：" + customerNo);
        return accountService.openCashAccount(cashAccount, customerNo);
    }

    /**
     * 新增現金账户交易明细
     * @param param
     */
    @ApiOperation(value = "新增現金账户交易明细", notes = "请求header:{channel: THB; timeStamp:1541987111725} ；加密前请求体：{\"signature\":\"签名数据\",\"transDetailInfo\":{\"detailType\":\"（1：充值；2：提现；3：出借；4：回款；5：奖励）\",\"amount\":交易金额,\"buildTime\":创建时间（时间戳）,\"detailOddNo\":\"交易明细编号\",\"customerNo\":\"客户编号\",\"transStatus\":\"交易状态（1：处理中；2：成功；3：失败）\"}}")
    @RequestMapping(value = "insertCashTransDetail", method = RequestMethod.POST)
    public AppReturnMsgData insertCashTransDetail(
            @ApiParam(required = true, name = "cashTransDetailParam", value = "新增現金账户交易明细信息加密串")
            @RequestParam("cashTransDetailParam") String param,
            HttpServletRequest request){
        logger.info("========请求新增現金账户交易明细接口=========");
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject transDetailInfo = object.getJSONObject("transDetailInfo");
        if(transDetailInfo == null){
            return new AppReturnMsgData("9000","交易明細数据为空");
        }
        String channel = request.getHeader("channel");
        String customerNo = transDetailInfo.getString("customerNo");
        if(StringUtil.isBlanks(customerNo)) {
            return new AppReturnMsgData("3000", "客户编号不能为空");
        }
        String detailOddNo = transDetailInfo.getString("detailOddNo");
        if(StringUtil.isBlanks(detailOddNo)) {
            return new AppReturnMsgData("3000", "明细单号不能为空");
        }

        if(transDetailInfo.get("buildTime") == null) {
            return new AppReturnMsgData("3000", "创建时间不能为空");
        }
        Date buildTime = new Date(transDetailInfo.getLongValue("buildTime"));
        String detailType = transDetailInfo.getString("detailType");
        if(StringUtil.isBlanks(detailType)) {
            return new AppReturnMsgData("3000", "明细类型不能为空");
        }
        if(!("1".equals(detailType) || "2".equals(detailType) ||
                "3".equals(detailType) || "4".equals(detailType) || "5".equals(detailType))) {
            return  new AppReturnMsgData("3000", "明细类型错误");
        }
        BigDecimal amount = transDetailInfo.getBigDecimal("amount");
        if(amount == null || amount.compareTo(new BigDecimal("0")) != 1) {
            return  new AppReturnMsgData("3000", "明细交易金额错误");
        }
        String transStatus = transDetailInfo.getString("transStatus");
        if(StringUtil.isBlanks(transStatus)) {
            return  new AppReturnMsgData("3000", "交易状态不能为空");
        }
        if(!("1".equals(transStatus) || "2".equals(transStatus) || "3".equals(transStatus))) {
            return new AppReturnMsgData("3000", "交易状态错误");
        }
        CashTransDetail cashTransDetail = new CashTransDetail();
        cashTransDetail.setAmount(amount);
        cashTransDetail.setCreateTime(new Date());
        cashTransDetail.setBuildTime(buildTime);
        cashTransDetail.setDetailOddNo(detailOddNo);
        cashTransDetail.setTransStatus(transStatus);
        cashTransDetail.setDetailType(detailType);
        logger.info("开通现金账户数据===", JSONObject.toJSONString(cashTransDetail) + ",客户编号：" + customerNo + ",渠道：" + channel);
        return accountService.insertCashTransDetail(cashTransDetail, customerNo, channel);
    }

    /**
     * 修改現金账户交易明细
     * @param param
     * @return
     */
    @ApiOperation(value = "修改現金账户交易明细", notes = "请求header:{channel: THB; timeStamp:1541986715717} ；加密前请求体：{\"signature\":\"签名数据\",\"transDetailInfo\":{\"detailOddNo\":\"交易明细编号\",\"customerNo\":\"客户编号\",\"transStatus\":\"交易状态（2：成功；3：失败；修改交易状态只能由，处理中变为成功/失败）\"}}")
    @RequestMapping(value = "modifyCashTransDetail", method = RequestMethod.POST)
    public AppReturnMsgData modifyCashTransDetail(
            @ApiParam(required = true, name = "cashTransDetailParam", value = "修改現金账户交易明细信息加密串")
            @RequestParam("cashTransDetailParam") String param,
            HttpServletRequest request){
        logger.info("========请求修改現金账户交易明细接口=========");
        AppCommonParm appCommonParm = new AppCommonParm();
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject transDetailInfo = object.getJSONObject("transDetailInfo");
        if(transDetailInfo == null){
            return new AppReturnMsgData("9000","交易明細数据为空");
        }
        String channel = request.getHeader("channel");
        String customerNo = transDetailInfo.getString("customerNo");
        if(StringUtil.isBlanks(customerNo)) {
            return new AppReturnMsgData("3000", "客户编号不能为空");
        }
        String detailOddNo = transDetailInfo.getString("detailOddNo");
        if(StringUtil.isBlanks(detailOddNo)) {
            return new AppReturnMsgData("3000", "明细单号不能为空");
        }

        String transStatus = transDetailInfo.getString("transStatus");
        if(StringUtil.isBlanks(transStatus)) {
            return  new AppReturnMsgData("3000", "交易状态不能为空");
        }
        if(!("2".equals(transStatus) || "3".equals(transStatus))) {
            return new AppReturnMsgData("3000", "交易状态错误");
        }
        appCommonParm.setDetailOddNo(detailOddNo);
        appCommonParm.setTransResult(transStatus);
        appCommonParm.setChannel(channel);
        appCommonParm.setCustomerNo(customerNo);
        logger.info("修改現金账户交易明细数据===", JSONObject.toJSONString(appCommonParm));
        return accountService.modifyCashTransDetail(appCommonParm);
    }

    /**
     * 发送新增/消耗积分请求
     * @param param
     * @return
     */
    @ApiOperation(value = "发送新增/消耗积分请求", notes = "请求header:{channel: THB; timeStamp:1541986715717} ；加密前请求体：{\"signature\":\"签名数据\",\"transDetailInfo\":{\"detailType\":\"明细类型（1：获取；2：消耗）\",\"userNo\":\"用户编号\",\"integralCount\":\"积分数量\",\"transFlowNo\":\"交易流水号\",\"integralType\":\"积分类型(1:通用积分；2：自由积分)\"}}")
    @RequestMapping(value = "createIntegralTransDetail", method = RequestMethod.POST)
    public AppReturnMsgData createIntegralTransDetail(
            @ApiParam(required = true, name = "integralTransDetailParam", value = "发送新增/消耗积分请求信息加密串")
            @RequestParam("integralTransDetailParam") String param,
            HttpServletRequest request
    ) {
        logger.info("========请求发送新增/消耗积分请求接口=========");
        //校验签名数据
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject transDetailInfo = object.getJSONObject("transDetailInfo");
        if(transDetailInfo == null) {
            return new AppReturnMsgData("9000","明细数据为空");
        }
        String integralType = transDetailInfo.getString("integralType"); //积分类型 1：通用积分；2：自由积分
        String userNo = transDetailInfo.getString("userNo");
        String customerNo = transDetailInfo.getString("customerNo");
        Integer integralCount = transDetailInfo.getInteger("integralCount");
        String detailType = transDetailInfo.getString("detailType"); //明细类型1：新增；2：消耗
        String transFlowNo = transDetailInfo.getString("transFlowNo"); //请求方交易流水号
        if(StringUtil.isBlanks(integralType)) {
            return new AppReturnMsgData("4000", "积分类型不能为空");
        }
        if(StringUtil.isBlanks(transFlowNo)) {
            return new AppReturnMsgData("4000", "交易流水号不能为空");
        }
        if("1".equals(integralType)) {
            if(StringUtil.isBlanks(customerNo)) {
                return new AppReturnMsgData("4000", "客户编号不能为空");
            }
        }else if("2".equals(integralType)) {
            if(StringUtil.isBlanks(userNo)) {
                return new AppReturnMsgData("4000", "用户编号不能为空");
            }
        }else {
            return new AppReturnMsgData("4000", "积分类型不能为空");
        }
        if(integralCount == null || integralCount <= 0) {
            return new AppReturnMsgData("4000", "积分数量必须大于0");
        }
        if(!("1".equals(detailType) || "2".equals(detailType))) {
            return new AppReturnMsgData("4000", "积分明细类型错误");
        }

        AppCommonParm appCommonParm = JSONObject.parseObject(transDetailInfo.toJSONString(), AppCommonParm.class);
        appCommonParm.setChannel(request.getHeader("channel"));
        logger.info("发送新增/消耗积分请求数据===", JSONObject.toJSONString(appCommonParm));
        return accountService.createIntegralTransDetail(appCommonParm);
    }

    /**
     * 修改积分交易结果
     * @param param
     * @return
     */
    @ApiOperation(value = "修改积分交易结果", notes = "请求header:{channel: THB; timeStamp:1541986715717} ；加密前请求体：{\"signature\":\"签名数据\",\"transResultInfo\":{\"transResult\":\"交易状态状态（1：成功；2：失败；）\",\"detailOddNo\":\"交易明细编号\"}}")
    @RequestMapping(value = "modifyIntegralTransResult", method = RequestMethod.POST)
    public AppReturnMsgData modifyIntegralTransResult(
            @ApiParam(required = true, name = "modifyIntegralTransResultParam", value = "修改积分交易结果信息加密串")
            @RequestParam("modifyIntegralTransResultParam") String param,
            HttpServletRequest request
    ) {
        logger.info("========请求修改积分交易结果接口=========");
        //校验签名数据
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject transResult = object.getJSONObject("transResultInfo");
        if(transResult == null) {
            return new AppReturnMsgData("9000","账户数据为空");
        }
        String detailOddNo = transResult.getString("detailOddNo"); //积分类型通用积分；2：自由积分
        String result = transResult.getString("transResult");
        if(StringUtil.isBlanks(detailOddNo)) {
            return new AppReturnMsgData("4000", "明细编号不能为空");
        }
        if(StringUtil.isBlanks(result)){
            return new AppReturnMsgData("4000", "交易结果不能为空");
        }
        if(!("1".equals(result) || "2".equals(result))) {
            return new AppReturnMsgData("4000", "交易结果类型错误");
        }
        AppCommonParm appCommonParm = JSONObject.parseObject(transResult.toJSONString(), AppCommonParm.class);
        logger.info("修改积分交易结果数据", JSONObject.toJSONString(appCommonParm));
        return accountService.modifyIntegralTransResult(appCommonParm);
    }


}
