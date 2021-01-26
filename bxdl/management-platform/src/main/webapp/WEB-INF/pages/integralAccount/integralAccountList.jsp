<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>积分账户管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/account/integralAccount/integralAccountList.css">
    <script src="${path}/js/account/integralAccount/integralAccount.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-body">
        <form id="conFormIntegralAccount" class=" form-inline hz-form-inline">
            <div class="form-group">
                <label class="control-label">客户姓名</label>
                <input type="text" class="form-control" id="customerName" name="customerName" placeholder="请输入客户姓名">
            </div>

            <div class="form-group">
                <label class="control-label">身份证号</label>
                <input type="text" class="form-control" id="cardNo" name="cardNo" placeholder="请输入身份证号">
            </div>

         <%--   <div class="form-group">
                    <label class="control-label">账户状态</label>
                    <select id="accountStatus" name="accountStatus" class="form-control form-control-static">
                        <option value="">请选择</option>
                    </select>
            </div>
--%>
            <div class="form-group">
                <label class="control-label">渠道</label>
                <select id="channel" name="channel" class="form-control form-control-static">
                    <option value="">请选择</option>
                </select>
            </div>

        <%--    <div class="form-group">
                    <label class="control-label">账户创建时间</label>
                    <input class="form-control input-sm Wdate" id="minRegisterTime" name="minRegisterTime" type="text"
                           placeholder="请选择开始时间"
                           onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'minCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                    <label class="control-label control-label-time">至</label>
                    <input class="form-control input-sm Wdate" id="maxRegisterTime" type="text" name="maxRegisterTime"
                           placeholder="请选择结束时间"
                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'maxCreateTime\');}',dateFmt:'yyyy-MM-dd',readOnly:true})"/>
            </div>--%>
            <div class="form-group">
                <button type="button" onclick="integralAccount.searchIntegralAccount()" class="btn btn-info ">
                    <span class="glyphicon glyphicon-search" aria-hidden="true">搜索</span>
                </button>
                <button type="button" onclick="integralAccount.searchEmpty()" class="btn btn-danger ">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true">清空</span>
                </button>
            </div>
        </form>
    </div>
</div>
<!--toolbar  -->
<div id="integralAccount-toolbar" class="btn-toolbar">
    <button onclick="integralAccount.integralExportExcel()" type="button" class="btn btn-success">
        <span class="glyphicon glyphicon-paste" aria-hidden="true">导出</span>
    </button>
</div>
<!--列表 -->
<div style="width: 99%;overflow: auto;">
    <table id="integralAccount-table" class="table table-hover table-striped table-condensed table-bordered"
           style="min-width:2400px;"></table>
</div>

</body>
</html>