package com.hzcf.api.controller;

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
import com.hzcf.api.service.UserService;
import com.hzcf.pojo.user.UserInfo;
import com.hzcf.util.StringUtil;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.controller</dt>
 * <dd>描述: 用户体系相关接口</dd>
 * <dd>创建时间：上午 9:54 2018/10/29 0029 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Api(value = "用户", description ="用户")
@RestController
public class UserController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户注册", notes = "请求header:{channel: THB; timeStamp:1541986217050} ；加密前请求体：{\n" +
            "\t\"userInfo\": {\n" +
            "\t\t\"registerTime\": 注册时间（时间戳）,\n" +
            "\t\t\"userName\": \"用户名\"\n" +
            "\t},\n" +
            "\t\"signature\": \"签名数据（利用请求header中channel和timeStamp进行加密）\"\n" +
            "}")
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public AppReturnMsgData customerRegister(
            @ApiParam(required = true, name = "userInfo", value
                    = "用户注册信息加密串")
            @RequestParam("userInfo") String param,
            HttpServletRequest request) {
    		logger.info("========请求用户注册接口=========");
        //校验签名数据
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject userObj = object.getJSONObject("userInfo");
        if(userObj == null){
          return new AppReturnMsgData("9000","用户数据为空");
        }

        UserInfo user = new UserInfo();
        user.setUserName(userObj.getString("userName"));
        user.setRegisterTime(new Date(userObj.getLongValue("registerTime")));
        if(StringUtil.isBlank(user.getUserName())) {
            return new AppReturnMsgData("1000","用户名不能为空");
        }
        user.setChannel(request.getHeader("channel"));
        logger.info("注册用户数据", JSONObject.toJSONString(user));
        return userService.userRegister(user);
    }
    @ApiOperation(value = "修改用户信息", notes = "请求header:{channel: THB; timeStamp:1541986390622} ；加密前请求体：{\"userInfo\":{\"userStatus\":\"使用状态（0：禁用；1：启用）\",\"userNo\":\"用户编号\",\"userName\":\"用户名\"},\"signature\":\"签名\"}")
    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    public AppReturnMsgData modifyUserInfo(
            @ApiParam(required = true, name = "userInfo", value
                    = "修改用户信息加密串")
            @RequestParam("userInfo") String param,
            HttpServletRequest request) {
        logger.info("========请求修改用户信息接口=========");
        AppCommonParm appCommonParm = new AppCommonParm();
        //校验签名数据
        if(!"0000".equals(checkSignature(param, request).getReturnCode())){
            return checkSignature(param, request);
        }
        param = String.valueOf(checkSignature(param, request).getData());
        JSONObject object =JSONObject.parseObject(param);
        JSONObject userObj = object.getJSONObject("userInfo");
        if(userObj == null){
            return new AppReturnMsgData("9000","用户数据为空");
        }
        String userNo = userObj.getString("userNo");
        if(StringUtil.isBlanks(userNo)){
            return new AppReturnMsgData("1000", "用户编号不能为空");
        }
        appCommonParm.setUserNo(userNo);
        String userName = userObj.getString("userName");
        String userStatus = userObj.getString("userStatus");

        if(StringUtil.isNotBlanks(userName)) {
            appCommonParm.setModifyMobileFlag(true);
            appCommonParm.setUserName(userName);
        }else if(StringUtil.isNotBlanks(userStatus)){
            if(!("1".equals(userStatus)|| "2".equals(userStatus))) {
                return new AppReturnMsgData("1000", "请上送正确的用户状态");
            }
            appCommonParm.setModifyUserStatusFlag(true);
            appCommonParm.setUserStatus(userStatus);
        }else {
            return new AppReturnMsgData("1000", "请上送要修改的用户信息");
        }
        appCommonParm.setChannel(request.getHeader("channel"));
        logger.info("修改用户信息数据", JSONObject.toJSONString(appCommonParm));
        return userService.modifyUserInfo(appCommonParm);
    }


}
