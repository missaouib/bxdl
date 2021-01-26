<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>投保单列表</title>
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


function becomeRecCommit() {
	var allTableData = $('#grant_insComanpy-table').bootstrapTable('getData');
	var id="";
	$.each(allTableData,function(i,e){
		id =id+e.POLICY_ID+","
	})
		var data = {id:id}
		$.ajax({
			url:"/insurance_policy/becomeRecCommit",
			data:data, 
			type:"POST",
			dataType:"JSON",
			success: function (obj) {
				if (obj.code=="200"){
					alert("接收成功")
					commCloseTab('becomeInsurancePolicyReceive:commit')
					createAddProcessTab("becomeInsurancePolicyReceive:list","")				} else {
					alert("接收失败")
				}
			},
			error: function () {
				alert("系统异常")
			}
			
		})
}
		var insComanpy = function (){
			return{
				init:function (){
					$('#grant_insComanpy-table').bootstrapTable({
						url: "insurance_policy/do_become_insurance_policy_receive_commit",
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
						toolbar:"#grant_toolbar",
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
							field : "INSURED_PERSON_ID",
							visible:false
						},{
							field : "CORRESPONDING",
							title : "投保单号",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "POLICY_ID",
							title : "保单号",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "INSURANCE_COMPANY_NAME",
							title : "保险公司",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "PRODUCT_CODE",
							title : "产品代码",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "PRODUCT_NAME",
							title : "产品名称",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "PREMIUM",
							title : "保费",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "SALES_ORG_NAME",
							title : "组织机构",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "SALES_TEAM_NAME",
							title : "营销团队",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "NAME1",
							title : "投保人",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "NAME2",
							title : "被保人",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "EMPLOYEE_NO",
							title : "员工工号",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "NAME3",
							title : "员工姓名",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "INSURE",
							title : "投保日期",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter:function (value) {
								var cellval =value+"";
								if (cellval != null) {
									var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
									var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
									var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
									return date.getFullYear() + "-" + month + "-" + currentDate;
								}
							}
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
						principalDeputySign:"0",
						batchName:'${BATCH_NAME}'

					};
					return temp;
				},

				checkSingleData:function () {
					var selected = $('#grant_insComanpy-table').bootstrapTable('getSelections');
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
function clo() {
	commCloseTab('becomeInsurancePolicyReceive:commit')
	createAddProcessTab("becomeInsurancePolicyReceive:list","")
}
	</script>
 
</head>
<body>
<!--列表 -->
<div style="overflow:scroll;">

		<div class="form-group" style="margin-right: 20px;margin-bottom: 10px">
			<label for="becomeRecCom_BATCH_NAME">转移批次名称</label>
			<input type="text" class="form-control" id="becomeRecCom_BATCH_NAME"  value="${BATCH_NAME}" readonly="readonly">
		</div>
		<div class="form-group" style="margin-right: 20px;margin-bottom: 10px">
			<label for="becomeRecCom_dept_name">发送机构</label>
			<input type="text" class="form-control" id="becomeRecCom_dept_name"  value="${dept_name}" readonly="readonly">
		</div>
		<br>

	<div class="form-group" style="margin-right: 20px;margin-bottom: 10px">
		<label for="becomeRecCom_BATCH_TYPE">转移批次类型</label>
		<input type="text" class="form-control" id="becomeRecCom_BATCH_TYPE"  value="${BATCH_TYPE}" readonly="readonly">
	</div>
	<div class="form-group" style="margin-right: 20px;margin-bottom: 10px">
		<label for="becomeRecCom_INSURANCE_SALER">操作转移员工</label>
		<input type="text" class="form-control" id="becomeRecCom_INSURANCE_SALER"  value="${INSURANCE_SALER}" readonly="readonly">
	</div>
	<br>
	<div class="form-group" style="margin-right: 20px;margin-bottom: 10px">
		<label for="becomeRecCom_INSURANCE_COMPANY_NAME">接收机构</label>
		<input type="text" class="form-control" id="becomeRecCom_INSURANCE_COMPANY_NAME"  value="${INSURANCE_COMPANY_NAME}" readonly="readonly">
	</div>
	<div class="form-group" style="margin-right: 20px;margin-bottom: 10px">
		<label for="becomeRecCom_BATCH_DATE">下发日期</label>
		<input type="text" class="form-control" id="becomeRecCom_BATCH_DATE"  value="${BATCH_DATE}" readonly="readonly">
	</div>

</div>
<button class="btn btn-info" onclick="becomeRecCommit()">确认接收</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button class="btn btn-info" onclick="clo()">取消</button>
<div style="overflow:scroll;">
	<table id="grant_insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>


</body>
</html>