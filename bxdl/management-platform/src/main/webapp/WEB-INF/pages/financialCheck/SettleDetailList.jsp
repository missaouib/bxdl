<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>结算清单确认</title>
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
	<script src="${path}/js/financialCheck/settleDetail.js"></script>
</head>
<body>
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline" id="searchForm">
			<input type="hidden" value="1" id="type" name="type">
			<div class="form-group has-feedback">
				<label class="control-label" for="company_org_name">保险公司名称</label>

				<input type="text" class="form-control" id="company_org_name" name="company_org_name" placeholder="保险公司名称" data-provide="typeahead" autocomplete="off" value="" onmouseover="this.title=this.value"></input>
			 <span class="glyphicon glyphicon-search form-control-feedback"></span>
			     <input  type="hidden" class="form-control" id="company_org_id" name="company_org_id" placeholder="保险公司"></input>
			</div>

			<div class="form-group has-feedback">
				<label class="control-label" for="sales_org_name">组织机构名称</label>
				<input type="text" class="form-control" id="sales_org_name" name="sales_org_name" placeholder="组织机构名称" data-provide="typeahead" autocomplete="off" value="" onmouseover="this.title=this.value"></input>
				<span class="glyphicon glyphicon-search form-control-feedback"></span>
			   <input type="hidden" class="form-control" id="sales_org_id" name="sales_org_id" ></input>
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">对账月份</label>
					<input name="check_month" id="check_month" type='text' class="form-control input-sm Wdate"
						   onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})"/>

			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">结算状态</label>
					<select id="settle_status" name="settle_status" class="form-control">
						<option value=""></option>
						<option value="0">未结算</option>
						<option value="1">已结算</option>
					</select>

			</div>
			<div class="form-group pull-right">
				<button type="button" class="btn btn-info" onclick="settleDetail.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
				 <button type="button" class="btn btn-info" onclick="confirm()">
				<span class="glyphicon glyphicon-ok" aria-hidden="true" >结算确认</span>
			    </button>

			</div>
		</form>
	</div>

</div>

<div style="width: 99%;overflow: auto;">
	<table id="settleDetail-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>

<!--toolbar  -->
<div id="toolbarPolList" style="overflow:scroll;">
                <span style="color: crimson">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总规模保费:</span><span id="total_premium" STYLE="color: crimson"></span><span style="color: crimson">元,</span>
				<span style="color: crimson">&nbsp;&nbsp;&nbsp;&nbsp;总手续费:</span><span id="total_process_cost" STYLE="color: crimson"></span><span style="color: crimson">元,</span>
				<span style="color: crimson">&nbsp;&nbsp;&nbsp;&nbsp;总推广费:</span><span id="total_pust_cost" STYLE="color: crimson"></span><span style="color: crimson">元,</span>
				<span style="color: crimson">&nbsp;&nbsp;&nbsp;&nbsp;保司合计支付:</span><span id="total_total_cost" STYLE="color: crimson"></span><span style="color: crimson">元</span>
</div>




</body>
</html>