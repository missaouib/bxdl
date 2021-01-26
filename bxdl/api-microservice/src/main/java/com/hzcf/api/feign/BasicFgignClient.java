package com.hzcf.api.feign;

import com.hzcf.pojo.account.CashAccount;
import com.hzcf.vo.AppReturnMsgData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <dl>
 * <dt>类名：com.hzcf.account.feign</dt>
 * <dd>描述: 调用basic服务feign客户端</dd>
 * <dd>创建时间：下午 17:33 2018/11/1 0001 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@FeignClient(name = "basic-microservice", fallback = BasicFgignClientFallback.class)
public interface BasicFgignClient {
    @RequestMapping(value = "apiLog/addLog", method = RequestMethod.POST)
    public void addLog(
            @RequestParam("headerInfo") String headerInfo,
            @RequestParam("param") String param,
            @RequestParam("requestURL") String requestURL,
            @RequestParam("callTime")String callTime);
}
