<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>员工部门职级调整	</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/insurance/salerMove.js"></script>
</head>
<body>
<div class="container">
<form class="form-horizontal" id="salerMove_addForm"  method="post">
	<div class="form-group">
	<input type="hidden" id="salerId" name="salerId" value="${insuranceSalesInfo.INSURANCE_SALES_ID}" />
	<input type="hidden" id="checkStatus" name="checkStatus" value="1" />
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">员工工号：</label>
		<div class="col-md-3">
			<input type="text" readonly="readonly" id="salerNo"  name="salerNo" value="${insuranceSalesInfo.INSURANCE_SALER_NO}" class="form-control form-control-static" placeholder="必填">
		</div>
		<label class="col-md-2 control-label">员工姓名：</label>
		<div class="col-md-3">
			<input type="text" readonly="readonly" id="salerName"  name="salerName" value="${insuranceSalesInfo.INSURANCE_SALER}"  class="form-control form-control-static" placeholder="必填">
		</div>		
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">员工职级序列：</label>
		<div class="col-md-3 ">
		<input type="hidden" name="preRankSeqId" value="${insuranceSalesInfo.RANK_SEQUENCE_ID}" />
		<input type="text"  readonly="readonly" class="form-control form-control-static" placeholder="必填" name = "preRankSeqName" value="销售序列">
			<%--<select class="form-control form-control-static" disabled="disabled" id="preRankSeqId" name="preRankSeqId_show" myvalue="${insuranceSalesInfo.RANK_SEQUENCE_ID}">
             <option value="">请选择职级序列</option>
        </select>	--%>
        </div>
		
		<label class="col-md-2 control-label">员工职级：</label>
		<div class="col-md-3 ">
		<input type="hidden" name="preSalesGradeId" value="${insuranceSalesInfo.SALES_GRADE_ID}" />
		<input type="text"  readonly="readonly" class="form-control form-control-static" placeholder="必填" name = "preSalesGradeName" value="${insuranceSalesInfo.SALES_GRADE_NAME}">
			<%--<select class="form-control form-control-static" disabled="disabled" id="preSalesGradeId" name="preSalesGradeId_show" myvalue="${insuranceSalesInfo.SALES_GRADE_ID}">
             <option value="">请选择职级</option>
        </select>--%>
        </div>	            	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">组织机构：</label>
		<div class="col-md-3 ">
		<input type="hidden" name="preBasicOrgId"  id="preBasicOrgId" value="${insuranceSalesInfo.basic_org}" />
		<input type="hidden" name="preOrgId" value="${insuranceSalesInfo.SALES_ORG_ID}" />
		<%--<select class="form-control form-control-static" disabled="disabled" id="preOrgId" name="preOrgId_show" myvalue="${insuranceSalesInfo.SALES_ORG_ID}">
              <option value="">请选择机构</option>
        </select>--%>
		<input type="text"  readonly="readonly" class="form-control form-control-static" placeholder="必填" name = "preOrgName" value="${insuranceSalesInfo.SALES_ORG_NAME}">
        </div>	

		<label class="col-md-2 control-label">销售团队：</label>
		<div class="col-md-3 ">
		<input type="hidden" name="preTeamId" value="${insuranceSalesInfo.SALES_TEAM_ID}" />
		<input type="text" readonly="readonly" class="form-control form-control-static"  name = "preOrgName" value="${insuranceSalesInfo.SALES_TEAM_NAME}">
		<%--<select class="form-control form-control-static" disabled="disabled" id="preTeamId" name="preTeamId_show" myvalue="${insuranceSalesInfo.SALES_TEAM_ID}">
             <option value="">请选择销售团队</option>
        </select>	--%>
        </div>	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">异动备注：</label>
		<div class="col-md-3 ">
			<input type="text" style="width:655px;" id="moveMark"  name="moveMark" value=""  class="form-control form-control-static" placeholder="请输入备注">						
        </div>	
	</div>	
	
	<div class="form-group">
		<label class="col-md-2 control-label">目标组织机构：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static"  id="nextOrgId" name="nextOrgId">
              <option value="">请选择机构</option>
        </select>				
        </div>	

		<label class="col-md-2 control-label">目标销售团队：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="nextTeamId" name="nextTeamId">
             <option value="">请选择销售团队</option>
        </select>				
        </div>	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">目标职级序列：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="nextRankSeqId" name="nextRankSeqId">
             <option value="">请选择职级序列</option>
        </select>				
        </div>
		
		<label class="col-md-2 control-label">目标职级：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="nextSalesGradeId" name="nextSalesGradeId">
             <option value="">请选择职级</option>
        </select>				
        </div>
		<input type="hidden" id="moveEffect" name="moveEffect" value="" />
	</div>
	
	<!-- <div class="form-group">
		<label class="col-md-2 control-label">调动日期：</label>
		<div class="col-md-3 ">
			<input type="text" id="moveDate"  name="moveDate" value="2019-01-01"  class="form-control form-control-static" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">						
        </div>	
	</div> -->																																							
																																									
    <div class="modal-footer col-md-6">
	   <input type="reset" name="reset" style="display: none;" />
	   <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
	   <button id="saveButton" type="button" onclick="SalerMove.add(0)" class="btn btn-primary">次月生效</button>
		<button id="saveButtonNow" type="button" onclick="SalerMove.add(1)" class="btn btn-primary">立即生效</button>
	</div>
</form>
</div>            
</body>
</html>