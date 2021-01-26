<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>设置协议折标率</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/protocol/standardRate/addLifeProtocolStandard.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body>
<div class="container">
	<div  class="table-responsive" style="width:1100px;">
			<table  id="standard-protocol-table"   style="margin-top: 30px;"  class="table text-nowrap" ></table>
	</div>
	
	<input onclick="addProtocolStandard()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
	<button onclick="cancelProtocolStandard()"  type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" >返回</button>
	
	<!-- 协议id 加载列表使用 -->
	<input type="hidden" id="standard-protocol-id"  value="${protocolId}">
	<!-- 判断是否点击查看按钮 -->
	<input id="isLook" type="hidden" value="${isLook}">
	<!--修改 查看 协议id -->
	<input type="hidden" id="standard_look_protocolId" value="${standard_look_protocolId}">
</body>
</html>