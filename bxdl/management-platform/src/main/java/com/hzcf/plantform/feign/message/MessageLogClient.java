package com.hzcf.plantform.feign.message;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.message.MessageLog;

/**
 * 
 * @author XJin
 * 短信息Feign
 */
@FeignClient(name = "message-center",fallback = FeignDisableHystrixConfiguration.class)
public interface MessageLogClient {
	/**
	 * 查询短信息信息（可条件查询）
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/messageLog/search_messageLog_list",method = RequestMethod.POST)
	public PageModel searchMessageLogList(@RequestParam Map<String, Object> paramMap);
	/**
	 * 根据ID查询短信数据
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/messageLog/get_messageLog_by_id", method = RequestMethod.POST)
	public MessageLog getMessageLogById(@RequestParam("id") String id);
	/**
	 * 添加短信息
	 * @param messageLog
	 * @return
	 */
	@RequestMapping(value = "/messageLog/insert_messageLog",method = RequestMethod.POST)
	public int insertMessageLog(@RequestBody MessageLog messageLog);

	/**
	 * 修改短信息
	 * @param messageLog
	 * @return
	 */
	@RequestMapping(value = "/messageLog/update_messageLog",method = RequestMethod.POST)
	public int updateMessageLogById(@RequestBody MessageLog messageLog);
	/**
	 * 删除短信息
	 * @param messageLog
	 * @return
	 */
	@RequestMapping(value = "/messageLog/delete_messageLog",method = RequestMethod.POST)
	public int deleteMessageLogById(@RequestParam("id") String id);

}
