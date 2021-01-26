package com.hzcf.plantform.feign.message;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.message.Notice;

/**
 * 
 * @author XJin
 * 公告
 */
@FeignClient(name = "message-center" ,fallback = FeignDisableHystrixConfiguration.class)
public interface NoticeClient {
	/**
	 * 查询公告（可条件查询）
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/notice/seach_notice_list",method = RequestMethod.POST)
	public PageModel searchNoticeList(@RequestParam Map<String, Object> paramMap);
	/**
	 * 根据ID查询公告数据
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/notice/get_notice_by_id", method = RequestMethod.POST)
	public Notice getNoticeById(@RequestParam("id") String id);

	/**
	 * 添加公告
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/notice/insert_notice",method = RequestMethod.POST)
	public int insertNotice(@RequestBody Notice notice);

	/**
	 * 修改公告
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/notice/update_notice",method = RequestMethod.POST)
	public int updateNoticeById(@RequestBody Notice notice);

	/**
	 * 删除公告
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/notice/delete_notice",method = RequestMethod.POST)
	public int deleteNoticeById(@RequestParam("id") String id);

}
