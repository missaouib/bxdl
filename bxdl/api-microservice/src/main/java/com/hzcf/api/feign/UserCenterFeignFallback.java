package com.hzcf.api.feign;

import com.hzcf.pojo.user.UserInfo;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;
import org.springframework.stereotype.Component;

/**
 * <dl>
 * <dt>类名：com.hzcf.account.feign</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：下午 15:05 2018/10/23 0023 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Component
public class UserCenterFeignFallback implements UserCenterFeignClient{

    @Override
    public AppReturnMsgData userRegister(UserInfo userInfo) {
        return new AppReturnMsgData("9999", "服务不可用");
    }

    @Override
    public AppReturnMsgData modifyUserInfo(AppCommonParm appCommonParm){
        return new AppReturnMsgData("9999", "服务不可用");
    }

    @Override
    public AppReturnMsgData realNameResult(AppCommonParm appCommonParm) {
        return new AppReturnMsgData("9999", "服务不可用");
    }

    @Override
    public AppReturnMsgData modifyRealNameInfo(AppCommonParm appCommonParm) {
        return new AppReturnMsgData("9999", "服务不可用");
    }
}
