<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>销售组织管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/tableExport.js"></script>
	<script type="text/javascript">
		var salesOrgInfo = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
			salesOrgInfo.init();
		});
		
		function openDetailTab(permission){
			if(salesOrgInfo.checkSingleData()){
				var salesOrgInfoId = salesOrgInfo.seItem.SALES_ORG_ID;
				//alert(insComanpy.seItem.INSURANCE_COMPANY_ID);
				createAddProcessTab('salesOrgInfo:update',salesOrgInfoId);
			}
		}
		
		function exportSelect(){
			var orgIdsS = $('#salesOrgInfo-table').bootstrapTable('getSelections');
			var orgIds = "";
			for(var i=0;i<orgIdsS.length;i++){
				orgIds = orgIds + orgIdsS[i].SALES_ORG_ID + ",";
			}
			orgIds = orgIds.substring(0,orgIds.length-1);
			orgExport(orgIds);
		}
		
		function exportAll(){
			orgExport("all");
		}
		
		function orgExport(ids){
			location.href="salesOrgInfo/orgExport?ids="+ids;
			/* 
			$.ajax({
                url:'salesOrgInfo/orgExport',
                type:'post',
                dataType:'json',
                data:{ids:ids},
                success:function(data){
					$.alert({
						title: '提示！',
						content: '导出成功',
						type: 'blue'
					});
                	$("#salesOrgInfo-table").bootstrapTable('refresh');
                }
			})*/
		}
		
		function updateStatus(salesOrgId,orgStatus){
			$.confirm({
		        title: '提示信息!',
		        content: '您确定要执行此操作吗？',
		        type: 'blue',
		        typeAnimated: true,
		        buttons: {
		        	确定: {
			        	action: function(){
							$.ajax({
				                url:'salesOrgInfo/updateStatus',
				                type:'post',
				                dataType:'json',
				                data:{salesOrgId:salesOrgId,orgStatus:orgStatus},
				                success:function(data){
				                	$.alert({
					                     title: '提示信息！',
					                     content: '执行成功!',
					                     type: 'blue'
					                 });
				                	$("#salesOrgInfo-table").bootstrapTable('refresh');
				                }
							})
			        	}
		        	},
			       	 取消: function () {
			        	return true; 
			        }
		        }
			});	
		}
		
		var salesOrgInfo = function (){
			return{
				init:function (){
		            $('#salesOrgInfo-table').bootstrapTable({
		            	url: "salesOrgInfo/salesOrgInfoList",
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
		                pageSize : 10, //默认每页条数
		                pageNumber : 1, //默认分页
		                pageList : [5, 10, 20, 50 ],//分页数
		                toolbar:"#salesOrg_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "SALES_ORG_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: salesOrgInfo.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
		                    field : "SALES_ORG_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
	  			            formatter:function(value,row,index){
	  			                var pageSize=$('#salesOrgInfo-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#salesOrgInfo-table').bootstrapTable('getOptions').pageNumber;
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
		                },{
		                    field : "SALES_ORG_NAME",
		                    title : "机构名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SALES_ORG_CODE",
		                    title : "机构编码",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SALES_ORG_LEVEL",
		                    title : "机构级别",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";
				            	if(row.SALES_ORG_LEVEL=="01"){
				            		value = "总公司";
				            	}else if(row.SALES_ORG_LEVEL=="02"){
				            		value = "省级分公司";
				            	}else if(row.SALES_ORG_LEVEL=="03"){
				            		value = "市级分公司";
				            	}else if(row.SALES_ORG_LEVEL=="04"){
				            		value = "区县分公司";
				            	}else if(row.SALES_ORG_LEVEL=="05") {
									value = "团队级别";
								} else{
				            		value = row.SALES_ORG_LEVEL;
				            	}
				            	return value;
		                    }
		                },{
		                    field : "ORG_STATUS",
		                    title : "状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";
				            	if(row.ORG_STATUS=="0"){
				            		value = "筹建";
				            	}else if(row.ORG_STATUS=="1"){
				            		value = "正常";
				            	}else if(row.ORG_STATUS=="2"){
				            		value = "整改";
				            	}else if(row.ORG_STATUS=="3"){
				            		value = "撤销";
				            	}else{
				            		value = row.ORG_STATUS;
				            	}
				            	return value;
		                    }
		                },{
							field : "CREATED_TIME",
							title : "创建时间",
							align : "center",
							valign : "middle",
							sortable : "true",
							formatter:function(value,row,index){
								var time = new Date(row.CREATED_TIME);
								var year = time.getFullYear();
								var month = (time.getMonth()+1<10)?'0'+(time.getMonth()+1):(time.getMonth()+1);
								var date = (time.getDate()+1<11)?'0'+time.getDate():time.getDate();
								var hours = (time.getHours()+1<11)?'0'+time.getHours():time.getHours();
								var minutes = (time.getMinutes()+1<11)?'0'+time.getMinutes():time.getMinutes();
								var seconds = (time.getSeconds()+1<11)?'0'+time.getSeconds():time.getSeconds();
								return year+'-'+month+'-'+date+' '+hours+':'+minutes+':'+seconds;
							}
						},{
		                    field : "操作",
		                    title : "操作",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){
		                    	if(row.ORG_STATUS=="0"){
									return '<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_ORG_ID+'\',\'1\')" style="color:blue">正常</a>';
		                    	}else if(row.ORG_STATUS=="1"){
		                    		return '<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_ORG_ID+'\',\'2\')" style="color:blue">整改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_ORG_ID+'\',\'3\')" style="color:blue">撤销</a>';
		                    	}else if(row.ORG_STATUS=="2"){
		                    		return '<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_ORG_ID+'\',\'1\')" style="color:blue">正常</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_ORG_ID+'\',\'3\')" style="color:blue">撤销</a>';
		                    	}else if(row.ORG_STATUS=="3"){
		                    		return '<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_ORG_ID+'\',\'1\')" style="color:blue">正常</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_ORG_ID+'\',\'2\')" style="color:blue">整改</a>';
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
						pageNo: params.pageNumber, //页码
						salesOrgName: $("#os_salesOrgName").val(),
						salesOrgCode: $("#os_salesOrgCode").val(),
						salesOrgLevel: $("#os_salesOrgLevel").val(),
						cooperationType: $("#os_cooperationType").val()
					};
					return temp;
				},
				
				search:function(){
					$("#salesOrgInfo-table").bootstrapTable('destroy');
					salesOrgInfo.init();
				},
				
				empty:function(){
					$("#os_salesOrgName").val("");
					$("#os_salesOrgCode").val("");
					$("#os_salesOrgLevel").val("");
					$("#os_cooperationType").val("");
					$("#salesOrgInfo-table").bootstrapTable('refresh');
				},
				
		        checkSingleData:function () {
		            var selected = $('#salesOrgInfo-table').bootstrapTable('getSelections');
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
		            	salesOrgInfo.seItem = selected[0];
		                return true;
		            }
		        },
			}
		}();
	</script>
 
</head>
<body>

<div class="panel panel-default">
	<div class="panel-body">
		<form id="SearchConForm" class=" form-inline hz-form-inline">
			<div class="form-group">
			    <label class="control-label">机构名称</label>
			   	<input type="text" class="form-control" id="os_salesOrgName" name="salesOrgName" placeholder="请输入组织机构名称">
			</div>
		 	<div class="form-group">
		  		<label class="control-label">机构代码</label>
		   		<input type="text" class="form-control" id="os_salesOrgCode" name="salesOrgCode" placeholder="请输入组织好机构代码">
		  	</div>
		  	<div class="form-group">
			  	<label class="control-label">机构级别</label>
				<select class="form-control" id="os_salesOrgLevel" name="salesOrgLevel">
		             <option value="">机构级别</option>
		             <option value="01">总公司</option>
		             <option value="02">省级分公司</option>
		             <option value="03">市级分公司</option>
		             <option value="04">区县分公司</option>
					<option value="05">团队级别</option>
		        </select>
		  	</div>
	      	<div class="form-group">
		  		<button type="button" onclick="salesOrgInfo.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="salesOrgInfo.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="salesOrgInfo-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->
<div id="salesOrg_toolbar" class="btn-toolbar">
	<shiro:hasPermission name="salesOrgInfo:add">
    	<button class="btn btn-info" type="button" onclick="createAddProcessTab('salesOrgInfo:add','')">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesOrgInfo:update">
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('salesOrgInfo:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesOrgInfo:delete">
    	<button class=" btn btn-danger" type="button" onclick="">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesOrgInfo:exportAll">
	    <button onclick="exportAll()" type="button" class=" btn btn-info">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出全部</span>
	    </button>
 	</shiro:hasPermission>
    <shiro:hasPermission name="salesOrgInfo:exportSelect">
	    <button onclick="exportSelect()" type="button" class=" btn btn-info">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出选中</span>
	    </button>
 	</shiro:hasPermission> 	
    <shiro:hasPermission name="salesOrgInfo:tree">
	    <button onclick="createAddProcessTab('salesOrgInfo:tree','')" type="button" class="btn btn-success">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >组织机构树</span>
	    </button>
 	</shiro:hasPermission>  	    
</div> 
</body>
</html>