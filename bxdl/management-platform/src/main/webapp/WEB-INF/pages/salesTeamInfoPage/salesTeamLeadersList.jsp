<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>销售级别</title>
    <link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/js/insurance/salesTeamLeaders.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<body>
<!--toolbar  -->
<div id="salesTeamLeadersToolbar" class="btn-toolbar">
  	<button type="button" class="btn btn-info" onclick="">
  		<span class="glyphicon glyphicon-pencil" >当前团队：${salesTeamInfo.salesTeamName}</span>
  	</button>
	<shiro:hasPermission name="salesTeamLeaders:add">
    	<button class="btn btn-info" type="button" onclick="SalesTeamLeaders.openAddModal()">
    		<span class="glyphicon glyphicon-plus" >任命主管</span>
    	</button>
    </shiro:hasPermission>
	<shiro:hasPermission name="salesTeamLeaders:disable">
    	<button class="btn btn-info" type="button" onclick="disableLeaders()">
    		<span class="glyphicon glyphicon-plus" >终止任命</span>
    	</button>
    </shiro:hasPermission>    
    <shiro:hasPermission name="salesTeamLeaders:update">
    	<button type="button" class=" btn btn-info" onclick="SalesTeamLeaders.openUpdateModal()">
    		<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesTeamLeaders:delete">
    	<button class=" btn btn-danger" type="button" onclick="">
    		<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>    
</div> 
<table id="salesTeamLeaders-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="salesTeamLeadersAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">主管任命</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="salesTeamLeaders_addForm"  method="post">
			<input type="hidden" id="salesTeamId" name="salesTeamId" value="${salesTeamInfo.salesTeamId}">
			<div class="form-group">
			<label class="col-md-2 control-label">员工工号：</label>
			<div class="col-md-3 ">
			<input type="text" id="salerNo"  name="salerNo" class="form-control form-control-static" placeholder="请输入员工工号">
			</div>
			</div>
						
			<div class="form-group">
			<label class="col-md-2 control-label">员工：</label>
			<div class="col-md-3">
			<select class="form-control form-control-static" id="salerName" name="salerName">
              	<option value="">请选择员工</option>
           	</select>			
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">领导类型：</label>
			<div class="col-md-3">
			<select class="form-control form-control-static" id="leaderType" name="leaderType">
              	<option value="">请选择领导类型</option>
              	<option value="1">行政主管</option>
              	<option value="2">销售主管</option>
           	</select>			
			</div>
			</div>			
			
			<div class="form-group">
			<label class="col-md-2 control-label">证件类型：</label>
			<div class="col-md-3 ">
			<input type="hidden" value="" id="cardType"  name="cardType" />
			<select class="form-control form-control-static" disabled="disabled" id="cardType_dis" name="cardType_dis">
           	</select>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">证件号码：</label>
			<div class="col-md-3 ">
			<input type="text" readonly="readonly" id="cardNo"  name="cardNo" class="form-control form-control-static" placeholder="请输入证件号码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">生效日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="effectDate"  name="effectDate" value="2019-01-01" class="form-control form-control-static"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">失效日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="stopDate"  name="stopDate" value="2019-01-01" class="form-control form-control-static"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
			</div>
			</div>
			
			
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
            <button type="button" class="btn btn-default" onclick="SalesTeamLeaders.closeDlg()">关闭</button>
            <button id="saveButton" type="button" onclick="SalesTeamLeaders.addSalesTeamLeaders()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>