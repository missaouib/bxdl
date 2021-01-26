<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>继续率奖金-修改例外产品	</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/insurance/pbExitProUpd.js"></script>
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
<div id="XY-PB-top" style="margin:20 0 20 0">
	<button type="button" class="btn btn-info" onclick="backToPb()">返回上一步</button>
	<button type="button" class="btn btn-info" onclick="backToIPList()">返回列表</button>
</div>
<div id="XY-PB-toolbar" style="margin:20 0 20 0">
	<button type="button" class="btn btn-info" onclick="openAddProtocolBasic()">1.协议信息</button>
	<button type="button" class="btn btn-info" onclick="openAddProtocolService()" >2.手续费及折标率</button>
	<button type="button" class="btn btn-info" onclick="openAddProtocolSubsidy()" >3.续年度服务津贴</button>
	<button type="button" class="btn btn-info" onclick="javascript:void(0)" >4.继续率奖金</button>
	<button type="button" class="btn btn-info" onclick="" >5.费率调节</button>
	<button type="button" class="btn btn-info" onclick="openProtocolPromotion()" >6.业务推动</button>
</div>
<form class="form-horizontal" id="Pb_ExitPro_addForm"  method="post">
	<div class="form-group">
	<input type="hidden" id="protocolId" name="protocolId" value="${ippbs.protocolId}" />
	<input type="hidden" id="bonusId" name="bonusId" value="${ippbs.bonusId}" />
	<input type="hidden" id="exitProductId_hide" name="exitProductId_hide" value="${exitProductId}" />
	<input type="hidden" id="outProducts" name="outProducts" value='${outProducts}' />
	<input id="isLook" type="hidden" value="${isLook}">
	</div>	
	
	<div class="form-group">
		<label class="col-md-2 control-label">产品名称：</label>
		<div class="col-md-3">
			<select class="form-control form-control-static" id="exitProductId" name="exitProductId">
				<option value="">请选择</option>
			</select>
		</div>	
		
		<label class="col-md-2 control-label">产品代码：</label>
		<div class="col-md-3">
			<select class="form-control form-control-static" id="exitProCode" name="exitProCode">
				<option value="">请选择</option>
			</select>
		</div>		
	</div>
		
	<div class="form-group" style="display:none">
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
	</div>
	<input type="hidden" id="monthyBasic" name="monthyBasic" value='${monthyBasic}' /><!--结算继续率奖金参数-->
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
	<input type="hidden" id="thrMonthyBasic" name="thrMonthyBasic" value='${thrMonthyBasic}' /><!--季度算继续率奖金参数-->
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
	<input type="hidden" id="sixMonthyBasic" name="sixMonthyBasic" value='${sixMonthyBasic}' /><!--半年算继续率奖金参数-->
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
	<input type="hidden" id="twnMonthyBasic" name="twnMonthyBasic" value='${twnMonthyBasic}' /><!--年通算继续率奖金参数-->
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
    <div class="modal-footer col-md-6">
	   <input type="reset" name="reset" style="display: none;" />
	   <button type="button" class="btn btn-default" onclick="windowCloseTab()">关闭</button>
	   <c:choose>
		   	<c:when test="${isLook == 'yes'}">
			   <button id="saveButton" type="button" disabled="disabled" onclick="pbExitPro.add()" class="btn btn-primary">保存</button>
		   	</c:when>
		  	<c:otherwise>
			   <button id="saveButton" type="button" onclick="pbExitPro.add()" class="btn btn-primary">保存</button>
		  	</c:otherwise>
		</c:choose>
	</div>
</form>
</div>            
</body>
</html>