package com.hzcf.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.util.AESUtil;
import com.hzcf.util.Md5Util2;
import com.hzcf.util.StringUtil;
import com.hzcf.vo.AppReturnMsgData;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.controller</dt>
 * <dd>描述: controller基类</dd>
 * <dd>创建时间：上午 10:11 2018/10/29 0029 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@SuppressWarnings("ALL")
@RestController
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${user.channel.thb}")
    public String thbChannel;
    @Value("${user.channel.hzd}")
    public String hzdChannel;
    @Value("${user.channel.hzw}")
    public String hzwChannel;
    @Value("${common.md5.key}")
    public String md5Key;
    @Value("${aes.key.thb}")
    public String thbAESKey;
    @Value("${aes.key.hzd}")
    public String hzdAESKey;
    @Value("${aes.key.hzw}")
    public String hzwAESKey;

    public AppReturnMsgData checkSignature(String param, HttpServletRequest request) {
        String channel = request.getHeader("channel");
        String timeStamp = request.getHeader("timeStamp");
        String aesKey = "";
        if(StringUtil.isBlank(channel) ||
                !(thbChannel.equals(channel) ||
                        hzdChannel.equals(channel)||
                        hzwChannel.equals(channel))) {
            return new AppReturnMsgData("9006", "无效的系统来源");
        }
        switch (channel){
            case "THB":
                aesKey = thbAESKey;
                break;
            case "HZD":
                aesKey = hzdAESKey;
                break;
            case "HZW":
                aesKey = hzwAESKey;
                break;
        }

        if(StringUtil.isBlank(param)) {
            return new AppReturnMsgData("9001", "上送参数为空");
        }
        try{
            //数据解密
            param = AESUtil.deCrypt(param, aesKey);
        }catch (Exception e){
            logger.error("出借报告生成通知解密异常：", e);
            return new AppReturnMsgData("9002", "解密错误");
        }
        //验证字符串是否为JSON格式
        if(!StringUtil.isJSONValid(param)){
            return new AppReturnMsgData("9003", "解密错误");
        }
        JSONObject object =JSONObject.parseObject(param);
        String signature = object.getString("signature");

        if(StringUtil.isBlank(signature)) {
            return new AppReturnMsgData("9004", "解密错误");
        }
        if(timeStamp == null) {
            return new AppReturnMsgData("9005", "无效的请求时间");
        }

        String localSign = Md5Util2.getMD5String(
                StringUtil.join(new String[]{channel, timeStamp}, ","), md5Key);
        if(!signature.equals(localSign)) {
            return new AppReturnMsgData("9007", "签名错误");
        }
        return new AppReturnMsgData("0000", "验证通过", param);
    }
}
