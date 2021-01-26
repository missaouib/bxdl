<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>设置协议内部折标率</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/protocol/insideStandardRate/addLifeProtocolInsideStandard.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body>
<div class="container">
	<div  class="table-responsive" style="width:1100px;">
				<table  style="margin-top: 30px;"  id="inside-standard-protocol-table" class="table text-nowrap" ></table>
	</div>
	
	<input onclick="addProtocolInsideStandard()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
 	<button onclick="cancelInProtocolInStandard()"  type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" >返回</button>
	
	<!-- 协议id 列表使用 -->
	<input type="hidden" id="inside-standard-protocol-id"  value="${protocolId}">
	<!-- 查看 标识 -->
	<input id="isLook" type="hidden" value="${isLook}">
	<!-- 修改 查看 协议id-->
	<input type="hidden" id="in_standard_look_protocolId" value="${in_standard_look_protocolId}">
</body>
</html>