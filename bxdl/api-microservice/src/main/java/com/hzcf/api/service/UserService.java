package com.hzcf.api.service;

import com.hzcf.pojo.user.UserInfo;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.service</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：上午 10:56 2018/10/29 0029 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    AppReturnMsgData userRegister(UserInfo user);

    /**
     * 修改用户信息
     * @param appCommonParm
     * @return
     */
    AppReturnMsgData modifyUserInfo(AppCommonParm appCommonParm);
}
