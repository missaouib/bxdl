/**
 * 
 */
/**
 *<dl>
 *<dt>类名：package-info.java</dt>
 *<dd>描述: app统一vo类</dd> 
 *<dd>创建时间： 2018年5月25日 上午9:06:45</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2018年5月25日 上午9:06:45    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
package com.hzcf.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

public class AppReturnMsgData implements Serializable{

	private static final long serialVersionUID = 5014607822539057104L;
	private String returnCode;// 返回状态码
	private String returnMsg;// 返回状态描述信息
	private Object data;// 返回参数表
	public AppReturnMsgData(){};

	public AppReturnMsgData(String returnCode, String returnMsg, Object data) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.data = data;
	}

	public AppReturnMsgData(String returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.data=new HashMap<>();
	}
	public <T> T parseDataFieldToPojo(Class<T> cls){
		if(data == null){
			return null;
		}
		return JSONObject.parseObject(JSONObject.toJSONString(data), cls);
	}
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
