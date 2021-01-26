<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>新增销售人员	</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/insurance/salerQuit.js"></script>
</head>
<body>
<div class="container">
<form class="form-horizontal" id="salerQuit_addForm"  method="post">
	<div class="form-group">
	<input type="hidden" id="salerId" name="salerId" value="${insuranceSalesInfo.insuranceSalesId}" />
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">员工工号：</label>
		<div class="col-md-3">
			<input type="text" readonly="readonly" id="salerNo"  name="salerNo" value="${insuranceSalesInfo.insuranceSalerNo}" class="form-control form-control-static" placeholder="必填">
		</div>
		<label class="col-md-2 control-label">员工姓名：</label>
		<div class="col-md-3">
			<input type="text" readonly="readonly" id="salerName"  name="salerName" value="${insuranceSalesInfo.insuranceSaler}"  class="form-control form-control-static" placeholder="必填">
		</div>		
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">员工职级序列：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" disabled="disabled" id="rankSequenceId" name="rankSequenceId" myvalue="${insuranceSalesInfo.rankSequenceId}">
             <option value="">请选择职级序列</option>
        </select>				
        </div>
		
		<label class="col-md-2 control-label">员工职级：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" disabled="disabled" id="salesGradeId" name="salesGradeId" myvalue="${insuranceSalesInfo.salesGradeId}">
             <option value="">请选择职级</option>
        </select>				
        </div>	            	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">组织机构：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" disabled="disabled" id="salesOrgId" name="salesOrgId" myvalue="${insuranceSalesInfo.salesOrgId}">
              <option value="">请选择机构</option>
        </select>				
        </div>	

		<label class="col-md-2 control-label">销售团队：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" disabled="disabled" id="salesTeamId" name="salesTeamId" myvalue="${insuranceSalesInfo.salesTeamId}">
             <option value="">请选择销售团队</option>
        </select>				
        </div>	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">离职备注：</label>
		<div class="col-md-3 ">
			<input type="text" style="width:655px;" id="quitMark"  name="quitMark" value=""  class="form-control form-control-static" placeholder="请输入离职备注">						
        </div>	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">离职去向：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="quitTo" name="quitTo">
             <option value="">请选择</option>
             <option value="01">同行业</option>
             <option value="02">其他行业</option>
        </select>				
        </div>
        
		<label class="col-md-2 control-label">离职日期：</label>
		<div class="col-md-3 ">
             <input class="form-control form-control-static" id="quitDate" name="quitDate" value="" type="text" placeholder="请选择离职时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
        </div>        	
	</div>

	<div class="form-group">
	</div>	
	<div class="form-group">
		<label class="col-md-2 control-label">保单交接人：</label>
		<div class="col-md-3 ">
		<select class="form-control form-control-static" id="jobHandoverId" name="jobHandoverId">
             <option value="">请选择交接人</option>
        </select>				
        </div>
        
		<label class="col-md-2 control-label">交接人工号：</label>
		<div class="col-md-3 ">
             <input class="form-control form-control-static" id="jobHandoverNo" name="jobHandoverNo" value="" type="text" placeholder="交接人工号" />
        </div>        	
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">交接人组织机构：</label>
		<div class="col-md-3 ">
		<!-- <select class="form-control form-control-static" id="toSalesOrgId" name="toSalesOrgId" myvalue="">
              <option value="">请选择机构</option>
        </select> -->
        	   <input class="form-control form-control-static" readonly="readonly" id="jobHandoverOrg" name="jobHandoverOrg" value="" type="text" placeholder="" />			
        </div>	

		<label class="col-md-2 control-label">交接人销售团队：</label>
		<div class="col-md-3 ">
		<!-- <select class="form-control form-control-static" id="toSalesTeamId" name="toSalesTeamId" myvalue="">
             <option value="">请选择销售团队</option>
        </select> -->
        	   <input class="form-control form-control-static" readonly="readonly" id="jobHandoverTeam" name="jobHandoverTeam" value="" type="text" placeholder="" />			        						
        </div>	
	</div>

	<div class="form-group">
	</div>
<%--	<div class="form-group">--%>
<%--		<label class="col-md-2 control-label">关系网转移人：</label>--%>
<%--		<div class="col-md-3">--%>
<%--		<select class="form-control form-control-static" id="salerTreeHandoverId" name="salerTreeHandoverId">--%>
<%--             <option value="">请选择交接人</option>--%>
<%--        </select>	--%>
<%--		</div>--%>
<%--		<label class="col-md-2 control-label">关系网转移人工号：</label>--%>
<%--		<div class="col-md-3">--%>
<%--			<input type="text" id="salerTreeHandoverNo"  name="salerTreeHandoverNo" value=""  class="form-control form-control-static" placeholder="关系转移人工号">--%>
<%--		</div>		--%>
<%--	</div>--%>
<%--	--%>
<%--	<div class="form-group">--%>
<%--		<label class="col-md-2 control-label">关系网转移人职级序列：</label>--%>
<%--		<div class="col-md-3 ">--%>
<%--		<select class="form-control form-control-static" id="tree_to_rankSequenceId" name="tree_to_rankSequenceId">--%>
<%--             <option value="">请选择职级序列</option>--%>
<%--        </select>				--%>
<%--        </div>--%>
<%--		--%>
<%--		<label class="col-md-2 control-label">关系网转移人职级：</label>--%>
<%--		<div class="col-md-3 ">--%>
<%--		<select class="form-control form-control-static" id="tree_to_salesGradeId" name="tree_to_salesGradeId">--%>
<%--             <option value="">请选择职级</option>--%>
<%--        </select>				--%>
<%--        </div>	            	--%>
<%--	</div>--%>
<%--	--%>
<%--	<div class="form-group">--%>
<%--		<label class="col-md-2 control-label">关系网转移人组织机构：</label>--%>
<%--		<div class="col-md-3 ">--%>
<%--		<!-- <select class="form-control form-control-static" id="tree_to_salesOrgId" name="tree_to_salesOrgId">--%>
<%--              <option value="">请选择机构</option>--%>
<%--        </select> -->--%>
<%--			<input type="text" id="salerTreeHandoverOrg"  name="salerTreeHandoverOrg" readonly="readonly" value=""  class="form-control form-control-static" placeholder="必填">        				--%>
<%--        </div>	--%>

<%--		<label class="col-md-2 control-label">关系网转移人销售团队：</label>--%>
<%--		<div class="col-md-3 ">--%>
<%--		<!-- <select class="form-control form-control-static" id="tree_to_salesTeamId" name="tree_to_salesTeamId">--%>
<%--             <option value="">请选择销售团队</option>--%>
<%--        </select> -->--%>
<%--			<input type="text" id="salerTreeHandoverTeam"  name="salerTreeHandoverTeam" readonly="readonly" value=""  class="form-control form-control-static" placeholder="必填">        				--%>
<%--        </div>	--%>
<%--	</div>																																							--%>
																																									
    <div class="modal-footer col-md-6">
	   <input type="reset" name="reset" style="display: none;" />
	   <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
	   <button id="saveButton" type="button" onclick="SalerQuit.add()" class="btn btn-primary">保存</button>
	</div>
</form>
</div>            
</body>
</html>