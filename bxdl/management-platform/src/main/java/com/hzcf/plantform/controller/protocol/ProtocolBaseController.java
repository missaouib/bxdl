
package com.hzcf.plantform.controller.protocol;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hzcf.pojo.basic.Employee;
import com.hzcf.util.StringUtil;





public class ProtocolBaseController {
	
	/**
	 * 获取当前登录用户信息
	 * @return
	 */
//	public Employee getCurrentEmployee () {
//		Subject subject = SecurityUtils.getSubject();
//		Employee employee = (Employee) subject.getPrincipal();
//		return employee;
//	}

	/**
	 * 判断是否是查看
	 */
	public boolean isLook(String ids){
		String look =ids.split(":")[0];
		if(StringUtil.isNotEmpty(look) && look.equals("look")){
			return true;
		}else{
			return false;
		}
	}
	
	public String checkIsLook(HttpServletRequest request, String protocolIds) {
		boolean look = isLook(protocolIds);
		String protocolId = null;
		if(look){
			protocolId = protocolIds.split(":")[1];
			request.setAttribute("isLook", "yes");
		}else{
			protocolId = protocolIds;
		}
		return protocolId;
	}
	
}
