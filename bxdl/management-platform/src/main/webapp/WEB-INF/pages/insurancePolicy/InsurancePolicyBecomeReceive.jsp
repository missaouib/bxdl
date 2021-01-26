<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>保单接收列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		var insComanpy = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
			insComanpy.init();
		});

function becomeReceive(BATCH_NAME,dept_name,BATCH_TYPE,INSURANCE_SALER,INSURANCE_COMPANY_NAME,BATCH_DATE) {
	var id =BATCH_NAME+','+dept_name+','+BATCH_TYPE+','+INSURANCE_SALER+','+INSURANCE_COMPANY_NAME+','+BATCH_DATE
commCloseTab('becomeInsurancePolicyReceive:list')
	createAddProcessTab("becomeInsurancePolicyReceive:commit",id)
}
		function operateFormatter(value, row, index) {

			var BATCH_NAME =row.BATCH_NAME

			var dept_name = row.dept_name

			var BATCH_TYPE = row.BATCH_TYPE

			var INSURANCE_SALER = row.INSURANCE_SALER

			var INSURANCE_COMPANY_NAME =row.INSURANCE_COMPANY_NAME

			var BATCH_DATE = row.BATCH_DATE
			return [
				'<shiro:hasPermission name="becomeInsurancePolicyReceive:commit">',
				'<button type="button" class="btn btn-info" onclick="becomeReceive(\'' +BATCH_NAME+ '\',\'' + dept_name + '\',\'' + BATCH_TYPE + '\',\'' +INSURANCE_SALER + '\',\'' + INSURANCE_COMPANY_NAME + '\',\'' + BATCH_DATE + '\')">',
				'<span class="glyphicon glyphicon-pencil" >接收</span>',
				'</button>',
				'</shiro:hasPermission>',

			].join('');
		}
		var insComanpy = function (){
			return{
				init:function (){
		            $('#br_insComanpy-table').bootstrapTable({
		            	url: "insurance_policy/do_become_insurance_policy_receive",
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
		                toolbar:"#toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: insComanpy.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
							field: 'SerialNumber',
							title: '序号',
							formatter: function (value, row, index) {
								return index+1;
							}
						},{
		                    field : "BATCH_NAME",
		                    title : "转移批次名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "dept_name",
		                    title : "发送机构",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "BATCH_TYPE",
		                    title : "转移资料类型",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
							field : "INSURANCE_SALER",
							title : "下发转移员工",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "name",
							title : "发送合作伙伴",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "BATCH_DATE",
							title : "发送转移日期",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "INSURANCE_COMPANY_NAME",
							visible:false
						},{
						title: "操作",
						align: "center",
						valign: "middle",
						sortable: "true",
						formatter: operateFormatter
					}],
		        	// bootstrap-table-treetreegrid.js 插件配置 -- end
		                formatLoadingMessage : function() {
		                    return "请稍等，正在加载中...";
		                },
		                formatNoMatches : function() {
		                    return '无符合条件的记录';
		                }
		            });
				},
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNumber: params.pageNumber, //页码
						orgLevel: "01", //组织机构级别
					};
					return temp;
				},
				
		        checkSingleData:function () {
		            var selected = $('#br_insComanpy-table').bootstrapTable('getSelections');
		            if (selected.length == 0) {
		            	 $.alert({
		                     title: '提示信息！',
		                     content: '至少选择一条记录！',
		                     type: 'red'
		                 });
		                return false;
		            }else if (selected.length > 1){
		            	 $.alert({
		                     title: '提示信息！',
		                     content: '该操作只能选择一条记录!',
		                     type: 'blue'
		                 });
		            }else {
		            	insComanpy.seItem = selected[0];
		                return true;
		            }
		        },
			}
		}();
	</script>
 
</head>
<body>
<!--列表 -->
<div style="overflow:scroll;">
	<table id="br_insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
</body>
</html>