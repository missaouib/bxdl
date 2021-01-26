<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>黑名单列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		var SalerBlackList = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
		    commSalesOrgs("BS_salesOrgId","");
        	commSalesTeams("BS_salesTeamId","");	
        	getSysDict("search_blackReason","BLACK_REASON");
        	commGetSales("","BS_salerId");	
			SalerBlackList.init(); 
			
			$('#BS_insuranceSalerNo').bind('input propertychange', function() {
				commGetSales($(this).val(),'BS_salerId');
			});			
		});	
		
		function openDetailTab(permission){
			if(SalerBlackList.checkSingleData()){
				var blackId = SalerBlackList.seItem.black_id;
				createAddProcessTab(permission,insuranceSalesId);
			}
		}	
		function openDetailView(INSURANCE_SALES_ID,permission){
			var insuranceSalesId = INSURANCE_SALES_ID;
			createAddProcessTab(permission,insuranceSalesId);
		}
		function setValue(selectId,value){
			$("#"+selectId+" option").each(function(){
		        if($(this).val()==value){
		            value = $(this).text();
		        }
			});	
	        return value;			
		}		
		
		var SalerBlackList = function (){
			return{
				init:function (){
		            $('#SalerBlackList-table').bootstrapTable({
		            	url: "insuranceSalesInfo/salesBlackPage",
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
		                toolbar:"#SalerBlackList_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "INSURANCE_SALES_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: SalerBlackList.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
		                    field : "black_id",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
	  			            formatter:function(value,row,index){
	  			                var pageSize=$('#SalerBlackList-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#SalerBlackList-table').bootstrapTable('getOptions').pageNumber;
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
		                },{
		                    field : "INSURANCE_SALER",
		                    title : "员工姓名",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "INSURANCE_SALER_NO",
		                    title : "员工工号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "CARD_NO",
		                    title : "证件号码",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SALES_ORG_ID",
		                    title : "组织机构",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";				            	
				            	return setValue("BS_salesOrgId",row.SALES_ORG_ID);
		                    }
		                },{
		                    field : "SALES_TEAM_ID",
		                    title : "销售团队",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";				            	
				            	return setValue("BS_salesTeamId",row.SALES_TEAM_ID);
		                    }
		                },{
		                    field : "black_date",
		                    title : "进入日期",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"	                    
		                },{
		                    field : "black_reason",
		                    title : "进入原因",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";				            	
				            	return setValue("search_blackReason",row.black_reason);
		                    }	
		                },{
		                    field : "black_status",
		                    title : "状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";
				            	if(row.black_status=="0"){
				            		value = "有效";
				            	}else if(row.black_status=="1"){
				            		value = "解除";
				            	}else{
				            		value = row.black_status;
				            	}
				            	return value;
		                    }
		                	},{
			                    field : "操作",
			                    title : "操作",
			                    align : "center",
			                    valign : "middle",
			                    sortable : "true",
			                    formatter:function(value,row,index){
									return '<a href="javascript:void(0)" onclick="openDetailView(\''+row.saler_id+'\',\'insuranceSalesInfo:view\')" style="color:blue">查看</a>';
			                    }
			            }
		                ],
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
						salesOrgId: $("#BS_salesOrgId").val(),
						salesTeamId: $("#BS_salesTeamId").val(),
						salerId: $("#BS_salerId").val(),
						insuranceSalerNo:$("#BS_insuranceSalerNo").val(),
						blackReason: $("#search_blackReason").val()
					};
					return temp;
				},
				
				search:function(){
					$("#SalerBlackList-table").bootstrapTable('destroy');
					SalerBlackList.init();
				},
				
				empty:function(){
					$("#BS_salesOrgId").val("");
					$("#BS_salesTeamId").val("");
					commGetSales("","BS_salerId");
					$("#BS_salerId").val("");
					$("#search_blackReason").val("");
					$("#BS_insuranceSalerNo").val("");
					$("#SalerBlackList-table").bootstrapTable('refresh');
				},
				
		        checkSingleData:function () {
		            var selected = $('#insuranceSalesInfo-table').bootstrapTable('getSelections');
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
		            	SalerBlackList.seItem = selected[0];
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
		  	<div class="form-group" style="display:none">
		   		<label class="control-label">选择</label>
				<select class="form-control" id="BS_salesOrgId" name="salesOrgId">
		              <option value="">销售机构</option>
		        </select>
		  	</div>
		  	<div class="form-group" style="display:none">
		  		<label class="control-label">选择</label>
				<select class="form-control" id="BS_salesTeamId" name="salesTeamId">
		             <option value="">销售团队</option>
		        </select>
	      	</div>
		  	<div class="form-group" style="display:none">
				<label class="control-label">选择</label>
				<select class="form-control" id="search_blackReason" name="search_blackReason">
		             <option value="">进入原因</option>
		        </select>
	      	</div>
		  	<div class="form-group">
		  		<label class="control-label">员工工号</label>
		   		<input type="text" class="form-control" id="BS_insuranceSalerNo" name="insuranceSalerNo" placeholder="请输入员工工号">
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">选择员工</label>
				<select class="form-control" id="BS_salerId" name="salerId">
		             <option value="">选择员工</option>
		        </select>
		  	</div>
	      	<div class="form-group">
		  		<button type="button" onclick="SalerBlackList.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="SalerBlackList.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="SalerBlackList-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->

</body>
</html>