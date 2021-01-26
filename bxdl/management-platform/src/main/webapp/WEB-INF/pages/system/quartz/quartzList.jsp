<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/core.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>定时器管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link rel="stylesheet" href="${path}/css/system/menu/menu.css">
   	<script src="${path}/js/system/comm.js" charset="UTF-8" type="text/javascript"></script>
    <script src="${path}/static/js/bootstrap.js"></script>
	<script src="${path}/static/js/bootstrap.js"></script>
    <script src="${path}/static/js/tableExport.js"></script>
	<script src="${path}/js/jquery-form.js"></script>
	<script src="${path}/js/system/quartz/quartz.js" />
	<style>
	.btn-toolbar {margin-bottom: 5px;}
	/* 弹出框样式 */
	.modal { padding-left: 0!important;}
	.modal-dialog {	background: #fff;}
	.modal-dialog .row { margin-left: 0; margin-right: 0;}
	.modal-dialog .form-horizontal { padding-top: 15px;}
	.modal-dialog .form-horizontal .form-group { padding-left: 100px;}
	.modal-content { box-shadow: none;}
	</style>
	<script type="text/javascript">

		var quartzJob = {
		    seItem: null		//选中的条目
		};

		function operateFormatter(value, row, index) {
			var id = row.job_id;
			var status = row.status;
			var content = "";
			if ("暂停"==status){
			    content = "启用"
            }else if ("启用"==status){
			    content = "暂停"
            }
			return [
				'<button type="button" class="btn btn-info" onclick="openSelectDetailTabByButton(\'' + id + '\')">',
				'<span class="glyphicon glyphicon-pencil" id="pause_resume_job_'+id+'"+ >'+content+'</span>',
				'</button>',
			].join('');
		}
		$(function () {
			quartzJob.init();
			getSysDictVal("businessType","WEALTH_LONGEVITY");

		});

		function openSelectDetailTabByButton(jobId) {
		    var content = $("#pause_resume_job_"+jobId).html();
		    var tip = "";
		    if ("启用"==content){
		        tip = "您确定要启用该定时任务吗?";
            }else if("暂停" == content){
                tip = "您确定要暂停该定时任务吗?";
            }
		     $.confirm({
                     title: '提示信息!',
                     content: tip,
                     type: 'red',
                     typeAnimated: true,
                     buttons: {
                         确定: {
                             action: function(){
                                 $.ajax({
                                     url:'quartz/pauseJob',
                                     dataType:'json',
                                     type:'post',
                                     data:{
                                         jobId:jobId,content:content
                                     },
                                     success:function(data){
                                         if(data.result){
                                             $.alert({
                                                 title: '提示信息！',
                                                 content: '操作成功!',
                                                 type: 'blue'
                                             });
                                         }else{
                                             $.alert({
                                                 title: '提示信息！',
                                                 content: '操作失败!'+data.msg,
                                                 type: 'red'
                                             });
                                         }
                                         $("#job-table").bootstrapTable('refresh');
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
                         },
                         取消: function () {
                         }
                     }
                 });
		}

		var quartzJob = function (){
			return{
				init:function (){
		            $('#job-table').bootstrapTable({
		            	url: "quartz/job_list",
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
		                toolbar:"#quartz_job",
		                showColumns : false, //显示隐藏列
		                uniqueId: "job_id", //每一行的唯一标识，一般为主键列
		                queryParamsType:'',
		                queryParams: quartzJob.queryParams,//传递参数（*）
		                columns : [{
		                    checkbox: true
		            	},{
							field: 'SerialNumber',
							title: '序号',
							formatter: function (value, row, index) {
								return index+1;
							}
						},{
							field : "job_name",
							title : "任务名称",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
							field : "job_class_name",
							title : "任务类名称",
							align : "center",
							valign : "middle",
							sortable : "true"
						},{
		                    field : "cron",
		                    title : "cron表达式",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"

		                },{
		                    field : "job_describe",
		                    title : "描述",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"

		                },{
		                    field : "status",
		                    title : "状态",
		                    align : "center",
		                    valign : "middle",
		                    sortable : "true"

		                },{
							field : "create_time",
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
						},{
							field : "update_time",
							title : "修改时间",
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
					$("#job-table").bootstrapTable('destroy');
					quartzJob.init();
				},
				queryParams:function(params){
					var temp = {
						pageSize: params.pageSize,  //页面大小
						pageNo: params.pageNumber, //页码
						jobName:$("#jobName_seach").val(),
					};
					return temp;
				},

		        checkSingleData:function () {
		            var selected = $('#job-table').bootstrapTable('getSelections');
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
                        quartzJob.seItem = selected[0];
		                return true;
		            }
		        },
			}
		}();
		function cla() {
			$("#jobName_seach").val(''),
					quartzJob.search()
		}

	</script>

</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-inline hz-form-inline" id="searchForm">
			<div class="form-group">
				<label  class="control-label" for="jobName_seach">任务名称</label>
				<input type="text" class="form-control" id="jobName_seach" name="jobName" placeholder="任务名称">
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-info" onclick="quartzJob.search()">
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
	<table id="job-table" class="table table-hover table-striped table-condensed table-bordered"></table>
</div>




<!--toolbar  -->
<div id="quartz_job" class="btn-toolbar">
	<shiro:hasPermission name="quartz:add">
    	<button class="btn btn-info" type="button" onclick="Quartz.addJob()">
	    		<span class="glyphicon glyphicon-plus" >新增任务作业</span>
    	</button>
      </shiro:hasPermission>
    <shiro:hasPermission name="quartz:update">
		<button type="button" class=" btn btn-primary" onclick="Quartz.getValue()">
			<span class="glyphicon glyphicon-book">修改任务作业</span>
		</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="quartz:delete">
    	<button class=" btn btn-danger" type="button" onclick="Quartz.delJob()">
    			<span class="glyphicon glyphicon-remove" >删除</span>
    	</button>
    </shiro:hasPermission>
</div>



<!-- 添加菜单 -->
<div id="jobAddDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true"  >
    <div class="modal-dialog">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">添加作业</h4>
		</div>
        <div class="modal-content">
            <div class="row">
			<form class="form-horizontal" id="addJobForm"  method="post">
				<div class="form-group">
					<label class="col-md-3 control-label">作业名称：</label>
					<div class="col-md-6 ">
						<input type="text" id="jobName" name="jobName" class="form-control form-control-static">
					</div>
				</div>


			<div class="form-group">
				<label class="col-md-3 control-label">任务类名称：</label>
				<div class="col-md-6">
					<input type="text"  name="jobClassName" class="form-control form-control-static"  >
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label">cron表达式：</label>
				<div class="col-md-6">
					<input type="text"  name="cron" class="form-control form-control-static">
				</div>
			</div>

			<div class="form-group" style="z-index: 0" >
				<label class="col-md-3 control-label">作业描述：</label>
				<div class="col-md-6">
					<input type="text"  name="jobDescribe" class="form-control form-control-static" >
				</div>
			</div>

			<div class="form-group" style="z-index: 0">
				<label class="col-md-3 control-label">状态：</label>
				<div class="col-md-6" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input   type="radio" checked name="status" value="0">启用&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="radio" name="status" value="1">禁用
				</div>
			</div>

            </form>
            </div>
        </div><!-- /.modal-content -->
         <div class="modal-footer">
            	<!--用来清空表单数据-->
            	<input type="reset" name="reset" style="display: none;" />
               	 <button type="button" class="btn btn-default" onclick="Quartz.closeDlg()">关闭</button>
             	  <button id="saveButton" type="button" onclick="Quartz.saveJob()" class="btn btn-primary">保存</button>
         </div>
    </div><!-- /.modal -->
</div>



<!-- 修改 -->
<div id="jobUpdateDlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
    		<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改定时任务</h4>
            </div>
        <div class="modal-content">

            <div class="row">
			<form class="form-horizontal" id="updateJobForm"  method="post">
				<div class="form-group">
					<label class="col-md-3 control-label">作业名称：</label>
					<div class="col-md-6 ">
						<input type="hidden" id="update_job_id" name="jobId" >
						<input type="text" id="update_job_name" name="jobName" class="form-control form-control-static">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">任务类名称：</label>
					<div class="col-md-6">
						<input type="text" id="update_job_class_name" name="jobClassName" class="form-control form-control-static"  >
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">cron表达式：</label>
					<div class="col-md-6">
						<input type="text"  id="update_cron" name="cron" class="form-control form-control-static"  >
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">作业描述：</label>
					<div class="col-md-6">
						<input type="text"id="update_job_describe"  name="jobDescribe" class="form-control form-control-static"  >
					</div>
				</div>


			<div class="form-group">
			<label class="col-md-3 control-label">状态：</label>
				<div class="col-md-6" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="open"  type="radio" name="status" value="0">启用&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="close" type="radio" name="status" value="1">禁用
				</div>
			</div>

            </form>
            </div>
        </div><!-- /.modal-content -->
        <div class="modal-footer">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="Quartz.closeDlg()">关闭</button>
               <button type="button" onclick="Quartz.updateJob()" class="btn btn-primary">保存</button>
            </div>
    </div><!-- /.modal -->
</div>

</body>
</html>