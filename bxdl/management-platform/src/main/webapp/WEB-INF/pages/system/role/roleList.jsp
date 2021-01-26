<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理</title>
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/system/role/role.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
<style>
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
<!--获取用员工号标签  -->
<input type="hidden" id="userEmployeeNo"  value ='<shiro:principal property="employeeNo"/>' />
<!--toolbar  -->
<div id="roleToolbar" class="btn-toolbar">
	<shiro:hasPermission name="roleManager:add">
	    	<button class="btn btn-info" type="button" onclick="Role.openAddModal()">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
	    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="roleManager:update">
    	<button type="button" class=" btn btn-info" onclick="Role.openUpdateModal()">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="roleManager:delete">
    	<button class=" btn btn-danger" type="button" onclick="Role.deleteRole()">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="roleManager:updateRoleAuth">
    	<button id="userCondition" type="button" class="btn btn-info" style="display:none" onclick="Role.getAuth()">
    		<span class="glyphicon glyphicon-ok-circle">权限配置</span>
    	</button>
    </shiro:hasPermission>
</div> 
<table id="role-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="roleAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
    	<div class="modal-header">
             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <h4 class="modal-title" id="myModalLabel">添加角色</h4>
         </div>
       	<div class="modal-content"> 
            <div class="row">
				<form class="form-horizontal" id="addRoleForm"  method="post">			
					<div class="form-group">
						<label class="col-md-3 control-label">角色名称：</label>
						<div class="col-md-6 ">
							<input type="text" id="role_name"  name="roleName" class="form-control form-control-static" placeholder="请输入角色名称">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">角色编码：</label>
						<div class="col-md-6 ">
							<input type="text" id="role_code"  name="roleCode" class="form-control form-control-static" placeholder="请输入角色编码">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">角色描述：</label>
						<div class="col-md-6">
							<textarea rows="3" id="remark" name="remark" cols="30" class="form-control form-control-static" placeholder="请输入角色 描述"></textarea>
						</div>
					</div>
	            </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
        	<!--用来清空表单数据-->
        	<input type="reset" name="reset" style="display: none;" />
            <button type="button" class="btn btn-default" onclick="Role.closeDlg()">关闭</button>
           <button id="saveButton" type="button" onclick="Role.addRole()" class="btn btn-primary">保存</button>
        </div>        
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="roleMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改角色</h4>
            </div>
            <div class="row">
			<form class="form-horizontal" id="updateRoleForm"  method="post">
			<!-- <div class="form-group">
				<div class="col-md-6 "> 
			-->
					<input type="hidden" id="role_update_id" name="id"  class="form-control form-control-static" readonly="readonly" placeholder="必填">
			<!-- 	</div>
			</div>
			-->
			
			<div class="form-group">
				<label class="col-md-3 control-label">角色名称：</label>
				<div class="col-md-6 ">
					<input type="text" id="role_update_roleName"  name="roleName" class="form-control form-control-static" placeholder="必填">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label">角色编码：</label>
				<div class="col-md-6">
					<input type="text" id="role_update_roleCode"  name="roleCode" class="form-control form-control-static" placeholder="必填">
				</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-3 control-label">角色描述：</label>
				<div class="col-md-6">
					<textarea rows="3" id="role_update_remark" name="remark" cols="30" class="form-control form-control-static" placeholder="必填"></textarea>
				</div>
			</div>
            <div class="modal-footer col-md-12">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="Role.closeDlg()">关闭</button>
               <button type="button" onclick="Role.updateRole()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 权限-->
<div id="roleAuthDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true"
padding-left="180px">
    <div class="modal-dialog">
    	<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">分配权限</h4>
        </div>
        <div class="modal-content">            
            <div class="row" style="height: 340px; overflow: hidden; overflow-y: scroll;">
				<form class="form-horizontal" id="authForm"  method="post">		
					<div class="form-group" style="padding-left:30px;">
						<label class="col-md-3 control-label">权限列表：</label>
						<div class="col-md-8">
							<input type="hidden" id="rid" name="role_id">
							<div id="treeview-checkable" class=""></div>
						</div>
					</div>
		            
	            </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
         		<!--用来清空表单数据-->
         		<input type="reset" name="reset" style="display: none;" />
             <button type="button" class="btn btn-default" onclick="Role.closeDlg()">关闭</button>
            <button type="button" onclick="Role.saveAuth()" class="btn btn-primary">保存</button>
         </div>
    </div><!-- /.modal -->
</div>
</body>
</html>