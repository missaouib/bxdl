<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>设置续年度服务津贴</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/protocol/subsidy/addLifeProtocolSubsidy.js" charset="UTF-8" type="text/javascript"></script>
</head>

<body>
<div class="container">

	<form class="form-horizontal" id="addProtocolBasicInfoForm"  method="post" enctype="application/x-www-form-urlencoded">
		<div class="form-group"  style="margin-top: 10px;">
			<label class="col-md-1 control-label" style="width: 130px;">费率基于*：</label>
			<div class="col-md-3">
				<input type="radio"   name="rateType" value="0" <c:if test="${rateType == '0'}">checked="checked"</c:if>/>规保
				
				<input type="radio"   name="rateType" value="1" <c:if test="${rateType == '1'}">checked="checked"</c:if> style="margin-left: 10px;"/>标保
			</div>
		</div>	
		<div class="form-group">
			<label class="col-md-1 control-label" style="width: 130px;">结算周期*：</label>
			<div class="col-md-8">
					<input type="radio"  name="settlementInterval" value="0" <c:if test="${setInterval == '0'}">checked="checked"</c:if>/>月
					<input type="radio" style="margin-left: 10px;"  name="settlementInterval" value="1" <c:if test="${setInterval == '1'}">checked="checked"</c:if>/>季
					<input type="radio"  style="margin-left: 10px;" name="settlementInterval" value="2" <c:if test="${setInterval == '2'}">checked="checked"</c:if>/>半年
					<input type="radio" style="margin-left: 10px;" name="settlementInterval" value="3" <c:if test="${setInterval == '3'}">checked="checked"</c:if>/>年
				</div>
		</div>	
	</form>	
	<div  class="table-responsive" style="width:1100px;">
		<table  id="protocol-subsidy-table" class="table text-nowrap" >
			<thead>
				<tr>
					<th>
						<span style="margin-left: 45px;margin-bottom: 3px;">保单年度</span>
						<span style="display: block;"  margin-top: 4px;">缴费期别</span>
					</th>
					<!-- 创建表头 -->
					<c:forEach var="i" begin="2" end="29" step="1" varStatus="status">
									<th>${status.index}</th>
					</c:forEach>
					<th>30期及以上</th>
				</tr>
			</thead>
			<tbody>
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
	</div>
	
	<!-- 添加产品管理 -->
	<div class="btn-toolbar" style="background: #C0C0C0;width: 2000px;">
			<span style="line-height:35px; background: #C0C0C0; display:block;font-size: 15px;font-weight: bold;">
				添加例外产品
				<input onclick="AddLifeProtocolSubsidy.addProtocolSubsidyProduct()" style="background:#1ab394;  height: 28px;margin-left: 30px; margin-top:5px;  width: 80px;color: white; line-height:30px"  vertical-align:middle;" type="button"  value="添加" />
			</span>
	</div>
	
	<table  id="add-subsidy-exception-table" class="table table-hover table-striped table-condensed table-bordered"></table>
	
	<!-- 选择例外产品 -->
	<div id="checkSubsidyEpDlg"  class="modal fade" aria-hidden="true" style="height: 370px;background: #fff; ">
		    <div>
			    <table id="check-subsidy-ep-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
			</div>
			<div id="checkSubsidyEpButton" class="btn-group">
                <button type="button"  class="btn btn-primary" onclick="AddLifeProtocolSubsidy.updateSubsidyStatus()">确定</button>
                <button type="button" onclick="AddLifeProtocolSubsidy.cancelCheckSubsidyEp()" class="btn btn-default" style="margin-left: 20px;">取消</button>
     		</div> 
	</div>
	
	<input onclick="addProtocolSubsidy()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
	<button onclick="cancelProtocolSubsidy()"  type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" >返回</button>

	<input type="hidden" id="add-subsidy-protocol-id"  value="${protocolId}">	<!-- 查看 标识-->
	<input id="isLook" type="hidden" value="${isLook}">
	<!-- 修改 查看 协议id-->
	<input type="hidden" id="subsidy_look_protocolId" value="${subsidy_look_protocolId}">
</body>
</html>