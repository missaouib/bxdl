<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>部门管理</title>
<link rel="stylesheet" href="${path}/css/system/dept/dept.css" />

<script src="${path}/js/system/dept/dept.js" />
<script src="${path}/static/js/bootstrap.js" />
<!-- treegrid -->
<link  href="https://cdn.bootcss.com/jquery-treegrid/0.2.0/css/jquery.treegrid.min.css" rel="stylesheet">
 <script src="https://cdn.bootcss.com/bootstrap-table/1.12.0/extensions/treegrid/bootstrap-table-treegrid.js"></script>
<script src="${path }/static/js/test/bootstrap-table.min.js"></script>
<script src="${path }/static/js/test/jquery.treegrid.min.js"></script>
</head>
<body>
<!--toolbar  -->
<div id="dept_toolbar" class="btn-toolbar">
<shiro:hasPermission name="departmentManager:add">
		<button type="button" class=" btn btn-info" onclick="Department.addDepartment()">
			<span class="glyphicon glyphicon-plus" >添加</span>
		</button>
</shiro:hasPermission>
<shiro:hasPermission name="departmentManager:update">
		<button type="button" class=" btn btn-info" onclick="Department.getValue()">
			<span class="glyphicon glyphicon-pencil" >修改</span>
		</button>
</shiro:hasPermission>
</div> 
<div >
	<table id="dept-table" class="table table-hover table-striped table-condensed table-bordered"/>
</div>

<!-- 模态框（Modal） -->
<!-- 添加菜单 -->
<div id="dept_addDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加部门</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="addDeptForm"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门名称：</label>
				<div class="col-md-3 ">
					<input type="text" id="dept_name" name="deptName" class="form-control form-control-static">
				</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">一级部门：</label>
			<div class="col-md-3" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input   type="radio"  onchange="Department.addChooseYes()"  name="oneLevel" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;
				<input  type="radio"  onchange="Department.addChooseNo()"  name="oneLevel" value="2">否
			</div>
			</div>
			
			<div id="isShow"  style="display: none; overflow: hidden;">
				<div class="form-group" >
					<label class="col-md-2 control-label">上级部门：</label>
					<div class="col-md-3">
							<input type="text" id="dept_departmentname" name="txt_departmentname" class="form-control" value="" placeholder="上级部门">
							<input type="hidden" id="dept_parentId" name="parentId" class="form-control" value=""placeholder="上级部门">
							<div id="parentIdtreeview" style="z-index: 9999" class=""></div>
					</div>
				</div>
			</div>
			
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门排序：</label>
				<div class="col-md-3" class="form-control form-control-static">
						<input type="text"  name="sort" class="form-control form-control-static" >
				</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3">
				<textarea rows="2" id="remark" name="remark" cols="20" class="form-control form-control-static"></textarea>
			</div>
			</div>
			
			
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
               	 <button type="button" class="btn btn-default" onclick="Department.closeDlg()">关闭</button>
             	  <button id="saveButton" type="button" onclick="Department.saveDepartment()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 修改 -->
<div id="dept_updateDlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改部门</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="updateDeptForm"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门名称：</label>
				<div class="col-md-3 ">
					<input type="hidden" id="update_dept_id" name="deptId" class="form-control form-control-static">
					<input type="text" id="update_deptName" name="deptName" class="form-control form-control-static">
				</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">一级部门：</label>
			<div class="col-md-3" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="yes"  type="radio"  onchange="Department.updateChooseYes()"  name="oneLevel" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="no" type="radio"  onchange="Department.updateChooseNo()"  name="oneLevel" value="2">否
			</div>
			</div>
			
			<div id="update_isShow"  style="display: none; overflow: hidden;">
				<div class="form-group" >
					<label class="col-md-2 control-label">上级部门：</label>
					<div class="col-md-3">
							<input type="text" id="dept_update_departmentname" name="txt_departmentname" class="form-control" value="" placeholder="上级部门">
							<input type="hidden" id="dept_update_parentId" name="parentId" class="form-control" value=""placeholder="上级部门">
							<div id="update_parentIdtreeview" style="z-index: 9999" class=""></div>
					</div>
				</div>
			</div>
			
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门排序：</label>
				<div class="col-md-3" class="form-control form-control-static">
						<input type="text" id="update_sort"  name="sort" class="form-control form-control-static" >
				</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-3">
				<textarea rows="2" id="update_remark" name="remark" cols="20" class="form-control form-control-static"></textarea>
			</div>
			</div>
			
			
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
               	 <button type="button" class="btn btn-default" onclick="Department.closeDlg()">关闭</button>
             	  <button type="button" onclick="Department.updateDepartment()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>