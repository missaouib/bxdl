package com.hzcf.core.util;

import java.util.List;

public class ProtocolTreeView {

	private String text;
	private String href;
	private String tags;
	private String icon;
	private Integer id;
	private String pid;
	private List<ProtocolTreeView> nodes;
	private Trees state;
	public Trees getState() {
		return state;
	}
	public void setState(Trees state) {
		this.state = state;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<ProtocolTreeView> getNodes() {
		return nodes;
	}
	public void setNodes(List<ProtocolTreeView> nodes) {
		this.nodes = nodes;
	}
}
