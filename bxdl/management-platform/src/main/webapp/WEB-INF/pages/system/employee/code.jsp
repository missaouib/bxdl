<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>员工序号规则管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${path}/css/system/employee/code.css">
    <script src="${path}/js/system/employee/code.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<body>
<button type="button" onclick="Code.updateDic()" class="btn btn-info ">
    <span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span>
</button>
<!--toolbar  -->

<!--列表 -->
<table id="code-table" class="table table-hover table-striped table-condensed table-bordered"></table>

</body>
</html>