package com.hzcf.util;

import java.util.UUID;

/**
 * <dl>
 * <dt>类名：com.hzcf.util</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：上午 10:15 2018/10/22 0022 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
public class IdGenerateUtil {
    private static final String UN_PREFIX = "UN";
    private static final String CN_PREFIX = "CN";
    private static final String IN_PREFIX = "IN";

    public static String generateUserNo(long workId){
        return UN_PREFIX  + new IdWorker(workId, 0).nextId();
    }
    public static String generateCustomerNo(long workId){
        return CN_PREFIX  + new IdWorker(workId, 1).nextId();
    }
    public static String generateIntegralAccountNo(long workId){
        return IN_PREFIX  + new IdWorker(workId, 1).nextId();
    }

}
