package com.hzcf.api.async;

import com.hzcf.api.feign.BasicFgignClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.async</dt>
 * <dd>描述: 异步执行</dd>
 * <dd>创建时间：下午 14:03 2018/11/7 0007 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@SuppressWarnings("ALL")
@Component
public class MyAsyncTask {

    @Autowired
    private BasicFgignClient basicFgignClient;

    Logger logger = Logger.getLogger(MyAsyncTask.class);
    @Async
    public void asyncInsertApiLog(String headerInfo, String param, String uri, String callTime) {
        try{
            basicFgignClient.addLog(headerInfo, param, uri, callTime);
        }catch (Exception e){
            logger.error("调用basic服务插入api日志信息错误", e);
        }

    }
}
