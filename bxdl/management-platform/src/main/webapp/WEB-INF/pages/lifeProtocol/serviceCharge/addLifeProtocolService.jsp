<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>设置协议手续费</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/protocol/serviceCharge/addLifeProtocolService.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body>
<div class="container">

	<form class="form-horizontal" id="addProtocolBasicInfoForm"  method="post" enctype="application/x-www-form-urlencoded">
		<div class="form-group"  style="margin-top: 10px;">
			<label class="col-md-1 control-label" style="width: 130px;">费率基于*：</label>
			<div class="col-md-3">
				<input   type="radio"   name="rateType" value="0"/>规保
				<input  type="radio"   name="rateType" value="1" style="margin-left: 10px;"/>标保
			</div>
		</div>	
		<div class="form-group">
			<label class="col-md-1 control-label" style="width: 130px;">结算周期*：</label>
			<div class="col-md-8">
					<input type="radio"   name="settlementInterval" value="0" />月
					<input type="radio"   name="settlementInterval" value="1" style="margin-left: 10px;"/>季
					<input type="radio"   name="settlementInterval" value="2" style="margin-left: 10px;"/>半年
					<input type="radio"  name="settlementInterval" value="3" style="margin-left: 10px;"/>年
				</div>
		</div>	
	</form>	
	 
	<div  class="table-responsive" style="width:1100px;" > 
			<table  id="service-protocol-table" class="table text-nowrap"></table>
  	</div> 
	<input onclick="addProtocolService()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
	<button onclick="cancelProtocolService()"  type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" >返回</button>
	
	<!-- 协议id 加载列表使用 -->
	<input type="hidden" id="service-protocol-id"  value="${protocolId}">
	<!-- 查看 标识 -->
	<input id="isLook" type="hidden" value="${isLook}">
	<!-- 修改 查看 协议id -->
	<input type="hidden" id="service_look_protocolId" value="${service_look_protocolId}">
</body>
</html>