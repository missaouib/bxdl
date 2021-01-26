<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>保单下发</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
	<script src="${path}/static/js/jquery-form.js"></script>
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script type="text/javascript">
        function generateBatches() {
           var ALLDATE = $("#grant_already_table").bootstrapTable("getData")
            if (ALLDATE.length<1){
                alert("您没有选择任何数据")
                return;
            }
            var selectListIds = $("#selectListIds").val()
			commCloseTab("becomeInsurancePolicyGrant:list")
            createAddProcessTab("insurancePolicyGrantBecome:commit",selectListIds)

        }
		function changeDateFormat(cellval) {
			if (cellval != null) {
				var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
				var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
				var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
				return date.getFullYear() + "-" + month + "-" + currentDate;
			}
		}
        function delsome() {
			var ALLDATE = $('#grant_already_table').bootstrapTable('getData');
			var ALLlen = ALLDATE.length;
			var selectListIds='';
			var idList=[]
			for(var i =0;i<ALLlen;i++){
				var grant_forTdVal1 = ALLDATE[i].POLICY_ID//bao保单号
				idList.push(grant_forTdVal1)
			}
			var selected = $('#grant_already_table').bootstrapTable('getSelections');
			var len = selected.length;
			Array.prototype.contains = function(obj) {
				var i = this.length;
				while (i--) {
					if (this[i] === obj) {
						return i; // 返回的这个 i 就是元素的索引下标，
					}
				}
				return false;
			}
			for(var i =0;i<len;i++){
				var grant_forTdVal1 = selected[i].POLICY_ID//bao保单号
				idList.splice(idList.contains(grant_forTdVal1),1)
			}
			for (var c=0;c<idList.length;c++){
				selectListIds +=idList[c]+","
			}
			$("#selectListIds").val(selectListIds)
			insComanpyAlreadyBec.search()
			insComanpyBec.search()
        }
        function delAll() {
			$("#grant_already_table tbody").html("")
        }
        function grantAdds(){
			var selected = $('#grant_insComanpy-table').bootstrapTable('getSelections');

			var zcListLength = $("#grant_already_table").find("tr").length;
			if (selected.length==0){
				$.alert({
					title: '提示信息！',
					content: '至少选择一条记录！',
					type: 'red'
				});
			} else {
				var len = selected.length;
				var table = ""
				var selectListIds='';
				for(var i =0;i<len;i++){
					var grant_forTdVal1 = selected[i].POLICY_ID//bao保单号
					selectListIds+=grant_forTdVal1+","
				}
				$("#selectListIds").val()
				$("#selectListIds").val($("#selectListIds").val()+selectListIds)
				insComanpyAlreadyBec.search()
				insComanpyBec.search()
			}

        }
        //-------------------------------
		function grantAddAll() { //全部加入
			var ALLDATE = $('#grant_insComanpy-table').bootstrapTable('getData');
			if (ALLDATE == null || ALLDATE ==''){
				alert("您没有选择任何数据")
				return;
			}
			var zcListLength = $("#grant_insComanpy-table").find("tr").length;

			var len = $("#grant_already_table").find("tr").length;
			var table ="";
			var grant = {};
			var selectListIds='';
			for(var i=1 ; i< zcListLength ; i++) {
				var grant_forTdVal1 = $("#grant_insComanpy-table tr:eq(" + i + ")").children("#grant_insComanpy-table td:eq("+"3"+")").text();//投保单号
				selectListIds+=grant_forTdVal1+","


			}
			$("#selectListIds").val($("#selectListIds").val()+selectListIds)
			insComanpyAlreadyBec.search()
			insComanpyBec.search()
		}

		function cla() {
			$("#beGra_exampleInputName1").val('')
			$("#beGra_exampleInputName2").val('')
			$("#beGra_exampleInputName3").val('')
			$("#beGra_exampleInputName4").val('')
			$("#beGra_exampleInputName5").val('')
			$("#beGra_exampleInputName6").val('')
			$("#beGra_exampleInputName7").val('')
			$("#beGra_exampleInputName8").val('')
			$("#beGra_exampleInputName9").val('')
			$("#beGra_exampleInputName20").val('')
			$("#beGra_exampleInputName21").val('')
			$("#beGra_exampleInputName22").val('')
			$("#beGra_exampleInputName23").val('')
			$("#beGra_exampleInputName24").val('')
			$("#beGra_exampleInputName25").val('')
			insComanpyBec.search()
		}
		var insComanpyBec = {
		    seItem: null		//选中的条目
		};
		function allgrant(){//全部审批
		$("#grant_alert_div").modal('show')
		}
		function closeDlg() {//关闭
			$("#grant_alert_div").modal('hide');
		}
		function determine(){//审批确定
			var data=grant_params+'&'+grant_fileForm;///////
			 var grant_fileForm=$("#grant_fileForm").serialize()
			var grant_params=$("#grant_search").serialize();
			$.ajax({
				url:"insurance_policy/submit_grant",
				data:grant_fileForm,
				type:"post",
				dataType:"json",
				success:function(obj){

				},
				error:function (obj) {

				}
			})
		}
		$(function () {
			insComanpyBec.init();
			getSysDictVal("grant_auditresults","AUDITRESULTS")
			commSelectProductOrg("beGra_exampleInputName2")
			commSalesOrgsHX("beGra_exampleInputName5")
			$("#beGra_exampleInputName5").on(
					"change",function(){
						commSalesTeamsHX("beGra_exampleInputName6",$(this).find("option:selected").val());
					}
			)
			insComanpyAlreadyBec.init()
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
		function openDetailTab(permission){
				var selected = $('#grant_insComanpy-table').bootstrapTable('getSelections');
				if (selected.length==0){
					$.alert({
						title: '提示信息！',
						content: '至少选择一条记录！',
						type: 'red'
					});
				} else {
				var len = selected.length;
				var param = "";
					for(var i =0;i<len;i++){
						var policyId = selected[i].POLICY_ID
						param+=policyId+","
					}
			$("#Tab").val(param)
					$("#grant_alert_div").modal('show')
					/*selected.each(function (indexedDB, val) {
						alert(JSON.stringify(val.POLICY_ID));
					})*/
				}
				//alert(insComanpy.seItem.INSURANCE_COMPANY_ID);
				//createAddProcessTab('insurancePolicy:update',rowid);

		}
		function delAny() {
			$("#selectListIds").val(null)
			insComanpyAlreadyBec.search()
			insComanpyBec.search()
		}
		var insComanpyBec = function (){
			return{
				init:function (){
		            $('#grant_insComanpy-table').bootstrapTable({
		            	url: "insurance_policy/do_insurance_policy_become_grant",
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
		                toolbar:"#grant_toolbar_button",
		                showColumns : false, //显示隐藏列
		                uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: insComanpyBec.queryParams,//传递参数（*）
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
							formatter: function (value, row, index){
								var date = new Date(value);
								return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
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
						notSelectListIds:$("#selectListIds").val(),
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						principalDeputySign:"0",
						type:"2",
						insState:"1002",
						exampleInputName1:$("#beGra_exampleInputName1").val(),
						exampleInputName2:$("#beGra_exampleInputName2").val(),
						exampleInputName3:$("#beGra_exampleInputName3").val(),
						exampleInputName4:$("#beGra_exampleInputName4").val(),
						exampleInputName5:$("#beGra_exampleInputName5").val(),
						exampleInputName6:$("#beGra_exampleInputName6").val(),
						exampleInputName7:$("#beGra_exampleInputName7").val(),
						exampleInputName8:$("#beGra_exampleInputName8").val(),
						exampleInputName9:$("#beGra_exampleInputName9").val(),
						exampleInputName20:$("#beGra_exampleInputName20").val(),
						exampleInputName21:$("#beGra_exampleInputName21").val(),
						exampleInputName22:$("#beGra_exampleInputName22").val(),
						exampleInputName23:$("#beGra_exampleInputName23").val(),
						exampleInputName24:$("#beGra_exampleInputName24").val(),
						exampleInputName25:$("#beGra_exampleInputName25").val(),
					};
					return temp;
				},
				search:function(){
					$("#grant_insComanpy-table").bootstrapTable('destroy');
					insComanpyBec.init();
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
						insComanpyBec.seItem = selected[0];
		                return true;
		            }
		        },
			}
		}();
		var insComanpyAlreadyBec = function (){
			return{
				init:function (){
					$('#grant_already_table').bootstrapTable({
						url: "insurance_policy/do_insurance_policy_become_grant",
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
						pageSize : 100, //默认每页条数
						pageNumber : 1, //默认分页
						pageList : [100, 200, 400, 800 ],//分页数
						toolbar:"#grant_ready_toolbar",
						showColumns : false, //显示隐藏列
						uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
						queryParamsType:'',
						queryParams: insComanpyAlreadyBec.queryParams,//传递参数（*）
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
							formatter: function (value, row, index){
								var date = new Date(value);
								return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
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
						}],
						// bootstrap-table-treetreegrid.js 插件配置 -- end
						formatLoadingMessage : function() {
							return "请稍等，正在加载中...";
						},
						onLoadSuccess:function(data){
							if ($("#selectListIds").val() == null || $("#selectListIds").val()==''){
								delAll()
							}
						},
						formatNoMatches : function() {
							return '无符合条件的记录';
						}
					});
				},
				queryParams:function(params){
					var temp = {
						principalDeputySign:"0",
						type:"2",
						insState:"1002",
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						not:"not",
						selectListIds:$("#selectListIds").val(),

					};
					return temp;
				},
				search:function(){
					$("#grant_already_table").bootstrapTable('refresh');
				},
				checkSingleData:function () {
					var selected = $('#grant_already_table').bootstrapTable('getSelections');
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
						insComanpyAlreadyBec.seItem = selected[0];
						return true;
					}
				},
			}
		}();
	</script>

</head>
<body>
<input type="hidden" name="selectListIds" id="selectListIds" >
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline" id="grant_search" enctype="application/x-www-form-urlencoded">
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName1">保单号</label>
				<input type="text" class="form-control" id="beGra_exampleInputName1" name="beGra_exampleInputName1" placeholder="保单号">
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName2">保险公司</label>
				<select type="text" class="form-control" id="beGra_exampleInputName2" name="beGra_exampleInputName2" placeholder="保险公司"></select>
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName3">产品名称</label>
				<input type="text" class="form-control" id="beGra_exampleInputName3" name="beGra_exampleInputName3" placeholder="产品名称"></input>
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName4">产品代码</label>
				<input type="text" class="form-control" id="beGra_exampleInputName4" name="beGra_exampleInputName4" placeholder="产品代码">
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName5">组织机构</label>
				<select type="text" class="form-control" id="beGra_exampleInputName5" name="beGra_exampleInputName5" placeholder="组织机构"></select>
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName6">营销团队</label>
				<select type="text" class="form-control" id="beGra_exampleInputName6" name="beGra_exampleInputName6" placeholder="营销团队"></select>
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName7">投保人</label>
				<input type="text" class="form-control" id="beGra_exampleInputName7" name="beGra_exampleInputName7" placeholder="投保人">
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName8">被保人</label>
				<input type="text" class="form-control" id="beGra_exampleInputName8" name="beGra_exampleInputName8" placeholder="被保人">
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName9">员工工号</label>
				<input type="text" class="form-control" id="beGra_exampleInputName9" name="beGra_exampleInputName9" placeholder="员工工号">
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName20">投保日期</label>
				<input type="date" class="form-control" id="beGra_exampleInputName20" name="beGra_exampleInputName20" placeholder="投保日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="beGra_exampleInputName21" name="beGra_exampleInputName21" placeholder="投保日期">
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName22">生效日期</label>
				<input type="date" class="form-control" id="beGra_exampleInputName22" name="beGra_exampleInputName22" placeholder="生效日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="beGra_exampleInputName23" name="beGra_exampleInputName23" placeholder="生效日期">
			</div>
			<div class="form-group" >
				<label class="control-label" for="beGra_exampleInputName24">承保日期</label>
				<input type="date" class="form-control" id="beGra_exampleInputName24" name="beGra_exampleInputName24" placeholder="承保日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="beGra_exampleInputName25" name="beGra_exampleInputName25" placeholder="承保日期">
			</div>
			<div class="form-group" >
				<button type="button" class="btn btn-info" onclick="insComanpyBec.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>
</div>
<div id="grant_toolbar_button" class="btn-toolbar">
    <shiro:hasPermission name="insurancePolicyGrant:commit">
        <button type="button" class=" btn btn-info" onclick="generateBatches()">
            <span class="glyphicon glyphicon-pencil" >生成批次</span>
        </button>
    </shiro:hasPermission>

</div>
<div>
	待选择列表，点击加入列表进行选择
	<a href="#" onclick="grantAddAll()" style="color: darkblue" id="grant_add_all">全部加入</a>
	<a href="#" onclick="grantAdds()" style="color: darkblue" id="grant_adds">批量加入</a>
</div>
<div style="width: 99%;overflow: auto;">
	<table id="grant_insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>

<div>
    已选择列表
    <a href="#" onclick="delAny()" style="color: darkblue" id="grant_del_all">全部删除</a>
    <a href="#" onclick="delsome()" style="color: darkblue" id="grant_dels">批量删除</a>
</div>
<div style="width: 99%;overflow: auto;">
<form action="/insurance_policy/to_generate_batches" enctype="application/x-www-form-urlencoded" id="to_generate_batches_from">
    <table id="grant_already_table" class="table table-hover table-striped table-condensed table-bordered">
        <thead>
        <tr>
            <td>选择</td>
            <td>序号</td>
            <td>保单号</td>
            <td>保险公司</td>
            <td>产品代码</td>
            <td>产品名称</td>
            <td>保费</td>
            <td>组织机构</td>
            <td>营销团队</td>
            <td>投保人</td>
            <td>被保人</td>
            <td>员工工号</td>
            <td>员工姓名</td>
            <td>投保日期</td>
        </tr>
        </thead>
       <tbody id="grant_already_thead">

       </tbody>
    </table>
</form>
</div>
<!--toolbar  -->

<div id="grant_ready_toolbar" class="btn-toolbar" style="overflow:scroll;background: slategray;width: 1800px;">


	<shiro:hasPermission name="insurancePolicygrantSome:update">
		<button type="button" class=" btn btn-info" onclick="allgrant()">
			<span class="glyphicon glyphicon-pencil" >全部审核</span>
		</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="insurancePolicygrantOne:update">
		<button type="button" class=" btn btn-info" onclick="openDetailTab('insurancePolicygrantOne:update')">
			<span class="glyphicon glyphicon-pencil" >选中审核</span>
		</button>
	</shiro:hasPermission>


</div>

<div id="grant_alert_div" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
	 aria-labelledby="grant_zsadd_myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="grant_zsadd_myModalLabel">审核</h4>
			</div>
			<div class="container">
				<form class="form-horizontal" id="grant_fileForm"  enctype="application/x-www-form-urlencoded">
					<input type="hidden" name="Tab" id="Tab">
					<div class="form-group">
						<label class="col-md-2 control-label">审核结果</label>
							<select class="form-control" style=" width: 150px" id="grant_auditresults" name="grant_auditresults">
							</select>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">审核意见</label>
						<textarea class="form-control" style=" width: 400px; height: 200px" id="grant_auditopinions" name="grant_auditopinions" type="text"></textarea>
					</div>
					<div class="modal-footer col-md-6">
						<!--用来清空表单数据-->
						<input type="reset" name="reset" style="display: none;"/>
						<button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
						<button id="grant_zs_saveButton" type="button" onclick="determine()" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>