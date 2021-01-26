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
		var SalesMoveList = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
		    commSalesOrgs("MS_salesOrgId");
		    commSalesTeams("MS_salesTeamId","");
			getRankSequenceList("MS_rankSequenceId");
			getSalesGrade("MS_salesGradeId","");
		    commGetSales("","MS_salerId");
		    
			SalesMoveList.init();
			
			$('#MS_insuranceSalerNo').bind('input propertychange', function() {
				commGetSales($(this).val(),'MS_salerId');
			});

			SalesMoveList.formValidator();
		    /* $("#rankSequenceId").on(  
	            "change",function(){
	            	getSalesGrade("salesGradeId",$(this).find("option:selected").val());
	            }
		    ) */

		    /* $("#salesOrgId").on(  
	            "change",function(){
	            	commSalesTeams("salesTeamId",$(this).find("option:selected").val());
	            }
			) */		    
		    
		});	
		
		function openDetailTab(permission){
			if(SalesMoveList.checkSingleData()){
				var insuranceSalesId = SalesMoveList.seItem.INSURANCE_SALES_ID;
				createAddProcessTab(permission,insuranceSalesId);
			}
		}		
		
		function setValue(selectId,value){
			$("#"+selectId+" option").each(function(){
		        if($(this).val()==value){
		        	value = $(this).text();
		        }
			});	
	        return value;			
		}
		
		var SalesMoveList = function (){
			return{
				init:function (){
		            $('#SalesMoveList-table').bootstrapTable({
		            	url: "insuranceSalesInfo/salesMovePage",
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
		                toolbar:"#SalesMoveList_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "MOVE_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: SalesMoveList.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
		                    field : "MOVE_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
	  			            formatter:function(value,row,index){
	  			                var pageSize=$('#SalesMoveList-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#SalesMoveList-table').bootstrapTable('getOptions').pageNumber;
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
		                },{
		                    field : "SALER_NAME",
		                    title : "员工姓名",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "insurance_saler_no",
		                    title : "员工工号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PRE_ORG_ID",
		                    title : "原组织机构",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 			            	
				            	return setValue("MS_salesOrgId",row.PRE_ORG_ID);
		                    }
		                },{
		                    field : "PRE_TEAM_ID",
		                    title : "原销售团队",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 				            	
				            	return setValue("MS_salesTeamId",row.PRE_TEAM_ID);
		                    }
		                },{
		                    field : "PRE_SALES_GRADE_ID",
		                    title : "原职级",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 				            	
				            	return setValue("MS_salesGradeId",row.PRE_SALES_GRADE_ID);
		                    }
		                },{
		                    field : "NEXT_ORG_ID",
		                    title : "目标组织机构",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 			            	
				            	return setValue("MS_salesOrgId",row.NEXT_ORG_ID);
		                    }
		                },{
		                    field : "NEXT_TEAM_ID",
		                    title : "目标销售团队",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 				            	
				            	return setValue("MS_salesTeamId",row.NEXT_TEAM_ID);
		                    }
		                },{
		                    field : "NEXT_SALES_GRADE_ID",
		                    title : "目标职级",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 				            	
				            	return setValue("MS_salesGradeId",row.NEXT_SALES_GRADE_ID);
		                    }
		                },{
		                    field : "CHANGE_FLAG",
		                    title : "执行状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";
				            	if(row.CHANGE_FLAG=="0"){
				            		value = "待执行";
				            	}else if(row.CHANGE_FLAG=="1"){
				            		value = "已执行";
				            	}else{
				            		value = row.CHANGE_FLAG;
				            	}
				            	return value;
		                    }
		                },{
			                    field : "MOVE_DATE",
			                    title : "调整时间",
			                    align : "center",
			                    valign : "middle",
			                    sortable : "true"
			            },{
		                    field : "CHECK_STATUS",
		                    title : "操作",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";
				            	if(row.CHECK_STATUS=="0"){
				            		value = '<a href="javascript:void(0)" onclick="SalesMoveList.shenhe(\''+row.MOVE_ID+'\')" style="color:blue">审核</a>';
				            	}else if(row.CHECK_STATUS=="1"){
				            		value = "审核通过";
				            	}
				            	return value;
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
						salerId: $("#MS_salerId").val(),
						insuranceSalerNo:$("#MS_insuranceSalerNo").val()
					};
					return temp;
				},
				
		        //打开审核模态框
		        shenhe:function (moveId) {
		            $("#shenheAddDlg").modal('show');
		            $("#sh_add_moveId").val(moveId);
		        },
		        
		        renzhengSave:function (formId) {
		        	document.getElementById("saveButton").setAttribute("disabled", true);
		            if($("#"+formId).data('bootstrapValidator').validate().isValid()){
		            	flag = true;
		    			if(flag){
			            	$.ajax({
			                    url:'insuranceSalesInfo/moveShenSave',
			                    dataType:'json',
			                    type:'post',
			                    data:$("#"+formId).serialize(),
			                    success:function(data){
			                        if(data){
			                        	 $.alert({
			                                 title: '提示信息！',
			                                 content: '操作成功!',
			                                 type: 'blue'
			                             });
			                 		    document.getElementById("saveButton").removeAttribute("disabled");
			                            $("#SalesMoveList-table").bootstrapTable("refresh");
			                            SalesMoveList.closeDlg();
			                        }else{
			                            $.alert({
			                                title: '提示信息！',
			                                content: '添加失败！',
			                                type: 'red'
			                            });
			                		    document.getElementById("saveButton").removeAttribute("disabled");
			                        }
			                    },
			                    error:function(){
			                        $.alert({
			                            title: '提示信息！',
			                            content: '请求失败！',
			                            type: 'red'
			                        });
			                    }
			                });
			            }
		            }else{
		    		    document.getElementById("saveButton").removeAttribute("disabled");
		            }
		        },
				
		      //关闭模态框
		        closeDlg:function () {
		            $("#shenheAddDlg").modal('hide');
				    document.getElementById("saveButton").removeAttribute("disabled");         
		        },	
		        
		      //表单验证
		        formValidator:function () {
		            $("#shenhe_addForm").bootstrapValidator({
		                fields:{
		                	checkStatus:{
		                        validators:{
		                            notEmpty:{
		                                message:"审核结果不能为空"
		                            },
		                        }
		                    }
		                }
		            });
		        },
		        
				search:function(){
					$("#SalesMoveList-table").bootstrapTable('destroy');
					SalesMoveList.init();
				},
				
				empty:function(){
					$("#MS_salesOrgId").val("");
					$("#MS_salesTeamId").val("");
					$("#MS_insuranceSalerNo").val("");
					commGetSales("","MS_salerId");
					$("#MS_salerId").val("");
					$("#MS_rankSequenceId").val("");
					$("#MS_salesGradeId").val("");	
					$("#SalesMoveList-table").bootstrapTable('refresh');
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
		            	SalesMoveList.seItem = selected[0];
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
				<select class="form-control" id="MS_salesOrgId" name="salesOrgId">
		              <option value="">销售机构</option>
		        </select>
		  	</div>
		  	<div class="form-group" style="display:none">
				<select class="form-control" id="MS_salesTeamId" name="salesTeamId">
		             <option value="">销售团队</option>
		        </select>
	      	</div>
	  		<div class="form-group">
		  		<label class="control-label">员工工号</label>
		   		 <input type="text" class="form-control" id="MS_insuranceSalerNo" name="insuranceSalerNo" placeholder="请输入员工工号">
	  		</div>
		  	<div class="form-group">
			  	<label class="control-label">选择员工</label>
				<select class="form-control form-control-static" id="MS_salerId" name="salerId">
		             <option value="">选择员工</option>
		        </select>
		  	</div>
	  		<div class="form-group" style="display:none">
				<select class="form-control form-control-static" id="MS_rankSequenceId" name="rankSequenceId">
		             <option value="">职级序列</option>
		        </select>
	  		</div>
		  	<div class="form-group" style="display:none">
				<select class="form-control form-control-static" id="MS_salesGradeId" name="salesGradeId">
		             <option value="">员工职级</option>
		        </select>
	      	</div>
	      	<div class="form-group">
		  		<button type="button" onclick="SalesMoveList.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="SalesMoveList.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="SalesMoveList-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="shenheAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">审核情况</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="shenhe_addForm"  method="post">
			<input type="hidden" id="sh_add_moveId" name="moveId" value="" />			
			<div class="form-group">
			<label class="col-md-2 control-label">审核结果：</label>
			<div class="col-md-3">
			<select class="form-control form-control-static" id="sh_add_checkStatus" name="checkStatus">
              	<option value="">请选择</option>
              	<option value="1">通过</option>
              	<option value="-1">不通过</option>
           	</select>			
			</div>
			</div>			
			<div class="form-group">
			<label class="col-md-2 control-label">原因：</label>
			<div class="col-md-3 ">
			<textarea id="sh_add_checkMark" name="checkMark" rows="4" class="form-control form-control-static" placeholder="请输入原因"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="SalesMoveList.closeDlg()">关闭</button>
                <button id="saveButton" type="button" onclick="SalesMoveList.renzhengSave('shenhe_addForm')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>