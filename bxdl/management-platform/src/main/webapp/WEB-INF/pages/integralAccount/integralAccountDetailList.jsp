<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>积分账户明细</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/account/integralAccount/integralAccountDetail.css">
    <script src="${path}/js/account/integralAccount/integralAccountDetail.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">
        <form id="conFormIntegralAccountDetail" modelAttribute="map" class=" form-inline hz-form-inline">
            <input type="hidden" class="form-control" id="customerInfoId" name="customerInfoId" value="${map.customerInfoId}">
            <input type="hidden" class="form-control" id="realNameInfoId" name="realNameInfoId" value="${map.realNameInfoId}">
            <div class="form-group">
                <label class="control-label">明细单号</label>
                <input type="text" class="form-control" id="detailOddNo" name="detailOddNo">
            </div>

            <div class="form-group">
                <label class="control-label">明细类别</label>
                <select id="detailType" name="detailType" class="form-control form-control-static">
                    <option value="">请选择</option>
                </select>
            </div>

            <div class="form-group">
                <label class="control-label">积分类别</label>
                <select id="integralType" name="integralType" class="form-control form-control-static">
                    <option value="">请选择</option>
                </select>
            </div>

            <div class="form-group">
                <label class="control-label">状态</label>
                <select id="status" name="status" class="form-control form-control-static">
                    <option value="">请选择</option>
                </select>
            </div>

            <div class="form-group">
                <label class="control-label">生成时间</label>
                <input class="form-control input-sm Wdate" id="minCreateTime" name="minCreateTime" type="text" placeholder="请选择开始时间"
                       onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'minCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
            </div>

            <div class="form-group">
                <label class="control-label">结束时间</label>
                    <input class="form-control input-sm Wdate" id="maxCreateTime" type="text" name="maxCreateTime"
                           placeholder="请选择结束时间"
                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'maxCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
            </div>
            <div class="form-group">
                <button type="button" onclick="integralAccountDetail.searchIntegralAccount()" class="btn btn-info ">
                    <span class="glyphicon glyphicon-search" aria-hidden="true">搜索</span>
                </button>
                <button type="button" onclick="integralAccountDetail.searchEmpty()" class="btn btn-danger ">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true">清空</span>
                </button>
            </div>
        </form>
    </div>
</div>
<!--toolbar  -->
<div id="integralAccountDetail-toolbar" class="btn-toolbar">
    <button onclick="integralAccountDetail.integralExportExcel()" type="button" class="btn btn-success">
        <span class="glyphicon glyphicon-paste" aria-hidden="true">导出</span>
    </button>
</div>
<!--列表 -->
<div style="width: 99%;overflow: auto;">
    <table id="integralAccountDetail-table" class="table table-hover table-striped table-condensed table-bordered"
           style="min-width:1800px;"></table>
</div>
</body>
</html>