package com.hzcf.api.service.impl;

import com.hzcf.api.feign.AccountCenterFeignClient;
import com.hzcf.api.service.AccountService;
import com.hzcf.pojo.account.CashAccount;
import com.hzcf.pojo.account.CashTransDetail;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.service.impl</dt>
 * <dd>描述: 账户相关service实现</dd>
 * <dd>创建时间：下午 15:12 2018/10/30 0030 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@SuppressWarnings("ALL")
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountCenterFeignClient accountCenterFeignClient;

    /**
     * 开通现金账户
     *
     * @param cashAccount
     * @param customerNo
     * @return
     */
    @Override
    public AppReturnMsgData openCashAccount(CashAccount cashAccount, String customerNo) {
        return accountCenterFeignClient.openCashAccount(cashAccount,customerNo );
    }

    @Override
    public AppReturnMsgData insertCashTransDetail(CashTransDetail cashTransDetail, String customerNo, String channel) {
        return accountCenterFeignClient.insertCashTransDetail(cashTransDetail, customerNo, channel);
    }

    /**
     * 生成积分明细
     *
     * @param appCommonParm
     * @return
     */
    @Override
    public AppReturnMsgData createIntegralTransDetail(AppCommonParm appCommonParm) {
        return accountCenterFeignClient.createIntegralTransDetail(appCommonParm);
    }

    /**
     * 获取积分交易结果
     *
     * @param appCommonParm
     * @return
     */
    @Override
    public AppReturnMsgData modifyIntegralTransResult(AppCommonParm appCommonParm) {
        return accountCenterFeignClient.modifyIntegralTransResult(appCommonParm);
    }

    /**
     * 修改现金交易明细
     *
     * @param appCommonParm
     * @return
     */
    @Override
    public AppReturnMsgData modifyCashTransDetail(AppCommonParm appCommonParm) {
        return accountCenterFeignClient.modifyCashTransDetail(appCommonParm);
    }
}
