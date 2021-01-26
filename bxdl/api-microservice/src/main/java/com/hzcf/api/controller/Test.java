package com.hzcf.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.util.AESUtil;
import com.hzcf.util.UUIDUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.controller</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：上午 11:37 2018/10/29 0029 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
public class Test {
    public static final String md5Key = "18d6d9b9-3159-091d-a101-6502c8fb8601";
    public static final String aesKey = "21b4e0f1e42842f1bb66d29b6becf79c";

    public static void main(String[] args) throws Exception{

        int i = 3;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MONTH, -1);
        System.out.println(sdf.format(instance.getTime()));
        instance.add(Calendar.MONTH, -(i-1));
        System.out.println(sdf.format(instance.getTime()));

    }

    /**
     * 用户注册
     * @param jsonObject
     */
    public static void userRegister(JSONObject jsonObject) throws Exception{
        JSONObject userObject = new JSONObject();
        userObject.put("userName", "17610667923");
        userObject.put("registerTime", System.currentTimeMillis());
        jsonObject.put("userInfo", userObject);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }
    /**
     * 修改用户信息
     * @param jsonObject
     */
    public static void modifyUserInfo(JSONObject jsonObject) throws Exception{
        JSONObject userObject = new JSONObject();
        userObject.put("userNo", "1061893378846752768");
        userObject.put("userName", "17600667924");
        //userObject.put("userStatus", "1");
        jsonObject.put("userInfo", userObject);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }

    /**
     * 客户进行实名认证
     * @param jsonObject
     */
    public static void custmoerRealName(JSONObject jsonObject) throws Exception{
        JSONObject realNameObj = new JSONObject();
        realNameObj.put("customerName", "朱广伟");
        realNameObj.put("cardNo", "230125198008085112");
        realNameObj.put("crmMobile", "17600667923");
        realNameObj.put("maritalStatus", "1");
        realNameObj.put("userNo", "1061893378846752768");

        jsonObject.put("realNameInfo", realNameObj);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }

    /**
     * 客户修改实名信息
     * @param jsonObject
     */
    public static void modifyRealNameInfo(JSONObject jsonObject) throws Exception{
        JSONObject realNameObj = new JSONObject();
        realNameObj.put("maritalStatus", "0");
        realNameObj.put("crmMobile", "18210092922");
        realNameObj.put("customerNo", "1061898302162731008");

        jsonObject.put("realNameInfo", realNameObj);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }
    /**
     * 开通现金账户
     * @param jsonObject
     */
    public static void openCashAccount(JSONObject jsonObject) throws Exception{
        JSONObject accountInfoObj = new JSONObject();
        accountInfoObj.put("bankCode", "1000");
        accountInfoObj.put("bankCardNo", "12314235346124124");
        accountInfoObj.put("openBankName", "建设银行双井支行");
        accountInfoObj.put("bankReserveMobile", "18210092922");
        accountInfoObj.put("customerNo", "1061898302162731008");

        jsonObject.put("accountInfo", accountInfoObj);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }

    /**
     * 新增现金交易明细
     * @param jsonObject
     * @throws Exception
     */
    public static void insertCashTransDetail(JSONObject jsonObject) throws Exception{
        JSONObject transDetailObj = new JSONObject();
        transDetailObj.put("customerNo", "1065147562371055616");
        transDetailObj.put("detailOddNo", "MN370cae850e747ae948aa01312312");
        transDetailObj.put("buildTime", System.currentTimeMillis());
        transDetailObj.put("detailType", "1");
        transDetailObj.put("amount", 100);
        transDetailObj.put("transStatus", "1");


        jsonObject.put("transDetailInfo", transDetailObj);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }
    /**
     * 修改现金交易明细
     * @param jsonObject
     * @throws Exception
     */
    public static void modifyCashTransDetail(JSONObject jsonObject) throws Exception{
        JSONObject transDetailObj = new JSONObject();
        transDetailObj.put("customerNo", "1065147562371055616");
        transDetailObj.put("detailOddNo", "MN370c5ae850e747ae948aa02ecc18da83");
        //transDetailObj.put("buildTime", System.currentTimeMillis());
        transDetailObj.put("transStatus", "2");
        jsonObject.put("transDetailInfo", transDetailObj);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }
    /**
     * 新增积分明细
     * @param jsonObject
     */
    public static void createIntegralTransDetail(JSONObject jsonObject) throws Exception{
        JSONObject transDetailObj = new JSONObject();
        transDetailObj.put("transFlowNo", UUIDUtil.getUUID());
        //通用积分
        transDetailObj.put("integralType", "1");
        transDetailObj.put("integralCount", "100");
        transDetailObj.put("detailType", "1");
        transDetailObj.put("customerNo", "1064791224801558528");
        //自有积分
        /*transDetailObj.put("integralType", "2");
        transDetailObj.put("integralCount", "500");
        transDetailObj.put("detailType", "1");
        transDetailObj.put("userNo", "1064791223216111616");*/

        jsonObject.put("transDetailInfo", transDetailObj);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }

    /**
     * 积分交易结果
     * @param jsonObject
     * @throws Exception
     */
    public static void getIntegralTransResult(JSONObject jsonObject) throws Exception{
        JSONObject transResultObj = new JSONObject();

        transResultObj.put("detailOddNo", "1065174294700490752");
        transResultObj.put("transResult", "1");
        jsonObject.put("transResultInfo", transResultObj);
        System.out.println(jsonObject.toJSONString());
        System.out.println("args = [" + AESUtil.enCrypt(jsonObject.toString(), aesKey) + "]");
    }

}
