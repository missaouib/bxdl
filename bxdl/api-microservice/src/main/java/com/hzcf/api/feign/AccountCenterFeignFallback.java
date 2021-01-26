package com.hzcf.api.feign;

import com.hzcf.pojo.account.CashAccount;
import com.hzcf.pojo.account.CashTransDetail;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;
import org.springframework.stereotype.Component;

/**
 * <dl>
 * <dt>类名：com.hzcf.account.feign</dt>
 * <dd>描述: 账户中心相关</dd>
 * <dd>创建时间：下午 15:05 2018/10/23 0023 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Component
public class AccountCenterFeignFallback implements AccountCenterFeignClient {


    /**
     * 开通现金账户
     *
     * @param cashAccount
     * @param customerNo
     * @return
     */
    @Override
    public AppReturnMsgData openCashAccount(CashAccount cashAccount, String customerNo) {
        return new AppReturnMsgData("9999", "服务不可用");
    }

    /**
     * 生成积分交易明细
     *
     * @param appCommonParm
     * @return
     */
    @Override
    public AppReturnMsgData createIntegralTransDetail(AppCommonParm appCommonParm) {
        return new AppReturnMsgData("9999", "服务不可用");
    }

    @Override
    public AppReturnMsgData modifyIntegralTransResult(AppCommonParm appCommonParm) {
        return new AppReturnMsgData("9999", "服务不可用");
    }

    /**
     * 新增现金交易明细
     *
     * @param cashTransDetail
     * @param customerNo
     * @param channel
     * @return
     */
    @Override
    public AppReturnMsgData insertCashTransDetail(CashTransDetail cashTransDetail, String customerNo, String channel) {
        return new AppReturnMsgData("9999", "服务不可用");
    }

    /**
     * 修改现金交易明细
     *
     * @param appCommonParm
     * @return
     */
    @Override
    public AppReturnMsgData modifyCashTransDetail(AppCommonParm appCommonParm) {
        return new AppReturnMsgData("9999", "服务不可用");
    }
}
