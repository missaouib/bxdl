<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>销售级别</title>
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/insurance/salesGrade.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<body>
<!--toolbar  -->
<div id="salesGradeToolbar" class="btn-toolbar">
	<%-- <shiro:hasPermission name="salesGrade:add">
    	<button class="btn btn-info" type="button" onclick="SalesGrade.openAddModal()">
    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesGrade:update">
    	<button type="button" class=" btn btn-info" onclick="SalesGrade.openUpdateModal()">
    		<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesGrade:delete">
    	<button class=" btn btn-danger" type="button" onclick="">
    		<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission> --%>
</div> 
<table id="salesGrade-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="salesGradeAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加职级序列</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="addForm"  method="post">
						
			<div class="form-group">
			<label class="col-md-2 control-label">职级序列：</label>
			<div class="col-md-3">
			<input type=hidden id="rankSequenceName" name="rankSequenceName" value="">
			<select class="form-control form-control-static" id="rankSequenceId" name="rankSequenceId">
              	<option value="">请选择职级序列</option>
           	</select>			
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职级编码：</label>
			<div class="col-md-3 ">
			<input type="text" id="salesGradeCode"  name="salesGradeCode" class="form-control form-control-static" placeholder="请输入职级编码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职级名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="salesGradeName"  name="salesGradeName" class="form-control form-control-static" placeholder="请输入职级名称">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="SalesGrade.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="SalesGrade.addSalesGrade()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="salesGradeMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改职级序列</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="updateForm"  method="post">
			<div class="form-group">
				<div class="col-md-3 ">
					<input type="hidden" id="update_salesGradeId" name="salesGradeId"  class="form-control form-control-static" readonly="readonly" placeholder="必填">
				</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职级序列：</label>
				<div class="col-md-3">
					<input type=hidden id="update_rankSequenceName" name="rankSequenceName" value="">
					<select class="form-control form-control-static" id="update_rankSequenceId" name="rankSequenceId">
                        <option value="">请选择职级序列</option>
             		</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">职级编码：</label>
				<div class="col-md-3">
					<input type="text" id="update_salesGradeCode"  name="salesGradeCode" class="form-control form-control-static" placeholder="必填">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">职级名称：</label>
				<div class="col-md-3 ">
					<input type="text" id="update_salesGradeName"  name="salesGradeName" class="form-control form-control-static" placeholder="必填">
				</div>
			</div>

            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="SalesGrade.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="SalesGrade.updateSalesGrade()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>