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
	<script src="${path}/static/js/jquery-form.js"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		function search(){
			alert("搜索")
		}
		function cla() {
			$("#be_exampleInputName1").val('')
			$("#be_exampleInputName2").val('')
			$("#be_exampleInputName3").val('')
			$("#be_exampleInputName4").val('')
			$("#be_exampleInputName5").val('')
			$("#be_exampleInputName6").val('')
			$("#be_exampleInputName7").val('')
			$("#be_exampleInputName8").val('')
			$("#be_exampleInputName9").val('')
			$("#be_exampleInputName20").val('')
			$("#be_exampleInputName21").val('')
			$("#be_exampleInputName22").val('')
			$("#be_exampleInputName23").val('')
			$("#be_exampleInputName24").val('')
			$("#be_exampleInputName25").val('')
					insComanpy.search()
		}
		var insComanpy = {
			seItem: null		//选中的条目
		};

		$(function () {
			insComanpy.init();
			commSelectProductOrg("be_exampleInputName2")
			commSalesOrgsHX("be_exampleInputName5")
			$("#be_exampleInputName5").on(
					"change",function(){
						commSalesTeamsHX("be_exampleInputName6",$(this).find("option:selected").val());
					}
			)
		});
		function delDept(row) {
			var cha = $('#be_insComanpy-table').bootstrapTable('getSelections');
			if (cha.length<1){
				$.alert({
					title: '提示信息！',
					content: '至少选择一条记录！',
					type: 'red'
				});
			}else{
				$.confirm({
					title: '提示信息！',
					content: '您确定要删除所选记录吗？',
					type: 'red',
					buttons: {
						confirm: function () {
							var ids = "";
							for(var i=0; i<cha.length; i++){
								ids+=cha[i].INSURANCE_COMPANY_CODE+",";
							}

							var data={"ids":ids}
							$.ajax({
								url:"insurance_dept/delete_insurance_dept",
								data:data,
								type:"POST",
								dataType:"json",
								success: function(obj){

									if (obj.returnCode==200){
										$.alert({
											title: '提示信息！',
											content: '删除成功！',
											type: 'green'
										});
										$("#be_insComanpy-table").bootstrapTable('refresh');
									} else if (obj.returnCode==201) {
										//数据有子集
										$.alert({
											title: '提示信息！',
											content: '无法删除：部门'+obj.data+"为选中部门子部门,请先删除该部门以及子部门",
											type: 'red'
										});
									}else{
										$.alert({
											title: '提示信息！',
											content: '删除失败,系统异常',
											type: 'red'
										});
									}
								},
								error:function (obj) {
									$.alert({
										title: '提示信息！',
										content: '删除失败,系统异常',
										type: 'red'
									});
								}

							})
						},
						cancel: function () {

						}}


				})
			}
		}
		function openDetailTab(permission){
			if(insComanpy.checkSingleData()){
				var rowid = insComanpy.seItem.POLICY_ID;
				//alert(insComanpy.seItem.INSURANCE_COMPANY_ID);
				createAddProcessTab('insurancePolicy:update',rowid);
			}
		}
		//修改——转换日期格式(时间戳转换为datetime格式)
		function changeDateFormat(cellval) {
			if (cellval != null) {
				var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
				var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
				var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
				return date.getFullYear() + "-" + month + "-" + currentDate;
			}
		}
		function becomeExamine(id) {
			commCloseTab('becomeInsurancePolicy:list')
			createAddProcessTab("becomeInsurancePolicy:examine",id)
		}
		function operateFormatter(value, row, index) {
			var id = row.POLICY_ID;
			return [
				'<shiro:hasPermission name="becomeInsurancePolicy:examine">',
				'<button type="button" class="btn btn-info" onclick="becomeExamine(\'' + id + '\')">',
				'<span class="glyphicon glyphicon-pencil" >审核</span>',
				'</button>',
				'</shiro:hasPermission>',

			].join('');
		}
		var insComanpy = function (){
			return{
				init:function (){
					$('#be_insComanpy-table').bootstrapTable({
						url: "/insurance_policy/do_become_insurance_policy",
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
						toolbar:"#ibiptoolbar",
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
						},{
							field : "INS_STATE",
							title : "作业节点",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter:function (value) {
								return getStateTest(value);
							}
						},{
							field : "TAKE_EFFECT_DATA",
							title : "生效日期",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter: function (value, row, index) {
								value=value+"";
								return changeDateFormat(value)
							}
						},{
							field : "UNDERWRITING_DATA",
							title : "承保日期",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter: function (value, row, index) {
								value=value+"";
								return changeDateFormat(value)
							}
						}, {
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
				search:function(){
					$("#be_insComanpy-table").bootstrapTable('destroy');
					  insComanpy.init();
				},
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						principalDeputySign:"0",
						orgLevel: "01", //组织机构级别
						type:"2",
						insState:"1001,1003",
						exampleInputName1:$("#be_exampleInputName1").val(),
						exampleInputName2:$("#be_exampleInputName2").val(),
						exampleInputName3:$("#be_exampleInputName3").val(),
						exampleInputName4:$("#be_exampleInputName4").val(),
						exampleInputName5:$("#be_exampleInputName5").val(),
						exampleInputName6:$("#be_exampleInputName6").val(),
						exampleInputName7:$("#be_exampleInputName7").val(),
						exampleInputName8:$("#be_exampleInputName8").val(),
						exampleInputName9:$("#be_exampleInputName9").val(),
						exampleInputName20:$("#be_exampleInputName20").val(),
						exampleInputName21:$("#be_exampleInputName21").val(),
						exampleInputName22:$("#be_exampleInputName22").val(),
						exampleInputName23:$("#be_exampleInputName23").val(),
						exampleInputName24:$("#be_exampleInputName24").val(),
						exampleInputName25:$("#be_exampleInputName25").val(),
					};
					return temp;
				},

				checkSingleData:function () {
					var selected = $('#be_insComanpy-table').bootstrapTable('getSelections');
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
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline">
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName1">保单号</label>
				<input type="text" class="form-control" id="be_exampleInputName1" name="be_exampleInputName1" placeholder="保单号">
			</div>
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName2">保险公司</label>
				<select type="text" class="form-control" id="be_exampleInputName2" name="be_exampleInputName2" placeholder="保险公司"></select>
			</div>
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName3">产品名称</label>
				<input type="text" class="form-control" id="be_exampleInputName3" name="be_exampleInputName3" placeholder="产品名称"></input>
			</div>
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName4">产品代码</label>
				<input type="text" class="form-control" id="be_exampleInputName4" name="be_exampleInputName4" placeholder="产品代码">
			</div>
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName5">组织机构</label>
				<select type="text" class="form-control" id="be_exampleInputName5" name="be_exampleInputName5" placeholder="组织机构"></select>
			</div>
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName6">营销团队</label>
				<select type="text" class="form-control" id="be_exampleInputName6" name="be_exampleInputName6" placeholder="营销团队"></select>
			</div>
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName7">投保人</label>
				<input type="text" class="form-control" id="be_exampleInputName7" name="be_exampleInputName7" placeholder="投保人">
			</div>
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName8">被保人</label>
				<input type="text" class="form-control" id="be_exampleInputName8" name="be_exampleInputName8" placeholder="被保人">
			</div>
			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName9">员工工号</label>
				<input type="text" class="form-control" id="be_exampleInputName9" name="be_exampleInputName9" placeholder="员工工号">
			</div>

			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName20">投保日期</label>
				<input type="date" class="form-control" id="be_exampleInputName20" name="be_exampleInputName20" placeholder="投保日期">
				<div class="control-label control-label-time">至</div>
				<input type="date" class="form-control" id="be_exampleInputName21" name="be_exampleInputName21" placeholder="投保日期">
			</div>

			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName22">生效日期</label>
				<input type="date" class="form-control" id="be_exampleInputName22" name="be_exampleInputName22" placeholder="生效日期">
				<div class="control-label control-label-time">至</div>
				<input type="date" class="form-control" id="be_exampleInputName23" name="be_exampleInputName23" placeholder="生效日期">
			</div>


			<div class="form-group" >
				<label  class="control-label" for="be_exampleInputName24">承保日期</label>
				<input type="date" class="form-control" id="be_exampleInputName24" name="be_exampleInputName24" placeholder="承保日期">
				<div class="control-label control-label-time">至</div>
				<input type="date" class="form-control" id="be_exampleInputName25" name="be_exampleInputName25" placeholder="承保日期">
			</div>

			<div class="form-group" >
				<button type="button" class="btn btn-info" onclick="insComanpy.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>
</div>

<div style="width: 99%;overflow: auto;">
	<table id="be_insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
</body>
</html>