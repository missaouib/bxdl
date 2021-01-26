<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>员工管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/employee/employeePage.css">
   	<script src="${path}/js/system/employee/employeePage.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
<style>
.panel-body .form-inline {margin-bottom: 0;}
.panel-body .form-group {margin-bottom: 0;}

.btn-toolbar {margin-bottom: 5px;}
/* 弹出框样式 */
.modal { padding-left: 0!important;}
.modal-dialog {	background: #fff;}
.modal-dialog .row { margin-left: 0; margin-right: 0;}
.modal-dialog .form-horizontal { padding-top: 15px;}
.modal-dialog .form-horizontal .form-group { padding-left: 100px;}
.modal-content { box-shadow: none;}
</style>
</head>
<body>

<div class="panel panel-default">
	<div class="panel-body">
		<form id="emplpyeeConForm" class="form-inline hz-form-inline">
		  <div class="form-group">
		  		<label class="control-label">员工编号</label>
		   		<input type="text" class="form-control" id="search_employee_no" name="employeeNo" placeholder="请输入员工编号">
		  </div>
		   <div class="form-group">
		   		<label class="control-label">员工姓名</label>
		   		<input type="text" class="form-control" id="search_employee_name" name="employeeName" placeholder="请输入员工姓名">
		  </div>
		  <div class="form-group">
	  		<button type="button" onclick="Employee.searchEmployee()" class="btn btn-info ">
	   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
	   		</button>
	   		<button type="button" onclick="Employee.empty()" class="btn btn-danger ">
	   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
	   		</button>
	   	</div>
</form>
	</div>
</div>
<!--toolbar  -->
<div id="employeeToolbar" class="btn-toolbar">
	<shiro:hasPermission name="employeeManager:add">
    <button class=" btn btn-info" type="button" onclick="Employee.openDlg()">
        <span class="glyphicon glyphicon-plus" >添加用户</span>
    </button>
</shiro:hasPermission>
    <shiro:hasPermission name="employeeManager:add">
        <button class=" btn btn-info" type="button" onclick="Employee.openUpdateModal()">
            <span class="glyphicon glyphicon-plus" >修改用户</span>
        </button>
    </shiro:hasPermission>
  <shiro:hasPermission name="employeeManager:updateRole">
  		<button type="button" class=" btn btn-info" onclick="Employee.getRole()">
  				<span class="glyphicon glyphicon-ok-circle">角色分配</span>
  		</button>
  </shiro:hasPermission>
  
   <shiro:hasPermission name="employeeManager:delete">
  		<button class=" btn btn-info" type="button" onclick="Employee.deleteEmployee(1)">
  			<span class="glyphicon glyphicon-ok-circle" >启用</span>
  		</button>
  </shiro:hasPermission>
  <shiro:hasPermission name="employeeManager:delete">
  		<button class=" btn btn-danger" type="button" onclick="Employee.deleteEmployee(3)">
  			<span class="glyphicon glyphicon-remove" >禁用</span>
  		</button>
  </shiro:hasPermission>


</div>
<!--列表 -->
<table id="employee-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!-- 添加员工 -->
<div id="employeeAddDlg" class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="height: 450px;width: 605px;">
    <div class="modal-dialog">
    		<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加员工</h4>
            </div>
        <div class="modal-content">
            <div class="row">
				<form class="form-horizontal" id="addEmpForm"  method="post">
					<input name="deptId" id="deptId" type="hidden" />
					<input name="deptNo" id="deptNo" type="hidden" />
					<div class="form-group">
						<label class="col-md-3 control-label">*所属机构：</label>
						<div class="col-md-6 ">
							<select class="form-control form-control-static" id="deptInfo" name="deptInfo">
								<option value="">请选择所属机构</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">*员工姓名：</label>
						<div class="col-md-6 ">
							<input type="text"  id="employee_name" name="name" class="form-control form-control-static">
						</div>
					</div>
					<%--<div class="form-group">--%>
						<%--<label class="col-md-3 control-label">性别：</label>--%>
						<%--<div class="col-md-6 ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--<input type="radio"  name="sex" value="0">男&nbsp;&nbsp;&nbsp;&nbsp;--%>
		                   <%--<input type="radio"  name="sex" value="1">女--%>
						<%--</div>--%>
					<%--</div>--%>
					<%--<div class="form-group">--%>
						<%--<label class="col-md-3 control-label">邮编：</label>--%>
						<%--<div class="col-md-6 ">--%>
							<%--<input type="text"  id="email" name="email" class="form-control form-control-static">--%>
						<%--</div>--%>
					<%--</div>--%>
					
					<div class="form-group">
						<label class="col-md-3 control-label">*员工编号：</label>
						<div class="col-md-6 ">
							<input type="text"  id="employee_no" name="employeeNo" class="form-control form-control-static">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">手机号：</label>
						<div class="col-md-6 ">
							<input type="text"  id="mobile" name="mobile" class="form-control form-control-static">
						</div>
					</div>
					
					<%--<div class="form-group">--%>
						<%--<label class="col-md-3 control-label">固定电话：</label>--%>
						<%--<div class="col-md-6 ">--%>
							<%--<input type="text"  id="telephone" name="telephone" class="form-control form-control-static">--%>
						<%--</div>--%>
					<%--</div>--%>
					
					<div class="form-group">
						<label class="col-md-3 control-label">身份证号：</label>
						<div class="col-md-6 ">
							<input type="text"  id="card_no" name="cardNo" class="form-control form-control-static">
						</div>
					</div>
					
					<%--<div class="form-group">--%>
						<%--<label class="col-md-3 control-label">入职时间：</label>--%>
						<%--<div class="col-md-6 ">--%>
							<%--<input type="text" id="entry_time" name="entryDate" onfocus="WdatePicker({lang:'zh-cn'})" class="form-control form-control-static"/>--%>
						<%--</div>--%>
					<%--</div>--%>
					
					<%--<div class="form-group">--%>
						<%--<label class="col-md-3 control-label">所属部门：</label>--%>
						<%--<div class="col-md-6">--%>
							<%--<input type="text" id="department_name" name="departmentName" class="form-control" value="" onclick="" placeholder="所属部门">--%>
							<%--<input type="hidden" id="employee_dept_id" name="deptId" class="form-control" value="" onclick="" placeholder="所属部门">--%>
							<%--<div id="parentIdtreeview" style="z-index: 9999" class=""></div>--%>
						<%--</div>--%>
					<%--</div>--%>
					
					<div class="form-group" style="display: none">
						<label class="col-md-3 control-label">默认密码：</label>
						<div class="col-md-6 ">
							<input type="text"  id="password" name="password" value="123456" class="form-control form-control-static" placeholder="123456" readonly="readonly">
						</div>
					</div>
	            </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
            <!--用来清空表单数据-->
            	<input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="Employee.closeDlg()">关闭</button>
               <button id="saveButton"  type="button" onclick="Employee.saveEmployee()" class="btn btn-primary">提交</button>
        </div>
    </div><!-- /.modal -->
</div> 


<!-- 修改员工信息 -->
<div id="employeeUpdateDlg" class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="height: 450px;width: 605px;">
    <div class="modal-dialog">
    	<div class="modal-header">
             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <h4 class="modal-title" id="myModalLabel">修改员工信息</h4>
         </div>
        <div class="modal-content">
            <div class="row">
			<form class="form-horizontal" id="employeeUpdateForm"  method="post">
                <input name="employeeId" id="update_employee_id" type="hidden" />
                <input name="deptId" id="update_deptId" type="hidden" />
                <input name="deptNo" id="update_deptNo" type="hidden" />
                <div class="form-group">
                    <label class="col-md-3 control-label">*所属机构：</label>
                    <div class="col-md-6 ">
                        <select class="form-control form-control-static" id="update_deptInfo" name="deptInfo">
                            <option value="">请选择所属机构</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">*员工姓名：</label>
                    <div class="col-md-6 ">
                        <input type="text"  id="update_employee_name" name="name" class="form-control form-control-static">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">*员工编号：</label>
                    <div class="col-md-6 ">
                        <input type="text"  id="update_employee_no" name="employeeNo" class="form-control form-control-static" readonly />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">手机号：</label>
                    <div class="col-md-6 ">
                        <input type="text"  id="update_mobile" name="mobile" class="form-control form-control-static">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">身份证号：</label>
                    <div class="col-md-6 ">
                        <input type="text"  id="update_card_no" name="cardNo" class="form-control form-control-static">
                    </div>
                </div>
            
            </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="Employee.closeDlg()">关闭</button>
               <button type="button" onclick="Employee.updateEmployee()" class="btn btn-primary">提交</button>
            </div>
    </div><!-- /.modal -->
</div> 

<!-- 模态框（Modal） -->
<!-- 角色分配-->
<div id="employeeRoleDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static"
	 data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="top: 0px">

	<div class="modal-dialog">
    	<div class="modal-header">
             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <h4 class="modal-title" id="myModalLabel">分配角色</h4>
         </div>
        <div class="modal-content" style="height: 350px;overflow-y: scroll;">
			<div class="form-group">
				<input type="hidden" id="employee_rid" />
				<table class="table">
					<thead>
						<tr>
							<th><input type="checkbox" id="allCheckedId" style="width: 16px;height: 16px;"/></th>
							<th>角色编号</th>
							<th>角色名称</th>
							<th>角色描述</th>
						</tr>
					</thead>
					<tbody id="roleTbody" >

					</tbody>
				</table>
			</div>
        </div><!-- /.modal-content -->
         <div class="modal-footer" style="position: fixed;bottom: -52px;right: 0px;width: 100%;height: 62px;background: #fff;">
			 <button type="button" class="btn btn-default" onclick="Employee.closeDlg()">关闭</button>
			 <button type="button" onclick="Employee.saveRole()" class="btn btn-primary">保存</button>
		 </div>
    </div><!-- /.modal -->

</div>
<!-- 查看拥有角色 -->
<div id="ownEmployeeRolesDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static"
	 data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="top: 0px">

	<div class="modal-dialog">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="ownEmpRolesModalLabel">查看角色</h4>
		</div>
		<div class="modal-content" style="height: 350px;overflow-y: scroll;">
			<div class="form-group">
				<table class="table">
					<thead>
					<tr>
						<th>角色编号</th>
						<th>角色名称</th>
						<th>角色描述</th>
					</tr>
					</thead>
					<tbody id="ownEmpRolesTbody" >

					</tbody>
				</table>
			</div>
		</div><!-- /.modal-content -->
		<div class="modal-footer" style="position: fixed;bottom: -52px;right: 0px;width: 100%;height: 62px;background: #fff;">
			<button type="button" class="btn btn-default" onclick="Employee.closeDlg()">关闭</button>
		</div>
	</div><!-- /.modal -->

</div>
</body>
</html>