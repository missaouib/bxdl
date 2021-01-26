package com.hzcf.basic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.basic.RedisDistributedLock.RedisDistributedLock;
import com.hzcf.vo.AppReturnMsgData;

/**
 * <dl>
 * <dt>类名：com.hzcf.basic.controller</dt>
 * <dd>描述:分布式锁相关接口 </dd>
 * <dd>创建时间：下午 16:59 2018/11/1 0001 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@RequestMapping("/redis")
@RestController
public class RedisDistributedLockController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final long expireTime = 5L * 1000;
    @Autowired
    RedisDistributedLock redisDistributedLock;

    @RequestMapping(value = "getLock", method = RequestMethod.POST)
    public AppReturnMsgData getLock(
            @RequestParam("key") String key,
            @RequestParam("value") String value
    ) {
        logger.info("进入获取分布式锁流程[key]:" + key +", [value]:" + value);
        boolean flag = false;
        try{
            long beginTime = System.currentTimeMillis();
            int i = 0;

            while (true){

                if(System.currentTimeMillis() - beginTime > expireTime) {
                    return new AppReturnMsgData("9999", "请求超时");
                }
                flag = redisDistributedLock.lock(key, String.valueOf(value));
                if(flag) { //获取锁成功
                    break;
                }
                Thread.sleep(300);
            }
        }catch (Exception e){
            logger.error("请求分布式锁错误", e);
            return new AppReturnMsgData("9999", "系统错误");
        }
        if(flag) {
            return new AppReturnMsgData("0000", "获取锁成功");
        }else {
            return new AppReturnMsgData("9999", "获取锁失败");
        }
    }
    @RequestMapping(value = "unLock", method = RequestMethod.POST)
    public void unLock(
            @RequestParam("key") String key,
            @RequestParam("value") String value
    ) {
        logger.info("进入释放分布式锁流程[key]:" + key +", [value]:" + value);
        try{
            redisDistributedLock.unLock(key, value);
        }catch (Exception e){
            logger.error("分布式锁，解锁错误", e);
        }
    }
}
