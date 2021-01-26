package com.hzcf.basic.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hzcf.basic.mongo.pojo.Logs;
import com.hzcf.basic.service.LogService;
import com.hzcf.basic.util.PageModel;

/**
 * <dl>
 * <dt>类名：com.hzcf.basic.service.impl</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：下午 16:23 2018/8/14 0014 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@Service
public class LogServiceImpl implements LogService{
    Logger logger = Logger.getLogger(LogService.class);
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public void addLog(Logs logs) {
        try{
            mongoTemplate.insert(logs);
        }catch (Exception e){

        }

    }

	@Override
	public PageModel getLogList(Map<String, Object> map) {
		try {
			Integer pageNo = Integer.valueOf(String.valueOf(map.get("pageNo")));
			Integer pageSize = Integer.valueOf(String.valueOf(map.get("pageSize")));
			PageModel pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			Query query = new Query();
			String requestURL = (String) map.get("requestURL");
			String startTime = (String) map.get("startTime");
			String endTime = (String) map.get("endTime");
			if (!StringUtils.isEmpty(requestURL)) {
				query.addCriteria(Criteria.where("requestURL").is(requestURL));
			}
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isEmpty(endTime)) {
				query.addCriteria(Criteria.where("callTime").gte(startTime));
			}
			if (StringUtils.isNotEmpty(endTime) && StringUtils.isEmpty(startTime)) {
				query.addCriteria(Criteria.where("callTime").lte(endTime));
			}
			if (StringUtils.isNotEmpty(endTime) && StringUtils.isNotEmpty(startTime)) {
				query.addCriteria(Criteria.where("callTime").gte(startTime).lte(endTime));
			}
			query.skip(pageModel.getStartIndex());
			query.limit(pageSize);
			query.with(new Sort(Direction.DESC, "callTime"));
			List<Logs> data = mongoTemplate.find(query, Logs.class);
			long count = mongoTemplate.count(query, Logs.class);
			pageModel.setList(data);
			pageModel.setTotalRecords(count);
			return pageModel;
		} catch (Exception e) {
			logger.error("========查询日志列表异常=========="+e);
			return null;
		}
		
	}
}
