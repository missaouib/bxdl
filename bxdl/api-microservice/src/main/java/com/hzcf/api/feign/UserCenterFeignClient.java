package com.hzcf.api.feign;

import com.hzcf.pojo.user.UserInfo;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <dl>
 * <dt>类名：com.hzcf.account.feign</dt>
 * <dd>描述: 调用用户中心feign客户端</dd>
 * <dd>创建时间：下午 15:03 2018/10/23 0023 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@FeignClient(serviceId="user-center-microservice", fallback = UserCenterFeignFallback.class)
public interface UserCenterFeignClient {
    //用户注册
    @RequestMapping(value = "/user/userRegister", method = RequestMethod.POST)
    AppReturnMsgData userRegister(@RequestBody UserInfo userInfo);
    //修改用户信息
    @RequestMapping(value = "/user/modifyUserInfo", method = RequestMethod.POST)
    AppReturnMsgData modifyUserInfo(@RequestBody AppCommonParm appCommonParm);
    //实名认证
    @RequestMapping(value = "/realName/customerRealName", method = RequestMethod.POST)
    AppReturnMsgData realNameResult(@RequestBody AppCommonParm appCommonParm);
    //修改实名认证信息
    @RequestMapping(value = "/realName/modifyRealNameInfo", method = RequestMethod.POST)
    AppReturnMsgData modifyRealNameInfo(@RequestBody AppCommonParm appCommonParm);
}
