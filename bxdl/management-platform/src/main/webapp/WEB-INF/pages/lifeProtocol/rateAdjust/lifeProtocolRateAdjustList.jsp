<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>费率调节列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<script src="${path}/js/protocol/rateAdjust/lifeProtocolRateAdjustList.js" charset="UTF-8" type="text/javascript"></script>
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<style>
 
</style>
<body>
	<input onclick="addProtocolAdjust()" style="background:#23c6c8;margin-top: 10px;  height: 30px;width: 120px;color: white;" type="button"  value="新增费率调节" />
<!--列表 -->
<table id="protocol-adjust-table" class="table table-hover table-striped table-condensed table-bordered"></table>
<button onclick="returnProtocol()"  type="button" style="background:#1ab394; margin-left:400px; height: 30px;width: 80px;color: white; line-height:20px" >返回</button>
<input type="hidden" id="adjust-protocol-id" value="${protocolId}">
<!-- 修改 查看 协议id-->
<input type="hidden" id="adjust_look_protocolId" value="${adjust_look_protocolId}">
<input type="hidden" id="isLook" value="${isLook}">
</body>
</html>