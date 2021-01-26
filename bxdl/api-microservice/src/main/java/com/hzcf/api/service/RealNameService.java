package com.hzcf.api.service;

import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.service</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：下午 14:27 2018/10/29 0029 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
public interface RealNameService {
    /**
     * 客户实名认证
     * @param appCommonParm
     * @return
     */
    AppReturnMsgData customerRealName(AppCommonParm appCommonParm);

    /**
     * 修改实名认证信息
     * @param appCommonParm
     * @return
     */
    AppReturnMsgData modifyRealNameInfo(AppCommonParm appCommonParm);
}
