package com.hzcf.api.feign;

import com.hzcf.vo.AppReturnMsgData;
import org.springframework.stereotype.Component;

/**
 * <dl>
 * <dt>类名：com.hzcf.account.feign</dt>
 * <dd>描述: basic</dd>
 * <dd>创建时间：下午 17:34 2018/11/1 0001 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Component
public class BasicFgignClientFallback implements BasicFgignClient{

    @Override
    public void addLog(String headerInfo, String param, String requestURL, String callTime) {

    }
}
