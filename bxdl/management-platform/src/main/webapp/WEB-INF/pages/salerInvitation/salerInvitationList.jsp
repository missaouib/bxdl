<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>邀请加盟列表</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script type="text/javascript">
		var SalerInvitationList = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
		    commSalesOrgs("SIS_salesOrgId","");
        	commSalesTeams("SIS_salesTeamId","");	
        	SalerInvitationList.formValidator();
			SalerInvitationList.init(); 
		});	
		
		function openDetailTab(permission){
			if(SalerInvitationList.checkSingleData()){
				var blackId = SalerInvitationList.seItem.black_id;
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
		
		var SalerInvitationList = function (){
			return{
				init:function (){
		            $('#SalerInvitationList-table').bootstrapTable({
		            	url: "salerInvitation/getSalerInvitationPage",
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
		                toolbar:"#SalerInvitationList_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "invited_id", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: SalerInvitationList.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
		                    field : "INVITED_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
	  			            formatter:function(value,row,index){
	  			                var pageSize=$('#SalerInvitationList-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#SalerInvitationList-table').bootstrapTable('getOptions').pageNumber;
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
		                },{
		                    field : "PERSON_NAME",
		                    title : "申请人姓名",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "PERSON_MOBILE",
		                    title : "申请人手机号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "TJ_SALER_NO",
		                    title : "推荐员工号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "DB_SALER_NO",
		                    title : "担保员工号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "sales_org_id",
		                    title : "组织机构",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";				            	
				            	return setValue("SIS_salesOrgId",row.sales_org_id);
		                    }
		                },{
		                    field : "sales_team_id",
		                    title : "销售团队",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";				            	
				            	return setValue("SIS_salesTeamId",row.sales_team_id);
		                    }		                    
		                },{
		                    field : "APPLY_DATE",
		                    title : "申请时间",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "CONFIRM_DATE",
		                    title : "认证时间",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "CHECK_DATE",
		                    title : "审核时间",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "CHECK_STATUS",
		                    title : "状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";				            	
				            	if(row.CHECK_STATUS == "0"){
				            		value = "待认证";
				            	}else if(row.CHECK_STATUS == "1"){
				            		value = "待审核";
				            	}else if(row.CHECK_STATUS == "2"){
				            		value = "审核通过";
				            	}else if(row.CHECK_STATUS == "-1"){
				            		value = "认证不通过";
				            	}else if(row.CHECK_STATUS == "-2"){
				            		value = "审核不通过";
				            	}else if(row.CHECK_STATUS == "3"){
				            		value = "已入职";
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
				            	var value="";				            	
				            	if(row.CHECK_STATUS == "0"){
									return '<a href="javascript:void(0)" onclick="SalerInvitationList.renzheng(\''+row.INVITED_ID+'\')" style="color:blue">认证</a>';
				            	}else if(row.CHECK_STATUS == "1"){
				            		return '<a href="javascript:void(0)" onclick="SalerInvitationList.shenhe(\''+row.INVITED_ID+'\')" style="color:blue">审核</a>';
				            	}else if(row.CHECK_STATUS == "2"){
				            		return '<a href="javascript:void(0)" onclick="createAddProcessTab(\'salerInvitation:toCreateSaler\',\''+row.INVITED_ID+'\');" style="color:blue">维护信息</a>';
				            	}else if(row.CHECK_STATUS == "-1"){
				            		return '<a href="javascript:void(0)" onclick="SalerInvitationList.rzView(\''+row.INVITED_ID+'\',\''+row.CHECK_STATUS+'\',\''+row.CHECK_MARK+'\')" style="color:blue">查看原因</a>';
				            	}else if(row.CHECK_STATUS == "-2"){
				            		return '<a href="javascript:void(0)" onclick="SalerInvitationList.shView(\''+row.INVITED_ID+'\',\''+row.CHECK_STATUS+'\',\''+row.CHECK_MARK+'\')" style="color:blue">查看原因</a>';
				            	}else if(row.CHECK_STATUS == "3"){
				            		return '<a href="javascript:void(0)" onclick="createAddProcessTab(\'salerInvitation:toUpdateSaler\',\''+row.INVITED_ID+'\');" style="color:blue">修改信息</a>';
				            	}
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
				
		        //打开认证模态框
		        renzheng:function (invitedId) {
		            $("#renzhengAddDlg").modal('show');
		            $("#rz_add_invitedId").val(invitedId);
		        },
		        
		        //打开认证模态框
		        rzView:function (invitedId,checkStatus,checkMark) {
		            $("#renzhengMydlg").modal('show');
		            $("#rz_up_invitedId").val(invitedId);
		            $("#rz_up_checkStatus").val(checkStatus);
		            $("#rz_up_checkMark").val(checkMark);
		        },
		        
		        //打开审核模态框
		        shenhe:function (invitedId) {
		            $("#shenheAddDlg").modal('show');
		            $("#sh_add_invitedId").val(invitedId);
		        },
		        
		        //打开认证模态框
		        shView:function (invitedId,checkStatus,checkMark) {
		            $("#shenheMydlg").modal('show');
		            $("#sh_up_invitedId").val(invitedId);
		            $("#sh_up_checkStatus").val(checkStatus);
		            $("#sh_up_checkMark").val(checkMark);
		        },
		        
		        renzhengSave:function (formId) {
		        	document.getElementById("saveButton").setAttribute("disabled", true);
		            if($("#"+formId).data('bootstrapValidator').validate().isValid()){
		            	flag = true;
		    			if(flag){
			            	$.ajax({
			                    url:'salerInvitation/renzhengSave',
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
			                            $("#SalerInvitationList-table").bootstrapTable("refresh");
			                            SalerInvitationList.closeDlg();
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
		        
		        //表单验证
		        formValidator:function () {
		            $("#renzheng_addForm").bootstrapValidator({
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
				
		        //关闭模态框
		        closeDlg:function () {
		            $("#renzhengAddDlg").modal('hide');
		            $("#shenheAddDlg").modal('hide');
		            $("#renzhengMydlg").modal('hide');
		            $("#shenheMydlg").modal('hide');
		            $("input[type=reset]").trigger("click");
				    document.getElementById("saveButton").removeAttribute("disabled");         
		        },				
				
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						salesOrgId: $("#SIS_salesOrgId").val(),
						salesTeamId: $("#SIS_salesTeamId").val(),
						likeSalerNo: $("#SIS_insuranceSalerNo").val(),
						personName: $("#SIS_personName").val(),
						personMobile: $("#SIS_personMobile").val(),
					};
					return temp;
				},
				
				search:function(){
					$("#SalerInvitationList-table").bootstrapTable('destroy');
					SalerInvitationList.init();
				},
				
				empty:function(){
					$("#SIS_salesOrgId").val("");
					$("#SIS_salesTeamId").val("");
					$("#SIS_insuranceSalerNo").val("");
					$("#SIS_personName").val("");
					$("#SIS_personMobile").val("");
					$("#SalerInvitationList-table").bootstrapTable('refresh');
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
		            	SalerInvitationList.seItem = selected[0];
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
				<select class="form-control form-control-static" id="SIS_salesOrgId" name="salesOrgId">
		              <option value="">销售机构</option>
		        </select>
		  	</div>
		  	<div class="form-group" style="display:none">
		  		<label class="control-label">选择</label>
				<select class="form-control form-control-static" id="SIS_salesTeamId" name="salesTeamId">
		             <option value="">销售团队</option>
		        </select>
	      	</div>
		  	<div class="form-group">
		  		<label class="control-label">推荐人工号</label>
		   		<input type="text" class="form-control" id="SIS_insuranceSalerNo" name="insuranceSalerNo" placeholder="请输入推荐人工号">
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">申请人姓名</label>
		   		 <input type="text" class="form-control" id="SIS_personName" name="personName" placeholder="请输入申请人姓名">
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">申请人手机号</label>
		   		<input type="text" class="form-control" id="SIS_personMobile" name="personMobile" placeholder="请输入申请人手机号">
		  	</div>
	      	<div class="form-group">
		  		<button type="button" onclick="SalerInvitationList.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="SalerInvitationList.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="SalerInvitationList-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>
<!--toolbar  -->

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="renzhengAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">认证情况</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="renzheng_addForm"  method="post">
			<input type="hidden" id="rz_add_invitedId" name="invitedId" value="" />			
			<div class="form-group">
			<label class="col-md-2 control-label">认证结果：</label>
			<div class="col-md-3">
			<select class="form-control form-control-static" id="rz_add_checkStatus" name="checkStatus">
              	<option value="">请选择</option>
              	<option value="1">通过</option>
              	<option value="-1">不通过</option>
           	</select>			
			</div>
			</div>			
			<div class="form-group">
			<label class="col-md-2 control-label">原因：</label>
			<div class="col-md-3 ">
			<textarea id="rz_add_checkMark" name="checkMark" rows="4" class="form-control form-control-static" placeholder="请输入原因"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="SalerInvitationList.closeDlg()">关闭</button>
                <button id="saveButton" type="button" onclick="SalerInvitationList.renzhengSave('renzheng_addForm')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="shenheAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">认证情况</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="shenhe_addForm"  method="post">
			<input type="hidden" id="sh_add_invitedId" name="invitedId" value="" />			
			<div class="form-group">
			<label class="col-md-2 control-label">审核结果：</label>
			<div class="col-md-3">
			<select class="form-control form-control-static" id="sh_add_checkStatus" name="checkStatus">
              	<option value="">请选择</option>
              	<option value="2">通过</option>
              	<option value="-2">不通过</option>
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
                <button type="button" class="btn btn-default" onclick="SalerInvitationList.closeDlg()">关闭</button>
                <button id="saveButton" type="button" onclick="SalerInvitationList.renzhengSave('shenhe_addForm')" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="renzhengMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">认证情况</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="renzheng_updateForm"  method="post">
			
			<input type="hidden" id="rz_up_invitedId" name="invitedId" value="" />			
			<div class="form-group">
			<label class="col-md-2 control-label">认证结果：</label>
			<div class="col-md-3">
			<select disabled="disabled" class="form-control form-control-static" id="rz_up_checkStatus" name="checkStatus">
              	<option value="">请选择</option>
              	<option value="1">通过</option>
              	<option value="-1">不通过</option>
           	</select>			
			</div>
			</div>			
			<div class="form-group">
			<label class="col-md-2 control-label">原因：</label>
			<div class="col-md-3 ">
			<textarea id="rz_up_checkMark" readonly="readonly" name="checkMark" rows="4" class="form-control form-control-static" placeholder="请输入原因"></textarea>
			</div>
			</div>			
			
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="SalerInvitationList.closeDlg()">关闭</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="shenheMydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">审核情况</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="shenhe_updateForm"  method="post">
			
			<input type="hidden" id="rz_up_invitedId" name="invitedId" value="" />			
			<div class="form-group">
			<label class="col-md-2 control-label">审核结果：</label>
			<div class="col-md-3">
			<select disabled="disabled" class="form-control form-control-static" id="sh_up_checkStatus" name="checkStatus">
              	<option value="">请选择</option>
              	<option value="2">通过</option>
              	<option value="-2">不通过</option>
           	</select>			
			</div>
			</div>			
			<div class="form-group">
			<label class="col-md-2 control-label">原因：</label>
			<div class="col-md-3 ">
			<textarea id="sh_up_checkMark" readonly="readonly" name="checkMark" rows="4" class="form-control form-control-static" placeholder="请输入原因"></textarea>
			</div>
			</div>			
			
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="SalerInvitationList.closeDlg()">关闭</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>