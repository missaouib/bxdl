package com.hzcf.pojo.message;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Xjin
 * 站内信DTO
 */
public class Mail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String business_line; //业务线
	private String terminal; //发送端
	private String trigger_type; //触发类型
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date sended_time; //发布时间
	private String title; //站内信标题
	private String content; //站内信内容
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date update_time;
	private String is_delete; //0:正常 1:删除
	private String status; //0:正常 1:结束
	private String object;//发送对象
    private String original_filename;//原始文件名

    public String getOriginal_filename() {
        return original_filename;
    }

    public void setOriginal_filename(String original_filename) {
        this.original_filename = original_filename;
    }

    public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getTrigger_type() {
		return trigger_type;
	}
	public void setTrigger_type(String trigger_type) {
		this.trigger_type = trigger_type;
	}
	public Date getSended_time() {
		return sended_time;
	}
	public void setSended_time(Date sended_time) {
		this.sended_time = sended_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	

	
}
