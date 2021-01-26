<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>总监津贴管理</title>
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
			if(insComanpy.checkSingleData()){
				var rowid = insComanpy.seItem.INSURANCE_COMPANY_CODE;
				//alert(insComanpy.seItem.INSURANCE_COMPANY_ID);
				createAddProcessTab('insCompany:update',rowid);
			}
		}		
		
		var insComanpy = function (){
			return{
				init:function (){
		            $('#insComanpy-table').bootstrapTable({
		            	url: "allowance_manager/do_director_allowance",
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
		                uniqueId: "allowance_id", //每一行的唯一标识，一般为主键列
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
		                    field : "allowance_id",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
							visible:false

		                },{
		                    field : "cooperation_type",
		                    title : "合作类型",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "default_flag",
		                    title : "是否默认",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "team_fyc_min",
		                    title : "所属团队FYC下限",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
							field : "team_fyc_max",
							title : "所属团队FYC上限",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "allowance_index",
							title : "管理津贴（FYC）",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "created_by",
							title : "创建人",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "created_time",
							title : "创建时间",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "updated_by",
							title : "更新人",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "updated_time",
							title : "更新时间",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "remark",
							title : "备注",
							align : "center",
							valign : "middle",
							sortable : "true"
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

					};
					return temp;
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
	</script>
 
</head>
<body>
<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="insComanpy-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
<div id="toolbar" class="btn-toolbar">
	<shiro:hasPermission name="allowance:add">
    	<button class="btn btn-info" type="button" onclick="createAddProcessTab('allowance:add','')">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="allowance:update">
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('allowance:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="allowance:delete">
    	<button class=" btn btn-danger" type="button" onclick="delDept('allowance:delete')">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="allowance:export">
	    <button onclick="" type="button" class="btn btn-success">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出</span>
	    </button>
 	</shiro:hasPermission>    
</div> 
</body>
</html>