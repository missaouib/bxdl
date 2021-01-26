/*
package com.hzcf.plantform.hystrixCallback;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.Role;
@Component
public class EmployeeClientFallback  implements EmployeeFeignClient {

	@Override
	public Employee getEmployeeById(int employeeId) {
		
		return null;
	}

	@Override
	public PageModel getEmployeeList(Map<String, Object> paramsCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getRoleByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}


	

	@Override
	public void updateEmployeeRole(int employeeId, String rids) {
		// TODO Auto-generated method stub
		
	}

	


	@Override
	public boolean checkOldPwd(int employeeId, String oldPwd, String newPs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePwd(int employeeId, String newPwd, String newPs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveEmployee(Employee employee, String newPs,@RequestParam("uuid")String uuid) {
		
	}

	@Override
	public void updateEmployee(Employee employee, String newPs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(Employee employee, String activatedState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployeeByEmployeeNo(Map<String, Object> emap) {
<<<<<<< HEAD
=======
		// TODO Auto-generated method stub
>>>>>>> 0e3f261a0d44a6392528fa7aaf628361828feac8
		return null;
	}


}
*/
