<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>代理协议列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<script src="${path}/js/protocol/protocolBasic/lifeProtocolList.js" charset="UTF-8" type="text/javascript"></script>
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<body>
<div class="panel panel-default">
	<div class="panel-body">
		<form id="search_protocol_form" class="form-inline hz-form-inline">
			<div class="form-group">
				<label for="INSURANCE_COMPANY_NAME"  class="control-label">签约保险公司</label>
		   		 <input type="text" class="form-control"  id="INSURANCE_COMPANY_NAME" name="INSURANCE_COMPANY_NAME">
		  	</div>

		   	<div class="form-group">
			   		<label for="INSURANCE_ORG_NAME"  class="control-label">签约机构</label>
			   		<input type="text" class="form-control"  id="INSURANCE_ORG_NAME" name="INSURANCE_ORG_NAME">
			  </div>

			   <div class="form-group">
			   		<label for="PROTOCOL_NAME"  class="control-label">协议名称</label>
			   		 <input type="text" class="form-control"  id="PROTOCOL_NAME"  name="PROTOCOL_NAME">
			  </div>

			   <div class="form-group" >
			   		<label for="PROTOCOL_CODE"  class="control-label">协议编号</label>
			   		 <input type="text" class="form-control"   id="PROTOCOL_CODE"  name="PROTOCOL_CODE">
			  </div>
			   <div class="form-group">
					<label for="EFFECTIVE_DATE"  class="control-label">生效日</label>
	                <input class="form-control input-sm Wdate" id="EFFECTIVE_DATE"   name="EFFECTIVE_DATE" type="text" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	           </div>

	           <div class="form-group">
	               <label for="TERMINATION_DATE" class="control-label">终止日</label>
	                <input class="form-control input-sm Wdate" type="text" id="TERMINATION_DATE" id="TERMINATION_DATE"  name="TERMINATION_DATE"  onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            </div>

			  <div class="form-group">
			   		<label for="PROTOCOL_STATUS"   class="control-label">状态</label>
			    	<select  id="PROTOCOL_STATUS" name="PROTOCOL_STATUS" class="form-control">
	                        <option value="">请选择</option>
	                        <option value=0>录入中</option>
	                        <option value=1>生效</option>
	                        <option value=2>失效</option>
	                        <option value=3>其他</option>
	                </select>
			  </div>
 			<div class="form-group">
				 <button type="button" onclick="Protocol.searchProtocol()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="Protocol.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
		   	</div>
		</form>
	</div>
</div>
<!--列表 -->
<button class="btn btn-info" type="button" onclick="addProtocolServiceBasic()">
	<span class="glyphicon glyphicon-plus" >新增寿险代理协议</span>
</button>
<div  class="table-responsive">
	<table id="protocol-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
</body>
</html>