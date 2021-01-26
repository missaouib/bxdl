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
	<script src="${path}/static/js/jquery-form.js"></script>
	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/tableExport.js"></script>
	<script src="${path}/js/jquery-form.js"></script>
	<script type="text/javascript">

		function cla() {
			$("#exampleInputName1").val('');
			$("#exampleInputName2").val('');
			$("#exampleInputName3").val('');
			$("#exampleInputName4").val('');
			$("#exampleInputName5").val('');
			$("#exampleInputName6").val('');
			$("#exampleInputName7").val('');
			$("#exampleInputName8").val('');
			$("#exampleInputName9").val('');
			$("#exampleInputName10").val('');
			$("#exampleInputName20").val('');
			$("#exampleInputName21").val('');
			insComanpy.search()
		}
		var insComanpy = {
			seItem: null		//选中的条目

		};

		$(function () {

			insComanpy.init();
			getSysDictVal("exampleInputName10","STATE");
			commSelectProductOrg("exampleInputName2");
			commSalesOrgsHX("exampleInputName5");
			$("#exampleInputName5").on(
					"change",function(){
						commSalesTeamsHX("exampleInputName6",$(this).find("option:selected").val());
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

							var data={"ids":ids};
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
                var rowid = id;
				commCloseTab('insurancePolicy:list')
				createAddProcessTab('insurancePolicy:update',rowid)
            } else{
			if(insComanpy.checkSingleData()){
				var rowid = insComanpy.seItem.POLICY_ID;
				commCloseTab('insurancePolicy:list')
				createAddProcessTab('insurancePolicy:update',rowid)
			}
            }
		}
		function selectOne(id) {
			commCloseTab('insurancePolicy:list')
			createAddProcessTab('selectByPid:List',id);
		}

		function del(id) {
			var truthBeTold = window.confirm("确定要删除选中的投保单吗？")
			if (truthBeTold ) {
				var data={}
				if (id != ''){
					data={"ids":id}
				} else{
					var a= $('#insComanpy-table').bootstrapTable('getSelections');
					if (a.length<1){
						alert("至少选择一条");
						return;
					}
					var ids ='';
					for (var i=0;i<a.length;i++){
						ids = ids+a[i].POLICY_ID+','
					}
					ids= ids.substring(0,ids.length - 1);
					data={"ids":ids};
				}
				$.ajax({
					url:"insurance_policy/deleteInsByIds",
					data:data,
					type:"POST",
					dataType:"JSON",
					success:function (obj) {
						if (obj.code=="200"){
							alert("删除成功");
							insComanpy.search()
						} else{
							alert("删除失败")
						}

					}

				})
			}

		}
		function operateFormatter(value, row, index) {
			var id = row.POLICY_ID;
		    var state =	row.STATE
			if (state == '1' || state == '3' || state == '0000'){
			return [
				'<shiro:hasPermission name="selectByPid:List">',
				'<button type="button" class="btn btn-info" onclick="selectOne(\'' + id + '\')">',
				'<span class="glyphicon glyphicon-pencil" >查看</span>',
				'</button>',
				'</shiro:hasPermission>',
				'<shiro:hasPermission name="insurancePolicy:update">',
				'<button type="button" class="btn btn-info" onclick="openDetailTab(\'' + id + '\')">',
				'<span class="glyphicon glyphicon-pencil" >修改</span>',
				'</button>',
				'<button type="button" class=" btn btn-info" onclick="del(\''+id+'\')">',
				'<span class="glyphicon glyphicon-pencil" >删除</span>',
				'</button>',
				'</shiro:hasPermission>',
			].join('');
			}else{
				return [
					'<shiro:hasPermission name="selectByPid:List">',
					'<button type="button" class="btn btn-info" onclick="selectOne(\'' + id + '\')">',
					'<span class="glyphicon glyphicon-pencil" >查看</span>',
					'</button>',
					'</shiro:hasPermission>',
				].join('');
			}
		}


        function DoOnMsoNumberFormat(cell, row, col) {
            var result = "";
            if (row > 0 && col == 0)
                result = "\\@";
            return result;
        }
		var insComanpy = function (){
			return{
				init:function (){
					$('#insComanpy-table').bootstrapTable({
						url: "insurance_policy/do_insurance_policy",
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
						toolbar:"#toolbarPolList",
						showColumns : false, //显示隐藏列
						uniqueId: "INSURED_PERSON_ID", //每一行的唯一标识，一般为主键列
						queryParamsType:'',
						queryParams: insComanpy.queryParams,//传递参数（*）
						onLoadSuccess:function(data){
							$("#total_premium").html(data.data == null?0:data.data)
						},
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
							title : "作业节点",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter: function (value, row, index) {
								if (value==1){
									value = "待审核"
								}  else if (value==2){
									value = "审核成功"
								} else if (value==3){
									value = "审核失败"
								} else if (value==4){
									value = "待移交"
								} else if (value==5){
									value = "已移交"
								} else if (value==6){
									value = "待发放"
								} else if (value==7){
									value = "已发放"
								} else if (value==8){
									value = "已回复"
								} else if (value==9){
									value = "已回销"
								} else if (value==10){
									value = "异常回销"
								}else if (value==11){
									value = "已录入"
								}else if (value=="0000"){
									value = "暂存"
								}
								return value;
							}
						}, {
							title: "操作",
							align: "center",
							valign: "middle",
							sortable: "true",
							formatter: operateFormatter
						}],

                     /*   showExport: true,  //是否显示导出按钮
						initExport:true,
						exportButton: $('#excelAll'),
                     	exportDataType:"all",
						buttonsAlign:"left",  //按钮位置
						exportTypes:['excel'],
                        exportOptions:{
                            ignoreColumn: [0,1],  //忽略某一列的索引
                            fileName: '总台帐报表',  //文件名称设置
                            worksheetName: 'sheet1',  //表格工作区名称
                            tableName: '总台帐报表',
                            excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],
                            onMsoNumberFormat: DoOnMsoNumberFormat
                        },*/
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
						state:"1,2,3,4,5,6,7,8,9,10,11,0000",
						exampleInputName1:$("#exampleInputName1").val(),
						exampleInputName2:$("#exampleInputName2").val(),
						exampleInputName3:$("#exampleInputName3").val(),
						exampleInputName4:$("#exampleInputName4").val(),
						exampleInputName5:$("#exampleInputName5").val(),
						exampleInputName6:$("#exampleInputName6").val(),
						exampleInputName7:$("#exampleInputName7").val(),
						exampleInputName8:$("#exampleInputName8").val(),
						exampleInputName9:$("#exampleInputName9").val(),
						exampleInputName10:$("#exampleInputName10").val(),
						exampleInputName20:$("#exampleInputName20").val(),
						exampleInputName21:$("#exampleInputName21").val(),
					};
					return temp;
				},
				exportParams:function(){

					var temp = {

						principalDeputySign:"0",
						type:"1",
						state:"1,2,3,4,5,6,7,8,9,10,11,0000",
						exampleInputName1:$("#exampleInputName1").val(),
						exampleInputName2:$("#exampleInputName2").val(),
						exampleInputName3:$("#exampleInputName3").val(),
						exampleInputName4:$("#exampleInputName4").val(),
						exampleInputName5:$("#exampleInputName5").val(),
						exampleInputName6:$("#exampleInputName6").val(),
						exampleInputName7:$("#exampleInputName7").val(),
						exampleInputName8:$("#exampleInputName8").val(),
						exampleInputName9:$("#exampleInputName9").val(),
						exampleInputName10:$("#exampleInputName10").val(),
						exampleInputName20:$("#exampleInputName20").val(),
						exampleInputName21:$("#exampleInputName21").val(),
					};
					return temp;
				},
				exportAll:function() {
					$("#searchForm").attr("action", "insurance_policy/export?type=1");
					$("#searchForm").attr("method", "POST");
					$("#searchForm").submit();
			},
				exportSelect:function() {
				var a = $('#insComanpy-table').bootstrapTable('getSelections');
				if (a.length < 1) {
					alert("至少选择一条");
					return;
				}
				var ids = [];
				for (var i = 0; i < a.length; i++) {
					ids.push(a[i].POLICY_ID);
				}
				location.href="insurance_policy/export??type=1&ids="+JSON.stringify(ids);
			},
				search:function(){
					$("#insComanpy-table").bootstrapTable('destroy');
					insComanpy.init();
				},
				checkSingleData:function () {
					var selected = $('#insComanpy-table').bootstrapTable('getSelections');
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
		function addPolicy(){
			commCloseTab('insurancePolicy:list')
			createAddProcessTab('insurancePolicy:add','')
		}
		 function closeDlg() {
            $("#alertFile").modal('hide');
        }
		function alertFile() {
            $("#alertFile").modal('show');
        }
         function downloadTemplate() {
            window.open("${path}/upload/files/导入投保单模板.xls");
        }

          function upload() {
            var multipart = $("#fileName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeDlg();
            $("#loadingModal").modal('show');
            //$('#loadingModal').modal('hide');
            $("#fileForm").ajaxSubmit({
                type: 'POST',
                url: 'insurance_policy/importInsurancePolicy',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#insComanpy-table").bootstrapTable('refresh');
                       // createAddProcessTab('to_insurance_policy_list','')
                        alert("文件导入成功")
                    } else if(data.code == "400"){
                        alert("文件导入失败请重新上传")
                    }else if (data.code == "0000"){
                        alert("文件不能为空")
                    }else if (data.code == "0001"){
                        alert("导入失败：原因-"+data.msg)
                    }else if(data.code == "500"){
                       $.alert({
                           title: '导入失败！',
                           content:data.error,
                           type: 'red'

                       })
                    }
                },
                error: function (data) {
                    $('#loadingModal').modal('hide');
                    alert("系统异常")
                }
            })
        }

	</script>

</head>
<body>
<!--列表 -->
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline" id="searchForm">
			<input type="hidden" value="1" id="type" name="type">
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">投保单号</label>
				<input type="text" class="form-control" id="exampleInputName1" name="exampleInputName1" placeholder="投保单号">
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">保险公司</label>
				<select type="text" class="form-control" id="exampleInputName2" name="exampleInputName2" placeholder="保险公司"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">产品名称</label>
				<input type="text" class="form-control" id="exampleInputName3" name="exampleInputName3" placeholder="产品名称"></input>
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">产品代码</label>
				<input type="text" class="form-control" id="exampleInputName4" name="exampleInputName4" placeholder="产品代码">
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">组织机构</label>
				<select type="text" class="form-control" id="exampleInputName5" name="exampleInputName5" placeholder="组织机构"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">营销团队</label>
				<select type="text" class="form-control" id="exampleInputName6" name="exampleInputName6" placeholder="营销团队"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">投保人</label>
				<input type="text" class="form-control" id="exampleInputName7" name="exampleInputName7" placeholder="投保人">
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">被保人</label>
				<input type="text" class="form-control" id="exampleInputName8" name="exampleInputName8" placeholder="被保人">
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">员工工号</label>
				<input type="text" class="form-control" id="exampleInputName9" name="exampleInputName9" placeholder="员工工号">
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">状态</label>
				<select class="form-control" id="exampleInputName10" name="exampleInputName10" placeholder="状态"></select>
			</div>
			<div class="form-group">
				<label class="control-label" for="exampleInputName2">投保日期</label>
				<input type="date" class="form-control" id="exampleInputName20" name="exampleInputName20" placeholder="投保日期">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="exampleInputName21" name="exampleInputName21" placeholder="投保日期">
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-info" onclick="insComanpy.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>

</div>

<div style="width: 99%;overflow: auto;">
	<table id="insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>


<!-- 导入文件div -->
<div id="alertFile" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
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
                        <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
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

<!--toolbar  -->
<div id="toolbarPolList" class="btn-toolbar" style="overflow:scroll;">
	<shiro:hasPermission name="insurancePolicy:add">
		<button class="btn btn-info" type="button" onclick="addPolicy()">
			<span class="glyphicon glyphicon-plus" >录入投保单</span>
		</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="insurancePolicy:add">
		<button type="button" class=" btn btn-primary" onclick="downloadTemplate()">
			<span class="glyphicon glyphicon-book">导入模板</span>
		</button>
		<button class="btn btn-success" type="button" onclick="alertFile()">
			<span class="glyphicon glyphicon-import" >导入投保单</span>
		</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="insurancePolicy:update">
	<%--	<button type="button" class=" btn btn-info" onclick="openDetailTab('')">
			<span class="glyphicon glyphicon-pencil" >修改</span>
		</button>
		<button type="button" class=" btn btn-info" onclick="del('')">
			<span class="glyphicon glyphicon-pencil" >删除</span>
		</button>--%>

		<button type="button" class="export-excel btn btn-info" onclick="insComanpy.exportAll()">
			<span class="glyphicon glyphicon-export" >导出全部</span>
		</button>
		<button type="button" class=" btn btn-info" onclick="insComanpy.exportSelect()">
			<span class="glyphicon glyphicon-export" >导出选中</span>
		</button>
	</shiro:hasPermission>


	<shiro:hasPermission name="insurancePolicy:delete">
		<button class=" btn btn-danger" type="button" onclick="delDept('insurancePolicy:delete')">
			<span class="glyphicon glyphicon-remove" >删除</span>
		</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="insurancePolicy:export">
		<button onclick="" type="button" class="btn btn-success">
			<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出</span>
		</button>
	</shiro:hasPermission>
		<span style="color: black">&nbsp;&nbsp;"总保费:</span><span id="total_premium" STYLE="color: crimson"></span><span style="color: black">万元</span>

</div>
</body>
</html>