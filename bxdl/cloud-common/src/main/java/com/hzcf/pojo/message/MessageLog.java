package com.hzcf.pojo.message;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Xjin
 * 短信息DTO
 */
public class MessageLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String business_line; //业务线（CFSYB:财富事业部）
	private String terminal; //发送端
	private String content; //短信内容
	private String sending_time_start; //发送时间开始
	private String sending_time_end; //发送时间结束
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date sended_time; //已发送时间
	private String trigger_type; //触发类型（000:无触发点，001:发送验证码，002:出借成功）
	private String status; //发送状态（1:执行中，0:执行完毕）
	private String is_delete; //是否删除（0:删除,1:正常）
	private String object; //发送对象
	private String is_circulate; //是否循环发送（0:否，1:是）
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date update_time;
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date one_send_time;//一次性发送时间
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd")
	private Date circulate_date_start;//循环发送开始日期
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd")
	private Date circulate_date_end;//循环发送结束日期
	private String circulate_day;//循环发送日，多个日期，逗号隔开
	@JsonFormat(locale="zh", pattern="HH:mm:ss")
	private Date circulate_time;//循环发送HH:mm:ss
    private String original_filename;//原始文件名

    public String getOriginal_filename() {
        return original_filename;
    }

    public void setOriginal_filename(String original_filename) {
        this.original_filename = original_filename;
    }

    public Date getCirculate_time() {
		return circulate_time;
	}

	public void setCirculate_time(Date circulate_time) {
		this.circulate_time = circulate_time;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Date getOne_send_time() {
		return one_send_time;
	}

	public void setOne_send_time(Date one_send_time) {
		this.one_send_time = one_send_time;
	}

	public Date getCirculate_date_start() {
		return circulate_date_start;
	}

	public void setCirculate_date_start(Date circulate_date_start) {
		this.circulate_date_start = circulate_date_start;
	}

	public Date getCirculate_date_end() {
		return circulate_date_end;
	}

	public void setCirculate_date_end(Date circulate_date_end) {
		this.circulate_date_end = circulate_date_end;
	}

	public String getCirculate_day() {
		return circulate_day;
	}

	public void setCirculate_day(String circulate_day) {
		this.circulate_day = circulate_day;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusiness_line() {
		return business_line;
	}
	public void setBusiness_line(String business_line) {
		this.business_line = business_line;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSending_time_start() {
		return sending_time_start;
	}
	public void setSending_time_start(String sending_time_start) {
		this.sending_time_start = sending_time_start;
	}
	public String getSending_time_end() {
		return sending_time_end;
	}
	public void setSending_time_end(String sending_time_end) {
		this.sending_time_end = sending_time_end;
	}
	public Date getSended_time() {
		return sended_time;
	}
	public void setSended_time(Date sended_time) {
		this.sended_time = sended_time;
	}
	public String getTrigger_type() {
		return trigger_type;
	}
	public void setTrigger_type(String trigger_type) {
		this.trigger_type = trigger_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getIs_circulate() {
		return is_circulate;
	}
	public void setIs_circulate(String is_circulate) {
		this.is_circulate = is_circulate;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}
