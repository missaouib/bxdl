package com.hzcf.basic.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.basic.mongo.pojo.Logs;
import com.hzcf.basic.service.LogService;
import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.RedisUtil;

/**
 * <dl>
 * <dt>类名：com.hzcf.basic.controller</dt>
 * <dd>描述: 日志管理</dd>
 * <dd>创建时间：下午 15:49 2018/8/14 0014 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@SuppressWarnings("ALL")
@RequestMapping("/apiLog")
@RestController
public class LogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LogService logService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/addLog", method = RequestMethod.POST)
    public void addLog(
            @RequestParam("headerInfo") String headerInfo,
            @RequestParam("param") String param,
            @RequestParam("requestURL") String requestURL,
            @RequestParam("callTime")String callTime
    ) {
        Map<String, Object> resMap = new HashMap<>();
        DateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Logs logs = new Logs();
        logs.setHeaderInfo(headerInfo);
        logs.setParam(param);
        logs.setRequestURL(requestURL);
        logs.setCallTime(callTime);
        logs.setCreateTime(format.format(new Date()));
        logService.addLog(logs);
    }
    
    /**
	 * 分页查询所有
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getLogList")
	@ResponseBody
	public PageModel getLogList(@RequestParam Map<String, Object> map ) {
		map.put("pageNo", Integer.valueOf((String) map.get("pageNo")));
		map.put("pageSize", Integer.valueOf((String) map.get("pageSize")));	
		return logService.getLogList(map);
	}
}
