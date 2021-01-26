package com.hzcf.pojo.message;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Xjin
 * 公告DTO
 */
public class Notice implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String business_line; //业务线（CFSYB:财富事业部）
	private String terminal; //发送端
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date release_time; //发布时间
	private String title; //标题
	private String content; //公告内容
	private String is_delete; //是否删除
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	@JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
	private Date update_time;
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
	public Date getRelease_time() {
		return release_time;
	}
	public void setRelease_time(Date release_time) {
		this.release_time = release_time;
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
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
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
