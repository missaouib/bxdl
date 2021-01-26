<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>销售级别序列</title>
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/insurance/rankSequence.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<body>
<!--toolbar  -->
<div id="rankSequenceToolbar" class="btn-toolbar">
	<%-- <shiro:hasPermission name="rankSequence:add">
    	<button class="btn btn-info" type="button" onclick="RankSequence.openAddModal()">
    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="rankSequence:update">
    	<button type="button" class=" btn btn-info" onclick="RankSequence.openUpdateModal()">
    		<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="rankSequence:delete">
    	<button class=" btn btn-danger" type="button" onclick="">
    		<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission> --%>
</div> 
<table id="rankSequence-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="rankSequenceAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加职级序列</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="addForm"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">职级序列编码：</label>
			<div class="col-md-3 ">
			<input type="text" id="sequenceCode"  name="sequenceCode" class="form-control form-control-static" placeholder="请输入职级序列编码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职级序列名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="sequenceName"  name="sequenceName" class="form-control form-control-static" placeholder="请输入职级序列名称">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职级序列描述：</label>
			<div class="col-md-3">
			<textarea rows="3" id="remark" name="remark" cols="30" class="form-control form-control-static" placeholder="请输入职级序列描述"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="RankSequence.closeDlg()">关闭</button>
               <button id="saveButton" type="button" onclick="RankSequence.addRankSequence()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="rankSequenceMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
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
					<input type="hidden" id="update_sequenceId" name="sequenceId"  class="form-control form-control-static" readonly="readonly" placeholder="必填">
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">职级序列编码：</label>
				<div class="col-md-3">
					<input type="text" id="update_sequenceCode"  name="sequenceCode" class="form-control form-control-static" placeholder="必填">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">职级序列名称：</label>
				<div class="col-md-3 ">
					<input type="text" id="update_sequenceName"  name="sequenceName" class="form-control form-control-static" placeholder="必填">
				</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">职级序列描述：</label>
				<div class="col-md-3">
					<textarea rows="3" id="update_remark" name="remark" cols="30" class="form-control form-control-static" placeholder="必填"></textarea>
				</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="RankSequence.closeDlg()">关闭</button>
               <button type="button" id="updateButton" onclick="RankSequence.updateRankSequence()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>