<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>协议费用列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<link rel="stylesheet" href="${path }/static/css/bootstrap-datepicker3.min.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script type="text/javascript" src="${path }/static/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${path }/static/js/bootstrap-datepicker.zh-CN.js"></script>
	<script type="text/javascript">
		var ProtocolCostList = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
			ProtocolCostList.init();
			$(".form_datetime").datepicker({
	            startView: 'years',  //起始选择范围
	            maxViewMode:'years', //最大选择范围
	            minViewMode:'months', //最小选择范围
	            todayHighlight : true,// 当前时间高亮显示
	            autoclose : 'true',// 选择时间后弹框自动消失
	            format : 'yyyy-mm',// 时间格式
	            language : 'zh-CN',// 汉化
	            // todayBtn:"linked",//显示今天 按钮
	            clearBtn : true,// 清除按钮，和今天 按钮只能显示一个
	        });
		});	
		var ProtocolCostList = function (){
			return{
				init:function (){
		            $('#ProtocolCostList-table').bootstrapTable({
		            	url: "lifeProtocol/protocolCostPage",
		            	method:"post",
		                dataType: "json",
		                contentType: "application/x-www-form-urlencoded",
		                striped:true,//隔行变色
		                cache:false,  //是否使用缓存
		                pagination: true, //分页
		                sortable: false, //是否启用排序
		                singleSelect: false,
		                search:false, //显示搜索框
		                buttonsAlign: "right", //按钮对齐方式
		                showRefresh:false,//是否显示刷新按钮
		                sidePagination: "server", //服务端处理分页
		                pageSize : 5, //默认每页条数
		                pageNumber : 1, //默认分页
		                pageList : [5, 10, 20, 50 ],//分页数
		                toolbar:"#ProtocolCostList_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "PROTOCOL_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: ProtocolCostList.queryParams,//传递参数（*）
		                columns : [{
		                    field : "PROTOCOL_NAME",
		                    title : "协议",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    visible: false
		                },{
		                    field : "SALES_ORG_NAME",
		                    title : "组织机构",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    visible: false
		                },{
		                    field : "PRODUCTS_NAME",
		                    title : "产品",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    visible: false
		                },{
		                    field : "DATE_YM",
		                    title : "日期",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SERVICE_CHARGE",
		                    title : "手续费（元）",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SUBSIDY",
		                    title : "续年度服务津贴（元）",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PERSISTENCY_BONUS",
		                    title : "继续率奖金（元）",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "RATE_ADJUST",
		                    title : "费率调节（元）",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PROMOTION",
		                    title : "业务推动费（元）",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                }
		                ],
		        	// bootstrap-table-treetreegrid.js 插件配置 -- end
		                formatLoadingMessage : function() {
		                    return "请稍等，正在加载中...";
		                },
		                formatNoMatches : function() {
		                    return '无符合条件的记录';
		                },
		                onLoadSuccess:function(data){
		                	var selectType = $("#selectType").val();
		                	if (selectType == 1) {
		                		$('#ProtocolCostList-table').bootstrapTable('showColumn', 'PROTOCOL_NAME');
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'SALES_ORG_NAME');
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'PRODUCTS_NAME');
		                	} else if(selectType == 2) {
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'PROTOCOL_NAME');
		                		$('#ProtocolCostList-table').bootstrapTable('showColumn', 'SALES_ORG_NAME');
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'PRODUCTS_NAME');
		                	} else if(selectType == 3) {
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'PROTOCOL_NAME');
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'SALES_ORG_NAME');
		                		$('#ProtocolCostList-table').bootstrapTable('showColumn', 'PRODUCTS_NAME');
		                	} else {
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'PROTOCOL_NAME');
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'SALES_ORG_NAME');
		                		$('#ProtocolCostList-table').bootstrapTable('hideColumn', 'PRODUCTS_NAME');
		                	}
						}
		            });
				},
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNumber: params.pageNumber, //页码
						startTime: $("#startTime").val(),
						endTime: $("#endTime").val(),
						selectType: $("#selectType").val(),
					};
					return temp;
				},
				
				search:function(){
					$("#ProtocolCostList-table").bootstrapTable('destroy');
					ProtocolCostList.init();
				},
				
				empty:function(){
					$("#startTime").val("")
					$("#endTime").val("");
					$("#selectType").val(""),
					$("#ProtocolCostList-table").bootstrapTable('refresh');
				},
			}
		}();
	</script>
 
</head>
<body>

<div class="panel panel-default">
	<div class="panel-body">
		<form id="SearchConForm" class="form-inline hz-form-inline">
		  <div class="form-group">
		  		<label class="control-label">选择</label>
				<select class="form-control form-control-static" id="selectType" name=""selectType"">
		              <option value="">筛选</option>
		              <option value="1">协议</option>
		              <option value="2">组织机构</option>
		              <option value="3">产品</option>
		        </select>
		  </div>
		  <div class="form-group">
		  		<label class="control-label">开始时间</label>
            	<input class="form-control input-sm form_datetime" id="startTime"  type="text" placeholder="开始时间"/>
		  </div>
		   <div class="form-group">
		   		<label class="control-label">结束时间</label>
		   		<input class="form-control input-sm form_datetime" id="endTime" type="text" placeholder="结束时间"/>
		   </div>
		   <div class="form-group">
		  		<button type="button" onclick="ProtocolCostList.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="ProtocolCostList.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   		</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="ProtocolCostList-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->

</body>
</html>