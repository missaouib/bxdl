<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>续年度服务津贴-例外产品设置</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/protocol/subsidy/lifeProtocolSubsidyConfig.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body>
<div class="container">
	<div  class="table-responsive" style="width:1100px;" >
		<table  id="subsidy-config-table" class="table text-nowrap" >
			<thead>
				<tr>
					<th>
						<span style="margin-left: 45px;margin-bottom: 3px;">保单年度</span>
						<span style="display: block;"  margin-top: 4px;">缴费期别</span>
						<c:forEach var="i" begin="2" end="29" step="1" varStatus="status">
								<th>${status.index}</th>
						</c:forEach>
						<th>30及以上</th>
					</th>
					<c:choose>
						<c:when test="${flag == 1 }">
								<c:forEach items="${array}" var="a" varStatus="status">
								<tr>
									<c:choose>
										<c:when test="${a == 30}">
											<td><input  type="text" style="width:70px;" readonly="readonly" value="${a}期及以上"></td>
										</c:when>
										<c:when test="${a != 30}">
											<td><input  type="text" style="width:70px;" readonly="readonly" value="${a}期"></td>
										</c:when>
									</c:choose>
									<c:forEach var="i" begin="1" end="29" step="1">
											<td><input  style="width:60px;"  type="number" step="0.01" min="0" value="0"></td>
									</c:forEach>
								<tr>
							</c:forEach> 
						</c:when>
						<c:when test="${flag == 0 }">
							<c:forEach items="${list}" var="map" >
								 <tr>
								    <c:forEach items="${map}" var="entry" varStatus="status">
									    <c:choose>
									    	<c:when test="${status.index ==0 }">
									    		<td><input  style="width:60px;" value="${entry.value}"  type="text" readonly="readonly"></td>
									    	</c:when>
									  	  	<c:when test="${status.index !=0 }">
									    		<td><input  style="width:60px;" value="${entry.value}"  type="number" step="0.01" min="0" ></td>
									    	</c:when>
									    </c:choose>
							   		 </c:forEach>
								</tr>
						</c:forEach>
					</c:when>
				</c:choose>		
			</tbody>
		</table>
		<div>
				<input onclick="addSubsidyConfig()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
				<button type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" onclick="cancelSubsidyConfig()">返回</button>
		</div>
	</div>
	
	<input id="subsidy-config-id" type="hidden" value="${subsidyId}">
	<!-- 查看 标识-->
	<input id="isLook" type="hidden" value="${isLook}">
	<!--  修改 查看 协议id-->
	<input type="text" id="ep_subsidy_look_protocolId" value="${ep_subsidy_look_protocolId}">
</body>
</html>