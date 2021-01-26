<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>销售团队管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/role/role.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/js/jquery-form.js"></script>
	<script type="text/javascript">
		var salesTeamInfo = {
		    seItem: null		//选中的条目
		};
	
		$(function () {	
			commSalesOrgs("TS_salesOrgId");
			salesTeamInfo.init();
		});
		
		function openDetailTab(permission){
			if(salesTeamInfo.checkSingleData()){
				var salesTeamId = salesTeamInfo.seItem.SALES_TEAM_ID;
				//alert(insComanpy.seItem.INSURANCE_COMPANY_ID);
				createAddProcessTab('salesTeamInfo:update',salesTeamId);
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
		
		function openLeadersTab(permission){
			if(salesTeamInfo.checkSingleData()){
				var salesTeamId = salesTeamInfo.seItem.SALES_TEAM_ID;
				createAddProcessTab(permission,salesTeamId);
			}
		}
		
		function updateStatus(salesTeamId,teamStatus){
			var contentStr = "";
			if(teamStatus=="0"){
				contentStr = "确定执行恢复操作吗？";
			}else if(teamStatus=="1"){
				contentStr = "确定执行撤销操作吗？";
			}
			$.confirm({
				title: '提示信息!',
				content:contentStr,
				type: 'blue',
				typeAnimated: true,
				buttons: {
					确定: {
						action: function(){
							$.ajax({
								url:'salesTeamInfo/updateStatus',
								type:'post',
								dataType:'json',
								data:{salesTeamId:salesTeamId,teamStatus:teamStatus},
								success:function(data){
									$.alert({
										 title: '提示信息！',
										 content: '执行成功!',
										 type: 'blue'
									 });
									$("#salesTeamInfo-table").bootstrapTable('refresh');
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
		
		var salesTeamInfo = function (){
			return{
				init:function (){
		            $('#salesTeamInfo-table').bootstrapTable({
		            	url: "salesTeamInfo/salesTeamInfoList",
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
		                toolbar:"#salesTeam_toolbar",
		                showColumns : false, //显示隐藏列
		                uniqueId: "SALES_TEAM_ID", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: salesTeamInfo.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
		                    field : "SALES_TEAM_ID",
		                    title : "编号",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
	  			            formatter:function(value,row,index){
	  			                var pageSize=$('#salesTeamInfo-table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	  			                var pageNumber=$('#salesTeamInfo-table').bootstrapTable('getOptions').pageNumber;
	  			                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	  			            }
		                },{
		                    field : "SALES_TEAM_NAME",
		                    title : "团队名称",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "SALES_TEAM_CODE",
		                    title : "团队编码",
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
				            	return setValue("TS_salesOrgId",row.SALES_ORG_ID);
		                    }
		                },{
		                    field : "DATE_OF_ESTABLISHMENT",
		                    title : "成立时间",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"
		                },{
		                    field : "TEAM_STATUS",
		                    title : "状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true",
		                    formatter:function(value,row,index){ 
				            	var value="";
				            	if(row.TEAM_STATUS=="0"){
				            		value = "有效";
				            	}else if(row.TEAM_STATUS=="1"){
				            		value = "无效";
				            	}else{
				            		value = row.TEAM_STATUS;
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
		                    	if(row.TEAM_STATUS=="0"){
									return '<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_TEAM_ID+'\',\'1\')" style="color:blue">撤销</a>';
		                    	}else if(row.TEAM_STATUS=="1"){
		                    		return '<a href="javascript:void(0)" onclick="updateStatus(\''+row.SALES_TEAM_ID+'\',\'0\')" style="color:blue">恢复</a>';
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
						salesTeamName: $("#TS_salesTeamName").val(),
						salesTeamCode: $("#TS_salesTeamCode").val(),
						salesOrgId: $("#TS_salesOrgId").val(),
						salesTeamType: $("#TS_salesTeamType").val(),
					};
					return temp;
				},
				
				search:function(){
					$("#salesTeamInfo-table").bootstrapTable('destroy');
					salesTeamInfo.init();
				},
				
				empty:function(){
					$("#TS_salesTeamName").val("");
					$("#TS_salesTeamCode").val("");
					$("#TS_salesOrgId").val("");
					$("#TS_salesTeamType").val("");
					$("#salesTeamInfo-table").bootstrapTable('refresh');
				},
				
		        checkSingleData:function () {
		            var selected = $('#salesTeamInfo-table').bootstrapTable('getSelections');
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
		            	salesTeamInfo.seItem = selected[0];
		                return true;
		            }
		        },
			}
		}();
			 function downloadTemplate() {
            window.open("${path}/upload/files/销售团队模板.xls");
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
                url: 'salesTeamInfo/importSalesTeam',
                dataType: 'json',
                success: function (data) {
                    $('#loadingModal').modal('hide');
                    if (data.code == "200") {
                        $("#salesTeamInfo-table").bootstrapTable('refresh');
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
		<form id="SearchConForm" class=" form-inline hz-form-inline">
		 	<div class="form-group">
		  		<label class="control-label">团队名称</label>
		   		<input type="text" class="form-control" id="TS_salesTeamName" name="salesTeamName" placeholder="请输入团队名称">
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">团队代码</label>
		   		<input type="text" class="form-control" id="TS_salesTeamCode" name="salesTeamCode" placeholder="请输入团队代码">
		  	</div>
		  	<div class="form-group">
		  		<label class="control-label">所属机构</label>
				<select class="form-control form-control-static" id="TS_salesOrgId" name="salesOrgId">
		              <option value="">销售机构</option>
		        </select>
		  	</div>
	     	<div class="form-group">
		  		<button type="button" onclick="salesTeamInfo.search()" class="btn btn-info ">
		   			<span class="glyphicon glyphicon-search" aria-hidden="true" >搜索</span>
		   		</button>
		   		<button type="button" onclick="salesTeamInfo.empty()" class="btn btn-danger ">
		   			<span class="glyphicon glyphicon-remove" aria-hidden="true" >清空</span>
		   		</button>
	   	  	</div>
		</form>
	</div>
</div>

<!--列表 -->
<div style="width: 99%;overflow: auto;">
	<table id="salesTeamInfo-table" class="table table-hover table-striped table-condensed table-bordered"></table>
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
<div id="salesTeam_toolbar" class="btn-toolbar">
	<shiro:hasPermission name="salesTeamInfo:add">
    	<button class="btn btn-info" type="button" onclick="createAddProcessTab('salesTeamInfo:add','')">
	    		<span class="glyphicon glyphicon-plus" >添加</span>
    	</button>
		<button type="button" class=" btn btn-primary" onclick="downloadTemplate()">
			<span class="glyphicon glyphicon-book">导入模板</span>
		</button>
		<button class="btn btn-success" type="button" onclick="alertFile()">
			<span class="glyphicon glyphicon-import" >批量导入</span>
		</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesTeamInfo:update">
    	<button type="button" class=" btn btn-info" onclick="openDetailTab('salesTeamInfo:update')">
    			<span class="glyphicon glyphicon-pencil" >修改</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesTeamInfo:delete">
    	<button class=" btn btn-danger" type="button" onclick="">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="salesTeamLeaders:list">
    	<button type="button" class=" btn btn-info" onclick="openLeadersTab('salesTeamLeaders:list')">
    			<span class="glyphicon glyphicon-pencil" >主管任命</span>
    	</button>
    </shiro:hasPermission>    
    <shiro:hasPermission name="salesTeamInfo:export">
	    <button onclick="" type="button" class="btn btn-success">
	      		<span class="glyphicon glyphicon-paste" aria-hidden="true" >导出</span>
	    </button>
 	</shiro:hasPermission>    
</div> 
</body>
</html>