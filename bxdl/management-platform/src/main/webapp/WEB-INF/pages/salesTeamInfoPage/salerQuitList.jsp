<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>离职人员列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		var SalerQuitList = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
			SalerQuitList.init();
			getRankSequenceList("QS_rankSequenceId");			
		    $("#QS_rankSequenceId").on(  
	            "change",function(){
	            	getSalesGrade("QS_salesGradeId",$(this).find("option:selected").val());
	            }
		    )
		    commSalesOrgs("QS_salesOrgId");
		    $("#QS_salesOrgId").on(  
	            "change",function(){
	            	commSalesTeams("QS_salesTeamId",$(this).find("option:selected").val());
	            }
			)		    
		    
		});	
		
		function openDetailTab(permission,insuranceSalesId){
			createAddProcessTab(permission,insuranceSalesId);
		}		
		
		var SalerQuitList = function (){
			return{
				init:function (){
		            $('#SalerQuitList-table').bootstrapTable({
		            	url: "insuranceSalesInfo/insuranceSalesPage",
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
		                toolbar:"#SalerQuitList_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "INSURANCE_SALES_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: SalerQuitList.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
		                    field : "INSURANCE_SALES_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
	  			            formatter:function(value,row,index){
	  			                var pageSize=$('#SalerQuitList-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#SalerQuitList-table').bootstrapTable('getOptions').pageNumber;
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
		                    field : "SALES_ORG_NAME",
		                    title : "组织机构",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SALES_TEAM_NAME",
		                    title : "销售团队",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SALES_STATUS",
		                    title : "状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";
				            	if(row.SALES_STATUS=="0"){
				            		value = "试用";
				            	}else if(row.SALES_STATUS=="1"){
				            		value = "正式";
				            	}else if(row.SALES_STATUS=="2"){
				            		value = "离职";
				            	}else if(row.SALES_STATUS=="3"){
				            		value = "黑名单";
				            	}else{
				            		value = row.SALES_STATUS;
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
								return '<a href="javascript:void(0)" onclick="openDetailTab(\'insuranceSalesInfo:quitDetail\',\''+row.INSURANCE_SALES_ID+'\')" style="color:blue">查看</a>';
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
						salesStatus: "2",
						salesOrgId: $("#QS_salesOrgId").val(),
						salesTeamId: $("#QS_salesTeamId").val(),
						insuranceSalerNo: $("#QS_insuranceSalerNo").val(),
						insuranceSaler: $("#QS_insuranceSaler").val(),
						rankSequenceId: $("#QS_rankSequenceId").val(),
						salesGradeId: $("#QS_salesGradeId").val()
					};
					return temp;
				},
				
				search:function(){
					$("#SalerQuitList-table").bootstrapTable('destroy');
					SalerQuitList.init();
				},
				
				empty:function(){
					$("#QS_salesOrgId").val("");
					$("#QS_salesTeamId").val("");
					$("#QS_insuranceSalerNo").val("");
					$("#QS_insuranceSaler").val("");
					$("#QS_rankSequenceId").val("");
					$("#QS_salesGradeId").val("");	
					$("#SalerQuitList-table").bootstrapTable('refresh');
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
		            	SalerQuitList.seItem = selected[0];
		                return true;
		            }
		        },
		        
		        //关闭模态框
		        closeDlg:function () {
		            $("#salerQuitAddDlg").modal('hide');
				    document.getElementById("saveButton").removeAttribute("disabled");
				    SalerQuitList.formValidator();           
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
		  		<label class="control-label">组织机构</label>
				<select class="form-control" id="QS_salesOrgId" name="salesOrgId">
		              <option value="">组织机构</option>
		        </select>
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">销售团队</label>
				<select class="form-control" id="QS_salesTeamId" name="salesTeamId">
		             <option value="">销售团队</option>
		        </select>
	      	</div>
		  	<div class="form-group">
		  		<label class="control-label">员工工号</label>
		   		 <input type="text" class="form-control" id="QS_insuranceSalerNo" name="insuranceSalerNo" placeholder="请输入员工工号">
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">员工姓名</label>
		   		 <input type="text" class="form-control" id="QS_insuranceSaler" name="insuranceSaler" placeholder="请输入员工姓名">
		  	</div>

		  	<div class="form-group">
			  	<label class="control-label">职级序列</label>
				<select class="form-control" id="QS_rankSequenceId" name="rankSequenceId">
		             <option value="">职级序列</option>
		        </select>
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">员工职级</label>
				<select class="form-control" id="QS_salesGradeId" name="salesGradeId">
		             <option value="">员工职级</option>
		        </select>
	      	</div>
	      	<div class="form-group">
		  		<button type="button" onclick="SalerQuitList.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="SalerQuitList.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="SalerQuitList-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->

</body>
</html>