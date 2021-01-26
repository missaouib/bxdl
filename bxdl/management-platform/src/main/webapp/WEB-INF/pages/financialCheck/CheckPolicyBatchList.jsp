<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>手续费对账</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${path}/css/system/role/role.css">
	<script src="${path}/static/js/jquery-form.js"></script>
	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/tableExport.js"></script>
	<script src="${path}/js/jquery-form.js"></script>
	<script src="${path}/js/bootstrap3-typeahead.js" type="text/javascript"></script>
	<script src="${path}/js/financialCheck/checkPolicyBatch.js"></script>
</head>
<body>
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline" id="searchForm">
			<input type="hidden" value="1" id="type" name="type">
			<div class="form-group has-feedback">
				<label class="control-label" for="company_org_name">保险公司名称</label>
				<input type="text" class="form-control" id="company_org_name" name="company_org_name" placeholder="保险公司名称" data-provide="typeahead" autocomplete="off" onmouseover="this.title=this.value"></input>
				<span class="glyphicon glyphicon-search form-control-feedback"></span>
			    <input type="hidden" class="form-control" id="company_org_id" name="company_org_id" ></input>
			</div>


			<div class="form-group has-feedback">
				<label class="control-label" for="sales_org_name">组织机构名称</label>
				<input type="text" class="form-control" id="sales_org_name" name="sales_org_name" placeholder="组织机构名称" data-provide="typeahead" autocomplete="off" onmouseover="this.title=this.value"></input>
				<span class="glyphicon glyphicon-search form-control-feedback"></span>
			    <input type="hidden" class="form-control" id="sales_org_id" name="sales_org_id"></input>
			</div>
				<div class="form-group">
				<label class="control-label" for="batch_num">批次号</label>
				<input type="text" class="form-control" id="batch_num" name="batch_num" placeholder="批次号" ></input>
			</div>
				<div class="form-group">
				<label class="control-label" for="batch_name">批次名称</label>
				<input type="text" class="form-control" id="batch_name" name="batch_name" placeholder="批次名称" ></input>
			</div>
	<div class="form-group">
				<label class="control-label">对账类型</label>
				<select class="form-control" id="batch_type" name="batch_type">
					<option value="">对账类型</option>
					<option value="0">手续费对账</option>
					<option value="1">技术服务费对账</option>
					<option value="2">广告服务费对账</option>
				</select>
			</div>
			<%--<div class="form-group">
				<label class="control-label">状态</label>
				<select class="form-control" id="status" name="status">
					<option value="">状态</option>
					<option value="0">新建</option>
					<option value="1">进行中</option>
					<option value="2">处理完成</option>
				</select>
			</div>--%>
<div class="form-group">
				<label class="col-md-2 control-label">对账月份</label>
					<input name="check_month" id="check_month" type='text' class="form-control input-sm Wdate"
						   onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})"/>

			</div>

			<div class="form-group">
				<label class="control-label">创建日期</label>
				<input class="form-control input-sm Wdate" id="minCreateTime" name="minCreateTime" type="text" placeholder="请选择创建时间" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})">
				<label class="control-label control-label-time">至</label>
				<input class="form-control input-sm Wdate" id="maxCreateTime" name="maxCreateTime" type="text" placeholder="请选择创建时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
			</div>


			<div class="form-group ">
				<button type="button" class="btn btn-info" onclick="checkPolicyBatch.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>

</div>

<div style="width: 99%;overflow: auto;">
	<table id="checkPolicyBatch-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>


<!-- 导入文件div -->
<div id="alertFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="zsadd_myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="zsadd_myModalLabel">上传文件</h4>
            </div>
            <div class="container">
                <form class="form-horizontal" id="fileForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择文件</label>
                        <div class="col-md-3 ">
                            <input type="file" name="file" id="fileName"/>
                        </div>
                    </div>
                    <div class="modal-footer col-md-6">
                        <!--用来清空表单数据-->
                        <input type="reset" name="reset" style="display: none;"/>
                        <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
                        <button id="zs_saveButton" type="button" onclick="upload()" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="loadingModal">
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
        <div class="progress progress-striped active" style="margin-bottom: 0;">
            <div class="progress-bar" style="width: 100%;"></div>
        </div>
        <h5 style="color:black"><strong>正在缓冲文件...预计1-5秒，请稍等！</strong></h5>
    </div>
</div>

<!--toolbar  -->
<div id="toolbarPolList" class="btn-toolbar" style="overflow:scroll;">
	<%--<shiro:hasPermission name="inspolicyBatch:add">--%>
		<button type="button" class=" btn btn-primary" onclick="addCheckPolicyBatch()">
			<span class="glyphicon glyphicon-plus">新建批次</span>
		</button>
	<%--</shiro:hasPermission>--%>

		<%--<span style="color: black">&nbsp;&nbsp;"总保费:</span><span id="total_premium" STYLE="color: crimson"></span><span style="color: black">万元</span>--%>

</div>
</body>
</html>