<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>添加协议基本信息</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/js/protocol/protocolBasic/addLifeProtocolBasic.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/js/jquery-form.js" charset="UTF-8" type="text/javascript"></script>
</head>

<body>
<!-- 导航栏 -->
<div style="height: 70px;margin-top: 5px;">
		<button type="button" style="margin-left: 20px;height: 40px;width: 150px;" onclick="openAddProtocolBasic()">1.协议信息</button>
		<button type="button" style="margin-left: 25px;height: 40px;width: 150px;"  onclick="showSecondMenu()" >2.手续费及折标率</button>
		<%--<button type="button" style="margin-left: 25px;height: 40px;width: 150px;" onclick="openAddProtocolSubsidy()" >3.续年度服务津贴</button>
		<button type="button" style="margin-left: 25px;height: 40px;width: 150px;" onclick="openPersistencyBonus()" >4.继续率奖金</button>
		<button type="button" style="margin-left: 25px;height: 40px;width: 150px;" onclick="openProtocolRateAdjust()" >5.费率调节</button>
		<button type="button" style="margin-left: 25px;height: 40px;width: 150px;" onclick="openProtocolPromotion()" >6.业务推动</button>--%>
</div>
<div id="showSecond" style="height: 50px;display: none; overflow: hidden;">
		<button type="button" style="margin-left: 50px;height: 35px;width: 140px;" onclick="openAddProtocolService()">2.1.手续费配置</button>
		<%--<button type="button" style="margin-left: 20px;height: 35px;width: 140px;" onclick="openAddProtocolStandard()" >2.2.折标率配置</button>--%>
		<%--<button type="button" style="margin-left: 20px;height: 35px;width: 140px;" onclick="openAddProtocolInsideStandard()" >2.3.内部折标率配置</button>--%>
</div>
<div class="container">
<!-- 基本信息 -->
	<form class="form-horizontal" id="addProtocolBasicInfoForm"  method="post" enctype="application/x-www-form-urlencoded">
		<div class="btn-toolbar" style="overflow:scroll;background: #C0C0C0;">
			<span style="line-height:35px;display:block;font-size: 15px;font-weight: bold;">基本信息</span>
		</div>
		<div class="form-group">
			<label class="col-md-1 control-label" style="width: 130px;">协议名称*：</label>
			<div class="col-md-3">
				<input type="text" class="form-control form-control-static"   name="protocolName"  value="${protocol.PROTOCOL_NAME}" />
				<input type="hidden" class="form-control form-control-static"   name="protocolId"  value="${protocol.PROTOCOL_ID}" />
			</div>
			<label class="col-md-1 control-label"  style="width: 130px;">协议代码*：</label>
			<div class="col-md-3">
				<input type="text" class="form-control form-control-static" name="protocolCode" value="${protocol.PROTOCOL_CODE}" />
			</div>
			<label class="col-md-1 control-label"  style="width: 130px;">中介机构*：</label>
			<div class="col-md-3">
					<input type="text" id="c_salesOrgId"  name="c_salesOrgId" value="${protocol.SALES_ORG_NAME}" class="form-control" readonly="readonly" >
					<input type="hidden" id="salesOrgId" name="salesOrgId" class="form-control" value="${protocol.SALES_ORG_ID}" >
					<div id="protocolParentIdtreeview"  class="modal fade" tabindex="-1" role="dialog" style="height: 500px; margin-left:200px; margin-top: 0; width:360px; padding-right:17px; background: #f3f3f4;">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding:2px 5px;">&times;</button>
						<div id="protocolParentIdtreeviewDiv" style="margin-top: 30px; height: 470px; overflow-y: auto; padding: 10px;"></div>
					</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-1 control-label" style="width: 130px;">签约保险公司*：</label>
			<div class="col-md-3">
 					<select onchange= "changeCompany()" id="INSURANCE_COMPANY_ID" name="insuranceCompanyId" readonly="readonly"  class="form-control form-control-static">
                        <option value="">请选择</option>
                  </select>
             </div>
			<label class="col-md-1 control-label"  style="width: 130px;">保险公司机构*：</label>
			<div class="col-md-3">
					<input type="text" id="protocol_org_name" name="c_insuranceOrgId" value="${protocol.INSURANCE_ORG_NAME}" readonly="readonly"  class="form-control" >
					<input type="hidden" id="INSURANCE_ORG_ID" name="insuranceOrgId" value="${protocol.INSURANCE_ORG_ID}" class="form-control">
					<div id="protocolParentCodetreeview"  class="modal fade" tabindex="-1" role="dialog" style="margin-left:200px; width:300px;background: #f3f3f4;"/>
			</div>
			<label class="col-md-1 control-label"  style="width: 130px;">签订日期*：</label>
			<div class="col-md-3">
	              <input class="form-control input-sm Wdate" type="text" id="SIGNING_DATE"  name="cSigningDate"  value="${protocol.SIGNING_DATE}"  onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true})" style="height: 30px;"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-1 control-label" style="width: 130px;">生效日期*：</label>
			<div class="col-md-3">
	                   <input class="form-control input-sm Wdate" type="text" id="EFFECTIVE_DATE" name="cEffectiveDate" value="${protocol.EFFECTIVE_DATE}"  onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true})" style="height: 30px;"/>
			</div>
			<label class="col-md-1 control-label"  style="width: 130px;">终止日期*：</label>
			<div class="col-md-3">
	               <input class="form-control input-sm Wdate" type="text" id="TERMINATION_DATE" name="cTerminationDate" value="${protocol.TERMINATION_DATE}"   onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true})" style="height: 30px;"/>
			</div>
				<input id = "saveProtocolBasicInfo_id" onclick="AddLifeProtocolBasic.saveProtocolBasicInfo()()" style="background:#1ab394; margin-left:40px;  height: 28px;width: 70px;color: white; line-height:20px" type="button"  value="保存" />
		</div>
	</form>

	<!-- 添加产品管理 -->
	<div class="btn-toolbar" style="background: #C0C0C0;">
			<span style="line-height:35px; background: #C0C0C0; display:block;font-size: 15px;font-weight: bold;">
				添加产品管理
				<input id="addProtocolAndProduct"  onclick="AddLifeProtocolBasic.addProtocolAndProduct()" style="background:#1ab394;  height: 28px;margin-left: 30px; margin-top:5px;  width: 80px;color: white; line-height:30px"  vertical-align:middle;" type="button"  value="新增产品" />
			</span>
	</div>
	<table id="protocol-product-table" class="table table-hover table-striped table-condensed table-bordered"></table>

	<div id="checkProductDlg"  class="modal fade" aria-hidden="true" style="height: 370px;background: #fff; ">
		    <div>
			    <table id="check-product-table"  style="overflow: hidden;padding: 5px 15px 15px;" class="table table-hover table-striped table-condensed table-bordered"></table>
			</div>
			<div id="checkProducrButton" class="btn-group">
                <button type="button"  class="btn btn-primary" onclick="AddLifeProtocolBasic.productBindingProtocol()">确定</button>
                <button type="button" onclick="AddLifeProtocolBasic.cancelCheckProduct()" class="btn btn-default" style="margin-left: 20px;">取消</button>
     		</div>
	</div>

	<!-- 产品管理列表 -->
  	<table id="product-manage-table" class="table table-hover table-striped table-condensed table-bordered"></table>

    <!-- 添加影像件信息 -->
	<div class="btn-toolbar" style="background: #C0C0C0;">
				<span style="line-height:35px;display:inline; background: #C0C0C0;font-size: 15px;font-weight: bold;">
		 			  影像件信息
		 			<input onclick="AddLifeProtocolBasic.openImageDlg()" style="background:#1ab394;  height: 28px;margin-left:30px; margin-top:5px;  width: 100px;color: white; line-height:30px"  vertical-align:middle;" type="button"  value="新增影像件" />
				</span>
	</div>
	<div id="openImageDlg" class="modal fade" aria-hidden="true">
		  <div class="modal-dialog">
	        <div class="modal-content">
			    		 <form id="imageForm"  enctype="multipart/form-data" style="margin-top: 10px;">
	         				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				    		<input type="file" name="file" id="fileName" style="height: 30px;" >
							<input style="margin-left: 260px;" type="button" value="上传" onclick="AddLifeProtocolBasic.uploadImage()" class="btn btn-info" />
						</form>
	        </div>
        </div>
	</div>
	<table id="protocol-image-table" class="table table-hover table-striped table-condensed table-bordered"></table>
	<input onclick="addfirstProtocol()" style="background:#1ab394;  height: 30px;margin-left: 400px;width: 80px;color: white; line-height:20px" type="button"  value="保存" />
	<button type="button" style="background:#1ab394;  height:30px;width: 80px; color: white; line-height:20px"  onclick="returnProtocolList()">返回列表</button>

	<!-- 协议id -->
	<input type="hidden" id="new_protocol_id" value="${protocolId}">
	<!-- 一级保险公司id -->
	<input id="insuranceCompanyId" type="hidden" value="${INSURANCE_COMPANY_ID}">
	<!-- 是否查看 标识-->
	<input id="isLook" type="hidden" value="${isLook}">
	<!-- 修改 查看 协议id-->
	<input type="hidden" id="basic_look_protocol_Ids" value="${lookProtocolIds}">

</body>
</html>