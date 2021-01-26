<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日志管理 </title>
    <script src="${path}/js/system/log/logs.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
</head>
<body>
<div class="panel panel-default">
	<div class="panel-body">
		<form id="conForm" class="form-inline hz-form-inline">
  		  <div class="form-group">
  		      <label class="control-label">请求路径</label>
  		   		<input type="text" class="form-control" id="search_requestURL"  placeholder="请输入请求路径">
  		  </div>

  	    <div class="form-group">
  	       <label class="control-label">调用时间</label>
           <input class="form-control" id="startTime"  type="text" placeholder="开始调用时间" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'startTime\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
           <label class="control-label control-label-time">—</label>
           <input class="form-control" id="endTime" type="text" placeholder="结束调用时间" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'endTime\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>

  		  </div>

        <div class="form-group">
      		<button type="button" onclick="Logs.searchLogs()" class="btn btn-info ">
       			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
       		</button>
       		<button type="button" onclick="Logs.empty()" class="btn btn-danger ">
       			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
       		</button>
        </div>
    </form>
	</div>
</div>

<div style="width: 99%;overflow: auto;">
  <table id="logs-table" class="table table-hover table-striped table-condensed table-bordered" style="table-layout: fixed;"></table>
</div>
</body>
</html>