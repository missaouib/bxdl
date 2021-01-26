<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>投保照会/保单录入</title>
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
        function downloadTemplate() {
            window.open("${path}/upload/files/照会回销模板.xls");
        }
		function search(){
			alert("搜索")
		}
		function cla() {
			$("#not_exampleInputName1").val('')
			$("#not_exampleInputName2").val('')
			$("#not_exampleInputName3").val('')
			$("#not_exampleInputName4").val('')
			$("#not_exampleInputName5").val('')
			$("#not_exampleInputName6").val('')
			$("#not_exampleInputName7").val('')
			$("#not_exampleInputName8").val('')
			$("#not_exampleInputName9").val('')
			$("#not_exampleInputName20").val('')
			$("#not_exampleInputName21").val('')
			insComanpy_IPNOE.search()
		}
		var insComanpy_IPNOE = {
			seItem: null		//选中的条目
		};

		function closeDlg() {//关闭
			$("#not_alert_div").modal('hide');
		}

        function closeImport() {//关闭导入
            $("#alertFileNote").modal('hide');
        }

		function alertFile() {
			$("#alertFileNote").modal('show')
		}

		function upload() {
			var multipart = $("#fileName").val();
			if (multipart == "" || multipart == null) {
				alert("请先选择文件!");
				return;
			}
			$("#alertFileNote").modal('hide');
			$("#loadingModal").modal('show');
			//$('#loadingModal').modal('hide');
			$("#fileForm").ajaxSubmit({
				type: 'POST',
				url: 'insurance_policy/import_note_or_entry',
				dataType: 'json',
				success: function (data) {
					$('#loadingModal').modal('hide');
					if (data.code == "200") {

						alert("文件导入成功")
						insComanpy_IPNOE.search();
					}else if(data.code == "500"){
						alert(data.error)
					} else {
						alert("文件导入失败请重新上传")
					}
				},
				error: function (data) {
					$('#loadingModal').modal('hide');
					alert("系统异常")
				}
			})
		}

		$(function () {
			insComanpy_IPNOE.init();
			getSysDictVal("not_auditresults","AUDITRESULTS")
			commSelectProductOrg("not_exampleInputName2")
			commSalesOrgsHX("not_exampleInputName5")
			$("#not_exampleInputName5").on(
					"change",function(){
						commSalesTeamsHX("not_exampleInputName6",$(this).find("option:selected").val());
					}
			)
		});
		function  notel(id){
			commCloseTab('insurancePolicyNoteOrEntry:list')
			createAddProcessTab("insurancePolicyNote:list",id)
		}
		function entry(id){
			commCloseTab('insurancePolicyNoteOrEntry:list')
			createAddProcessTab("insurancePolicyEntry:list",id)
		}
		function operateFormatter(value, row, index) {
			var id = row.POLICY_ID;

			return [
				'<shiro:hasPermission name="insurancePolicyNote:list">',
				'<button type="button" class="btn btn-info" onclick="notel(\''+id+'\')">',
				'<span class="glyphicon glyphicon-pencil" >照会</span>',
				'</button>',
				'</shiro:hasPermission>',
				'<shiro:hasPermission name="insurancePolicyEntry:list">',
				'<button type="button" class=" btn btn-info" onclick="entry(\''+id+'\')">',
				'<span class="glyphicon glyphicon-pencil" >录入</span>',
				'</button>',
				'</shiro:hasPermission>'

			].join('');
		}
		var insComanpy_IPNOE = function (){
			return{
				init:function (){
					$('#not_insComanpy-table').bootstrapTable({
						url: "insurance_policy/do_insurance_policy_note_or_entry",
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
						toolbar:"#insurance_toolbar_button_noe",
						showColumns : false, //显示隐藏列
						uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
						queryParamsType:'',
						queryParams: insComanpy_IPNOE.queryParams,//传递参数（*）
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
							field : "POLICY_ID",
							title : "投保单号",
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
							field : "STATE",
							title : "状态",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter:function (value) {
								if (value=='5'){
									value="已移交"
								}else if(value=='9'){
									value="已移交"
								}
								else{
									value = "错误状态"
								}
								return value;
							}
						},{
							title : "操作",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter:operateFormatter
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
					$("#not_insComanpy-table").bootstrapTable('destroy');
					insComanpy_IPNOE.init();
				},
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						principalDeputySign:"0",
						type:"1",
						state:"5,9",
						exampleInputName1:$("#not_exampleInputName1").val(),
						exampleInputName2:$("#not_exampleInputName2").val(),
						exampleInputName3:$("#not_exampleInputName3").val(),
						exampleInputName4:$("#not_exampleInputName4").val(),
						exampleInputName5:$("#not_exampleInputName5").val(),
						exampleInputName6:$("#not_exampleInputName6").val(),
						exampleInputName7:$("#not_exampleInputName7").val(),
						exampleInputName8:$("#not_exampleInputName8").val(),
						exampleInputName9:$("#not_exampleInputName9").val(),
						exampleInputName20:$("#not_exampleInputName20").val(),
						exampleInputName21:$("#not_exampleInputName21").val(),

					};
					return temp;
				},

				checkSingleData:function () {
					var selected = $('#not_insComanpy-table').bootstrapTable('getSelections');
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
						insComanpy_IPNOE.seItem = selected[0];
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
		<form class="form-inline hz-form-inline" id="not_search" enctype="application/x-www-form-urlencoded">
			<div class="form-group">
				<label class="control-label" for="not_exampleInputName2">投保单号</label>
				<input type="text" class="form-control" name="not_exampleInputName1" id="not_exampleInputName1" placeholder="投保单号">
			</div>
			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">保险公司</label>
				<select type="text" class="form-control" name="not_exampleInputName2" id="not_exampleInputName2" placeholder="保险公司"></select>
			</div>
			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">产品名称</label>
				<input type="text" class="form-control" name="not_exampleInputName3" id="not_exampleInputName3" placeholder="产品名称">
			</div>
			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">产品代码</label>
				<input type="text" class="form-control" name="not_exampleInputName4" id="not_exampleInputName4" placeholder="产品代码">
			</div>
			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">组织机构</label>
				<select type="text" class="form-control" name="not_exampleInputName5" id="not_exampleInputName5" placeholder="组织机构"></select>
			</div>
			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">营销团队</label>
				<select type="text" class="form-control" name="not_exampleInputName6" id="not_exampleInputName6" placeholder="营销团队"></select>
			</div>
			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">投保人</label>
				<input type="text" class="form-control" name="not_exampleInputName7" id="not_exampleInputName7" placeholder="投保人">
			</div>
			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">被保人</label>
				<input type="text" class="form-control" name="not_exampleInputName8" id="not_exampleInputName8" placeholder="被保人">
			</div>
			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">员工工号</label>
				<input type="text" class="form-control" name="not_exampleInputName9" id="not_exampleInputName9" placeholder="员工工号">
			</div>

			<div class="form-group" >
				<label class="control-label" for="not_exampleInputName2">投保日期</label>
				<input type="date" class="form-control" name="not_exampleInputName20" id="not_exampleInputName20" placeholder="投保日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" name="not_exampleInputName21" id="not_exampleInputName21" placeholder="投保日期">
			</div>
			<div class="form-group" >
				<button type="button" class="btn btn-info" onclick="insComanpy_IPNOE.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>
</div>

<div id="insurance_toolbar_button_noe" class="btn-toolbar">
	<shiro:hasPermission name="noteOrEntryTemplate:download">
		<button type="button" class=" btn btn-info" onclick="downloadTemplate()">
			<span class="glyphicon glyphicon-pencil">导入模板</span>
		</button>
	</shiro:hasPermission>

	<shiro:hasPermission name="noteOrEntryTemplate:import">
		<button type="button" class=" btn btn-info" onclick="alertFile()">
			<span class="glyphicon glyphicon-pencil">导入</span>
		</button>
	</shiro:hasPermission>
</div>

<div style="width: 99%;overflow: auto;">
	<table id="not_insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->

<!-- 导入文件div -->
<div id="alertFileNote" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
	 aria-labelledby="zsadd_myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="zsadd_myModalLabel">上传文件</h4>
			</div>
			<div class="container">
				<form class="form-horizontal" id="fileForm" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-md-2 control-label">选择文件</label>
						<div class="col-md-3 ">
							<input type="file" name="file" id="fileName"/>
						</div>
					</div>
					<div class="modal-footer col-md-6">
						<!--用来清空表单数据-->
						<input type="reset" name="reset" style="display: none;"/>
						<button type="button" class="btn btn-default" onclick="closeImport()">关闭</button>
						<button id="zs_saveButton" type="button" onclick="upload()" class="btn btn-primary">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="loadingModal">
	<div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
		<div class="progress progress-striped active" style="margin-bottom: 0;">
			<div class="progress-bar" style="width: 100%;"></div>
		</div>
		<h5 style="color:black"><strong>正在缓冲文件...预计1-5秒，请稍等！</strong></h5>
	</div>
</div>

</body>
</html>