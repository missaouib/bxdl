package com.hzcf.api.service.impl;

import com.hzcf.api.feign.UserCenterFeignClient;
import com.hzcf.api.service.UserService;
import com.hzcf.pojo.user.UserInfo;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.service.impl</dt>
 * <dd>描述: 用户相关service实现</dd>
 * <dd>创建时间：上午 10:56 2018/10/29 0029 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserCenterFeignClient userCenterFeignClient;
    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public AppReturnMsgData userRegister(UserInfo user) {
        return userCenterFeignClient.userRegister(user);
    }

    /**
     * 修改用户信息
     *
     * @param appCommonParm
     * @return
     */
    @Override
    public AppReturnMsgData modifyUserInfo(AppCommonParm appCommonParm) {
        return userCenterFeignClient.modifyUserInfo(appCommonParm);
    }
}
