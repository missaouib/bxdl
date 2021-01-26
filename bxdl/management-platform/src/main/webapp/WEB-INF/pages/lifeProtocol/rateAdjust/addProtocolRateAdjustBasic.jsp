<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增费率调节基本信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/protocol/rateAdjust/addProtocolRateAdjustBasic.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body >
<div class="container">
	<form class="form-horizontal" id="addProtocolRateAdjust"  method="post" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="protocolId" value="${protocolId}">
	
		<div class="form-group"   style="margin-top: 10px;">
			<label class="col-md-2 control-label" style="width: 130px;">费率调节名称*：</label>
			<div class="col-md-3 ">
				<input type="text" id="add-adjust-name" name="adjustName" style="width: 200px;" value="${adjust.ADJUST_NAME}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-1 control-label" style="width: 130px;">费率基于*：</label>
			<div class="col-md-3">
				<input  type="radio"   name="rateType" value="0" <c:if test="${adjust.RATE_TYPE == '0'}">checked="checked"</c:if>/>规保
				<input  type="radio"   name="rateType" value="1" <c:if test="${adjust.RATE_TYPE == '1'}">checked="checked"</c:if>style="margin-left: 10px;"/>标保
				<input  type="radio"   name="rateType" value="2" <c:if test="${adjust.RATE_TYPE == '2'}">checked="checked"</c:if>style="margin-left: 10px;"/>首年佣金和
			</div>
		</div>	
		<div class="form-group">
			<label class="col-md-1 control-label" style="width: 130px;">附加费率基于*：</label>
			<div class="col-md-8">
					 <input type="checkbox" name="subjoinType" value="0" >第2年续年度佣金和
				     <input type="checkbox" name="subjoinType" value="1" style="margin-left: 10px;">第3年续年度佣金和
				     <input type="checkbox" name="subjoinType" value="2" style="margin-left: 10px;">第4年续年度佣金和
				     <input type="checkbox" name="subjoinType" value="3" style="margin-left: 10px;">第5年续年度佣金和
				</div>
		</div>	
		<div class="form-group"  style="margin-top: 10px;">
			<label class="col-md-1 control-label" style="width: 130px;">结算周期*：</label>
			<div class="col-md-3">
				<input  type="radio"   name="settlementInterval" value="0" <c:if test="${adjust.SETTLEMENT_INTERVAL == '0'}">checked="checked"</c:if>/>月
				<input  type="radio"   name="settlementInterval" value="1" <c:if test="${adjust.SETTLEMENT_INTERVAL == '1'}">checked="checked"</c:if>style="margin-left: 10px;"/>季
				<input  type="radio"   name="settlementInterval" value="2" <c:if test="${adjust.SETTLEMENT_INTERVAL == '2'}">checked="checked"</c:if>style="margin-left: 10px;"/>半年
				<input  type="radio"   name="settlementInterval" value="3" <c:if test="${adjust.SETTLEMENT_INTERVAL == '3'}">checked="checked"</c:if>style="margin-left: 10px;"/>年
			</div>
		</div>	
		<div class="form-group"  style="margin-top: 10px;">
			<label class="col-md-1 control-label" style="width: 130px;">产品类型*：</label>
			<div class="col-md-3">
				<input  type="radio"   name="productType" value="0" <c:if test="${adjust.PRODUCT_TYPE == '0'}">checked="checked"</c:if>/>单产品
				<input  type="radio"   name="productType" value="1"  style="margin-left: 10px;" <c:if test="${adjust.PRODUCT_TYPE == '1'}">checked="checked"</c:if>/>全产品
				<input  type="radio"   name="productType" value="2"  style="margin-left: 10px;"<c:if test="${adjust.PRODUCT_TYPE == '2'}">checked="checked"</c:if>/>混合产品
			</div>
		</div>	
		<div class="form-group"  style="margin-top: 10px;">
			<label class="col-md-1 control-label" style="width: 130px;">费率调节类型*：</label>
			<div class="col-md-3">
				<input   type="radio"  name="rateAdjustType" value="0" <c:if test="${adjust.RATE_ADJUST_TYPE == '0'}">checked="checked"</c:if>/>固定费率
				<input   type="radio"  name="rateAdjustType" value="1"  <c:if test="${adjust.RATE_ADJUST_TYPE == '1'}">checked="checked"</c:if> style="margin-left: 10px;"/>变动费率
			</div>
		</div>	
		<div class="form-group">
				<input onclick="addProtocolRateAdjustBasic()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
				<button onclick="returnAdjustRateBasic()"  type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" >返回</button>
		</div>
	</form>	
	</div>
	<!-- 单产品/全产品/混合产品  固定费率 添加页面 -->
	<div id="showAdjustFixedDetail" style="margin-top: 20px;display: none; overflow: hidden;">
			<div class="form-group">
				 <label class="col-md-2 control-label" style="width: 180px;">调节费率产品添加*：</label>
           		<div class="col-md-3 ">
            		<a onclick="checkAdjustProduct()" href="javascript:void(0)" style="color: blue;">选择</a>
				</div>
			</div>
        	<table id="adjust-fixed-rate-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
        	<input onclick="updateAdjustFixedRate()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
			<button onclick="returnAdjustFixedRate()"  type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" >返回</button>
        </div>
	
		<!-- 单产品/全产品/混合产品  添加例外产品 选择页面 -->
		<div id="checkAdjustProductDlg"  class="modal fade" aria-hidden="true" style="height: 370px;background: #fff; ">
			    <div>
				    <table id="adjust-check-product-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
				</div>
				<div id="checkAdjustProducrButton" class="btn-group" style="margin-top: 20px;">
	                <input onclick="addAdjustEpProduct()" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" type="button"  value="确定" />
	                <button onclick="cancelAdjustEpProduct()"  type="button" style="background:#1ab394;  height: 30px;width: 80px;color: white; line-height:20px" >取消</button>
	     		</div>
		</div>
		
		<!-- 单产品/全产品 变动费率 添加页面 -->
		<div id="showAdjustChangeDetail" style="margin-top: 20px;display: none; overflow: hidden;">
			<div class="form-group">
					 <label class="col-md-2 control-label" style="width: 180px;">调节费率产品添加*：</label>
	          		<div class="col-md-3 ">
		           		<a onclick="checkAdjustProduct()" href="javascript:void(0)" style="color: blue;">选择</a>
					</div>
			</div>
		    <table id="adjust-change-rate-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
      </div>
      
    <!--混合产品 变动费率 添加页面 -->
	<div id="showBlendAdjustChangeDetail" style="margin-top: 20px;display: none; overflow: hidden;">
		<div class="form-group">
			<label class="col-md-2 control-label" style="width: 180px;">调节费率产品添加*：</label>
         		<div class="col-md-3 ">
           		<a onclick="checkAdjustProduct()" href="javascript:void(0)" style="color: blue;">选择</a>
			</div>
		</div>
	    <table id="blend-adjust-change-rate-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
     </div>
     
	<!-- 协议id -->
	<input type="hidden" id="rate-adjust-protocol-id" value="${protocolId}">
	<!-- 费率调节id -->
	<input type="hidden" id="add-adjust-id" value="${adjustId}">
	
	<!-- 修改-查看协议id -->
	<input type="hidden" id="look_rate_adjust_protocolId" value="${adjust_look_protocolId}">
	<!-- 是否查看 标识-->
	<input id="adjust_basic_isLook" type="hidden" value="${isLook}">
	<!-- 费率调节对象 -->
	<div id="table" style="display: none; overflow: hidden;">
		<span id="hidden_adjust">${adjust}</span>
	</div>
</body>
</html>