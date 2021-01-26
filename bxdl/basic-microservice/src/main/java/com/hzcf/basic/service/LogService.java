package com.hzcf.basic.service;

import java.util.List;
import java.util.Map;

import com.hzcf.basic.mongo.pojo.Logs;
import com.hzcf.basic.util.PageModel;

/**
 * <dl>
 * <dt>类名：com.hzcf.basic.service</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：下午 16:22 2018/8/14 0014 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
public interface LogService {
    public void addLog(Logs logs);

    /**
     * 查询日志列表
     * @param map
     */
	public PageModel getLogList(Map<String, Object> map);
}
