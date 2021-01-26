<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>协议继续率奖金</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/insurance/persistencyBonus.js"></script>
    <style type="text/css">
		table td{
		     overflow: hidden;
		     text-overflow:ellipsis;
		     white-space: nowrap;
		     font-size:12px;
		     font-family: "Microsoft YaHei";
		     font-weight:400;
		}
		.table>thead th { font-size: 12px; background: #e2ebef!important;}
		.btn .glyphicon{ top: 0;}
		.btn .glyphicon { line-height: normal;}
		.btn .glyphicon::before {
			font-size: 12px;
			margin-right: 3px;
		}     
    </style>
</head>
<body>
<div class="container">
<!-- 
<div id="XY-PB-toolbar" style="margin:20 0 20 0">
	<button type="button" class="btn btn-info" onclick="openAddProtocolBasic()">1.协议信息</button>
	<button type="button" class="btn btn-info" onclick="openAddProtocolService()" >2.手续费及折标率</button>
	<button type="button" class="btn btn-info" onclick="openAddProtocolSubsidy()" >3.续年度服务津贴</button>
	<button type="button" class="btn btn-info" onclick="javascript:void(0)" >4.继续率奖金</button>
	<button type="button" class="btn btn-info" onclick="" >5.费率调节</button>
	<button type="button" class="btn btn-info" onclick="openProtocolPromotion()" >6.业务推动</button>
</div> -->

<form class="form-horizontal" id="persistencyBonus_updateForm"  method="post">
	<div class="form-group">
	<input type="hidden" id="protocolId" name="protocolId" value="${ippbs.protocolId}" />
	<input type="hidden" id="bonusId" name="bonusId" value="${ippbs.bonusId}" />
	<input type="hidden" id="periodRatio" name="periodRatio" value='${ippbs.periodRatio}' />
	<input type="hidden" id="monthyBasic" name="monthyBasic" value='${monthyBasic}' />
	<input type="hidden" id="thrMonthyBasic" name="thrMonthyBasic" value='${thrMonthyBasic}' />
	<input type="hidden" id="sixMonthyBasic" name="sixMonthyBasic" value='${sixMonthyBasic}' />
	<input type="hidden" id="twnMonthyBasic" name="twnMonthyBasic" value='${twnMonthyBasic}' />
	<input type="hidden" id="outProducts" name="outProducts" value='${outProducts}' />
	<input id="isLook" type="hidden" value="${isLook}">
	<input id="persistencyBounds_look_protocolId" type="hidden" value="${persistencyBoundsLookProtocolId}">
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">费率基于：</label>
		<div class="col-md-3">
			<input type="radio" name="bonusBases" value="0"  <c:if test="${ippbs.bonusBases=='0'}"> checked="checked"</c:if> />&nbsp;规保&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="bonusBases" value="1"  <c:if test="${ippbs.bonusBases=='1'}"> checked="checked"</c:if> />&nbsp;标保
		</div>
		<label class="col-md-2 control-label">结算周期：</label>
		<div class="col-md-3">
			<input type="radio" name="settlementCycle" value="1" checked="checked" />&nbsp;月
		</div>		
	</div>	
	
	<div class="form-group">
		<label class="col-md-2 control-label">通算方式：</label>
		<div class="col-md-3">
			<select class="form-control form-control-static" id="addSubsidyWay" name="addSubsidyWay">
				<option value="">请选择</option>
				<option value="2" <c:if test="${ippbs.addSubsidyWay=='2'}"> selected="selected"</c:if> >季度算</option>
				<option value="3" <c:if test="${ippbs.addSubsidyWay=='3'}"> selected="selected"</c:if> >半年算</option>
				<option value="4" <c:if test="${ippbs.addSubsidyWay=='4'}"> selected="selected"</c:if> >年通算</option>
			</select>
		</div>	
	</div>
	
	<div class="form-group">	
		<label class="col-md-2 control-label">年期换算系数：</label>
		<div class="col-md-3">
			<input type="radio" name="periodRatioShow" value="0" checked="checked" />&nbsp;配置&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="periodRatioShow" value="1" />&nbsp;隐藏
		</div>		
	</div>
	
	<div id="nq-div" class="table-responsive" style="display:block;width:93%">
	<table id="nq-table" class="table text-nowrap">
			<thead>
				<tr>
					<th>
						<span style="margin-left: 45px;margin-bottom: 3px;">缴费期别</span>
						<span style="display: block;"  margin-top: 4px;">保单年度</span>
					</th>	
					<c:forEach var="i" begin="2" end="29" step="1">
					<th>${i}期</th>
					</c:forEach>
					<th>30及以上</th>					
				</tr>
			</thead>
			<tbody>		
			<c:forEach var="ac" begin="2" end="30" step="1">
				<tr>
					<td>${ac}</td>
					<c:forEach var="ii" begin="1" end="29" step="1">
					<td><input  style="width:60px;"  type="number" step="0.01" min="0" value="0"></td>
					</c:forEach>
				</tr>
			</c:forEach> 
			</tbody>	
	</table>
	</div>
	<div class="form-group">
	</div>
	<div class="form-group" id="monthyBasic_div" style="display:block;">
		<label class="col-md-2 control-label">月结算：
		<c:choose>
		   	<c:when test="${isLook == 'yes'}">
				（<button id="zhengshu_add" type="button" disabled="disabled" onclick="persistencyBonus.add_monthyBasic()" class="btn btn-primary">新增</button>）</label>
		   	</c:when>
		  	<c:otherwise>
				（<button id="zhengshu_add" type="button" onclick="persistencyBonus.add_monthyBasic()" class="btn btn-primary">新增</button>）</label>
		  	</c:otherwise>
		</c:choose>
		<div class="col-md-3 " style="width:880px;">
			<div style="overflow:auto;width:850px;">
			<table id="monthyBasic_list" class="table table-hover table-striped table-condensed table-bordered" style="width:850px;font-size:13px;">
				<tr>
					<td style="width:120px;text-align:center;">操作</td>
					<td style="width:140px;text-align:center;">N月继续率</td>
					<td style="width:470px;text-align:center;">规则</td>
					<td style="width:120px;text-align:center;">奖励费率(%)</td>
				</tr>					
			</table>
			</div>
		</div>			
	</div>	

	<div class="form-group" id="thrMonthyBasic_div" style="display:none;">
		<label class="col-md-2 control-label">季度算：
		<c:choose>
		   	<c:when test="${isLook == 'yes'}">
				（<button id="zhengshu_add" type="button" disabled="disabled" onclick="persistencyBonus.add_thrMonthyBasic()" class="btn btn-primary">新增</button>）</label>
		   	</c:when>
		  	<c:otherwise>
				（<button id="zhengshu_add" type="button" onclick="persistencyBonus.add_thrMonthyBasic()" class="btn btn-primary">新增</button>）</label>
		  	</c:otherwise>
		</c:choose>
		
		<div class="col-md-3 " style="width:880px;">
			<div style="overflow:auto;width:850px;">
			<table id="thrMonthyBasic_list" class="table table-hover table-striped table-condensed table-bordered" style="width:850px;font-size:13px;">
				<tr>
					<td style="width:120px;text-align:center;">操作</td>
					<td style="width:140px;text-align:center;">N月继续率</td>
					<td style="width:470px;text-align:center;">规则</td>
					<td style="width:120px;text-align:center;">奖励费率(%)</td>
				</tr>					
			</table>
			</div>
		</div>			
	</div>
	
	<div class="form-group" id="sixMonthyBasic_div" style="display:none;">
		<label class="col-md-2 control-label">半年算：
		<c:choose>
		   	<c:when test="${isLook == 'yes'}">
				（<button id="zhengshu_add" type="button" disabled="disabled" onclick="persistencyBonus.add_sixMonthyBasic()" class="btn btn-primary">新增</button>）</label>
		   	</c:when>
		  	<c:otherwise>
				（<button id="zhengshu_add" type="button" onclick="persistencyBonus.add_sixMonthyBasic()" class="btn btn-primary">新增</button>）</label>
		  	</c:otherwise>
		</c:choose>
		<div class="col-md-3 " style="width:880px;">
			<div style="overflow:auto;width:850px;">
			<table id="sixMonthyBasic_list" class="table table-hover table-striped table-condensed table-bordered" style="width:850px;font-size:13px;">
				<tr>
					<td style="width:120px;text-align:center;">操作</td>
					<td style="width:140px;text-align:center;">N月继续率</td>
					<td style="width:470px;text-align:center;">规则</td>
					<td style="width:120px;text-align:center;">奖励费率(%)</td>
				</tr>					
			</table>
			</div>
		</div>			
	</div>	
	
	<div class="form-group" id="twnMonthyBasic_div" style="display:none;">
		<label class="col-md-2 control-label">年通算：
		<c:choose>
		   	<c:when test="${isLook == 'yes'}">
				（<button id="zhengshu_add" type="button" disabled="disabled" onclick="persistencyBonus.add_twnMonthyBasic()" class="btn btn-primary">新增</button>）</label>
		   	</c:when>
		  	<c:otherwise>
				（<button id="zhengshu_add" type="button" onclick="persistencyBonus.add_twnMonthyBasic()" class="btn btn-primary">新增</button>）</label>
		  	</c:otherwise>
		</c:choose>
		<div class="col-md-3 " style="width:880px;">
			<div style="overflow:auto;width:850px;">
			<table id="twnMonthyBasic_list" class="table table-hover table-striped table-condensed table-bordered" style="width:850px;font-size:13px;">
				<tr>
					<td style="width:120px;text-align:center;">操作</td>
					<td style="width:140px;text-align:center;">N月继续率</td>
					<td style="width:470px;text-align:center;">规则</td>
					<td style="width:120px;text-align:center;">奖励费率(%)</td>
				</tr>					
			</table>
			</div>
		</div>			
	</div>
	
	<div class="form-group" id="twnMonthyBasic_div">
		<label class="col-md-2 control-label">例外产品：
		<c:choose>
		   	<c:when test="${isLook == 'yes'}">
				（<button id="zhengshu_add" type="button" disabled="disabled" onclick="exitProAdd()" class="btn btn-primary">新增</button>）</label>
		   	</c:when>
		  	<c:otherwise>
				（<button id="zhengshu_add" type="button" onclick="exitProAdd()" class="btn btn-primary">新增</button>）</label>
		  	</c:otherwise>
		</c:choose>
		<div class="col-md-3 " style="width:880px;">
			<div style="overflow:auto;width:850px;">
			<table id="outPro_list" class="table table-hover table-striped table-condensed table-bordered" style="width:850px;font-size:13px;">
				<tr>
					<td style="width:120px;text-align:center;">操作</td>
					<td style="width:340px;text-align:center;">产品名称</td>
					<td style="width:190px;text-align:center;">产品代码</td>
					<td style="width:200px;text-align:center;">外部标保佣金系数(%)</td>
				</tr>					
			</table>
			</div>
		</div>			
	</div>																																										
																																									
    <div class="modal-footer col-md-6">
	   <input type="reset" name="reset" style="display: none;" />
	   <c:choose>
		   	<c:when test="${isLook == 'yes'}">
		   		<button id="saveButton" type="button" disabled="disabled" onclick="persistencyBonus.update()" class="btn btn-primary">保存</button>
		   	</c:when>
		  	<c:otherwise>
			    <button id="saveButton" type="button" onclick="persistencyBonus.update()" class="btn btn-primary">保存</button>
		  	</c:otherwise>
		</c:choose>
		<button type="button" onclick="backToIPList()" class="btn btn-primary">返回</button>
		<!-- <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button> -->
	</div>
</form>
</div>            
</body>
</html>