package com.hzcf.api.service.impl;

import com.hzcf.api.feign.UserCenterFeignClient;
import com.hzcf.api.service.RealNameService;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.service.impl</dt>
 * <dd>描述: 实名相关service实现</dd>
 * <dd>创建时间：下午 14:28 2018/10/29 0029 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Service
public class RealNameServiceImpl implements RealNameService{

    @Autowired
    UserCenterFeignClient userCenterFeignClient;
    /**
     * 客户实名认证
     *
     * @param appCommonParm
     * @return
     */
    @Override
    public AppReturnMsgData customerRealName(AppCommonParm appCommonParm) {
        return userCenterFeignClient.realNameResult(appCommonParm);
    }

    /**
     * 修改实名认证信息
     *
     * @param appCommonParm
     * @return
     */
    @Override
    public AppReturnMsgData modifyRealNameInfo(AppCommonParm appCommonParm) {
        return userCenterFeignClient.modifyRealNameInfo(appCommonParm);
    }
}
