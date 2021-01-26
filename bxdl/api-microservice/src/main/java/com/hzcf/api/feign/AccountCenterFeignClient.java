package com.hzcf.api.feign;

import com.hzcf.pojo.account.CashAccount;
import com.hzcf.pojo.account.CashTransDetail;
import com.hzcf.vo.AppCommonParm;
import com.hzcf.vo.AppReturnMsgData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <dl>
 * <dt>类名：com.hzcf.account.feign</dt>
 * <dd>描述: 调用账户中心feign客户端</dd>
 * <dd>创建时间：下午 15:03 2018/10/23 0023 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@FeignClient(serviceId="account-center-microservice", fallback = AccountCenterFeignFallback.class)
public interface AccountCenterFeignClient {
    /**
     * 开通现金账户
     * @param cashAccount
     * @param customerNo
     * @return
     */
    @RequestMapping(value = "account/openCashAccount", method = RequestMethod.POST)
    public AppReturnMsgData openCashAccount(
            @RequestBody CashAccount cashAccount, @RequestParam("customerNo") String customerNo);

    /**
     * 生成积分交易明细
     * @param appCommonParm
     * @return
     */
    @RequestMapping(value = "account/createIntegralTransDetail", method = RequestMethod.POST)
    AppReturnMsgData createIntegralTransDetail(@RequestBody AppCommonParm appCommonParm);

    /**
     * 获取积分交易结果
     * @param appCommonParm
     * @return
     */
    @RequestMapping(value = "account/modifyIntegralTransResult", method = RequestMethod.POST)
    AppReturnMsgData modifyIntegralTransResult(@RequestBody AppCommonParm appCommonParm);

    /**
     * 新增现金交易明细
     * @param cashTransDetail
     * @param
     * @return
     */
    @RequestMapping(value = "account/insertCashTransDetail", method = RequestMethod.POST)
    AppReturnMsgData insertCashTransDetail(@RequestBody CashTransDetail cashTransDetail,
                                           @RequestParam("customerNo") String customerNo,
                                           @RequestParam("channel") String channel);

    /**
     * 修改现金交易明细
     * @param appCommonParm
     * @return
     */
    @RequestMapping(value = "account/modifyCashTransDetail", method = RequestMethod.POST)
    AppReturnMsgData modifyCashTransDetail(@RequestBody AppCommonParm appCommonParm);
}
