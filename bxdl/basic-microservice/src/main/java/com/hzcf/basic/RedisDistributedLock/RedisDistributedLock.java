package com.hzcf.basic.RedisDistributedLock;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * <dl>
 * <dt>类名：com.hzcf.basic.RedisDistributedLock</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：下午 18:07 2018/10/31 0031 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Component
public class RedisDistributedLock{

    Logger logger = Logger.getLogger(RedisDistributedLock.class);
    @Autowired
    protected StringRedisTemplate redisTemplate;
    private static final long expireTime = 10;

    public boolean lock(String key, String value) {

        String status = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Jedis jedis = (Jedis) redisConnection.getNativeConnection();
                return jedis.set(key, value, "nx", "ex", expireTime);
            }
        });
        if("OK".equals(status)) {
            return true;
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unLock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue)
                    && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            logger.error("【redis分布式锁】 解锁异常，{}", e);
        }
    }
}
