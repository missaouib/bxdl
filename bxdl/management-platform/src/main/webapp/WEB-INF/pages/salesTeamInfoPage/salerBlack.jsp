<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>员工加入黑名单</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/insurance/salerBlack.js"></script>
</head>
<body>
<div class="container">
<form class="form-horizontal" id="salerMove_addForm"  method="post">
	<div class="form-group">
	<input type="hidden" id="salerId" name="salerId" value="${insuranceSalesInfo.insuranceSalesId}" />
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">员工工号：</label>
		<div class="col-md-3">
			<input type="text" id="salerNo"  name="salerNo" readonly="readonly" value="${insuranceSalesInfo.insuranceSalerNo}" class="form-control form-control-static" placeholder="必填">
		</div>
		<label class="col-md-2 control-label">员工姓名：</label>
		<div class="col-md-3">
			<input type="text" id="salerName"  name="salerName" readonly="readonly" value="${insuranceSalesInfo.insuranceSaler}"  class="form-control form-control-static" placeholder="必填">
		</div>		
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">员工职级序列：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="preRankSeqId" name="preRankSeqId"  disabled="disabled" myvalue="${insuranceSalesInfo.rankSequenceId}">
             <option value="">请选择职级序列</option>
        </select>				
        </div>
		
		<label class="col-md-2 control-label">员工职级：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="preSalesGradeId" name="preSalesGradeId"  disabled="disabled" myvalue="${insuranceSalesInfo.salesGradeId}">
             <option value="">请选择职级</option>
        </select>				
        </div>	            	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">组织机构：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="preOrgId" name="preOrgId"  disabled="disabled" myvalue="${insuranceSalesInfo.salesOrgId}">
              <option value="">请选择机构</option>
        </select>				
        </div>	

		<label class="col-md-2 control-label">销售团队：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="preTeamId" name="preTeamId"  disabled="disabled" myvalue="${insuranceSalesInfo.salesTeamId}">
             <option value="">请选择销售团队</option>
        </select>				
        </div>	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">加黑原因：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="blackReason" name="blackReason">
             <option value="">请选择加黑原因</option>
        </select>
        </div>	
	
		<label class="col-md-2 control-label">加黑日期：</label>
		<div class="col-md-3 ">
			<input type="text" id="blackDate"  name="blackDate" value="2019-01-01"  class="form-control form-control-static" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">						
        </div>	
	</div>	
		
	<div class="form-group">
		<label class="col-md-2 control-label">加黑备注：</label>
		<div class="col-md-3 ">
			<input type="text" style="width:655px;" id="blackMark"  name="blackMark" value=""  class="form-control form-control-static" placeholder="请输入离职备注">						
        </div>	
	</div>																																							
																																									
    <div class="modal-footer col-md-6">
	   <input type="reset" name="reset" style="display: none;" />
	   <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
	   <button id="saveButton" type="button" onclick="SalerBlack.add()" class="btn btn-primary">保存</button>
	</div>
</form>
</div>            
</body>
</html>