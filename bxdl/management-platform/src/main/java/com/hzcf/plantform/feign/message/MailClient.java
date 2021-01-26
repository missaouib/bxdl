package com.hzcf.plantform.feign.message;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.message.Mail;
/**
 * 
 * @author XJin
 * 站内信Feign
 */
@FeignClient(name = "message-center",fallback =FeignDisableHystrixConfiguration.class)
public interface MailClient {
	/**
	 * 查询站内信列表（可条件查询)
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mail/search_mail_list", method = RequestMethod.POST)
	public PageModel searchMailList(@RequestParam Map<String, Object> paramMap);
	/**
	 * 根据ID查询站内信数据
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mail/get_mail_by_id", method = RequestMethod.POST)
	public Mail getMailById(@RequestParam("id") String id);

	/**
	 * 添加站内信
	 * @param mail
	 * @return
	 */
	@RequestMapping(value = "/mail/insert_mail", method = RequestMethod.POST)
	public int insertMail(@RequestBody Mail mail);
	
	/**
	 * 修改站内信信息
	 * @param mail
	 * @return
	 */
	@RequestMapping(value = "/mail/update_mail", method = RequestMethod.POST)
	public int updateMailById(@RequestBody Mail mail);
	
	/**
	 * 删除站内信信息
	 * @param mail
	 * @return
	 */
	@RequestMapping(value = "/mail/delete_mail", method = RequestMethod.POST)
	public int deleteMailById(@RequestParam("id") String id);
}
