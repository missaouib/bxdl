<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>投保单审核</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		function search(){
			alert("搜索")
		}
		function selectOne(id) {
			createAddProcessTab('selectByPid:List',id);
		}
		function cla() {
				$("#ex_exampleInputName1").val('')
				$("#ex_exampleInputName2").val('')
				$("#ex_exampleInputName3").val('')
				$("#ex_exampleInputName4").val('')
				$("#ex_exampleInputName5").val('')
				$("#ex_exampleInputName6").val('')
				$("#ex_exampleInputName7").val('')
				$("#ex_exampleInputName8").val('')
				$("#ex_exampleInputName9").val('')
				$("#ex_exampleInputName20").val('')
				$("#ex_exampleInputName21").val('')
			insComanpy_IPE.search()

		}
		var insComanpy_IPE = {
		    seItem: null		//选中的条目
		};
		function allExamine(){//全部审批
			var allTableData = $('#examine_insComanpy-table').bootstrapTable('getData');
			if(allTableData == null || allTableData ==''){
				alert("您没有选择任何数据")
				return
			}
		$("#examine_alert_div_ipe").modal('show')
		}
		function closeDlg() {//关闭
			$("#examine_alert_div_ipe").modal('hide');
			$("#examine_auditresults").val('')
			$("#examine_auditopinions").val('')
			$("#Tab").val('')
		}
		function determine(){//审批确定

			var examine_auditresults_q = $("#examine_auditresults_q").val()
			if(examine_auditresults_q==''){
				$.alert({
					title: '提示信息！',
					content: '请选择审核结果！',
					type: 'red'
				});
				return;
			}
			var varexamine_auditopinions_q = $("#examine_auditopinions_q").val()
			$("#examine_auditresults").val(examine_auditresults_q)
			$("#examine_auditopinions").val(varexamine_auditopinions_q)
		//	examine_fileForm
			 var examine_fileForm=$("#examine_search").serialize()
			$.ajax({
				url:"insurance_policy/submit_examine",
				data:examine_fileForm,
				type:"post",
				dataType:"json",
				success:function(obj){
					$("#examine_auditresults").val('')
					$("#examine_auditopinions").val('')
					$("#Tab").val('')
					if (obj.code=="200"){
						$("#examine_alert_div_ipe").modal('hide')
						alert("审核提交成功")
						insComanpy_IPE.search()
					} else {
						alert("审核提交失败")
					}
				},
				error: function (obj) {
					alert("系统异常")
				}
			})
		}
		$(function () {
			insComanpy_IPE.init();
			getSysDictVal("examine_auditresults_q","AUDITRESULTS")
			commSelectProductOrg("ex_exampleInputName2")
			commSalesOrgsHX("ex_exampleInputName5")
			$("#ex_exampleInputName5").on(
					"change",function(){
						commSalesTeamsHX("ex_exampleInputName6",$(this).find("option:selected").val());
					}
			)
		});
		function delDept(row) {
			var cha = $('#insComanpy-table').bootstrapTable('getSelections');
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
                                    $("#insComanpy-table").bootstrapTable('refresh');
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
		function openDetailTab(id){
			if (id != ''){
				$("#Tab").val(id)
			} else{
				var selected = $('#examine_insComanpy-table').bootstrapTable('getSelections');
				if (selected.length==0){
					$.alert({
						title: '提示信息！',
						content: '至少选择一条记录！',
						type: 'red'
					});
					return;
				} else {
				var len = selected.length;
				var param = "";
					for(var i =0;i<len;i++){
						var policyId = selected[i].POLICY_ID
						param+=policyId+","
					}
			$("#Tab").val(param)
				}

					/*selected.each(function (indexedDB, val) {
						alert(JSON.stringify(val.POLICY_ID));
					})*/
				}
			$("#examine_alert_div_ipe").modal('show')
				//alert(insComanpy.seItem.INSURANCE_COMPANY_ID);
				//createAddProcessTab('insurancePolicy:update',rowid);

		}
		function operateFormatter(value, row, index) {
			var id = row.POLICY_ID;
			return [
					'<shiro:hasPermission name="insurancePolicyExamineOne:update">',
					'<button type="button" class=" btn btn-info" onclick="openDetailTab(\''+id+'\')">',
					'<span class="glyphicon glyphicon-pencil" >审核</span>',
					'</button>',
					'</shiro:hasPermission>',
				'<shiro:hasPermission name="selectByPid:List">',
				'<button type="button" class="btn btn-info" onclick="selectOne(\'' + id + '\')">',
				'<span class="glyphicon glyphicon-pencil" >查看</span>',
				'</button>',
				'</shiro:hasPermission>',
			].join('');
		}

		var insComanpy_IPE = function (){
			return{
				init:function (){
		            $('#examine_insComanpy-table').bootstrapTable({
		            	url: "insurance_policy/do_insurance_policy_examine",
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
		                toolbar:"#examine_toolbar_exa",
		                showColumns : false, //显示隐藏列
		                uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: insComanpy_IPE.queryParams,//传递参数（*）
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
								var cellval =value+""
								if (cellval != null) {
									var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
									var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
									var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
									return date.getFullYear() + "-" + month + "-" + currentDate;
								}
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
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						principalDeputySign:"0",
						type:"1",
						state:1,
						exampleInputName1:$("#ex_exampleInputName1").val(),
						exampleInputName2:$("#ex_exampleInputName2").val(),
						exampleInputName3:$("#ex_exampleInputName3").val(),
						exampleInputName4:$("#ex_exampleInputName4").val(),
						exampleInputName5:$("#ex_exampleInputName5").val(),
						exampleInputName6:$("#ex_exampleInputName6").val(),
						exampleInputName7:$("#ex_exampleInputName7").val(),
						exampleInputName8:$("#ex_exampleInputName8").val(),
						exampleInputName9:$("#ex_exampleInputName9").val(),
						exampleInputName20:$("#ex_exampleInputName20").val(),
						exampleInputName21:$("#ex_exampleInputName21").val(),
					};
					return temp;
				},
				search:function(){
					$("#examine_insComanpy-table").bootstrapTable('destroy');
					insComanpy_IPE.init();
				},
		        checkSingleData:function () {
		            var selected = $('#examine_insComanpy-table').bootstrapTable('getSelections');
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
						insComanpy_IPE.seItem = selected[0];
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
		<form class="form-inline hz-form-inline" id="examine_search" enctype="application/x-www-form-urlencoded">
			<input type="hidden" name="Tab" id="Tab">
			<input value="1" type="hidden" name="state" id="state">
			<input type="hidden" name="examine_auditresults" id="examine_auditresults">
			<input type="hidden" name="examine_auditopinions" id="examine_auditopinions">
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName2">投保单号</label>
				<input type="text" class="form-control" id="ex_exampleInputName1" name="ex_exampleInputName1" placeholder="投保单号">
			</div>

			<div class="form-group">
				<label class="control-label">保险公司</label>
				<select type="text" class="form-control" id="ex_exampleInputName2" name="ex_exampleInputName2" placeholder="保险公司"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName3">产品名称</label>
				<input type="text" class="form-control" id="ex_exampleInputName3" name="ex_exampleInputName3" placeholder="产品名称">
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName4">产品代码</label>
				<input type="text" class="form-control" id="ex_exampleInputName4" name="ex_exampleInputName4" placeholder="产品代码">
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName5">组织机构</label>
				<select type="text" class="form-control" id="ex_exampleInputName5" name="ex_exampleInputName5" placeholder="组织机构"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName6">营销团队</label>
				<select type="text" class="form-control" id="ex_exampleInputName6" name="ex_exampleInputName6" placeholder="营销团队"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName7">投保人</label>
				<input type="text" class="form-control" id="ex_exampleInputName7" name="ex_exampleInputName7" placeholder="投保人">
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName8">被保人</label>
				<input type="text" class="form-control" id="ex_exampleInputName8" name="ex_exampleInputName8" placeholder="被保人">
			</div>
			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName9">员工工号</label>
				<input type="text" class="form-control" id="ex_exampleInputName9" name="ex_exampleInputName9" placeholder="员工工号">
			</div>

			<div class="form-group">
				<label class="control-label" for="ex_exampleInputName20">投保日期</label>
				<input type="date" class="form-control" id="ex_exampleInputName20" name="ex_exampleInputName20" placeholder="投保日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="ex_exampleInputName21" name="ex_exampleInputName21" placeholder="投保日期">
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-info" onclick="insComanpy_IPE.search()">
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
	<table id="examine_insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
<div id="examine_toolbar_exa" class="btn-toolbar">


	<shiro:hasPermission name="insurancePolicyExamineSome:update">
		<button type="button" class=" btn btn-info" onclick="allExamine()">
			<span class="glyphicon glyphicon-pencil" >全部审核</span>
		</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="insurancePolicyExamineOne:update">
		<button type="button" class=" btn btn-info" onclick="openDetailTab('')">
			<span class="glyphicon glyphicon-pencil" >选中审核</span>
		</button>
	</shiro:hasPermission>


</div>

<div id="examine_alert_div_ipe" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
	 aria-labelledby="examine_zsadd_myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="examine_zsadd_myModalLabel">审核</h4>
			</div>
			<div class="container">
				<form class="form-horizontal" id="examine_fileForm"  enctype="application/x-www-form-urlencoded">

					<div class="form-group">
						<label class="col-md-2 control-label">审核结果</label>
							<select class="form-control" style=" width: 150px" id="examine_auditresults_q" name="examine_auditresults">
							</select>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">审核意见</label>
						<textarea class="form-control" style=" width: 400px; height: 200px" id="examine_auditopinions_q" name="examine_auditopinions" type="text"></textarea>
					</div>
					<div class="modal-footer col-md-6">
						<!--用来清空表单数据-->
						<input type="reset" name="reset" style="display: none;"/>
						<button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
						<button id="examine_zs_saveButton" type="button" onclick="determine()" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>