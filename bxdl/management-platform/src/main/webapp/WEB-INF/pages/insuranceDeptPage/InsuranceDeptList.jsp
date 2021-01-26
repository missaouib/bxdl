<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>保险机构管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/tableExport.js"></script>
	<script src="${path}/js/jquery-form.js"></script>
	<script type="text/javascript">
		var insComanpydept = {
		    seItem: null		//选中的条目
		};

		function operateFormatter(value, row, index) {
			var id = row.INSURANCE_COMPANY_ID;
			return [
				'<shiro:hasPermission name="insCompany:Select">',
				'<button type="button" class="btn btn-info" onclick="openSelectDetailTabByButton(\'' + id + '\')">',
				'<span class="glyphicon glyphicon-pencil" >查看</span>',
				'</button>',
				'</shiro:hasPermission>',
				'<shiro:hasPermission name="insCompany:update">',
				'<button type="button" class="btn btn-info" onclick="openDetailTabByButton(\'' + id + '\')">',
				'<span class="glyphicon glyphicon-pencil" >编辑</span>',
				'</button>',
				'</shiro:hasPermission>',
			].join('');
		}
		$(function () {
			insComanpydept.init();
			getSysDictVal("businessType","WEALTH_LONGEVITY");

		});
		function delDept(row) {
			var cha = $('#insDept-table').bootstrapTable('getSelections');
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
                                    $("#insDept-table").bootstrapTable('refresh');
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
		function openDetailTabByButton(id) {
			commCloseTab('insCompanyDept:list');
			createAddProcessTab('insCompany:update',id);
		}

		function openSelectDetailTabByButton(id) {
			createAddProcessTab('insCompany:Select',id);
		}
		function openDetailTab(permission){
			alert(111);
			if(insComanpydept.checkSingleData()){
				var rowid = insComanpydept.seItem.INSURANCE_COMPANY_ID;
				//alert(insComanpy.seItem.INSURANCE_COMPANY_ID);
				createAddProcessTab('insCompany:update',rowid);
			}
		}

		var insComanpydept = function (){
			return{
				init:function (){
		            $('#insDept-table').bootstrapTable({
		            	url: "insurance_dept/do_insurance_dept",
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
		                toolbar:"#insdept",
		                showColumns : false, //显示隐藏列
		                uniqueId: "INSURANCE_COMPANY_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: insComanpydept.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
							field : "INSURANCE_COMPANY_CODE",
							visible:false
						},{
		                    field : "INSURANCE_COMPANY_ID",
							visible:false
		                },{
							field: 'SerialNumber',
							title: '序号',
							formatter: function (value, row, index) {
								return index+1;
							}
						},{
							field : "INSURANCE_COMPANY_CODE",
							title : "保险公司代码",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
		                    field : "INSURANCE_COMPANY_NAME",
		                    title : "保险公司名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
							field : "INSURANCE_COMPANY_NAME_LESS",
							title : "保险公司简称",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
		                    field : "BUSINESS_TYPE",
		                    title : "保险公司财寿标志",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
							formatter:function (value) {
								if (value == 0){
									return"寿险";
								} else if (value == 1){
									return"财险";
								}
									else{
									return"-";
								}
							}
		                },{
							field : "CREATED_TIME",
							title : "创建时间",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter:function (value) {
								var cellval =value+"";
								if (cellval != null) {
									var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
									var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
									var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
									var hours = (date.getHours()+1<11)?'0'+date.getHours():date.getHours();
									var minutes = (date.getMinutes()+1<11)?'0'+date.getMinutes():date.getMinutes();
									var seconds = (date.getSeconds()+1<11)?'0'+date.getSeconds():date.getSeconds();
									return date.getFullYear() + "-" + month + "-" + currentDate+' '+hours+':'+minutes+':'+seconds;
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
				search:function(){
					$("#insDept-table").bootstrapTable('destroy');
					insComanpydept.init();
				},
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						orgLevel: "01", //组织机构级别
						insuranceCompanyCode:$("#insuranceCompanyCode").val(),
						insuranceCompanyName:$("#insuranceCompanyName").val(),
						insuranceCompanyNameLess:$("#insuranceCompanyNameLess").val(),
						businessType:$("#businessType").val(),
						createdTimeStat:$("#createdTimeStat").val(),
						createdTimeEnd:$("#createdTimeEnd").val(),
					};
					return temp;
				},

		        checkSingleData:function () {
		            var selected = $('#insDept-table').bootstrapTable('getSelections');
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
                        insComanpydept.seItem = selected[0];
		                return true;
		            }
		        },
			}
		}();
		function cla() {
			$("#insuranceCompanyCode").val(''),
					$("#insuranceCompanyName").val(''),
					$("#insuranceCompanyNameLess").val(''),
					$("#businessType").val(''),
					$("#createdTimeStat").val(''),
					$("#createdTimeEnd").val(''),
					insComanpydept.search()
		}
		function add() {
			commCloseTab('insCompanyDept:list');
			createAddProcessTab('insCompany:add','')
		}

		  function downloadTemplate() {
            window.open("${path}/upload/files/保险公司模板.xls");
        }

         function closeDlg() {
            $("#alertFile").modal('hide');
        }
		function alertFile() {
            $("#alertFile").modal('show');
        }

         function upload() {
            var multipart = $("#fileName").val();
            if (multipart == "" || multipart == null) {
                alert("请先选择文件!");
                return;
            }
            closeDlg();
            $("#loadingModal").modal('show');
            $("#fileForm").ajaxSubmit({
                type: 'POST',
                url: 'insurance_dept/importInsuranceDept',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#insDept-table").bootstrapTable('refresh');
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
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline" id="searchForm">
			<div class="form-group">
				<label  class="control-label" for="insuranceCompanyCode">保险公司代码</label>
				<input type="text" class="form-control" id="insuranceCompanyCode" name="insuranceCompanyCode" placeholder="保险公司代码">
			</div>
			<div class="form-group">
				<label  class="control-label" for="insuranceCompanyName">保险公司名称</label>
				<input type="text" class="form-control" id="insuranceCompanyName" name="insuranceCompanyName" placeholder="保险公司名称"></input>
			</div>
			<div class="form-group">
				<label  class="control-label" for="insuranceCompanyNameLess">保险公司简称</label>
				<input type="text" class="form-control" id="insuranceCompanyNameLess" name="insuranceCompanyNameLess" placeholder="保险公司简称"></input>
			</div>
			<div class="form-group">
				<label  class="control-label" for="businessType">财寿险标志</label>
				<select type="text" class="form-control" id="businessType" name="businessType"></select>
			</div>
			<div class="form-group">
				<label  class="control-label" for="createdTimeStat">创建时间</label>
				<input type="date" class="form-control" id="createdTimeStat" name="createdTimeStat" placeholder="创建时间">
				<label class="control-label control-label-time">至</label>
				<input type="date" class="form-control" id="createdTimeEnd" name="createdTimeEnd" placeholder="创建时间">
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-info" onclick="insComanpydept.search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="cla()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
				</button>
			</div>
		</form>
	</div>
</div>
<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="insDept-table" class="table table-hover table-striped table-condensed table-bordered"></table>
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
<%--缓冲div--%>
<div class="modal fade" id="loadingModal">
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
        <div class="progress progress-striped active" style="margin-bottom: 0;">
            <div class="progress-bar" style="width: 100%;"></div>
        </div>
        <h5 style="color:black"><strong>正在缓冲文件...预计1-5秒，请稍等！</strong></h5>
    </div>
</div>


<!--toolbar  -->
<div id="insdept" class="btn-toolbar">
	<shiro:hasPermission name="insCompany:add">
    	<button class="btn btn-info" type="button" onclick="add()">
	    		<span class="glyphicon glyphicon-plus" >新增保险公司</span>
    	</button>
		<button type="button" class=" btn btn-primary" onclick="downloadTemplate()">
			<span class="glyphicon glyphicon-book">导入模板</span>
		</button>
		<button class="btn btn-success" type="button" onclick="alertFile()">
			<span class="glyphicon glyphicon-import" >导入保险公司</span>
		</button>
    </shiro:hasPermission>
    <%--<shiro:hasPermission name="insCompany:update">
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('insCompany:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>--%>
    <shiro:hasPermission name="insCompany:delete">
    	<button class=" btn btn-danger" type="button" onclick="delDept('insCompany:delete')">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="insCompany:export">
	    <button onclick="" type="button" class="btn btn-success">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出</span>
	    </button>
 	</shiro:hasPermission>
</div>
</body>
</html>