<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>险种类别管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		var insuranceType = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
			insuranceType.init();
		});
		
		function openDetailTab(permission){
			if(insuranceType.checkSingleData()){
				var id = insuranceType.seItem.INSURANCE_TYPE_ID;
				 createAddProcessTab('insuranceType:update',id);
			}
		}		
		
		var insuranceType = function (){
			return{
				init:function (){
		            $('#insuranceType-table').bootstrapTable({
		            	url: "/Insurance_type_manager/do_Insurance_type",
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
		                toolbar:"#insuranceType_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "INSURANCE_TYPE_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: insuranceType.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
		                    field : "INSURANCE_TYPE_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
	  			            formatter:function(value,row,index){
	  			                var pageSize=$('#insuranceType-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#insuranceType-table').bootstrapTable('getOptions').pageNumber;
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
		                },{
		                    field : "INSURANCE_TYPE_NAME",
		                    title : "险种名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "INSURANCE_TYPE_CODE",
		                    title : "险种代码",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SYS_PARAMETER",
		                    title : "是否本系统参数",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "CREATED_BY",
		                    title : "创建人",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "CREATED_TIME",
		                    title : "创建时间",
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
						pageNo: params.pageNumber, //页码
					};
					return temp;
				},
				
		        checkSingleData:function () {
		            var selected = $('#insuranceType-table').bootstrapTable('getSelections');
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
		            	insuranceType.seItem = selected[0];
		                return true;
		            }
		        },
			}
		}();
		
		 /* 逻辑删除修改状态 */
		 function delUpdateType() {
			 if(insuranceType.checkSingleData()){
				var id = insuranceType.seItem.INSURANCE_TYPE_ID;
				 var mymessage = confirm("确认删除吗？");
					if (mymessage == true) {
					 /* 删除服务 */
					$.ajax({
						url : "Insurance_type_manager/update_type_status",
						type : "post",
						dataType : "json",
						data : {
							id : id
						},
						success : function(msg) {
							 if (msg.messageCode == "200") {
								 $("#insuranceType-table").bootstrapTable('refresh');
								 alert("删除成功！");
								/*  parent.createTab('${app}/goldProduct/toUpdateGoldProduct/' + productId, '修改信息'); */
							 } else {
								$.messager.show({
									title : '提示',
									msg : '操作失败'
								});
						} 
					},
				});  
					
			 }	 
				}
			 
			 
			
		}
		
		
	</script>
 
</head>
<body>
<!--列表 -->
<div style="overflow:scroll;">
	<table id="insuranceType-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
<div id="insuranceType_toolbar" class="btn-toolbar">
<%--  <shiro:hasPermission name="insuranceType:add">  --%>
    	<button class="btn btn-info" type="button" onclick="createAddProcessTab('insuranceType:add','')">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
  <%-- </shiro:hasPermission> --%>
   <%--  <shiro:hasPermission name="insuranceType:update">  --%>
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('insuranceType:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
   <%--   </shiro:hasPermission>
    <shiro:hasPermission name="insuranceType:delete">  --%>
    	<button class=" btn btn-danger" type="button" onclick="delUpdateType('insuranceType:updateStatus')">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
   <%--  </shiro:hasPermission>  --%>
  <%--   <shiro:hasPermission name="insuranceType:export"> --%>
	    <!-- <button onclick="" type="button" class="btn btn-success">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出</span>
	    </button> -->
 	<%-- </shiro:hasPermission>    --%> 
</div> 
</body>
</html>