package com.hzcf.api.service;

import com.hzcf.pojo.account.CashAccount;
import com.hzcf.pojo.account.CashTransDetail;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.service</dt>
 * <dd>描述: 账户相关service</dd>
 * <dd>创建时间：下午 15:12 2018/10/30 0030 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
public interface AccountService {
    /**
     * 开通现金账户
     * @param cashAccount
     * @param customerNo
     * @return
     */
    AppReturnMsgData openCashAccount(CashAccount cashAccount, String customerNo);

    AppReturnMsgData insertCashTransDetail(CashTransDetail cashTransDetail, String customerNo, String channel);

    /**
     * 生成积分明细
     * @param appCommonParm
     * @return
     */
    AppReturnMsgData createIntegralTransDetail(AppCommonParm appCommonParm);

    /**
     * 获取积分交易结果
     * @param appCommonParm
     * @return
     */
    AppReturnMsgData modifyIntegralTransResult(AppCommonParm appCommonParm);

    /**
     * 修改现金交易明细
     * @param appCommonParm
     * @return
     */
    AppReturnMsgData modifyCashTransDetail(AppCommonParm appCommonParm);
}
