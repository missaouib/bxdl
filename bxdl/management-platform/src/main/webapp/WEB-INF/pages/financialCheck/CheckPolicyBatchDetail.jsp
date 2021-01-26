<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>批次详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/js/jquery-form.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/js/bootstrap3-typeahead.js" type="text/javascript"></script>
    <script src="${path}/js/financialCheck/batchDetail.js"></script>
</head>

<body>
<!-- 导航栏 -->
<div style="height: 60px;margin-top: 5px;background-color: #bcdedf;margin-left: 15px;" id = "showOne">
    <button id="addBatch_button" style="margin-left: 20px;height: 40px;width: 150px;margin-top: 10px"
            onclick="createBatchButton()">1.生成批次
    </button>
    <button id="checkImport_button"  style="margin-left: 25px;height: 40px;width: 150px;"
            onclick="checkImportButton()">2.对账导入
    </button>
    <button id="extractData_button" style="margin-left: 25px;height: 40px;width: 150px;"
            onclick="extractDataButton()">3.本司提取清单
    </button>
    <button id="checkResult_button"  style="margin-left: 25px;height: 40px;width: 150px;"
            onclick="checkResultButton()">4.核对结果
    </button>
</div>
<div id="showSecond" hidden="hidden" style="height: 40px;background-color: #d1e2e3;margin-left: 15px;">
		<button id = "checkResultForStatus_1"   style="margin-left: 380px;height: 35px;width: 140px;" onclick="showCheckResultForStatus(1)">4.1.保单结算不一致</button>
		<button id = "checkResultForStatus_2"   style="margin-left: 20px;height: 35px;width: 180px;" onclick="showCheckResultForStatus(2)" >4.2.我司存在,保司不存在</button>
		<button id = "checkResultForStatus_3"  style="margin-left: 20px;height: 35px;width: 180px;" onclick="showCheckResultForStatus(3)" >4.3.保司存在，我司不存在</button>
    	<button id = "checkResultForStatus_4"   style="margin-left: 20px;height: 35px;width: 140px;" onclick="showCheckResultForStatus(4)" >4.4.核对无误</button>
</div>
<div class="container">
    <!-- 新增批次-->
    <div id="batch_div" class="panel panel-default" style="margin-top: 5px">
        <div class="panel-body">
            <form class="form-inline hz-form-inline" id="addCheckPolicyBatchForm" method="post"
                  enctype="application/x-www-form-urlencoded">


                <div class="form-group has-feedback">
                    <label class="control-label" for="companyOrgName">保险公司名称</label>
                    <input style="width: 300px" type="text" class="form-control" id="companyOrgName"
                           name="companyOrgName"  value="${checkPolicyBatch.companyOrgName}" readonly="readonly"></input>
                     <input  type="hidden"  id="companyOrgId" name="companyOrgId" value="${checkPolicyBatch.companyOrgId}"></input>
                </div>
                <div class="form-group has-feedback" >
                    <label class="control-label" for="salesOrgName">组织机构名称</label>
                    <input style="width: 300px"  type="text" class="form-control" id="salesOrgName" name="salesOrgName"
                           placeholder="组织机构名称" value="${checkPolicyBatch.salesOrgName}" readonly="readonly"></input>
                     <input  type="hidden" class="form-control" id="salesOrgId" name="salesOrgId" value="${checkPolicyBatch.salesOrgId}"></input>
                </div>
                <div class="form-group" style="margin-top: 20px">
                    <label class="control-label">对账月份</label>
                    <input style="width: 300px"  class="form-control"  name="checkMonth" id="checkMonth" type='text' value="${checkPolicyBatch.checkMonth}" readonly="readonly"/>
                </div>
                <div class="form-group" style="margin-top: 20px">
                    <label class="control-label" for="batchName">批次名称</label>
                    <input style="width: 300px" type="text" class="form-control" id="batchName" name="batchName"
                           placeholder="批次名称" value="${checkPolicyBatch.batchName}" readonly="readonly"></input>
                </div>
                <div class="form-group" style="margin-top: 20px">
                    <label class="control-label" for="batchNum">批次号</label>
                    <input style="width: 300px" type="text" class="form-control" id="batchNum" name="batchNum" value="${checkPolicyBatch.batchNum}" readonly="readonly"></input>
                </div>
               <input id="checkReustStatus_id" hidden="hidden" value="">
            </form>
        </div>
        <button type="button" style="background:#22adb3; margin-left: 400px; height:30px;width: 80px; color: white; line-height:20px"
                onclick="returnCheckPolicyBatchList()">返回列表
        </button>
    </div>

    <%--保险公司上传数据--%>
    <div id="checkPolicyDataComp_div" hidden="hidden">
        <div style="width: 99%;overflow: auto;">
            <table id="checkPolicyDataComp-table"
                   class="table table-hover table-striped table-condensed table-bordered"></table>
        </div>
    </div>


    <%--提取数据--%>
    <div id="extractPolicyDataHK_div" hidden="hidden">
        <div style="width: 99%;overflow: auto;">
            <table id="extractPolicyDataHK-table"
                   class="table table-hover table-striped table-condensed table-bordered"></table>
        </div>
    </div>

    <%--核对结果--%>
    <div id="checkResult_div" hidden="hidden">
        <div style="width: 99%;overflow: auto;">
            <table id="checkResult-table"
                   class="table table-hover table-striped table-condensed table-bordered"></table>
        </div>
         <div id="toolbarCheckResult" class="btn-toolbar" style="overflow:scroll;">
            <button class="btn btn-info"  type="button" onclick="exportExtractData()">
                <span class="glyphicon glyphicon-import	">导出excel</span>
            </button>
        </div>
    </div>
</body>
</html>