<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单管理</title>
<link rel="stylesheet" href="${path}/css/system/menu/menu.css" />

<script src="${path}/js/system/menu/menu.js" />
<script src="${path}/static/js/bootstrap.js" />
<!-- treegrid -->
<link  href="https://cdn.bootcss.com/jquery-treegrid/0.2.0/css/jquery.treegrid.min.css" rel="stylesheet">
 <script src="https://cdn.bootcss.com/bootstrap-table/1.12.0/extensions/treegrid/bootstrap-table-treegrid.js"></script>
<script src="${path }/static/js/test/bootstrap-table.min.js"></script>
<script src="${path }/static/js/test/jquery.treegrid.min.js"></script>
	<link href="${path }/static/js/plugins/treeview/bootstrap-treeview.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${path }/static/js/plugins/treeview/bootstrap-treeview.js"></script>
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
<body class="fixed-sidebar full-height-layout gray-bg">
<!--toolbar  -->
<div id="menuToolbar" class="btn-toolbar">
<shiro:hasPermission name="menuManager:add">
		<button type="button" class=" btn btn-info" onclick="Menu.addMenu()">
			<span class="glyphicon glyphicon-plus" >添加</span>
		</button>
</shiro:hasPermission>
<shiro:hasPermission name="menuManager:update">
		<button type="button" class=" btn btn-info" onclick="Menu.getValue()">
			<span class="glyphicon glyphicon-pencil" >修改</span>
		</button>
</shiro:hasPermission>
<shiro:hasPermission name="menuManager:delete">
		<button class=" btn btn-danger" type="button" onclick="Menu.delMenu()">
			<span class="glyphicon glyphicon-remove" >删除</span>
		</button>
</shiro:hasPermission> 
</div> 

<div style="width: 99%;overflow: auto;">
	<table id="menu-table" class="table table-hover table-striped table-condensed table-bordered" style="min-width:99%;"></table>
</div>



 
<!-- 模态框（Modal） -->
<!-- 添加菜单 -->
<div id="menuAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true"  >
    <div class="modal-dialog">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">添加菜单</h4>
		</div>
        <div class="modal-content">           
            <div class="row"> 
			<form class="form-horizontal" id="addMenuForm"  method="post">			
				<div class="form-group">
					<label class="col-md-3 control-label">菜单名称：</label>
					<div class="col-md-6 ">
						<input type="text" id="nameZh" name="nameZh" class="form-control form-control-static">
					</div>
				</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label">请求地址：</label>
				<div class="col-md-6">
					<input type="text"  name="menuUrl" class="form-control form-control-static" >
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label">菜单类型：</label>
				<div class="col-md-6">
					<input type="text"  name="menuType" class="form-control form-control-static" placeholder="请输入menu或者button" >
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="col-md-3 control-label">权限标识：</label>
				<div class="col-md-6">
					<input type="text"  name="permission" class="form-control form-control-static">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label">一级菜单：</label>
				<div class="col-md-6" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input   type="radio"  onchange="Menu.addChooseYes()"  name="oneLevel" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="radio"  onchange="Menu.addChooseNo()"  name="oneLevel" value="2">否
				</div>
			</div>
			
			<div id="menuIsShow"  style="display: none; overflow: hidden;">
				<div class="form-group" >
					<label class="col-md-3 control-label">父级菜单：</label>
					<div class="col-md-6">
						<input type="text" id="txt_parentMenuName" name="menuName" class="form-control" value="" onclick="" placeholder="父级菜单">
						<input type="hidden" id="parentMenuId" name="parentId" class="form-control" value="" onclick="" placeholder="父级菜单">
						<div id="menu_parentIdtreeview" style="z-index: 9999" class=""></div>
					</div>
				</div>
			</div>
			
			<div class="form-group" style="z-index: 0" >
				<label class="col-md-3 control-label">图标样式：</label>
				<div class="col-md-6">
					<input type="text"  name="menuIcon" class="form-control form-control-static" >
				</div>
			</div>
			
			<div class="form-group" style="z-index: 0">
				<label class="col-md-3 control-label">状态：</label>
				<div class="col-md-6" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input   type="radio" checked name="menuStatus" value="1">启用&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="radio" name="menuStatus" value="2">禁用
				</div>
			</div>
			
			<div class="form-group" style="z-index: 0">
				<label class="col-md-3 control-label">排序：</label>
				<div class="col-md-6" class="form-control form-control-static">
						<input type="text"  name="menuSort" class="form-control form-control-static" >
				</div>
			</div>
			
			<div class="form-group" style="z-index: 0">
				<label class="col-md-3 control-label">菜单描述：</label>
				<div class="col-md-6">
					<textarea rows="2" id="remark" name="remark" cols="20" class="form-control form-control-static"></textarea>
				</div>
			</div>
            </form>
            </div>
        </div><!-- /.modal-content -->
         <div class="modal-footer">
            	<!--用来清空表单数据-->
            	<input type="reset" name="reset" style="display: none;" />
               	 <button type="button" class="btn btn-default" onclick="Menu.closeDlg()">关闭</button>
             	  <button id="saveButton" type="button" onclick="Menu.saveMenu()" class="btn btn-primary">保存</button>
         </div>
    </div><!-- /.modal -->
</div>


<!-- 修改 -->
<div id="menuUpdateDlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
    		<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改菜单</h4>
            </div>
        <div class="modal-content">
            
            <div class="row">
			<form class="form-horizontal" id="updateMenuForm"  method="post">
				<div class="form-group">
					<label class="col-md-3 control-label">菜单名称：</label>
					<div class="col-md-6 ">
						<input type="hidden" id="update_menu_id" name="menuId" value="${ menu.menuId}">
						<input type="text" id="update_menu_name" name="nameZh" class="form-control form-control-static">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">请求地址：</label>
					<div class="col-md-6">
						<input type="text" id="update_menu_url" name="menuUrl" class="form-control form-control-static" placeholder="请输入请求地址" >
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">菜单类型：</label>
					<div class="col-md-6">
						<input type="text"  id="update_menu_type" name="menuType" class="form-control form-control-static" placeholder="请输入菜单类型" >
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">权限标识：</label>
					<div class="col-md-6">
						<input type="text"id="update_menu_permission"  name="permission" class="form-control form-control-static" placeholder="请输入权限标识" >
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">一级菜单：</label>
					<div class="col-md-6" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input  id="yes" type="radio"  onchange="Menu.updateChooseYes()"  name="oneLevel" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;
						<input  id= "no" type="radio"  onchange="Menu.updateChooseNo()"  name="oneLevel" value="2">否
					</div>
				</div>


			<div id="menuUpdateIsShow"  style="display: none; overflow: hidden;">
				<div class="form-group" >
					<label class="col-md-3 control-label">父级菜单：</label>
					<div class="col-md-6">
						<input type="text" id="update_parentMenuName" name="menuName" class="form-control" value="" onclick="" placeholder="父级菜单">
						<input type="hidden" id="update_parentMenuId" name="parentId" class="form-control" value="" onclick="" placeholder="父级菜单">
						<div id="update_parentMenuIdtreeview" style="z-index: 9999" class=""></div>
					</div>
				</div>
			</div>

			<div class="form-group">
			<label class="col-md-3 control-label">图标样式：</label>
			<div class="col-md-6">
				<input type="text" id="update_menu_icon" name="menuIcon" class="form-control form-control-static" placeholder="请输入图标样式" >
			</div>
			</div>

			<div class="form-group">
			<label class="col-md-3 control-label">状态：</label>
				<div class="col-md-6" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="open"  type="radio" name="menuStatus" value="1">启用&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="close" type="radio" name="menuStatus" value="2">禁用
				</div>
			</div>

			<div class="form-group">
			<label class="col-md-3 control-label">排序：</label>
				<div class="col-md-6" class="form-control form-control-static">
						<input type="text" id="update_menu_sort"  name="menuSort" class="form-control form-control-static" >
				</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-3 control-label">菜单描述：</label>
				<div class="col-md-6">
					<textarea rows="2" id="menu_update_remark" name="remark" cols="20" class="form-control form-control-static" placeholder="请输入角色 描述"></textarea>
				</div>
			</div>
            </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="Menu.closeDlg()">关闭</button>
               <button type="button" onclick="Menu.updateMenu()" class="btn btn-primary">保存</button>
            </div>
    </div><!-- /.modal -->
</div>
</body>
</html>